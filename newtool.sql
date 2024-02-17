-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: daily_report_system
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `employees`
--

USE heroku_5cc47ab7d4bed21;

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `employees` (
  `delete_flg` tinyint NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `code` varchar(10) COLLATE utf8mb3_general_ci NOT NULL,
  `name` varchar(20) COLLATE utf8mb3_general_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb3_general_ci NOT NULL,
  `role` varchar(10) COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (0,'2024-02-18 06:27:21.000000','2024-02-18 06:27:21.000000','1','aaa名前 管理者','$2a$10$vY93/U2cXCfEMBESYnDJUevcjJ208sXav23S.K8elE/J6Sxr4w5jO','ADMIN'),(0,'2024-02-18 06:27:21.000000','2024-02-18 06:27:21.000000','2','bbb名前 管理者','$2a$10$vY93/U2cXCfEMBESYnDJUevcjJ208sXav23S.K8elE/J6Sxr4w5jO','ADMIN'),(0,'2024-02-18 06:27:21.000000','2024-02-18 06:27:21.000000','3','ccc名前 管理者','$2a$10$vY93/U2cXCfEMBESYnDJUevcjJ208sXav23S.K8elE/J6Sxr4w5jO','ADMIN'),(0,'2024-02-18 06:27:21.000000','2024-02-18 06:27:21.000000','4','ddd名前 管理者','$2a$10$vY93/U2cXCfEMBESYnDJUevcjJ208sXav23S.K8elE/J6Sxr4w5jO','ADMIN'),(0,'2024-02-18 06:27:21.000000','2024-02-18 06:27:21.000000','5','eee名前 管理者','$2a$10$vY93/U2cXCfEMBESYnDJUevcjJ208sXav23S.K8elE/J6Sxr4w5jO','ADMIN'),(0,'2024-02-18 06:27:21.000000','2024-02-18 06:27:21.000000','6','fff名前 店員','$2a$10$HPIjRCymeRZKEIq.71TDduiEotOlb8Ai6KQUHCs4lGNYlLhcKv4Wi','GENERAL'),(0,'2024-02-18 06:27:21.000000','2024-02-18 06:27:21.000000','7','ggg名前 店員','$2a$10$HPIjRCymeRZKEIq.71TDduiEotOlb8Ai6KQUHCs4lGNYlLhcKv4Wi','GENERAL'),(0,'2024-02-18 06:27:21.000000','2024-02-18 06:27:21.000000','8','hhh名前 店員','$2a$10$HPIjRCymeRZKEIq.71TDduiEotOlb8Ai6KQUHCs4lGNYlLhcKv4Wi','GENERAL'),(0,'2024-02-18 06:27:21.000000','2024-02-18 06:27:21.000000','9','iii名前 店員','$2a$10$HPIjRCymeRZKEIq.71TDduiEotOlb8Ai6KQUHCs4lGNYlLhcKv4Wi','GENERAL');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reports`
--

DROP TABLE IF EXISTS `reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `reports` (
  `delete_flg` tinyint NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `report_date` date NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `employee_code` varchar(10) COLLATE utf8mb3_general_ci NOT NULL,
  `title` varchar(100) COLLATE utf8mb3_general_ci NOT NULL,
  `content` longtext COLLATE utf8mb3_general_ci,
  PRIMARY KEY (`id`),
  KEY `FKml3syulw48umvbjfdaeqawq3b` (`employee_code`),
  CONSTRAINT `FKml3syulw48umvbjfdaeqawq3b` FOREIGN KEY (`employee_code`) REFERENCES `employees` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reports`
--

LOCK TABLES `reports` WRITE;
/*!40000 ALTER TABLE `reports` DISABLE KEYS */;
INSERT INTO `reports` VALUES (0,1,'2024-02-18','2024-02-18 06:27:21.000000','2024-02-18 06:27:21.000000','1','1のaaa、タイトル','aaa、内容'),(0,2,'2024-02-18','2024-02-18 06:27:21.000000','2024-02-18 06:27:21.000000','2','2のbbb、タイトル','bbb、内容'),(0,3,'2024-02-18','2024-02-18 06:27:21.000000','2024-02-18 06:27:21.000000','1','1のccc、タイトル','ccc、内容'),(0,4,'2024-02-18','2024-02-18 06:27:21.000000','2024-02-18 06:27:21.000000','4','4のddd、タイトル','ddd、内容'),(0,5,'2024-02-18','2024-02-18 06:27:21.000000','2024-02-18 06:27:21.000000','1','1のeee、タイトル','eee、内容'),(0,6,'2024-02-18','2024-02-18 06:27:21.000000','2024-02-18 06:27:21.000000','6','6のfff、タイトル','fff、内容'),(0,7,'2024-02-18','2024-02-18 06:27:21.000000','2024-02-18 06:27:21.000000','1','1のggg、タイトル','ggg、内容'),(0,8,'2024-02-18','2024-02-18 06:27:21.000000','2024-02-18 06:27:21.000000','8','8のhhh、タイトル','hhh、内容'),(0,9,'2024-02-18','2024-02-18 06:27:21.000000','2024-02-18 06:27:21.000000','1','1のiii、タイトル','iii、内容');
/*!40000 ALTER TABLE `reports` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-18  6:37:47
