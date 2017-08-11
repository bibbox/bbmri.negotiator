/**
 * This class is generated by jOOQ
 */
package de.samply.bbmri.negotiator.jooq;


import de.samply.bbmri.negotiator.jooq.tables.Biobank;
import de.samply.bbmri.negotiator.jooq.tables.Collection;
import de.samply.bbmri.negotiator.jooq.tables.Comment;
import de.samply.bbmri.negotiator.jooq.tables.ConnectorLog;
import de.samply.bbmri.negotiator.jooq.tables.FlaggedQuery;
import de.samply.bbmri.negotiator.jooq.tables.JsonQuery;
import de.samply.bbmri.negotiator.jooq.tables.Offer;
import de.samply.bbmri.negotiator.jooq.tables.Person;
import de.samply.bbmri.negotiator.jooq.tables.PersonCollection;
import de.samply.bbmri.negotiator.jooq.tables.Query;
import de.samply.bbmri.negotiator.jooq.tables.QueryAttachment;
import de.samply.bbmri.negotiator.jooq.tables.QueryCollection;
import de.samply.bbmri.negotiator.jooq.tables.Tag;
import de.samply.bbmri.negotiator.jooq.tables.TaggedQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Sequence;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.6.4"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

	private static final long serialVersionUID = 1022191354;

	/**
	 * The reference instance of <code>public</code>
	 */
	public static final Public PUBLIC = new Public();

	/**
	 * No further instances allowed
	 */
	private Public() {
		super("public");
	}

	@Override
	public final List<Sequence<?>> getSequences() {
		List result = new ArrayList();
		result.addAll(getSequences0());
		return result;
	}

	private final List<Sequence<?>> getSequences0() {
		return Arrays.<Sequence<?>>asList(
			Sequences.BIOBANK_ID_SEQ,
			Sequences.COLLECTION_ID_SEQ,
			Sequences.COMMENT_ID_SEQ,
			Sequences.CONNECTOR_LOG_ID_SEQ,
			Sequences.JSON_QUERY_ID_SEQ,
			Sequences.OFFER_ID_SEQ,
			Sequences.PERSON_ID_SEQ,
			Sequences.QUERY_ATTACHMENT_ID_SEQ,
			Sequences.QUERY_ID_SEQ,
			Sequences.TAG_ID_SEQ);
	}

	@Override
	public final List<Table<?>> getTables() {
		List result = new ArrayList();
		result.addAll(getTables0());
		return result;
	}

	private final List<Table<?>> getTables0() {
		return Arrays.<Table<?>>asList(
			Biobank.BIOBANK,
			Collection.COLLECTION,
			Comment.COMMENT,
			ConnectorLog.CONNECTOR_LOG,
			FlaggedQuery.FLAGGED_QUERY,
			JsonQuery.JSON_QUERY,
			Offer.OFFER,
			Person.PERSON,
			PersonCollection.PERSON_COLLECTION,
			Query.QUERY,
			QueryAttachment.QUERY_ATTACHMENT,
			QueryCollection.QUERY_COLLECTION,
			Tag.TAG,
			TaggedQuery.TAGGED_QUERY);
	}
}