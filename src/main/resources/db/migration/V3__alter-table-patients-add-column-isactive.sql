ALTER TABLE patients ADD is_active tinyint;

UPDATE patients SET is_active = 1