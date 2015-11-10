
    create table USERS (
        USER_ID number(19,0) not null,
        CREATION_DATE date,
        EMAIL varchar2(60) not null,
        LAST_UPDATE_DATE date,
        LOGIN varchar2(25) not null,
        NAME varchar2(25),
        SECOND_NAME varchar2(25),
        primary key (USER_ID)
    );

    alter table USERS 
        add constraint UK_l3c3ahdulnjx8bt2ivgyvh1ss  unique (LOGIN);

    alter table USERS 
        add constraint UK_81nqioeq3njjrwqaltk2mcobj  unique (EMAIL);

    create sequence USERS_SEQ;
