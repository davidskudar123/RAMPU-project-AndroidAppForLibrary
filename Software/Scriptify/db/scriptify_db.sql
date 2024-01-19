CREATE DATABASE  IF NOT EXISTS `scriptify_final` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `scriptify_final`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: scriptify_final
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `knjige`
--

DROP TABLE IF EXISTS `knjige`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `knjige` (
  `idKnjige` int NOT NULL AUTO_INCREMENT,
  `naziv_knjige` varchar(45) DEFAULT NULL,
  `autor` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idKnjige`)
) ENGINE=InnoDB AUTO_INCREMENT=513649219 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `knjige`
--

LOCK TABLES `knjige` WRITE;
/*!40000 ALTER TABLE `knjige` DISABLE KEYS */;
INSERT INTO `knjige` VALUES (1,'Astrophysics for people in a hurry','Neil DeGrass Tyso','An engaging and concise exploration of astrophysics, written by the renowned astrophysicist Neil deGrasse Tyson. This book provides a quick yet informative journey through the mysteries of the cosmos.'),(2,'Friends in New York','Mathew Perry','Beautiful story of 6 friends in New York'),(3,'Ana Karenjina','Fjodor Dostojevski','Amazing Book, about Ana'),(4,'Moja igra','Luka Modrić','\"Moja igra\" is written by the talented Croatian footballer Luka Modrić. In this autobiography, Modrić shares insights into his life, career, and the world of football'),(5,'Zločin i kazna','Fjodor Miljič Dostojevski','Man who killed grandma, and was emotional'),(6,'Tired of Winning','Donald Trump','Guide on how to win'),(323232,'Uvod u programiranje','Alen Lovrenčić','Uvod u c++ programiranje'),(9138960,'Uvod u programiranje','Mario Konecki i Alen Lovrencic','Uvod u programiranje'),(25950724,'testBook','autor','decs'),(38996773,'seg','esr','svd'),(91886543,'test','test','test'),(112270560,'test','te','test'),(123102973,'test','test','test'),(125982519,'test','test','test'),(152047813,'Uvod u algoritme','Alen Lovrenic','Uvod u sve vrste algoritama'),(173538435,'test','test','teste'),(189191730,'To Kill a Mocking Bir','Harper Lee','A classic novel that explores the issues of racial injustice and segregation.'),(199721778,'buba saba','buba saba','mala buba'),(211897043,'test','te','test'),(227475030,'how to love','Stura marino','volim luku'),(228499805,'test','test','test'),(284298740,'dvsvsd','fewwef','gwegwed'),(300380050,'Moj program','Marino Štura','Knjiga govori o prvom programu'),(384475309,'e','e','e'),(387626094,'gesg','test','sgeg'),(397472551,'test','test','test'),(406617487,'test','test','test'),(414527267,'test2','test2','test2'),(420784528,'hre','sdfs','sdf'),(423328433,'test','test','test'),(431630138,'t','t','t'),(468530946,'test','test','test'),(484525344,'Napredno programiranje 3','Alen Lovrencic','Napredni koncepti programiranja'),(510190237,'test','testreq.params.id','test'),(513649218,'test','test','test');
/*!40000 ALTER TABLE `knjige` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `knjizara`
--

DROP TABLE IF EXISTS `knjizara`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `knjizara` (
  `idKnjizara` int NOT NULL AUTO_INCREMENT,
  `ime` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idKnjizara`),
  UNIQUE KEY `idKnjizara_UNIQUE` (`idKnjizara`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `knjizara`
--

LOCK TABLES `knjizara` WRITE;
/*!40000 ALTER TABLE `knjizara` DISABLE KEYS */;
INSERT INTO `knjizara` VALUES (1,'Zagrebacka knjiznica'),(2,'Varazdinska knjiznica');
/*!40000 ALTER TABLE `knjizara` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `knjizara_has_knjige`
--

DROP TABLE IF EXISTS `knjizara_has_knjige`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `knjizara_has_knjige` (
  `knjizara_has_knjige_id` int NOT NULL AUTO_INCREMENT,
  `Knjizara_idKnjizara` int NOT NULL,
  `Knjige_idKnjige` int NOT NULL,
  PRIMARY KEY (`knjizara_has_knjige_id`,`Knjizara_idKnjizara`,`Knjige_idKnjige`),
  KEY `fk_Knjizara_has_Knjige_Knjige1_idx` (`Knjige_idKnjige`,`knjizara_has_knjige_id`),
  KEY `fk_Knjizara_has_Knjige_Knjizara1_idx` (`Knjizara_idKnjizara`),
  CONSTRAINT `knjizara_has_knjige_ibfk_1` FOREIGN KEY (`Knjizara_idKnjizara`) REFERENCES `knjizara` (`idKnjizara`),
  CONSTRAINT `knjizara_has_knjige_ibfk_2` FOREIGN KEY (`Knjige_idKnjige`) REFERENCES `knjige` (`idKnjige`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `knjizara_has_knjige`
--

LOCK TABLES `knjizara_has_knjige` WRITE;
/*!40000 ALTER TABLE `knjizara_has_knjige` DISABLE KEYS */;
INSERT INTO `knjizara_has_knjige` VALUES (13,1,1),(14,1,2),(15,2,2),(16,2,3);
/*!40000 ALTER TABLE `knjizara_has_knjige` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews` (
  `idReview` int NOT NULL AUTO_INCREMENT,
  `idKnjige` int DEFAULT NULL,
  `review_text` varchar(255) DEFAULT NULL,
  `rating` int DEFAULT NULL,
  PRIMARY KEY (`idReview`),
  KEY `idKnjige` (`idKnjige`),
  CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`idKnjige`) REFERENCES `knjige` (`idKnjige`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id_user` int NOT NULL AUTO_INCREMENT,
  `username` varchar(16) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(32) NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `address` varchar(255) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `money` decimal(10,0) DEFAULT NULL,
  `status` text,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `id_user_UNIQUE` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'john_doe','john.doe@example.com','1234','2023-11-05 16:00:42','123 Main St, City, Country','John ','Doe',495,'I like reading a lot'),(2,'jane_smith','jane.smith@example.com','password456','2023-11-05 16:00:42','456 Elm St, Town, Country','Jane ','Smith',135,'Reading is my favourite hobby'),(3,'joey_balboa','yoe_te@gmail.com','12345','2023-11-17 20:47:07','765 Royal Lane, USA','Joe','Balboa',0,'I like books and the gym'),(4,'test','test','test','2023-11-18 15:38:35','test','test','test',25,NULL),(5,'marino','mojmail@gmail.com','1234','2023-11-18 15:44:39','Sv. Bana jelacica 5','Marino','Štura',50,NULL),(7,'test','test','test','2023-11-18 15:47:27','test','test','test',NULL,NULL),(8,'test','test','ttest','2023-11-18 16:21:34','test','test','test',NULL,NULL),(9,'tst','test','test','2023-11-18 16:23:33','test','test','test',NULL,NULL),(10,'tst','test','test','2023-11-18 16:25:24','test','test','test',NULL,NULL),(11,'mrvu','krse.volim@gmail.con','krse123','2023-11-18 16:47:54','foi 4','Luka','Krsevan nizic',NULL,NULL),(12,'mrvu','krse.volim@gmail.com','krse123','2023-11-18 16:47:58','foi 4','Luka','Krsevan nizic',NULL,NULL),(13,'mrvu','krse.volim@gmail.com','krse123','2023-11-18 16:48:02','foi 4','Luka','Krsevan ',NULL,NULL),(14,'mrvu','krse.volim@gmail.com','krse123','2023-11-18 16:48:07','foi 4','Luka','Krsevan nizic',NULL,NULL),(15,'mrvi','krse.volim@gmail.com','krse123','2023-11-18 16:48:13','foi 4','Luka','Krsevan nizic',NULL,NULL),(16,'mrvi','krse.volim@gmail.com','krse123','2023-11-18 16:48:20','foi 4','Luka','Krsevan nizic',NULL,NULL),(17,'mrvi','krse.volim@gmail.com','krse123','2023-11-18 16:48:21','foi 4','Luka','Krsevan nizic',NULL,NULL),(18,'t','t','t','2023-11-18 16:49:21','t','t','t',NULL,NULL),(19,'krse','volim.krsu@gmail.com','krse123','2023-11-18 16:50:03','foi 4','Luka','Krsevan Nizic',200,NULL),(20,'bubasba','mgveric7@gmail.com','hah','2023-11-18 19:04:56','bubiceva kuca','buha','saba',100,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_has_knjige`
--

DROP TABLE IF EXISTS `user_has_knjige`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_has_knjige` (
  `user_id_user` int NOT NULL,
  `Knjige_idKnjige` int NOT NULL,
  PRIMARY KEY (`user_id_user`,`Knjige_idKnjige`),
  KEY `fk_user_has_Knjige_Knjige1_idx` (`Knjige_idKnjige`),
  KEY `fk_user_has_Knjige_user1_idx` (`user_id_user`),
  CONSTRAINT `user_has_knjige_ibfk_1` FOREIGN KEY (`user_id_user`) REFERENCES `user` (`id_user`),
  CONSTRAINT `user_has_knjige_ibfk_2` FOREIGN KEY (`Knjige_idKnjige`) REFERENCES `knjige` (`idKnjige`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_has_knjige`
--

LOCK TABLES `user_has_knjige` WRITE;
/*!40000 ALTER TABLE `user_has_knjige` DISABLE KEYS */;
INSERT INTO `user_has_knjige` VALUES (1,4),(1,5),(2,5),(1,6),(2,6),(5,123102973),(2,152047813),(19,227475030),(4,484525344);
/*!40000 ALTER TABLE `user_has_knjige` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_has_knjizara_has_knjige`
--

DROP TABLE IF EXISTS `user_has_knjizara_has_knjige`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_has_knjizara_has_knjige` (
  `user_id_user` int NOT NULL,
  `Knjizara_has_Knjige_knjizara_has_knjige_id` int NOT NULL,
  PRIMARY KEY (`user_id_user`,`Knjizara_has_Knjige_knjizara_has_knjige_id`),
  KEY `user_has_Knjizara_has_Knjige_fk_idx` (`Knjizara_has_Knjige_knjizara_has_knjige_id`),
  KEY `user_has_Knjizara_has_Knjige_user_idx` (`user_id_user`),
  CONSTRAINT `fk_user_has_Knjizara_has_Knjige_Knjizara_has_Knjige1` FOREIGN KEY (`Knjizara_has_Knjige_knjizara_has_knjige_id`) REFERENCES `knjizara_has_knjige` (`knjizara_has_knjige_id`),
  CONSTRAINT `fk_user_has_Knjizara_has_Knjige_user1` FOREIGN KEY (`user_id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_has_knjizara_has_knjige`
--

LOCK TABLES `user_has_knjizara_has_knjige` WRITE;
/*!40000 ALTER TABLE `user_has_knjizara_has_knjige` DISABLE KEYS */;
INSERT INTO `user_has_knjizara_has_knjige` VALUES (1,13),(2,14);
/*!40000 ALTER TABLE `user_has_knjizara_has_knjige` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-19 19:48:09
