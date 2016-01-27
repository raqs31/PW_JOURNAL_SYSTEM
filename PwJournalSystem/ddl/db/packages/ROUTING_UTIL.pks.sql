create or replace PACKAGE ROUTING_UTIL
AS
  PROCEDURE insert_rule(
      p_name           VARCHAR2 ,
      p_description    VARCHAR2,
      p_for_role       VARCHAR2,
      p_from_Status    VARCHAR2,
      p_to_status      VARCHAR2,
      p_ready_to_print VARCHAR2,
      p_for_acceptor   VARCHAR2,
      p_for_author     VARCHAR2,
      p_for_manager    VARCHAR2,
      p_pick_acceptor  VARCHAR2
    );
    
  FUNCTION get_status_id(
      p_status VARCHAR2)
    RETURN NUMBER;
END ROUTING_UTIL;
