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
public enum Granularity {
    G15MIN("15MIN"),
    G24HR("24HR"),
    G5MIN("5MIN"),
    G1MIN("1MIN"),
    GINVALID("INVALID");
    
    private String text;

    Granularity(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static Granularity fromString(String text) {
        if (text != null) {
            for (Granularity b : Granularity.values()) {
                if (text.equalsIgnoreCase(b.text)) {
                    return b;
                }
            }
        }
        return Granularity.GINVALID;
    }

    public static Set<Granularity> fromStringToSet(String fields) {
        // Convert fields parameter to a set of TroubleTicketField
        Set<Granularity> fieldsSet = new HashSet<Granularity>();
        Granularity fieldName;
        if (fields != null) {
            String[] tokenArray = fields.split(",");
            for (int i = 0; i < tokenArray.length; i++) {
                fieldName = Granularity.fromString(tokenArray[i]);
                // Avoid to add null when fieldName doesn't exist
                if (fieldName != null) {
                    fieldsSet.add(fieldName);
                }
            }
        } else {
            // ALL
            fieldsSet.add(Granularity.GINVALID);
        }

        return fieldsSet;
    }
}
