/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 8.0.30 : Database - inventorydata
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`inventorydata` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `inventorydata`;

/*Table structure for table `auditlogs` */

DROP TABLE IF EXISTS `auditlogs`;

CREATE TABLE `auditlogs` (
  `audit_log_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `action` varchar(255) DEFAULT NULL,
  `entity` varchar(255) DEFAULT NULL,
  `entity_id` bigint DEFAULT NULL,
  `ip_address` varchar(255) DEFAULT NULL,
  `user_user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`audit_log_id`),
  KEY `FKcgme55q95u3qwy57w6q41o714` (`user_user_name`),
  CONSTRAINT `FKcgme55q95u3qwy57w6q41o714` FOREIGN KEY (`user_user_name`) REFERENCES `users` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `auditlogs` */

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `category_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `category_name` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `category` */

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `customer_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `billing_address` varchar(500) DEFAULT NULL,
  `customer_type` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `loyalty_points` int DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `shipping_address` varchar(500) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `customer` */

insert  into `customer`(`customer_id`,`created_at`,`created_by`,`updated_at`,`updated_by`,`billing_address`,`customer_type`,`email`,`first_name`,`last_name`,`loyalty_points`,`phone`,`shipping_address`,`status`) values 
(1,'2025-02-05 18:54:31.602995',NULL,'2025-02-05 18:54:31.602995',NULL,'hffgkhgkhjljlj','Regular','tarik.du.esol.4th@gmail.com','Mona','Boudi',23,'987-654-3210','jljhljklfgchfhfhfg','Active');

/*Table structure for table `discount` */

DROP TABLE IF EXISTS `discount`;

CREATE TABLE `discount` (
  `discount_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `applicable_to` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `discount_code` varchar(255) DEFAULT NULL,
  `discount_type` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `valid_from` datetime(6) DEFAULT NULL,
  `valid_until` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`discount_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `discount` */

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `employee_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `hire_date` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `role_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  KEY `FK4am1h1y1msy35hglg78wjiv2a` (`role_id`),
  CONSTRAINT `FK4am1h1y1msy35hglg78wjiv2a` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `employee` */

insert  into `employee`(`employee_id`,`created_at`,`created_by`,`updated_at`,`updated_by`,`email`,`hire_date`,`name`,`phone`,`salary`,`status`,`role_id`) values 
(1,NULL,NULL,NULL,NULL,'john.doe@example.com','2025-02-05','John Doe','123-456-7890',55000,'Active',NULL),
(3,NULL,NULL,'2025-02-05 18:05:10.090777',NULL,'dfhngfj@gmail.com','2025-02-05','Nazmul','+1234567890',1004663,'Active',NULL),
(4,NULL,NULL,'2025-02-05 18:06:09.258842',NULL,'pagol@gmail.com','2025-02-05','Rakib','+15678901234',57568679789,'Active',NULL);

/*Table structure for table `inventory` */

DROP TABLE IF EXISTS `inventory`;

CREATE TABLE `inventory` (
  `inventory_id` bigint NOT NULL AUTO_INCREMENT,
  `created_time` datetime(6) DEFAULT NULL,
  `quantity_allocated` int DEFAULT NULL,
  `quantity_available` int DEFAULT NULL,
  `quantity_on_hand` int DEFAULT NULL,
  `updated_time` datetime(6) DEFAULT NULL,
  `product_id` bigint NOT NULL,
  `warehouse_id` bigint NOT NULL,
  PRIMARY KEY (`inventory_id`),
  KEY `FKp7gj4l80fx8v0uap3b2crjwp5` (`product_id`),
  KEY `FKix9yxgetau1y25hhnv42gsiok` (`warehouse_id`),
  CONSTRAINT `FKix9yxgetau1y25hhnv42gsiok` FOREIGN KEY (`warehouse_id`) REFERENCES `warehouse` (`warehouse_id`),
  CONSTRAINT `FKp7gj4l80fx8v0uap3b2crjwp5` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `inventory` */

/*Table structure for table `notification` */

DROP TABLE IF EXISTS `notification`;

CREATE TABLE `notification` (
  `notification_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `notification_type` enum('CRITICAL','INFO','WARNING') DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`notification_id`),
  KEY `FK65lrosgs26526g1rwlljpkeu0` (`user_user_name`),
  CONSTRAINT `FK65lrosgs26526g1rwlljpkeu0` FOREIGN KEY (`user_user_name`) REFERENCES `users` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `notification` */

/*Table structure for table `order_item` */

DROP TABLE IF EXISTS `order_item`;

CREATE TABLE `order_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_time` datetime(6) DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `tax` double DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `unit_price` double DEFAULT NULL,
  `updated_time` datetime(6) DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt4dc2r9nbvbujrljv3e23iibt` (`order_id`),
  KEY `FK551losx9j75ss5d6bfsqvijna` (`product_id`),
  CONSTRAINT `FK551losx9j75ss5d6bfsqvijna` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `FKt4dc2r9nbvbujrljv3e23iibt` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `order_item` */

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `order_id` bigint NOT NULL AUTO_INCREMENT,
  `billing_address` varchar(255) DEFAULT NULL,
  `created_time` datetime(6) DEFAULT NULL,
  `customer_contact` varchar(255) DEFAULT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  `order_date` datetime(6) DEFAULT NULL,
  `payment_status` enum('paid','pending') DEFAULT NULL,
  `shipping_address` varchar(255) DEFAULT NULL,
  `shipping_method` varchar(255) DEFAULT NULL,
  `status` enum('CANCELLED','DELIVERED','PENDING','PROCESSING','SHIPPED') DEFAULT NULL,
  `total_amount` double DEFAULT NULL,
  `updated_time` datetime(6) DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  `discount_id` bigint DEFAULT NULL,
  `employee_id` bigint DEFAULT NULL,
  `shipment_id` bigint DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FK624gtjin3po807j3vix093tlf` (`customer_id`),
  KEY `FKdovsc3u2it5yoknwgx4brjid1` (`discount_id`),
  KEY `FKog5v9ga2g2ukytypn4ocip6b4` (`employee_id`),
  KEY `FK8bfmxsa3iccyqkkqk224slw2e` (`shipment_id`),
  CONSTRAINT `FK624gtjin3po807j3vix093tlf` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  CONSTRAINT `FK8bfmxsa3iccyqkkqk224slw2e` FOREIGN KEY (`shipment_id`) REFERENCES `shipment` (`shipment_id`),
  CONSTRAINT `FKdovsc3u2it5yoknwgx4brjid1` FOREIGN KEY (`discount_id`) REFERENCES `discount` (`discount_id`),
  CONSTRAINT `FKog5v9ga2g2ukytypn4ocip6b4` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `orders` */

/*Table structure for table `orders_taxes` */

DROP TABLE IF EXISTS `orders_taxes`;

CREATE TABLE `orders_taxes` (
  `order_order_id` bigint NOT NULL,
  `taxes_tax_id` bigint NOT NULL,
  UNIQUE KEY `UK5t9i8wgjqrcroltmgsocnbmx0` (`taxes_tax_id`),
  KEY `FK28gtkphw8ny3cax4puw8k6gm6` (`order_order_id`),
  CONSTRAINT `FK28gtkphw8ny3cax4puw8k6gm6` FOREIGN KEY (`order_order_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `FKborxig9etulw1ntsdnbjt06f` FOREIGN KEY (`taxes_tax_id`) REFERENCES `tax` (`tax_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `orders_taxes` */

/*Table structure for table `payment` */

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `created_time` datetime(6) DEFAULT NULL,
  `currency` varchar(255) DEFAULT NULL,
  `payment_date` datetime(6) DEFAULT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  `payment_status` varchar(255) DEFAULT NULL,
  `transaction_id` varchar(255) DEFAULT NULL,
  `updated_time` datetime(6) DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKmf7n8wo2rwrxsd6f3t9ub2mep` (`order_id`),
  KEY `FKby2skjf3ov608yb6nm16b49lg` (`customer_id`),
  CONSTRAINT `FKby2skjf3ov608yb6nm16b49lg` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  CONSTRAINT `FKlouu98csyullos9k25tbpk4va` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `payment` */

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `product_id` bigint NOT NULL AUTO_INCREMENT,
  `barcode` varchar(255) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `cost_price` double DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity_in_stock` int DEFAULT NULL,
  `reorder_level` int DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `unit_price` double DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  `supplier_id` bigint DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`),
  KEY `FK2kxvbr72tmtscjvyp9yqb12by` (`supplier_id`),
  CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`),
  CONSTRAINT `FK2kxvbr72tmtscjvyp9yqb12by` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `product` */

/*Table structure for table `purchase_order` */

DROP TABLE IF EXISTS `purchase_order`;

CREATE TABLE `purchase_order` (
  `purchase_order_id` bigint NOT NULL AUTO_INCREMENT,
  `created_time` datetime(6) DEFAULT NULL,
  `delivery_date` datetime(6) DEFAULT NULL,
  `order_date` datetime(6) DEFAULT NULL,
  `payment_terms` varchar(255) DEFAULT NULL,
  `status` enum('CANCELLED','COMPLETED','PENDING') DEFAULT NULL,
  `total_amount` double DEFAULT NULL,
  `updated_time` datetime(6) DEFAULT NULL,
  `created_by` bigint DEFAULT NULL,
  `supplier_id` bigint DEFAULT NULL,
  PRIMARY KEY (`purchase_order_id`),
  KEY `FKeq9iymn90xw6q862mimr9iiq3` (`created_by`),
  KEY `FK4traogu3jriq9u7e8rvm86k7i` (`supplier_id`),
  CONSTRAINT `FK4traogu3jriq9u7e8rvm86k7i` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplier_id`),
  CONSTRAINT `FKeq9iymn90xw6q862mimr9iiq3` FOREIGN KEY (`created_by`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `purchase_order` */

/*Table structure for table `purchase_order_item` */

DROP TABLE IF EXISTS `purchase_order_item`;

CREATE TABLE `purchase_order_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cost_per_unit` double DEFAULT NULL,
  `created_time` datetime(6) DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `tax_rate` double DEFAULT NULL,
  `total_cost` double DEFAULT NULL,
  `total_cost_after_discount` double DEFAULT NULL,
  `total_cost_with_tax` double DEFAULT NULL,
  `updated_time` datetime(6) DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `purchase_order_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK593lt017d995ds7nuqxgo3mmm` (`product_id`),
  KEY `FKmj122necubadvuquvjoq967y7` (`purchase_order_id`),
  CONSTRAINT `FK593lt017d995ds7nuqxgo3mmm` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `FKmj122necubadvuquvjoq967y7` FOREIGN KEY (`purchase_order_id`) REFERENCES `purchase_order` (`purchase_order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `purchase_order_item` */

/*Table structure for table `report` */

DROP TABLE IF EXISTS `report`;

CREATE TABLE `report` (
  `report_id` bigint NOT NULL AUTO_INCREMENT,
  `created_time` datetime(6) DEFAULT NULL,
  `data` longtext,
  `end_date` datetime(6) DEFAULT NULL,
  `generated_at` datetime(6) DEFAULT NULL,
  `report_type` varchar(255) DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `generated_by` bigint DEFAULT NULL,
  PRIMARY KEY (`report_id`),
  KEY `FKide3gruwmi3na8jjsgfs04din` (`created_by`),
  KEY `FKjq27mb61isxnvwnwir3vw2quc` (`generated_by`),
  CONSTRAINT `FKide3gruwmi3na8jjsgfs04din` FOREIGN KEY (`created_by`) REFERENCES `users` (`user_name`),
  CONSTRAINT `FKjq27mb61isxnvwnwir3vw2quc` FOREIGN KEY (`generated_by`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `report` */

/*Table structure for table `returns` */

DROP TABLE IF EXISTS `returns`;

CREATE TABLE `returns` (
  `return_id` bigint NOT NULL AUTO_INCREMENT,
  `amount_refunded` double DEFAULT NULL,
  `created_time` datetime(6) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `reason_for_return` varchar(255) DEFAULT NULL,
  `refund_method` varchar(255) DEFAULT NULL,
  `return_date` datetime(6) DEFAULT NULL,
  `return_status` varchar(255) DEFAULT NULL,
  `return_type` varchar(255) DEFAULT NULL,
  `updated_time` datetime(6) DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  `purchase_order_id` bigint DEFAULT NULL,
  PRIMARY KEY (`return_id`),
  KEY `FKtge2tys80xohjn8v3wtiy21yi` (`order_id`),
  KEY `FK3reg6y3ry014j1pdaadbkuhpm` (`purchase_order_id`),
  CONSTRAINT `FK3reg6y3ry014j1pdaadbkuhpm` FOREIGN KEY (`purchase_order_id`) REFERENCES `purchase_order` (`purchase_order_id`),
  CONSTRAINT `FKtge2tys80xohjn8v3wtiy21yi` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `returns` */

/*Table structure for table `roles` */

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `role_name` varchar(255) NOT NULL,
  `date_created` datetime(6) NOT NULL,
  `last_updated` datetime(6) NOT NULL,
  `role_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `roles` */

insert  into `roles`(`role_name`,`date_created`,`last_updated`,`role_description`) values 
('ADMIN','2025-02-05 10:29:49.558760','2025-02-05 12:54:11.822645','Admin role'),
('MANAGER','2025-02-05 10:29:49.565746','2025-02-05 12:54:11.909414','Default role for newly ROLE_MODERATOR record'),
('STAFF','2025-02-05 10:29:49.563748','2025-02-05 12:54:11.906421','Default role for newly created record');

/*Table structure for table `roles_users` */

DROP TABLE IF EXISTS `roles_users`;

CREATE TABLE `roles_users` (
  `role_role_name` varchar(255) NOT NULL,
  `users_user_name` varchar(255) NOT NULL,
  PRIMARY KEY (`role_role_name`,`users_user_name`),
  KEY `FKbsfig7uji6a1ex98yg2mcqohn` (`users_user_name`),
  CONSTRAINT `FKbsfig7uji6a1ex98yg2mcqohn` FOREIGN KEY (`users_user_name`) REFERENCES `users` (`user_name`),
  CONSTRAINT `FKjyk8ajtgwkv2kjbr35cnb72m1` FOREIGN KEY (`role_role_name`) REFERENCES `roles` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `roles_users` */

/*Table structure for table `shipment` */

DROP TABLE IF EXISTS `shipment`;

CREATE TABLE `shipment` (
  `shipment_id` bigint NOT NULL AUTO_INCREMENT,
  `carrier_name` varchar(255) DEFAULT NULL,
  `created_time` datetime(6) DEFAULT NULL,
  `delivery_address` varchar(255) DEFAULT NULL,
  `delivery_date` datetime(6) DEFAULT NULL,
  `shipment_status` varchar(255) DEFAULT NULL,
  `shipping_cost` double DEFAULT NULL,
  `shipping_date` datetime(6) DEFAULT NULL,
  `tracking_number` varchar(255) DEFAULT NULL,
  `updated_time` datetime(6) DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  PRIMARY KEY (`shipment_id`),
  UNIQUE KEY `UKp06cong2injx54ipykoegys3w` (`order_id`),
  CONSTRAINT `FK8amw90d62x67honrwucvjado7` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `shipment` */

/*Table structure for table `supplier` */

DROP TABLE IF EXISTS `supplier`;

CREATE TABLE `supplier` (
  `supplier_id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `created_time` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `payment_terms` varchar(255) DEFAULT NULL,
  `contact_phone` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `supplier_rating` double DEFAULT NULL,
  `updated_time` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `supplier` */

/*Table structure for table `tax` */

DROP TABLE IF EXISTS `tax`;

CREATE TABLE `tax` (
  `tax_id` bigint NOT NULL AUTO_INCREMENT,
  `created_time` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `effective_from` datetime(6) DEFAULT NULL,
  `effective_till` datetime(6) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `rate` double DEFAULT NULL,
  `tax_category` varchar(255) DEFAULT NULL,
  `tax_type` varchar(255) DEFAULT NULL,
  `updated_time` datetime(6) DEFAULT NULL,
  `orders_order_id` bigint DEFAULT NULL,
  PRIMARY KEY (`tax_id`),
  KEY `FKkjrpktx63if6g983dds24lpr7` (`orders_order_id`),
  CONSTRAINT `FKkjrpktx63if6g983dds24lpr7` FOREIGN KEY (`orders_order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `tax` */

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `user_name` varchar(255) NOT NULL,
  `account_non_expired` bit(1) DEFAULT NULL,
  `account_non_locked` bit(1) DEFAULT NULL,
  `credentials_non_expired` bit(1) DEFAULT NULL,
  `date_created` datetime(6) NOT NULL,
  `email` varchar(255) NOT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `last_updated` datetime(6) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_first_name` varchar(255) DEFAULT NULL,
  `user_last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_name`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `users` */

insert  into `users`(`user_name`,`account_non_expired`,`account_non_locked`,`credentials_non_expired`,`date_created`,`email`,`enabled`,`last_updated`,`password`,`user_first_name`,`user_last_name`) values 
('admin123',NULL,NULL,NULL,'2025-02-05 10:29:49.631563','admin@gmail.com',NULL,'2025-02-05 12:54:12.006016','$2a$10$vLxaelgrRwRPXKi06G0YyeRaWADEy9.gNuK8KhxO8eCRSAQ.t5TGS','admin','admin');

/*Table structure for table `users_roles` */

DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE `users_roles` (
  `user_user_name` varchar(255) NOT NULL,
  `roles_role_name` varchar(255) NOT NULL,
  PRIMARY KEY (`user_user_name`,`roles_role_name`),
  KEY `FK4u9huytin67ksr541vpeb5415` (`roles_role_name`),
  CONSTRAINT `FK4u9huytin67ksr541vpeb5415` FOREIGN KEY (`roles_role_name`) REFERENCES `roles` (`role_name`),
  CONSTRAINT `FKaey8qby3yhrbg7uw2h51t4om0` FOREIGN KEY (`user_user_name`) REFERENCES `users` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `users_roles` */

/*Table structure for table `warehouse` */

DROP TABLE IF EXISTS `warehouse`;

CREATE TABLE `warehouse` (
  `warehouse_id` bigint NOT NULL AUTO_INCREMENT,
  `capacity` int DEFAULT NULL,
  `contact_info` varchar(255) DEFAULT NULL,
  `created_time` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_time` datetime(6) DEFAULT NULL,
  `warehouse_address` varchar(255) DEFAULT NULL,
  `warehouse_name` varchar(255) DEFAULT NULL,
  `manager_id` bigint DEFAULT NULL,
  PRIMARY KEY (`warehouse_id`),
  KEY `FKi8xvsxeox9a41ulkadd7tadq2` (`manager_id`),
  CONSTRAINT `FKi8xvsxeox9a41ulkadd7tadq2` FOREIGN KEY (`manager_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `warehouse` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
