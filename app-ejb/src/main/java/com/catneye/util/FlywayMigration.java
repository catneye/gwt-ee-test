/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catneye.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.Location;

/**
 *
 * @author plintus
 */
@Singleton
@Startup
@TransactionManagement(value=TransactionManagementType.BEAN)
public class FlywayMigration {

    @Resource(lookup = "java:jboss/datasources/testDS")
    private DataSource dataSource;

    @PostConstruct
    public void initFlyWay() {
        Location location=new Location("classpath:flyway");
        Flyway flyway = Flyway.configure().cleanDisabled(false).locations(location).dataSource(dataSource).load();

        Logger.getLogger(FlywayMigration.class.getName()).log(Level.INFO, 
                "getPlaceholders : {0}", flyway.getConfiguration().getPlaceholders());
        Logger.getLogger(FlywayMigration.class.getName()).log(Level.INFO, 
                "getLocations : {0}", flyway.getConfiguration().getLocations());
        //flyway.clean();
        flyway.baseline();
        flyway.migrate();
    }
}
