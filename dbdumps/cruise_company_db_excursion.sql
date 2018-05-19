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
-- Table structure for table `excursion`
--

DROP TABLE IF EXISTS `excursion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `excursion` (
  `excursion_id` int(11) NOT NULL AUTO_INCREMENT,
  `excursion_name_en` varchar(45) NOT NULL,
  `excursion_name_ua` varchar(45) NOT NULL,
  `price` int(11) NOT NULL,
  `port_id` int(11) NOT NULL,
  PRIMARY KEY (`excursion_id`,`port_id`),
  KEY `fk_excursions_ports1_idx` (`port_id`),
  CONSTRAINT `fk_excursions_ports1` FOREIGN KEY (`port_id`) REFERENCES `port` (`port_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `excursion`
--

LOCK TABLES `excursion` WRITE;
/*!40000 ALTER TABLE `excursion` DISABLE KEYS */;
INSERT INTO `excursion` VALUES (8,'San Miguel Monastery','Монастир Сан Мігель',350,1),(9,'Triangle Dali','Трикутник Далі',550,1),(10,'Monastery Montserrat','Монастир Монсеррат ',245,1),(11,'Egyptian Museum','Египетський Музей',69,2),(12,'Museum of Cinema','Музей Кіно',69,2),(13,'Medieval Towns','Середньовічні Міста',60,2),(14,'Excursion to Vesuvius','Екскурсія на Везувій',180,3),(15,'Excursion to Pompeii','Екскурсія в Помпеї',120,3),(16,'Excursion to Malta','Екскурсія по Мальті',250,4),(17,'Valletta and Medina','Валлетта и Медіна',260,4),(18,'Etna and Taormina','Етна і Таорміна',150,5),(19,'Valley of the Temples','Долина Храмів',129,5),(20,'Agrigento and Piazza','Агрідженто і П\'яцца',50,5),(21,'Alfabia Gardens','Сади Альфабія',150,6),(22,'Pearl Factory','Фабрика Перлин',100,6),(23,'Belver Castle','Замок Бельвер',125,6),(24,'Winery Arjolas','Винзавод Арджолас',80,7),(25,'Walking Tour','Пішохідна Екскурсія',30,7),(27,'Fort Michelangelo','Форт Мікеланджело',60,8),(28,'St. Francis Cathedral','Собор Св. Франциска',55,8),(29,'Luberon Valley','Долина Люберон',150,9),(30,'Verdon Gorge','Вердонська Ущелина',170,9),(31,'Village of Provence','Села Прованса',360,9),(32,'Fort Sumter','Форт Самтер',45,10),(33,'Cathedral of St. John','Собор Св. Іоанна',69,10),(34,'Charleston Prison','В\'язниця Чарльстона',50,10),(35,'Fort Finkale','Форт Фінкасл',80,11),(36,'Government House','Дім Уряду',110,11),(37,'Watling Distillery','Винокурня Уотлінга ',75,11),(40,'Everglades Park','Парк Еверглейдс',120,12),(41,'Oceanarium','Океанаріум',60,12),(42,'Coral Castle','Коралловий Замок',80,12),(43,'Review Tour','Оглядна Екскурсія',50,13),(44,'The Old Town','Старе Місто',45,14),(45,'Suzhou','Сучжоу',110,14),(46,'Zhujiajiao','Чжуцзяцзяо',230,14),(47,'Sakuradzima Volcano','Вулкан Сакурадзіма',150,15),(48,'Garden of Saint-Ghana-En','Сад Сен-Ган-Ен',100,15),(49,'Khirakawa Zoo','Зоопарк Хіракава',60,15);
/*!40000 ALTER TABLE `excursion` ENABLE KEYS */;
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
