-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 19, 2025 at 07:40 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `smartcityhub`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteCustomer` (IN `inputId` INT)   BEGIN
    DECLARE customerExists INT;

    SELECT COUNT(*) INTO customerExists
    FROM customer
    WHERE id = inputId;

    IF customerExists = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Customer ID not found';
    ELSE
        DELETE FROM customer
        WHERE id = inputId;
    END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `get_deleted_customers` ()   BEGIN
    SELECT customerId, username, password, email, fullName, deletedAt
    FROM CustomerLog
    WHERE deletedAt IS NOT NULL
    ORDER BY deletedAt DESC;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `Id` int(11) NOT NULL,
  `UserName` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `CreatedAt` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`Id`, `UserName`, `Password`, `Email`, `CreatedAt`) VALUES
(1, 'Admin', 'Admin@789', 'admin@gmail.com', '2025-08-14 11:21:13'),
(2, 'lju', 'lju@456', 'lju@gmail.com', '2025-08-14 11:23:52'),
(3, 'Suhani', 'suhani@678', 'suhani@gmail.com', '2025-08-15 08:18:20');

-- --------------------------------------------------------

--
-- Table structure for table `area`
--

CREATE TABLE `area` (
  `Id` int(11) NOT NULL,
  `Name` varchar(155) NOT NULL,
  `Latitude` double NOT NULL,
  `Longitude` double NOT NULL,
  `IsEmergencyPoint` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `area`
--

INSERT INTO `area` (`Id`, `Name`, `Latitude`, `Longitude`, `IsEmergencyPoint`) VALUES
(380001, 'Lal Darwaja', 23.0264, 72.5819, 0),
(380004, 'Shahibaug', 23.0561, 72.5962, 1),
(380005, 'Motera', 23.0973, 72.596, 0),
(380006, 'Ellisbridge', 23.0995, 72.602, 1),
(380007, 'Paldi', 23.0112, 72.5631, 1),
(380008, 'Maninagar', 23.0063, 72.602, 1),
(380009, 'Navrangpura', 23.0421, 72.5597, 1),
(380015, 'Vastrapur', 30.21, 70.28, 0),
(380021, 'Apparel Park', 23.0187, 72.6378, 1),
(380024, 'Bapunagar', 23.0336, 72.6352, 1),
(380028, 'Bhairavnath Road SO', 26.02, 27.28, 1),
(380052, 'Thaltej', 23.0504, 72.4991, 1),
(380058, 'Bopal', 26.21, 27.51, 0),
(382350, 'Nikol', 23.05, 72.67, 1),
(382418, 'Vastral', 22.9978, 72.666, 1),
(382421, 'Adalaj', 23.1667, 72.581, 1),
(382470, 'Gota', 23.0995, 72.5518, 1);

-- --------------------------------------------------------

--
-- Table structure for table `bus`
--

CREATE TABLE `bus` (
  `Id` int(11) NOT NULL,
  `LicensePlate` varchar(155) NOT NULL,
  `Capacity` int(11) NOT NULL,
  `CurrentRouteId` int(11) NOT NULL,
  `CurrentAreaId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bus`
--

INSERT INTO `bus` (`Id`, `LicensePlate`, `Capacity`, `CurrentRouteId`, `CurrentAreaId`) VALUES
(1, 'GJ01AB1234', 50, 1, 380008),
(2, 'GJ01CD5678', 45, 2, 380009),
(3, 'GJ01IJ7890', 48, 5, 380004),
(4, 'GJ01FF1122', 50, 7, 382470),
(5, 'GJ01GG3344', 48, 8, 380024);

-- --------------------------------------------------------

--
-- Table structure for table `complaint`
--

CREATE TABLE `complaint` (
  `Id` int(11) NOT NULL,
  `Department` varchar(150) NOT NULL,
  `UserId` int(11) NOT NULL,
  `Issue` text NOT NULL,
  `Status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `complaint`
--

INSERT INTO `complaint` (`Id`, `Department`, `UserId`, `Issue`, `Status`) VALUES
(1, 'Water Supply', 4, 'Low water pressure in morning', 0),
(2, 'Electricity', 2, 'Frequent power cuts', 2),
(3, 'Sanitation', 6, 'Garbage not collected regularly', 1),
(4, 'Roads and Transport', 3, 'Potholes on main road', 0),
(5, 'Street Lighting', 5, 'Streetlight not working near house', 2),
(6, 'Street Lighting ', 1, 'Dim lights in parking area', 1);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `Id` int(11) NOT NULL,
  `UserName` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `FullName` varchar(100) NOT NULL,
  `CreatedAt` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`Id`, `UserName`, `Password`, `Email`, `FullName`, `CreatedAt`) VALUES
(1, 'Suhani', 'suhani@123', 'suhani@gmail.com', 'Jain Suhani', '2025-08-14 15:21:13'),
(2, 'Pranay', 'pranay@123', 'pranay@gmail.com', 'Jain Pranay', '2025-08-14 15:21:13'),
(3, 'Aayush', 'aayush@123', 'aayush@gmail.com', 'Kanth Aayush', '2025-08-14 15:21:13'),
(4, 'Teja', 'teja@123', 'teja@gamil.com', 'Teja Sajja', '2025-08-15 10:41:30'),
(5, 'Yami', 'yami@123', 'yami@gamil.com', 'Yami Gautam', '2025-08-15 10:32:54'),
(6, 'Amritha', 'amritha@123', 'amritha@gmail.com', 'Amritha Aiyer', '2025-08-15 10:38:34');

--
-- Triggers `customer`
--
DELIMITER $$
CREATE TRIGGER `LogDeletedCustomer` BEFORE DELETE ON `customer` FOR EACH ROW BEGIN
    INSERT INTO customer_log(customerId,username,password,email,fullName,deletedAt) VALUES(OLD.id,OLD.UserName,OLD.Password,OLD.Email,OLD.FullName,NOW());
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `customer_log`
--

CREATE TABLE `customer_log` (
  `logId` int(11) NOT NULL,
  `customerId` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `fullName` varchar(100) NOT NULL,
  `deletedAt` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `emergencyservice`
--

CREATE TABLE `emergencyservice` (
  `Id` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Type` varchar(100) NOT NULL,
  `AreaId` int(11) NOT NULL,
  `ContactNumber` bigint(10) NOT NULL,
  `AvailableVehicles` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `emergencyservice`
--

INSERT INTO `emergencyservice` (`Id`, `Name`, `Type`, `AreaId`, `ContactNumber`, `AvailableVehicles`) VALUES
(1, 'Navrangpura Police Station', 'Police Station', 380009, 7926345678, 3),
(2, 'LG Hospital Trauma Center', 'Hospital', 380021, 7925467890, 5),
(3, 'Maninagar Fire Station', 'Fire Station', 380008, 7925412345, 2),
(5, 'Paldi Fire Station', 'Fire Station', 380007, 7926512345, 3),
(6, 'Rajasthan Hospitals', 'Hospital', 380004, 7969086310, 7);

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `Id` int(11) NOT NULL,
  `UserID` int(11) NOT NULL,
  `PlaceId` int(11) NOT NULL,
  `Comments` text NOT NULL,
  `Rating` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`Id`, `UserID`, `PlaceId`, `Comments`, `Rating`) VALUES
(2, 1, 5, 'The aquarium is fantastic', 7),
(3, 4, 7, 'An excellent location for strolling', 5),
(4, 1, 3, 'Awesome collection of vintage cars', 9),
(5, 6, 6, 'Very historical Stepwell', 6);

-- --------------------------------------------------------

--
-- Table structure for table `metro`
--

CREATE TABLE `metro` (
  `Id` int(11) NOT NULL,
  `TrainName` varchar(100) NOT NULL,
  `Capacity` int(11) NOT NULL,
  `CurrentRouteId` int(11) NOT NULL,
  `CurrentAreaId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `metro`
--

INSERT INTO `metro` (`Id`, `TrainName`, `Capacity`, `CurrentRouteId`, `CurrentAreaId`) VALUES
(1, 'Ahmedabad Metro Express', 300, 3, 380052),
(3, 'Gota Green Line', 310, 6, 382470),
(4, 'Motera Sttdium Express', 325, 10, 380005),
(5, 'Apparel Park Connector', 315, 11, 380021),
(6, 'Thaltej Junction Rapid', 305, 12, 380052);

-- --------------------------------------------------------

--
-- Table structure for table `parkinglot`
--

CREATE TABLE `parkinglot` (
  `Id` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `AreaId` int(11) NOT NULL,
  `Capacity` int(11) NOT NULL,
  `CurrentOccupancy` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `parkinglot`
--

INSERT INTO `parkinglot` (`Id`, `Name`, `AreaId`, `Capacity`, `CurrentOccupancy`) VALUES
(1, 'Shahibaug Parking', 380004, 110, 85),
(3, 'Navrangpura Parking', 380009, 60, 30),
(4, 'Paldi Bus Stand Parking', 380007, 70, 55),
(5, 'CG Road Parking', 380009, 120, 90);

-- --------------------------------------------------------

--
-- Table structure for table `route`
--

CREATE TABLE `route` (
  `Id` int(11) NOT NULL,
  `Name` varchar(155) NOT NULL,
  `Length(km)` double NOT NULL,
  `IsBusRoute` tinyint(1) NOT NULL,
  `IsMetroRoute` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `route`
--

INSERT INTO `route` (`Id`, `Name`, `Length(km)`, `IsBusRoute`, `IsMetroRoute`) VALUES
(1, 'Maninagar to Lal Darwaja', 8.5, 1, 0),
(2, 'Navrangpura to  ISKCON Cross Road', 6.2, 1, 0),
(3, 'Thaltej to Vastral', 21.2, 0, 1),
(4, 'Shahpur to Thaltej', 15, 0, 1),
(5, 'Shahibaug to Vastral', 10.1, 1, 0),
(6, 'Gota to Appparel Park', 18.7, 0, 1),
(7, 'Gota to Kalupur', 15.4, 1, 0),
(8, 'Bapunagar to Geeta Mandir', 6.9, 1, 0),
(9, 'Chandkheda to Paldi', 14.2, 1, 0),
(10, 'Motera to Vastral', 22.5, 0, 1),
(11, 'Apparel Park to Sabarmati', 19.3, 0, 1),
(12, 'Thaltej to Sabarmati', 12.7, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `schedule`
--

CREATE TABLE `schedule` (
  `Id` int(11) NOT NULL,
  `RouteId` int(11) NOT NULL,
  `DepartureTime` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `schedule`
--

INSERT INTO `schedule` (`Id`, `RouteId`, `DepartureTime`) VALUES
(1, 11, '06:30:00'),
(2, 8, '07:15:00'),
(3, 9, '08:00:00'),
(4, 6, '09:00:00'),
(5, 7, '09:45:00'),
(6, 1, '10:30:00'),
(7, 10, '11:15:00'),
(8, 2, '12:00:00'),
(9, 5, '12:45:00'),
(10, 4, '13:30:00'),
(11, 12, '14:15:00'),
(12, 3, '15:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `station`
--

CREATE TABLE `station` (
  `Id` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `AreaId` int(11) NOT NULL,
  `IsBusStation` tinyint(1) NOT NULL,
  `IsMetroStation` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `station`
--

INSERT INTO `station` (`Id`, `Name`, `AreaId`, `IsBusStation`, `IsMetroStation`) VALUES
(1, 'Shahibaug', 380004, 1, 0),
(2, 'Maninagar', 380008, 1, 0),
(3, 'Navrangpura', 380009, 1, 0),
(4, 'Gota', 382470, 1, 1),
(5, 'Thaltej', 380052, 0, 1),
(7, 'Bapunagar', 380024, 1, 0),
(8, 'Apparel Park', 380021, 0, 1),
(10, 'Motera', 380005, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `street`
--

CREATE TABLE `street` (
  `Id` int(11) NOT NULL,
  `StartAreaId` int(11) NOT NULL,
  `EndAreaId` int(11) NOT NULL,
  `Distance` double NOT NULL,
  `IsOneWay` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `street`
--

INSERT INTO `street` (`Id`, `StartAreaId`, `EndAreaId`, `Distance`, `IsOneWay`) VALUES
(1, 380008, 380001, 4.6, 0),
(3, 380009, 380007, 3.2, 1),
(5, 382470, 380052, 6.1, 0),
(8, 382418, 380021, 4.8, 1);

-- --------------------------------------------------------

--
-- Table structure for table `ticket`
--

CREATE TABLE `ticket` (
  `Id` int(11) NOT NULL,
  `UserID` int(11) NOT NULL,
  `RouteId` int(11) NOT NULL,
  `IsBusTransport` tinyint(1) NOT NULL,
  `IsMetroTransport` tinyint(1) NOT NULL,
  `Time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `TotalBill` double NOT NULL,
  `Distance` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ticket`
--

INSERT INTO `ticket` (`Id`, `UserID`, `RouteId`, `IsBusTransport`, `IsMetroTransport`, `Time`, `TotalBill`, `Distance`) VALUES
(1, 4, 10, 1, 0, '2025-08-15 10:50:41', 25, 22.5),
(2, 1, 5, 0, 1, '2025-08-15 10:46:52', 30, 21.2),
(3, 5, 8, 1, 0, '2025-08-15 10:50:41', 15, 6.9),
(4, 2, 6, 0, 1, '2025-08-15 10:50:41', 20, 18.7),
(5, 1, 12, 0, 1, '2025-08-15 10:52:03', 15, 12.7),
(6, 3, 9, 1, 0, '2025-08-15 10:52:55', 20, 14.2),
(7, 6, 2, 1, 0, '2025-08-15 10:54:49', 15, 6.2);

-- --------------------------------------------------------

--
-- Table structure for table `touristplace`
--

CREATE TABLE `touristplace` (
  `Id` int(11) NOT NULL,
  `Name` varchar(155) NOT NULL,
  `AreaId` int(11) NOT NULL,
  `Category` varchar(155) NOT NULL,
  `Ratings` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `touristplace`
--

INSERT INTO `touristplace` (`Id`, `Name`, `AreaId`, `Category`, `Ratings`) VALUES
(2, 'Sidi Saiyyed Masjid', 380001, 'cultural', 4.5),
(3, 'Auto World Vintage Car Museum', 382350, 'cultural', 4.4),
(5, 'Science City', 380052, 'man made', 4.4),
(6, 'Adalaj Stepwell', 382421, 'cultural', 4.5),
(7, 'Kankaria Lake', 380008, 'natural', 4.5),
(8, 'Alpha one Mall', 380015, 'man made', 4.6),
(9, 'Manek Chowk', 380001, 'Man made', 4.7),
(10, 'Riverfront Flower Park', 380006, 'man made', 4.9);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `area`
--
ALTER TABLE `area`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `bus`
--
ALTER TABLE `bus`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `BusArea` (`CurrentAreaId`),
  ADD KEY `BusRoute` (`CurrentRouteId`);

--
-- Indexes for table `complaint`
--
ALTER TABLE `complaint`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `UserInfo` (`UserId`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `customer_log`
--
ALTER TABLE `customer_log`
  ADD PRIMARY KEY (`logId`);

--
-- Indexes for table `emergencyservice`
--
ALTER TABLE `emergencyservice`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `ServiceArea` (`AreaId`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `PlaceInfo` (`PlaceId`),
  ADD KEY `CustomerInfo` (`UserID`);

--
-- Indexes for table `metro`
--
ALTER TABLE `metro`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `MetroArea` (`CurrentAreaId`),
  ADD KEY `MetroRoute` (`CurrentRouteId`);

--
-- Indexes for table `parkinglot`
--
ALTER TABLE `parkinglot`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `LotArea` (`AreaId`);

--
-- Indexes for table `route`
--
ALTER TABLE `route`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `RouteInfo` (`RouteId`);

--
-- Indexes for table `station`
--
ALTER TABLE `station`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `StationArea` (`AreaId`);

--
-- Indexes for table `street`
--
ALTER TABLE `street`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `StartArea` (`StartAreaId`),
  ADD KEY `EndArea` (`EndAreaId`);

--
-- Indexes for table `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `CustomerTicket` (`UserID`),
  ADD KEY `TravelRoute` (`RouteId`);

--
-- Indexes for table `touristplace`
--
ALTER TABLE `touristplace`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `PlaceArea` (`AreaId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `area`
--
ALTER TABLE `area`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=382472;

--
-- AUTO_INCREMENT for table `bus`
--
ALTER TABLE `bus`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `complaint`
--
ALTER TABLE `complaint`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `customer_log`
--
ALTER TABLE `customer_log`
  MODIFY `logId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `emergencyservice`
--
ALTER TABLE `emergencyservice`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `metro`
--
ALTER TABLE `metro`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `parkinglot`
--
ALTER TABLE `parkinglot`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `route`
--
ALTER TABLE `route`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `schedule`
--
ALTER TABLE `schedule`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `station`
--
ALTER TABLE `station`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `street`
--
ALTER TABLE `street`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `ticket`
--
ALTER TABLE `ticket`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `touristplace`
--
ALTER TABLE `touristplace`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bus`
--
ALTER TABLE `bus`
  ADD CONSTRAINT `BusArea` FOREIGN KEY (`CurrentAreaId`) REFERENCES `area` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `BusRoute` FOREIGN KEY (`CurrentRouteId`) REFERENCES `route` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `complaint`
--
ALTER TABLE `complaint`
  ADD CONSTRAINT `UserInfo` FOREIGN KEY (`UserId`) REFERENCES `customer` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `emergencyservice`
--
ALTER TABLE `emergencyservice`
  ADD CONSTRAINT `ServiceArea` FOREIGN KEY (`AreaId`) REFERENCES `area` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `feedback`
--
ALTER TABLE `feedback`
  ADD CONSTRAINT `CustomerInfo` FOREIGN KEY (`UserID`) REFERENCES `customer` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `PlaceInfo` FOREIGN KEY (`PlaceId`) REFERENCES `touristplace` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `metro`
--
ALTER TABLE `metro`
  ADD CONSTRAINT `MetroArea` FOREIGN KEY (`CurrentAreaId`) REFERENCES `area` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `MetroRoute` FOREIGN KEY (`CurrentRouteId`) REFERENCES `route` (`Id`);

--
-- Constraints for table `parkinglot`
--
ALTER TABLE `parkinglot`
  ADD CONSTRAINT `LotArea` FOREIGN KEY (`AreaId`) REFERENCES `area` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `schedule`
--
ALTER TABLE `schedule`
  ADD CONSTRAINT `RouteInfo` FOREIGN KEY (`RouteId`) REFERENCES `route` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `station`
--
ALTER TABLE `station`
  ADD CONSTRAINT `StationArea` FOREIGN KEY (`AreaId`) REFERENCES `area` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `street`
--
ALTER TABLE `street`
  ADD CONSTRAINT `EndArea` FOREIGN KEY (`EndAreaId`) REFERENCES `area` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `StartArea` FOREIGN KEY (`StartAreaId`) REFERENCES `area` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `CustomerTicket` FOREIGN KEY (`UserID`) REFERENCES `customer` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `TravelRoute` FOREIGN KEY (`RouteId`) REFERENCES `route` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `touristplace`
--
ALTER TABLE `touristplace`
  ADD CONSTRAINT `PlaceArea` FOREIGN KEY (`AreaId`) REFERENCES `area` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
