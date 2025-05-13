-- MySQL dump 10.13  Distrib 8.0.39, for Win64 (x86_64)
--
-- Host: localhost    Database: practicasprofesionales
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `academicoevaluador`
--

DROP TABLE IF EXISTS `academicoevaluador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `academicoevaluador` (
  `numeroDeTrabajador` varchar(9) NOT NULL,
  `nombreAcademico` varchar(100) DEFAULT NULL,
  `Estudiante_matricula` varchar(9) NOT NULL,
  KEY `fk_AcademicoEvaluador_Estudiante1_idx` (`Estudiante_matricula`),
  CONSTRAINT `fk_AcademicoEvaluador_Estudiante1` FOREIGN KEY (`Estudiante_matricula`) REFERENCES `estudiante` (`matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `academicoevaluador`
--

LOCK TABLES `academicoevaluador` WRITE;
/*!40000 ALTER TABLE `academicoevaluador` DISABLE KEYS */;
/*!40000 ALTER TABLE `academicoevaluador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `autoevaluacion`
--

DROP TABLE IF EXISTS `autoevaluacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `autoevaluacion` (
  `puntaje` int NOT NULL,
  `totalHoras` int NOT NULL,
  `ProfesorEE_numeroDeTrabajador` varchar(9) NOT NULL,
  `Estudiante_matricula` varchar(9) NOT NULL,
  KEY `fk_AutoEvaluacion_ProfesorEE1_idx` (`ProfesorEE_numeroDeTrabajador`),
  KEY `fk_AutoEvaluacion_Estudiante1_idx` (`Estudiante_matricula`),
  CONSTRAINT `fk_AutoEvaluacion_Estudiante1` FOREIGN KEY (`Estudiante_matricula`) REFERENCES `estudiante` (`matricula`),
  CONSTRAINT `fk_AutoEvaluacion_ProfesorEE1` FOREIGN KEY (`ProfesorEE_numeroDeTrabajador`) REFERENCES `profesoree` (`numeroDeTrabajador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autoevaluacion`
--

LOCK TABLES `autoevaluacion` WRITE;
/*!40000 ALTER TABLE `autoevaluacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `autoevaluacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coordinador`
--

DROP TABLE IF EXISTS `coordinador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coordinador` (
  `numeroDeTrabajador` varchar(9) NOT NULL,
  `nombreCoordinador` varchar(100) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  PRIMARY KEY (`numeroDeTrabajador`),
  UNIQUE KEY `numeroDeTrabajador_UNIQUE` (`numeroDeTrabajador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coordinador`
--

LOCK TABLES `coordinador` WRITE;
/*!40000 ALTER TABLE `coordinador` DISABLE KEYS */;
/*!40000 ALTER TABLE `coordinador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estudiante`
--

DROP TABLE IF EXISTS `estudiante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estudiante` (
  `matricula` varchar(9) NOT NULL,
  `nombreEstudiante` varchar(100) NOT NULL,
  `periodoEscolar` varchar(45) NOT NULL,
  `seccionEstudiante` varchar(45) NOT NULL,
  `AvanceCrediticio` int NOT NULL,
  `promedio` double(2,2) NOT NULL,
  PRIMARY KEY (`matricula`),
  UNIQUE KEY `matricula_UNIQUE` (`matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estudiante`
--

LOCK TABLES `estudiante` WRITE;
/*!40000 ALTER TABLE `estudiante` DISABLE KEYS */;
INSERT INTO `estudiante` VALUES ('S23014093','Marcos Zenon Sanchez','Febrero 2025 - Junio 2025','Febrero 2025 - Junio 2025',58,8.00);
/*!40000 ALTER TABLE `estudiante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organizacionvinculada`
--

DROP TABLE IF EXISTS `organizacionvinculada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `organizacionvinculada` (
  `rfcMoral` varchar(12) NOT NULL,
  `nombreOV` varchar(45) NOT NULL,
  `telefonoOV` varchar(10) NOT NULL,
  `direccionOV` varchar(200) NOT NULL,
  PRIMARY KEY (`rfcMoral`),
  UNIQUE KEY `rfc_UNIQUE` (`rfcMoral`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organizacionvinculada`
--

LOCK TABLES `organizacionvinculada` WRITE;
/*!40000 ALTER TABLE `organizacionvinculada` DISABLE KEYS */;
INSERT INTO `organizacionvinculada` VALUES ('123456789012','Marcos','1234567890','papas 12'),('1234567890MA','Registro Test','2282929871','Moral 1234'),('SAMM981231A0','Tecno Pollos','2282929871','Av Villa Hermosa 23, Col Revolución'),('SAMM981231HV','SiDotcom','2282929871','Av Americas 123');
/*!40000 ALTER TABLE `organizacionvinculada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profesoree`
--

DROP TABLE IF EXISTS `profesoree`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profesoree` (
  `numeroDeTrabajador` varchar(9) NOT NULL,
  `seccion` varchar(45) NOT NULL,
  `nombreProfesor` varchar(45) NOT NULL,
  PRIMARY KEY (`numeroDeTrabajador`),
  UNIQUE KEY `numeroDeTrabajador_UNIQUE` (`numeroDeTrabajador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesoree`
--

LOCK TABLES `profesoree` WRITE;
/*!40000 ALTER TABLE `profesoree` DISABLE KEYS */;
/*!40000 ALTER TABLE `profesoree` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proyecto`
--

DROP TABLE IF EXISTS `proyecto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proyecto` (
  `titulo` varchar(100) NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaFinal` date NOT NULL,
  `periodoEscolar` varchar(45) NOT NULL,
  `Coordinador_numeroDeTrabajador` varchar(9) NOT NULL,
  `OrganizacionVinculada_rfc` varchar(12) NOT NULL,
  PRIMARY KEY (`titulo`),
  KEY `fk_Proyecto_Coordinador_idx` (`Coordinador_numeroDeTrabajador`),
  KEY `fk_Proyecto_OrganizacionVinculada1_idx` (`OrganizacionVinculada_rfc`),
  CONSTRAINT `fk_Proyecto_Coordinador` FOREIGN KEY (`Coordinador_numeroDeTrabajador`) REFERENCES `coordinador` (`numeroDeTrabajador`),
  CONSTRAINT `fk_Proyecto_OrganizacionVinculada1` FOREIGN KEY (`OrganizacionVinculada_rfc`) REFERENCES `organizacionvinculada` (`rfcMoral`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proyecto`
--

LOCK TABLES `proyecto` WRITE;
/*!40000 ALTER TABLE `proyecto` DISABLE KEYS */;
/*!40000 ALTER TABLE `proyecto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reporteparcial`
--

DROP TABLE IF EXISTS `reporteparcial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reporteparcial` (
  `fechaDeReporte` date NOT NULL,
  `horasReportadas` int NOT NULL,
  `Estudiante_matricula` varchar(9) NOT NULL,
  `ProfesorEE_numeroDeTrabajador` varchar(9) NOT NULL,
  PRIMARY KEY (`fechaDeReporte`),
  UNIQUE KEY `fechaDeReporte_UNIQUE` (`fechaDeReporte`),
  KEY `fk_ReporteParcial_Estudiante1_idx` (`Estudiante_matricula`),
  KEY `fk_ReporteParcial_ProfesorEE1_idx` (`ProfesorEE_numeroDeTrabajador`),
  CONSTRAINT `fk_ReporteParcial_Estudiante1` FOREIGN KEY (`Estudiante_matricula`) REFERENCES `estudiante` (`matricula`),
  CONSTRAINT `fk_ReporteParcial_ProfesorEE1` FOREIGN KEY (`ProfesorEE_numeroDeTrabajador`) REFERENCES `profesoree` (`numeroDeTrabajador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reporteparcial`
--

LOCK TABLES `reporteparcial` WRITE;
/*!40000 ALTER TABLE `reporteparcial` DISABLE KEYS */;
/*!40000 ALTER TABLE `reporteparcial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `representanteov`
--

DROP TABLE IF EXISTS `representanteov`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `representanteov` (
  `rfc` varchar(13) NOT NULL,
  `puesto` varchar(45) NOT NULL,
  `nombreRepresentante` varchar(45) NOT NULL,
  PRIMARY KEY (`rfc`),
  UNIQUE KEY `rfc_UNIQUE` (`rfc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `representanteov`
--

LOCK TABLES `representanteov` WRITE;
/*!40000 ALTER TABLE `representanteov` DISABLE KEYS */;
/*!40000 ALTER TABLE `representanteov` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-12 23:43:53
