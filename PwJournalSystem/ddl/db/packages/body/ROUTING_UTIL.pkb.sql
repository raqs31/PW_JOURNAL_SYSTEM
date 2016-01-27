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
END get_status_id;

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
    p_pick_acceptor  VARCHAR2)
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
      pick_acceptors
    )
    VALUES
    (
      routing_rules_seq.nextval,
      p_name,
      p_description,
      p_for_role,
      get_status_id(p_from_status),
      get_status_id(p_to_status),
      DECODE(NVL(p_ready_to_print, 'NULL'), 'NULL', 1,0),
      DECODE(NVL(p_for_acceptor, 'NULL'), 'NULL', 1,0),
      DECODE(NVL(p_for_author, 'NULL'), 'NULL', 1,0),
      DECODE(NVL(p_for_manager, 'NULL'), 'NULL', 1,0),
      DECODE(NVL(p_pick_acceptor, 'NULL'), 'NULL', 1,0)
    );
END insert_rule;
END ROUTING_UTIL;
