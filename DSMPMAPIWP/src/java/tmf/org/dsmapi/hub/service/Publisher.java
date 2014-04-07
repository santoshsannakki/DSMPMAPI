/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tmf.org.dsmapi.hub.service;

import java.util.Iterator;
import java.util.List;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import tmf.org.dsmapi.hub.Hub;
import tmf.org.dsmapi.hub.HubEvent;
import tmf.org.dsmapi.hub.PmJobEventTypeEnum;
import tmf.org.dsmapi.pm.entity.Measurementcollectionjob;

/**
 *
 * @author pierregauthier should be async or called with MDB
 */
@Stateless
//@Asynchronous bug in 7.3
@Asynchronous
public class Publisher implements PublisherLocal {

    @EJB
    HubFacade hubFacade;
    @EJB
    RESTEventPublisherLocal restEventPublisher;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    //Access Hubs using callbacks and send to http publisher 
    //(pool should be configured around the RESTEventPublisher bean)
    //Loop into array of Hubs
    //Call RestEventPublisher - Need to implement resend policy plus eviction
    //Filtering is done in RestEventPublisher based on query expression
    @Override
    public void publish(Object event) {
  
        System.out.println("Sending Event");

        List<Hub> hubList = hubFacade.findAll();
        Iterator<Hub> it = hubList.iterator();
        while (it.hasNext()) {
            Hub hub = it.next();
            restEventPublisher.publish(hub, event);


            //Thread.currentThread().sleep(1000);
        }
        System.out.println("Sending Event After");
    }

    @Override
 public void publishJobCreateNotification(Measurementcollectionjob tt) {
  
        HubEvent event = new HubEvent();
        event.setEvent(tt);
        event.setEventType(PmJobEventTypeEnum.JobCreateNotification);
        publish(event);

    }

    @Override
    public void publishJobStatusChangedNotification(Measurementcollectionjob tt) {

        HubEvent event = new HubEvent();
        event.setEvent(tt);
        event.setEventType(PmJobEventTypeEnum.JobStatusChangedNotification);
        publish(event);

    }

    @Override
    public void publishJobChangedNotification(Measurementcollectionjob tt) {

        HubEvent event = new HubEvent();
        event.setEvent(tt);
        event.setEventType(PmJobEventTypeEnum.JobChangedNotification);
        publish(event);

    }
}
