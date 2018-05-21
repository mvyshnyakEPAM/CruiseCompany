-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: cruise_company_db
-- ------------------------------------------------------
-- Server version	5.7.19-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `port_has_ship`
--

DROP TABLE IF EXISTS `port_has_ship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `port_has_ship` (
  `port_id` int(11) NOT NULL,
  `ship_id` int(11) NOT NULL,
  `order_number` int(11) NOT NULL,
  UNIQUE KEY `index3` (`port_id`,`ship_id`,`order_number`),
  KEY `fk_port_has_cruises_cruises1_idx` (`ship_id`),
  KEY `fk_port_has_cruises_port1_idx` (`port_id`),
  CONSTRAINT `fk_port_has_cruises_cruises1` FOREIGN KEY (`ship_id`) REFERENCES `ship` (`ship_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_port_has_cruises_port1` FOREIGN KEY (`port_id`) REFERENCES `port` (`port_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `port_has_ship`
--

LOCK TABLES `port_has_ship` WRITE;
/*!40000 ALTER TABLE `port_has_ship` DISABLE KEYS */;
INSERT INTO `port_has_ship` VALUES (1,1,1),(1,1,6),(2,1,2),(3,1,3),(4,1,5),(5,1,4),(1,2,1),(1,2,5),(7,2,2),(8,2,3),(9,2,4),(10,3,1),(10,3,3),(11,3,2),(11,4,3),(12,4,1),(12,4,4),(13,4,2),(1,5,3),(3,5,2),(8,5,1),(14,6,1),(14,6,3),(15,6,2),(1,7,2),(2,7,1),(2,7,4),(9,7,3),(1,8,1),(1,8,5),(2,8,2),(3,8,3),(6,8,4);
/*!40000 ALTER TABLE `port_has_ship` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-20 22:50:06
