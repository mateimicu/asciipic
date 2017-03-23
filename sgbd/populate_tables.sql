-- Script for populating the project database.

set serveroutput on;

drop table t_surnames;
drop table t_names;
drop table t_domains;
drop table t_urls;

delete from saved_images;
delete from j_crawl;
delete from j_search;
delete from j_login;
delete from j_logout;
delete from j_filters;
delete from j_register;
delete from tags;
delete from jobs;
delete from journalize;
delete from users;
delete from images;


create table t_surnames(name varchar2(16), id number);

INSERT INTO t_surnames VALUES ('Popa', 1);
INSERT INTO t_surnames VALUES ('Toma', 2);
INSERT INTO t_surnames VALUES ('Apetrei', 3);
INSERT INTO t_surnames VALUES ('Mitrea', 4);
INSERT INTO t_surnames VALUES ('Micut', 5);
INSERT INTO t_surnames VALUES ('Milut', 6);
INSERT INTO t_surnames VALUES ('Mihalcut', 7);
INSERT INTO t_surnames VALUES ('Aioanei', 8);
INSERT INTO t_surnames VALUES ('Amariei', 9);
INSERT INTO t_surnames VALUES ('Arsene', 10);
INSERT INTO t_surnames VALUES ('Baston', 11);
INSERT INTO t_surnames VALUES ('Cretu', 12);
INSERT INTO t_surnames VALUES ('Covrig', 13);
INSERT INTO t_surnames VALUES ('Stolniceanu', 14);
INSERT INTO t_surnames VALUES ('Ailincai', 15);
INSERT INTO t_surnames VALUES ('Andreescu', 16);
INSERT INTO t_surnames VALUES ('Averescu', 17);
INSERT INTO t_surnames VALUES ('Popovici', 18);
INSERT INTO t_surnames VALUES ('Tanase', 19);
INSERT INTO t_surnames VALUES ('Ispirescu', 20);
INSERT INTO t_surnames VALUES ('Balutoiu', 21);
INSERT INTO t_surnames VALUES ('Galan', 22);
INSERT INTO t_surnames VALUES ('Caraiman', 23);
INSERT INTO t_surnames VALUES ('Murarasu', 24);
INSERT INTO t_surnames VALUES ('Dudau', 25);
INSERT INTO t_surnames VALUES ('Boca', 26);
INSERT INTO t_surnames VALUES ('Butacu', 27);
INSERT INTO t_surnames VALUES ('Simionescu', 28);
INSERT INTO t_surnames VALUES ('Simion', 29);


create table t_names(name varchar2(16), id number);

INSERT INTO t_names VALUES ('Alexandru', 1);
INSERT INTO t_names VALUES ('Andrei', 2);
INSERT INTO t_names VALUES ('Matei', 3);
INSERT INTO t_names VALUES ('Maria', 4);
INSERT INTO t_names VALUES ('Andra', 5);
INSERT INTO t_names VALUES ('Alice', 6);
INSERT INTO t_names VALUES ('Dana', 7);
INSERT INTO t_names VALUES ('Pavel', 8);
INSERT INTO t_names VALUES ('Ionut', 9);
INSERT INTO t_names VALUES ('Vasile', 10);
INSERT INTO t_names VALUES ('Madalina', 11);
INSERT INTO t_names VALUES ('Florina' , 12);
INSERT INTO t_names VALUES ('Codruta', 13);
INSERT INTO t_names VALUES ('Mircea', 14);
INSERT INTO t_names VALUES ('Claudiu', 15);
INSERT INTO t_names VALUES ('Paul', 16);
INSERT INTO t_names VALUES ('Tudoe', 17);
INSERT INTO t_names VALUES ('Tatiana', 18);
INSERT INTO t_names VALUES ('Claudia', 19);
INSERT INTO t_names VALUES ('Petronela', 20);
INSERT INTO t_names VALUES ('George', 21);
INSERT INTO t_names VALUES ('Gianina', 22);
INSERT INTO t_names VALUES ('Mihai', 23);
INSERT INTO t_names VALUES ('Stefan', 24);
INSERT INTO t_names VALUES ('Ioan', 25);
INSERT INTO t_names VALUES ('Adelina', 26);
INSERT INTO t_names VALUES ('Codrut', 27);
INSERT INTO t_names VALUES ('Mara', 28);
INSERT INTO t_names VALUES ('Margareta', 29);
INSERT INTO t_names VALUES ('Mihail', 30);
INSERT INTO t_names VALUES ('Miruna', 31);
INSERT INTO t_names VALUES ('Andrada', 32);
INSERT INTO t_names VALUES ('Daniel', 33);
INSERT INTO t_names VALUES ('Lucian', 34);
INSERT INTO t_names VALUES ('Cristina', 35);


create table t_domains(name varchar2(16), id number);

INSERT INTO t_domains VALUES ('@info.uaic.ro', 1);
INSERT INTO t_domains VALUES ('@gmail.com', 2);
INSERT INTO t_domains VALUES ('@gmail.ro', 3);
INSERT INTO t_domains VALUES ('@yahoo.com', 4);
INSERT INTO t_domains VALUES ('@yahoo.ro', 5);
INSERT INTO t_domains VALUES ('@outlook.com', 6);


create table t_urls(name varchar2(32), id number);

INSERT INTO t_urls VALUES ('http://www.facebook.com/', 1);
INSERT INTO t_urls VALUES ('http://www.google.com/', 2);
INSERT INTO t_urls VALUES ('http://www.flickr.com/', 3);
INSERT INTO t_urls VALUES ('http://www.instagram.com/', 4);
INSERT INTO t_urls VALUES ('http://www.picasa.com/', 5);

declare
  g_line_count NUMBER := 0;
  g_rand NUMBER := 0;
  g_names NUMBER;
  g_surnames NUMBER;
  g_domains NUMBER;
  g_urls NUMBER;
  g_name varchar2(16);
  g_surname varchar2(16);
  g_domain varchar2(16);
  
  --users variables
  g_id users.id%type;
  g_username users.username%type;
  g_password users.password%type;
  g_email users.email%type;
  g_salt users.salt%type;
  g_is_confirmed users.is_confirmed%type;
  g_is_active users.is_active%type;
  
  --image variables
  i_id images.id%type;
  i_url images.url%type;
  i_post_date images.post_date%type;
  i_crawl_date images.crawl_date%type;
  i_height images.height%type;
  i_width images.width%type;
  i_is_saved images.is_saved%type;
  
begin
  select count(*) into g_names from t_names;
  select count(*) into g_surnames from t_surnames;
  select count(*) into g_domains from t_domains;

  select count(*) into g_urls from t_urls;
  
  select max(id) into g_id from users; --ensures avoiding duplicates on multiple runs
  if g_id is null then 
    g_id := 0;
  end if;
  
  select max(id) into i_id from images;
  if i_id is null then 
    i_id := 0;
  end if;
  
  loop
    --populate users table
    g_id := g_id + 1;
    g_rand := trunc(dbms_random.value(1, g_names + 1));
    select name into g_name
      from t_names where id = g_rand;
    g_rand := trunc(dbms_random.value(1, g_surnames + 1));
    select name into g_surname
      from t_surnames where id = g_rand;
    g_rand := trunc(dbms_random.value(1, g_domains + 1));
    select name into g_domain
      from t_domains where id = g_rand;
    g_username := g_name || g_surname;
    g_email := lower(g_name || '.' || g_surname || g_domain);
    g_salt := dbms_random.string('A', 4);
    g_password := dbms_random.string('P', 255);
    g_is_confirmed := trunc(dbms_random.value(0, 2));
    g_is_active := trunc(dbms_random.value(0, 2));
    insert into users values(g_id, g_username, g_email, g_password, g_salt, g_is_confirmed, g_is_active);

    --populate images table
    i_id := i_id + 1;
    g_rand := trunc(dbms_random.value(1, g_urls + 1));
    select name || dbms_random.string('P', trunc(dbms_random.value(10, 1000))) into i_url
      from t_urls where id = g_rand;
    select  SYSDATE + dbms_random.value(SYSDATE - SYSDATE - 1000, SYSDATE - SYSDATE - 200) 
      into i_post_date from dual;
    select  i_post_date + dbms_random.value(0, SYSDATE - i_post_date)
      into i_crawl_date from dual;
    i_height := trunc(dbms_random.value(10, 10000));    
    i_width := trunc(dbms_random.value(10, 10000));
    i_is_saved := trunc(dbms_random.value(0, 2));
    insert into images values(i_id, i_url, i_post_date, i_crawl_date, i_height, i_width, i_is_saved);

    g_line_count := g_line_count + 1;
    exit when g_line_count = 10000;
  end loop;

  --populate the rest of the tables with some data
  --TODO: Alexandra Folvaiter
end;  
