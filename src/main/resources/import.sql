INSERT INTO usernames(creation_date,last_modified_date,nombre,apellido,email,password) VALUES (now(),now(),'ivan','cuevas','ivan@gmail.com','$2a$10$xGw4tjFfKfllkCqy.bYUVu.RH0G9L7nim3RDW64dRMCcx3bNXYMeC');
INSERT INTO usernames(creation_date,last_modified_date,nombre,apellido,email,password) VALUES (now(),now(),'erich','hc','erich@gmail.com','$2a$10$xGw4tjFfKfllkCqy.bYUVu.RH0G9L7nim3RDW64dRMCcx3bNXYMeC');

INSERT INTO roles(id,creation_date,last_modified_date,authority) VALUES(1,now(),now(),'ROLE_ADMIN');
INSERT INTO roles(id,creation_date,last_modified_date,authority) VALUES(2,now(),now(),'ROLE_USER');

INSERT INTO usernames_roles(username_id,roles_id) VALUES (1,2);
INSERT INTO usernames_roles(username_id,roles_id) VALUES (2,1);