-- MySQL dump 10.13  Distrib 5.6.24, for osx10.8 (x86_64)
--
-- Host: 127.0.0.1    Database: patEx
-- ------------------------------------------------------
-- Server version	5.6.26

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
-- Table structure for table `newappt`
--

DROP TABLE IF EXISTS `newappt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `newappt` (
  `apptid` varchar(14) NOT NULL,
  `username` varchar(45) NOT NULL,
  `reqbegtime` time NOT NULL,
  `apptdate` date NOT NULL,
  `reason` varchar(45) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `c` varchar(1) NOT NULL,
  `docid` varchar(45) DEFAULT NULL,
  `endtime` time NOT NULL,
  PRIMARY KEY (`apptid`),
  UNIQUE KEY `apptid_UNIQUE` (`apptid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `newappt`
--

LOCK TABLES `newappt` WRITE;
/*!40000 ALTER TABLE `newappt` DISABLE KEYS */;
INSERT INTO `newappt` VALUES ('09acecb30e6e47','Taylor','16:00:00','2016-03-26','Fever','Temperature and headache','n','Dr.Jon','17:00:00'),('3670b10a6c8b40','Justin','17:00:00','2016-03-24','Knee injury','Caused by accident','n','Dr.Jon','18:00:00'),('506f2857de0745','Niall','15:00:00','2016-03-26','Typhoid','','n','Dr.Jon','16:00:00'),('5e00cd52420f45','Britney','14:00:00','2016-03-24','Pain','Pain for the past 2 days','n','Dr.Jon','15:00:00'),('8799afdd2b144f','Taylor','16:00:00','2016-03-24','Test','Examination','n','Dr.Jon','17:00:00'),('9257106ca3ca4a','Niall','15:00:00','2016-03-24','Checkup','Regular checkup','n','Dr.Jon','16:00:00'),('a81d0882681d4e','Justin','17:00:00','2016-03-26','Cold','Severe cold','n','Dr.Jon','18:00:00'),('ae6e27d5047541','Britney','14:00:00','2016-03-26','Headache','','n','Dr.Jon','15:00:00'),('b26489dd8a4c40','Justin','19:00:00','2016-03-24','Cold','','n','Dr.Cooper','20:00:00');
/*!40000 ALTER TABLE `newappt` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-24 11:28:55
