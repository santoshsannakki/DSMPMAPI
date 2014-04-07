/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tmf.org.dsmapi.hub;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import tmf.org.dsmapi.pm.entity.Measurementcollectionjob;

/**
 *
 * @author pierregauthier
 */
@XmlRootElement
public class HubEvent implements Serializable {

    private Measurementcollectionjob event; //checl for object
    private PmJobEventTypeEnum eventType;

    public Measurementcollectionjob getEvent() {
        return event;
    }

    public void setEvent(Measurementcollectionjob event) {
        this.event = event;
    }

    public PmJobEventTypeEnum getEventType() {
        return eventType;
    }

    public void setEventType(PmJobEventTypeEnum eventType) {
        this.eventType = eventType;
    }

    @Override
    public String toString() {
        return "HubEvent{" + "event=" + event + ", eventType=" + eventType + '}';
    }
}
