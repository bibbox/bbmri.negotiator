/**
 * This class is generated by jOOQ
 */
package de.samply.bbmri.negotiator.jooq.tables.daos;


import de.samply.bbmri.negotiator.jooq.tables.Query;
import de.samply.bbmri.negotiator.jooq.tables.records.QueryRecord;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * query table to contain all  queries
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.6.4"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class QueryDao extends DAOImpl<QueryRecord, de.samply.bbmri.negotiator.jooq.tables.pojos.Query, Integer> {

	/**
	 * Create a new QueryDao without any configuration
	 */
	public QueryDao() {
		super(Query.QUERY, de.samply.bbmri.negotiator.jooq.tables.pojos.Query.class);
	}

	/**
	 * Create a new QueryDao with an attached configuration
	 */
	public QueryDao(Configuration configuration) {
		super(Query.QUERY, de.samply.bbmri.negotiator.jooq.tables.pojos.Query.class, configuration);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Integer getId(de.samply.bbmri.negotiator.jooq.tables.pojos.Query object) {
		return object.getId();
	}

	/**
	 * Fetch records that have <code>id IN (values)</code>
	 */
	public List<de.samply.bbmri.negotiator.jooq.tables.pojos.Query> fetchById(Integer... values) {
		return fetch(Query.QUERY.ID, values);
	}

	/**
	 * Fetch a unique record that has <code>id = value</code>
	 */
	public de.samply.bbmri.negotiator.jooq.tables.pojos.Query fetchOneById(Integer value) {
		return fetchOne(Query.QUERY.ID, value);
	}

	/**
	 * Fetch records that have <code>title IN (values)</code>
	 */
	public List<de.samply.bbmri.negotiator.jooq.tables.pojos.Query> fetchByTitle(String... values) {
		return fetch(Query.QUERY.TITLE, values);
	}

	/**
	 * Fetch records that have <code>text IN (values)</code>
	 */
	public List<de.samply.bbmri.negotiator.jooq.tables.pojos.Query> fetchByText(String... values) {
		return fetch(Query.QUERY.TEXT, values);
	}

	/**
	 * Fetch records that have <code>query_creation_time IN (values)</code>
	 */
	public List<de.samply.bbmri.negotiator.jooq.tables.pojos.Query> fetchByQueryCreationTime(Timestamp... values) {
		return fetch(Query.QUERY.QUERY_CREATION_TIME, values);
	}

	/**
	 * Fetch records that have <code>researcher_id IN (values)</code>
	 */
	public List<de.samply.bbmri.negotiator.jooq.tables.pojos.Query> fetchByResearcherId(Integer... values) {
		return fetch(Query.QUERY.RESEARCHER_ID, values);
	}

	/**
	 * Fetch records that have <code>json_text IN (values)</code>
	 */
	public List<de.samply.bbmri.negotiator.jooq.tables.pojos.Query> fetchByJsonText(String... values) {
		return fetch(Query.QUERY.JSON_TEXT, values);
	}

	/**
	 * Fetch records that have <code>num_attachments IN (values)</code>
	 */
	public List<de.samply.bbmri.negotiator.jooq.tables.pojos.Query> fetchByNumAttachments(Integer... values) {
		return fetch(Query.QUERY.NUM_ATTACHMENTS, values);
	}

	/**
	 * Fetch records that have <code>negotiator_token IN (values)</code>
	 */
	public List<de.samply.bbmri.negotiator.jooq.tables.pojos.Query> fetchByNegotiatorToken(String... values) {
		return fetch(Query.QUERY.NEGOTIATOR_TOKEN, values);
	}

	/**
	 * Fetch a unique record that has <code>negotiator_token = value</code>
	 */
	public de.samply.bbmri.negotiator.jooq.tables.pojos.Query fetchOneByNegotiatorToken(String value) {
		return fetchOne(Query.QUERY.NEGOTIATOR_TOKEN, value);
	}

	/**
	 * Fetch records that have <code>valid_query IN (values)</code>
	 */
	public List<de.samply.bbmri.negotiator.jooq.tables.pojos.Query> fetchByValidQuery(Boolean... values) {
		return fetch(Query.QUERY.VALID_QUERY, values);
	}

	/**
	 * Fetch records that have <code>request_description IN (values)</code>
	 */
	public List<de.samply.bbmri.negotiator.jooq.tables.pojos.Query> fetchByRequestDescription(String... values) {
		return fetch(Query.QUERY.REQUEST_DESCRIPTION, values);
	}

	/**
	 * Fetch records that have <code>ethics_vote IN (values)</code>
	 */
	public List<de.samply.bbmri.negotiator.jooq.tables.pojos.Query> fetchByEthicsVote(String... values) {
		return fetch(Query.QUERY.ETHICS_VOTE, values);
	}
}