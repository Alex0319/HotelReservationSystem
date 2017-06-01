-- MySQL dump 10.13  Distrib 5.7.12, for Win32 (AMD64)
--
-- Host: 127.0.0.1    Database: db_hotel
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discount` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` VALUES (1,'No discount'),(4,'rrrr');
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parking_space`
--

DROP TABLE IF EXISTS `parking_space`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parking_space` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `level` int(11) NOT NULL,
  `isReserved` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parking_space`
--

LOCK TABLES `parking_space` WRITE;
/*!40000 ALTER TABLE `parking_space` DISABLE KEYS */;
INSERT INTO `parking_space` VALUES (11,5,0);
/*!40000 ALTER TABLE `parking_space` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idUser` int(11) NOT NULL,
  `dateIn` date NOT NULL,
  `dateOut` date NOT NULL,
  `costAdditionalServices` int(11) NOT NULL,
  `idDiscount` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reservation_discount1_idx` (`idDiscount`),
  KEY `fk_reservation_user1_idx` (`idUser`),
  CONSTRAINT `fk_reservation_discount1` FOREIGN KEY (`idDiscount`) REFERENCES `discount` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservation_user1` FOREIGN KEY (`idUser`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (4,2,'2017-01-05','2017-02-25',0,1),(5,3,'2017-01-03','2017-01-26',0,1),(6,5,'2017-02-11','2017-02-26',0,1),(7,4,'2017-02-10','2017-02-12',1000,1),(8,2,'2016-05-11','2016-05-14',103,1),(9,3,'2017-03-07','2017-03-11',103,1),(10,3,'2017-09-04','2017-09-15',100,1),(11,3,'2016-06-04','2016-06-09',1000,1),(12,4,'2017-11-01','2017-11-12',150,1),(13,3,'2017-08-01','2017-08-09',100,1),(14,3,'2017-04-24','2017-05-03',300,1),(17,5,'2017-05-01','2017-05-11',0,1),(20,10,'2017-05-17','2017-05-27',200,1);
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation_parking_space`
--

DROP TABLE IF EXISTS `reservation_parking_space`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation_parking_space` (
  `idParkingSpace` int(11) NOT NULL,
  `idReservation` int(11) NOT NULL,
  PRIMARY KEY (`idParkingSpace`,`idReservation`),
  KEY `id_reserv_idx` (`idReservation`),
  CONSTRAINT `id_park` FOREIGN KEY (`idParkingSpace`) REFERENCES `parking_space` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `id_reserv` FOREIGN KEY (`idReservation`) REFERENCES `reservation` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation_parking_space`
--

LOCK TABLES `reservation_parking_space` WRITE;
/*!40000 ALTER TABLE `reservation_parking_space` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservation_parking_space` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation_room`
--

DROP TABLE IF EXISTS `reservation_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation_room` (
  `idRoom` int(11) NOT NULL,
  `idReservation` int(11) NOT NULL,
  PRIMARY KEY (`idRoom`,`idReservation`),
  KEY `id_reserv_idx` (`idReservation`),
  CONSTRAINT `id_res` FOREIGN KEY (`idReservation`) REFERENCES `reservation` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `id_room` FOREIGN KEY (`idRoom`) REFERENCES `room` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation_room`
--

LOCK TABLES `reservation_room` WRITE;
/*!40000 ALTER TABLE `reservation_room` DISABLE KEYS */;
INSERT INTO `reservation_room` VALUES (2,4),(3,4),(1,5),(2,6),(3,7),(2,9),(2,10),(1,11),(2,12),(4,12),(1,13),(2,14),(3,14);
/*!40000 ALTER TABLE `reservation_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nameRole` varchar(50) NOT NULL,
  `update` tinyint(4) NOT NULL,
  `delete` tinyint(4) NOT NULL,
  `insert` tinyint(4) NOT NULL,
  `create` tinyint(4) NOT NULL,
  `select` tinyint(4) NOT NULL,
  `drop` tinyint(4) NOT NULL,
  `grant` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'user',0,0,0,0,1,0,0),(3,'admin',1,1,1,1,1,1,1),(4,'usernew',0,0,0,0,0,0,0);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idRoomType` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `floor` int(11) NOT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_room_type_idx` (`idRoomType`),
  CONSTRAINT `id_room_type` FOREIGN KEY (`idRoomType`) REFERENCES `room_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,1,'Романтическая двушечка',2,'45678933','pathpathpathpathpath'),(2,4,'Тройка с огоньком',2,'18429423','pathpathpathpathpath'),(3,3,'Ты столько не осилишь',1,'46395741','pathpathpathpathpath'),(4,6,'12345678',3,'12345678/path','12345678/path');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_type`
--

DROP TABLE IF EXISTS `room_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roomsCount` int(11) unsigned NOT NULL,
  `bedsCount` int(11) unsigned NOT NULL,
  `costPerDay` float unsigned NOT NULL,
  `additionalInfo` varchar(85) NOT NULL,
  `bathroomsCount` int(11) unsigned NOT NULL,
  `size` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_type`
--

LOCK TABLES `room_type` WRITE;
/*!40000 ALTER TABLE `room_type` DISABLE KEYS */;
INSERT INTO `room_type` VALUES (1,3,3,40,'Hello',2,40),(2,3,2,50,'New',1,50),(3,4,2,60,'Second',2,60),(4,4,3,70,'Third',2,80),(5,5,3,90,'Fourth',2,100),(6,4,3,95,'Fifth',2,100),(10,2,5,2,'ksjfhkdsjhfsdkjh[]lp\'kyghtgf',1,2);
/*!40000 ALTER TABLE `room_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `passportNumber` varchar(10) NOT NULL,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `mobilePhone` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `idRole` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_role1_idx` (`idRole`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'HB1111121','Александр','Савчук','maxsavchuk.97@gmail.com','12345678','a0a937c77f7d92ddfc913d2e047315e2','login',1),(3,'HB1111131','Игорь','Козлов','maxsavchuk.97@gmail.com','37524577','a0a937c77f7d92ddfc913d2e047315e2','login',1),(4,'HB1111141','Анастасия','Даниленко','maxsavchuk.97@gmail.com','12345678','a0a937c77f7d92ddfc913d2e047315e2','login',1),(5,'HB1111151','Владислав','Грамович','maxsavchuk.97@gmail.com','54658445','a0a937c77f7d92ddfc913d2e047315e2','login',1),(10,'AB2663436','Alexandr','Savchuk','maxsavchuk.97@gmail.com','12345678','b682c6d288c912fe916f045d7c9658f8','AlexandrSavchuk97',1),(11,'AB26634336','Alex','Savchuk','alexsavchuk.97@gmail.com','375336428100','25d55ad283aa400af464c76d713c07ad','Alex97',1),(14,'AB2663435','Alex','Savchuk','alexkdj@gmail.com','432324342','7e2819b0c23ee334fc4a56b620d8a887','Alex97',1),(15,'AB2663436','Alec','Savchuk','kjsdjfhsdkjh@gmail.com','23783247378','25f9e794323b453885f5181f1b624d0b','ALexex97',1),(17,'AB2663436','Alex','Savchuk','alex@gmail.com','266572615','25f9e794323b453885f5181f1b624d0b','Alex97',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-01 13:25:40
