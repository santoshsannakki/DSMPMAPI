/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tmf.org.dsmapi.hub.service;

import javax.ejb.Local;
import tmf.org.dsmapi.pm.entity.Measurementcollectionjob;

/**
 *
 * @author pierregauthier
 */
@Local
public interface PublisherLocal {

   void publish(Object event);

    public void publishJobCreateNotification(Measurementcollectionjob tt);

    public void publishJobStatusChangedNotification(Measurementcollectionjob tt);

    public void publishJobChangedNotification(Measurementcollectionjob tt);
}
