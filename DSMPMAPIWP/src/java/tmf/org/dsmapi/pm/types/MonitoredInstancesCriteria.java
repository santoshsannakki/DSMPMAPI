/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tmf.org.dsmapi.pm.types;

import tmf.org.dsmapi.pm.types.base.MonitoredObjectsCriteria;
import java.util.Arrays;
import org.codehaus.jackson.annotate.JsonTypeName;
import tmf.org.dsmapi.pm.types.enums.MOCriteria;

/**
 *
 * @author user
 */
@JsonTypeName("INSTANCE")
public class MonitoredInstancesCriteria extends MonitoredObjectsCriteria{
    private String monitoredObjectInstances[];

    public MonitoredInstancesCriteria() {
        super();
    }
    
    public MonitoredInstancesCriteria(String[] monitoredObjectInstances, String type) {
        super(type);
        this.monitoredObjectInstances = monitoredObjectInstances;
    }

    public MonitoredInstancesCriteria(String[] monitoredObjectInstances) {
        super(MOCriteria.INSTANCE.getText());
        this.monitoredObjectInstances = monitoredObjectInstances;
    }
    
    public String[] getMonitoredObjectInstances() {
        return monitoredObjectInstances;
    }

    public void setMonitoredObjectInstances(String[] monitoredObjectInstances) {
        this.monitoredObjectInstances = monitoredObjectInstances;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Arrays.deepHashCode(this.monitoredObjectInstances);
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
        final MonitoredInstancesCriteria other = (MonitoredInstancesCriteria) obj;
        if (!Arrays.deepEquals(this.monitoredObjectInstances, other.monitoredObjectInstances)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String str = "";
        for(String s:monitoredObjectInstances)
        {
            str += ","+s;
        }
        return super.toString()+str;
    }
    
    
}
