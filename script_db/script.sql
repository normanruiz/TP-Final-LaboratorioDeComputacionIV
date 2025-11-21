CREATE DATABASE IF NOT EXISTS `TPFinalLaboratorioDeComputacionIV` DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_0900_as_cs;;

USE `TPFinalLaboratorioDeComputacionIV`;

CREATE TABLE `clients` (
	`id` INTEGER NOT NULL AUTO_INCREMENT UNIQUE,
	`usr` VARCHAR(20) UNIQUE,
	`pwd` VARCHAR(12),
	`profile` ENUM('ADMIN', 'CLIENT') NOT NULL DEFAULT 'CLIENT',
	`status` BOOLEAN,
	`DNI` VARCHAR(8) UNIQUE,
	`CUIL` VARCHAR(
CREATE TABLE `movements` (
  `id` int NOT NULL AUTO_INCREMENT,
  `clientId` int NOT NULL,
  `bankAccountsId` int NOT NULL,
  `amount` decimal(12,2) DEFAULT NULL,
  `typemovements` enum('CREATEDBANKACCOUNT','CREATEDBANKLOAN','PAYMENTBANKLOAN','TRANSFER') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_cs NOT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `createdAt` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `clientId` (`clientId`),
  KEY `bankAccountsId` (`bankAccountsId`),
  CONSTRAINT `bankLoans_ibfk_1` FOREIGN KEY (`clientId`) REFERENCES `clients` (`id`),
  CONSTRAINT `bankLoans_ibfk_2` FOREIGN KEY (`bankAccountsId`) REFERENCES `bankAccounts` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_cs;14),
	`name` VARCHAR(255),
	`lastName` VARCHAR(255),
	`sex` ENUM('MALE', 'FEMALE') DEFAULT 'MALE',
	`nationality` VARCHAR(255),
	`birthdate` DATE,
	`address` VARCHAR(255),
	`city` VARCHAR(255),
	`state` VARCHAR(255),
	`email` VARCHAR(255),
	`phone` VARCHAR(255),
	`createdAt` DATETIME,
	`updatedAt` DATETIME,
	PRIMARY KEY(`id`)
);


CREATE TABLE `admins` (
	`id` INTEGER NOT NULL AUTO_INCREMENT UNIQUE,
	`usr` VARCHAR(20) UNIQUE,
	`pwd` VARCHAR(12),
	`profile` ENUM('ADMIN', 'CLIENT') NOT NULL DEFAULT 'ADMIN',
	`status` BOOLEAN,
	`name` VARCHAR(255),
	`lastName` VARCHAR(255),
	`email` VARCHAR(255),
	`createdAt` TIMESTAMP,
	`updatedAt` TIMESTAMP,
	PRIMARY KEY(`id`)
);

CREATE TABLE TPFinalLaboratorioDeComputacionIV.bankAccounts (
	id INTEGER auto_increment NOT NULL,
	clientId INTEGER NOT NULL,
	bankAccountType ENUM('SAVINGSBANK', 'CURRENTACCOUNT') DEFAULT 'SAVINGSBANK' NOT NULL,
	accountNumber varchar(100) NOT NULL,
	CBU varchar(100) NOT NULL,
	saldo DECIMAL(12,2) NOT NULL,
	createdAt TIMESTAMP NOT NULL,
	updatedAt TIMESTAMP NOT NULL,
	status BOOL NOT NULL,
	CONSTRAINT bankAccounts_pk PRIMARY KEY (id),
	CONSTRAINT bankAccounts_clients_FK FOREIGN KEY (clientId) REFERENCES TPFinalLaboratorioDeComputacionIV.clients(id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_as_cs;

CREATE TABLE `bankLoans` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `clientId` INTEGER NOT NULL,
  `bankAccountsId` INTEGER NOT NULL,
  `requestedAmount` decimal(12,2) DEFAULT NULL,
  `bankInterest` decimal(5,2) DEFAULT NULL,
  `amountWithInterest` decimal(12,2) DEFAULT NULL,
  `quotas` INTEGER DEFAULT NULL,
  `amountQuota` decimal(12,2) DEFAULT NULL,
  `status` enum('PENDING','AUTHORIZED','CLOSE','REFUSED') COLLATE utf8mb4_0900_as_cs NOT NULL DEFAULT 'PENDING',
  `applyDate` timestamp NULL DEFAULT NULL,
  `approvalDate` timestamp NULL DEFAULT NULL,
  `updatedAt` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `clientId` (`clientId`),
  KEY `bankAccountsId` (`bankAccountsId`),
  CONSTRAINT `bankLoans_ibfk_1` FOREIGN KEY (`clientId`) REFERENCES `clients` (`id`),
  CONSTRAINT `bankLoans_ibfk_2` FOREIGN KEY (`bankAccountsId`) REFERENCES `bankAccounts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_cs;

CREATE TABLE `bankLoansPayments` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `bankLoandsId` INTEGER NOT NULL,
  `amount` decimal(12,2) DEFAULT NULL,
  `quotaNumber` INTEGER DEFAULT NULL,
  `outstandingQuotas` INTEGER DEFAULT NULL,
  `paymentDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bankLoandsId` (`bankLoandsId`),
  CONSTRAINT `bankLoansPayments_ibfk_1` FOREIGN KEY (`bankLoandsId`) REFERENCES `bankLoans` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_cs;

CREATE TABLE `movements` (
  `id` int NOT NULL AUTO_INCREMENT,
  `clientId` int NOT NULL,
  `bankAccountsId` int NOT NULL,
  `amount` decimal(12,2) DEFAULT NULL,
  `typeMovements` enum('CREATEDBANKACCOUNT','CREATEDBANKLOAN','PAYMENTBANKLOAN','TRANSFER') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_cs NOT NULL,
  `detail` varchar(255) COLLATE utf8mb4_0900_as_cs DEFAULT NULL,
  `createdAt` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `clientId` (`clientId`),
  KEY `bankAccountsId` (`bankAccountsId`),
  CONSTRAINT `movements_ibfk_1` FOREIGN KEY (`clientId`) REFERENCES `clients` (`id`),
  CONSTRAINT `movements_ibfk_2` FOREIGN KEY (`bankAccountsId`) REFERENCES `bankAccounts` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_cs;

CREATE PROCEDURE TPFinalLaboratorioDeComputacionIV.AddBankAccount(
	IN CLIENTID INTEGER, 
	IN BANKACCOUNTTYPE ENUM('SAVINGSBANK', 'CURRENTACCOUNT'), 
	IN ACCOUNTNUMBER VARCHAR(100), 
	IN CBU VARCHAR(100)
	)
BEGIN
	
	DECLARE BANKACCOUNTID INTEGER;
	START TRANSACTION;
	
	    -- Adicionar la cuenta
		INSERT INTO TPFinalLaboratorioDeComputacionIV.bankAccounts
			(clientId, bankAccountType, accountNumber, CBU, saldo, createdAt, updatedAt, status)
			VALUES( CLIENTID, BANKACCOUNTTYPE, ACCOUNTNUMBER, CBU, 10000.00, NOW(), NOW(), 1);
	    
	    -- Obtengo el id de la cuenta bancaria recien creada
		SET BANKACCOUNTID = LAST_INSERT_ID();
	
	    -- Registrar el movimiento
		INSERT INTO TPFinalLaboratorioDeComputacionIV.movements
			( clientId, bankAccountsId, amount, typeMovements, detail, createdAt )
			VALUES( CLIENTID, BANKACCOUNTID, 10000.00, 'CREATEDBANKACCOUNT', null, NOW() );
	    
    COMMIT;
    
END;

CREATE PROCEDURE TPFinalLaboratorioDeComputacionIV.AuthorizeBankLoans(
    IN bankLoanId INT,
    IN bankAccountId INT,
    IN incrementAmount DECIMAL(12,2)
)
BEGIN
	
	START TRANSACTION;
    -- ACTUALIZAR ESTADO Y FECHA
    
	UPDATE TPFinalLaboratorioDeComputacionIV.bankLoans
        SET status = 'AUTHORIZED',
            updatedAt = NOW()
    WHERE id = bankLoanId;

    -- ACREDITAR SALDO Y ACTUALIZAR FECHA
    UPDATE TPFinalLaboratorioDeComputacionIV.bankAccounts
        SET saldo = saldo + incrementAmount,
            updatedAt = NOW()
    WHERE id = bankAccountId;
    
    COMMIT;
	
END;

INSERT INTO TPFinalLaboratorioDeComputacionIV.admins
(usr, pwd, profile, status, name, lastName, email, createdAt, updatedAt)
VALUES
('ADMIN', 'ADMIN', 'ADMIN', 1, 'Norman', 'Ruiz', 'norman.ruiz@alumnos.frgp.utn.edu.ar', NOW(), NOW()),
('nruiz', 'NRuiz1234', 'ADMIN', 1, 'Norman', 'Ruiz', 'norman.ruiz@outlook.com.ar', NOW(), NOW());

INSERT INTO TPFinalLaboratorioDeComputacionIV.clients
(usr, pwd, profile, status, DNI, CUIL, name, lastName, sex, nationality, birthdate, address, city, state, email, phone, createdAt, updatedAt)
VALUES('CLIENT', 'CLIENT', 'CLIENT', 1, '06669999', '20-27846268-5', 'Norman', 'Ruiz', 'MALE', 'Argentino', '1980-04-02', 'Muñoz 2964', 'San Miguel', 'Buenos Aires', 'norman.ruiz@alumnos.frgp.utn.edu.ar', '1140838968', NOW(), NOW()),
	  ('nruizc', 'NRuiz1234', 'CLIENT', 1, '27846268', '20-27846268-5', 'Norman', 'Ruiz', 'MALE', 'Argentino', '1980-04-02', 'Muñoz 2964', 'San Miguel', 'Buenos Aires', 'norman.ruiz@outlook.com.ar', '1140838968', NOW(), NOW());


