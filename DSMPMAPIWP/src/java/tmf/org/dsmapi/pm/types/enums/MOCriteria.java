/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tmf.org.dsmapi.pm.types.enums;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import tmf.org.dsmapi.commons.utils.FormatUtils;
import tmf.org.dsmapi.pm.types.MonitoredInstancesCriteria;
import tmf.org.dsmapi.pm.types.RecurringScheduleDefinition;
import tmf.org.dsmapi.pm.types.base.MonitoredObjectsCriteria;
import tmf.org.dsmapi.pm.types.base.ScheduleDefinition;
import tmf.org.dsmapi.pm.types.parser.ScheduleDefinitionParser;

/**
 *
 * @author user
 */
public enum MOCriteria {

    INSTANCE("INSTANCE"),
    CLASS("CLASS"),
    UNKONWN("UNKNOWN");

    private String text;

    MOCriteria(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static MOCriteria fromString(String text) {
        if (text != null) {
            for (MOCriteria b : MOCriteria.values()) {
                if (text.equalsIgnoreCase(b.text)) {
                    return b;
                }
            }
        }
        return MOCriteria.UNKONWN;
    }

    public static Set<MOCriteria> fromStringToSet(String fields) {
        // Convert fields parameter to a set of TroubleTicketField
        Set<MOCriteria> fieldsSet = new HashSet<MOCriteria>();
        MOCriteria fieldName;
        if (fields != null) {
            String[] tokenArray = fields.split(",");
            for (int i = 0; i < tokenArray.length; i++) {
                fieldName = MOCriteria.fromString(tokenArray[i]);
                // Avoid to add null when fieldName doesn't exist
                if (fieldName != null) {
                    fieldsSet.add(fieldName);
                }
            }
        } else {
            // ALL
            fieldsSet.add(MOCriteria.UNKONWN);
        }

        return fieldsSet;
    }

    public static MonitoredObjectsCriteria resolveObject(String type, String value) {
        MonitoredObjectsCriteria d = null;
        MOCriteria sType = MOCriteria.fromString(type);
        switch (sType) {
            case CLASS:
                return null;
            //break;
            case INSTANCE:

                StringTokenizer s = new StringTokenizer(value,",");
                List<String> ss = new ArrayList<String>();
                while(s.hasMoreTokens())
                {
                    ss.add(s.nextToken());
                }
                
                d = new MonitoredInstancesCriteria(ss.toArray(new String[ss.size()]));
                System.out.println("...OBJECT::" + d);

                break;
            case UNKONWN:
                return null;
        }
        return d;
    }

    public static String getLinearString(ScheduleDefinition sch) {
        String linear = "";


            MOCriteria sType = MOCriteria.fromString(sch.getType());
            switch (sType) {
                case CLASS:
                    return null;
                //break;
                case INSTANCE:
                    break;
                case UNKONWN:
                    return null;
            }
        return linear;
    }
}
