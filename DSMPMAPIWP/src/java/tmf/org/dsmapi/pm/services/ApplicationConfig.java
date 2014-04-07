/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tmf.org.dsmapi.pm.services;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author user
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {        
        Set<Class<?>> resources = new java.util.HashSet<>();
        // following code can be used to customize Jersey 1.x JSON provider:
        try {
            Class jacksonProvider = Class.forName("org.codehaus.jackson.jaxrs.JacksonJsonProvider");
            resources.add(jacksonProvider);
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(tmf.org.dsmapi.hub.service.HubFacadeREST.class);
        resources.add(tmf.org.dsmapi.pm.service.mapper.BadUsageExceptionMapper.class);
        resources.add(tmf.org.dsmapi.pm.service.mapper.JacksonConfigurator.class);
        resources.add(tmf.org.dsmapi.pm.service.mapper.JsonMappingExceptionMapper.class);
        resources.add(tmf.org.dsmapi.pm.service.mapper.MeasurementCollectionJobReader.class);
        resources.add(tmf.org.dsmapi.pm.service.mapper.UnknowResourceExceptionMapper.class);
        resources.add(tmf.org.dsmapi.pm.services.MeasurementCollectionJobFacadeREST.class);
       
    }
    
}
