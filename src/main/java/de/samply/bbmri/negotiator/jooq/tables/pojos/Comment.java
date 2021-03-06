/**
 * This class is generated by jOOQ
 */
package de.samply.bbmri.negotiator.jooq.tables.pojos;


import java.io.Serializable;
import java.sql.Timestamp;

import javax.annotation.Generated;


/**
 * table to store commentCount on a query
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.6.4"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Comment implements Serializable {

	private static final long serialVersionUID = -2105032786;

	private Integer   id;
	private Integer   queryId;
	private Integer   personId;
	private Timestamp commentTime;
	private String    text;

	public Comment() {}

	public Comment(Comment value) {
		this.id = value.id;
		this.queryId = value.queryId;
		this.personId = value.personId;
		this.commentTime = value.commentTime;
		this.text = value.text;
	}

	public Comment(
		Integer   id,
		Integer   queryId,
		Integer   personId,
		Timestamp commentTime,
		String    text
	) {
		this.id = id;
		this.queryId = queryId;
		this.personId = personId;
		this.commentTime = commentTime;
		this.text = text;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQueryId() {
		return this.queryId;
	}

	public void setQueryId(Integer queryId) {
		this.queryId = queryId;
	}

	public Integer getPersonId() {
		return this.personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public Timestamp getCommentTime() {
		return this.commentTime;
	}

	public void setCommentTime(Timestamp commentTime) {
		this.commentTime = commentTime;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
