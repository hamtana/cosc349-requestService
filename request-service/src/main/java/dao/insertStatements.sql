-- Insert data into Manager table
INSERT INTO Manager (firstName, lastName, phoneNumber, username, password) VALUES
('Alice', 'Johnson', '1234567890', 'alicej', 'password123');
-- Insert data into Tenant table
INSERT INTO Tenant (id, firstName, lastName, phoneNumber, username, password) VALUES
('T001', 'Charlie', 'Brown', '2345678901', 'charlieb', 'password789'),
('T002', 'Daisy', 'Duke', '3456789012', 'daisyduke', 'password012');

-- Insert data into Property table
INSERT INTO Property (name, address, tenant_username, manager_username) VALUES
('Green Villa', '123 Elm St', 'Bob', 'alicej');

-- Insert data into Request table
INSERT INTO Request (id, name, description, urgent, tenant_username, property_address) VALUES
('R001', 'Leaky Faucet', 'The kitchen faucet is leaking and needs urgent repair.', TRUE, 'Bob', '123 Elm St'),
('R002', 'Broken Window', 'The living room window is broken and needs replacement.', FALSE, 'Bob', '123 Elm St');
