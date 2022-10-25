CREATE TABLE `tbl_board` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `content` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `uname` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `pwd` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ref` smallint(6) DEFAULT 0,
  `restep` smallint(6) DEFAULT 0,
  `relvl` smallint(6) DEFAULT 0,
  `click` int(11) DEFAULT 1,
  `regdate` datetime DEFAULT current_timestamp(),
  `moddate` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`idx`),
  KEY `idx` (`idx`)
) ENGINE=MyISAM AUTO_INCREMENT=111 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci