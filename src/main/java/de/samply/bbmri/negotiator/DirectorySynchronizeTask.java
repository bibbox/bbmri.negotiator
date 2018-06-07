/**
 * Copyright (C) 2016 Medizinische Informatik in der Translationalen Onkologie,
 * Deutsches Krebsforschungszentrum in Heidelberg
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option) any
 * later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program; if not, see http://www.gnu.org/licenses.
 *
 * Additional permission under GNU GPL version 3 section 7:
 *
 * If you modify this Program, or any covered work, by linking or combining it
 * with Jersey (https://jersey.java.net) (or a modified version of that
 * library), containing parts covered by the terms of the General Public
 * License, version 2.0, the licensors of this Program grant you additional
 * permission to convey the resulting work.
 */

package de.samply.bbmri.negotiator;

import java.util.List;
import java.util.TimerTask;

import de.samply.bbmri.negotiator.jooq.tables.records.DirectoryCatalogueRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.samply.bbmri.negotiator.config.Negotiator;
import de.samply.bbmri.negotiator.db.util.DbUtil;
import de.samply.directory.client.DirectoryClient;
import de.samply.directory.client.dto.DirectoryBiobank;
import de.samply.directory.client.dto.DirectoryCollection;

/**
 * Handles the perdiodical synchonization between the directory and our negotiator.
 */
public class DirectorySynchronizeTask extends TimerTask {

    /**
     *
     */
    private final static Logger logger = LoggerFactory.getLogger(DirectorySynchronizeTask.class);

    private Config config = null;

    public void setTestConfig(Config config) {
        this.config = config;
    }

    @Override
    public void run() {
        try(Config config = (this.config!=null?this.config:ConfigFactory.get())) {
            List<DirectoryCatalogueRecord> directories = DbUtil.getDirectories(config);
            for(DirectoryCatalogueRecord directoryCatalogueRecord : directories) {
                if (directoryCatalogueRecord.getSyncActive()) {
                    runDirectorySync(directoryCatalogueRecord.getId(), directoryCatalogueRecord.getName(), directoryCatalogueRecord.getRestUrl(), directoryCatalogueRecord.getResourceBiobanks(),
                            directoryCatalogueRecord.getResourceCollections(), directoryCatalogueRecord.getApiUsername(), directoryCatalogueRecord.getApiPassword());
                }
            }
        } catch (Exception e) {
            logger.error("Synchronization all directories failed", e);
            NegotiatorStatus.get().newFailStatus(NegotiatorStatus.NegotiatorTaskType.DIRECTORY, e.getMessage());
        }
    }

    public void runDirectorySync(int directoryCatalogueId, String name, String dirBaseUrl, String resourceBiobanks, String resourceCollections, String username, String password) {
        try(Config config = (this.config!=null?this.config:ConfigFactory.get())) {
            Negotiator negotiatorConfig = NegotiatorConfig.get().getNegotiator();

            DirectoryClient client = new DirectoryClient(dirBaseUrl,
                    resourceBiobanks, resourceCollections,
                    username, password);

            logger.info("Starting synchronization with the directory " + name);

            List<DirectoryBiobank> allBiobanks = client.getAllBiobanks();

            for(DirectoryBiobank dto : allBiobanks) {
                DbUtil.synchronizeBiobank(config, dto, directoryCatalogueId);
            }

            List<DirectoryCollection> allCollections = client.getAllCollections();

            for(DirectoryCollection dto : allCollections) {
                DbUtil.synchronizeCollection(config, dto, directoryCatalogueId);
            }

            logger.info("Synchronization with the directory finished.");
            config.commit();

            NegotiatorStatus.get().newSuccessStatus(NegotiatorStatus.NegotiatorTaskType.DIRECTORY,
                    "Biobanks: " + allBiobanks.size() + ", Collections: " + allCollections.size());
        } catch (Exception e) {
            logger.error("Synchronization failed with " + name + " url: " + dirBaseUrl, e);
            NegotiatorStatus.get().newFailStatus(NegotiatorStatus.NegotiatorTaskType.DIRECTORY, e.getMessage());
        }
    }
}
