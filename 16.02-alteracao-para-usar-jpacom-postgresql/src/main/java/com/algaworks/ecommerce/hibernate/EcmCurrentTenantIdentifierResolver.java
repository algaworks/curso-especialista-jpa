package com.algaworks.ecommerce.hibernate;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

public class EcmCurrentTenantIdentifierResolver implements CurrentTenantIdentifierResolver {

    private static ThreadLocal<String> tl = new ThreadLocal<>();

    public static void setTenantIdentifier(String tenantId) {
        tl.set(tenantId);
    }

    @Override
    public String resolveCurrentTenantIdentifier() {
        return tl.get();
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return false;
    }
}
