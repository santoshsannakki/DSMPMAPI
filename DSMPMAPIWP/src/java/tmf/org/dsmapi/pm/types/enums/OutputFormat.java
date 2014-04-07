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
public enum OutputFormat {
    O_3GPP("3GPP"),
    O_MTOSI("MTOSI");
    
    private String text;

    OutputFormat(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static OutputFormat fromString(String text) {
        if (text != null) {
            for (OutputFormat b : OutputFormat.values()) {
                if (text.equalsIgnoreCase(b.text)) {
                    return b;
                }
            }
        }
        return OutputFormat.O_3GPP;
    }

    public static Set<OutputFormat> fromStringToSet(String fields) {
        // Convert fields parameter to a set of TroubleTicketField
        Set<OutputFormat> fieldsSet = new HashSet<OutputFormat>();
        OutputFormat fieldName;
        if (fields != null) {
            String[] tokenArray = fields.split(",");
            for (int i = 0; i < tokenArray.length; i++) {
                fieldName = OutputFormat.fromString(tokenArray[i]);
                // Avoid to add null when fieldName doesn't exist
                if (fieldName != null) {
                    fieldsSet.add(fieldName);
                }
            }
        } else {
            // ALL
            fieldsSet.add(OutputFormat.O_3GPP);
        }

        return fieldsSet;
    }
}
