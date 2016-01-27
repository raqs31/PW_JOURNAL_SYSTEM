CREATE OR REPLACE PACKAGE BODY ROUTING_UTIL
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
      p_pick_acceptor  VARCHAR2)
  IS
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
  BEGIN
    INSERT
    INTO
      routin_rules
      (
        rule_id,
        name,
        desciption,
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
        DECODE(p_ready_to_print, 'x', 1,0),
        DECODE(p_for_acceptor, 'x', 1,0),
        DECODE(p_for_author, 'x', 1,0),
        DECODE(p_for_manager, 'x', 1,0),
        DECODE(p_pick_acceptor, 'x', 1,0)
      );
  END;
END ROUTING_UTIL;
/