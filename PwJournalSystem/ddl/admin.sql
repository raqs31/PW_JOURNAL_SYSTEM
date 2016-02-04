declare 
  l_admin_id number;
begin
  Insert into USERS (USER_ID,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE,OVN,EMAIL,LOGIN,NAME,SECOND_NAME,PASSWD,DEPARTMENT_ID,IS_ACTIVE) values (USERS_SEQ.nextval,'-1',to_timestamp('15/11/21 22:33:23,000000000','RR/MM/DD HH24:MI:SSXFF'),'-1',to_timestamp('15/11/21 22:33:23,000000000','RR/MM/DD HH24:MI:SSXFF'),'1','raqs31@gmail.com','ADMIN','ADMIN','ADMIN',trim('n4bQgYhMfWWaL+qgxVrQFaO/TxsrC4Is0V1sFbDwCgg='),null,'1')
  returning user_id into l_admin_id;
  Insert into SYSTEM_ROLES (SYSTEM_ROLE_ID,DESCRIPTION,IS_ACTIVE,ROLE_NAME) values (SYSTEM_ROLES_SEQ.nextval,'Rola Administracyjna','1','ADMIN');
  Insert into SYSTEM_ROLES (SYSTEM_ROLE_ID,DESCRIPTION,IS_ACTIVE,ROLE_NAME) values (SYSTEM_ROLES_SEQ.nextval,'Rola użytkownika uprawniająca do podstawowych formularzy','1','USER');
	Insert into SYSTEM_ROLES (SYSTEM_ROLE_ID,DESCRIPTION,IS_ACTIVE,ROLE_NAME) values (SYSTEM_ROLES_SEQ.nextval,'Rola autora publikacji','1','AUTHOR');
	Insert into SYSTEM_ROLES (SYSTEM_ROLE_ID,DESCRIPTION,IS_ACTIVE,ROLE_NAME) values (SYSTEM_ROLES_SEQ.nextval,'Rola recenzenta publikacji','1','ACCEPTOR');
	Insert into SYSTEM_ROLES (SYSTEM_ROLE_ID,DESCRIPTION,IS_ACTIVE,ROLE_NAME) values (SYSTEM_ROLES_SEQ.nextval,'Rola zarządzającego publikacją','1','ARTICLE_MANAGER');

  select user_id into l_admin_id from users where login = 'ADMIN';

  for u in (select * from SYSTEM_ROLES where ROLE_NAME in ('ADMIN','USER'))
  loop
    Insert into USER_SYSTEM_ROLES (USER_ID,SYSTEM_ROLE_ID) values (l_admin_id,u.system_role_id);
  end loop;
  
	/* Konfiguracja słownikowa w excelu */
  commit;
end;
