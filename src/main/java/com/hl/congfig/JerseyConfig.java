package com.hl.congfig;

import org.glassfish.jersey.jackson.JacksonFeature;  
import org.glassfish.jersey.server.ResourceConfig;  
import org.springframework.stereotype.Component;  
  
/*@Component*/
public class JerseyConfig extends ResourceConfig {  
  
    public JerseyConfig() {  
        register(JacksonFeature.class);  
        packages("com.hl");  
    }  
}  
