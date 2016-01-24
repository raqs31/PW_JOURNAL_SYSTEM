UPDATE
  article_versions av
SET
  last_version =
  CASE
      (
        SELECT
          MAX(a.version_num)
        FROM
          article_Versions a
        WHERE
          a.article_id = av.article_id
      )
    WHEN av.version_num
    THEN 1
    ELSE 0
  END;
 commit;