CREATE TYPE "role_type" AS ENUM ('OWNER', 'RESEARCHER');
CREATE TYPE "flag" AS ENUM ('UNFLAGGED', 'ARCHIVED', 'IGNORED', 'STARRED');

CREATE TABLE directory_catalogue (
    "id" SERIAL NOT NULL,
    "name" CHARACTER VARYING(255) NOT NULL,
    "url" CHARACTER VARYING(255) NOT NULL,
    "rest_url" CHARACTER VARYING(255) NOT NULL,
    "username" CHARACTER VARYING(255) NOT NULL,
    "password" CHARACTER VARYING(255) NOT NULL,
    "api_username" CHARACTER VARYING(255) NOT NULL,
    "api_password" CHARACTER VARYING(255) NOT NULL,
    "resource_biobanks" CHARACTER VARYING(255) NOT NULL,
    "resource_collections" CHARACTER VARYING(255) NOT NULL,
    "description" TEXT,
    "sync_active" BOOLEAN,
    PRIMARY KEY ("id")
);

COMMENT ON TABLE directory_catalogue IS 'Table to store directorys';
COMMENT ON COLUMN directory_catalogue."id" IS 'primary key';
COMMENT ON COLUMN directory_catalogue."name" IS 'The directory name';
COMMENT ON COLUMN directory_catalogue."url" IS 'The directory url';
COMMENT ON COLUMN directory_catalogue."rest_url" IS 'The directory API url';
COMMENT ON COLUMN directory_catalogue."username" IS 'The directories username';
COMMENT ON COLUMN directory_catalogue."password" IS 'The directoryíes password';
COMMENT ON COLUMN directory_catalogue."api_username" IS 'The directories API username';
COMMENT ON COLUMN directory_catalogue."api_password" IS 'The directories API password';
COMMENT ON COLUMN directory_catalogue."resource_biobanks" IS 'The directories biobank model';
COMMENT ON COLUMN directory_catalogue."resource_collections" IS 'The directories collection model';
COMMENT ON COLUMN directory_catalogue."description" IS 'The description for this directory';



CREATE TABLE biobank (
    "id" SERIAL NOT NULL,
    "name" CHARACTER VARYING(255) NOT NULL,
    "description" TEXT,
    "directory_catalogue_id" INTEGER REFERENCES directory_catalogue("id") ON UPDATE CASCADE ON DELETE CASCADE,
    "directory_id" CHARACTER VARYING(255) NOT NULL,
    PRIMARY KEY ("id"),
    unique(directory_id, directory_catalogue_id)
);


COMMENT ON TABLE biobank IS 'Table to store biobanks from the directory';
COMMENT ON COLUMN biobank."id" IS 'primary key';
COMMENT ON COLUMN biobank."name" IS 'The biobank name';
COMMENT ON COLUMN biobank."description" IS 'The description for this biobank';
COMMENT ON COLUMN biobank."directory_catalogue_id" IS 'The Directory Catalogue this biobank belongs to';
COMMENT ON COLUMN biobank."directory_id" IS 'The directory ID, e.g. eu_bbmri_eric_biobank:NL45';



CREATE TABLE collection (
    "id" SERIAL NOT NULL,
    "name" TEXT NOT NULL,
    "directory_id" CHARACTER VARYING(255) NOT NULL,
    "directory_catalogue_id" INTEGER REFERENCES directory_catalogue("id") ON UPDATE CASCADE ON DELETE CASCADE,
    "biobank_id" INTEGER REFERENCES biobank("id") ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY ("id"),
    unique(directory_id, directory_catalogue_id)
);
CREATE INDEX "biobankIdIndexCollection" ON "collection" ("biobank_id");


COMMENT ON TABLE collection IS 'Table to store collections from the directory';
COMMENT ON COLUMN collection."id" IS 'primary key';
COMMENT ON COLUMN collection."name" IS 'The collection name';
COMMENT ON COLUMN collection."directory_id" IS 'The directory ID, e.g. eu_bbmri_eric_collections:NL45:blood_collection';
COMMENT ON COLUMN collection."directory_catalogue_id" IS 'The Directory Catalogue this collection belongs to';
COMMENT ON COLUMN collection."biobank_id" IS 'The Biobank ID this collection belongs to';


CREATE TABLE "person" (
    "id" SERIAL NOT NULL,
    "auth_subject" CHARACTER VARYING(255) NOT NULL UNIQUE,
    "auth_name" CHARACTER VARYING(255) NOT NULL,
    "auth_email" CHARACTER VARYING(255) NOT NULL,
    "person_image" BYTEA,
    "is_admin" BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY ("id")
);

COMMENT ON TABLE "person" IS 'person table which is parent of researcher and owner';
COMMENT ON COLUMN "person"."id" IS 'primary key';
COMMENT ON COLUMN "person".auth_subject IS 'authentication string that comes from the authentication service';
COMMENT ON COLUMN "person".auth_name IS 'the real name of the user, value comes from the authentication service';
COMMENT ON COLUMN "person".auth_email IS 'the email of the user, value comes from the authentication service';
COMMENT ON COLUMN "person".person_image IS 'image/avatar of the person';
COMMENT ON COLUMN "person".is_admin IS 'boolean if the person is an admin';


CREATE TABLE "query" (
    "id" SERIAL NOT NULL,
    "title" CHARACTER VARYING(255) NOT NULL,
    "text" TEXT,
    "query_creation_time" TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    "researcher_id" INTEGER NOT NULL,
    "json_text" TEXT NOT NULL,
    "num_attachments" INTEGER NOT NULL,
    "negotiator_token" CHARACTER VARYING(255) NOT NULL UNIQUE,
    "valid_query" BOOLEAN NOT NULL DEFAULT FALSE,
    "request_description" TEXT,
    "ethics_vote" TEXT,
    PRIMARY KEY ("id"),
    FOREIGN KEY ("researcher_id") REFERENCES "person"("id") ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE INDEX "researcherIdIndexQuery" ON "query" (researcher_id);


COMMENT ON TABLE "query" IS 'query table to contain all  queries';
COMMENT ON COLUMN "query"."id" IS 'primary key';
COMMENT ON COLUMN "query"."title" IS 'title of query';
COMMENT ON COLUMN "query"."text" IS 'text of query';
COMMENT ON COLUMN "query"."query_creation_time" IS 'date and time of query with out time zone';
COMMENT ON COLUMN "query"."num_attachments" IS 'number of attachments ever associated with this query - both existing and deleted, used as an index for naming future attachments';
COMMENT ON COLUMN "query"."researcher_id" IS 'Foreign key. Exists as primary key in the researcher table(which takes it in turn from the person table)';
COMMENT ON COLUMN "query"."request_description" IS 'description of the request';
COMMENT ON COLUMN "query"."ethics_vote" IS 'ethics vote for the query';

CREATE TABLE "query_attachment" (
    "id" SERIAL NOT NULL,
    "query_id" INTEGER NOT NULL,
    "attachment" TEXT NOT NULL,
    PRIMARY KEY("id"),
    FOREIGN KEY ("query_id") REFERENCES "query"("id") ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE INDEX "queryIdIndexQueryAttachment" ON "query_attachment" (query_id);


COMMENT ON TABLE "query_attachment" IS 'Table for queries that have one or more attachments uploaded.';
COMMENT ON COLUMN "query_attachment"."query_id" IS 'This column is a foreign key here, taken from query table';
COMMENT ON COLUMN "query_attachment"."attachment" IS 'The name of the attached file stored in file system, not including the directory';



CREATE TABLE "tag" (
    "id" SERIAL NOT NULL,
    "query_id" INTEGER NOT NULL,
    "text" CHARACTER VARYING(255) NOT NULL,
    PRIMARY KEY("id"),
    FOREIGN KEY ("query_id") REFERENCES "query"("id") ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE INDEX "queryIdIndexTag" ON "tag" (query_id);


COMMENT ON TABLE "tag" IS 'Table that contains tags for queries';
COMMENT ON COLUMN "tag"."id" IS 'Primary key';
COMMENT ON COLUMN "tag".query_id IS 'Foreign key which exists in the query table as primary key';
COMMENT ON COLUMN "tag"."text" IS 'Text for the given tag id';



CREATE TABLE "comment" (
    "id" SERIAL NOT NULL,
    "query_id" INTEGER NOT NULL,
    "person_id" INTEGER NOT NULL,
    "comment_time" TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    "text" TEXT NOT NULL,
    PRIMARY KEY("id"),
    FOREIGN KEY ("query_id") REFERENCES "query"("id") ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY ("person_id") REFERENCES "person"("id") ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE INDEX "queryIdIndexComment" ON "comment" (query_id);
CREATE INDEX "personIdIndexComment" ON "comment" (person_id);


COMMENT ON TABLE "comment" IS 'table to store commentCount on a query';
COMMENT ON COLUMN "comment"."id" IS 'Primary key';
COMMENT ON COLUMN "comment".query_id IS 'Foreign key which exists as primary key in the query table. ';
COMMENT ON COLUMN "comment".comment_time IS 'timestamp of when the comment was made.';
COMMENT ON COLUMN "comment".person_id IS 'Foreign key which exists as primary key in the person table. describes the person who made the comment.';
COMMENT ON COLUMN "comment"."text" IS 'Text of the comment.';



CREATE TABLE "tagged_query" (
    "query_id" INTEGER NOT NULL,
    "tag_id" INTEGER NOT NULL,
    PRIMARY KEY("query_id", "tag_id"),
    FOREIGN KEY ("tag_id") REFERENCES "tag"("id") ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY ("query_id") REFERENCES "query"("id") ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE INDEX "tagIdIndexTaggedQuery" ON "tagged_query" (tag_id);
CREATE INDEX "queryIdIndexTaggedQuery" ON "tagged_query" (query_id);


COMMENT ON TABLE "tagged_query" IS 'Table for queries that are tagged by names e.g. colonCancer,SkinCancer etc. Tag names need to be decided .';
COMMENT ON COLUMN "tagged_query".query_id IS 'This column along with tagId will make the primary key. Its also a foreign key here, taken from query table';
COMMENT ON COLUMN "tagged_query".tag_id IS 'This column along with the Queryid column will make the primary key. Its also a foreign key here, taken from "tag" table';



CREATE TABLE "flagged_query" (
    "query_id" INTEGER NULL,
    "person_id" INTEGER NULL,
    "flag" "flag",
    PRIMARY KEY("query_id", "person_id"),
    FOREIGN KEY ("person_id") REFERENCES "person"("id") ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY ("query_id") REFERENCES "query"("id") ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE INDEX "ownerIdIndexFlaggedQuery" ON "flagged_query" (person_id);
CREATE INDEX "queryIdIndexFlaggedQuery" ON "flagged_query" (query_id);


COMMENT ON TABLE "flagged_query" IS 'Table for queries that are flagged/bookmarked. bookmark options are starred, archived and ignored.';
COMMENT ON COLUMN "flagged_query".query_id IS 'This column along with ownerId will make the primary key. Its also a foreign key here, taken from query table';
COMMENT ON COLUMN "flagged_query".person_id IS 'This column along with the id column will make the primary key. Its also a foreign key here, taken from person table';
COMMENT ON COLUMN "flagged_query"."flag" IS 'The flag of the comment. One of "ARCHIVED", "IGNORED" or "STARRED"';


CREATE TABLE "query_collection" (
    "query_id" INTEGER NOT NULL,
    "collection_id" INTEGER NOT NULL,
    "expect_connector_result" BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY("query_id", "collection_id"),
    FOREIGN KEY ("collection_id") REFERENCES "collection"("id") ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY ("query_id") REFERENCES "query"("id") ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE INDEX "collectionIdIndexQueryCollection" ON "query_collection" (collection_id);
CREATE INDEX "queryIdIndexQueryCollection" ON "query_collection" (query_id);


COMMENT ON TABLE "query_collection" IS 'Table for connecting queries with collections';
COMMENT ON COLUMN "query_collection"."expect_connector_result" IS 'Column that tells the negotiator to expect results from the given connector';


CREATE TABLE "person_collection" (
    "person_id" INTEGER NOT NULL,
    "collection_id" INTEGER NOT NULL,
    PRIMARY KEY("person_id", "collection_id"),
    FOREIGN KEY ("collection_id") REFERENCES "collection"("id") ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY ("person_id") REFERENCES "person"("id") ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE INDEX "collectionIdIndexPersonCollection" ON "person_collection" (collection_id);
CREATE INDEX "personIdIndexPersonCollection" ON "person_collection" (person_id);

COMMENT ON TABLE "person_collection" IS 'Table for connecting people with collections';


CREATE TABLE "json_query" (
    "id" SERIAL NOT NULL,
    "json_text" TEXT NOT NULL,
    PRIMARY KEY ("id")
);

COMMENT ON TABLE "json_query" IS 'query table to contain json text queries';
COMMENT ON COLUMN "json_query"."id" IS 'primary key';
COMMENT ON COLUMN "json_query"."json_text" IS 'text of query in json format';



CREATE TABLE "offer" (  
    "id" SERIAL NOT NULL,
    "query_id" INTEGER NOT NULL,    
    "person_id" INTEGER NOT NULL,
    "offer_from" INTEGER NOT NULL,
    "comment_time" TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    "text" TEXT NOT NULL,
    PRIMARY KEY("id"),
    FOREIGN KEY ("query_id") REFERENCES "query"("id") ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY ("person_id") REFERENCES "person"("id") ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY ("offer_from") REFERENCES "person"("id") ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE INDEX "queryIdIndexOffer" ON "offer" (query_id);
CREATE INDEX "personIdIndexOffer" ON "offer" (person_id);
CREATE INDEX "offerFromIndexOffer" ON "offer" (offer_from);


COMMENT ON TABLE "offer" IS 'table to store private conversation made on a query between two people(owner and researcher)';

COMMENT ON COLUMN "offer"."id" IS 'Primary key';
COMMENT ON COLUMN "offer".query_id IS 'Foreign key which exists as primary key in the query table. ';
COMMENT ON COLUMN "offer".comment_time IS 'timestamp of when the comment was made on the offer.';
COMMENT ON COLUMN "offer".person_id IS 'Foreign key which exists as primary key in the person table. describes the person who made the comment.';
COMMENT ON COLUMN "offer".offer_from IS 'Foreign key which exists as primary key in the person table. describes the owner of the samples who made the offer.';
COMMENT ON COLUMN "offer"."text" IS 'Text of the comment.';


CREATE TABLE "connector_log" (
    "id" SERIAL NOT NULL,
    "last_query_time" TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    PRIMARY KEY("id")
);

COMMENT ON TABLE "connector_log" IS 'table to store the timestamp when the connector makes a get request for new queries';

COMMENT ON COLUMN "connector_log"."id" IS 'Primary key';
COMMENT ON COLUMN "connector_log"."last_query_time" IS 'Timestamp when the request was made. ';


