/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tmf.org.dsmapi.pm.types.base;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import tmf.org.dsmapi.pm.types.MonitoredClassCriteria;
import tmf.org.dsmapi.pm.types.MonitoredInstancesCriteria;

/**
 *
 * @author user
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.WRAPPER_OBJECT, property="type")
  @JsonSubTypes({
        @JsonSubTypes.Type(value=MonitoredClassCriteria.class, name="CLASS"),
        @JsonSubTypes.Type(value=MonitoredInstancesCriteria.class, name="INSTANCE")
    }) 
public abstract class MonitoredObjectsCriteria  implements java.io.Serializable{
    private String type;

    public MonitoredObjectsCriteria() {
    }
    
    
    public MonitoredObjectsCriteria(String type) {
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
        return type;
    }
    
    
}
