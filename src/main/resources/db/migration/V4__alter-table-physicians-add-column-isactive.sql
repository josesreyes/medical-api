ALTER TABLE physicians ADD is_active tinyint;

UPDATE physicians SET is_active = 1