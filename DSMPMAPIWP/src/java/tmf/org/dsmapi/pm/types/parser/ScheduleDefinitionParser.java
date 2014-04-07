/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tmf.org.dsmapi.pm.types.parser;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tmf.org.dsmapi.commons.utils.FormatUtils;
import tmf.org.dsmapi.pm.types.RecurringScheduleDefinition;
import tmf.org.dsmapi.pm.types.base.ScheduleDefinition;

/**
 *
 * @author user
 */
public class ScheduleDefinitionParser {
    public static ScheduleDefinition valueOf(String s)
    {        
        String type = s.substring(0, s.indexOf(","));
             
        String value = s.substring(s.indexOf(",")+1, s.length());
             
        switch(type)
        {
            case "RECURRING":
            case "recurring":
                
                String startTime = value.substring(0, value.indexOf(","));
                
                String endTime = value.substring(value.indexOf(",")+1, value.length());
                
        try {
            ScheduleDefinition d = new RecurringScheduleDefinition(FormatUtils.parse(startTime), FormatUtils.parse(endTime));;
            return d;
        } catch (ParseException ex) {
            Logger.getLogger(ScheduleDefinitionParser.class.getName()).log(Level.SEVERE, null, ex);
        }
                break;
            case "FIXED":
            default:
                return null;
        }
        return null;
    }
}
