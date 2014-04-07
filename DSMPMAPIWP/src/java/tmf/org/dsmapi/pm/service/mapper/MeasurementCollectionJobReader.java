package tmf.org.dsmapi.pm.service.mapper;

import tmf.org.dsmapi.pm.types.enums.MeasurementCollectionJobField;
import tmf.org.dsmapi.pm.entity.Measurementcollectionjob;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.ObjectMapper;
import tmf.org.dsmapi.commons.utils.FormatUtils;
import tmf.org.dsmapi.pm.types.FileTransferData;
import tmf.org.dsmapi.pm.types.MonitoredInstancesCriteria;
import tmf.org.dsmapi.pm.types.RecurringScheduleDefinition;
import tmf.org.dsmapi.pm.types.enums.MOCriteria;
import tmf.org.dsmapi.pm.types.enums.ScheduleTypes;
import tmf.org.dsmapi.pm.types.enums.TransferProtocols;

/**
 *
 */
@Provider
@Consumes({"application/json"})
public class MeasurementCollectionJobReader implements MessageBodyReader<Measurementcollectionjob> {

    /**
     *
     * @param type
     * @param genericType
     * @param annotations
     * @param mediaType
     * @return
     */
    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Measurementcollectionjob.class.isAssignableFrom(type);
    }

    /**
     *
     * @param type
     * @param type1
     * @param antns
     * @param mediaType
     * @param httpHeaders
     * @param entityStream
     * @return
     * @throws IOException
     * @throws WebApplicationException
     */
    @Override
    public Measurementcollectionjob readFrom(Class<Measurementcollectionjob> type,
            Type type1,
            Annotation[] antns,
            MediaType mediaType,
            MultivaluedMap<String, String> httpHeaders,
            InputStream entityStream)
            throws IOException, WebApplicationException {

        Measurementcollectionjob job = new Measurementcollectionjob();

        System.out.println("=== MeasurementCollectionJob MessageBodyReader ENABLE ===");

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readValue(entityStream, JsonNode.class);
        //JsonNode root = mapper.readTree(entityStream);

        Set<MeasurementCollectionJobField> tokenList = new HashSet<MeasurementCollectionJobField>();

        Iterator<Map.Entry<String, JsonNode>> nn = root.getFields();
        while (nn.hasNext()) {
            Map.Entry<String, JsonNode> entry = nn.next();
            String key = entry.getKey();
            JsonNode value = entry.getValue();            
            MeasurementCollectionJobField field = MeasurementCollectionJobField.fromString(key);
            
            if (field != null) {
                mapField(field, root, key, job);
            }

        }

//        System.out.println(".,.,. GET ELEM::::");
//        Iterator<JsonNode> el = root.getElements();
//        while (el.hasNext()) {
//            JsonNode rNode = el.next();
//            System.out.println(rNode);
//            Iterator<String> it = rNode.getFieldNames();
//            while (it.hasNext()) {
//                String node = it.next();
//                MeasurementCollectionJobField field = MeasurementCollectionJobField.fromString(node);
//                tokenList.add(field);
//                System.out.println("-+=++++++++++++++ FIELD::::" + field);
//                if (field != null) {
//                    mapField(field, rNode, node, job);
//                }
//            }
//            job.setFieldsIN(tokenList);
//        }

        return job;
    }

    private void mapField(MeasurementCollectionJobField field, JsonNode rNode,
            String node, Measurementcollectionjob job) {
        switch (field) {
            case ID:
                job.setId(rNode.findValue(node).getIntValue());            
                break;
            case ADMIN_STATE:
                job.setAdminstate(rNode.findValue(node).getTextValue());                
                break;
            case CONSUMING_APPLICATION_ID:
                job.setConsumingapplicationid(rNode.findValue(node).getTextValue());                
                break;
            case PRODUCING_APPLICATION_ID:
                job.setProducingapplicationid(rNode.findValue(node).getTextValue());                
                break;
            case CREATION_DATE:
                try {
                    job.setCreationtime(FormatUtils.parse(rNode.findValue(node).getTextValue()));
                } catch (ParseException ex) {
                    Logger.getLogger(MeasurementCollectionJobReader.class.getName()).log(Level.SEVERE, null, ex);
                }                
                break;
            case LAST_MODIFIED_TIME:
                try {
                    job.setLastmodifiedtime(FormatUtils.parse(rNode.findValue(node).getTextValue()));
                } catch (ParseException ex) {
                    Logger.getLogger(MeasurementCollectionJobReader.class.getName()).log(Level.SEVERE, null, ex);
                }                
                break;
            case JOB_PRIORITY:
                job.setJobpriority(rNode.findValue(node).getIntValue());                
                break;
            case EXECUTION_STATE:
                job.setExecutionstate(rNode.findValue(node).getTextValue());                
                break;
            case GRANULARITY:
                job.setGranularity(rNode.findValue(node).getTextValue());                
                break;
            case REPORTING_PERIOD:
                job.setReportingperiod(rNode.findValue(node).getTextValue());                
                break;
            case OUTPUT_FORMAT:
                job.setOutputformat(rNode.findValue(node).getTextValue());                
                break;
            case SCHEDULE_DEFINITION:
                JsonNode sch = rNode.findValue(node);                

                String type = sch.findValue("type").getTextValue();
                if (type.equalsIgnoreCase(ScheduleTypes.RECURRING.getText())) {
                    try {
                        Date start = FormatUtils.parse(sch.findValue("scheduleStartTime").getTextValue());
                        Date end = FormatUtils.parse(sch.findValue("scheduleEndTime").getTextValue());
                        job.setScheduleDefinition(new RecurringScheduleDefinition(start, end));

                    } catch (ParseException ex) {
                        Logger.getLogger(MeasurementCollectionJobReader.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case MONITORED_OBJECTS_CRITERIA:
                JsonNode mon = rNode.findValue(node);
                String monType = mon.findValue("type").getTextValue();

                if (monType.equalsIgnoreCase(MOCriteria.INSTANCE.getText())) {
                    JsonNode inst = mon.findValue("monitoredObjectInstances");
                    Iterator<JsonNode> insts = inst.getElements();
                    List<String> inputs = new ArrayList<String>();

                    while (insts.hasNext()) {
                        inputs.add(insts.next().getTextValue());
                    }
                    
                    String[] monitoredObjectInstances = inputs.toArray(new String[inputs.size()]);
                    job.setMonitoredObjectsCriteria(new MonitoredInstancesCriteria(monitoredObjectInstances));
                }
                break;
            case PROTOCOL_TRANSFER_DATA:
                JsonNode prot = rNode.findValue(node);

                String pType = prot.findValue("transportProtocol").getTextValue();
                if (pType.equalsIgnoreCase(TransferProtocols.FILE.getText())) {
                    String fileLocation = prot.findValue("fileLocation").getTextValue();
                                        
                    String fileFormat = prot.findValue("fileFormat").getTextValue();
                                        
                    String compressionType = prot.findValue("compressionType").getTextValue();
                                        
                    int retentionDuration = prot.findValue("retentionDuration").getIntValue();
                                        
                    String packingType = prot.findValue("packingType").getTextValue();
                                        
                    job.setProtocolTransferData(
                            new FileTransferData(fileLocation, fileFormat, compressionType, retentionDuration, packingType));

                }
                break;
            case PERFORMANCE_INDICATOR_GROUP:
                JsonNode pig = rNode.findValue(node);

                    Iterator<JsonNode> pigs = pig.getElements();
                    List<String> piginputs = new ArrayList<String>();

                    while (pigs.hasNext()) {
                        piginputs.add(pigs.next().get("name").getTextValue());
                    }
                    job.setPerformanceIndicatorGroup(piginputs);
                
                break;
            case PERFORMANCE_INDICATOR_SPECIFICATION:
                JsonNode pis = rNode.findValue(node);                

                    Iterator<JsonNode> piss = pis.getElements();
                    List<String> pisinputs = new ArrayList<String>();

                    while (piss.hasNext()) {
                        pisinputs.add(piss.next().get("name").getTextValue());
                    }
                    job.setPerformanceIndicatorSpecification(pisinputs);
                break;
        }
    }
}
