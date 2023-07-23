package com.algaworks.ecommerce.hibernate;

//import org.hibernate.engine.config.spi.ConfigurationService;
//import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
//import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
//import org.hibernate.hikaricp.internal.HikariCPConnectionProvider;
//import org.hibernate.service.spi.ServiceRegistryAwareService;
//import org.hibernate.service.spi.ServiceRegistryImplementor;
//import org.hibernate.service.spi.Startable;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class EcmMachineMultiTenantConnectionProvider
//        implements MultiTenantConnectionProvider, ServiceRegistryAwareService, Startable
{

//    private Map<String, ConnectionProvider> connectionProviders = null;
//
//    private Map<String, Object> properties = null;
//
//    @Override
//    public Connection getConnection(String tenantIdentifier) throws SQLException {
//        Connection connection = connectionProviders
//                .get(tenantIdentifier)
//                .getConnection();
//
//        return connection;
//    }
//
//    @Override
//    public Connection getAnyConnection() throws SQLException {
//        return getAnyConnectionProvider()
//                .getConnection();
//    }
//
//    @Override
//    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
//        releaseAnyConnection(connection);
//    }
//
//    @Override
//    public void releaseAnyConnection(Connection connection) throws SQLException {
//        getAnyConnectionProvider().closeConnection(connection);
//    }
//
//    @Override
//    public boolean supportsAggressiveRelease() {
//        return getAnyConnectionProvider().supportsAggressiveRelease();
//    }
//
//    @Override
//    public boolean isUnwrappableAs(Class unwrapType) {
//        return getAnyConnectionProvider().isUnwrappableAs(unwrapType);
//    }
//
//    @Override
//    public <T> T unwrap(Class<T> unwrapType) {
//        return getAnyConnectionProvider().unwrap(unwrapType);
//    }
//
//    @Override
//    public void injectServices(ServiceRegistryImplementor serviceRegistry) {
//        this.properties = serviceRegistry
//                .getService(ConfigurationService.class)
//                .getSettings();
//    }
//
//    @Override
//    public void start() {
//        connectionProviders = new HashMap<>();
//
//        configurarTenant("algaworks_ecommerce",
//                "jdbc:mysql://localhost/algaworks_ecommerce?" +
//                        "createDatabaseIfNotExist=true&useTimezone=true&serverTimezone=UTC",
//                "root",
//                "1234");
//
//        configurarTenant("loja_ecommerce",
//                "jdbc:mysql://localhost/loja_ecommerce?" +
//                        "createDatabaseIfNotExist=true&useTimezone=true&serverTimezone=UTC",
//                "root",
//                "1234");
//
//        this.properties = null;
//    }
//
//    private void configurarTenant(String tenant,
//                                         String url, String usuario, String senha) {
//        Map<String, Object> props = new HashMap<>(this.properties);
//
//        props.put("jakarta.persistence.jdbc.url", url);
//        props.put("hibernate.connection.url", url);
//
//        props.put("jakarta.persistence.jdbc.user", usuario);
//        props.put("hibernate.connection.username", usuario);
//
//        props.put("jakarta.persistence.jdbc.password", senha);
//        props.put("hibernate.connection.password", senha);
//
//        HikariCPConnectionProvider cp = new HikariCPConnectionProvider();
//        cp.configure(props);
//
//        this.connectionProviders.put(tenant, cp);
//    }
//
//    private ConnectionProvider getAnyConnectionProvider() {
//        return connectionProviders
//                .values().iterator().next();
//    }
}