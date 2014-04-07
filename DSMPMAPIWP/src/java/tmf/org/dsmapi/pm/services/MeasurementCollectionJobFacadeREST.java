package tmf.org.dsmapi.pm.services;

import tmf.org.dsmapi.pm.service.mapper.FacadeRestUtil;
import tmf.org.dsmapi.commons.jaxrs.PATCH;
import tmf.org.dsmapi.pm.entity.Measurementcollectionjob;
import tmf.org.dsmapi.pm.types.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import org.codehaus.jackson.node.ObjectNode;
import tmf.org.dsmapi.commons.exceptions.BadUsageException;
import tmf.org.dsmapi.commons.exceptions.UnknownResourceException;
import tmf.org.dsmapi.hub.service.PublisherLocal;
//import tmf.org.dsmapi.hub.service.PublisherLocal;
import tmf.org.dsmapi.pm.services.MeasurementCollectionJobFacade;
import tmf.org.dsmapi.pm.types.enums.AdminState;
import tmf.org.dsmapi.pm.types.enums.ExecutionState;
import tmf.org.dsmapi.pm.types.enums.Granularity;
import tmf.org.dsmapi.pm.types.enums.MeasurementCollectionJobField;

/**
 *
 * @author pierregauthier
 */
@Stateless
@Path("measurementCollectionJob")
public class MeasurementCollectionJobFacadeREST {

    @Context
    UriInfo uriInfo;
    @EJB
    MeasurementCollectionJobFacade manager;
//    @EJB
    PublisherLocal publisher;

    public MeasurementCollectionJobFacadeREST() {
    }

    /*
     * RESOURCE
     * troubleTicket
     */
    @GET
    @Produces({"application/json"})
    public Response list(@Context UriInfo info) {

        Response response = null;
        // search queryParameters
//        MultivaluedMap<String, String> queryParameters = info.getQueryParameters();
//        // fields to filter view
//        Set<String> fieldSet = FacadeRestUtil.getFieldSet(queryParameters);
//
//        List<Measurementcollectionjob> resultList = manager.find(queryParameters);
//
//        if (fieldSet.isEmpty() || fieldSet.contains(FacadeRestUtil.ALL_FIELDS)) {
//
//            response = Response.ok(resultList).build();
//
//        } else {
//
//            fieldSet.add(FacadeRestUtil.ID_FIELD);
//            List<ObjectNode> nodeList = new ArrayList<ObjectNode>();
//            ObjectNode node;
//            for (Measurementcollectionjob tt : resultList) {
//                node = FacadeRestUtil.createNodeViewWithFields(tt, fieldSet);
//                nodeList.add(node);
//            }
//            response = Response.ok(nodeList).build();
//
//        }
        List<Measurementcollectionjob> resultList = manager.findAll();
        List<Measurementcollectionjob> list2 = new ArrayList<Measurementcollectionjob>();
        list2.addAll(resultList);
        response = Response.ok(list2).build();

        return response;
    }

    public void preProcess(Measurementcollectionjob entity)
    {
        for (MeasurementCollectionJobField token : MeasurementCollectionJobField.values()) {
            switch (token) {
                case CREATION_DATE:
                    if(entity.getCreationtime() == null)
                    {
                        entity.setCreationtime(new Date());
                    }
                break;
                case LAST_MODIFIED_TIME:
                    if(entity.getLastmodifiedtime() == null)
                    {
                        entity.setLastmodifiedtime(new Date());
                    }
                break;
                case ADMIN_STATE:
                    if(entity.getAdminstate() == null || 
                            AdminState.fromString(entity.getAdminstate()) == AdminState.INVALID)
                    {
                       entity.setAdminstate(AdminState.UNLOCKED.getText());
                    }
                    break;
                case GRANULARITY:
                    if(entity.getGranularity() == null || 
                            Granularity.fromString(entity.getGranularity()) == Granularity.GINVALID)
                    {
                       entity.setAdminstate(Granularity.GINVALID.getText());
                    }
                    break;
                case JOB_PRIORITY:
                    if(entity.getJobpriority() == null || !(entity.getJobpriority()>0 && entity.getJobpriority()<=10))
                    {
                        entity.setJobpriority(5);                        
                    }
                    break;
                case EXECUTION_STATE:
                    if(entity.getExecutionstate() == null)
                    {
                        entity.setExecutionstate(ExecutionState.CREATED.getText());                        
                    }
                    break;
            }
        }
    }
    
    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response post(Measurementcollectionjob entity) throws BadUsageException {

        preProcess(entity);
        manager.create(entity);

        System.out.println("Calling  Publish");
        System.out.println("Calling  Publish1");
        System.out.println(entity.getLastmodifiedtime());
        publisher.publishJobCreateNotification(entity);
        
        System.out.println("After Calling  Publish");

        // 201 OK + location
        UriBuilder uriBuilder = UriBuilder.fromUri(uriInfo.getRequestUri());
        int id = entity.getId();
        uriBuilder.path("{id}");
        return Response.created(uriBuilder.build(id)).entity(entity).build();
    }

    /*
     * RESOURCE
     * measurementCollectionJob/{id}
     */
    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response put(@PathParam("id") int id, Measurementcollectionjob entity) throws UnknownResourceException {

        // Try to merge        
        entity = manager.edit(id, entity);
//
//        publisher.publishTicketChangedNotification(entity);
//        publisher.publishTicketStatusChangedNotification(entity);

        // 201 OK + location
        UriBuilder uriBuilder = UriBuilder.fromUri(uriInfo.getRequestUri());
        uriBuilder.path("{id}");
        return Response.created(uriBuilder.build(id)).
                entity(entity).
                build();
    }

    /*
     * RESOURCE
     * measurementCollectionJob/{id}
     */
    @DELETE
    @Path("{id}")
    @Consumes({"application/json"})
    public Response delete(@PathParam("id") int id, Measurementcollectionjob entity) throws UnknownResourceException {

        // Try to merge        
        manager.remove(id);

//        publisher.publishTicketChangedNotification(entity);
//        publisher.publishTicketStatusChangedNotification(entity);

        // 201 OK + location
        UriBuilder uriBuilder = UriBuilder.fromUri(uriInfo.getRequestUri());
        uriBuilder.path("{id}");
        return Response.created(uriBuilder.build(id)).
                entity(entity).
                build();
    }
    
    
    //X-HTTP-Method-Override on POST
    @PATCH
    @Path("{id}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response patch(@PathParam("id") String id, Measurementcollectionjob pJob) throws BadUsageException, UnknownResourceException {

        Measurementcollectionjob fJob = manager.partialEdit(id, pJob);

//        publisher.publishTicketChangedNotification(partialTT);
//        publisher.publishTicketStatusChangedNotification(partialTT);

        // 201 OK + location
        UriBuilder uriBuilder = UriBuilder.fromUri(uriInfo.getRequestUri());
//        id = fullTT.getId();
        uriBuilder.path("{id}");
        return Response.created(uriBuilder.build(id)).
                entity(fJob).
                build();


    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response get(@PathParam("id") int id, @Context UriInfo info) throws UnknownResourceException {
        
        Response response = null;
        Measurementcollectionjob tt = manager.find(id);
        
//        MultivaluedMap<String, String> queryParameters = info.getQueryParameters();
//        Set<String> fieldSet = FacadeRestUtil.getFieldSet(queryParameters);
//
//        if (fieldSet.isEmpty() || fieldSet.contains(FacadeRestUtil.ALL_FIELDS)) {
//            response = Response.ok(tt).build();
//        } else {
//            fieldSet.add(FacadeRestUtil.ID_FIELD);
//            ObjectNode root = FacadeRestUtil.createNodeViewWithFields(tt, fieldSet);
//            response = Response.ok(root).build();
//        }        
            response = Response.ok(tt).build();

        return response;
    }
    
}
