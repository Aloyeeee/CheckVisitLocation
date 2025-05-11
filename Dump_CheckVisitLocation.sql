CREATE DATABASE  IF NOT EXISTS `checkvisitlocation` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `checkvisitlocation`;
-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: checkvisitlocation
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `location_tags`
--

DROP TABLE IF EXISTS `location_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location_tags` (
  `location_id` bigint NOT NULL,
  `tag_id` bigint NOT NULL,
  PRIMARY KEY (`location_id`,`tag_id`),
  KEY `FKqmpxbisprsq6bow2afb90ja1x` (`tag_id`),
  CONSTRAINT `FKcx62x5vircxecs7ysq0bille5` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`),
  CONSTRAINT `FKqmpxbisprsq6bow2afb90ja1x` FOREIGN KEY (`tag_id`) REFERENCES `tags` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location_tags`
--

LOCK TABLES `location_tags` WRITE;
/*!40000 ALTER TABLE `location_tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `location_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location_translations`
--

DROP TABLE IF EXISTS `location_translations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location_translations` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(1000) DEFAULT NULL,
  `language_code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `location_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK92o04l0njm6xav58mqpo2srva` (`location_id`),
  CONSTRAINT `FK92o04l0njm6xav58mqpo2srva` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location_translations`
--

LOCK TABLES `location_translations` WRITE;
/*!40000 ALTER TABLE `location_translations` DISABLE KEYS */;
/*!40000 ALTER TABLE `location_translations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locations`
--

DROP TABLE IF EXISTS `locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `locations` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `geo_tag` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `type` enum('RESTAURANT','CAFE','RECREATION','BEACH','MUSEUM','PARK') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=653 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locations`
--

LOCK TABLES `locations` WRITE;
/*!40000 ALTER TABLE `locations` DISABLE KEYS */;
INSERT INTO `locations` VALUES (1,'вул. Харківська, 2/2','Сучасний ресторан із європейською кухнею та живою музикою.','50.9058, 34.7983','Графський двір','RESTAURANT'),(2,'вул. Соборна, 21','Затишне кафе з десертами власного виробництва.','50.9112, 34.7987','Карамель','CAFE'),(3,'вул. Петропавлівська, 63','Міський парк з атракціонами та озером.','50.9115, 34.7901','Парк імені Кожедуба','PARK'),(4,'вул. Герасима Кондратьєва, 160','Місце для прогулянок і пікніків біля Псла.','50.8970, 34.8142','Зона відпочинку Баранівка','RECREATION'),(5,'вул. Троїцька, 24а','Музей із багатою колекцією народного мистецтва.','50.9137, 34.7911','Сумський обласний краєзнавчий музей','MUSEUM'),(6,'вул. Покровська, 9','Кафе в центрі міста зі стильною атмосферою.','50.9118, 34.7962','Urban Coffee','CAFE'),(7,'вул. Героїв Сумщини, 2','Сучасна ресторація з панорамним видом на центр міста.','50.9084, 34.7972','Panorama','RESTAURANT'),(8,'вул. Привокзальна, 1','Озеро біля залізничного вокзалу, популярне місце для відпочинку.','50.9042, 34.7835','Озеро Чеха','RECREATION'),(9,'вул. Набережна р. Стрілки, 10','Міський пляж уздовж річки Стрілки.','50.9165, 34.8024','Пляж біля Стрілки','BEACH'),(10,'вул. Лушпи, 5','Просторий парк з дитячими майданчиками.','50.8990, 34.7914','Парк Казка','PARK'),(11,'вул. Харківська, 46','Популярне кафе з авторськими напоями.','50.8932, 34.8155','CoffeeMania','CAFE'),(12,'вул. Прокоф’єва, 22','Затишний ресторан з італійською кухнею.','50.8941, 34.8131','Bella Italia','RESTAURANT'),(13,'вул. Лушпи, 22','Природна зона для активного відпочинку.','50.8975, 34.7903','Сумська набережна','RECREATION'),(14,'вул. Воскресенська, 19','Культурно-мистецький центр у центрі міста.','50.9101, 34.7938','Галерея \"Наша\"','MUSEUM'),(15,'вул. 20 років Перемоги, 10','Сімейне кафе з дитячою зоною.','50.8979, 34.8067','Family Cafe','CAFE'),(16,'вул. Шевченка, 12','Елітний ресторан із винною картою.','50.9113, 34.7989','Шевченко','RESTAURANT'),(17,'вул. Нижньосироватська, 1','Міський пляж уздовж річки Псел.','50.9010, 34.8301','Пляж Псел','BEACH'),(18,'вул. Троїцька, 12','Зелений парк у центрі Сум.','50.9130, 34.7920','Центральний парк','PARK'),(19,'вул. Кооперативна, 3','Кафе-кондитерська з кавою та тістечками.','50.9115, 34.7950','Bakery','CAFE'),(20,'вул. Харківська, 5','Спортивно-розважальний парк.','50.8952, 34.8087','СпортЛенд','RECREATION'),(21,'вул. Героїв Крут, 3','Атмосферний ресторан з українською кухнею.','50.9025, 34.8102','Корчма','RESTAURANT'),(22,'вул. Охтирська, 13','Тихий парк у спальному районі.','50.8998, 34.7876','Парк Юність','PARK'),(23,'вул. Петропавлівська, 67','Кав’ярня в історичній частині міста.','50.9121, 34.7913','Кавова Хата','CAFE'),(24,'вул. Кондратьєва, 193','Простір для кемпінгу та шашликів.','50.9017, 34.8183','Пікнік-зона Баранівка','RECREATION'),(25,'вул. Перемоги, 9','Ресторан із живою музикою щовихідних.','50.9055, 34.7956','Golden Hall','RESTAURANT'),(26,'вул. СКД, 10','Кафе з авторською випічкою.','50.8963, 34.8047','Солодка Кава','CAFE'),(27,'вул. Герасима Кондратьєва, 164','Музей військової історії.','50.8975, 34.8139','Музей бойової слави','MUSEUM'),(28,'вул. Гамалія, 3','Пляжна зона для купання та засмаги.','50.8990, 34.8287','Міський пляж','BEACH'),(29,'вул. Прокоф’єва, 28','Локація для йоги та медитації на свіжому повітрі.','50.8936, 34.8145','Острів Спокою','RECREATION'),(30,'вул. Інтернаціоналістів, 12','Сімейний ресторан з дитячим меню.','50.8922, 34.8073','Дача','RESTAURANT'),(31,'вул. Реміснича, 2','Кафе зі швидким обслуговуванням.','50.9131, 34.7941','Кафетерій 24/7','CAFE'),(32,'вул. Першотравнева, 5','Музей сучасного мистецтва.','50.9107, 34.7954','Арт-Платформа','MUSEUM'),(33,'вул. Лушпи, 10','Парк для активного відпочинку та прогулянок.','50.8994, 34.7912','Зелена долина','PARK'),(34,'вул. Супруна, 11','Місце для культурних заходів і кінопоказів.','50.9098, 34.7967','Сумський Арт Хаб','RECREATION'),(35,'вул. Холодногірська, 3','Кав’ярня зі зручними місцями для роботи.','50.9021, 34.7960','WorkCafe','CAFE'),(36,'вул. Роменська, 2','Пляж на околиці Сум біля лісу.','50.8877, 34.8351','Лісовий пляж','BEACH'),(37,'вул. Петропавлівська, 88','Елітний ресторан з літньою терасою.','50.9129, 34.7904','Еліт','RESTAURANT'),(38,'вул. Іллінська, 15','Культурна пам’ятка з історичними експонатами.','50.9139, 34.7930','Музей історії міста Суми','MUSEUM'),(39,'вул. Прокоф’єва, 16','Кафе у стилі лофт із крафтовою кавою.','50.8933, 34.8122','Loft Coffee','CAFE'),(40,'вул. Лушпи, 15','Міський простір для відпочинку та ігор.','50.8986, 34.7905','Сімейний сквер','PARK'),(41,'вул. Засумська, 3','Пляж біля старого мосту.','50.9102, 34.8011','Старий пляж','BEACH'),(42,'вул. Холодногірська, 9','Ресторан для великих подій та банкетів.','50.9028, 34.7971','Форест','RESTAURANT'),(43,'вул. Героїв Сумщини, 4','Кафе з літнім майданчиком.','50.9086, 34.7978','Кавове місто','CAFE'),(44,'вул. Черепіна, 13','Тихий парк з лавками та зеленими насадженнями.','50.8969, 34.8124','Малий парк','PARK'),(45,'вул. Воскресенська, 15','Тематичний музей із екскурсіями для школярів.','50.9108, 34.7943','Музей науки','MUSEUM'),(46,'вул. Роменська, 10','Локація для кемпінгу та літнього відпочинку.','50.8888, 34.8340','Сосновий гай','RECREATION'),(47,'вул. Петропавлівська, 19','Кафе зі сніданками цілий день.','50.9117, 34.7934','Breakfast Club','CAFE'),(48,'вул. Харківська, 10','Ф’южн-ресторан із відкритою кухнею.','50.8945, 34.8095','Fusion','RESTAURANT'),(49,'вул. Нижньосироватська, 5','Популярне місце для відпочинку на природі.','50.8994, 34.8309','Лісовий простір','RECREATION'),(50,'вул. Кондратьєва, 200','Пляж з пірсом і волейбольним майданчиком.','50.8981, 34.8145','Пляж на Баранівці','BEACH'),(51,'Street 51, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 51 типу recreation у місті Суми','50.9151,34.7951','Recreation Place 51','RECREATION'),(52,'Street 52, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 52 типу beach у місті Суми','50.9152,34.7952','Beach Place 52','BEACH'),(53,'Street 53, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 53 типу museum у місті Суми','50.9153,34.7953','Museum Place 53','MUSEUM'),(54,'Street 54, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 54 типу park у місті Суми','50.9154,34.7954','Park Place 54','PARK'),(55,'Street 55, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 55 типу restaurant у місті Суми','50.9155,34.7955','Restaurant Place 55','RESTAURANT'),(56,'Street 56, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 56 типу cafe у місті Суми','50.9156,34.7956','Cafe Place 56','CAFE'),(57,'Street 57, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 57 типу recreation у місті Суми','50.9157,34.7957','Recreation Place 57','RECREATION'),(58,'Street 58, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 58 типу beach у місті Суми','50.9158,34.7958','Beach Place 58','BEACH'),(59,'Street 59, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 59 типу museum у місті Суми','50.9159,34.7959','Museum Place 59','MUSEUM'),(60,'Street 60, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 60 типу park у місті Суми','50.9160,34.7960','Park Place 60','PARK'),(61,'Street 61, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 61 типу restaurant у місті Суми','50.9161,34.7961','Restaurant Place 61','RESTAURANT'),(62,'Street 62, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 62 типу cafe у місті Суми','50.9162,34.7962','Cafe Place 62','CAFE'),(63,'Street 63, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 63 типу recreation у місті Суми','50.9163,34.7963','Recreation Place 63','RECREATION'),(64,'Street 64, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 64 типу beach у місті Суми','50.9164,34.7964','Beach Place 64','BEACH'),(65,'Street 65, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 65 типу museum у місті Суми','50.9165,34.7965','Museum Place 65','MUSEUM'),(66,'Street 66, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 66 типу park у місті Суми','50.9166,34.7966','Park Place 66','PARK'),(67,'Street 67, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 67 типу restaurant у місті Суми','50.9167,34.7967','Restaurant Place 67','RESTAURANT'),(68,'Street 68, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 68 типу cafe у місті Суми','50.9168,34.7968','Cafe Place 68','CAFE'),(69,'Street 69, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 69 типу recreation у місті Суми','50.9169,34.7969','Recreation Place 69','RECREATION'),(70,'Street 70, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 70 типу beach у місті Суми','50.9170,34.7970','Beach Place 70','BEACH'),(71,'Street 71, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 71 типу museum у місті Суми','50.9171,34.7971','Museum Place 71','MUSEUM'),(72,'Street 72, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 72 типу park у місті Суми','50.9172,34.7972','Park Place 72','PARK'),(73,'Street 73, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 73 типу restaurant у місті Суми','50.9173,34.7973','Restaurant Place 73','RESTAURANT'),(74,'Street 74, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 74 типу cafe у місті Суми','50.9174,34.7974','Cafe Place 74','CAFE'),(75,'Street 75, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 75 типу recreation у місті Суми','50.9175,34.7975','Recreation Place 75','RECREATION'),(76,'Street 76, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 76 типу beach у місті Суми','50.9176,34.7976','Beach Place 76','BEACH'),(77,'Street 77, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 77 типу museum у місті Суми','50.9177,34.7977','Museum Place 77','MUSEUM'),(78,'Street 78, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 78 типу park у місті Суми','50.9178,34.7978','Park Place 78','PARK'),(79,'Street 79, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 79 типу restaurant у місті Суми','50.9179,34.7979','Restaurant Place 79','RESTAURANT'),(80,'Street 80, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 80 типу cafe у місті Суми','50.9180,34.7980','Cafe Place 80','CAFE'),(81,'Street 81, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 81 типу recreation у місті Суми','50.9181,34.7981','Recreation Place 81','RECREATION'),(82,'Street 82, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 82 типу beach у місті Суми','50.9182,34.7982','Beach Place 82','BEACH'),(83,'Street 83, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 83 типу museum у місті Суми','50.9183,34.7983','Museum Place 83','MUSEUM'),(84,'Street 84, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 84 типу park у місті Суми','50.9184,34.7984','Park Place 84','PARK'),(85,'Street 85, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 85 типу restaurant у місті Суми','50.9185,34.7985','Restaurant Place 85','RESTAURANT'),(86,'Street 86, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 86 типу cafe у місті Суми','50.9186,34.7986','Cafe Place 86','CAFE'),(87,'Street 87, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 87 типу recreation у місті Суми','50.9187,34.7987','Recreation Place 87','RECREATION'),(88,'Street 88, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 88 типу beach у місті Суми','50.9188,34.7988','Beach Place 88','BEACH'),(89,'Street 89, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 89 типу museum у місті Суми','50.9189,34.7989','Museum Place 89','MUSEUM'),(90,'Street 90, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 90 типу park у місті Суми','50.9190,34.7990','Park Place 90','PARK'),(91,'Street 91, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 91 типу restaurant у місті Суми','50.9191,34.7991','Restaurant Place 91','RESTAURANT'),(92,'Street 92, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 92 типу cafe у місті Суми','50.9192,34.7992','Cafe Place 92','CAFE'),(93,'Street 93, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 93 типу recreation у місті Суми','50.9193,34.7993','Recreation Place 93','RECREATION'),(94,'Street 94, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 94 типу beach у місті Суми','50.9194,34.7994','Beach Place 94','BEACH'),(95,'Street 95, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 95 типу museum у місті Суми','50.9195,34.7995','Museum Place 95','MUSEUM'),(96,'Street 96, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 96 типу park у місті Суми','50.9196,34.7996','Park Place 96','PARK'),(97,'Street 97, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 97 типу restaurant у місті Суми','50.9197,34.7997','Restaurant Place 97','RESTAURANT'),(98,'Street 98, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 98 типу cafe у місті Суми','50.9198,34.7998','Cafe Place 98','CAFE'),(99,'Street 99, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 99 типу recreation у місті Суми','50.9199,34.7999','Recreation Place 99','RECREATION'),(100,'Street 100, Sumy, Sumska oblast, Ukraine, 40000','Опис локації 100 типу beach у місті Суми','50.9100,34.7900','Beach Place 100','BEACH'),(251,'Kharkivs\'ka St, 9, Sumy, Sums\'ka oblast, Ukraine, 40000','Затишний ресторан із романтичною атмосферою та смачною українською кухнею.','50.9123,34.8034','Sazha','RESTAURANT'),(529,'Pokrovska Square, 15, Sumy, Sums\'ka oblast, Ukraine, 40000','Популярний ресторан із бюджетними цінами та традиційними стравами.','50.9078,34.7985','Shalena Shkvarka','RESTAURANT');
/*!40000 ALTER TABLE `locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tags`
--

DROP TABLE IF EXISTS `tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tags` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `type` enum('ROMANTIC','BUDGET','FAMILY_FRIENDLY','PET_FRIENDLY','OUTDOOR','LUXURY','CULTURAL','ADVENTURE','RELAXING','FOODIE','NIGHTLIFE','HISTORICAL','SCENIC','ACCESSIBLE','ECO_FRIENDLY') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nx14m2p37yc6etfm0oax9ln84` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tags`
--

LOCK TABLES `tags` WRITE;
/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'$2a$10$YYUy/3ocpYkPcr5ClikpfO.zh5en3A7f73oJKpzrk4YrLKKykBvGO','string'),(2,'$2a$10$.8RkNaSg/AtJxWxvQucPLeV.xfPbjUPC8pHKCzt5qhS1NU.tr2UFK','stringgg');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visits`
--

DROP TABLE IF EXISTS `visits`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `visits` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `impressions` varchar(1000) DEFAULT NULL,
  `rating` int NOT NULL,
  `visit_date` date NOT NULL,
  `location_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdaf99cr0d5d7myg0l1ljj3o9o` (`location_id`),
  KEY `FK5kmnbgokfpcalwrminoedrb68` (`user_id`),
  CONSTRAINT `FK5kmnbgokfpcalwrminoedrb68` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKdaf99cr0d5d7myg0l1ljj3o9o` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visits`
--

LOCK TABLES `visits` WRITE;
/*!40000 ALTER TABLE `visits` DISABLE KEYS */;
INSERT INTO `visits` VALUES (1,'string',5,'2025-05-05',4,1),(2,'string',2,'2025-05-08',2,1),(3,'string',5,'2025-05-08',6,1),(4,'string',2,'2025-05-08',7,1),(5,'string',3,'2025-05-08',17,1),(6,'string',1,'2025-05-08',12,1);
/*!40000 ALTER TABLE `visits` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'checkvisitlocation'
--

--
-- Dumping routines for database 'checkvisitlocation'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-09 12:02:20
