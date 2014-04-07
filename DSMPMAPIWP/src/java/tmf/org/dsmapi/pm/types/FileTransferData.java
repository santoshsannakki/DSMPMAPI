/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tmf.org.dsmapi.pm.types;

import tmf.org.dsmapi.pm.types.base.ProtocolTransferData;
import java.util.Objects;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import tmf.org.dsmapi.pm.types.enums.TransferProtocols;

/**
 *
 * @author user
 */
@JsonTypeName("FILE")
public class FileTransferData extends ProtocolTransferData{
    private String fileLocation;
    private String fileFormat;
    private String compressionType;
    private int retentionDuration;
    private String packingType;

    public FileTransferData() {
        super();
    }
    
    public FileTransferData(String fileLocation, String fileFormat, String compressionType, int retentionDuration, String packingType, String transportProtocol) {
        super(transportProtocol);
        this.fileLocation = fileLocation;
        this.fileFormat = fileFormat;
        this.compressionType = compressionType;
        this.retentionDuration = retentionDuration;
        this.packingType = packingType;
    }

    public FileTransferData(String fileLocation, String fileFormat, String compressionType, int retentionDuration, String packingType) {
        super(TransferProtocols.FILE.getText());
        this.fileLocation = fileLocation;
        this.fileFormat = fileFormat;
        this.compressionType = compressionType;
        this.retentionDuration = retentionDuration;
        this.packingType = packingType;
    }
    
    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public String getCompressionType() {
        return compressionType;
    }

    public void setCompressionType(String compressionType) {
        this.compressionType = compressionType;
    }

    public int getRetentionDuration() {
        return retentionDuration;
    }

    public void setRetentionDuration(int retentionDuration) {
        this.retentionDuration = retentionDuration;
    }

    public String getPackingType() {
        return packingType;
    }

    public void setPackingType(String packingType) {
        this.packingType = packingType;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.fileLocation);
        hash = 89 * hash + Objects.hashCode(this.fileFormat);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FileTransferData other = (FileTransferData) obj;
        if (!Objects.equals(this.fileLocation, other.fileLocation)) {
            return false;
        }
        if (!Objects.equals(this.fileFormat, other.fileFormat)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString()+"," + this.fileLocation + "," + this.fileFormat + "," + this.compressionType + "," + this.retentionDuration + "," + this.packingType;
    }
}
