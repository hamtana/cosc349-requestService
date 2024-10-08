

-- Create Table for the Manager Class
CREATE TABLE IF NOT EXISTS Manager (
    id SERIAL PRIMARY KEY,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    phoneNumber VARCHAR(10),
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255)
);

-- Create Table for the Tenant Class
CREATE TABLE IF NOT EXISTS Tenant (
    id SERIAL PRIMARY KEY,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    phoneNumber VARCHAR(10),
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255)
);

-- Create Table for the Property Class
CREATE TABLE IF NOT EXISTS Property (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    address VARCHAR(100) UNIQUE NOT NULL,
    tenant_username VARCHAR(50) NOT NULL,
    manager_username VARCHAR(50) NOT NULL,
    CONSTRAINT FK_TenantUsername FOREIGN KEY (tenant_username) REFERENCES Tenant(username),
    CONSTRAINT FK_ManagerUsername FOREIGN KEY (manager_username) REFERENCES Manager(username)
);

-- Create Table for the Request Class
CREATE TABLE IF NOT EXISTS Request (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL,
    description VARCHAR(500),
    urgent BOOLEAN,
    tenant_username VARCHAR(50) NOT NULL,
    completed BOOLEAN DEFAULT FALSE,
    property_address VARCHAR(100) NOT NULL,

    CONSTRAINT FK_Property FOREIGN KEY (property_address) REFERENCES Property(address) ON DELETE CASCADE,
    CONSTRAINT FK_TenantUsername FOREIGN KEY (tenant_username) REFERENCES Tenant(username) ON DELETE CASCADE
);


-- Create Table for Management
CREATE TABLE IF NOT EXISTS Management (
    workNumber SERIAL PRIMARY KEY,
    name VARCHAR(50),
    description VARCHAR(500),
    property_address VARCHAR(100) NOT NULL,
    status VARCHAR(100) NOT NULL,
    LOCAL_DATE_TIME TIMESTAMP,

    CONSTRAINT FK_Property FOREIGN KEY (property_address) REFERENCES Property(address) ON DELETE CASCADE
);

--Dummy insert statements

-- Insert into Manager
INSERT INTO Manager (firstName, lastName, phoneNumber, username, password)
VALUES
('John', 'Doe', '1234567890', 'johndoe', 'hashed_password_1'),
('Jane', 'Smith', '0987654321', 'janesmith', 'hashed_password_2');

-- Insert into Tenant
INSERT INTO Tenant (firstName, lastName, phoneNumber, username, password)
VALUES
('Alice', 'Brown', '1112223333', 'alicebrown', 'hashed_password_3'),
('Bob', 'Johnson', '4445556666', 'bobjohnson', 'hashed_password_4');

-- Insert into Property
INSERT INTO Property (name, address, tenant_username, manager_username)
VALUES
('Green Apartments', '123 Elm St', 'alicebrown', 'johndoe'),
('Sunset Villas', '456 Oak St', 'bobjohnson', 'janesmith');

-- Insert into Request
INSERT INTO Request (name, description, urgent, tenant_username, completed, property_address)
VALUES
('Leaky Faucet', 'The faucet in the kitchen is leaking', TRUE, 'alicebrown', FALSE, '123 Elm St'),
('Broken Window', 'Window in the living room is broken', FALSE, 'bobjohnson', TRUE, '456 Oak St');
