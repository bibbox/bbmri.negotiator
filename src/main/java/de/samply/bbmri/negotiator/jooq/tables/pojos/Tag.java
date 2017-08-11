/**
 * This class is generated by jOOQ
 */
package de.samply.bbmri.negotiator.jooq.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;


/**
 * Table that contains tags for queries
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.6.4"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tag implements Serializable {

	private static final long serialVersionUID = 142400267;

	private Integer id;
	private Integer queryId;
	private String  text;

	public Tag() {}

	public Tag(Tag value) {
		this.id = value.id;
		this.queryId = value.queryId;
		this.text = value.text;
	}

	public Tag(
		Integer id,
		Integer queryId,
		String  text
	) {
		this.id = id;
		this.queryId = queryId;
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

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}
}