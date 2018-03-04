package com.lobo.rauffer.springboot.multitenant.mongodb;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import com.mongodb.DB;
import com.mongodb.MongoClient;

/**
 * Factory to create {@link DB} instances based on the user's request.
 * 
 * @author Rauffer Lobo
 */
public class MultitenantMongoDbFactory extends SimpleMongoDbFactory {

    @Autowired
    private HttpServletRequest request;
    
    private TenantResolver tenantResolver;
    
    @Value("${spring.data.mongodb.tenant-prefix:${spring.data.mongodb.database}}-")
    private String databasePrefix;
    
    public MultitenantMongoDbFactory(MongoClient mongoClient, String databaseName) {
        super(mongoClient, databaseName);
    }
    
    @Autowired(required=false)
    public void setTenantResolver(TenantResolver tenantResolver) {
        this.tenantResolver = tenantResolver;
    }

    @Override
    public DB getDb() throws DataAccessException {

        if(tenantResolver == null) {
            return super.getDb();
        }
        
        String tenant = tenantResolver.resolve(request);

        if(tenant == null) {
            return super.getDb();
        }

        return getDb(databasePrefix + tenant);

    }

}