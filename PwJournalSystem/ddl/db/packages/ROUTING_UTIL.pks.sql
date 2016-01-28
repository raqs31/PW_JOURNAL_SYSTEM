create or replace PACKAGE ROUTING_UTIL
AS
  PROCEDURE insert_rule(
      P_NAME           VARCHAR2 ,
      P_DESCRIPTION    VARCHAR2,
      P_FOR_ROLE       VARCHAR2,
      P_FROM_STATUS    VARCHAR2,
      P_TO_STATUS      VARCHAR2,
      P_READY_TO_PRINT VARCHAR2,
      P_FOR_ACCEPTOR   VARCHAR2,
      P_FOR_AUTHOR     VARCHAR2,
      P_FOR_MANAGER    VARCHAR2,
      P_PICK_ACCEPTOR  VARCHAR2
    );
  FUNCTION get_status_id(
      p_status VARCHAR2)
    RETURN NUMBER;
  FUNCTION is_empty(
    p_str VARCHAR2)
  RETURN NUMBER;
END ROUTING_UTIL;