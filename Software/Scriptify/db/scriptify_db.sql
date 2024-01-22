-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 21, 2024 at 06:39 PM
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
  `Description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `knjige`
--

INSERT INTO `knjige` (`idKnjige`, `naziv_knjige`, `autor`, `Description`) VALUES
(323232, 'Uvod u programiranje', 'Alen Lovrenčić', 'Uvod u c++ programiranje'),
(9138960, 'Uvod u programiranje', 'Mario Konecki i Alen Lovrencic', 'Uvod u programiranje'),
(15348240, 'Zlocin i kazna', 'Fjodor Miljic Dostojevski', 'Man who killed grandma, and was emotional'),
(16907856, 'Ana Karenjina', 'Fjodor Dostojevski', 'Amazing Book, about Ana.'),
(25950724, 'testBook', 'autor', 'decs'),
(38996773, 'seg', 'esr', 'svd'),
(85338843, 'Knjiga od korisnika TEST', 'Testiramo za kupovinu', 'Testiramo kupovinu knjiga.'),
(91886543, 'test', 'test', 'test'),
(112270560, 'test', 'te', 'test'),
(123102973, 'test', 'test', 'test'),
(125982519, 'test', 'test', 'test'),
(135544289, 'asd', 'ad', 'asd'),
(152047813, 'Uvod u algoritme', 'Alen Lovrenic', 'Uvod u sve vrste algoritama'),
(173538435, 'test', 'test', 'teste'),
(189191730, 'To Kill a Mocking Bir', 'Harper Lee', 'A classic novel that explores the issues of racial injustice and segregation.'),
(199721778, 'buba saba', 'buba saba', 'mala buba'),
(211897043, 'test', 'te', 'test'),
(227475030, 'how to love', 'Stura marino', 'volim luku'),
(228499805, 'test', 'test', 'test'),
(240508263, 'dsa', 'dsa', 'dsa'),
(247895104, 'Moja igra', 'Luka Modric', 'Moja igra is written by the talented Croatian fo..'),
(256534695, 'tt', 'tt', 'tt'),
(284298740, 'dvsvsd', 'fewwef', 'gwegwed'),
(300380050, 'Moj program', 'Marino Štura', 'Knjiga govori o prvom programu'),
(384475309, 'e', 'e', 'e'),
(387626094, 'gesg', 'test', 'sgeg'),
(397472551, 'test', 'test', 'test'),
(406617487, 'test', 'test', 'test'),
(414527267, 'test2', 'test2', 'test2'),
(420784528, 'hre', 'sdfs', 'sdf'),
(423328433, 'test', 'test', 'test'),
(431630138, 't', 't', 't'),
(468530946, 'test', 'test', 'test'),
(484525344, 'Napredno programiranje 3', 'Alen Lovrencic', 'Napredni koncepti programiranja'),
(486662880, 'asd', 'asd', 'asdasfasfsafsafasfasfsafgdahfdhrgjztzfsdgsdfsdhfgsdfasfsdgdsgffsafsdgdfsgsddsagddasdgsdfdshdfsdbsdsrbrdhfadfdsggea'),
(510190237, 'test', 'testreq.params.id', 'test'),
(513649218, 'test', 'test', 'test');

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

-- --------------------------------------------------------;


INSERT INTO `knjizara_has_knjige` (`knjizara_has_knjige_id`, `Knjizara_idKnjizara`, `Knjige_idKnjige`) VALUES
(1, 1, 1690785),
(2,1, 85338843),
(,2, 152047813),
--
-- Table structure for table `purchased_books`
--

CREATE TABLE `purchased_books` (
  `idKnjige` int(11) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  `naziv_knjige` varchar(255) DEFAULT NULL,
  `autor` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `purchased_books`
--

INSERT INTO `purchased_books` (`idKnjige`, `id_user`, `naziv_knjige`, `autor`, `Description`) VALUES
(6, 1, 'Tired of Winning', 'Guide on how to win', 'Donald Trump'),
(15348240, 7, 'Zlocin i kazna', 'Man who killed grandma, and was emotional', 'Fjodor Miljic Dostojevski'),
(16907856, 7, 'Ana Karenjina', 'Amazing Book, about Ana.', 'Fjodor Dostojevski'),
(85338843, 7, 'Knjiga od korisnika TEST', 'Testiramo kupovinu knjiga.', 'Testiramo za kupovinu'),
(152047813, 1, 'Uvod u algoritme', 'Uvod u sve vrste algoritama', 'Alen Lovrenic'),
(227475030, 7, 'how to love', 'volim luku', 'Stura marino'),
(240508263, 1, 'dsa', 'dsa', 'dsa'),
(247895104, 7, 'Moja igra', 'Moja igra is written by the talented Croatian fo..', 'Luka Modric'),
(484525344, 1, 'Napredno programiranje 3', 'Napredni koncepti programiranja', 'Alen Lovrencic'),
(486662880, 1, 'asd', 'asdasfasfsafsafasfasfsafgdahfdhrgjztzfsdgsdfsdhfgsdfasfsdgdsgffsafsdgdfsgsddsagddasdgsdfdshdfsdbsdsrbrdhfadfdsggea', 'asd');

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
(1, 'john_doe', 'john.doe@example.com', '1234', '2023-11-05 15:00:42', '123 Main St, City, Country', 'John ', 'Doe', 499, 'I like reading a lot'),
(2, 'jane_smith', 'jane.smith@example.com', 'password456', '2023-11-05 15:00:42', '456 Elm St, Town, Country', 'Jane ', 'Smith', 135, 'Reading is my favourite hobby'),
(3, 'joey_balboa', 'yoe_te@gmail.com', '12345', '2023-11-17 19:47:07', '765 Royal Lane, USA', 'Joe', 'Balboa', 0, 'I like books and the gym'),
(4, 'test', 'test', 'test', '2023-11-18 14:38:35', 'test', 'test', 'test', 75, NULL),
(5, 'marino', 'mojmail@gmail.com', '1234', '2023-11-18 14:44:39', 'Sv. Bana jelacica 5', 'Marino', 'Štura', 50, NULL),
(7, 'test', 'test', 'test', '2023-11-18 14:47:27', 'test', 'test', 'test', 300, NULL),
(8, 'test', 'test', 'ttest', '2023-11-18 15:21:34', 'test', 'test', 'test', NULL, NULL),
(9, 'tst', 'test', 'test', '2023-11-18 15:23:33', 'test', 'test', 'test', NULL, NULL),
(10, 'tst', 'test', 'test', '2023-11-18 15:25:24', 'test', 'test', 'test', NULL, NULL),
(11, 'mrvu', 'krse.volim@gmail.con', 'krse123', '2023-11-18 15:47:54', 'foi 4', 'Luka', 'Krsevan nizic', NULL, NULL),
(12, 'mrvu', 'krse.volim@gmail.com', 'krse123', '2023-11-18 15:47:58', 'foi 4', 'Luka', 'Krsevan nizic', NULL, NULL),
(13, 'mrvu', 'krse.volim@gmail.com', 'krse123', '2023-11-18 15:48:02', 'foi 4', 'Luka', 'Krsevan ', NULL, NULL),
(14, 'mrvu', 'krse.volim@gmail.com', 'krse123', '2023-11-18 15:48:07', 'foi 4', 'Luka', 'Krsevan nizic', NULL, NULL),
(15, 'mrvi', 'krse.volim@gmail.com', 'krse123', '2023-11-18 15:48:13', 'foi 4', 'Luka', 'Krsevan nizic', NULL, NULL),
(16, 'mrvi', 'krse.volim@gmail.com', 'krse123', '2023-11-18 15:48:20', 'foi 4', 'Luka', 'Krsevan nizic', NULL, NULL),
(17, 'mrvi', 'krse.volim@gmail.com', 'krse123', '2023-11-18 15:48:21', 'foi 4', 'Luka', 'Krsevan nizic', NULL, NULL),
(18, 't', 't', 't', '2023-11-18 15:49:21', 't', 't', 't', NULL, NULL),
(19, 'krse', 'volim.krsu@gmail.com', 'krse123', '2023-11-18 15:50:03', 'foi 4', 'Luka', 'Krsevan Nizic', 200, NULL),
(20, 'bubasba', 'mgveric7@gmail.com', 'hah', '2023-11-18 18:04:56', 'bubiceva kuca', 'buha', 'saba', 100, NULL);

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
(1, 16907856),
(1, 85338843),
(1, 152047813),
(1, 227475030),
(1, 240508263),
(1, 247895104),
(1, 484525344),
(1, 486662880),
(7, 15348240);




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
  ADD PRIMARY KEY (`idKnjige`);

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
-- AUTO_INCREMENT for table `reviews`
--
ALTER TABLE `reviews`
  MODIFY `idReview` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

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
