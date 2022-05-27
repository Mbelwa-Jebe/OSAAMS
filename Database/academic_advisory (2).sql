-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 27, 2022 at 11:15 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `academic_advisory`
--
CREATE DATABASE IF NOT EXISTS `academic_advisory` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `academic_advisory`;

-- --------------------------------------------------------

--
-- Table structure for table `advisors`
--

CREATE TABLE `advisors` (
  `advisor_id` varchar(20) NOT NULL,
  `advisor_fname` varchar(20) NOT NULL,
  `advisor_lname` varchar(20) NOT NULL,
  `advisor_rank` varchar(20) NOT NULL,
  `advisor_office` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `advisors`
--

INSERT INTO `advisors` (`advisor_id`, `advisor_fname`, `advisor_lname`, `advisor_rank`, `advisor_office`, `password`) VALUES
('100', 'john', 'doe', 'lecturer', 'b205', 'passwrd'),
('101', 'john', 'dillinger', 'lecturer', 'b209', '1234'),
('200', 'Dre', 'Dre', 'Dr', '6789', 'Dre');

-- --------------------------------------------------------

--
-- Table structure for table `appointments`
--

CREATE TABLE `appointments` (
  `appointment_id` int(20) NOT NULL,
  `student_id` varchar(20) NOT NULL,
  `advisor_id` varchar(20) NOT NULL,
  `response` varchar(20) NOT NULL DEFAULT 'waiting',
  `request_info` varchar(20) NOT NULL,
  `ap_from` varchar(20) NOT NULL,
  `ap_to` varchar(20) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `appointments`
--

INSERT INTO `appointments` (`appointment_id`, `student_id`, `advisor_id`, `response`, `request_info`, `ap_from`, `ap_to`, `timestamp`) VALUES
(3, '6789', '200', 'waiting', 'academic', '6789', '200', '2022-04-09 11:54:16'),
(18, '1234', '100', 'Rejected', 'consultations', '1234', '100', '2022-04-24 10:57:28'),
(30, '1234', '100', 'Accepted', 'voila', '1234', '100', '2022-05-01 10:18:05'),
(47, '1234', '100', 'Rejected', 'sync 1', '1234', '100', '2022-05-24 06:46:59'),
(51, '1234', '100', 'Rejected', 'test 45', '100', '1234', '2022-05-25 12:06:57'),
(52, '1234', '100', 'waiting', 'testing', '100', '1234', '2022-05-25 12:20:10');

-- --------------------------------------------------------

--
-- Table structure for table `consultations`
--

CREATE TABLE `consultations` (
  `consultation_id` int(20) NOT NULL,
  `advisor_id` varchar(20) NOT NULL,
  `student_id` varchar(20) NOT NULL,
  `report` varchar(9000) NOT NULL,
  `report_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `consultations`
--

INSERT INTO `consultations` (`consultation_id`, `advisor_id`, `student_id`, `report`, `report_date`) VALUES
(2, '100', '1234', 'First Report', '2022-04-19 16:45:24'),
(4, '200', '6789', 'rejected', '2022-04-19 16:47:47'),
(6, '200', '6789', 'REPORT TEST', '2022-04-26 07:04:59'),
(8, '100', '1234', 'upload_test', '2022-04-26 14:21:44'),
(12, '100', '1234', 'yctyc', '2022-04-26 14:30:37'),
(14, '100', '1234', 'new skin', '2022-05-01 11:37:08'),
(15, '100', '1234', 'student_upload_test', '2022-05-02 08:14:11'),
(16, '100', '1234', 'upload on app', '2022-05-02 08:57:36');

-- --------------------------------------------------------

--
-- Table structure for table `faqs`
--

CREATE TABLE `faqs` (
  `faqs_id` int(255) NOT NULL,
  `faqs_question` varchar(999) NOT NULL,
  `faqs_answer` varchar(999) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `faqs`
--

INSERT INTO `faqs` (`faqs_id`, `faqs_question`, `faqs_answer`) VALUES
(1, 'What should I do if I miss a test or exam?         ', 'A student who has missed a test or exam must write a letter with their particular reason for missing the test or exam together with proper attachments to the Head of Department for the permission to resit for the test or exam.'),
(2, 'What is the progress of a student from Year to Year?', 'Candidates who are full time students are required to pass a total minimum of 120 course credits in examinations in the academic year and attain a minimum overall GPA of 2.0 before proceeding to the following year of study. A candidate may be allowed to re-sit failed courses in Supplementary Examinations if he or she has attained an overall GPA of 1.8 or above in the First Sitting calculated in accordance with the credit weighting of individual courses. The maximum grade obtainable in a Supplementary Examination shall be the minimum passing grade i.e. \'C\'.                     '),
(3, 'What should I do if I want to postpone my studies?', 'A student who has registered for a course but who wishes to withdraw from that course must apply to the Head or Dean or Director or Principal, as the case may be, of the unit hosting the programme through the Head of the course offering Department.'),
(4, 'What procedures should I take if I want to transfer my course?', 'Transfer of course should be done via ARIS3. A student should do so not later than Friday of the fourth teaching week of the semester. No student will be permitted to commence any course three weeks after the beginning of the semester or withdraw from any course four weeks after the beginning of the semester.'),
(5, 'How do I get a Practical Training (PT) place?', 'PT places are to be searched individually with the help of the college.'),
(6, 'Are the consultations done private?', 'Yes, all consultations done are surely to be private.'),
(7, 'What are the total minimum and maximum course credits per semester in an academic year?', 'Total minimum of 60 course credits and a maximum of 72 course credits per semester in the academic year.'),
(8, 'What is the grading system of the assessment of the student\'s progress?', 'Assessment of the student’s progress shall be based on the following grading system: A : 70–100%, B+ : 60–69%,B : 50–59 %, C : 40–49%, D : 35–39%, E : 0–34%. The range of marks being a result of rounding of all decimals of the marks and the minimum passing grade shall be \'C\'.'),
(9, 'What happens if I do not adhere examination regularities?', 'All cases of alleged examination irregularities, including alleged unauthorized absence from examination, possession of unauthorized material in the examination room, causing disturbances in or near any examination room and any form of or kind of dishonesty, destruction or falsification of any evidence of irregularity or cheating in examination, shall be reported to the Senate Undergraduate Studies Committee or to a College Academic Board or Committee, which Committee or Board shall have power to summon the students and members of staff of the University, as it deems necessary and make decisions, subject to confirmation by Senate.');

-- --------------------------------------------------------

--
-- Table structure for table `messages`
--

CREATE TABLE `messages` (
  `message_id` varchar(20) NOT NULL,
  `student_id` varchar(20) NOT NULL,
  `advisor_id` varchar(20) NOT NULL,
  `timestamp` datetime(6) NOT NULL DEFAULT current_timestamp(6),
  `content` varchar(9000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `registration_no` varchar(20) NOT NULL,
  `student_fname` varchar(20) NOT NULL,
  `student_lname` varchar(20) NOT NULL,
  `programme` varchar(20) NOT NULL,
  `year_of_study` year(4) NOT NULL,
  `advisor_id` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`registration_no`, `student_fname`, `student_lname`, `programme`, `year_of_study`, `advisor_id`, `password`) VALUES
('1234', 'Mbelwa', 'Jebe', 'BIT', 2003, '100', '1234'),
('2019-04-02822', 'Mbelwa', 'Jebe', 'BIT', 2003, '100', 'passwrd'),
('6789', 'Bill', 'Ali', 'BIT', 2003, '200', '6789');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `advisors`
--
ALTER TABLE `advisors`
  ADD PRIMARY KEY (`advisor_id`);

--
-- Indexes for table `appointments`
--
ALTER TABLE `appointments`
  ADD PRIMARY KEY (`appointment_id`),
  ADD KEY `student_id` (`student_id`),
  ADD KEY `advisor_id` (`advisor_id`);

--
-- Indexes for table `consultations`
--
ALTER TABLE `consultations`
  ADD PRIMARY KEY (`consultation_id`),
  ADD KEY `advisor_id` (`advisor_id`),
  ADD KEY `student_id` (`student_id`);

--
-- Indexes for table `faqs`
--
ALTER TABLE `faqs`
  ADD PRIMARY KEY (`faqs_id`);

--
-- Indexes for table `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`message_id`),
  ADD KEY `advisor_id` (`advisor_id`),
  ADD KEY `student_id` (`student_id`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`registration_no`),
  ADD KEY `advisor_id` (`advisor_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appointments`
--
ALTER TABLE `appointments`
  MODIFY `appointment_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT for table `consultations`
--
ALTER TABLE `consultations`
  MODIFY `consultation_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `faqs`
--
ALTER TABLE `faqs`
  MODIFY `faqs_id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `appointments`
--
ALTER TABLE `appointments`
  ADD CONSTRAINT `appointments_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`registration_no`),
  ADD CONSTRAINT `appointments_ibfk_2` FOREIGN KEY (`advisor_id`) REFERENCES `advisors` (`advisor_id`);

--
-- Constraints for table `consultations`
--
ALTER TABLE `consultations`
  ADD CONSTRAINT `consultations_ibfk_1` FOREIGN KEY (`advisor_id`) REFERENCES `advisors` (`advisor_id`),
  ADD CONSTRAINT `consultations_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `students` (`registration_no`);

--
-- Constraints for table `messages`
--
ALTER TABLE `messages`
  ADD CONSTRAINT `messages_ibfk_1` FOREIGN KEY (`advisor_id`) REFERENCES `advisors` (`advisor_id`),
  ADD CONSTRAINT `messages_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `students` (`registration_no`);

--
-- Constraints for table `students`
--
ALTER TABLE `students`
  ADD CONSTRAINT `students_ibfk_1` FOREIGN KEY (`advisor_id`) REFERENCES `advisors` (`advisor_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
