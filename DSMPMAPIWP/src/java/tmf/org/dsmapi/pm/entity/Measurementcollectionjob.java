package tmf.org.dsmapi.pm.entity;

import tmf.org.dsmapi.pm.types.enums.MeasurementCollectionJobField;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;
import tmf.org.dsmapi.commons.utils.FormatUtils;
import tmf.org.dsmapi.pm.types.FileTransferData;
import tmf.org.dsmapi.pm.types.MonitoredInstancesCriteria;
import tmf.org.dsmapi.pm.types.base.MonitoredObjectsCriteria;
import tmf.org.dsmapi.pm.types.base.ProtocolTransferData;
import tmf.org.dsmapi.pm.types.RecurringScheduleDefinition;
import tmf.org.dsmapi.pm.types.base.ScheduleDefinition;
import tmf.org.dsmapi.pm.types.enums.MOCriteria;
import tmf.org.dsmapi.pm.types.enums.ScheduleTypes;
import tmf.org.dsmapi.pm.types.enums.TransferProtocols;
import tmf.org.dsmapi.pm.types.parser.ScheduleDefinitionParser;

/**
 *
 * @author user
 */
@Entity
@Table(name = "MEASUREMENTCOLLECTIONJOB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Measurementcollectionjob.findAll", query = "SELECT m FROM Measurementcollectionjob m"),
    @NamedQuery(name = "Measurementcollectionjob.findById", query = "SELECT m FROM Measurementcollectionjob m WHERE m.id = :id"),
    @NamedQuery(name = "Measurementcollectionjob.findByCreationtime", query = "SELECT m FROM Measurementcollectionjob m WHERE m.creationtime = :creationtime"),
    @NamedQuery(name = "Measurementcollectionjob.findByLastmodifiedtime", query = "SELECT m FROM Measurementcollectionjob m WHERE m.lastmodifiedtime = :lastmodifiedtime"),
    @NamedQuery(name = "Measurementcollectionjob.findByAdminstate", query = "SELECT m FROM Measurementcollectionjob m WHERE m.adminstate = :adminstate"),
    @NamedQuery(name = "Measurementcollectionjob.findByJobpriority", query = "SELECT m FROM Measurementcollectionjob m WHERE m.jobpriority = :jobpriority"),
    @NamedQuery(name = "Measurementcollectionjob.findByExecutionstate", query = "SELECT m FROM Measurementcollectionjob m WHERE m.executionstate = :executionstate"),
    @NamedQuery(name = "Measurementcollectionjob.findByConsumingapplicationid", query = "SELECT m FROM Measurementcollectionjob m WHERE m.consumingapplicationid = :consumingapplicationid"),
    @NamedQuery(name = "Measurementcollectionjob.findByProducingapplicationid", query = "SELECT m FROM Measurementcollectionjob m WHERE m.producingapplicationid = :producingapplicationid"),
    @NamedQuery(name = "Measurementcollectionjob.findByGranularity", query = "SELECT m FROM Measurementcollectionjob m WHERE m.granularity = :granularity"),
    @NamedQuery(name = "Measurementcollectionjob.findByReportingperiod", query = "SELECT m FROM Measurementcollectionjob m WHERE m.reportingperiod = :reportingperiod"),
    @NamedQuery(name = "Measurementcollectionjob.findByOutputformat", query = "SELECT m FROM Measurementcollectionjob m WHERE m.outputformat = :outputformat")})
public class Measurementcollectionjob implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    //@NotNull
    @Column(name = "ID")
//    @SequenceGenerator(name="seq",sequenceName = "id_seq",initialValue = 1,allocationSize = 10000)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq")
    private Integer id;
    @Column(name = "CREATIONTIME")
    @Temporal(TemporalType.TIME)
    private Date creationtime;
    @Column(name = "LASTMODIFIEDTIME")
    @Temporal(TemporalType.TIME)
    private Date lastmodifiedtime;
    @Size(max = 50)
    @Column(name = "ADMINSTATE")
    private String adminstate;
    @Column(name = "JOBPRIORITY")
    private Integer jobpriority;
    @Size(max = 50)
    @Column(name = "EXECUTIONSTATE")
    private String executionstate;
    @Size(max = 100)
    @Column(name = "CONSUMINGAPPLICATIONID")
    private String consumingapplicationid;
    @Size(max = 100)
    @Column(name = "PRODUCINGAPPLICATIONID")
    private String producingapplicationid;
    @Size(max = 50)
    @Column(name = "GRANULARITY")
    private String granularity;
    @Size(max = 50)
    @Column(name = "REPORTINGPERIOD")
    private String reportingperiod;
    @Size(max = 50)
    @Column(name = "OUTPUTFORMAT")
    private String outputformat;
    
    @Size(max = 200)
    @Column(name = "PROTOCOLTRANSFERDATA")
    @JsonIgnore
    private String iprotocoltransferdata;
    
    @Size(max = 200)
    @Column(name = "SCHEDULEDEFINITION")
    @JsonIgnore
    private String ischeduledefinition;
    
    @Size(max = 500)
    @Column(name = "MONITOREDOBJECTSCRITERIA")
    @JsonIgnore    
    private String imonitoredobjectscriteria;
    
    @Size(max = 500)
    @Column(name = "PIGROUPS")
    @JsonIgnore
    private String ipigroups;
    
    @JsonIgnore
    @Size(max = 500)
    @Column(name = "PISPECS")
    private String ipispecs;

//    @JsonIgnore
    @Transient
    private ScheduleDefinition scheduleDefinition;
    
//    @JsonIgnore
    @Transient
    private MonitoredObjectsCriteria monitoredObjectsCriteria;
    
//    @JsonIgnore
    @Transient
    private ProtocolTransferData protocolTransferData ;
    
//    @JsonIgnore
    @Transient
    private List<String> performanceIndicatorGroup;
    
//    @JsonIgnore
    @Transient
    private List<String> performanceIndicatorSpecification;

    
    @Transient
    @JsonIgnore
    private Set<MeasurementCollectionJobField> fieldsIN;
    
    
    public ScheduleDefinition getScheduleDefinition() {
        String type = FormatUtils.getLinearTypeId(this.ischeduledefinition);
        if(type != null)
        {
            String value = FormatUtils.getLinearTypeValue(this.ischeduledefinition);
            this.scheduleDefinition = ScheduleTypes.resolveObject(type, value);            
        }
        
        return this.scheduleDefinition;
        //return new RecurringScheduleDefinition(new Date(), new Date(), "recurring");
        //return ScheduleDefinitionParser.valueOf(this.ischeduledefinition);
    }
    
    public void setScheduleDefinition(ScheduleDefinition scheduleDefinition) {
        this.scheduleDefinition = scheduleDefinition;

        String store = ScheduleTypes.getLinearString(scheduleDefinition);        
        this.ischeduledefinition = store;
    }
    

    public MonitoredObjectsCriteria getMonitoredObjectsCriteria() {
        String type = FormatUtils.getLinearTypeId(this.imonitoredobjectscriteria);
        if(type != null)
        {
            String value = FormatUtils.getLinearTypeValue(this.imonitoredobjectscriteria);
            this.monitoredObjectsCriteria = MOCriteria.resolveObject(type, value);            
        }
        
        return this.monitoredObjectsCriteria;
        
//        return new MonitoredInstancesCriteria(new String[]{"/md1/me1", "/md2/me2"}, "instance");
    }

    public void setMonitoredObjectsCriteria(MonitoredObjectsCriteria monitoredObjectsCriteria) {
        this.monitoredObjectsCriteria = monitoredObjectsCriteria;

        String store = monitoredObjectsCriteria.toString();
        this.imonitoredobjectscriteria = store;        
    }

    public ProtocolTransferData getProtocolTransferData() {
        String type = FormatUtils.getLinearTypeId(this.iprotocoltransferdata);
        if(type != null)
        {
            String value = FormatUtils.getLinearTypeValue(this.iprotocoltransferdata);
            this.protocolTransferData = TransferProtocols.resolveObject(type, value);
            
        }        
        return this.protocolTransferData;
        
//        return new FileTransferData("//x/y/z", "XML", "GZ", 3600, "TEST", "FILE");
    }

    public void setProtocolTransferData(ProtocolTransferData protocolTransferData) {
        this.protocolTransferData = protocolTransferData;

        String store = protocolTransferData.toString();
        this.iprotocoltransferdata = store;
        
    }

    public List<String> getPerformanceIndicatorGroup() {
        List<String> str = FormatUtils.getDisplayList(ipigroups);
        this.performanceIndicatorGroup = str;
        return str;
    }

    public void setPerformanceIndicatorGroup(List<String> performanceIndicatorGroup) {
        this.performanceIndicatorGroup = performanceIndicatorGroup;
        String str= FormatUtils.getDBString(performanceIndicatorGroup);
        this.ipigroups = str;
        
    }

    public List<String> getPerformanceIndicatorSpecification() {
        List<String> str = FormatUtils.getDisplayList(ipispecs);
        this.performanceIndicatorSpecification = str;
        return str;
    }

    public void setPerformanceIndicatorSpecification(List<String> performanceIndicatorSpecification) {
        this.performanceIndicatorSpecification = performanceIndicatorSpecification;
        String str= FormatUtils.getDBString(performanceIndicatorSpecification);
        this.ipispecs = str;
    }
    
    public Measurementcollectionjob() {
    }

    public Measurementcollectionjob(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(Date creationtime) {
        this.creationtime = creationtime;
    }

    public Date getLastmodifiedtime() {
        return lastmodifiedtime;
    }

    public void setLastmodifiedtime(Date lastmodifiedtime) {
        this.lastmodifiedtime = lastmodifiedtime;
    }

    public String getAdminstate() {
        return adminstate;
    }

    public void setAdminstate(String adminstate) {
        this.adminstate = adminstate;
    }

    public Integer getJobpriority() {
        return jobpriority;
    }

    public void setJobpriority(Integer jobpriority) {
        this.jobpriority = jobpriority;
    }

    public String getExecutionstate() {
        return executionstate;
    }

    public void setExecutionstate(String executionstate) {
        this.executionstate = executionstate;
    }

    public String getConsumingapplicationid() {
        return consumingapplicationid;
    }

    public void setConsumingapplicationid(String consumingapplicationid) {
        this.consumingapplicationid = consumingapplicationid;
    }

    public String getProducingapplicationid() {
        return producingapplicationid;
    }

    public void setProducingapplicationid(String producingapplicationid) {
        this.producingapplicationid = producingapplicationid;
    }

    public String getGranularity() {
        return granularity;
    }

    public void setGranularity(String granularity) {
        this.granularity = granularity;
    }

    public String getReportingperiod() {
        return reportingperiod;
    }

    public void setReportingperiod(String reportingperiod) {
        this.reportingperiod = reportingperiod;
    }

    public String getOutputformat() {
        return outputformat;
    }

    public void setOutputformat(String outputformat) {
        this.outputformat = outputformat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Measurementcollectionjob)) {
            return false;
        }
        Measurementcollectionjob other = (Measurementcollectionjob) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tmf.org.dsmapi.pm.entity.Measurementcollectionjob[ id=" + id + " ]";
    }

    /**
     * @return the fieldsIN
     */
    public Set<MeasurementCollectionJobField> getFieldsIN() {
        return fieldsIN;
    }

    /**
     * @param fieldsIN the fieldsIN to set
     */
    public void setFieldsIN(Set<MeasurementCollectionJobField> fields) {
        this.fieldsIN = fields;
    }
    

}
