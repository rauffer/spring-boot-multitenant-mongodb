package com.lobo.rauffer.springboot.multitenant.mongodb;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface for resolving tenant name based on the user's request.
 * 
 * @author Rauffer Lobo
 */
public interface TenantResolver {

    /**
     * Resolves the tenant name.
     * 
     * @param request the user's request
     * @return
     */
    String resolve(HttpServletRequest request);
    
}
