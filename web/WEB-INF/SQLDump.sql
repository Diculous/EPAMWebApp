CREATE DATABASE  IF NOT EXISTS `bank` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `bank`;
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: bank
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `accounts` (
  `idAccount` int(11) NOT NULL AUTO_INCREMENT,
  `accNumber` int(20) NOT NULL,
  `ownerID` int(11) NOT NULL,
  `isBlocked` tinyint(1) DEFAULT '0',
  `balance` int(11) DEFAULT '0',
  PRIMARY KEY (`idAccount`),
  KEY `accountOwner_idx` (`ownerID`),
  KEY `accountID_idx` (`ownerID`),
  CONSTRAINT `accountID` FOREIGN KEY (`ownerID`) REFERENCES `clients` (`idClient`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (1,1223242,1,0,100),(2,223424,3,0,10),(3,54321,5,0,1000),(4,12345,4,0,241),(5,425,1,0,1000),(6,4565,1,1,10000);
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cards`
--

DROP TABLE IF EXISTS `cards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cards` (
  `idCard` int(11) NOT NULL AUTO_INCREMENT,
  `cardNumber` int(16) NOT NULL,
  `CardType` varchar(45) NOT NULL,
  `AccID` int(11) NOT NULL,
  PRIMARY KEY (`idCard`),
  KEY `cardType_idx` (`CardType`),
  KEY `linkedAccount_idx` (`AccID`),
  CONSTRAINT `linkedAccount` FOREIGN KEY (`AccID`) REFERENCES `accounts` (`idAccount`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cards`
--

LOCK TABLES `cards` WRITE;
/*!40000 ALTER TABLE `cards` DISABLE KEYS */;
INSERT INTO `cards` VALUES (1,11,'1',1),(2,12,'2',1),(3,13,'3',2),(4,14,'2',3),(5,15,'1',2),(6,16,'1',1),(9,1425,'1',3);
/*!40000 ALTER TABLE `cards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cardtype`
--

DROP TABLE IF EXISTS `cardtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cardtype` (
  `idCardType` int(11) NOT NULL AUTO_INCREMENT,
  `cardType` varchar(45) NOT NULL,
  `cashbackBonus` int(3) DEFAULT NULL,
  PRIMARY KEY (`idCardType`),
  UNIQUE KEY `cardType_UNIQUE` (`cardType`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cardtype`
--

LOCK TABLES `cardtype` WRITE;
/*!40000 ALTER TABLE `cardtype` DISABLE KEYS */;
INSERT INTO `cardtype` VALUES (1,'Silver',5),(2,'Gold',10),(3,'Platinum',15);
/*!40000 ALTER TABLE `cardtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientlogindata`
--

DROP TABLE IF EXISTS `clientlogindata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `clientlogindata` (
  `id` int(11) NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientlogindata`
--

LOCK TABLES `clientlogindata` WRITE;
/*!40000 ALTER TABLE `clientlogindata` DISABLE KEYS */;
INSERT INTO `clientlogindata` VALUES (1,'Fir','123'),(2,'Sec','1234'),(3,'Thi','12345');
/*!40000 ALTER TABLE `clientlogindata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `clients` (
  `idClient` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `address` varchar(60) DEFAULT NULL,
  `passportNumber` varchar(20) DEFAULT NULL,
  `dateOfBirth` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idClient`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (1,'First','gsdsf','142587','12'),(2,'Second','fdgf','45228','11'),(3,'Third','ds','44574','157'),(4,'Forth','fgs','25514','145'),(5,'Fifth','sdfg','52565','15'),(6,'Sixth','sdfg','42425','47');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `employee` (
  `idEmployee` int(11) NOT NULL AUTO_INCREMENT,
  `employeeName` varchar(60) NOT NULL,
  `employeePosition` varchar(20) NOT NULL,
  PRIMARY KEY (`idEmployee`),
  UNIQUE KEY `idEmployee_UNIQUE` (`idEmployee`),
  KEY `employeeType_idx` (`employeePosition`),
  CONSTRAINT `employeeType` FOREIGN KEY (`employeePosition`) REFERENCES `employeetype` (`employeeType`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Clerk1','Clerk'),(2,'DirectorMain','Director'),(3,'Manager1','Manager'),(4,'Manager2','Manager'),(5,'Clerc2','Clerk'),(6,'Guardian','Guard');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employeetype`
--

DROP TABLE IF EXISTS `employeetype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `employeetype` (
  `idEmployeeType` int(11) NOT NULL AUTO_INCREMENT,
  `employeeType` varchar(20) NOT NULL,
  `baseSalary` int(11) NOT NULL,
  PRIMARY KEY (`idEmployeeType`),
  UNIQUE KEY `idEmployeeType_UNIQUE` (`idEmployeeType`),
  UNIQUE KEY `employeeType_UNIQUE` (`employeeType`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employeetype`
--

LOCK TABLES `employeetype` WRITE;
/*!40000 ALTER TABLE `employeetype` DISABLE KEYS */;
INSERT INTO `employeetype` VALUES (1,'Director',1000),(2,'Manager',800),(3,'Clerk',500),(4,'Guard',400);
/*!40000 ALTER TABLE `employeetype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operationtype`
--

DROP TABLE IF EXISTS `operationtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `operationtype` (
  `idOperationType` int(11) NOT NULL AUTO_INCREMENT,
  `operationType` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idOperationType`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operationtype`
--

LOCK TABLES `operationtype` WRITE;
/*!40000 ALTER TABLE `operationtype` DISABLE KEYS */;
INSERT INTO `operationtype` VALUES (1,'pay'),(2,'fill');
/*!40000 ALTER TABLE `operationtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `payments` (
  `idPayment` int(11) NOT NULL AUTO_INCREMENT,
  `paymentValue` int(11) NOT NULL,
  `AccID` int(11) NOT NULL,
  `operationType` int(11) NOT NULL,
  PRIMARY KEY (`idPayment`),
  KEY `AccID_idx` (`AccID`),
  KEY `OperationID_idx` (`operationType`),
  CONSTRAINT `AccID` FOREIGN KEY (`AccID`) REFERENCES `accounts` (`idAccount`),
  CONSTRAINT `OperationID` FOREIGN KEY (`operationType`) REFERENCES `operationtype` (`idOperationType`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

LOCK TABLES `payments` WRITE;
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
INSERT INTO `payments` VALUES (1,100,1,1),(2,11,2,1),(3,1,3,2),(4,111,1,2);
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-20 20:56:59
