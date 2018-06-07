/**
 * This class is generated by jOOQ
 */
package de.samply.bbmri.negotiator.jooq.tables.daos;


import de.samply.bbmri.negotiator.jooq.tables.Collection;
import de.samply.bbmri.negotiator.jooq.tables.records.CollectionRecord;

import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * Table to store collections from the directory
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.6.4"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CollectionDao extends DAOImpl<CollectionRecord, de.samply.bbmri.negotiator.jooq.tables.pojos.Collection, Integer> {

	/**
	 * Create a new CollectionDao without any configuration
	 */
	public CollectionDao() {
		super(Collection.COLLECTION, de.samply.bbmri.negotiator.jooq.tables.pojos.Collection.class);
	}

	/**
	 * Create a new CollectionDao with an attached configuration
	 */
	public CollectionDao(Configuration configuration) {
		super(Collection.COLLECTION, de.samply.bbmri.negotiator.jooq.tables.pojos.Collection.class, configuration);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Integer getId(de.samply.bbmri.negotiator.jooq.tables.pojos.Collection object) {
		return object.getId();
	}

	/**
	 * Fetch records that have <code>id IN (values)</code>
	 */
	public List<de.samply.bbmri.negotiator.jooq.tables.pojos.Collection> fetchById(Integer... values) {
		return fetch(Collection.COLLECTION.ID, values);
	}

	/**
	 * Fetch a unique record that has <code>id = value</code>
	 */
	public de.samply.bbmri.negotiator.jooq.tables.pojos.Collection fetchOneById(Integer value) {
		return fetchOne(Collection.COLLECTION.ID, value);
	}

	/**
	 * Fetch records that have <code>name IN (values)</code>
	 */
	public List<de.samply.bbmri.negotiator.jooq.tables.pojos.Collection> fetchByName(String... values) {
		return fetch(Collection.COLLECTION.NAME, values);
	}

	/**
	 * Fetch records that have <code>directory_id IN (values)</code>
	 */
	public List<de.samply.bbmri.negotiator.jooq.tables.pojos.Collection> fetchByDirectoryId(String... values) {
		return fetch(Collection.COLLECTION.DIRECTORY_ID, values);
	}

	/**
	 * Fetch records that have <code>directory_catalogue_id IN (values)</code>
	 */
	public List<de.samply.bbmri.negotiator.jooq.tables.pojos.Collection> fetchByDirectoryCatalogueId(Integer... values) {
		return fetch(Collection.COLLECTION.DIRECTORY_CATALOGUE_ID, values);
	}

	/**
	 * Fetch records that have <code>biobank_id IN (values)</code>
	 */
	public List<de.samply.bbmri.negotiator.jooq.tables.pojos.Collection> fetchByBiobankId(Integer... values) {
		return fetch(Collection.COLLECTION.BIOBANK_ID, values);
	}
}
