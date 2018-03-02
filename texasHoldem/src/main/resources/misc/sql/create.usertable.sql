CREATE TABLE `usertable` (
  `userId` varchar(10) NOT NULL,
  `userName` varchar(10) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `comments` varchar(20) DEFAULT NULL,
  `DeleteFlg` int(11) DEFAULT '0',
  `DisplayFlg` int(11) DEFAULT '1',
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

