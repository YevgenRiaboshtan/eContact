DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

create sequence s$person_seq increment 10 start 2 cache 1;
CREATE TABLE person (
  id_person numeric(38,0) NOT NULL,
  version bigint NOT NULL,
  first_name character varying(50) NOT NULL,
  last_name character varying(50) NOT NULL,
  role_type character varying(255),
  CONSTRAINT pk$person PRIMARY KEY (id_person)
);

create sequence s$person_account_seq increment 10 start 2 cache 1;
CREATE TABLE person_account (
  id_person_account numeric(38,0) NOT NULL,
  version bigint NOT NULL,
  account_non_expired boolean NOT NULL,
  account_non_locked boolean NOT NULL,
  enabled boolean NOT NULL,
  login character varying(100) NOT NULL,
  password character varying(60) NOT NULL,
  sign numeric(38,0) NOT NULL,
  update_author character varying(100) NOT NULL,
  update_date timestamp without time zone NOT NULL,
  id_person_fk numeric(38,0) NOT NULL,
  CONSTRAINT pk$person_account PRIMARY KEY (id_person_account),
  CONSTRAINT fk$person_account__person FOREIGN KEY (id_person_fk) REFERENCES person (id_person),
  CONSTRAINT ch$login UNIQUE (login)
);
create index i$person_account_login on person_account (login);

create sequence s$visit_log_seq increment 10 start 1 cache 1;
CREATE TABLE visit_log (
  id_visit_log numeric(38,0) NOT NULL,
  device_name character varying(255) NOT NULL,
  end_visit timestamp without time zone,
  ip_address character varying(255) NOT NULL,
  session_id character varying(100) NOT NULL,
  start_visit timestamp without time zone NOT NULL,
  id_person_fk numeric(38,0) NOT NULL,
  CONSTRAINT pk$visit_log PRIMARY KEY (id_visit_log),
  CONSTRAINT fk$visit_log__person FOREIGN KEY (id_person_fk) REFERENCES person (id_person)
);
create index i$visit_log__session_id on visit_log (session_id);

create sequence s$audit_rev increment 10 start 1 cache 1;
CREATE TABLE audit_rev (
  id bigint NOT NULL,
  date_ev timestamp without time zone NOT NULL,
  name_ev character varying(300),
  note character varying(1000),
  time_stamp bigint,
  id_person_fk numeric(38,0) NOT NULL,
  CONSTRAINT pk$audit_rev PRIMARY KEY (id),
  CONSTRAINT fk$audit_rev__person FOREIGN KEY (id_person_fk) REFERENCES person (id_person)
 );

create sequence s$audit_rev_changed increment 10 start 1 cache 1;
CREATE TABLE audit_rev_changed (
  id bigint NOT NULL,
  entity_id character varying(100),
  entity_name character varying(300) NOT NULL,
  id_audit_rev_fk bigint NOT NULL,
  CONSTRAINT pk$audit_rev_changed PRIMARY KEY (id),
  CONSTRAINT fk$audit_rev_changed__audit_rev FOREIGN KEY (id_audit_rev_fk) REFERENCES audit_rev (id)
);

CREATE TABLE person_audit (
  id_person numeric(38,0) NOT NULL,
  rev bigint NOT NULL,
  revtype smallint,
  first_name character varying(50),
  last_name character varying(50),
  role_type character varying(255),
  CONSTRAINT pk$person_audit PRIMARY KEY (id_person, rev),
  CONSTRAINT fk$person_audit__audit_rev FOREIGN KEY (rev) REFERENCES audit_rev (id)
);

CREATE TABLE person_account_audit (
  id_person_account numeric(38,0) NOT NULL,
  rev bigint NOT NULL,
  revtype smallint,
  account_non_expired boolean,
  account_non_locked boolean,
  enabled boolean,
  login character varying(100),
  password character varying(60),
  sign numeric(38,0),
  update_author character varying(100),
  update_date timestamp without time zone,
  id_person_fk numeric(38,0),
  CONSTRAINT pk$person_account_audit PRIMARY KEY (id_person_account, rev),
  CONSTRAINT fk$person_account_audit__audit_rev FOREIGN KEY (rev) REFERENCES audit_rev (id)
);

-- create user SYSTEM ADMINISTRATOR with login/password = admin/1111  decode by bcrypt value ($2a$06$dqOEgcYQMRcQIn7P7mrKSOQpGBBzeJMSeq1QknanKEDLmNtymcoj6)
INSERT INTO person(id_person, version, first_name, last_name, role_type)
VALUES (1, 1, 'admin', 'admin', 'ROLE_SYS_ADMIN');

INSERT INTO person_account(
  id_person_account, version, account_non_expired, account_non_locked, enabled,
  login, password, sign, update_author, update_date, id_person_fk)
VALUES (nextval('s$person_account_seq'), 1, true, true, true,
  'admin', '$2a$06$dqOEgcYQMRcQIn7P7mrKSOQpGBBzeJMSeq1QknanKEDLmNtymcoj6', 0, 'SYSTEM', current_date, 1);
