-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Maj 30, 2026 at 03:21 PM
-- Wersja serwera: 10.4.28-MariaDB
-- Wersja PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `szkola`
--

DELIMITER $$
--
-- Procedury
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `L2Z01` ()   BEGIN
    SELECT `Uczniowie`.`Nazwisko`, `Uczniowie`.`Imie`, `Uczniowie`.`IdU`, `Uczniowie`.`KlasaU`, `Miasta`.`NazwaM`
    FROM `Uczniowie`
    JOIN `Miasta` ON `Uczniowie`.`Miasto` = `Miasta`.`IdM`
    WHERE `Uczniowie`.`KlasaU` LIKE 'I%' AND `Uczniowie`.`KlasaU` NOT LIKE 'IV%' AND `Uczniowie`.`KlasaU` NOT LIKE 'IX%';
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `L2Z03` (IN `klasa` VARCHAR(6))   BEGIN
    SELECT `Uczniowie`.`IdU`, `Uczniowie`.`Imie`, `Uczniowie`.`Nazwisko`, `Uczniowie`.`KlasaU`
    FROM `Uczniowie`
    WHERE `Uczniowie`.`KlasaU` = klasa
    ORDER BY `Uczniowie`.`Nazwisko`, `Uczniowie`.`Imie`;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `L2Z09` ()   BEGIN
    SELECT Uczniowie.IdU, Uczniowie.Nazwisko, Uczniowie.Imie, ROUND(AVG(Oceny.Ocena), 2) AS SredniaOcen
    FROM Uczniowie
    JOIN Oceny ON Uczniowie.IdU = Oceny.IdU
    GROUP BY Uczniowie.IdU, Uczniowie.Nazwisko, Uczniowie.Imie
    ORDER BY Uczniowie.IdU, Uczniowie.Nazwisko, Uczniowie.Imie;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `L2Z12` ()   BEGIN
    SELECT DISTINCT Miasta.NazwaM
    FROM Uczniowie
    JOIN Miasta ON Uczniowie.Miasto = Miasta.IdM;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `L2Z15` ()   BEGIN
    SELECT Klasy.Symbol, Nauczyciele.Nazwisko, Nauczyciele.Imie, Nauczyciele.IdN
    FROM Klasy
    JOIN Nauczyciele ON Klasy.Wych = Nauczyciele.IdN
    ORDER BY Klasy.Symbol;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `L2Z16` ()   BEGIN
    SELECT Nauczyciele.IdN, Nauczyciele.Nazwisko, Nauczyciele.Imie, Nauczyciele.Pensum, SUM(Uczy.IleGodz) AS ZajeciaTygodniowo
    FROM Nauczyciele
    LEFT JOIN Uczy ON Nauczyciele.IdN = Uczy.IdN
    GROUP BY Nauczyciele.IdN, Nauczyciele.Nazwisko, Nauczyciele.Imie, Nauczyciele.Pensum
    ORDER BY ZajeciaTygodniowo DESC, Nauczyciele.Nazwisko, Nauczyciele.Imie, Nauczyciele.Pensum;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `L2Z20` ()   BEGIN
    SELECT Uczniowie.IdU, Uczniowie.Nazwisko, Uczniowie.Imie, COUNT(Oceny.IdP)
    FROM Uczniowie
    LEFT JOIN Oceny ON Uczniowie.IdU = Oceny.IdU
    GROUP BY Uczniowie.IdU, Uczniowie.Nazwisko, Uczniowie.Imie;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `L2Z21` ()   BEGIN
    SELECT Uczniowie.KlasaU,
    SUM(CASE WHEN Uczniowie.Plec = 'M' THEN 1 ELSE 0 END) AS chlopcy,
    SUM(CASE WHEN Uczniowie.Plec = 'K' THEN 1 ELSE 0 END) AS dziewczynki
    FROM Uczniowie
    GROUP BY Uczniowie.KlasaU
    ORDER BY Uczniowie.KlasaU;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `L2Z28` ()   BEGIN
    SELECT U1.Imie, U1.Nazwisko, U1.IdU, U2.Imie, U2.Nazwisko, U2.IdU
    FROM Uczniowie U1
    JOIN Uczniowie U2 ON U1.Imie = U2.Imie
    WHERE U1.IdU < U2.IdU;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `L2Z35` ()   BEGIN
    SELECT Nauczyciele.Nazwisko, Nauczyciele.Imie, Nauczyciele.IdN, Nauczyciele.DUr FROM Nauczyciele
    UNION ALL
    SELECT Uczniowie.Nazwisko, Uczniowie.Imie, Uczniowie.IdU, Uczniowie.DUr FROM Uczniowie
    ORDER BY DUr DESC, Nazwisko, Imie;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Klasy`
--

CREATE TABLE `Klasy` (
  `Symbol` varchar(6) NOT NULL,
  `Profil` varchar(30) NOT NULL,
  `Wych` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `Klasy`
--

INSERT INTO `Klasy` (`Symbol`, `Profil`, `Wych`) VALUES
('Ia', 'biol-chem', 2),
('Ib', 'mat-inf', 1),
('Ic', 'mat-geo', 4),
('Id', 'hist-pol', 3),
('IIa', 'biol-chem', 2),
('IIb', 'mat-inf', NULL),
('IIc', 'wos-geo', NULL),
('IIIa', 'biol-chem', 15),
('IIIb', 'mat-inf', 18),
('IIIc', 'wos-geo', NULL),
('IVa', 'biol-chem', 19),
('IVb', 'mat-inf', 1),
('IVc', 'wos-geo', NULL),
('IVd', 'mat-fiz', 3);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Miasta`
--

CREATE TABLE `Miasta` (
  `IdM` int(11) NOT NULL,
  `NazwaM` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `Miasta`
--

INSERT INTO `Miasta` (`IdM`, `NazwaM`) VALUES
(1, 'Wrocław'),
(2, 'Gdańsk'),
(3, 'Poznań'),
(4, 'Warszawa'),
(5, 'Żywiec'),
(6, 'Trzebnica'),
(7, 'Oława'),
(8, 'Katowice'),
(9, 'Legnica'),
(10, 'Wałbrzych'),
(11, 'Uraz'),
(12, 'Kąty Wrocławskie'),
(13, 'Piła');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Nauczyciele`
--

CREATE TABLE `Nauczyciele` (
  `IdN` int(11) NOT NULL,
  `Nazwisko` varchar(30) NOT NULL,
  `Imie` varchar(30) NOT NULL,
  `DZatr` date DEFAULT curdate(),
  `DUr` date DEFAULT NULL,
  `Plec` varchar(1) DEFAULT NULL,
  `Pensja` decimal(10,2) DEFAULT 0.00,
  `Pensum` int(11) DEFAULT 0,
  `Telefon` varchar(15) DEFAULT NULL,
  `Premia` decimal(10,2) DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `Nauczyciele`
--

INSERT INTO `Nauczyciele` (`IdN`, `Nazwisko`, `Imie`, `DZatr`, `DUr`, `Plec`, `Pensja`, `Pensum`, `Telefon`, `Premia`) VALUES
(1, 'NOWAK', 'Kamil', '2001-11-23', '1970-07-13', 'M', 9570.00, 690, '+48 667 212 429', NULL),
(2, 'ŚLIMAK', 'Patryk', '2025-12-04', '1989-11-18', 'M', 770.00, 720, '+48 121 313 321', NULL),
(3, 'KOWALSKA', 'Zofia', '1967-11-13', '1944-11-14', 'K', 9900.00, 720, '+48 188 909 563', NULL),
(4, 'PIEROGOWA-KOWALSKA', 'Łucja', '2021-05-24', '1992-12-31', 'K', 6930.00, 690, '+48 320 399 343', NULL),
(7, 'WĘGRZYN', 'Michał', '1988-12-17', '1970-12-17', 'M', NULL, NULL, '', 40.12),
(11, 'MICHALSKI', 'Dariusz', '2025-07-17', NULL, 'M', 7700.00, 690, '+48 988 432 422', 0.00),
(12, 'MICHALSKI', 'Dariusz', NULL, NULL, NULL, NULL, 690, NULL, 300.00),
(13, 'MICHALSKI', 'Tomasz', '2025-11-11', '1960-01-01', 'M', NULL, 690, NULL, 0.00),
(14, 'NOWAK', 'Kamila', NULL, '1956-07-18', 'K', 9900.00, 690, '+48 516 511 651', 0.00),
(15, 'LEW', 'Aleksandra', '2024-08-22', NULL, 'K', 9130.00, 690, NULL, 0.00),
(16, 'KABACKI', 'Piotr', NULL, '2001-10-13', NULL, 7920.00, 690, '+48 516 516 586', 0.00),
(17, 'PODRECKI', 'Stanisław', '2025-12-25', NULL, 'M', 9130.00, 690, '+48 654 846 152', 0.00),
(18, 'GRZEGORZEWSKI', 'Grzegorz', '2026-04-01', NULL, 'M', 6380.00, 600, '+48 554 168 165', 0.00),
(19, 'ABACKI', 'Piotr', NULL, '1977-04-11', 'M', 9790.00, 840, NULL, 0.00),
(20, 'WOLAŃSKA-ABACKA', 'Zofia', '2026-03-18', '1984-08-13', 'K', 3.00, 690, '', 0.00),
(21, 'MIŁOSZEWSKA', 'Hanna', '2026-03-18', NULL, NULL, 10230.00, 930, '+48 551 385 416', 0.00),
(22, 'MOLENDA', 'Wiktoria', '2024-04-01', '2000-12-31', 'K', 7370.00, 720, NULL, 0.00),
(23, 'GUZEK', 'Aleksandra', '2025-03-01', NULL, 'K', 10.00, 0, '+48 891 955 916', 10.00),
(24, 'WIELKOPOSLKI', 'Filip', '2026-03-03', NULL, 'M', NULL, NULL, NULL, 999.99),
(25, 'LINDA', 'Bogusław', NULL, '1995-09-28', NULL, 10561.08, 690, NULL, 0.00),
(26, 'KOŁDUN', 'Maciej', '2026-04-06', '1944-12-16', 'M', 10999.98, 690, '', 50.10);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Oceny`
--

CREATE TABLE `Oceny` (
  `IdU` int(11) NOT NULL,
  `IdP` int(11) NOT NULL,
  `Ocena` decimal(3,2) NOT NULL,
  `Data` date DEFAULT curdate()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `Oceny`
--

INSERT INTO `Oceny` (`IdU`, `IdP`, `Ocena`, `Data`) VALUES
(2, 11, 1.00, '2026-02-19'),
(6, 5, 4.50, '2026-02-19'),
(10, 5, 2.50, '2026-02-19'),
(12, 7, 5.00, '2025-12-12'),
(12, 10, 1.00, '2026-02-09'),
(12, 12, 5.50, '2026-02-19'),
(18, 1, 4.50, '2026-02-19'),
(19, 2, 3.00, '2026-02-16'),
(20, 3, 3.00, '2026-02-19'),
(20, 10, 4.50, '2026-02-19'),
(20, 12, 6.00, '2026-02-18'),
(21, 11, 1.00, '2026-02-19'),
(25, 3, 4.00, '2026-02-19'),
(27, 2, 4.50, '2026-02-17'),
(29, 3, 5.50, '2026-02-19'),
(31, 10, 3.00, '2026-02-19'),
(32, 7, 6.00, '2026-02-09'),
(37, 12, 3.50, '2026-02-12'),
(42, 1, 2.50, '2026-02-02'),
(43, 9, 4.00, '2026-02-16'),
(44, 1, 4.00, '2026-04-04'),
(45, 1, 1.00, '2026-03-11'),
(48, 11, 2.50, '2026-02-16'),
(53, 2, 1.50, '2026-02-19'),
(54, 1, 2.50, '2026-02-13'),
(55, 11, 6.00, '2026-02-19'),
(57, 8, 2.50, '2026-02-13'),
(58, 2, 1.00, '2026-02-19'),
(59, 2, 6.00, '2026-02-09'),
(63, 1, 4.00, '2026-02-03'),
(63, 2, 5.00, '2026-02-06'),
(63, 3, 3.00, '2026-02-19'),
(63, 4, 4.00, '2026-02-19'),
(63, 5, 4.00, '2026-02-19'),
(63, 6, 4.00, '2026-02-06'),
(63, 7, 4.00, '2026-02-19'),
(63, 8, 2.00, '2026-02-16'),
(63, 9, 5.00, '2026-02-16'),
(63, 10, 3.00, '2026-02-17'),
(63, 11, 6.00, '2026-02-19'),
(63, 12, 4.00, '2026-02-18'),
(64, 4, 3.50, '2026-02-13');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Przedmioty`
--

CREATE TABLE `Przedmioty` (
  `IdP` int(11) NOT NULL,
  `NazwaP` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `Przedmioty`
--

INSERT INTO `Przedmioty` (`IdP`, `NazwaP`) VALUES
(1, 'matematyka'),
(2, 'fizyka'),
(3, 'informatyka'),
(4, 'biologia'),
(5, 'geografia'),
(6, 'wiedza o społeczeństwie'),
(7, 'podstawy przedsiębiorczości'),
(8, 'język polski'),
(9, 'język angielski'),
(10, 'wychowanie fizyczne'),
(11, 'chemia'),
(12, 'historia');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Uczniowie`
--

CREATE TABLE `Uczniowie` (
  `IdU` int(11) NOT NULL,
  `Nazwisko` varchar(30) NOT NULL,
  `Imie` varchar(30) NOT NULL,
  `DUr` date DEFAULT NULL,
  `Plec` varchar(1) DEFAULT NULL,
  `KlasaU` varchar(6) DEFAULT NULL,
  `Miasto` int(11) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `Uczniowie`
--

INSERT INTO `Uczniowie` (`IdU`, `Nazwisko`, `Imie`, `DUr`, `Plec`, `KlasaU`, `Miasto`, `Email`) VALUES
(1, 'Nowak', 'Jan', NULL, 'M', NULL, 1, NULL),
(2, 'Grzegorzewski', 'Wojciech', '2010-12-02', 'M', 'IIa', 1, 'wgrzegorzewski@gmail.com'),
(3, 'Kowalska', 'Klaudia', '2009-06-22', 'K', 'IIIc', 1, NULL),
(5, 'Kowalski', 'Piotr', NULL, 'M', 'IIa', 1, NULL),
(6, 'Wójcik', 'Michał', '2009-03-15', 'M', 'IIIb', 1, 'wojcikmich@gmail.com'),
(7, 'Kamińska', 'Zofia', '2010-11-04', 'K', NULL, NULL, NULL),
(8, 'Lewandowski', 'Kacper', '2008-01-22', 'M', 'IVb', 3, 'kacpere2244@onet.pl'),
(9, 'Zielińska', 'Maja', '2011-08-10', 'K', 'Ia', 8, 'maaaja@gmail.com'),
(10, 'Szymański', 'Jakub', '2009-05-05', 'M', 'IIIb', 1, NULL),
(11, 'Dąbrowska', 'Julia', '2007-12-18', 'K', 'IVb', NULL, NULL),
(12, 'Kozłowski', 'Filip', '2010-09-29', 'M', 'IIc', 12, NULL),
(13, 'Mazur', 'Oliwia', '2008-02-11', 'K', 'IVa', 11, NULL),
(14, 'Krawczyk', 'Szymon', '2011-07-07', 'M', 'Id', 10, 'oliwiam@gmail.com'),
(15, 'Kaczmarek', 'Alicja', '2009-04-24', 'K', 'IIIa', 5, NULL),
(16, 'Zając', 'Antoni', '2007-10-13', 'M', 'IVa', 6, NULL),
(17, 'Król', 'Lena', '2010-06-02', 'K', 'IIc', 1, 'krolena@wp.pl'),
(18, 'Wiśniewski', 'Aleksander', '2008-11-19', 'M', 'IVc', 4, NULL),
(19, 'Włodarczyk', 'Maria', '2011-01-25', 'K', 'Id', NULL, NULL),
(20, 'Stefański', 'Mikołaj', '2009-08-08', 'M', 'IIa', 1, NULL),
(21, 'Nowakowska', 'Amelia', '2008-03-14', 'K', 'Id', 1, NULL),
(22, 'Pawlak', 'Wiktor', '2010-05-30', 'M', 'IIIa', 1, NULL),
(23, 'Sikora', 'Wiktoria', '2007-09-17', 'K', 'Ic', 1, NULL),
(24, 'Tomaszewski', 'Igor', '2011-12-01', 'M', 'IIb', NULL, NULL),
(25, 'Pietrzak', 'Aleksandra', '2009-02-21', 'K', 'IIIc', 1, 'alekspietrz@o2.pl'),
(26, 'Marciniak', 'Maciej', '2008-06-09', 'M', 'IVa', 6, NULL),
(27, 'Mazur', 'Oliwia', '2010-10-16', 'K', 'IIIb', 3, NULL),
(28, 'Kołodziej', 'Dawid', '2007-04-28', 'M', 'IVc', 9, 'dawkol@gmail.com'),
(29, 'Michalak', 'Emilia', '2011-11-05', 'K', 'IIb', 7, NULL),
(30, 'Mazur', 'Mateusz', '2009-01-12', 'M', 'IIIc', 6, NULL),
(31, 'Dudzińska', 'Antonina', '2008-08-23', 'K', NULL, 12, NULL),
(32, 'Adamczyk', 'Bartosz', '2010-03-06', 'M', NULL, 3, NULL),
(33, 'Jaworska', 'Iga', '2007-07-19', 'K', 'IVa', 7, 'jaworskai@outlook.com'),
(34, 'Górski', 'Oskar', '2011-12-27', 'M', 'Id', 1, NULL),
(35, 'Rutkowska', 'Pola', '2009-05-10', 'K', 'IIIb', 1, NULL),
(36, 'Maciejewski', 'Tomasz', '2008-09-14', 'M', 'IVb', 1, 'mactom@proton.me'),
(37, 'Szczepańska', 'Liliana', '2010-02-03', 'K', 'IIIa', 1, NULL),
(38, 'Walczak', 'Krzysztof', '2007-10-20', 'M', 'IVc', 1, NULL),
(39, 'Baran', 'Nadia', '2011-06-08', 'K', 'Ic', 1, NULL),
(40, 'Chmiel', 'Kamil', '2009-11-11', 'M', 'IIIa', 1, 'chmielkam@gmail.com'),
(41, 'Sikorska', 'Marcelina', '2008-04-25', 'K', 'IVb', 1, NULL),
(42, 'Lis', 'Piotr', '2010-09-02', 'M', 'IIa', 1, 'piotrlis@gmail.com'),
(43, 'Zalewska', 'Weronika', '2007-01-17', 'K', 'IVa', 1, 'werazalewska@outlook.com'),
(44, 'Ostrowski', 'Paweł', '2011-07-29', NULL, 'Id', 5, NULL),
(45, 'Kowalik', 'Agata', '2009-12-04', 'K', 'IIIa', 8, NULL),
(46, 'Cieślak', 'Marcin', '2008-05-15', 'M', 'IVa', NULL, NULL),
(47, 'Wróblewska', 'Karolina', '2010-02-22', 'K', 'IIa', 6, NULL),
(48, 'Borkowski', 'Andrzej', '2007-10-07', 'M', 'IVb', 1, NULL),
(49, 'Zawadzka', 'Magdalena', '2011-06-13', 'K', 'Ic', 4, NULL),
(50, 'Szewczyk', 'Krystian', '2009-01-26', 'M', 'IIb', 9, NULL),
(51, 'Makała', 'Anna', '2008-08-09', 'K', 'IVa', 1, NULL),
(52, 'Urbański', 'Patryk', '2010-03-18', 'M', 'IIb', 1, NULL),
(53, 'Kurek', 'Laura', '2007-11-01', 'K', 'IVc', 1, NULL),
(54, 'Szydłowski', 'Radosław', '2011-05-24', 'M', 'Ia', 1, NULL),
(55, 'Bąk', 'Milena', '2009-09-12', 'K', 'IVa', 1, NULL),
(56, 'Kubiak', 'Sebastian', '2008-02-05', 'M', 'IVc', 1, NULL),
(57, 'Wilk', 'Kornelia', '2010-10-28', 'K', 'IIc', 1, 'kornelkawilk@gmail.com'),
(58, 'Czarniecki', 'Damian', '2007-07-03', 'M', 'IIIc', 1, NULL),
(59, 'Michalska', 'Nina', '2011-12-16', 'K', 'Ic', NULL, NULL),
(60, 'Maciąg', 'Kacper', '2009-04-21', 'M', 'IVb', NULL, 'maciagkac@gmail.com'),
(61, 'Sobkowiak', 'Zuzanna', '2008-01-08', 'K', 'IVb', NULL, NULL),
(62, 'Laskowski', 'Jan', '2010-08-19', 'M', 'IIb', 1, 'jlaskowski@wp.pl'),
(63, 'Krajewska', 'Ewa', '2007-03-27', 'K', 'IVc', NULL, 'kewa@outlook.com'),
(64, 'Czajka', 'Dominik', '2011-11-14', NULL, 'Ia', NULL, NULL),
(65, 'Domańska', 'Klaudia', '2009-06-02', 'K', 'IVb', 1, NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Uczy`
--

CREATE TABLE `Uczy` (
  `IdN` int(11) NOT NULL,
  `IdP` int(11) NOT NULL,
  `IleGodz` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `Uczy`
--

INSERT INTO `Uczy` (`IdN`, `IdP`, `IleGodz`) VALUES
(1, 8, 9),
(2, 1, 8),
(3, 12, 18),
(4, 2, 10),
(4, 3, 18),
(7, 1, 18),
(11, 4, 28),
(12, 4, 29),
(13, 8, 18),
(14, 3, 18),
(15, 3, 5),
(15, 7, 5),
(15, 10, 22),
(17, 1, 2),
(17, 2, 3),
(17, 3, 4),
(17, 4, 2),
(17, 5, 5),
(17, 6, 3),
(17, 7, 3),
(17, 8, 2),
(17, 9, 1),
(17, 10, 2),
(17, 11, 1),
(17, 12, 3),
(18, 10, 18),
(19, 9, 12),
(19, 10, 18),
(20, 1, 18),
(21, 2, 18),
(21, 11, 18),
(22, 2, 18),
(22, 8, 18),
(23, 4, 24),
(23, 11, 11),
(24, 12, 18),
(25, 6, 18),
(25, 8, 10),
(25, 12, 6);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `Klasy`
--
ALTER TABLE `Klasy`
  ADD PRIMARY KEY (`Symbol`),
  ADD KEY `Wych` (`Wych`);

--
-- Indeksy dla tabeli `Miasta`
--
ALTER TABLE `Miasta`
  ADD PRIMARY KEY (`IdM`);

--
-- Indeksy dla tabeli `Nauczyciele`
--
ALTER TABLE `Nauczyciele`
  ADD PRIMARY KEY (`IdN`);

--
-- Indeksy dla tabeli `Oceny`
--
ALTER TABLE `Oceny`
  ADD KEY `IdU` (`IdU`),
  ADD KEY `IdP` (`IdP`);

--
-- Indeksy dla tabeli `Przedmioty`
--
ALTER TABLE `Przedmioty`
  ADD PRIMARY KEY (`IdP`);

--
-- Indeksy dla tabeli `Uczniowie`
--
ALTER TABLE `Uczniowie`
  ADD PRIMARY KEY (`IdU`),
  ADD KEY `KlasaU` (`KlasaU`),
  ADD KEY `Miasto` (`Miasto`);

--
-- Indeksy dla tabeli `Uczy`
--
ALTER TABLE `Uczy`
  ADD PRIMARY KEY (`IdN`,`IdP`),
  ADD KEY `IdP` (`IdP`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Miasta`
--
ALTER TABLE `Miasta`
  MODIFY `IdM` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `Nauczyciele`
--
ALTER TABLE `Nauczyciele`
  MODIFY `IdN` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `Przedmioty`
--
ALTER TABLE `Przedmioty`
  MODIFY `IdP` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `Uczniowie`
--
ALTER TABLE `Uczniowie`
  MODIFY `IdU` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=66;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Klasy`
--
ALTER TABLE `Klasy`
  ADD CONSTRAINT `Klasy_ibfk_1` FOREIGN KEY (`Wych`) REFERENCES `Nauczyciele` (`IdN`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `Oceny`
--
ALTER TABLE `Oceny`
  ADD CONSTRAINT `Oceny_ibfk_1` FOREIGN KEY (`IdU`) REFERENCES `Uczniowie` (`IdU`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Oceny_ibfk_2` FOREIGN KEY (`IdP`) REFERENCES `Przedmioty` (`IdP`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `Uczniowie`
--
ALTER TABLE `Uczniowie`
  ADD CONSTRAINT `Uczniowie_ibfk_1` FOREIGN KEY (`KlasaU`) REFERENCES `Klasy` (`Symbol`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `Uczniowie_ibfk_2` FOREIGN KEY (`Miasto`) REFERENCES `Miasta` (`IdM`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `Uczy`
--
ALTER TABLE `Uczy`
  ADD CONSTRAINT `Uczy_ibfk_1` FOREIGN KEY (`IdN`) REFERENCES `Nauczyciele` (`IdN`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Uczy_ibfk_2` FOREIGN KEY (`IdP`) REFERENCES `Przedmioty` (`IdP`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
