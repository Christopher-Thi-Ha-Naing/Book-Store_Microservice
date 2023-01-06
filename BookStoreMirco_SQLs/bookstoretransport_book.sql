-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bookstoretransport
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `author_name` varchar(255) DEFAULT NULL,
  `category_name` varchar(255) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `item_count` int DEFAULT NULL,
  `price` double NOT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `year_published` date DEFAULT NULL,
  `transport_info_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKou446lvbr7jvaq6xy7eqkb4ls` (`transport_info_id`),
  CONSTRAINT `FKou446lvbr7jvaq6xy7eqkb4ls` FOREIGN KEY (`transport_info_id`) REFERENCES `transport_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'Ishiguro',NULL,'Novel','https://source.unsplash.com/random/400+300/?book',2,32.3,'Moron','A pale view of Hill','1978-02-12',NULL),(2,'Thomas Hardy',NULL,'Novel','https://source.unsplash.com/random/400+300/?nature',1,22.5,'Sun','Under the GreenWood Tree','1798-03-22',NULL),(4,'Thomas Hardy',NULL,'Novel','https://source.unsplash.com/random/400+300/?nature',NULL,22.5,'Sun','Under the GreenWood Tree','1798-03-22',4),(5,'DH Lawrence',NULL,'Novel','https://source.unsplash.com/random/400+300/?romance',NULL,22.5,'Sun','Sons and Lovers','1964-05-10',4),(6,'Ishiguro',NULL,'Novel','https://source.unsplash.com/random/400+300/?book',NULL,32.3,'Moron','A pale view of Hill','1978-02-12',5),(7,'Thomas Hardy',NULL,'Novel','https://source.unsplash.com/random/400+300/?nature',NULL,22.5,'Sun','Under the GreenWood Tree','1798-03-22',5),(8,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,6),(9,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,6),(10,'Ishiguro',NULL,'Novel','https://source.unsplash.com/random/400+300/?book',2,32.3,'Moron','A pale view of Hill','1978-02-12',7),(11,'Thomas Hardy',NULL,'Novel','https://source.unsplash.com/random/400+300/?nature',4,22.5,'Sun','Under the GreenWood Tree','1798-03-22',7),(12,'DH Lawrence',NULL,'Novel','https://source.unsplash.com/random/400+300/?women',1,23.5,'GreenBean','Women in Love','1970-03-23',8),(13,'Charles Dickens',NULL,'Novel','https://source.unsplash.com/random/400+300/?city',1,22.4,'GreenBean','A Tale of Two Cities','1690-03-10',8),(14,'Ishiguro',NULL,'Novel','https://source.unsplash.com/random/400+300/?book',2,32.3,'Moron','A pale view of Hill','1978-02-12',9),(15,'Ishiguro',NULL,'Novel','https://source.unsplash.com/random/400+300/?book',1,32.3,'Moron','A pale view of Hill','1978-02-12',10),(16,'DH Lawrence',NULL,'Novel','https://source.unsplash.com/random/400+300/?romance',2,22.5,'Sun','Sons and Lovers','1964-05-10',10),(17,'Ishiguro',NULL,'Novel','https://source.unsplash.com/random/400+300/?book',2,32.3,'Moron','A pale view of Hill','1978-02-12',11);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-06 10:38:48
