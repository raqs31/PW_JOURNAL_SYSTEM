create or replace PACKAGE ROUTING_UTIL
as
  PROCEDURE insert_rule(
      p_name           VARCHAR2 ,
      p_description    VARCHAR2,
      p_for_role       VARCHAR2,
      p_from_Status    VARCHAR2,
      p_to_status      VARCHAR2,
      p_ready_to_print varchar2,
      p_for_acceptor   varchar2,
      p_for_author     varchar2,
      p_for_manager    varchar2,
      p_pick_acceptor  varchar2
  );
END ROUTING_UTIL;
/