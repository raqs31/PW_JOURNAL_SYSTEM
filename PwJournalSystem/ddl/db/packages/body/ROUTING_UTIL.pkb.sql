create or replace PACKAGE BODY ROUTING_UTIL
AS
FUNCTION get_status_id(
    p_status VARCHAR2)
  RETURN NUMBER
IS
  l_id NUMBER;
BEGIN
  SELECT
    dict_id
  INTO
    l_id
  FROM
    dictionaries
  WHERE
    dictionary_name = 'ARTICLE_STATUS'
  AND code          = p_status;
  RETURN l_id;
END;
FUNCTION is_empty(
    p_str VARCHAR2)
  RETURN NUMBER
IS
BEGIN
  RETURN
  CASE
  WHEN p_str IS NULL THEN
    0
  WHEN p_str ='' THEN
    0
  ELSE
    1
  END;
END;
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
    P_PICK_ACCEPTOR  VARCHAR2,
    P_PICK_MANAGER   VARCHAR2)
IS
BEGIN
  INSERT
  INTO
    routing_rules
    (
      rule_id,
      name,
      description,
      for_role_name,
      from_status,
      to_status,
      ready_to_print,
      for_acceptor,
      for_author,
      for_manager,
      pick_acceptors,
      pick_manager
    )
    VALUES
    (
      routing_rules_seq.nextval,
      p_name,
      p_description,
      p_for_role,
      get_status_id(p_from_status),
      get_status_id(p_to_status),
      is_empty(p_ready_to_print),
      is_empty(p_for_acceptor),
      is_empty(p_for_author),
      is_empty(p_for_manager),
      is_empty(p_pick_acceptor),
      is_empty(p_pick_manager)
    );
END;
END ROUTING_UTIL;