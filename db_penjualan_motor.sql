-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 06, 2022 at 11:54 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 7.4.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_penjualan_motor`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete__CashPurchase` (IN `key1` VARCHAR(7))  BEGIN
DELETE FROM t_belicash WHERE kd_belicash = key1;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `delete__CreditPurchase` (IN `key1` VARCHAR(7))  BEGIN
DELETE FROM t_belikredit WHERE kd_belikredit = key1;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `delete__Customer` (IN `key1` VARCHAR(5))  BEGIN
DELETE FROM t_pelanggan WHERE kd_pelanggan = key1;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `delete__Employee` (IN `key1` VARCHAR(5))  BEGIN
DELETE FROM t_karyawan WHERE kd_karyawan = key1;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `delete__Motor` (IN `key1` VARCHAR(5))  BEGIN
DELETE FROM t_motor WHERE kd_motor=key1;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `delete__User` (IN `key1` VARCHAR(5))  BEGIN
DELETE FROM t_user WHERE kd_user = key1;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insert__Angsuran` (IN `key1` VARCHAR(15), IN `key2` INT(11), IN `key3` INT(11), IN `key4` INT(11), IN `key5` INT(11))  BEGIN
INSERT INTO t_angsuran(tanggal, kd_beli, cicilan, jumlah, bayar, kembalian) VALUES (NOW(), key1, key2, key3, key4, key5);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insert__CashPurchase` (IN `key1` VARCHAR(15), IN `key2` VARCHAR(5), IN `key3` VARCHAR(5), IN `key4` INT(11), IN `key5` INT(11))  BEGIN
	INSERT INTO t_beli (kd_beli, tanggal, kd_user, kd_pelanggan, tipe, diskon, pajak, bunga, uang_muka, lama_cicilan, angsuran, keterangan)
    VALUES (key1, NOW(), key2, key3, 'TUNAI', key4, key5, NULL, NULL, NULL, NULL, 'BELUM LUNAS');
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insert__CreditPurchase` (IN `key1` VARCHAR(15), IN `key2` VARCHAR(5), IN `key3` VARCHAR(5), IN `key4` INT(11), IN `key5` INT(11), IN `key6` INT(11), IN `key7` INT(11))  BEGIN
INSERT INTO t_beli VALUES (key1, NOW(), key2, key3, 'KREDIT', key4, key5, key6, NULL, key7, NULL, 'BELUM LUNAS');
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insert__Customer` (IN `key1` VARCHAR(5), IN `key2` VARCHAR(16), IN `key3` VARCHAR(50), IN `key4` ENUM('Laki-Laki','Perempuan'), IN `key5` VARCHAR(50), IN `key6` VARCHAR(10), IN `key7` VARCHAR(12), IN `key8` VARCHAR(50), IN `key9` ENUM('AKTIF','TIDAK AKTIF'))  BEGIN
INSERT INTO t_pelanggan VALUES (key1, key2, key3, key4, key5, key6, key7, key8, key9, NOW(), NULL, NULL);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insert__DetailPurchase` (IN `key1` VARCHAR(15), IN `key2` VARCHAR(5), IN `key3` INT(11), IN `key4` INT(11), IN `key5` INT(11))  BEGIN
INSERT INTO t_bayar (tanggal, kd_beli, kd_motor, qty, total, bayar, kembalian, sisa) VALUES (NOW(), key1, key2, key3, key4, NULL, NULL, key5);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insert__Employee` (IN `key1` VARCHAR(5), IN `key2` VARCHAR(16), IN `key3` VARCHAR(50), IN `key4` ENUM('Laki-Laki','Perempuan'), IN `key5` VARCHAR(50), IN `key6` VARCHAR(10), IN `key7` VARCHAR(12), IN `key8` VARCHAR(50), IN `key9` ENUM('AKTIF','TIDAK AKTIF'))  BEGIN
INSERT INTO t_karyawan VALUES (key1, key2, key3, key4, key5, key6, key7, key8, key9, NOW(), NULL, NULL);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insert__Message` (IN `key1` VARCHAR(5), IN `key2` VARCHAR(5), IN `key3` VARCHAR(255), IN `key4` VARCHAR(255))  BEGIN
INSERT INTO t_message(sender, receiver, subject, message, status, date) VALUES (key1, key2, key3, key4, 'UNREAD', NOW());
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insert__Motor` (IN `key1` VARCHAR(5), IN `key2` VARCHAR(20), IN `key3` VARCHAR(20), IN `key4` VARCHAR(20), IN `key5` INT(11), IN `key6` VARCHAR(20), IN `key7` INT, IN `key8` VARCHAR(50), IN `key9` ENUM('AKTIF','TIDAK AKTIF'))  BEGIN
	INSERT INTO t_motor VALUES (key1, key2, key3, key4, key5, key6, key7, key8, key9, NOW(), NULL, NULL);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insert__user` (IN `key1` VARCHAR(5), IN `key2` VARCHAR(255), IN `key3` VARCHAR(255), IN `key4` VARCHAR(255), IN `key5` VARCHAR(300), IN `key6` ENUM('ADMIN','GUEST'), IN `key7` ENUM('AKTIF','TIDAK AKTIF'))  BEGIN
INSERT INTO t_user VALUES (key1, key2, key3, key4, key5, key6, NULL, key7, NOW(), NULL, NULL);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `update__CashStatus` (IN `key1` VARCHAR(15))  BEGIN
UPDATE t_beli SET keterangan = 'LUNAS' WHERE kd_beli = key1;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `update__CreditStatus` (IN `key1` VARCHAR(15), IN `key2` INT(11), IN `key3` INT(11), IN `key4` ENUM('LUNAS','BELUM LUNAS'))  BEGIN
UPDATE t_beli SET uang_muka = key2, angsuran = key3, keterangan = key4 WHERE kd_beli = key1;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `update__Customer` (IN `key1` VARCHAR(5), IN `key2` VARCHAR(16), IN `key3` VARCHAR(50), IN `key4` ENUM('Laki-Laki','Perempuan'), IN `key5` VARCHAR(50), IN `key6` VARCHAR(10), IN `key7` VARCHAR(12), IN `key8` VARCHAR(50), IN `key9` ENUM('AKTIF','TIDAK AKTIF'))  BEGIN
UPDATE t_pelanggan SET nik = key2, nm_pelanggan = key3, jns_kelamin = key4, almt_pelanggan = key5, no_kk = key6, no_hp = key7, keterangan = key8, status = key9, updated_at = NOW() WHERE kd_pelanggan = key1;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `update__DetailPurchase` (IN `key1` VARCHAR(15), IN `key2` INT(11), IN `key3` INT(11), IN `key4` INT(11))  BEGIN
UPDATE t_bayar SET bayar = key2, kembalian = key3, sisa = key4 WHERE kd_bayar = key1;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `update__Employee` (IN `key1` VARCHAR(5), IN `key2` VARCHAR(16), IN `key3` VARCHAR(50), IN `key4` ENUM('Laki-Laki','Perempuan'), IN `key5` VARCHAR(50), IN `key6` VARCHAR(10), IN `key7` VARCHAR(12), IN `key8` VARCHAR(50), IN `key9` ENUM('AKTIF','TIDAK AKTIF'))  BEGIN
UPDATE t_karyawan SET nik = key2, nm_karyawan = key3, jns_kelamin = key4, almt_karyawan = key5, no_kk = key6, no_hp = key7, keterangan = key8, status = key9, updated_at = NOW() WHERE kd_karyawan = key1;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `update__Motor` (IN `key1` VARCHAR(5), IN `key2` VARCHAR(20), IN `key3` VARCHAR(20), IN `key4` VARCHAR(20), IN `key5` INT(11), IN `key6` VARCHAR(20), IN `key7` INT(11), IN `key8` VARCHAR(50), IN `key9` ENUM('AKTIF','TIDAK AKTIF'))  BEGIN
UPDATE t_motor SET jenis = key2, type = key3, warna = key4, harga = key5, gambar = key6, stok = key7, keterangan = key8, status = key9, updated_at = NOW() WHERE kd_motor = key1;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `update__User` (IN `key1` VARCHAR(5), IN `key2` VARCHAR(255), IN `key3` VARCHAR(255), IN `key4` VARCHAR(255), IN `key5` VARCHAR(300), IN `key6` ENUM('ADMIN','GUEST'), IN `key7` ENUM('AKTIF','TIDAK AKTIF'))  BEGIN
UPDATE t_user SET name = key2, username = key3, email = key4, password = key5, role = key6, status = key7 WHERE kd_user = key1;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `log__belicash`
--

CREATE TABLE `log__belicash` (
  `id` bigint(20) NOT NULL,
  `action` enum('INSERT','UPDATE','DELETE') NOT NULL,
  `kd_belicash` varchar(7) NOT NULL,
  `tgl_beli` date NOT NULL,
  `kd_pelanggan` varchar(5) NOT NULL,
  `kd_motor` varchar(5) NOT NULL,
  `total` int(11) NOT NULL,
  `bayar` int(11) NOT NULL,
  `kembalian` int(11) NOT NULL,
  `keterangan` enum('LUNAS') NOT NULL,
  `status` enum('AKTIF','TIDAK AKTIF') NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `log__belicash`
--

INSERT INTO `log__belicash` (`id`, `action`, `kd_belicash`, `tgl_beli`, `kd_pelanggan`, `kd_motor`, `total`, `bayar`, `kembalian`, `keterangan`, `status`, `created_at`) VALUES
(1, 'INSERT', 'PT00004', '2022-01-26', 'P0003', 'M0003', 18000000, 18000000, 0, 'LUNAS', 'AKTIF', '2022-01-25 22:35:46'),
(2, 'UPDATE', 'PT00004', '2022-01-26', 'P0002', 'M0003', 18000000, 18000000, 0, 'LUNAS', 'AKTIF', '2022-01-25 22:36:20'),
(3, 'UPDATE', 'PT00004', '2022-01-26', 'P0002', 'M0003', 18000000, 18000000, 0, 'LUNAS', 'TIDAK AKTIF', '2022-01-25 22:36:35'),
(4, 'DELETE', 'PT00004', '2022-01-26', 'P0002', 'M0003', 18000000, 18000000, 0, 'LUNAS', 'TIDAK AKTIF', '2022-01-25 22:36:57');

-- --------------------------------------------------------

--
-- Table structure for table `log__belikredit`
--

CREATE TABLE `log__belikredit` (
  `id` bigint(20) NOT NULL,
  `action` enum('INSERT','UPDATE','DELETE') NOT NULL,
  `kd_belikredit` varchar(7) NOT NULL,
  `tgl_beli` date NOT NULL,
  `kd_pelanggan` varchar(7) NOT NULL,
  `kd_motor` varchar(7) NOT NULL,
  `uang_muka` int(11) NOT NULL,
  `lama_cicilan` int(11) NOT NULL,
  `angsuran` int(11) NOT NULL,
  `sisa` int(11) NOT NULL,
  `keterangan` enum('BELUM LUNAS','LUNAS') NOT NULL,
  `status` enum('AKTIF','TIDAK AKTIF') NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `log__belikredit`
--

INSERT INTO `log__belikredit` (`id`, `action`, `kd_belikredit`, `tgl_beli`, `kd_pelanggan`, `kd_motor`, `uang_muka`, `lama_cicilan`, `angsuran`, `sisa`, `keterangan`, `status`, `created_at`) VALUES
(1, 'INSERT', 'PK00004', '2022-01-26', 'P0003', 'M0002', 1000000, 12, 1166666, 14000000, 'BELUM LUNAS', 'AKTIF', '2022-01-25 22:37:37'),
(2, 'UPDATE', 'PK00004', '2022-01-26', 'P0001', 'M0002', 1000000, 12, 1166666, 14000000, 'BELUM LUNAS', 'AKTIF', '2022-01-25 22:38:07'),
(3, 'UPDATE', 'PK00004', '2022-01-26', 'P0001', 'M0002', 1000000, 12, 1166666, 14000000, 'BELUM LUNAS', 'TIDAK AKTIF', '2022-01-25 22:38:19'),
(4, 'DELETE', 'PK00004', '2022-01-26', 'P0001', 'M0002', 1000000, 12, 1166666, 14000000, 'BELUM LUNAS', 'TIDAK AKTIF', '2022-01-25 22:38:32');

-- --------------------------------------------------------

--
-- Table structure for table `log__karyawan`
--

CREATE TABLE `log__karyawan` (
  `id` bigint(20) NOT NULL,
  `action` enum('INSERT','UPDATE','DELETE') NOT NULL,
  `kd_karyawan` varchar(5) NOT NULL,
  `nik` varchar(16) NOT NULL,
  `nm_karyawan` varchar(50) NOT NULL,
  `jns_kelamin` enum('Laki-Laki','Perempuan') NOT NULL,
  `almt_karyawan` varchar(50) NOT NULL,
  `no_kk` varchar(10) NOT NULL,
  `no_hp` varchar(12) NOT NULL,
  `keterangan` varchar(50) NOT NULL,
  `status` enum('AKTIF','TIDAK AKTIF') NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `log__karyawan`
--

INSERT INTO `log__karyawan` (`id`, `action`, `kd_karyawan`, `nik`, `nm_karyawan`, `jns_kelamin`, `almt_karyawan`, `no_kk`, `no_hp`, `keterangan`, `status`, `created_at`) VALUES
(1, 'INSERT', 'K0001', '3206725189020001', 'Jaka Tingkir', 'Laki-Laki', 'Jl. Talagasari No. 35 Kawali', '0826261723', '081372618261', 'Karyawan OK', 'AKTIF', '2022-01-29 03:25:12'),
(2, 'INSERT', 'K0002', '3205726255020002', 'Budi Santoso', 'Laki-Laki', 'Jl. Kawalimukti No. 12 Kawali', '0257352146', '087654741110', 'Karyawan OK', 'AKTIF', '2022-01-29 03:25:12'),
(3, 'INSERT', 'K0003', '3206726783740001', 'Iman Saiful', 'Laki-Laki', 'Jl. Jatinagara No. 109', '0863528163', '087267227472', 'Karyawan OK', 'AKTIF', '2022-01-29 04:05:28'),
(4, 'INSERT', 'K0004', '3208726267230001', 'Dewi Melati', 'Perempuan', 'Jl. Winduraja No. 9', '0867263724', '086727236432', 'Karyawan OK', 'AKTIF', '2022-01-29 04:06:24'),
(5, 'INSERT', 'K0005', '3206887743800003', 'Ahmad Fauzi', 'Laki-Laki', 'Jl. Rajadesa No. 9', '0892837282', '081245637890', 'Karyawan OK', 'AKTIF', '2022-01-29 04:07:16'),
(6, 'INSERT', 'K0006', '3208778343880004', 'Muhammad Rizal', 'Laki-Laki', 'Jl. Cibiru No. 6', '0887732737', '088273824543', 'Karyawan OK', 'AKTIF', '2022-01-29 04:08:06'),
(7, 'UPDATE', 'K0006', '3208778343880004', 'Muhammad Rizal', 'Laki-Laki', 'Jl. Cibiru No. 7', '0887732737', '088273824543', 'Karyawan OK', 'AKTIF', '2022-01-29 07:13:19'),
(8, 'UPDATE', 'K0006', '3208778343880004', 'Muhammad Rizal', 'Laki-Laki', 'Jl. Cibiru No. 6', '0887732737', '088273824543', 'Karyawan OK', 'TIDAK AKTIF', '2022-01-29 07:17:49'),
(9, 'DELETE', 'K0006', '3208778343880004', 'Muhammad Rizal', 'Laki-Laki', 'Jl. Cibiru No. 6', '0887732737', '088273824543', 'Karyawan OK', 'TIDAK AKTIF', '2022-01-29 07:19:59'),
(10, 'INSERT', 'K0006', '3201234567891234', 'Ayu', 'Perempuan', 'Kawali, Jawa Barat', '3125412345', '08123456789', 'Karyawan OK', 'AKTIF', '2022-02-01 04:16:41');

-- --------------------------------------------------------

--
-- Table structure for table `log__motor`
--

CREATE TABLE `log__motor` (
  `id` bigint(20) NOT NULL,
  `action` enum('INSERT','UPDATE','DELETE') NOT NULL,
  `kd_motor` varchar(5) NOT NULL,
  `jenis` varchar(20) NOT NULL,
  `type` varchar(20) NOT NULL,
  `warna` varchar(20) NOT NULL,
  `harga` int(11) NOT NULL,
  `gambar` varchar(20) NOT NULL,
  `stok` int(11) NOT NULL,
  `keterangan` varchar(50) NOT NULL,
  `status` enum('AKTIF','TIDAK AKTIF') NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `log__motor`
--

INSERT INTO `log__motor` (`id`, `action`, `kd_motor`, `jenis`, `type`, `warna`, `harga`, `gambar`, `stok`, `keterangan`, `status`, `created_at`) VALUES
(1, 'UPDATE', 'M0001', 'Matic', 'Beat', 'Pink', 13750000, '20220117160716.png', 5, 'OK', 'AKTIF', '2022-01-29 12:09:39'),
(2, 'UPDATE', 'M0002', 'Bebek', 'Supra X 125', 'Hitam', 15000000, '20220117160729.png', 7, 'OK', 'AKTIF', '2022-01-29 12:09:39'),
(3, 'UPDATE', 'M0003', 'Sport', 'Verza', 'Biru', 18000000, '20220117160649.png', 2, 'OK', 'AKTIF', '2022-01-29 12:09:40'),
(4, 'UPDATE', 'M0004', 'Matic', 'Vario', 'Putih', 19000000, '20220117160746.png', 5, 'OK', 'AKTIF', '2022-01-29 12:09:40'),
(5, 'UPDATE', 'M0006', 'Matic', 'Beat', 'Merah', 12000000, '20220125153709.png', 12, 'OK', 'AKTIF', '2022-01-29 12:09:40'),
(6, 'DELETE', 'M0006', 'Matic', 'Beat', 'Merah', 12000000, '20220125153709.png', 12, 'OK', 'AKTIF', '2022-01-30 01:07:56'),
(7, 'INSERT', 'M0005', 'Matic', 'Vario', 'Merah', 1200000, '20220130144132.png', 12, 'OK', 'AKTIF', '2022-01-30 07:41:40'),
(8, 'UPDATE', 'M0005', 'Matic', 'Vario', 'Merah', 1200000, '20220130144132.png', 12, 'OK', 'TIDAK AKTIF', '2022-01-30 07:57:40'),
(9, 'UPDATE', 'M0005', 'Matic', 'Vario', 'Merah', 1200000, '20220130145833.png', 12, 'OK', 'TIDAK AKTIF', '2022-01-30 07:58:34'),
(10, 'UPDATE', 'M0005', 'Matic', 'Vario', 'Merah', 1200000, '20220130150126.png', 12, 'OK', 'TIDAK AKTIF', '2022-01-30 08:01:28'),
(11, 'DELETE', 'M0005', 'Matic', 'Vario', 'Merah', 1200000, '20220130150126.png', 12, 'OK', 'TIDAK AKTIF', '2022-01-30 08:03:19'),
(12, 'UPDATE', 'M0004', 'Matic', 'Vario', 'Putih', 19000000, '20220117160746.png', 5, 'OK', 'TIDAK AKTIF', '2022-01-31 08:42:34'),
(13, 'UPDATE', 'M0002', 'Bebek', 'Supra X 125', 'Hitam', 15000000, '20220117160729.png', 6, 'OK', 'AKTIF', '2022-01-31 08:53:43'),
(14, 'UPDATE', 'M0002', 'Bebek', 'Supra X 125', 'Hitam', 15000000, '20220117160729.png', 5, 'OK', 'AKTIF', '2022-01-31 09:06:24'),
(15, 'UPDATE', 'M0002', 'Bebek', 'Supra X 125', 'Hitam', 15000000, '20220117160729.png', 4, 'OK', 'AKTIF', '2022-01-31 09:42:37'),
(16, 'UPDATE', 'M0002', 'Bebek', 'Supra X 125', 'Hitam', 15000000, '20220117160729.png', 3, 'OK', 'AKTIF', '2022-01-31 09:44:58'),
(17, 'UPDATE', 'M0001', 'Matic', 'Beat', 'Pink', 13750000, '20220117160716.png', 4, 'OK', 'AKTIF', '2022-02-01 04:21:14'),
(18, 'UPDATE', 'M0002', 'Bebek', 'Supra X 125', 'Hitam', 15000000, '20220117160729.png', 2, 'OK', 'AKTIF', '2022-02-01 04:21:39'),
(19, 'UPDATE', 'M0001', 'Matic', 'Beat', 'Pink', 13750000, '20220117160716.png', 3, 'OK', 'AKTIF', '2022-02-01 05:47:00'),
(20, 'UPDATE', 'M0001', 'Matic', 'Beat', 'Pink', 13750000, '20220117160716.png', 2, 'OK', 'AKTIF', '2022-02-01 05:48:40'),
(21, 'UPDATE', 'M0001', 'Matic', 'Beat', 'Pink', 13750000, '20220117160716.png', 1, 'OK', 'AKTIF', '2022-02-01 05:53:32'),
(22, 'UPDATE', 'M0002', 'Bebek', 'Supra X 125', 'Hitam', 15000000, '20220117160729.png', 1, 'OK', 'AKTIF', '2022-02-01 06:00:19'),
(23, 'UPDATE', 'M0003', 'Sport', 'Verza', 'Biru', 18000000, '20220117160649.png', 1, 'OK', 'AKTIF', '2022-02-01 06:19:49'),
(24, 'UPDATE', 'M0001', 'Matic', 'Beat', 'Pink', 13750000, '20220117160716.png', 7, 'OK', 'AKTIF', '2022-02-01 06:26:50'),
(25, 'UPDATE', 'M0002', 'Bebek', 'Supra X 125', 'Hitam', 15000000, '20220117160729.png', 12, 'OK', 'AKTIF', '2022-02-01 06:27:01'),
(26, 'UPDATE', 'M0003', 'Sport', 'Verza', 'Biru', 18000000, '20220117160649.png', 3, 'OK', 'AKTIF', '2022-02-01 06:27:11'),
(27, 'UPDATE', 'M0003', 'Sport', 'Verza', 'Biru', 18000000, '20220117160649.png', 0, 'OK', 'AKTIF', '2022-02-04 00:39:19'),
(28, 'UPDATE', 'M0003', 'Sport', 'Verza', 'Biru', 18000000, '20220117160649.png', 5, 'OK', 'AKTIF', '2022-02-04 02:28:18'),
(29, 'UPDATE', 'M0004', 'Matic', 'Vario', 'Putih', 19000000, '20220117160746.png', 5, 'OK', 'AKTIF', '2022-02-04 23:55:52'),
(30, 'UPDATE', 'M0004', 'Matic', 'Vario', 'Putih', 19000000, '20220117160746.png', 5, 'OK', 'TIDAK AKTIF', '2022-02-05 00:08:35'),
(31, 'UPDATE', 'M0003', 'Sport', 'Verza', 'Biru', 18000000, '20220117160649.png', 4, 'OK', 'AKTIF', '2022-02-05 02:09:52'),
(32, 'UPDATE', 'M0003', 'Sport', 'Verza', 'Biru', 18000000, '20220117160649.png', 3, 'OK', 'AKTIF', '2022-02-05 08:55:02'),
(33, 'UPDATE', 'M0002', 'Bebek', 'Supra X 125', 'Hitam', 15000000, '20220117160729.png', 11, 'OK', 'AKTIF', '2022-02-05 09:25:18'),
(34, 'UPDATE', 'M0002', 'Bebek', 'Supra X 125', 'Hitam', 15000000, '20220117160729.png', 10, 'OK', 'AKTIF', '2022-02-05 09:25:18'),
(35, 'UPDATE', 'M0002', 'Bebek', 'Supra X 125', 'Hitam', 15000000, '20220117160729.png', 9, 'OK', 'AKTIF', '2022-02-05 09:25:18'),
(36, 'UPDATE', 'M0002', 'Bebek', 'Supra X 125', 'Hitam', 15000000, '20220117160729.png', 8, 'OK', 'AKTIF', '2022-02-05 10:37:56'),
(37, 'UPDATE', 'M0001', 'Matic', 'Beat', 'Pink', 13750000, '20220117160716.png', 6, 'OK', 'AKTIF', '2022-02-05 10:37:56');

-- --------------------------------------------------------

--
-- Table structure for table `log__pelanggan`
--

CREATE TABLE `log__pelanggan` (
  `id` bigint(20) NOT NULL,
  `action` enum('INSERT','UPDATE','DELETE') NOT NULL,
  `kd_pelanggan` varchar(5) NOT NULL,
  `nik` varchar(16) NOT NULL,
  `nm_pelanggan` varchar(50) NOT NULL,
  `jns_kelamin` enum('Laki-Laki','Perempuan') NOT NULL,
  `almt_pelanggan` varchar(50) NOT NULL,
  `no_kk` varchar(10) NOT NULL,
  `no_hp` varchar(12) NOT NULL,
  `keterangan` varchar(50) NOT NULL,
  `status` enum('AKTIF','TIDAK AKTIF') NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `log__pelanggan`
--

INSERT INTO `log__pelanggan` (`id`, `action`, `kd_pelanggan`, `nik`, `nm_pelanggan`, `jns_kelamin`, `almt_pelanggan`, `no_kk`, `no_hp`, `keterangan`, `status`, `created_at`) VALUES
(1, 'INSERT', 'P0004', '3207536264400001', 'Reza Rahardian', 'Laki-Laki', 'Jl. Angkasa No.9', '0825341351', '085247137352', 'Pelanggan OK', 'AKTIF', '2022-01-25 10:07:09'),
(2, 'UPDATE', 'P0004', '3207536264400001', 'Reza Rahardian', 'Laki-Laki', 'Jl. Angkasa No.9', '0825341351', '085247137352', 'Pelanggan Baru', 'AKTIF', '2022-01-25 10:08:23'),
(3, 'UPDATE', 'P0004', '3207536264400001', 'Reza Rahardian', 'Laki-Laki', 'Jl. Angkasa No.9', '0825341351', '085247137352', 'Pelanggan Baru', 'TIDAK AKTIF', '2022-01-25 10:08:36'),
(4, 'DELETE', 'P0004', '3207536264400001', 'Reza Rahardian', 'Laki-Laki', 'Jl. Angkasa No.9', '0825341351', '085247137352', 'Pelanggan Baru', 'TIDAK AKTIF', '2022-01-25 10:08:47'),
(5, 'UPDATE', 'P0001', '3207080182050002', 'Dani Pedrosa', 'Laki-Laki', 'Jl. Talagasari No. 12 Kawali', '090910102', '084654741244', 'Pelanggan OK', 'AKTIF', '2022-01-25 13:45:11'),
(6, 'INSERT', 'P0004', '3207536253712890', 'Eddie Brock', 'Laki-Laki', 'Jl. New York No. 9', '0863516351', '086345653727', 'Pelanggan OK', 'AKTIF', '2022-01-30 09:57:30'),
(7, 'UPDATE', 'P0004', '3207536253712890', 'Eddie Brock', 'Laki-Laki', 'Jl. New York No. 9', '0863516323', '086345653727', 'Pelanggan OK', 'AKTIF', '2022-01-30 09:58:33'),
(8, 'UPDATE', 'P0004', '3207536253712890', 'Eddie Brock', 'Laki-Laki', 'Jl. New York No. 9', '0863516323', '086345653727', 'Pelanggan OK', 'TIDAK AKTIF', '2022-01-30 09:59:35'),
(9, 'DELETE', 'P0004', '3207536253712890', 'Eddie Brock', 'Laki-Laki', 'Jl. New York No. 9', '0863516323', '086345653727', 'Pelanggan OK', 'TIDAK AKTIF', '2022-01-30 10:00:07');

-- --------------------------------------------------------

--
-- Table structure for table `log__user`
--

CREATE TABLE `log__user` (
  `id` bigint(20) NOT NULL,
  `action` enum('INSERT','UPDATE','DELETE') NOT NULL,
  `kd_user` varchar(5) NOT NULL,
  `name` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(300) NOT NULL,
  `role` enum('ADMIN','GUEST') NOT NULL,
  `remember_token` varchar(100) DEFAULT NULL,
  `status` enum('AKTIF','TIDAK AKTIF') NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `log__user`
--

INSERT INTO `log__user` (`id`, `action`, `kd_user`, `name`, `username`, `email`, `password`, `role`, `remember_token`, `status`, `created_at`) VALUES
(1, 'INSERT', 'U0004', 'Azlan', 'An2022', 'azlan@gmail.com', '1000:5b3131332c2034312c202d37342c2039302c2037372c202d332c202d3131392c2039352c202d39332c203132342c202d34352c203130302c202d3130372c2038302c2033382c202d37315d:819356793e1bc7156c0f461346c7d7879a028e4040885673cfbe58493c2045e309d707ce0bcde22e409c5bd9e493f434e8f726553dc0253bf3ef8f0da4a3b49d', 'GUEST', NULL, 'AKTIF', '2022-01-30 04:25:16'),
(2, 'INSERT', 'U0005', 'Hadi', 'Hadi21', 'hadi21@gmail.com', '1000:5b3130322c2039332c203130362c202d34392c2036372c2033342c202d39382c202d3131342c202d33302c202d35312c202d31362c202d3132302c2034382c202d3132342c2038372c2034365d:f9dd8a1e7351a5d3f8f154315125c84d28af4ae0f05a3641fd738d9f3c6b24646c8b0fae97e92192d9775d53eb1a5660ea6ba711c28e95fe106befe906225449', 'GUEST', NULL, 'AKTIF', '2022-02-01 06:25:51');

-- --------------------------------------------------------

--
-- Table structure for table `t_angsuran`
--

CREATE TABLE `t_angsuran` (
  `id` bigint(20) NOT NULL,
  `tanggal` timestamp NOT NULL DEFAULT current_timestamp(),
  `kd_beli` varchar(15) NOT NULL,
  `cicilan` int(11) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `bayar` int(11) NOT NULL,
  `kembalian` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `t_bayar`
--

CREATE TABLE `t_bayar` (
  `id` bigint(20) NOT NULL,
  `tanggal` timestamp NOT NULL DEFAULT current_timestamp(),
  `kd_beli` varchar(15) NOT NULL,
  `kd_motor` varchar(5) NOT NULL,
  `qty` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  `bayar` int(11) DEFAULT NULL,
  `kembalian` int(11) DEFAULT NULL,
  `sisa` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_bayar`
--

INSERT INTO `t_bayar` (`id`, `tanggal`, `kd_beli`, `kd_motor`, `qty`, `total`, `bayar`, `kembalian`, `sisa`) VALUES
(1, '2022-02-05 08:55:02', 'PJ2022020500001', 'M0003', 1, 43200000, NULL, NULL, 43200000),
(5, '2022-02-05 10:37:56', 'PJ2022020500002', 'M0002', 1, 28750000, NULL, NULL, 28750000),
(6, '2022-02-05 10:37:56', 'PJ2022020500002', 'M0001', 1, 28750000, NULL, NULL, 28750000);

--
-- Triggers `t_bayar`
--
DELIMITER $$
CREATE TRIGGER `stokKeluar` AFTER INSERT ON `t_bayar` FOR EACH ROW BEGIN
UPDATE t_motor SET stok = stok - NEW.qty WHERE kd_motor = NEW.kd_motor;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `t_beli`
--

CREATE TABLE `t_beli` (
  `id` bigint(20) NOT NULL,
  `kd_beli` varchar(15) NOT NULL,
  `tanggal` timestamp NOT NULL DEFAULT current_timestamp(),
  `kd_user` varchar(5) NOT NULL,
  `kd_pelanggan` varchar(5) NOT NULL,
  `tipe` enum('TUNAI','KREDIT') NOT NULL,
  `diskon` int(11) NOT NULL,
  `pajak` int(11) NOT NULL,
  `bunga` int(11) DEFAULT NULL,
  `uang_muka` int(11) DEFAULT NULL,
  `lama_cicilan` int(11) DEFAULT NULL,
  `angsuran` int(11) DEFAULT NULL,
  `keterangan` enum('LUNAS','BELUM LUNAS') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_beli`
--

INSERT INTO `t_beli` (`id`, `kd_beli`, `tanggal`, `kd_user`, `kd_pelanggan`, `tipe`, `diskon`, `pajak`, `bunga`, `uang_muka`, `lama_cicilan`, `angsuran`, `keterangan`) VALUES
(1, 'PJ2022020500001', '2022-02-05 08:55:02', 'U0001', 'P0001', 'TUNAI', 10, 0, NULL, NULL, NULL, NULL, 'BELUM LUNAS'),
(4, 'PJ2022020500002', '2022-02-05 10:37:56', 'U0001', 'P0001', 'TUNAI', 0, 0, NULL, NULL, NULL, NULL, 'BELUM LUNAS');

-- --------------------------------------------------------

--
-- Table structure for table `t_belicash`
--

CREATE TABLE `t_belicash` (
  `kd_belicash` varchar(7) NOT NULL,
  `tgl_beli` date NOT NULL,
  `kd_pelanggan` varchar(5) NOT NULL,
  `kd_motor` varchar(5) NOT NULL,
  `total` int(11) NOT NULL,
  `bayar` int(11) NOT NULL,
  `kembalian` int(11) NOT NULL,
  `keterangan` enum('LUNAS') NOT NULL,
  `status` enum('AKTIF','TIDAK AKTIF') NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_belicash`
--

INSERT INTO `t_belicash` (`kd_belicash`, `tgl_beli`, `kd_pelanggan`, `kd_motor`, `total`, `bayar`, `kembalian`, `keterangan`, `status`, `created_at`, `updated_at`, `deleted_at`) VALUES
('PT00001', '2021-12-01', 'P0001', 'M0001', 13750000, 13750000, 0, 'LUNAS', 'AKTIF', '2022-01-25 02:49:53', NULL, NULL),
('PT00002', '2022-01-25', 'P0002', 'M0002', 15000000, 15000000, 0, 'LUNAS', 'AKTIF', '2022-01-25 11:26:57', NULL, NULL),
('PT00003', '2022-01-25', 'P0003', 'M0003', 18000000, 18000000, 0, 'LUNAS', 'AKTIF', '2022-01-25 11:27:14', NULL, NULL);

--
-- Triggers `t_belicash`
--
DELIMITER $$
CREATE TRIGGER `deleteBeliCash` AFTER DELETE ON `t_belicash` FOR EACH ROW BEGIN
INSERT INTO log__belicash
(action, kd_belicash, tgl_beli, kd_pelanggan, kd_motor, total, bayar, kembalian, keterangan, status, created_at) 
VALUES ('DELETE', OLD.kd_belicash, OLD.tgl_beli, OLD.kd_pelanggan, OLD.kd_motor, OLD.total, OLD.bayar, OLD.kembalian, OLD.keterangan, OLD.status, NOW());
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `insertBeliCash` AFTER INSERT ON `t_belicash` FOR EACH ROW BEGIN
INSERT INTO log__belicash
(action, kd_belicash, tgl_beli, kd_pelanggan, kd_motor, total, bayar, kembalian, keterangan, status, created_at) 
VALUES ('INSERT', NEW.kd_belicash, NEW.tgl_beli, NEW.kd_pelanggan, NEW.kd_motor, NEW.total, NEW.bayar, NEW.kembalian, NEW.keterangan, NEW.status, NOW());
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `updateBeliCash` AFTER UPDATE ON `t_belicash` FOR EACH ROW BEGIN
INSERT INTO log__belicash
(action, kd_belicash, tgl_beli, kd_pelanggan, kd_motor, total, bayar, kembalian, keterangan, status, created_at) 
VALUES ('UPDATE', OLD.kd_belicash, NEW.tgl_beli, NEW.kd_pelanggan, NEW.kd_motor, NEW.total, NEW.bayar, NEW.kembalian, NEW.keterangan, NEW.status, NOW());
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `t_belikredit`
--

CREATE TABLE `t_belikredit` (
  `kd_belikredit` varchar(7) NOT NULL,
  `tgl_beli` date NOT NULL,
  `kd_pelanggan` varchar(5) NOT NULL,
  `kd_motor` varchar(5) NOT NULL,
  `uang_muka` int(11) NOT NULL,
  `lama_cicilan` int(11) NOT NULL,
  `angsuran` int(11) NOT NULL,
  `sisa` int(11) NOT NULL,
  `keterangan` enum('BELUM LUNAS','LUNAS') NOT NULL,
  `status` enum('AKTIF','TIDAK AKTIF') NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_belikredit`
--

INSERT INTO `t_belikredit` (`kd_belikredit`, `tgl_beli`, `kd_pelanggan`, `kd_motor`, `uang_muka`, `lama_cicilan`, `angsuran`, `sisa`, `keterangan`, `status`, `created_at`, `updated_at`, `deleted_at`) VALUES
('PK00001', '2021-12-22', 'P0001', 'M0001', 1000000, 12, 1062500, 0, 'LUNAS', 'AKTIF', '2022-01-25 02:26:23', NULL, NULL),
('PK00002', '2022-01-22', 'P0003', 'M0004', 1000000, 24, 750000, 16500000, 'BELUM LUNAS', 'AKTIF', '2022-01-25 02:26:23', NULL, NULL),
('PK00003', '2022-01-25', 'P0003', 'M0003', 500000, 18, 972222, 17500000, 'BELUM LUNAS', 'AKTIF', '2022-01-25 15:23:12', NULL, NULL);

--
-- Triggers `t_belikredit`
--
DELIMITER $$
CREATE TRIGGER `deleteBeliKredit` AFTER DELETE ON `t_belikredit` FOR EACH ROW BEGIN
INSERT INTO log__belikredit
(action, kd_belikredit, tgl_beli, kd_pelanggan, kd_motor, uang_muka, lama_cicilan, angsuran, sisa, keterangan, status, created_at) 
VALUES ('DELETE', OLD.kd_belikredit, OLD.tgl_beli, OLD.kd_pelanggan, OLD.kd_motor, OLD.uang_muka, OLD.lama_cicilan, OLD.angsuran, OLD.sisa, OLD.keterangan, OLD.status, NOW());
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `insertBeliKredit` AFTER INSERT ON `t_belikredit` FOR EACH ROW BEGIN
INSERT INTO log__belikredit
(action, kd_belikredit, tgl_beli, kd_pelanggan, kd_motor, uang_muka, lama_cicilan, angsuran, sisa, keterangan, status, created_at) 
VALUES ('INSERT', NEW.kd_belikredit, NEW.tgl_beli, NEW.kd_pelanggan, NEW.kd_motor, NEW.uang_muka, NEW.lama_cicilan, NEW.angsuran, NEW.sisa, NEW.keterangan, NEW.status, NOW());
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `updateBeliKredit` AFTER UPDATE ON `t_belikredit` FOR EACH ROW BEGIN
INSERT INTO log__belikredit
(action, kd_belikredit, tgl_beli, kd_pelanggan, kd_motor, uang_muka, lama_cicilan, angsuran, sisa, keterangan, status, created_at) 
VALUES ('UPDATE', OLD.kd_belikredit, NEW.tgl_beli, NEW.kd_pelanggan, NEW.kd_motor, NEW.uang_muka, NEW.lama_cicilan, NEW.angsuran, NEW.sisa, NEW.keterangan, NEW.status, NOW());
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `t_detail`
--

CREATE TABLE `t_detail` (
  `kd_detail` bigint(20) NOT NULL,
  `kd_beli` varchar(15) NOT NULL,
  `kd_motor` varchar(5) NOT NULL,
  `qty` int(11) NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `t_karyawan`
--

CREATE TABLE `t_karyawan` (
  `kd_karyawan` varchar(5) NOT NULL,
  `nik` varchar(16) NOT NULL,
  `nm_karyawan` varchar(50) NOT NULL,
  `jns_kelamin` enum('Laki-Laki','Perempuan') NOT NULL,
  `almt_karyawan` varchar(50) NOT NULL,
  `no_kk` varchar(10) NOT NULL,
  `no_hp` varchar(12) NOT NULL,
  `keterangan` varchar(50) NOT NULL,
  `status` enum('AKTIF','TIDAK AKTIF') NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_karyawan`
--

INSERT INTO `t_karyawan` (`kd_karyawan`, `nik`, `nm_karyawan`, `jns_kelamin`, `almt_karyawan`, `no_kk`, `no_hp`, `keterangan`, `status`, `created_at`, `updated_at`, `deleted_at`) VALUES
('K0001', '3206725189020001', 'Jaka Tingkir', 'Laki-Laki', 'Jl. Talagasari No. 35 Kawali', '0826261723', '081372618261', 'Karyawan OK', 'AKTIF', '2022-01-29 02:59:56', NULL, '0000-00-00 00:00:00'),
('K0002', '3205726255020002', 'Budi Santoso', 'Laki-Laki', 'Jl. Kawalimukti No. 12 Kawali', '0257352146', '087654741110', 'Karyawan OK', 'AKTIF', '2022-01-29 03:02:08', NULL, '0000-00-00 00:00:00'),
('K0003', '3206726783740001', 'Iman Saiful', 'Laki-Laki', 'Jl. Jatinagara No. 109', '0863528163', '087267227472', 'Karyawan OK', 'AKTIF', '2022-01-29 04:05:28', NULL, NULL),
('K0004', '3208726267230001', 'Dewi Melati', 'Perempuan', 'Jl. Winduraja No. 9', '0867263724', '086727236432', 'Karyawan OK', 'AKTIF', '2022-01-29 04:06:24', NULL, NULL),
('K0005', '3206887743800003', 'Ahmad Fauzi', 'Laki-Laki', 'Jl. Rajadesa No. 9', '0892837282', '081245637890', 'Karyawan OK', 'AKTIF', '2022-01-29 04:07:16', NULL, NULL),
('K0006', '3201234567891234', 'Ayu', 'Perempuan', 'Kawali, Jawa Barat', '3125412345', '08123456789', 'Karyawan OK', 'AKTIF', '2022-02-01 04:16:41', NULL, NULL);

--
-- Triggers `t_karyawan`
--
DELIMITER $$
CREATE TRIGGER `deleteEmployee` AFTER DELETE ON `t_karyawan` FOR EACH ROW BEGIN
INSERT INTO log__karyawan
(action, kd_karyawan, nik, nm_karyawan, jns_kelamin, almt_karyawan, no_kk, no_hp, keterangan, status, created_at) 
VALUES ('DELETE', OLD.kd_karyawan, OLD.nik, OLD.nm_karyawan, OLD.jns_kelamin, OLD.almt_karyawan, OLD.no_kk, OLD.no_hp, OLD.keterangan, OLD.status, NOW());
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `insertEmployee` AFTER INSERT ON `t_karyawan` FOR EACH ROW BEGIN
INSERT INTO log__karyawan
(action, kd_karyawan, nik, nm_karyawan, jns_kelamin, almt_karyawan, no_kk, no_hp, keterangan, status, created_at) 
VALUES ('INSERT', NEW.kd_karyawan, NEW.nik, NEW.nm_karyawan, NEW.jns_kelamin, NEW.almt_karyawan, NEW.no_kk, NEW.no_hp, NEW.keterangan, NEW.status, NOW());
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `updateEmployee` AFTER UPDATE ON `t_karyawan` FOR EACH ROW BEGIN
INSERT INTO log__karyawan
(action, kd_karyawan, nik, nm_karyawan, jns_kelamin, almt_karyawan, no_kk, no_hp, keterangan, status, created_at) 
VALUES ('UPDATE', OLD.kd_karyawan, NEW.nik, NEW.nm_karyawan, NEW.jns_kelamin, NEW.almt_karyawan, NEW.no_kk, NEW.no_hp, NEW.keterangan, NEW.status, NOW());
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `t_message`
--

CREATE TABLE `t_message` (
  `id` bigint(20) NOT NULL,
  `sender` varchar(5) NOT NULL,
  `receiver` varchar(5) NOT NULL,
  `subject` varchar(255) NOT NULL,
  `message` varchar(255) NOT NULL,
  `status` enum('READ','UNREAD') NOT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_message`
--

INSERT INTO `t_message` (`id`, `sender`, `receiver`, `subject`, `message`, `status`, `date`) VALUES
(1, 'U0002', 'U0002', 'test', 'test', 'UNREAD', '2022-01-31 14:20:16'),
(2, 'U0002', 'U0002', 'aaaa', 'aaaa', 'UNREAD', '2022-02-01 04:32:36');

-- --------------------------------------------------------

--
-- Table structure for table `t_motor`
--

CREATE TABLE `t_motor` (
  `kd_motor` varchar(5) NOT NULL,
  `jenis` varchar(20) NOT NULL,
  `type` varchar(20) NOT NULL,
  `warna` varchar(20) NOT NULL,
  `harga` int(11) NOT NULL,
  `gambar` varchar(20) NOT NULL,
  `stok` int(11) NOT NULL,
  `keterangan` varchar(50) NOT NULL,
  `status` enum('AKTIF','TIDAK AKTIF') NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_motor`
--

INSERT INTO `t_motor` (`kd_motor`, `jenis`, `type`, `warna`, `harga`, `gambar`, `stok`, `keterangan`, `status`, `created_at`, `updated_at`, `deleted_at`) VALUES
('M0001', 'Matic', 'Beat', 'Pink', 13750000, '20220117160716.png', 6, 'OK', 'AKTIF', '2021-12-24 23:58:56', '2022-02-01 06:26:50', NULL),
('M0002', 'Bebek', 'Supra X 125', 'Hitam', 15000000, '20220117160729.png', 8, 'OK', 'AKTIF', '2021-12-24 23:58:56', '2022-02-01 06:27:01', NULL),
('M0003', 'Sport', 'Verza', 'Biru', 18000000, '20220117160649.png', 3, 'OK', 'AKTIF', '2022-01-24 23:58:56', '2022-02-01 06:27:11', NULL),
('M0004', 'Matic', 'Vario', 'Putih', 19000000, '20220117160746.png', 5, 'OK', 'TIDAK AKTIF', '2022-01-24 23:58:56', '2022-01-31 08:42:34', NULL);

--
-- Triggers `t_motor`
--
DELIMITER $$
CREATE TRIGGER `deleteMotor` AFTER DELETE ON `t_motor` FOR EACH ROW BEGIN
INSERT INTO log__motor(action, kd_motor, jenis, type, warna, harga, gambar, stok, keterangan, status, created_at) VALUES ('DELETE', OLD.kd_motor, OLD.jenis, OLD.type, OLD.warna, OLD.harga, OLD.gambar, OLD.stok, OLD.keterangan, OLD.status, NOW());
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `insertMotor` AFTER INSERT ON `t_motor` FOR EACH ROW BEGIN
INSERT INTO log__motor
(action, kd_motor, jenis, type, warna, harga, gambar, stok, keterangan, status, created_at) 
VALUES ('INSERT', NEW.kd_motor, NEW.jenis, NEW.type, NEW.warna, NEW.harga, NEW.gambar, NEW.stok, NEW.keterangan, NEW.status, NOW());
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `updateMotor` AFTER UPDATE ON `t_motor` FOR EACH ROW BEGIN
INSERT INTO log__motor(action, kd_motor, jenis, type, warna, harga, gambar, stok, keterangan, status, created_at) VALUES ('UPDATE', OLD.kd_motor, NEW.jenis, NEW.type, NEW.warna, NEW.harga, NEW.gambar, NEW.stok, NEW.keterangan, NEW.status, NOW());
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `t_pelanggan`
--

CREATE TABLE `t_pelanggan` (
  `kd_pelanggan` varchar(5) NOT NULL,
  `nik` varchar(16) NOT NULL,
  `nm_pelanggan` varchar(50) NOT NULL,
  `jns_kelamin` enum('Laki-Laki','Perempuan') NOT NULL,
  `almt_pelanggan` varchar(50) NOT NULL,
  `no_kk` varchar(10) NOT NULL,
  `no_hp` varchar(12) NOT NULL,
  `keterangan` varchar(50) NOT NULL,
  `status` enum('AKTIF','TIDAK AKTIF') NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_pelanggan`
--

INSERT INTO `t_pelanggan` (`kd_pelanggan`, `nik`, `nm_pelanggan`, `jns_kelamin`, `almt_pelanggan`, `no_kk`, `no_hp`, `keterangan`, `status`, `created_at`, `updated_at`, `deleted_at`) VALUES
('P0001', '3207080182050002', 'Dani Pedrosa', 'Laki-Laki', 'Jl. Talagasari No. 12 Kawali', '090910102', '084654741244', 'Pelanggan OK', 'AKTIF', '2021-12-25 02:24:07', NULL, NULL),
('P0002', '3207080182050012', 'Valentino Rosi', 'Laki-Laki', 'Jl. Raya Ciamis No. 34', '0875214550', '087654741110', 'Pelanggan OK', 'AKTIF', '2022-01-25 02:24:07', NULL, NULL),
('P0003', '3207082058010120', 'Sahrini', 'Perempuan', 'Jl. Banjar No. 55', '0742451101', '081247852369', 'Pelanggan OK', 'AKTIF', '2022-01-25 02:24:07', NULL, NULL);

--
-- Triggers `t_pelanggan`
--
DELIMITER $$
CREATE TRIGGER `deleteCustomer` AFTER DELETE ON `t_pelanggan` FOR EACH ROW BEGIN
INSERT INTO log__pelanggan
(action, kd_pelanggan, nik, nm_pelanggan, jns_kelamin, almt_pelanggan, no_kk, no_hp, keterangan, status, created_at) 
VALUES ('DELETE', OLD.kd_pelanggan, OLD.nik, OLD.nm_pelanggan, OLD.jns_kelamin, OLD.almt_pelanggan, OLD.no_kk, OLD.no_hp, OLD.keterangan, OLD.status, NOW());
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `insertCustomer` AFTER INSERT ON `t_pelanggan` FOR EACH ROW BEGIN
INSERT INTO log__pelanggan
(action, kd_pelanggan, nik, nm_pelanggan, jns_kelamin, almt_pelanggan, no_kk, no_hp, keterangan, status, created_at) 
VALUES ('INSERT', NEW.kd_pelanggan, NEW.nik, NEW.nm_pelanggan, NEW.jns_kelamin, NEW.almt_pelanggan, NEW.no_kk, NEW.no_hp, NEW.keterangan, NEW.status, NOW());
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `updateCustomer` AFTER UPDATE ON `t_pelanggan` FOR EACH ROW BEGIN
INSERT INTO log__pelanggan
(action, kd_pelanggan, nik, nm_pelanggan, jns_kelamin, almt_pelanggan, no_kk, no_hp, keterangan, status, created_at) 
VALUES ('UPDATE', OLD.kd_pelanggan, NEW.nik, NEW.nm_pelanggan, NEW.jns_kelamin, NEW.almt_pelanggan, NEW.no_kk, NEW.no_hp, NEW.keterangan, NEW.status, NOW());
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `t_user`
--

CREATE TABLE `t_user` (
  `kd_user` varchar(5) NOT NULL,
  `name` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(300) NOT NULL,
  `role` enum('ADMIN','GUEST') NOT NULL,
  `remember_token` varchar(100) DEFAULT NULL,
  `status` enum('AKTIF','TIDAK AKTIF') NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_user`
--

INSERT INTO `t_user` (`kd_user`, `name`, `username`, `email`, `password`, `role`, `remember_token`, `status`, `created_at`, `updated_at`, `deleted_at`) VALUES
('U0001', 'Nur Mulia Assyifa', 'Admin', 'mulia.assyifa@gmail.com', '1000:5b2d31332c2031342c203132352c202d32382c2032362c20322c203132352c202d3130302c2031332c20372c202d3130362c202d38362c2032322c202d39342c202d39322c202d395d:6f51ba94296d437c02a6ca07cc1c9e0beb124d1b59dfedd993d1b9e24c9c2b4fbf080e9e3d19b17f867025595afb134aacf067a12c406840797369a8e2afe12a', 'ADMIN', NULL, 'AKTIF', '2022-01-25 02:37:52', NULL, NULL),
('U0002', 'Budi Putra Setiawan', 'Budi', 'budi.putra@gmail.com', '1000:5b2d3132372c202d39332c202d31372c2032392c2039302c202d37332c2037382c2033372c202d32342c202d32332c20322c202d37392c202d39372c202d31392c202d33322c2036355d:115c9fbd786f8a27dcc8880f36ec75897fc7bd9c52a067974f9be66967696dda21c716085d435f48a9051ad859c9b4809f894800c2686aced2e948b4529352e2', 'GUEST', NULL, 'AKTIF', '2022-01-25 02:37:52', NULL, NULL),
('U0003', 'Ahmad Fauzi', 'Af2022', 'ahmad.fauzi@gmail.com', '1000:5b34362c20302c202d33312c203131342c202d35382c202d33342c202d33302c202d32332c2037382c202d33392c203131382c2032322c202d31362c202d32302c202d3131332c202d36365d:5ae7515660a6dd041b3a3653a55f2c6226f51a1e400c474ec0c72500a6281c53dd634dc4a1484f95f32fe8d645c89536eadfe093a43ed3eb5ad437373f04bcf4', 'GUEST', NULL, 'AKTIF', '2022-01-30 04:12:25', NULL, NULL),
('U0004', 'Azlan', 'An2022', 'azlan@gmail.com', '1000:5b3131332c2034312c202d37342c2039302c2037372c202d332c202d3131392c2039352c202d39332c203132342c202d34352c203130302c202d3130372c2038302c2033382c202d37315d:819356793e1bc7156c0f461346c7d7879a028e4040885673cfbe58493c2045e309d707ce0bcde22e409c5bd9e493f434e8f726553dc0253bf3ef8f0da4a3b49d', 'GUEST', NULL, 'AKTIF', '2022-01-30 04:25:16', NULL, NULL),
('U0005', 'Hadi', 'Hadi21', 'hadi21@gmail.com', '1000:5b3130322c2039332c203130362c202d34392c2036372c2033342c202d39382c202d3131342c202d33302c202d35312c202d31362c202d3132302c2034382c202d3132342c2038372c2034365d:f9dd8a1e7351a5d3f8f154315125c84d28af4ae0f05a3641fd738d9f3c6b24646c8b0fae97e92192d9775d53eb1a5660ea6ba711c28e95fe106befe906225449', 'GUEST', NULL, 'AKTIF', '2022-02-01 06:25:51', NULL, NULL);

--
-- Triggers `t_user`
--
DELIMITER $$
CREATE TRIGGER `deleteUser` AFTER DELETE ON `t_user` FOR EACH ROW BEGIN
INSERT INTO log__user
(action, kd_user, name, username, email, password, role, remember_token, status, created_at) 
VALUES ('DELETE', OLD.kd_user, OLD.name, OLD.username, OLD.email, OLD.password, OLD.role, OLD.remember_token, OLD.status, NOW());
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `insertUser` AFTER INSERT ON `t_user` FOR EACH ROW BEGIN
INSERT INTO log__user
(action, kd_user, name, username, email, password, role, remember_token, status, created_at) 
VALUES ('INSERT', NEW.kd_user, NEW.name, NEW.username, NEW.email, NEW.password, NEW.role, NEW.remember_token, NEW.status, NOW());
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `updateUser` AFTER UPDATE ON `t_user` FOR EACH ROW BEGIN
INSERT INTO log__user
(action, kd_user, name, username, email, password, role, remember_token, status, created_at) 
VALUES ('UPDATE', OLD.kd_user, NEW.name, NEW.username, NEW.email, NEW.password, NEW.role, NEW.remember_token, NEW.status, NOW());
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Stand-in structure for view `v_belicash`
-- (See below for the actual view)
--
CREATE TABLE `v_belicash` (
`kd_belicash` varchar(7)
,`tgl_beli` date
,`kd_pelanggan` varchar(5)
,`nm_pelanggan` varchar(50)
,`almt_pelanggan` varchar(50)
,`nik` varchar(16)
,`kd_motor` varchar(5)
,`type` varchar(20)
,`warna` varchar(20)
,`harga` int(11)
,`total` int(11)
,`bayar` int(11)
,`kembalian` int(11)
,`keterangan` enum('LUNAS')
,`status` enum('AKTIF','TIDAK AKTIF')
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `v_belikredit`
-- (See below for the actual view)
--
CREATE TABLE `v_belikredit` (
`kd_belikredit` varchar(7)
,`tgl_beli` date
,`kd_pelanggan` varchar(5)
,`nm_pelanggan` varchar(50)
,`almt_pelanggan` varchar(50)
,`nik` varchar(16)
,`kd_motor` varchar(5)
,`type` varchar(20)
,`warna` varchar(20)
,`harga` int(11)
,`uang_muka` int(11)
,`lama_cicilan` int(11)
,`angsuran` int(11)
,`sisa` int(11)
,`keterangan` enum('BELUM LUNAS','LUNAS')
,`status` enum('AKTIF','TIDAK AKTIF')
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `v_detail`
-- (See below for the actual view)
--
CREATE TABLE `v_detail` (
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `v_karyawan`
-- (See below for the actual view)
--
CREATE TABLE `v_karyawan` (
`kd_karyawan` varchar(5)
,`nik` varchar(16)
,`nm_karyawan` varchar(50)
,`jns_kelamin` enum('Laki-Laki','Perempuan')
,`almt_karyawan` varchar(50)
,`no_kk` varchar(10)
,`no_hp` varchar(12)
,`keterangan` varchar(50)
,`status` enum('AKTIF','TIDAK AKTIF')
,`created_at` timestamp
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `v_motor`
-- (See below for the actual view)
--
CREATE TABLE `v_motor` (
`kd_motor` varchar(5)
,`jenis` varchar(20)
,`type` varchar(20)
,`warna` varchar(20)
,`harga` int(11)
,`gambar` varchar(20)
,`stok` int(11)
,`keterangan` varchar(50)
,`status` enum('AKTIF','TIDAK AKTIF')
,`created_at` timestamp
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `v_pelanggan`
-- (See below for the actual view)
--
CREATE TABLE `v_pelanggan` (
`kd_pelanggan` varchar(5)
,`nik` varchar(16)
,`nm_pelanggan` varchar(50)
,`jns_kelamin` enum('Laki-Laki','Perempuan')
,`almt_pelanggan` varchar(50)
,`no_kk` varchar(10)
,`no_hp` varchar(12)
,`keterangan` varchar(50)
,`status` enum('AKTIF','TIDAK AKTIF')
,`created_at` timestamp
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `v_user`
-- (See below for the actual view)
--
CREATE TABLE `v_user` (
`kd_user` varchar(5)
,`name` varchar(255)
,`username` varchar(255)
,`email` varchar(255)
,`password` varchar(300)
,`role` enum('ADMIN','GUEST')
,`status` enum('AKTIF','TIDAK AKTIF')
,`created_at` timestamp
);

-- --------------------------------------------------------

--
-- Structure for view `v_belicash`
--
DROP TABLE IF EXISTS `v_belicash`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_belicash`  AS SELECT `t_belicash`.`kd_belicash` AS `kd_belicash`, `t_belicash`.`tgl_beli` AS `tgl_beli`, `t_belicash`.`kd_pelanggan` AS `kd_pelanggan`, `t_pelanggan`.`nm_pelanggan` AS `nm_pelanggan`, `t_pelanggan`.`almt_pelanggan` AS `almt_pelanggan`, `t_pelanggan`.`nik` AS `nik`, `t_belicash`.`kd_motor` AS `kd_motor`, `t_motor`.`type` AS `type`, `t_motor`.`warna` AS `warna`, `t_motor`.`harga` AS `harga`, `t_belicash`.`total` AS `total`, `t_belicash`.`bayar` AS `bayar`, `t_belicash`.`kembalian` AS `kembalian`, `t_belicash`.`keterangan` AS `keterangan`, `t_belicash`.`status` AS `status` FROM ((`t_motor` join `t_pelanggan`) join `t_belicash`) WHERE `t_belicash`.`kd_motor` = `t_motor`.`kd_motor` AND `t_belicash`.`kd_pelanggan` = `t_pelanggan`.`kd_pelanggan` ORDER BY `t_belicash`.`kd_belicash` ASC ;

-- --------------------------------------------------------

--
-- Structure for view `v_belikredit`
--
DROP TABLE IF EXISTS `v_belikredit`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_belikredit`  AS SELECT `t_belikredit`.`kd_belikredit` AS `kd_belikredit`, `t_belikredit`.`tgl_beli` AS `tgl_beli`, `t_belikredit`.`kd_pelanggan` AS `kd_pelanggan`, `t_pelanggan`.`nm_pelanggan` AS `nm_pelanggan`, `t_pelanggan`.`almt_pelanggan` AS `almt_pelanggan`, `t_pelanggan`.`nik` AS `nik`, `t_belikredit`.`kd_motor` AS `kd_motor`, `t_motor`.`type` AS `type`, `t_motor`.`warna` AS `warna`, `t_motor`.`harga` AS `harga`, `t_belikredit`.`uang_muka` AS `uang_muka`, `t_belikredit`.`lama_cicilan` AS `lama_cicilan`, `t_belikredit`.`angsuran` AS `angsuran`, `t_belikredit`.`sisa` AS `sisa`, `t_belikredit`.`keterangan` AS `keterangan`, `t_belikredit`.`status` AS `status` FROM ((`t_motor` join `t_pelanggan`) join `t_belikredit`) WHERE `t_belikredit`.`kd_motor` = `t_motor`.`kd_motor` AND `t_belikredit`.`kd_pelanggan` = `t_pelanggan`.`kd_pelanggan` ORDER BY `t_belikredit`.`kd_belikredit` ASC ;

-- --------------------------------------------------------

--
-- Structure for view `v_detail`
--
DROP TABLE IF EXISTS `v_detail`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_detail`  AS SELECT `t_bayar`.`kd_bayar` AS `kd_bayar`, `t_pelanggan`.`nm_pelanggan` AS `nm_pelanggan`, `t_pelanggan`.`nik` AS `nik`, `t_pelanggan`.`almt_pelanggan` AS `almt_pelanggan`, `t_motor`.`type` AS `type`, `t_motor`.`warna` AS `warna`, `t_motor`.`harga` AS `harga`, `t_bayar`.`kd_beli` AS `kd_beli`, `t_beli`.`uang_muka` AS `uang_muka`, `t_beli`.`diskon` AS `diskon`, `t_beli`.`pajak` AS `pajak`, `t_beli`.`bunga` AS `bunga`, `t_beli`.`lama_cicilan` AS `lama_cicilan`, `t_beli`.`angsuran` AS `angsuran` FROM (((`t_bayar` join `t_beli`) join `t_pelanggan`) join `t_motor`) WHERE `t_bayar`.`kd_beli` = `t_beli`.`kd_beli` AND `t_beli`.`kd_pelanggan` = `t_pelanggan`.`kd_pelanggan` AND `t_bayar`.`kd_motor` = `t_motor`.`kd_motor` ORDER BY `t_bayar`.`kd_bayar` ASC ;

-- --------------------------------------------------------

--
-- Structure for view `v_karyawan`
--
DROP TABLE IF EXISTS `v_karyawan`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_karyawan`  AS SELECT `t_karyawan`.`kd_karyawan` AS `kd_karyawan`, `t_karyawan`.`nik` AS `nik`, `t_karyawan`.`nm_karyawan` AS `nm_karyawan`, `t_karyawan`.`jns_kelamin` AS `jns_kelamin`, `t_karyawan`.`almt_karyawan` AS `almt_karyawan`, `t_karyawan`.`no_kk` AS `no_kk`, `t_karyawan`.`no_hp` AS `no_hp`, `t_karyawan`.`keterangan` AS `keterangan`, `t_karyawan`.`status` AS `status`, `t_karyawan`.`created_at` AS `created_at` FROM `t_karyawan` ORDER BY `t_karyawan`.`kd_karyawan` ASC ;

-- --------------------------------------------------------

--
-- Structure for view `v_motor`
--
DROP TABLE IF EXISTS `v_motor`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_motor`  AS SELECT `t_motor`.`kd_motor` AS `kd_motor`, `t_motor`.`jenis` AS `jenis`, `t_motor`.`type` AS `type`, `t_motor`.`warna` AS `warna`, `t_motor`.`harga` AS `harga`, `t_motor`.`gambar` AS `gambar`, `t_motor`.`stok` AS `stok`, `t_motor`.`keterangan` AS `keterangan`, `t_motor`.`status` AS `status`, `t_motor`.`created_at` AS `created_at` FROM `t_motor` ORDER BY `t_motor`.`kd_motor` ASC ;

-- --------------------------------------------------------

--
-- Structure for view `v_pelanggan`
--
DROP TABLE IF EXISTS `v_pelanggan`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_pelanggan`  AS SELECT `t_pelanggan`.`kd_pelanggan` AS `kd_pelanggan`, `t_pelanggan`.`nik` AS `nik`, `t_pelanggan`.`nm_pelanggan` AS `nm_pelanggan`, `t_pelanggan`.`jns_kelamin` AS `jns_kelamin`, `t_pelanggan`.`almt_pelanggan` AS `almt_pelanggan`, `t_pelanggan`.`no_kk` AS `no_kk`, `t_pelanggan`.`no_hp` AS `no_hp`, `t_pelanggan`.`keterangan` AS `keterangan`, `t_pelanggan`.`status` AS `status`, `t_pelanggan`.`created_at` AS `created_at` FROM `t_pelanggan` ORDER BY `t_pelanggan`.`kd_pelanggan` ASC ;

-- --------------------------------------------------------

--
-- Structure for view `v_user`
--
DROP TABLE IF EXISTS `v_user`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_user`  AS SELECT `t_user`.`kd_user` AS `kd_user`, `t_user`.`name` AS `name`, `t_user`.`username` AS `username`, `t_user`.`email` AS `email`, `t_user`.`password` AS `password`, `t_user`.`role` AS `role`, `t_user`.`status` AS `status`, `t_user`.`created_at` AS `created_at` FROM `t_user` WHERE `t_user`.`role` = 'ADMIN' AND `t_user`.`status` <> 'AKTIF' OR `t_user`.`role` = 'GUEST' ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `log__belicash`
--
ALTER TABLE `log__belicash`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `log__belikredit`
--
ALTER TABLE `log__belikredit`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `log__karyawan`
--
ALTER TABLE `log__karyawan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `log__motor`
--
ALTER TABLE `log__motor`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `log__pelanggan`
--
ALTER TABLE `log__pelanggan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `log__user`
--
ALTER TABLE `log__user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `t_angsuran`
--
ALTER TABLE `t_angsuran`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `t_bayar`
--
ALTER TABLE `t_bayar`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `t_beli`
--
ALTER TABLE `t_beli`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `t_belicash`
--
ALTER TABLE `t_belicash`
  ADD PRIMARY KEY (`kd_belicash`);

--
-- Indexes for table `t_belikredit`
--
ALTER TABLE `t_belikredit`
  ADD PRIMARY KEY (`kd_belikredit`);

--
-- Indexes for table `t_detail`
--
ALTER TABLE `t_detail`
  ADD PRIMARY KEY (`kd_detail`);

--
-- Indexes for table `t_karyawan`
--
ALTER TABLE `t_karyawan`
  ADD PRIMARY KEY (`kd_karyawan`);

--
-- Indexes for table `t_message`
--
ALTER TABLE `t_message`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `t_motor`
--
ALTER TABLE `t_motor`
  ADD PRIMARY KEY (`kd_motor`);

--
-- Indexes for table `t_pelanggan`
--
ALTER TABLE `t_pelanggan`
  ADD PRIMARY KEY (`kd_pelanggan`);

--
-- Indexes for table `t_user`
--
ALTER TABLE `t_user`
  ADD PRIMARY KEY (`kd_user`),
  ADD UNIQUE KEY `username` (`username`,`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `log__belicash`
--
ALTER TABLE `log__belicash`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `log__belikredit`
--
ALTER TABLE `log__belikredit`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `log__karyawan`
--
ALTER TABLE `log__karyawan`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `log__motor`
--
ALTER TABLE `log__motor`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT for table `log__pelanggan`
--
ALTER TABLE `log__pelanggan`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `log__user`
--
ALTER TABLE `log__user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `t_angsuran`
--
ALTER TABLE `t_angsuran`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_bayar`
--
ALTER TABLE `t_bayar`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `t_beli`
--
ALTER TABLE `t_beli`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `t_detail`
--
ALTER TABLE `t_detail`
  MODIFY `kd_detail` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_message`
--
ALTER TABLE `t_message`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
