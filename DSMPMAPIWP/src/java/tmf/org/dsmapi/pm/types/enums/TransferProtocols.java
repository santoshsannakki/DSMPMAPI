/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tmf.org.dsmapi.pm.types.enums;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import tmf.org.dsmapi.commons.utils.FormatUtils;
import tmf.org.dsmapi.pm.types.FileTransferData;
import tmf.org.dsmapi.pm.types.RecurringScheduleDefinition;
import tmf.org.dsmapi.pm.types.base.ProtocolTransferData;
import tmf.org.dsmapi.pm.types.base.ScheduleDefinition;
import tmf.org.dsmapi.pm.types.parser.ScheduleDefinitionParser;

/**
 *
 * @author user
 */
public enum TransferProtocols {

    FILE("FILE"),
    STREAM("STREAM"),
    UNKONWN("UNKNOWN");

    private String text;

    TransferProtocols(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static TransferProtocols fromString(String text) {
        if (text != null) {
            for (TransferProtocols b : TransferProtocols.values()) {
                if (text.equalsIgnoreCase(b.text)) {
                    return b;
                }
            }
        }
        return TransferProtocols.UNKONWN;
    }

    public static Set<TransferProtocols> fromStringToSet(String fields) {
        // Convert fields parameter to a set of TroubleTicketField
        Set<TransferProtocols> fieldsSet = new HashSet<TransferProtocols>();
        TransferProtocols fieldName;
        if (fields != null) {
            String[] tokenArray = fields.split(",");
            for (int i = 0; i < tokenArray.length; i++) {
                fieldName = TransferProtocols.fromString(tokenArray[i]);
                // Avoid to add null when fieldName doesn't exist
                if (fieldName != null) {
                    fieldsSet.add(fieldName);
                }
            }
        } else {
            // ALL
            fieldsSet.add(TransferProtocols.UNKONWN);
        }

        return fieldsSet;
    }

    public static ProtocolTransferData resolveObject(String type, String value) {
        ProtocolTransferData d = null;
        TransferProtocols sType = TransferProtocols.fromString(type);
        switch (sType) {
            case STREAM:
                return null;
            //break;
            case FILE:
                    String fileLocation = "";
                    String fileFormat = "";
                    String compressionType = "";
                    int retentionDuration = 0;
                    String packingType = "";
                
                StringTokenizer ss = new StringTokenizer(value,",");
                int count =1;
                while(ss.hasMoreTokens())
                {
                    String s = ss.nextToken();
                    switch (count)
                    {
                        case 1:
                            fileLocation = s;
                            break;
                        case 2:
                            fileFormat = s;
                            break;
                        case 3:
                            compressionType = s;
                            break;
                        case 4:
                            retentionDuration = Integer.valueOf(s);
                            break;
                        case 5:
                            packingType = s;
                            break;
                    }
                    count++;
                }
                    d = new FileTransferData(fileLocation, fileFormat, compressionType, retentionDuration, packingType);
                    System.out.println("...OBJECT::" + d);
                break;
            case UNKONWN:
                return null;
        }
        return d;
    }
}
