set serveroutput on;
DECLARE
   v_contor INTEGER := 0;
   v_link VARCHAR2(2048);
   v_width INTEGER := 100;
   v_height INTEGER := 100;
   v_data DATE := SYSDATE;
   v_random_item FLOAT;
   v_random_item_2 FLOAT;
   v_random_item_3 FLOAT;
   v_username VARCHAR2(20);
   v_email VARCHAR2(20);
   v_image VARCHAR2(20);
   v_hash VARCHAR2(256);
   v_bool VARCHAR2(1);
   v_domain VARCHAR2(32);


BEGIN
   LOOP
   
       v_contor := v_contor + 1;
       DBMS_OUTPUT.PUT_LINE(v_contor);  
       select dbms_random.value INTO v_random_item from dual;
       select dbms_random.value(100, 400) INTO v_width from dual;
       select dbms_random.value(100, 400) INTO v_height from dual;
       select dbms_random.value INTO v_random_item_2 from dual;
       select dbms_random.value INTO v_random_item_3 from dual;
       select dbms_random.string('U', 20) str INTO v_username from dual;
       select dbms_random.string('U', 10) str INTO v_email from dual;
       select dbms_random.string('U', 20) str INTO v_image from dual;
       select dbms_random.string('U', 256) str INTO v_hash from dual;
        if v_random_item_2 > 0.5 
          then
            v_bool := 't';
          else
            v_bool := 'f';

        end if;
        
        if v_random_item_3 > 0.5 
          then
            v_domain := 'gmail.com';
          else
            v_domain := 'yahoo.com';

        end if;
       
       if v_random_item > 0.5 
          then
            insert into images values(v_contor, 'facebook.com/'||v_image, v_data, v_data, v_bool, v_width, v_height);
          else
            insert into images values(v_contor, 'instagram.com/'||v_image, v_data, v_data, v_bool, v_width, v_height);

        end if;
       insert into users values(v_contor, v_username, v_email||'@'||v_domain, v_hash, 'salt', 'f', v_bool);
       EXIT WHEN v_contor = 10;
   END LOOP;
END;
