package com.lobo.rauffer.springboot.multitenant.mongodb;

import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.mongodb.DB;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MultitenantMongoDbFactoryTest {

    @Autowired
    private MultitenantMongoDbFactory multitenantMongoDbFactory;

    @Test
    public void mustReturnDefaultDatabaseNameWhenThereisnotATenantResolver() {

        multitenantMongoDbFactory.setTenantResolver(null);

        DB database = multitenantMongoDbFactory.getDb();

        Assert.assertEquals("sample", database.getName());

    }
    
    @Test
    public void mustReturnDefaultDatabaseNameWhenTenantResolverReturnsNull() {

        multitenantMongoDbFactory.setTenantResolver(new TenantResolver() {
            @Override
            public String resolve(HttpServletRequest request) {
                return null;
            }
        });

        DB database = multitenantMongoDbFactory.getDb();

        Assert.assertEquals("sample", database.getName());

    }

    @Test
    public void mustReturnDatabaseWithTenantNameSufixed() {

        multitenantMongoDbFactory.setTenantResolver(new TenantResolver() {
            @Override
            public String resolve(HttpServletRequest request) {
                return "tenant1";
            }
        });

        DB database = multitenantMongoDbFactory.getDb();

        Assert.assertEquals("sample-tenant1", database.getName());

    }

}
