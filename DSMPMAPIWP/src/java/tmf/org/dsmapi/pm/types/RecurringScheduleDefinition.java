/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tmf.org.dsmapi.pm.types;

import tmf.org.dsmapi.pm.types.base.ScheduleDefinition;
import java.util.Date;
import java.util.Objects;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import tmf.org.dsmapi.pm.types.enums.ScheduleTypes;

/**
 *
 * @author user
 */
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//@JsonTypeName("RECURRING")
public class RecurringScheduleDefinition extends ScheduleDefinition{
    private Date scheduleStartTime;
    private Date scheduleEndTime;

    public RecurringScheduleDefinition() {
        super();
    }

    public RecurringScheduleDefinition(Date scheduleStartTime, Date scheduleEndTime) {
        super(ScheduleTypes.RECURRING.getText());
        this.scheduleStartTime = scheduleStartTime;
        this.scheduleEndTime = scheduleEndTime;
    }
    
    
    public RecurringScheduleDefinition(Date scheduleStartTime, Date scheduleEndTime, String type) {
        super(type);
        this.scheduleStartTime = scheduleStartTime;
        this.scheduleEndTime = scheduleEndTime;
    }

    public Date getScheduleStartTime() {
        return scheduleStartTime;
    }

    public void setScheduleStartTime(Date scheduleStartTime) {
        this.scheduleStartTime = scheduleStartTime;
    }

    public Date getScheduleEndTime() {
        return scheduleEndTime;
    }

    public void setScheduleEndTime(Date scheduleEndTime) {
        this.scheduleEndTime = scheduleEndTime;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.scheduleStartTime);
        hash = 59 * hash + Objects.hashCode(this.scheduleEndTime);
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
        final RecurringScheduleDefinition other = (RecurringScheduleDefinition) obj;
        if (!Objects.equals(this.scheduleStartTime, other.scheduleStartTime)) {
            return false;
        }
        if (!Objects.equals(this.scheduleEndTime, other.scheduleEndTime)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return (super.toString()+","+ scheduleStartTime + "," + scheduleEndTime);
    }
    
}
