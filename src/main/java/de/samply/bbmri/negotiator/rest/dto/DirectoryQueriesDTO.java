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

    public String getFormatedHumanReadableHTMLElement() {
        try(Config config = ConfigFactory.get()) {

            String humanReadableQueryData = "<div style=\"display: table;width: auto;\">";
            for(QueryDTO query : directoryqueries) {
                String directoryName = DbUtil.getDirectoryCatalogueName(config, query.getUrl());
                humanReadableQueryData += "<div style=\"display:table-row;width:auto;\"><div style=\"float:left;display:table-column;width:200px;\">" + directoryName + "</div><div style=\"float:left;display:table-column;\">" + query.getHumanReadable() + "</div></div>";
            }
            humanReadableQueryData += "</div>";
            return humanReadableQueryData;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
}
