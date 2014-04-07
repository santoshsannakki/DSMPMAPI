/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tmf.org.dsmapi.pm.types.enums;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public enum MeasurementCollectionJobField {

    ALL("all"),
    ID("id"),
    ADMIN_STATE("adminState"),
    CREATION_DATE("creationDate"),
    CONSUMING_APPLICATION_ID("consumingApplicationId"),
    EXECUTION_STATE("executionState"),
    GRANULARITY("granularity"),
    JOB_PRIORITY("jobPriotity"),
    LAST_MODIFIED_TIME("lastModifiedTime"),
    MONITORED_OBJECTS_CRITERIA("monitoredObjectsCriteria"),
    OUTPUT_FORMAT("outputFormat"),
    PERFORMANCE_INDICATOR_GROUP("performanceIndicatorGroup"),
    PERFORMANCE_INDICATOR_SPECIFICATION("performanceIndicatorSpecification"),
    PRODUCING_APPLICATION_ID("producingApplicationId"),
    PROTOCOL_TRANSFER_DATA("protocolTransferData"),
    TRANSPORT_PROTOCOL("transportProtocol"),
    REPORTING_PERIOD("reportingPeriod"),
    SCHEDULE_DEFINITION("scheduleDefinition");
    
    private String text;

    MeasurementCollectionJobField(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static MeasurementCollectionJobField fromString(String text) {
        if (text != null) {
            for (MeasurementCollectionJobField b : MeasurementCollectionJobField.values()) {
                if (text.equalsIgnoreCase(b.text)) {
                    return b;
                }
            }
        }
        return null;
    }
    
    public static Set<MeasurementCollectionJobField> fromStringToSet(String fields) {
        // Convert fields parameter to a set of TroubleTicketField
        Set<MeasurementCollectionJobField> fieldsSet = new HashSet<MeasurementCollectionJobField>();
        MeasurementCollectionJobField fieldName;
        if (fields != null) {
            String[] tokenArray = fields.split(",");
            for (int i = 0; i < tokenArray.length; i++) {
                fieldName = MeasurementCollectionJobField.fromString(tokenArray[i]);
                // Avoid to add null when fieldName doesn't exist
                if (fieldName != null) {
                    fieldsSet.add(fieldName);
                }
            }
        } else {
            // ALL
            fieldsSet.add(MeasurementCollectionJobField.ALL);
        }

        return fieldsSet;
    }
}
