/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tmf.org.dsmapi.pm.types.base;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import tmf.org.dsmapi.pm.types.RecurringScheduleDefinition;

/**
 *
 * @author user
 */
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=As.WRAPPER_OBJECT, property="type")
//  @JsonSubTypes({
//        @JsonSubTypes.Type(value=RecurringScheduleDefinition.class, name="RECURRING"),
//    }) 
public abstract class ScheduleDefinition implements java.io.Serializable{
    private String type;

    public ScheduleDefinition() {
    }
    
    
    public ScheduleDefinition(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return (type);
    }
    
}
