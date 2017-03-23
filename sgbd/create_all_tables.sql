-- Oracle script that creates an ASCIIPIC user and geneerate the project database.
-- You should run it as administrator. 


DROP USER ASCIIPIC CASCADE;
CREATE USER ASCIIPIC IDENTIFIED BY ASCIIPIC DEFAULT TABLESPACE USERS TEMPORARY TABLESPACE TEMP;
ALTER USER ASCIIPIC QUOTA 2000M ON USERS;
GRANT CONNECT TO ASCIIPIC;
GRANT CREATE TABLE TO ASCIIPIC;
GRANT CREATE VIEW TO ASCIIPIC;
GRANT CREATE SEQUENCE TO ASCIIPIC;
GRANT CREATE TRIGGER TO ASCIIPIC;
GRANT CREATE SYNONYM TO ASCIIPIC;
GRANT CREATE PROCEDURE TO ASCIIPIC;
CONN ASCIIPIC/ASCIIPIC@localhost/XE

CREATE TABLE images (
    id           INTEGER NOT NULL,
    url          VARCHAR2(2048) NOT NULL,
    post_date    DATE NOT NULL,
    crawl_date   DATE NOT NULL,
    height       INTEGER NOT NULL,
    widt         INTEGER NOT NULL,
    is_saved     CHAR(1) NOT NULL
);

ALTER TABLE images ADD CONSTRAINT images_pk PRIMARY KEY ( id );

CREATE TABLE j_crawl (
    journalize_id   INTEGER
        CONSTRAINT nnc_j_crawl_journalize_id NOT NULL,
    user_id         INTEGER
        CONSTRAINT nnc_j_crawl_user_id NOT NULL,
    job_id          INTEGER
        CONSTRAINT nnc_j_crawl_job_id NOT NULL,
    tag             VARCHAR2(32),
    post_date       DATE,
    width           INTEGER,
    height          INTEGER
);

ALTER TABLE j_crawl ADD CONSTRAINT j_crawl_pk PRIMARY KEY ( journalize_id );

CREATE TABLE j_filters (
    journalize_id   INTEGER
        CONSTRAINT nnc_j_filters_journalize_id NOT NULL,
    user_id         INTEGER
        CONSTRAINT nnc_j_filters_user_id NOT NULL,
    image_id        INTEGER
        CONSTRAINT nnc_j_filters_image_id NOT NULL,
    type            VARCHAR2(32)
        CONSTRAINT nnc_j_filters_type NOT NULL
);

ALTER TABLE j_filters ADD CONSTRAINT j_filters_pk PRIMARY KEY ( journalize_id );

CREATE TABLE j_login (
    journalize_id   INTEGER
        CONSTRAINT nnc_j_login_journalize_id NOT NULL,
    user_id         INTEGER
        CONSTRAINT nnc_j_login_user_id NOT NULL,
    ip              VARCHAR2(32)
        CONSTRAINT nnc_j_login_ip NOT NULL,
    user_agent      VARCHAR2(32)
        CONSTRAINT nnc_j_login_user_agent NOT NULL
);

ALTER TABLE j_login ADD CONSTRAINT j_login_pk PRIMARY KEY ( journalize_id );

CREATE TABLE j_logout (
    journalize_id   INTEGER
        CONSTRAINT nnc_j_logout_journalize_id NOT NULL,
    user_id         INTEGER
        CONSTRAINT nnc_j_logout_user_id NOT NULL,
    cause           VARCHAR2(32)
        CONSTRAINT nnc_j_logout_cause NOT NULL
);

ALTER TABLE j_logout ADD CONSTRAINT j_logout_pk PRIMARY KEY ( journalize_id );

CREATE TABLE j_register (
    journalize_id   INTEGER
        CONSTRAINT nnc_j_register_journalize_id NOT NULL,
    user_id         INTEGER
        CONSTRAINT nnc_j_register_user_id NOT NULL,
    ip              VARCHAR2(32)
        CONSTRAINT nnc_j_register_ip NOT NULL
);

ALTER TABLE j_register ADD CONSTRAINT j_register_pk PRIMARY KEY ( journalize_id );

CREATE TABLE j_search (
    journalize_id   INTEGER
        CONSTRAINT nnc_j_search_journalize_id NOT NULL,
    user_id         INTEGER
        CONSTRAINT nnc_j_search_user_id NOT NULL,
    tag             VARCHAR2(32),
    post_date       DATE,
    width           INTEGER,
    height          INTEGER
);

ALTER TABLE j_search ADD CONSTRAINT j_search_pk PRIMARY KEY ( journalize_id );

CREATE TABLE jobs (
    id            INTEGER
        CONSTRAINT nnc_imagesv1_id NOT NULL,
    post_date     DATE NOT NULL,
    start_date    DATE NOT NULL,
    finish_date   DATE NOT NULL
);

ALTER TABLE jobs ADD CONSTRAINT jobs_pk PRIMARY KEY ( id );

CREATE TABLE journalize (
    id            INTEGER
        CONSTRAINT nnc_imagesv1_id NOT NULL,
    action        VARCHAR2(512) NOT NULL,
    action_date   DATE NOT NULL
);

ALTER TABLE journalize ADD CONSTRAINT journalize_pk PRIMARY KEY ( id );

CREATE TABLE saved_images (
    image_id   INTEGER NOT NULL,
    data       blob NOT NULL
);

ALTER TABLE saved_images ADD CONSTRAINT saved_images_pk PRIMARY KEY ( image_id );

CREATE TABLE tags (
    tag        VARCHAR2(32) NOT NULL,
    image_id   INTEGER
        CONSTRAINT nnc_imagesv1_id NOT NULL
);

ALTER TABLE tags ADD CONSTRAINT tags_pk PRIMARY KEY ( image_id,tag );

CREATE TABLE users (
    id             INTEGER NOT NULL,
    username       VARCHAR2(32) NOT NULL,
    email          VARCHAR2(64) NOT NULL,
    password       VARCHAR2(256) NOT NULL,
    salt           VARCHAR2(8) NOT NULL,
    is_confirmed   CHAR(1) NOT NULL,
    is_active      CHAR(1) NOT NULL
);

ALTER TABLE users ADD CONSTRAINT users_pk PRIMARY KEY ( id );

ALTER TABLE j_crawl ADD CONSTRAINT j_crawl_jobs_fk FOREIGN KEY ( job_id )
    REFERENCES jobs ( id );

ALTER TABLE j_crawl ADD CONSTRAINT j_crawl_journalize_fk FOREIGN KEY ( journalize_id )
    REFERENCES journalize ( id );

ALTER TABLE j_crawl ADD CONSTRAINT j_crawl_users_fk FOREIGN KEY ( user_id )
    REFERENCES users ( id );

ALTER TABLE j_filters ADD CONSTRAINT j_filters_images_fk FOREIGN KEY ( image_id )
    REFERENCES images ( id );

ALTER TABLE j_filters ADD CONSTRAINT j_filters_journalize_fk FOREIGN KEY ( journalize_id )
    REFERENCES journalize ( id );

ALTER TABLE j_filters ADD CONSTRAINT j_filters_users_fk FOREIGN KEY ( user_id )
    REFERENCES users ( id );

ALTER TABLE j_login ADD CONSTRAINT j_login_journalize_fk FOREIGN KEY ( journalize_id )
    REFERENCES journalize ( id );

ALTER TABLE j_login ADD CONSTRAINT j_login_users_fk FOREIGN KEY ( user_id )
    REFERENCES users ( id );

ALTER TABLE j_logout ADD CONSTRAINT j_logout_journalize_fk FOREIGN KEY ( journalize_id )
    REFERENCES journalize ( id );

ALTER TABLE j_logout ADD CONSTRAINT j_logout_users_fk FOREIGN KEY ( user_id )
    REFERENCES users ( id );

ALTER TABLE j_register ADD CONSTRAINT j_register_journalize_fk FOREIGN KEY ( journalize_id )
    REFERENCES journalize ( id );

ALTER TABLE j_register ADD CONSTRAINT j_register_users_fk FOREIGN KEY ( user_id )
    REFERENCES users ( id );

ALTER TABLE j_search ADD CONSTRAINT j_search_journalize_fk FOREIGN KEY ( journalize_id )
    REFERENCES journalize ( id );

ALTER TABLE j_search ADD CONSTRAINT j_search_users_fk FOREIGN KEY ( user_id )
    REFERENCES users ( id );

ALTER TABLE saved_images ADD CONSTRAINT saved_images_images_fk FOREIGN KEY ( image_id )
    REFERENCES images ( id );

ALTER TABLE tags ADD CONSTRAINT tags_images_fk FOREIGN KEY ( image_id )
    REFERENCES images ( id );

CREATE SEQUENCE images_id_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER images_id_trg BEFORE
    INSERT ON images
    FOR EACH ROW
BEGIN
    :new.id := images_id_seq.nextval;
END;
/

CREATE SEQUENCE jobs_id_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER jobs_id_trg BEFORE
    INSERT ON jobs
    FOR EACH ROW
BEGIN
    :new.id := jobs_id_seq.nextval;
END;
/

CREATE SEQUENCE journalize_id_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER journalize_id_trg BEFORE
    INSERT ON journalize
    FOR EACH ROW
BEGIN
    :new.id := journalize_id_seq.nextval;
END;
/

CREATE SEQUENCE saved_images_image_id_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER saved_images_image_id_trg BEFORE
    INSERT ON saved_images
    FOR EACH ROW
BEGIN
    :new.image_id := saved_images_image_id_seq.nextval;
END;
/

CREATE SEQUENCE tags_image_id_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER tags_image_id_trg BEFORE
    INSERT ON tags
    FOR EACH ROW
BEGIN
    :new.image_id := tags_image_id_seq.nextval;
END;
/
