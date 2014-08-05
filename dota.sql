CREATE DATABASE  IF NOT EXISTS `dota` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `dota`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: dota
-- ------------------------------------------------------
-- Server version	5.6.16

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
-- Table structure for table `heroes`
--

DROP TABLE IF EXISTS `heroes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `heroes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `img_url` varchar(256) NOT NULL,
  `begin_str` double NOT NULL,
  `begin_agi` double NOT NULL,
  `begin_int` double NOT NULL,
  `str_per_lvl` double NOT NULL,
  `agi_per_lvl` double NOT NULL,
  `int_per_lvl` double NOT NULL,
  `move_speed` bigint(20) NOT NULL,
  `turn_rate` double NOT NULL,
  `day_sight` bigint(20) NOT NULL,
  `night_sight` bigint(20) NOT NULL,
  `attack_range` varchar(45) NOT NULL,
  `missile_speed` varchar(45) NOT NULL,
  `cast_point` double NOT NULL,
  `cast_swing` double NOT NULL,
  `attack_point` double NOT NULL,
  `attack_swing` double NOT NULL,
  `base_attack_time` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `heroes`
--

LOCK TABLES `heroes` WRITE;
/*!40000 ALTER TABLE `heroes` DISABLE KEYS */;
INSERT INTO `heroes` VALUES (1,'Pudge','http://hydra-media.cursecdn.com/dota2.gamepedia.com/9/9f/Pudge.png?version=c01a13e32ee97b7c239867659e45aed8',25,14,14,3.2,1.5,1.5,285,0.5,1800,800,'Melee','Instant',0.3,0.51,0.5,1.17,1.7),(2,'Sand King','http://hydra-media.cursecdn.com/dota2.gamepedia.com/2/2c/Sand_King.png?version=b0cc92ecf39f1dbfffbe96f132ec1e77',18,19,16,2.6,2.1,1.8,300,0.5,1800,800,'Melee','Instant',0,0.51,0.53,0.47,1.7);
/*!40000 ALTER TABLE `heroes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `level_stat`
--

DROP TABLE IF EXISTS `level_stat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `level_stat` (
  `hero_id` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  `hit_points` bigint(20) DEFAULT NULL,
  `mana` bigint(20) DEFAULT NULL,
  `min_damage` int(11) DEFAULT NULL,
  `max_damage` int(11) DEFAULT NULL,
  `armor` double DEFAULT NULL,
  `attacks_per_second` double DEFAULT NULL,
  PRIMARY KEY (`hero_id`,`level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `level_stat`
--

LOCK TABLES `level_stat` WRITE;
/*!40000 ALTER TABLE `level_stat` DISABLE KEYS */;
/*!40000 ALTER TABLE `level_stat` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-08-05  7:03:42
