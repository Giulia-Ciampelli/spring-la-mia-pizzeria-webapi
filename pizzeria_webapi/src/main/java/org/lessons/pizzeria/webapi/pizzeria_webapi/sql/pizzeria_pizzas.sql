-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: pizzeria
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
-- Table structure for table `pizzas`
--

DROP TABLE IF EXISTS `pizzas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pizzas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `price` float NOT NULL,
  `description` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `url` longtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pizzas`
--

LOCK TABLES `pizzas` WRITE;
/*!40000 ALTER TABLE `pizzas` DISABLE KEYS */;
INSERT INTO `pizzas` VALUES (1,7.5,'Classic pizza with tomato, mozzarella, and basil.','Margherita','https://cdn.loveandlemons.com/wp-content/uploads/2019/09/margherita-pizza-1080x1080.jpg'),(2,6.5,'Simple yet delicious with tomato, garlic, oregano, and olive oil.','Marinara','https://thepizzaheaven.com/wp-content/uploads/2021/07/Pizza-Marinara.jpg'),(3,9,'Spicy pizza with tomato, mozzarella, and spicy salami.','Diavola','https://www.pizzarecipe.org/wp-content/uploads/2019/01/Pizza-Diavola-2000x1500.jpg'),(4,10,'A mix of four cheeses: mozzarella, gorgonzola, parmesan, and fontina.','Quattro Formaggi','https://arezzobistro.ro/wp-content/uploads/2022/08/Pizza-Quatro-Formaggi-600x600.jpg'),(5,11,'A rich mix of tomato, mozzarella, ham, mushrooms, artichokes, and olives.','Capricciosa','https://www.salamico.de/images/pizza_capricciosa_oben_Salamico.jpg'),(6,9.5,'Tomato sauce, mozzarella, ham, and mushrooms.','Prosciutto e Funghi','https://www.bestofcinqueterre.com/photos/cinque-terre-italian-food-pizza-ham-mushrooms.jpg'),(7,8.5,'Tomato, mozzarella, anchovies, and oregano.','Napoli','https://www.chilirezept.de/wp-content/uploads/2019/05/Pizza-Napoli-2000x1500.jpg'),(8,9.5,'Tomato, mozzarella, tuna, and onions.','Tonno e Cipolla','https://images.lacucinaitaliana.it/gallery/132283/Big/06b1c4a6-32ca-4105-bec1-97a9db322e58.jpg'),(9,12,'Mozzarella, sausage, mushrooms, and truffle oil.','Boscaiola','https://vecchianapoli.ro/userfiles/0/meniu/pix_med/poza-pizza-boscaiola-554.jpg'),(10,10,'A vegetarian delight with zucchini, bell peppers, eggplant, and tomato sauce.','Ortolana','https://www.expresschef.ro/wp-content/uploads/2020/11/Pizza-Ortolana-800x534.jpg'),(11,14,'Truffle cream, porcini mushrooms, and mozzarella.','Tartufo e Porcini','https://pizzaware.com/wp-content/uploads/2023/05/Traditional-tartufo.jpg'),(12,11.5,'Tomato, fresh buffalo mozzarella, and basil.','Bufalina','https://dostawa.spaccanapoli.pl/wp-content/uploads/2020/09/BUFALINA-2048x1367.jpg'),(13,13,'Tomato, mozzarella, and mixed seafood.','Frutti di Mare','https://eat.de/wp-content/uploads/2023/09/pizza-frutti-di-mare-3140-1536x1024.jpg'),(14,12.5,'Inspired by pasta carbonara with egg, pancetta, and parmesan.','Carbonara','https://images.squarespace-cdn.com/content/v1/5ded605ddfaed42363278927/1614656132555-AGZ4M6SPJ2RVRK10EJ1K/carbonara-pizza.jpeg'),(15,11,'Gorgonzola cheese, walnuts, and honey.','Gorgonzola e Noci','https://cdn.nonnapaperina.it/wp-content/uploads/2023/07/Pizza-con-cipolle-noci-e-gorgonzola-2-scaled.jpg?strip=all&lossy=1&quality=80&webp=67&sharp=1&w=1170&ssl=1'),(16,12.5,'Sausage, Neapolitan friarielli (broccoli rabe), and mozzarella.','Salsiccia e Friarielli','https://i1.wp.com/www.fermentobirra.com/wp-content/uploads/2018/07/Pizza-Salsiccia-e-Friarielli-classica-della-Pizzeria-Del-Popolo.jpeg?w=1200&ssl=1'),(17,12,'A gourmet mix of gorgonzola, pears, and mozzarella.','Zola e Pere','https://lagrotta-wroclaw.pl/wp-content/uploads/2022/01/1845214_LaGrottaRistorantePizzaEPasta_Food_PizzaZolaEPere.jpg'),(18,10,'Tomato, mozzarella, ham, and pineapple.','Hawaiian','https://recetasypostres.net/wp-content/uploads/2020/07/pizza-hawaiana.jpg'),(19,13.5,'Brie cheese, smoked speck, and a drizzle of balsamic glaze.','Brie e Speck','http://stefysflavours.altervista.org/wp-content/uploads/2021/03/pizza_con_porri_speck_e_brie_5-2048x1536.jpg'),(20,9.5,'Thinly sliced potatoes, rosemary, and mozzarella.','Patate e Rosmarino','https://www.ricettedalmondo.it/images/foto-ricette/p/29493-pizza-con-patate-e-rosmarino-2.jpg');
/*!40000 ALTER TABLE `pizzas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-26 14:42:52
