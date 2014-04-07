/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tmf.org.dsmapi.pm.types.enums;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import tmf.org.dsmapi.commons.utils.FormatUtils;
import tmf.org.dsmapi.pm.types.RecurringScheduleDefinition;
import tmf.org.dsmapi.pm.types.base.ScheduleDefinition;
import tmf.org.dsmapi.pm.types.parser.ScheduleDefinitionParser;

/**
 *
 * @author user
 */
public enum ScheduleTypes {

    FIXED("FIXED"),
    RECURRING("RECURRING"),
    UNKONWN("UNKNOWN");

    private String text;

    ScheduleTypes(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static ScheduleTypes fromString(String text) {
        if (text != null) {
            for (ScheduleTypes b : ScheduleTypes.values()) {
                if (text.equalsIgnoreCase(b.text)) {
                    return b;
                }
            }
        }
        return ScheduleTypes.UNKONWN;
    }

    public static Set<ScheduleTypes> fromStringToSet(String fields) {
        // Convert fields parameter to a set of TroubleTicketField
        Set<ScheduleTypes> fieldsSet = new HashSet<ScheduleTypes>();
        ScheduleTypes fieldName;
        if (fields != null) {
            String[] tokenArray = fields.split(",");
            for (int i = 0; i < tokenArray.length; i++) {
                fieldName = ScheduleTypes.fromString(tokenArray[i]);
                // Avoid to add null when fieldName doesn't exist
                if (fieldName != null) {
                    fieldsSet.add(fieldName);
                }
            }
        } else {
            // ALL
            fieldsSet.add(ScheduleTypes.UNKONWN);
        }

        return fieldsSet;
    }

    public static ScheduleDefinition resolveObject(String type, String value) {
        ScheduleDefinition d = null;
        ScheduleTypes sType = ScheduleTypes.fromString(type);
        switch (sType) {
            case FIXED:
                return null;
            //break;
            case RECURRING:
                String startTime = value.substring(0, value.indexOf(","));
                System.out.println("......VALUE RECURRING S/E 1" + startTime);

                String endTime = value.substring(value.indexOf(",") + 1, value.length());
                System.out.println("......VALUE RECURRING S/E 2" + startTime + ", end:" + endTime);

                try {
                    d = new RecurringScheduleDefinition(FormatUtils.parse(startTime), FormatUtils.parse(endTime));
                    System.out.println("...OBJECT::" + d);
                } catch (ParseException ex) {
                    Logger.getLogger(ScheduleDefinitionParser.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("..ERROR:" + ex);
                }

                break;
            case UNKONWN:
                return null;
        }
        return d;
    }

    public static String getLinearString(ScheduleDefinition sch) {
        String linear = "";

        try {

            ScheduleTypes sType = ScheduleTypes.fromString(sch.getType());
            switch (sType) {
                case FIXED:
                    return null;
                //break;
                case RECURRING:
                    linear += RECURRING.getText()+",";
                    linear += FormatUtils.format(((RecurringScheduleDefinition) sch).getScheduleStartTime()) + ",";
                    linear += FormatUtils.format(((RecurringScheduleDefinition) sch).getScheduleStartTime());
                    break;
                case UNKONWN:
                    return null;
            }
        } catch (ParseException ex) {
            Logger.getLogger(ScheduleTypes.class.getName()).log(Level.SEVERE, null, ex);
            linear="";
        }
        return linear;
    }
}
