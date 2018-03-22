CREATE TABLE `detailbattlerecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` varchar(10) DEFAULT NULL,
  `battleDate` varchar(20) DEFAULT NULL,
  `opponentName` varchar(10) DEFAULT NULL,
  `Round` int(11) DEFAULT NULL,
  `userCard` varchar(10) DEFAULT NULL,
  `opponentCard` varchar(10) DEFAULT NULL,
  `bordInfo` varchar(30) DEFAULT NULL,
  `lastBetRound` varchar(10) DEFAULT NULL,
  `userHand` varchar(20) DEFAULT NULL,
  `opponentHand` varchar(20) DEFAULT NULL,
  `changePoint` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8

