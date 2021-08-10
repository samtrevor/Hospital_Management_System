-- phpMyAdmin SQL Dump
-- version 3.2.0.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 11, 2020 at 02:59 AM
-- Server version: 5.1.36
-- PHP Version: 5.3.0

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Database: `hospital`
--

-- --------------------------------------------------------

--
-- Table structure for table `admitpatient_room`
--

CREATE TABLE IF NOT EXISTS `admitpatient_room` (
  `AdmitID` int(11) NOT NULL AUTO_INCREMENT,
  `PatientID` varchar(50) NOT NULL,
  `Disease` text NOT NULL,
  `RoomNo` varchar(50) NOT NULL,
  `AdmitDate` text NOT NULL,
  `DoctorID` varchar(50) NOT NULL,
  `AP_Remarks` text NOT NULL,
  PRIMARY KEY (`AdmitID`),
  KEY `DoctorID` (`DoctorID`),
  KEY `DoctorID_2` (`DoctorID`),
  KEY `PatientID` (`PatientID`),
  KEY `RoomNo` (`RoomNo`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5437 ;

--
-- Dumping data for table `admitpatient_room`
--

INSERT INTO `admitpatient_room` (`AdmitID`, `PatientID`, `Disease`, `RoomNo`, `AdmitDate`, `DoctorID`, `AP_Remarks`) VALUES
(22, '21', 'hapa ni admit room', 'A1', '8989', 'A3245', 'sawa');

-- --------------------------------------------------------

--
-- Table structure for table `admitpatient_ward`
--

CREATE TABLE IF NOT EXISTS `admitpatient_ward` (
  `AdmitID` int(11) NOT NULL AUTO_INCREMENT,
  `PatientID` varchar(50) NOT NULL,
  `Disease` text NOT NULL,
  `Wardname` varchar(100) NOT NULL,
  `AdmitDate` text NOT NULL,
  `DoctorID` varchar(50) NOT NULL,
  `AP_Remarks` text NOT NULL,
  PRIMARY KEY (`AdmitID`),
  KEY `DoctorID` (`DoctorID`),
  KEY `PatientID` (`PatientID`),
  KEY `Wardname` (`Wardname`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=790 ;

--
-- Dumping data for table `admitpatient_ward`
--

INSERT INTO `admitpatient_ward` (`AdmitID`, `PatientID`, `Disease`, `Wardname`, `AdmitDate`, `DoctorID`, `AP_Remarks`) VALUES
(22, '21', 'homa', 'children', '22/9/2020', 'A3245', 'hahasa');

-- --------------------------------------------------------

--
-- Table structure for table `bill_room`
--

CREATE TABLE IF NOT EXISTS `bill_room` (
  `BillNo` int(11) NOT NULL AUTO_INCREMENT,
  `DischargeID` int(11) NOT NULL,
  `BillingDate` text NOT NULL,
  `NoOfDays` int(11) NOT NULL,
  `RoomCharges` double NOT NULL,
  `TotalRoomCharges` double NOT NULL,
  `ServiceCharges` double NOT NULL,
  `TotalCharges` double NOT NULL,
  `PaymentMode` text NOT NULL,
  `PaymentModeDetails` text NOT NULL,
  `ChargesPaid` double NOT NULL,
  `DueCharges` double NOT NULL,
  PRIMARY KEY (`BillNo`),
  KEY `DischargeID` (`DischargeID`),
  KEY `DischargeID_2` (`DischargeID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=27 ;

--
-- Dumping data for table `bill_room`
--

INSERT INTO `bill_room` (`BillNo`, `DischargeID`, `BillingDate`, `NoOfDays`, `RoomCharges`, `TotalRoomCharges`, `ServiceCharges`, `TotalCharges`, `PaymentMode`, `PaymentModeDetails`, `ChargesPaid`, `DueCharges`) VALUES
(25, 23, '29/9/2020', 4, 2500, 10000, 2000, 12000, 'by Mpesa', 'kutest', 12000, 0);

-- --------------------------------------------------------

--
-- Table structure for table `bill_ward`
--

CREATE TABLE IF NOT EXISTS `bill_ward` (
  `BillNo` int(11) NOT NULL AUTO_INCREMENT,
  `DischargeID` int(11) NOT NULL,
  `BillingDate` text NOT NULL,
  `BedCharges` double NOT NULL,
  `NoOfDays` int(11) NOT NULL,
  `TotalBedCharges` double NOT NULL,
  `ServiceCharges` double NOT NULL,
  `TotalCharges` double NOT NULL,
  `PaymentMode` text NOT NULL,
  `PaymentModeDetails` text NOT NULL,
  `ChargesPaid` double NOT NULL,
  `DueCharges` double NOT NULL,
  PRIMARY KEY (`BillNo`),
  KEY `DischargeID` (`DischargeID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=26 ;

--
-- Dumping data for table `bill_ward`
--


-- --------------------------------------------------------

--
-- Table structure for table `dischargepatient_room`
--

CREATE TABLE IF NOT EXISTS `dischargepatient_room` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `AdmitID` int(11) NOT NULL,
  `DischargeDate` text NOT NULL,
  `DP_Remarks` text NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `AdmitID` (`AdmitID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=68 ;

--
-- Dumping data for table `dischargepatient_room`
--

INSERT INTO `dischargepatient_room` (`ID`, `AdmitID`, `DischargeDate`, `DP_Remarks`) VALUES
(23, 22, '22/9/2020', 'test');

-- --------------------------------------------------------

--
-- Table structure for table `dischargepatient_ward`
--

CREATE TABLE IF NOT EXISTS `dischargepatient_ward` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `AdmitID` int(11) NOT NULL,
  `DischargeDate` text NOT NULL,
  `DP_Remarks` text NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `AdmitID` (`AdmitID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=44 ;

--
-- Dumping data for table `dischargepatient_ward`
--

INSERT INTO `dischargepatient_ward` (`ID`, `AdmitID`, `DischargeDate`, `DP_Remarks`) VALUES
(43, 22, '27/9/2020', 'kodos');

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE IF NOT EXISTS `doctor` (
  `DoctorID` varchar(50) NOT NULL,
  `DoctorName` text NOT NULL,
  `FatherName` text NOT NULL,
  `Address` text NOT NULL,
  `ContactNo` text NOT NULL,
  `Email` text NOT NULL,
  `Qualifications` text NOT NULL,
  `Specialization` text NOT NULL,
  `Gender` text NOT NULL,
  `Bloodgroup` text NOT NULL,
  `DateOfJoining` text NOT NULL,
  PRIMARY KEY (`DoctorID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`DoctorID`, `DoctorName`, `FatherName`, `Address`, `ContactNo`, `Email`, `Qualifications`, `Specialization`, `Gender`, `Bloodgroup`, `DateOfJoining`) VALUES
('A3245', 'Sanuel Mbugua', 'Richard Njehia', '317, S.Kinangop', '0790127078', 'samtrevor13@gmail.com', 'Degree in Medical Science', 'Neural Surgeon', 'Male', 'B-', '25/0/2020');

-- --------------------------------------------------------

--
-- Table structure for table `patientregistration`
--

CREATE TABLE IF NOT EXISTS `patientregistration` (
  `PatientID` varchar(50) NOT NULL,
  `Patientname` text NOT NULL,
  `Fathername` text NOT NULL,
  `Address` text NOT NULL,
  `ContactNo` text NOT NULL,
  `Email` text NOT NULL,
  `Age` int(11) NOT NULL,
  `Gen` text NOT NULL,
  `BG` text NOT NULL,
  `Remarks` text NOT NULL,
  PRIMARY KEY (`PatientID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patientregistration`
--

INSERT INTO `patientregistration` (`PatientID`, `Patientname`, `Fathername`, `Address`, `ContactNo`, `Email`, `Age`, `Gen`, `BG`, `Remarks`) VALUES
('21', 'sam', 'njehia', '317', '07', 'sam', 24, 'Male', 'O-', 'slkalsa');

-- --------------------------------------------------------

--
-- Table structure for table `registration`
--

CREATE TABLE IF NOT EXISTS `registration` (
  `username` varchar(100) NOT NULL,
  `password` varchar(50) NOT NULL,
  `NameOfUser` varchar(250) NOT NULL,
  `ContactNo` varchar(15) NOT NULL,
  `Email` varchar(250) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `registration`
--

INSERT INTO `registration` (`username`, `password`, `NameOfUser`, `ContactNo`, `Email`) VALUES
('sammy', 'admin', 'samuel mbugua njehia', '0790127078', 'samtrevor13@gmail.com'),
('elvo', 'mtoto', 'Elvis Njehia Njeri', '0703271444', 'elvis@gmail.com'),
('willy', 'kababaye', 'William Chege', '0720000000', 'home@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE IF NOT EXISTS `room` (
  `RoomNo` varchar(50) NOT NULL,
  `RoomType` varchar(100) NOT NULL,
  `RoomCharges` int(11) NOT NULL,
  `RoomStatus` varchar(100) NOT NULL,
  PRIMARY KEY (`RoomNo`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`RoomNo`, `RoomType`, `RoomCharges`, `RoomStatus`) VALUES
('A1', 'Deluxe', 2500, 'Vacant'),
('A2', 'Deluxe', 2500, 'Vacant'),
('A3', 'Deluxe', 2500, 'Vacant');

-- --------------------------------------------------------

--
-- Table structure for table `services`
--

CREATE TABLE IF NOT EXISTS `services` (
  `ServiceID` int(11) NOT NULL AUTO_INCREMENT,
  `ServiceName` varchar(250) NOT NULL,
  `ServiceDate` varchar(50) NOT NULL,
  `PatientID` varchar(50) NOT NULL,
  `ServiceCharges` int(11) NOT NULL,
  PRIMARY KEY (`ServiceID`),
  UNIQUE KEY `ServiceDate` (`ServiceDate`),
  UNIQUE KEY `PatientID` (`PatientID`),
  UNIQUE KEY `PatientID_2` (`PatientID`),
  UNIQUE KEY `ServiceDate_2` (`ServiceDate`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2346 ;

--
-- Dumping data for table `services`
--

INSERT INTO `services` (`ServiceID`, `ServiceName`, `ServiceDate`, `PatientID`, `ServiceCharges`) VALUES
(24, 'lab', '22/9/2020', '21', 2000);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(100) NOT NULL,
  `user_password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `user_password`) VALUES
('sammy', 'admin1'),
('elvo', 'admin'),
('willy', 'kababaye');

-- --------------------------------------------------------

--
-- Table structure for table `ward`
--

CREATE TABLE IF NOT EXISTS `ward` (
  `wardname` varchar(100) NOT NULL,
  `wardtype` varchar(50) NOT NULL,
  `NoOfBeds` int(11) NOT NULL,
  `Charges` int(11) NOT NULL,
  PRIMARY KEY (`wardname`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ward`
--

INSERT INTO `ward` (`wardname`, `wardtype`, `NoOfBeds`, `Charges`) VALUES
('jericho', 'General', 40, 1000),
('jerusalem', 'Special', 7, 2500),
('bethlehem', 'Special', 6, 3000),
('samaria', 'General', 30, 1300),
('bufallo', 'Special', 9, 3500),
('lion icu', 'Special', 2, 6000),
('giraffe', 'Special', 1, 3000),
('main', 'General', 150, 500),
('children', 'General', 30, 800),
('nursery', 'Special', 25, 1000),
('labor', 'Special', 40, 1500),
('maternal', 'Special', 5, 1500),
('male', 'General', 45, 1600),
('female', 'General', 45, 1600),
('nyayo', 'General', 400, 700),
('kenyatta', 'Special', 5, 6000);

-- --------------------------------------------------------

--
-- Table structure for table `wardboy_nurse_tbl`
--

CREATE TABLE IF NOT EXISTS `wardboy_nurse_tbl` (
  `ID` varchar(50) NOT NULL,
  `W_N_Name` varchar(250) NOT NULL,
  `Category` varchar(250) NOT NULL,
  `Address` varchar(250) NOT NULL,
  `ContactNo` varchar(15) NOT NULL,
  `Email` varchar(250) NOT NULL,
  `Qualifications` varchar(250) NOT NULL,
  `BloodGroup` varchar(50) NOT NULL,
  `DateOfJoining` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `wardboy_nurse_tbl`
--

INSERT INTO `wardboy_nurse_tbl` (`ID`, `W_N_Name`, `Category`, `Address`, `ContactNo`, `Email`, `Qualifications`, `BloodGroup`, `DateOfJoining`) VALUES
('32795265', 'Samuel Mbugua Njehia', 'Nurse', '317, South Kinangop', '0790127078', 'samtrevor13@gmail.com', 'Diploma in Nursing', 'B+', '23/09/2020'),
('33784267', 'Mickson Muchemi', 'WardBoy', '288, Thika', '0743672453', 'micky12@gmail.com', 'Diploma in Nursing', 'A-', '20/09/2020'),
('6871143', 'Richard', 'WardBoy', '317', '0724440427', 'rmbugua63@gmail.com', 'diploma', 'AB+', '28/9/2020');
