INSERT INTO routing_rules(RULE_ID,DESCRIPTION,NAME,FOR_ROLE_name,FROM_STATUS,TO_STATUS)
VALUES (routing_rules_seq.nextval,
        'Kompletacja artykułu',
        'Kompletuj',
        'AUTHOR',
        1,
        4);
        
        
commit;