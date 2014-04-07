/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tmf.org.dsmapi.pm.types;

import tmf.org.dsmapi.pm.types.base.MonitoredObjectsCriteria;
import java.util.Objects;
import org.codehaus.jackson.annotate.JsonTypeName;

/**
 *
 * @author user
 */
@JsonTypeName("CLASS")
public class MonitoredClassCriteria extends MonitoredObjectsCriteria{
    private String monitoredObjectClass;
    private String objectInstanceFilter;

    public MonitoredClassCriteria() {
        super();
    }
    
    public MonitoredClassCriteria(String monitoredObjectClass, String objectInstanceFilter, String type) {
        super(type);
        this.monitoredObjectClass = monitoredObjectClass;
        this.objectInstanceFilter = objectInstanceFilter;
    }

    public String getMonitoredObjectClass() {
        return monitoredObjectClass;
    }

    public void setMonitoredObjectClass(String monitoredObjectClass) {
        this.monitoredObjectClass = monitoredObjectClass;
    }

    public String getObjectInstanceFilter() {
        return objectInstanceFilter;
    }

    public void setObjectInstanceFilter(String objectInstanceFilter) {
        this.objectInstanceFilter = objectInstanceFilter;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.monitoredObjectClass);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MonitoredClassCriteria other = (MonitoredClassCriteria) obj;
        if (!Objects.equals(this.monitoredObjectClass, other.monitoredObjectClass)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString()+"," + monitoredObjectClass + "," + objectInstanceFilter;
    }
    
    
}
