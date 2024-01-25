-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 25, 2024 at 11:05 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `scriptify`
--

-- --------------------------------------------------------

--
-- Table structure for table `knjige`
--

CREATE TABLE `knjige` (
  `idKnjige` int(11) NOT NULL,
  `naziv_knjige` varchar(45) DEFAULT NULL,
  `autor` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `cijena_knjige` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `knjige`
--

INSERT INTO `knjige` (`idKnjige`, `naziv_knjige`, `autor`, `Description`, `cijena_knjige`) VALUES
(77280409, 'Strukture podataka i algoritmi', 'Alen Lovrencic', 'Knjiga iz predmeta \"Strukture podataka i algoritmi\".', 0),
(174750374, 'Matematicke metode', 'Blazenka Divjak', 'Knjiga iz MMZI.', 0),
(362938018, 'Matematika 2', 'Blazenka Divjak', 'Knjiga iz matematike 2.', 0),
(371032171, 'Matematika 1', 'Blazenka Divjak', 'Knjiga iz matematike 1.', 0);

-- --------------------------------------------------------

--
-- Table structure for table `knjizara`
--

CREATE TABLE `knjizara` (
  `idKnjizara` int(11) NOT NULL,
  `ime` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `knjizara`
--

INSERT INTO `knjizara` (`idKnjizara`, `ime`) VALUES
(1, 'Zagrebacka knjiznica'),
(2, 'Varazdinska knjiznica');

-- --------------------------------------------------------

--
-- Table structure for table `knjizara_has_knjige`
--

CREATE TABLE `knjizara_has_knjige` (
  `knjizara_has_knjige_id` int(11) NOT NULL,
  `Knjizara_idKnjizara` int(11) NOT NULL,
  `Knjige_idKnjige` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `purchased_books`
--

CREATE TABLE `purchased_books` (
  `id_purchased_book` int(11) NOT NULL,
  `idKnjige` int(11) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  `naziv_knjige` varchar(255) DEFAULT NULL,
  `autor` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `cijena_knjige` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `purchased_books`
--

INSERT INTO `purchased_books` (`id_purchased_book`, `idKnjige`, `id_user`, `naziv_knjige`, `autor`, `Description`, `cijena_knjige`) VALUES
(26, 371032171, 5, 'Matematika 1', 'Knjiga iz matematike 1.', 'Blazenka Divjak', 0);

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE `reviews` (
  `idReview` int(11) NOT NULL,
  `idKnjige` int(11) DEFAULT NULL,
  `review_text` varchar(255) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `username` varchar(16) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(32) NOT NULL,
  `create_time` timestamp NULL DEFAULT current_timestamp(),
  `address` varchar(255) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `money` decimal(10,0) DEFAULT NULL,
  `status` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `username`, `email`, `password`, `create_time`, `address`, `first_name`, `last_name`, `money`, `status`) VALUES
(1, 'john_doe', 'john.doe@example.com', '1234', '2023-11-05 15:00:42', '123 Main St, City, Country', 'John ', 'Doe', 3, 'I like reading a lot'),
(2, 'jane_smith', 'jane.smith@example.com', 'password456', '2023-11-05 15:00:42', '456 Elm St, Town, Country', 'Jane ', 'Smith', 135, 'Reading is my favourite hobby'),
(5, 'marino', 'mojmail@gmail.com', '1234', '2023-11-18 14:44:39', 'Sv. Bana jelacica 5', 'Marino', 'Štura', 21, NULL),
(21, 'dskudar20', 'dskudar20@foi.hr', '1234', '2024-01-25 21:39:31', 'Adresa', 'David', 'Skudar', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user_has_knjige`
--

CREATE TABLE `user_has_knjige` (
  `user_id_user` int(11) NOT NULL,
  `Knjige_idKnjige` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_has_knjige`
--

INSERT INTO `user_has_knjige` (`user_id_user`, `Knjige_idKnjige`) VALUES
(5, 362938018),
(5, 371032171),
(21, 174750374),
(21, 371032171);

-- --------------------------------------------------------

--
-- Table structure for table `user_has_knjizara_has_knjige`
--

CREATE TABLE `user_has_knjizara_has_knjige` (
  `user_id_user` int(11) NOT NULL,
  `Knjizara_has_Knjige_knjizara_has_knjige_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `knjige`
--
ALTER TABLE `knjige`
  ADD PRIMARY KEY (`idKnjige`);

--
-- Indexes for table `knjizara`
--
ALTER TABLE `knjizara`
  ADD PRIMARY KEY (`idKnjizara`),
  ADD UNIQUE KEY `idKnjizara_UNIQUE` (`idKnjizara`);

--
-- Indexes for table `knjizara_has_knjige`
--
ALTER TABLE `knjizara_has_knjige`
  ADD PRIMARY KEY (`knjizara_has_knjige_id`,`Knjizara_idKnjizara`,`Knjige_idKnjige`),
  ADD KEY `fk_Knjizara_has_Knjige_Knjige1_idx` (`Knjige_idKnjige`,`knjizara_has_knjige_id`),
  ADD KEY `fk_Knjizara_has_Knjige_Knjizara1_idx` (`Knjizara_idKnjizara`);

--
-- Indexes for table `purchased_books`
--
ALTER TABLE `purchased_books`
  ADD PRIMARY KEY (`id_purchased_book`);

--
-- Indexes for table `reviews`
--
ALTER TABLE `reviews`
  ADD PRIMARY KEY (`idReview`),
  ADD KEY `idKnjige` (`idKnjige`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `id_user_UNIQUE` (`id_user`);

--
-- Indexes for table `user_has_knjige`
--
ALTER TABLE `user_has_knjige`
  ADD PRIMARY KEY (`user_id_user`,`Knjige_idKnjige`),
  ADD KEY `fk_user_has_Knjige_Knjige1_idx` (`Knjige_idKnjige`),
  ADD KEY `fk_user_has_Knjige_user1_idx` (`user_id_user`);

--
-- Indexes for table `user_has_knjizara_has_knjige`
--
ALTER TABLE `user_has_knjizara_has_knjige`
  ADD PRIMARY KEY (`user_id_user`,`Knjizara_has_Knjige_knjizara_has_knjige_id`),
  ADD KEY `user_has_Knjizara_has_Knjige_fk_idx` (`Knjizara_has_Knjige_knjizara_has_knjige_id`),
  ADD KEY `user_has_Knjizara_has_Knjige_user_idx` (`user_id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `knjige`
--
ALTER TABLE `knjige`
  MODIFY `idKnjige` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=513649219;

--
-- AUTO_INCREMENT for table `knjizara`
--
ALTER TABLE `knjizara`
  MODIFY `idKnjizara` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `knjizara_has_knjige`
--
ALTER TABLE `knjizara_has_knjige`
  MODIFY `knjizara_has_knjige_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `purchased_books`
--
ALTER TABLE `purchased_books`
  MODIFY `id_purchased_book` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `reviews`
--
ALTER TABLE `reviews`
  MODIFY `idReview` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `knjizara_has_knjige`
--
ALTER TABLE `knjizara_has_knjige`
  ADD CONSTRAINT `knjizara_has_knjige_ibfk_1` FOREIGN KEY (`Knjizara_idKnjizara`) REFERENCES `knjizara` (`idKnjizara`),
  ADD CONSTRAINT `knjizara_has_knjige_ibfk_2` FOREIGN KEY (`Knjige_idKnjige`) REFERENCES `knjige` (`idKnjige`);

--
-- Constraints for table `reviews`
--
ALTER TABLE `reviews`
  ADD CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`idKnjige`) REFERENCES `knjige` (`idKnjige`);

--
-- Constraints for table `user_has_knjige`
--
ALTER TABLE `user_has_knjige`
  ADD CONSTRAINT `user_has_knjige_ibfk_1` FOREIGN KEY (`user_id_user`) REFERENCES `user` (`id_user`),
  ADD CONSTRAINT `user_has_knjige_ibfk_2` FOREIGN KEY (`Knjige_idKnjige`) REFERENCES `knjige` (`idKnjige`);

--
-- Constraints for table `user_has_knjizara_has_knjige`
--
ALTER TABLE `user_has_knjizara_has_knjige`
  ADD CONSTRAINT `fk_user_has_Knjizara_has_Knjige_Knjizara_has_Knjige1` FOREIGN KEY (`Knjizara_has_Knjige_knjizara_has_knjige_id`) REFERENCES `knjizara_has_knjige` (`knjizara_has_knjige_id`),
  ADD CONSTRAINT `fk_user_has_Knjizara_has_Knjige_user1` FOREIGN KEY (`user_id_user`) REFERENCES `user` (`id_user`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
