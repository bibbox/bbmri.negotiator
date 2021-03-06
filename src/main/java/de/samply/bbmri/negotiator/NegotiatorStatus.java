/*
 * Copyright (c) 2017. Medizinische Informatik in der Translationalen Onkologie,
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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class NegotiatorStatus {

    /**
     * The negotiator Status instance.
     */
    private static final NegotiatorStatus instance = new NegotiatorStatus();

    /**
     * The map for the last successful statuses
     */
    private Map<NegotiatorTaskType, NegotiatorTaskStatus> successMap = new HashMap<>();

    /**
     * The map for the last failed statuses
     */
    private Map<NegotiatorTaskType, NegotiatorTaskStatus> failMap = new HashMap<>();

    public enum NegotiatorTaskType {
        /**
         * The synchronization with the directory (including the biobanks and collections)
         */
        DIRECTORY,

        /**
         * The list of users from Perun
         */
        PERUN_USER,

        /**
         * The mapping between the users and the collections
         */
        PERUN_MAPPING
    }

    /**
     * A status contains the time+date and the error message, if there was an error.
     */
    public static class NegotiatorTaskStatus {

        /**
         * The date of the status
         */
        private Date date = new Date(0);

        /**
         * The error message, if the was one.
         */
        private String message = "";

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    /**
     * The private constructor.
     */
    private NegotiatorStatus() {
        successMap.put(NegotiatorTaskType.DIRECTORY, new NegotiatorTaskStatus());
        successMap.put(NegotiatorTaskType.PERUN_USER, new NegotiatorTaskStatus());
        successMap.put(NegotiatorTaskType.PERUN_MAPPING, new NegotiatorTaskStatus());

        failMap.put(NegotiatorTaskType.DIRECTORY, new NegotiatorTaskStatus());
        failMap.put(NegotiatorTaskType.PERUN_USER, new NegotiatorTaskStatus());
        failMap.put(NegotiatorTaskType.PERUN_MAPPING, new NegotiatorTaskStatus());
    }

    /**
     * Creates a new successful status for the given type.
     * @param type
     */
    public void newSuccessStatus(NegotiatorTaskType type, String message) {
        NegotiatorTaskStatus status = new NegotiatorTaskStatus();
        status.setDate(new Date());
        status.setMessage(message);
        successMap.put(type, status);
    }

    /**
     * Creates a new failed status for the given type with the given message
     * @param type
     * @param message
     */
    public void newFailStatus(NegotiatorTaskType type, String message) {
        NegotiatorTaskStatus status = new NegotiatorTaskStatus();
        status.setDate(new Date());
        status.setMessage(message);
        failMap.put(type, status);
    }

    /**
     * Returns the singleton instance.
     * @return
     */
    public static NegotiatorStatus get() {
        return instance;
    }

    /**
     * Returns the map with the successful statuses
     * @return
     */
    public Map<NegotiatorTaskType, NegotiatorTaskStatus> getSuccessMap() {
        return successMap;
    }

    /**
     * Returns the map with the failed statuses
     * @return
     */
    public Map<NegotiatorTaskType, NegotiatorTaskStatus> getFailMap() {
        return failMap;
    }

}
