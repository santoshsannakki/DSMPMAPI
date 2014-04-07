/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tmf.org.dsmapi.pm.types.enums;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author user
 */
public enum ExecutionState {
    SCHEDULED("SCHEDULED"),
    STOPPED("STOPPED"),
    SUSPENDED("SUSPENDED"),
    EXECUTING_IDLE("EXECUTING_IDLE"),
    EXECUTING_BUSY("EXECUTING_BUSY"),
    CREATED("CREATED"),
    DELETED("DELETED"),
    INVALID("INVALID");
    
    private String text;

    ExecutionState(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static ExecutionState fromString(String text) {
        if (text != null) {
            for (ExecutionState b : ExecutionState.values()) {
                if (text.equalsIgnoreCase(b.text)) {
                    return b;
                }
            }
        }
        return ExecutionState.INVALID;
    }

    public static Set<ExecutionState> fromStringToSet(String fields) {
        // Convert fields parameter to a set of TroubleTicketField
        Set<ExecutionState> fieldsSet = new HashSet<ExecutionState>();
        ExecutionState fieldName;
        if (fields != null) {
            String[] tokenArray = fields.split(",");
            for (int i = 0; i < tokenArray.length; i++) {
                fieldName = ExecutionState.fromString(tokenArray[i]);
                // Avoid to add null when fieldName doesn't exist
                if (fieldName != null) {
                    fieldsSet.add(fieldName);
                }
            }
        } else {
            // ALL
            fieldsSet.add(ExecutionState.INVALID);
        }

        return fieldsSet;
    }
    
}
