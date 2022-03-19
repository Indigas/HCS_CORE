CREATE OR REPLACE VIEW `patient_diagnose` AS
SELECT p.id as patient_id, d.id as diagnose_id, d.tag, d.description FROM MedicalRecord m, Patient p
join Diagnose d;