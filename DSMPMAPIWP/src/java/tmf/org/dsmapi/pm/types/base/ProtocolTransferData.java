/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tmf.org.dsmapi.pm.types.base;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import tmf.org.dsmapi.pm.types.FileTransferData;

/**
 *
 * @author user
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.WRAPPER_OBJECT, property="type")
  @JsonSubTypes({
        @JsonSubTypes.Type(value=FileTransferData.class, name="FILE"),
    }) 
public abstract class ProtocolTransferData implements java.io.Serializable{
    private String transportProtocol;

    public ProtocolTransferData() {
    }
    
    public ProtocolTransferData(String transportProtocol) {
        this.transportProtocol = transportProtocol;
    }

    public String getTransportProtocol() {
        return transportProtocol;
    }

    public void setTransportProtocol(String transportProtocol) {
        this.transportProtocol = transportProtocol;
    }

    @Override
    public String toString() {
        return transportProtocol;
    }
    
    
}
