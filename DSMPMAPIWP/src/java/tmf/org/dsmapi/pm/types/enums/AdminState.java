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
public enum AdminState {
    INVALID("INVALID"),
    LOCKED("LOCKED"),
    UNLOCKED("UNLOCKED");
    
    private String text;

    AdminState(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static AdminState fromString(String text) {
        if (text != null) {
            for (AdminState b : AdminState.values()) {
                if (text.equalsIgnoreCase(b.text)) {
                    return b;
                }
            }
        }
        return AdminState.INVALID;
    }

    public static Set<AdminState> fromStringToSet(String fields) {
        // Convert fields parameter to a set of TroubleTicketField
        Set<AdminState> fieldsSet = new HashSet<AdminState>();
        AdminState fieldName;
        if (fields != null) {
            String[] tokenArray = fields.split(",");
            for (int i = 0; i < tokenArray.length; i++) {
                fieldName = AdminState.fromString(tokenArray[i]);
                // Avoid to add null when fieldName doesn't exist
                if (fieldName != null) {
                    fieldsSet.add(fieldName);
                }
            }
        } else {
            // ALL
            fieldsSet.add(AdminState.INVALID);
        }

        return fieldsSet;
    }
}
