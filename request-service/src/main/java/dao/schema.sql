

-- Create Table for the Manager Class
CREATE TABLE IF NOT EXISTS Manager (
    id INTEGER AUTO_INCREMENT(1000) PRIMARY KEY,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    phoneNumber VARCHAR(10),
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50)
);

-- Create Table for the Tenant Class
CREATE TABLE IF NOT EXISTS Tenant (
    id INTEGER AUTO_INCREMENT(1000) PRIMARY KEY,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    phoneNumber VARCHAR(10),
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50)
);

-- Create Table for the Property Class
CREATE TABLE IF NOT EXISTS Property (
    id INTEGER AUTO_INCREMENT(1000) PRIMARY KEY,
    name VARCHAR(50),
    address VARCHAR(100) UNIQUE NOT NULL,
    tenant_username VARCHAR(50) NOT NULL,
    manager_username VARCHAR(50) NOT NULL,
    CONSTRAINT FK_TenantUsername FOREIGN KEY (tenant_username) REFERENCES Tenant(username),
    CONSTRAINT FK_ManagerUsername FOREIGN KEY (manager_username) REFERENCES Manager(username)
);

-- Create Table for the Request Class
CREATE TABLE IF NOT EXISTS Request (
    id INTEGER AUTO_INCREMENT(1000) PRIMARY KEY,
    name VARCHAR(50),
    description VARCHAR(500),
    urgent BOOLEAN,
    property_address VARCHAR(100) NOT NULL,
    tenant_username VARCHAR(50) NOT NULL,
    completed BOOLEAN DEFAULT FALSE,

    CONSTRAINT FK_PropertyAddress FOREIGN KEY (property_address) REFERENCES Property(address),
    CONSTRAINT FK_TenantUsername FOREIGN KEY (tenant_username) REFERENCES Tenant(username)
);


