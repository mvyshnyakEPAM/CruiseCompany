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
-- Table structure for table `ship`
--

DROP TABLE IF EXISTS `ship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ship` (
  `ship_id` int(11) NOT NULL AUTO_INCREMENT,
  `ship_name_en` varchar(45) NOT NULL,
  `ship_name_ua` varchar(45) NOT NULL,
  `price` int(11) NOT NULL,
  `ports_visited` int(11) NOT NULL,
  `departure` varchar(20) NOT NULL,
  `arrival` varchar(20) NOT NULL,
  `cruise_duration` int(11) NOT NULL,
  `ship_class` enum('STANDARD','PREMIUM','LUX') NOT NULL,
  `passenger_capacity` int(11) NOT NULL,
  `free_places` int(11) NOT NULL,
  `staff` int(11) NOT NULL,
  PRIMARY KEY (`ship_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ship`
--

LOCK TABLES `ship` WRITE;
/*!40000 ALTER TABLE `ship` DISABLE KEYS */;
INSERT INTO `ship` VALUES (1,'Cruise Fascinosa','Круїз Фешиноза',639,6,'2018-09-29','2018-10-05',7,'PREMIUM',2860,1,1100),(2,'Cruise Diadema','Круїз Діадема',749,8,'2018-06-18','2018-06-25',8,'PREMIUM',2950,3,1100),(3,'Cruise Ecstasy','Круїз Екстаз',1503,3,'2019-01-27','2019-01-31',5,'STANDARD',2052,452,920),(4,'Cruise Sensation','Круїз Сенсація',435,4,'2019.01.10','2019.01.14',5,'STANDARD',2052,56,1369),(5,'Cruise Royal','Круїз Королівський',739,3,'2018.10.11','2018.10.14',4,'LUX',6780,765,2175),(6,'Cruise Quantum','Круїз Квантум',494,3,'2018.11.22','2018.11.26',5,'LUX',4905,325,1534),(7,'Cruise Mediterranea','Круїз Медітеррена',295,4,'2018.11.15','2018.11.18',4,'PREMIUM',2680,876,897),(8,'Cruise Crystal','Круїз Кристал',368,5,'2018.10.12','2018.11.17',6,'STANDARD',1200,543,456);
/*!40000 ALTER TABLE `ship` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-20  0:47:31
