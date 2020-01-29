-- MySQL dump 10.13  Distrib 8.0.19, for Linux (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `Departaments`
--

DROP TABLE IF EXISTS `Departaments`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Departaments`
(
    `idDepartaments` int NOT NULL AUTO_INCREMENT,
    `address`        varchar(255) DEFAULT NULL,
    `eMail`          varchar(255) DEFAULT NULL,
    `phoneNumber`    varchar(255) DEFAULT NULL,
    PRIMARY KEY (`idDepartaments`)
) ENGINE = MyISAM
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Departaments`
--

LOCK TABLES `Departaments` WRITE;
/*!40000 ALTER TABLE `Departaments`
    DISABLE KEYS */;
INSERT INTO `Departaments`
VALUES (1, 'HQ', 'hq@example.com', '111222333'),
       (2, 'Remote', 'remote@example.com', '444555666');
/*!40000 ALTER TABLE `Departaments`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Employees`
--

DROP TABLE IF EXISTS `Employees`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Employees`
(
    `idEmployees` int NOT NULL AUTO_INCREMENT,
    `firstName`   varchar(255) DEFAULT NULL,
    `surName`     varchar(255) DEFAULT NULL,
    `idDept`      int          DEFAULT NULL,
    `idJob`       int          DEFAULT NULL,
    PRIMARY KEY (`idEmployees`)
) ENGINE = MyISAM
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employees`
--

LOCK TABLES `Employees` WRITE;
/*!40000 ALTER TABLE `Employees`
    DISABLE KEYS */;
INSERT INTO `Employees`
VALUES (1, 'P', 'D', 1, 1),
       (2, 'D', 'M', 2, 2);
/*!40000 ALTER TABLE `Employees`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Jobs`
--

DROP TABLE IF EXISTS `Jobs`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Jobs`
(
    `idJobs`     int NOT NULL AUTO_INCREMENT,
    `name`       varchar(255) DEFAULT NULL,
    `baseSalary` int          DEFAULT NULL,
    PRIMARY KEY (`idJobs`)
) ENGINE = MyISAM
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Jobs`
--

LOCK TABLES `Jobs` WRITE;
/*!40000 ALTER TABLE `Jobs`
    DISABLE KEYS */;
INSERT INTO `Jobs`
VALUES (1, 'Manager', 15000),
       (2, 'Developer', 12000);
/*!40000 ALTER TABLE `Jobs`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `new_view`
--

DROP TABLE IF EXISTS `new_view`;
/*!50001 DROP VIEW IF EXISTS `new_view`*/;
SET @saved_cs_client = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `new_view` AS
SELECT 1 AS `Imie`,
       1 AS `Nazwisko`,
       1 AS `Pozycja`,
       1 AS `Zarobki`,
       1 AS `Miejsce pracy`,
       1 AS `Numer do miejsca pracy`,
       1 AS `e-mail do miejsca pracy`
*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `new_view`
--

/*!50001 DROP VIEW IF EXISTS `new_view`*/;
/*!50001 SET @saved_cs_client = @@character_set_client */;
/*!50001 SET @saved_cs_results = @@character_set_results */;
/*!50001 SET @saved_col_connection = @@collation_connection */;
/*!50001 SET character_set_client = utf8 */;
/*!50001 SET character_set_results = utf8 */;
/*!50001 SET collation_connection = utf8_general_ci */;
/*!50001 CREATE ALGORITHM = UNDEFINED */ /*!50013 DEFINER =`root`@`localhost` SQL SECURITY DEFINER */ /*!50001 VIEW `new_view` AS
select `Employees`.`firstName`      AS `Imie`,
       `Employees`.`surName`        AS `Nazwisko`,
       `Jobs`.`name`                AS `Pozycja`,
       `Jobs`.`baseSalary`          AS `Zarobki`,
       `Departaments`.`address`     AS `Miejsce pracy`,
       `Departaments`.`phoneNumber` AS `Numer do miejsca pracy`,
       `Departaments`.`eMail`       AS `e-mail do miejsca pracy`
from ((`Employees` join `Departaments` on ((`Employees`.`idDept` = `Departaments`.`idDepartaments`)))
         join `Jobs` on ((`Employees`.`idJob` = `Jobs`.`idJobs`)))
*/;
/*!50001 SET character_set_client = @saved_cs_client */;
/*!50001 SET character_set_results = @saved_cs_results */;
/*!50001 SET collation_connection = @saved_col_connection */;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2020-01-28 18:19:22
