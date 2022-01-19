-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 14.01.2022 klo 09:29
-- Palvelimen versio: 10.4.21-MariaDB
-- PHP Version: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tietokone_paketit`
--

-- --------------------------------------------------------

--
-- Rakenne taululle `emolevyt`
--

CREATE TABLE `emolevyt` (
  `id` int(11) NOT NULL,
  `valmistaja` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `malli` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `kanta` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `hinta` int(11) NOT NULL,
  `varastossa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Vedos taulusta `emolevyt`
--

INSERT INTO `emolevyt` (`id`, `valmistaja`, `malli`, `kanta`, `hinta`, `varastossa`) VALUES
(1, 'Gigabyte', 'B450M DS3H', 'AM4', 75, 6),
(2, 'Asus', 'PRIME B560-PLUS', 'LGA1200', 140, 7),
(3, 'MSI', 'B365M PRO-VH', 'LGA1151', 85, 3),
(4, 'Asus', 'ROG STRIX B550-I GAMING', 'AM4', 220, 20),
(5, 'Asus', 'TUF GAMING B550M-PLUS', 'AM4', 170, 4),
(6, 'Asus', 'TUF GAMING B560-PLUS WIFI', 'LGA1200', 180, 9),
(7, 'MSI', 'MPG Z590 GAMING CARBON WIFI', 'LGA1200', 260, 12);

-- --------------------------------------------------------

--
-- Rakenne taululle `kotelot`
--

CREATE TABLE `kotelot` (
  `id` int(11) NOT NULL,
  `valmistaja` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `malli` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `hinta` int(11) NOT NULL,
  `varastossa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Vedos taulusta `kotelot`
--

INSERT INTO `kotelot` (`id`, `valmistaja`, `malli`, `hinta`, `varastossa`) VALUES
(1, 'DeepCool', 'MATREXX 40 3FS', 50, 7),
(2, 'Fractal Design', 'Define C', 92, 23),
(3, 'Fractal Design', 'Meshify 2 Compact - Black Solid', 120, 29),
(4, 'Fractal Design', 'Focus G', 62, 22),
(5, 'NZXT', 'H510', 83, 12),
(6, 'Corsair', '4000D Airflow', 100, 32),
(7, 'Cooler Master', 'MasterBox MB320L ARGB', 70, 8);

-- --------------------------------------------------------

--
-- Rakenne taululle `levyt`
--

CREATE TABLE `levyt` (
  `id` int(11) NOT NULL,
  `valmistaja` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `malli` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `tyyppi` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `koko` int(11) NOT NULL,
  `hinta` int(11) NOT NULL,
  `varastossa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Vedos taulusta `levyt`
--

INSERT INTO `levyt` (`id`, `valmistaja`, `malli`, `tyyppi`, `koko`, `hinta`, `varastossa`) VALUES
(1, 'Seagate', 'BarraCuda', 'HDD', 2000, 59, 27),
(2, 'Samsung', '870 EVO', 'SSD', 1000, 127, 30),
(3, 'Samsung', '870 QVO', 'SSD', 2000, 190, 24),
(4, 'Western Digital', 'WD Blue 3D', 'SSD', 1000, 120, 9),
(5, 'Western Digital', 'WD Blue', 'HDD', 4000, 95, 11),
(6, 'Crucial', 'MX500', 'SSD', 1000, 120, 7);

-- --------------------------------------------------------

--
-- Rakenne taululle `muisti`
--

CREATE TABLE `muisti` (
  `id` int(11) NOT NULL,
  `valmistaja` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `malli` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `koko` int(11) NOT NULL,
  `maara` int(11) NOT NULL,
  `hinta` int(11) NOT NULL,
  `varastossa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Vedos taulusta `muisti`
--

INSERT INTO `muisti` (`id`, `valmistaja`, `malli`, `koko`, `maara`, `hinta`, `varastossa`) VALUES
(1, 'Kingston', 'FURY Beast, DDR4', 8, 2, 80, 32),
(2, 'Corsair', 'Vengeance LPX, DDR4', 16, 2, 160, 26),
(3, 'Kingston', 'FURY Renegade RGB, DDR4', 16, 2, 190, 24),
(4, 'Corsair', 'Value Select, DDR4', 8, 1, 37, 38),
(5, 'G.Skill', 'Trident Z Neo, DDR4', 16, 2, 220, 14),
(6, 'Corsair', 'Vengeance, DDR3', 8, 2, 93, 15);

-- --------------------------------------------------------

--
-- Rakenne taululle `naytonohjaimet`
--

CREATE TABLE `naytonohjaimet` (
  `id` int(11) NOT NULL,
  `valmistaja` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `malli` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `hinta` int(11) NOT NULL,
  `varastossa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Vedos taulusta `naytonohjaimet`
--

INSERT INTO `naytonohjaimet` (`id`, `valmistaja`, `malli`, `hinta`, `varastossa`) VALUES
(1, 'Asus', 'RTX 3060 DUAL - OC Edition V2', 700, 11),
(2, 'Asus', 'RTX 3070 Ti TUF GAMING - OC Edition', 1270, 3),
(3, 'MSI', 'RTX 3070  GAMING Z TRIO', 1170, 18),
(4, 'Asus', 'GTX 1650 Dual OC', 290, 14),
(5, 'Asus', 'Radeon RX 6600 XT Dual', 700, 9);

-- --------------------------------------------------------

--
-- Rakenne taululle `paketit`
--

CREATE TABLE `paketit` (
  `id` int(11) NOT NULL,
  `mb_id` int(11) NOT NULL,
  `cpu_id` int(11) NOT NULL,
  `pwr_id` int(11) NOT NULL,
  `ram_id` int(11) NOT NULL,
  `gpu_id` int(11) NOT NULL,
  `strg_id` int(11) NOT NULL,
  `case_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Vedos taulusta `paketit`
--

INSERT INTO `paketit` (`id`, `mb_id`, `cpu_id`, `pwr_id`, `ram_id`, `gpu_id`, `strg_id`, `case_id`) VALUES
(1, 1, 1, 1, 1, 2, 3, 1),
(2, 5, 6, 3, 2, 2, 3, 5);

-- --------------------------------------------------------

--
-- Rakenne taululle `powerit`
--

CREATE TABLE `powerit` (
  `id` int(11) NOT NULL,
  `valmistaja` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `malli` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `teho` int(11) NOT NULL,
  `hinta` int(11) NOT NULL,
  `varastossa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Vedos taulusta `powerit`
--

INSERT INTO `powerit` (`id`, `valmistaja`, `malli`, `teho`, `hinta`, `varastossa`) VALUES
(1, 'Corsair', 'RM750x', 750, 125, 34),
(2, 'Seasonic', 'FOCUS GX-750', 750, 110, 27),
(3, 'Corsair', 'RM850x', 850, 140, 12),
(4, 'Seasonic', 'FOCUS GX-650', 650, 99, 26);

-- --------------------------------------------------------

--
-- Rakenne taululle `prosessorit`
--

CREATE TABLE `prosessorit` (
  `id` int(11) NOT NULL,
  `valmistaja` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `malli` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `kanta` varchar(255) COLLATE utf8_swedish_ci NOT NULL,
  `hinta` int(11) NOT NULL,
  `varastossa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Vedos taulusta `prosessorit`
--

INSERT INTO `prosessorit` (`id`, `valmistaja`, `malli`, `kanta`, `hinta`, `varastossa`) VALUES
(1, 'AMD', 'Ryzen 7 3700X', 'AM4', 350, 10),
(2, 'AMD', 'Ryzen 9 5600X', 'AM4', 330, 20),
(3, 'Intel', 'i5-11600K', 'LGA1200', 270, 16),
(4, 'Intel', 'i7-11700K', 'LGA1200', 390, 18),
(5, 'AMD', 'Ryzen 7 3800X', 'AM4', 325, 0),
(6, 'AMD', 'Ryzen 9 5950X', 'AM4', 820, 8);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `emolevyt`
--
ALTER TABLE `emolevyt`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `kotelot`
--
ALTER TABLE `kotelot`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `levyt`
--
ALTER TABLE `levyt`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `muisti`
--
ALTER TABLE `muisti`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `naytonohjaimet`
--
ALTER TABLE `naytonohjaimet`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `paketit`
--
ALTER TABLE `paketit`
  ADD PRIMARY KEY (`id`),
  ADD KEY `mb_id` (`mb_id`,`cpu_id`,`pwr_id`,`ram_id`,`gpu_id`,`strg_id`,`case_id`),
  ADD KEY `case_id` (`case_id`),
  ADD KEY `gpu_id` (`gpu_id`),
  ADD KEY `cpu_id` (`cpu_id`),
  ADD KEY `pwr_id` (`pwr_id`),
  ADD KEY `ram_id` (`ram_id`),
  ADD KEY `strg_id` (`strg_id`);

--
-- Indexes for table `powerit`
--
ALTER TABLE `powerit`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `prosessorit`
--
ALTER TABLE `prosessorit`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `emolevyt`
--
ALTER TABLE `emolevyt`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `kotelot`
--
ALTER TABLE `kotelot`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `levyt`
--
ALTER TABLE `levyt`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `muisti`
--
ALTER TABLE `muisti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `naytonohjaimet`
--
ALTER TABLE `naytonohjaimet`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `paketit`
--
ALTER TABLE `paketit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `powerit`
--
ALTER TABLE `powerit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `prosessorit`
--
ALTER TABLE `prosessorit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Rajoitteet vedostauluille
--

--
-- Rajoitteet taululle `paketit`
--
ALTER TABLE `paketit`
  ADD CONSTRAINT `paketit_ibfk_1` FOREIGN KEY (`mb_id`) REFERENCES `emolevyt` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `paketit_ibfk_2` FOREIGN KEY (`case_id`) REFERENCES `kotelot` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `paketit_ibfk_3` FOREIGN KEY (`gpu_id`) REFERENCES `naytonohjaimet` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `paketit_ibfk_4` FOREIGN KEY (`cpu_id`) REFERENCES `prosessorit` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `paketit_ibfk_5` FOREIGN KEY (`pwr_id`) REFERENCES `powerit` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `paketit_ibfk_6` FOREIGN KEY (`ram_id`) REFERENCES `muisti` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `paketit_ibfk_7` FOREIGN KEY (`strg_id`) REFERENCES `levyt` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
