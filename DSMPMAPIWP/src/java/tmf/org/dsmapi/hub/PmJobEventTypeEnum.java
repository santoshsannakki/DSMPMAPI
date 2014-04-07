/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tmf.org.dsmapi.hub;

/**
 *
 * @author pierregauthier
 */
public enum PmJobEventTypeEnum {

    JobCreateNotification("JobCreateNotification"),
    JobStatusChangedNotification("JobStatusChangedNotification"),
    JobChangedNotification("JobChangedNotification");
    
    private String text;

    PmJobEventTypeEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static tmf.org.dsmapi.hub.PmJobEventTypeEnum fromString(String text) {
        if (text != null) {
            for (PmJobEventTypeEnum b : PmJobEventTypeEnum.values()) {
                if (text.equalsIgnoreCase(b.text)) {
                    return b;
                }
            }
        }
        return null;
    }
}
