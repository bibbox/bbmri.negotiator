package de.samply.bbmri.negotiator.rest.dto;

import de.samply.bbmri.negotiator.Config;
import de.samply.bbmri.negotiator.ConfigFactory;
import de.samply.bbmri.negotiator.db.util.DbUtil;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@XmlRootElement
public class DirectoryQueriesDTO {

    @XmlElement(name = "directoryqueries")
    private Collection<QueryDTO> directoryqueries;

    public Collection<QueryDTO> getDirectoryqueries() {
        return directoryqueries;
    }

    public void setDirectoryqueries(Collection<QueryDTO> directoryqueries) {
        this.directoryqueries = directoryqueries;
    }
}
