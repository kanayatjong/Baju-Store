-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 15, 2022 at 08:49 AM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bajustore1`
--

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `idEmployee` varchar(5) NOT NULL,
  `namaEmployee` varchar(100) NOT NULL,
  `passwordEmployee` varchar(12) NOT NULL,
  `roleEmployee` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`idEmployee`, `namaEmployee`, `passwordEmployee`, `roleEmployee`) VALUES
('E0001', 'Willy Hansen', 'will0123', 'Operator'),
('E0002', 'Quinata Liu', 'liu99quin', 'Supervisor'),
('E0003', 'Engelina', 'en1234', 'Operator'),
('E0004', 'Windira', 'win12345', 'Supervisor'),
('E0005', 'Sintia', 'sin123456', 'Operator');

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE `item` (
  `idItem` varchar(5) NOT NULL,
  `namaItem` varchar(100) NOT NULL,
  `hargaItem` int(11) NOT NULL,
  `stockItem` int(11) NOT NULL,
  `imagePath` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`idItem`, `namaItem`, `hargaItem`, `stockItem`, `imagePath`) VALUES
('I0001', 'Cardigan bungaa', 1000000, 9, 'baju1.png'),
('I0002', 'Set Baju Jepang', 250000, 7, 'baju2.png'),
('I0003', 'Celana Kucing Krem', 80000, 1, 'baju3.png'),
('I0004', 'Celana Kain', 70000, 8, 'baju4.png'),
('I0005', 'Sweater Pink', 90000, 18, 'baju5.png');

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE `member` (
  `idMember` varchar(5) NOT NULL,
  `memberName` varchar(100) NOT NULL,
  `NIK` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`idMember`, `memberName`, `NIK`) VALUES
('M0001', 'Anggie', '6132673890162678'),
('M0002', 'Muliawanti', '6137283904917283'),
('M0003', 'Winata', '6130928371625364'),
('M0004', 'Helen', '6130622241688790');

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE `order` (
  `idOrder` varchar(5) NOT NULL,
  `dateOrder` date NOT NULL,
  `idMember` varchar(5) DEFAULT NULL,
  `idOperator` varchar(5) NOT NULL,
  `approvedBy` varchar(5) DEFAULT NULL,
  `statusOrder` varchar(12) DEFAULT NULL,
  `grandTotal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `order`
--

INSERT INTO `order` (`idOrder`, `dateOrder`, `idMember`, `idOperator`, `approvedBy`, `statusOrder`, `grandTotal`) VALUES
('O0001', '2022-02-14', NULL, 'E0001', 'E0002', 'Approved', 100000),
('O0002', '2022-02-14', NULL, 'E0001', 'E0002', 'Approved', 100000),
('O0003', '2022-02-14', 'M0004', 'E0001', 'E0002', 'Approved', 361000),
('O0004', '2022-02-14', 'M0001', 'E0001', 'E0002', 'Approved', 522500),
('O0005', '2022-02-14', NULL, 'E0005', NULL, 'Not Approved', 160000),
('O0006', '2022-02-14', 'M0003', 'E0003', 'E0002', 'Approved', 304000),
('O0007', '2022-02-14', NULL, 'E0003', 'E0002', 'Approved', 440000),
('O0008', '2022-02-15', NULL, 'E0001', 'E0004', 'Approved', 1080000);

-- --------------------------------------------------------

--
-- Table structure for table `orderdetail`
--

CREATE TABLE `orderdetail` (
  `idOrder` varchar(5) NOT NULL,
  `idItem` varchar(5) NOT NULL,
  `qty` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orderdetail`
--

INSERT INTO `orderdetail` (`idOrder`, `idItem`, `qty`) VALUES
('O0001', 'I0001', 1),
('O0002', 'I0001', 1),
('O0003', 'I0001', 3),
('O0003', 'I0003', 1),
('O0004', 'I0001', 2),
('O0004', 'I0004', 5),
('O0005', 'I0003', 2),
('O0006', 'I0004', 2),
('O0006', 'I0005', 2),
('O0007', 'I0001', 2),
('O0007', 'I0003', 3),
('O0008', 'I0001', 6),
('O0008', 'I0003', 6);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`idEmployee`);

--
-- Indexes for table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`idItem`);

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`idMember`);

--
-- Indexes for table `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`idOrder`);

--
-- Indexes for table `orderdetail`
--
ALTER TABLE `orderdetail`
  ADD PRIMARY KEY (`idOrder`,`idItem`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
