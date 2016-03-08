SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `choice_image`;

CREATE TABLE `choice_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userOwnedId` bigint(20) DEFAULT NULL,
  `imgSrc` varchar(255) DEFAULT NULL,
  `questionId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_tq7l5ffn6txf9nkqn7p5ma4sn` (`questionId`),
  CONSTRAINT `FK_tq7l5ffn6txf9nkqn7p5ma4sn` FOREIGN KEY (`questionId`) REFERENCES `quiz_question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `multiple_choice_quiz_answer`;

CREATE TABLE `multiple_choice_quiz_answer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userOwnedId` bigint(20) DEFAULT NULL,
  `answer` varchar(255) DEFAULT NULL,
  `questionId` bigint(20) DEFAULT NULL,
  `quizSessionId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_iln7cu3vkrq013apmvldxp6sv` (`questionId`),
  KEY `FK_8vmqa7li3ios2i14h3gh5f8fw` (`quizSessionId`),
  CONSTRAINT `FK_8vmqa7li3ios2i14h3gh5f8fw` FOREIGN KEY (`quizSessionId`) REFERENCES `quiz_session` (`id`),
  CONSTRAINT `FK_iln7cu3vkrq013apmvldxp6sv` FOREIGN KEY (`questionId`) REFERENCES `quiz_question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `question_image`;

CREATE TABLE `question_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userOwnedId` bigint(20) DEFAULT NULL,
  `imgSrc` varchar(255) DEFAULT NULL,
  `questionId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_eee1xmmd2wywtmgpx0wg23rqs` (`questionId`),
  CONSTRAINT `FK_eee1xmmd2wywtmgpx0wg23rqs` FOREIGN KEY (`questionId`) REFERENCES `quiz_question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `quiz`;

CREATE TABLE `quiz` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userOwnedId` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `categoryId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7uqnturvbcs8gm76f248bw67h` (`categoryId`),
  CONSTRAINT `FK_7uqnturvbcs8gm76f248bw67h` FOREIGN KEY (`categoryId`) REFERENCES `quiz_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `quiz_category`;

CREATE TABLE `quiz_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `quiz_question`;

CREATE TABLE `quiz_question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userOwnedId` bigint(20) DEFAULT NULL,
  `choiceA` longtext,
  `choiceB` longtext,
  `choiceC` longtext,
  `choiceD` longtext,
  `correctAnswer` varchar(255) DEFAULT NULL,
  `no` int(11) DEFAULT NULL,
  `question` longtext,
  `quizId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_a96t9e6w9qisr0closgbiwc1u` (`quizId`),
  CONSTRAINT `FK_a96t9e6w9qisr0closgbiwc1u` FOREIGN KEY (`quizId`) REFERENCES `quiz` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `quiz_session`;

CREATE TABLE `quiz_session` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userOwnedId` bigint(20) DEFAULT NULL,
  `done` datetime DEFAULT NULL,
  `isCompleted` tinyint(1) DEFAULT NULL,
  `rightAnswer` int(11) DEFAULT NULL,
  `start` datetime DEFAULT NULL,
  `wrongAnswer` int(11) DEFAULT NULL,
  `quizId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5qqxr6bchhab6f24x16bpdlhp` (`quizId`),
  KEY `FK_9dv3il7yw7eurdld99tujc372` (`userId`),
  CONSTRAINT `FK_5qqxr6bchhab6f24x16bpdlhp` FOREIGN KEY (`quizId`) REFERENCES `quiz` (`id`),
  CONSTRAINT `FK_9dv3il7yw7eurdld99tujc372` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `isActive` tinyint(1) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `school` varchar(255) DEFAULT NULL,
  `userType` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `workCompany` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;
