package com.algaworks.ecommerce.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.engine.config.spi.ConfigurationService;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.hikaricp.internal.HikariCPConnectionProvider;
import org.hibernate.service.spi.ServiceRegistryAwareService;
import org.hibernate.service.spi.ServiceRegistryImplementor;
import org.hibernate.service.spi.Startable;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class EcmSchemaMultiTenantConnectionProvider implements
        MultiTenantConnectionProvider, ServiceRegistryAwareService, Startable {

    private Map<String, String> properties = null;

    private ConnectionProvider connectionProvider = null;

    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        Connection connection = getAnyConnection();

        try {
            connection.createStatement().execute("use " + tenantIdentifier);
        } catch (SQLException e) {
            throw new HibernateException("Não foi possível alterar " +
                    "para o schema " + tenantIdentifier + ".", e);
        }

        return connection;
    }

    @Override
    public Connection getAnyConnection() throws SQLException {
        return connectionProvider.getConnection();
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        releaseAnyConnection(connection);
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connectionProvider.closeConnection(connection);
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return connectionProvider.supportsAggressiveRelease();
    }

    @Override
    public boolean isUnwrappableAs(Class unwrapType) {
        return connectionProvider.isUnwrappableAs(unwrapType);
    }

    @Override
    public <T> T unwrap(Class<T> unwrapType) {
        return connectionProvider.unwrap(unwrapType);
    }

    @Override
    public void injectServices(ServiceRegistryImplementor serviceRegistry) {
        this.properties = serviceRegistry
                .getService(ConfigurationService.class)
                .getSettings();
    }

    @Override
    public void start() {
        HikariCPConnectionProvider cp = new HikariCPConnectionProvider();
        cp.configure(this.properties);

        connectionProvider = cp;
    }
}