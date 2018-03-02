CREATE TABLE `battlerecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` varchar(10) DEFAULT NULL,
  `battleDate` varchar(20) DEFAULT NULL,
  `opponentName` varchar(10) DEFAULT NULL,
  `battleResult` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8

