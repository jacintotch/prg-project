-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 11, 2024 at 01:06 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pos`
--

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `idno` int(11) NOT NULL,
  `user_fullname` varchar(50) DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `user_password` varchar(64) DEFAULT NULL,
  `role` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`idno`, `user_fullname`, `user_name`, `user_password`, `role`) VALUES
(1, 'jacinto', 'jacinto@gmail.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'Administrator'),
(2, 'Ndilipawa Alweendo', 'ndilipawaalweendo@gamail.com', 'e506d4bb1e7ee6fbf3ec6187d57f729c4860857426487289d92821f6627ae291', 'Administrator'),
(3, 'Nangula Shilongo', 'nshilongo@gmail.com', 'f9bb481ecf9ed515aada26e5877184f683ea6c4e8a70fea7b8cb609f22221263', 'Cashier'),
(4, 'Marina Tuyemo', 'mtuyemo@gmail.com', 'f9bb481ecf9ed515aada26e5877184f683ea6c4e8a70fea7b8cb609f22221263', 'Cashier'),
(5, 'Simeon', 'Simeon', 'f9bb481ecf9ed515aada26e5877184f683ea6c4e8a70fea7b8cb609f22221263', 'Manager'),
(6, 'Heather Katjivena', 'hakatjivena@gmail.com', 'f9bb481ecf9ed515aada26e5877184f683ea6c4e8a70fea7b8cb609f22221263', 'Director'),
(7, 'Jill Coetze', 'jcoetze@gmail.com', 'f9bb481ecf9ed515aada26e5877184f683ea6c4e8a70fea7b8cb609f22221263', 'Cashier'),
(8, 'Tye', 'van der Merwe', 'f9bb481ecf9ed515aada26e5877184f683ea6c4e8a70fea7b8cb609f22221263', 'Select Role'),
(9, 'Tangeni Shikongeni', 'tshikongeni@gmail.com', 'f9bb481ecf9ed515aada26e5877184f683ea6c4e8a70fea7b8cb609f22221263', 'Cashier'),
(10, 'Tye van der Merwe', 'tvdmerwe@gmail.com', 'f9bb481ecf9ed515aada26e5877184f683ea6c4e8a70fea7b8cb609f22221263', 'Cashier'),
(11, 'Bruno Hucui', 'brntecnology@gmail.com', 'e12c10ef65f591afddea2959e6417a4e1e3404e6245cd79c0a2f0a063ab4016a', 'Manager');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `barcode` char(4) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `stock_quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`barcode`, `name`, `description`, `price`, `stock_quantity`) VALUES
('0001', 'Beef Burger', 'Juicy beef patty with lettuce, tomato, and cheese', 75.00, 50),
('0002', 'Chicken Curry', 'Spicy chicken curry served with rice', 85.00, 30),
('0003', 'Vegetable Stir Fry', 'Mixed vegetables stir-fried in soy sauce', 60.00, 40),
('0004', 'Pasta Primavera', 'Pasta with fresh vegetables in a light sauce', 70.00, 25),
('0005', 'Grilled Fish', 'Freshly grilled fish served with lemon and herbs', 90.00, 20),
('0006', 'Caesar Salad', 'Crispy romaine lettuce with Caesar dressing', 50.00, 45),
('0007', 'Margherita Pizza', 'Classic pizza with tomatoes, mozzarella, and basil', 80.00, 35),
('0008', 'Chocolate Cake', 'Rich chocolate cake topped with chocolate icing', 40.00, 60),
('0009', 'Spaghetti Bolognese', 'Pasta with a rich meat sauce', 65.00, 55),
('0010', 'Fish Tacos', 'Soft tacos filled with grilled fish and fresh salsa', 70.00, 40),
('0011', 'Lamb Chops', 'Grilled lamb chops served with mint sauce', 120.00, 25),
('0012', 'Stuffed Peppers', 'Bell peppers stuffed with quinoa and vegetables', 55.00, 30),
('0013', 'Fried Calamari', 'Crispy fried calamari served with marinara sauce', 75.00, 20),
('0014', 'Chicken Alfredo', 'Pasta in a creamy Alfredo sauce with chicken', 80.00, 28),
('0015', 'Caprese Salad', 'Fresh mozzarella, tomatoes, and basil drizzled with balsamic', 45.00, 50),
('0016', 'Shrimp Scampi', 'Shrimp sautéed in garlic and butter served over pasta', 95.00, 18),
('0017', 'Beef Tacos', 'Hard-shell tacos filled with seasoned beef', 65.00, 40),
('0018', 'Vegetable Lasagna', 'Layers of pasta with vegetables and cheese', 70.00, 32),
('0019', 'BBQ Ribs', 'Tender ribs smothered in BBQ sauce', 110.00, 22),
('0020', 'Pancakes', 'Fluffy pancakes served with syrup and butter', 45.00, 50),
('0021', 'Omelette', 'Three-egg omelette with your choice of fillings', 40.00, 45),
('0022', 'Cheeseboard', 'Assorted cheeses served with crackers and fruits', 80.00, 15),
('0023', 'Chili Con Carne', 'Spicy beef and bean chili served with cornbread', 65.00, 20),
('0024', 'Chicken Fajitas', 'Sizzling chicken with peppers and onions', 90.00, 25),
('0025', 'Vegetable Soup', 'Hearty soup made with seasonal vegetables', 40.00, 35),
('0026', 'Grilled Cheese Sandwich', 'Toasted bread with melted cheese', 30.00, 50),
('0027', 'Pork Schnitzel', 'Breaded pork cutlet served with lemon', 85.00, 20),
('0028', 'Fruit Salad', 'Fresh seasonal fruits served in a bowl', 40.00, 45),
('0029', 'Tiramisu', 'Classic Italian coffee-flavored dessert', 50.00, 30),
('0030', 'Quiche Lorraine', 'Savory pie filled with cheese and bacon', 60.00, 25),
('0031', 'Mushroom Risotto', 'Creamy risotto with sautéed mushrooms', 75.00, 18),
('0032', 'Beef Stroganoff', 'Tender beef in a creamy mushroom sauce', 95.00, 15),
('0033', 'Eggplant Parmesan', 'Baked eggplant with marinara and cheese', 70.00, 20),
('0034', 'Sushi Platter', 'Assorted sushi rolls and sashimi', 150.00, 10),
('0035', 'Roasted Chicken', 'Herb-marinated roasted chicken served with vegetables', 85.00, 22),
('0036', 'Crab Cakes', 'Crab meat patties served with remoulade sauce', 90.00, 15),
('0037', 'Chicken Caesar Wrap', 'Grilled chicken with Caesar salad in a wrap', 55.00, 30),
('0038', 'Beetroot Salad', 'Fresh beetroot with goat cheese and walnuts', 50.00, 40),
('0039', 'Pork Belly', 'Slow-cooked pork belly with crispy skin', 110.00, 12),
('0040', 'Coconut Shrimp', 'Breaded shrimp fried in coconut flakes', 80.00, 20),
('0041', 'Vegetable Spring Rolls', 'Crispy rolls filled with mixed vegetables', 40.00, 45),
('0042', 'Banana Bread', 'Homemade banana bread served warm', 30.00, 60),
('0043', 'Baked Ziti', 'Pasta baked with marinara sauce and cheese', 70.00, 25),
('0044', 'Mediterranean Platter', 'Hummus, olives, and pita bread', 75.00, 18),
('0045', 'Pesto Pasta', 'Pasta tossed in a fresh basil pesto', 65.00, 30),
('0046', 'Chocolate Mousse', 'Rich chocolate mousse topped with whipped cream', 50.00, 40),
('0047', 'Beef Wellington', 'Tender beef wrapped in pastry and baked', 150.00, 10),
('0048', 'Greek Salad', 'Tomatoes, cucumbers, olives, and feta cheese', 45.00, 35),
('0049', 'Peach Cobbler', 'Warm peach dessert served with ice cream', 40.00, 20),
('0050', 'Gnocchi', 'Potato dumplings in a tomato basil sauce', 70.00, 28);

-- --------------------------------------------------------

--
-- Table structure for table `receipt`
--

CREATE TABLE `receipt` (
  `receipt_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `barcode` varchar(50) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `total_cost` decimal(10,2) DEFAULT NULL,
  `vat` decimal(10,2) DEFAULT NULL,
  `total_amount` decimal(10,2) DEFAULT NULL,
  `payment_method` varchar(50) DEFAULT NULL,
  `payment_status` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `receipt`
--

INSERT INTO `receipt` (`receipt_id`, `user_id`, `barcode`, `date`, `time`, `total_cost`, `vat`, `total_amount`, `payment_method`, `payment_status`) VALUES
(3, 1, NULL, '2024-10-11', '11:06:42', 85.00, 0.00, 170.00, 'your_payment_method', 'your_payment_status'),
(4, 1, NULL, '2024-10-11', '11:11:33', 85.00, 0.00, 2125.00, 'your_payment_method', 'your_payment_status');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`idno`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`barcode`);

--
-- Indexes for table `receipt`
--
ALTER TABLE `receipt`
  ADD PRIMARY KEY (`receipt_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `barcode` (`barcode`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `idno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `receipt`
--
ALTER TABLE `receipt`
  MODIFY `receipt_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `receipt`
--
ALTER TABLE `receipt`
  ADD CONSTRAINT `receipt_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `login` (`idno`),
  ADD CONSTRAINT `receipt_ibfk_2` FOREIGN KEY (`barcode`) REFERENCES `products` (`barcode`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
