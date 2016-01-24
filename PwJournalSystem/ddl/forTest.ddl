BEGIN
  FOR i IN 1 .. 50
  LOOP
    INSERT
    INTO tags
      (
        tag_id,
        description,
        name
      )
    VALUES
      (
        tags_seq.nextval,
        'TEST'
        ||i,
        'TEST'
        ||i
      );
  END LOOP;
  
  for i in 1 .. 50 loop
    insert into departments(dept_id, dept_code, description, full_name, is_active, created_by, creation_date, last_updated_by, last_update_date, ovn)
    values(departments_seq.nextval, 'DEPT'||i,'DEPT'||i||' -DESCRIPTION', 'DEPT'||i,1, 'ADMIN', sysdate, 'ADMIN', sysdate, 1);
  end loop;
  commit;
END;