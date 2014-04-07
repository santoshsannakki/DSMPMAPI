package tmf.org.dsmapi.pm.services;

import java.util.Date;
import java.util.List;
import tmf.org.dsmapi.pm.entity.Measurementcollectionjob;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.ws.rs.core.MultivaluedMap;
import tmf.org.dsmapi.commons.exceptions.BadUsageException;
import tmf.org.dsmapi.commons.exceptions.ExceptionType;
import tmf.org.dsmapi.commons.exceptions.UnknownResourceException;
import tmf.org.dsmapi.commons.utils.Format;
import tmf.org.dsmapi.pm.types.enums.MeasurementCollectionJobField;

/**
 *
 * @author user
 *
 */
@Stateless
public class MeasurementCollectionJobFacade extends AbstractFacade<Measurementcollectionjob> {

    @PersistenceContext(unitName = "NewPMPU")
    private EntityManager em;
    private CriteriaBuilder cb;

    @PostConstruct
    private void init() {
        cb = em.getCriteriaBuilder();
    }

    /**
     *
     */
    public MeasurementCollectionJobFacade() {
        super(Measurementcollectionjob.class);
    }

    public Measurementcollectionjob edit(int id, Measurementcollectionjob tt) throws UnknownResourceException {
        tt.setId(id);
        return super.edit(id, tt);
    }

    /**
     *
     * @param partial Job
     * @return
     */
    public Measurementcollectionjob partialEdit(String id, Measurementcollectionjob partialJob) throws BadUsageException, UnknownResourceException {

        Measurementcollectionjob currentTT = this.find(id);

        if (currentTT == null) {
            throw new UnknownResourceException(ExceptionType.UNKNOWN_RESOURCE);
        }

        Set<MeasurementCollectionJobField> tokens = partialJob.getFieldsIN();

        for (MeasurementCollectionJobField token : tokens) {
            switch (token) {
                case ADMIN_STATE:
                    currentTT.setAdminstate(partialJob.getAdminstate());
                    break;
                case GRANULARITY:
                    currentTT.setGranularity(partialJob.getGranularity());
                    break;
                case JOB_PRIORITY:
                    currentTT.setJobpriority(partialJob.getJobpriority());
                    break;
                case MONITORED_OBJECTS_CRITERIA:
                    currentTT.setMonitoredObjectsCriteria(partialJob.getMonitoredObjectsCriteria());
                    break;
                case PERFORMANCE_INDICATOR_GROUP:
                    currentTT.setPerformanceIndicatorGroup(partialJob.getPerformanceIndicatorGroup());
                    break;
                case PERFORMANCE_INDICATOR_SPECIFICATION:
                    currentTT.setPerformanceIndicatorSpecification(partialJob.getPerformanceIndicatorSpecification());
                    break;
                case PROTOCOL_TRANSFER_DATA:
                    currentTT.setProtocolTransferData(partialJob.getProtocolTransferData());
                    break;
                case SCHEDULE_DEFINITION:
                    currentTT.setScheduleDefinition(partialJob.getScheduleDefinition());
                    break;
            }
        }

        return currentTT;

    }

    @Override
    public void create(Measurementcollectionjob job) throws BadUsageException {

        System.out.println("...CREATE CALLED"+job);
        
//        if (tt.getId() != null) {
//            throw new BadUsageException(ExceptionType.BAD_USAGE_GENERIC, "While creating a JOB id should be null");
//        }

        super.create(job);
    }

    /**
     *
     * @return
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Measurementcollectionjob> find(MultivaluedMap<String, String> queryParameters) {

        List<Measurementcollectionjob> tickets;
       //     tickets = this.findAll();
        
        if (queryParameters != null && !queryParameters.isEmpty()) {
            tickets = findByCriteria(queryParameters, Measurementcollectionjob.class);
        } else {
            tickets = this.findAll();
        }
        return tickets;

    }

    public int removeAll() {
        List<Measurementcollectionjob> tickets = this.findAll();
        int size = tickets.size();
        for (Measurementcollectionjob tt : tickets) {
            em.remove(tt);
        }
        return size;
    }
}
