INSERT INTO USERS (user_name, user_type) VALUES
    ('Test Contractor 1', 'Contractor'),
    ('Test Contractor 2', 'Contractor'),
    ('Test Contractor 3', 'Contractor'),
    ('Test Vendor 1', 'Vendor'),
    ('Test Vendor 2', 'Vendor');
--
-- INSERT INTO CONTRACTS (contract_name, contract_description, contract_value, contractor_id, vendor_id) VALUES
--     ('Test Contract 1', 'Test Contract Description 1', 100000, 1, 1),
--     ('Test Contract 2', 'Test Contract Description 2', 200000, 1, 1),
--     ('Test Contract 3', 'Test Contract Description 3', 300000, 1, 2);
--
-- INSERT INTO INVOICES (invoice_value, contract_id) VALUES
--     (10000, 1),
--     (20000, 1),
--     (30000, 2);