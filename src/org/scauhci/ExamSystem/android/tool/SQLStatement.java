package org.scauhci.ExamSystem.android.tool;

public class SQLStatement {

	public static final String CREATE_TABLE_COURSE = "CREATE TABLE `course` ("
			+ "  `courseId` char(32) NOT NULL,"
			+ "  `courseName` varchar(32) NOT NULL,"
			+ "  `courseType` tinyint(4) DEFAULT NULL,"
			+ "  PRIMARY KEY (`courseId`)"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

	public static final String CREATE_TABLE_EXAM = "CREATE TABLE `exam` ("
			+ "  `examId` char(32) NOT NULL,"
			+ "  `courseId` char(32) NOT NULL,"
			+ "  `teacherId` char(32) NOT NULL,"
			+ "  `paperId` char(32) NOT NULL,"
			+ "  `examExplain` mediumtext,"
			+ "  `examName` varchar(100) NOT NULL,"
			+ "  `examCreatetime` datetime NOT NULL,"
			+ "  `examStartTime` int(10) unsigned NOT NULL,"
			+ "  `examEndTime` int(10) unsigned NOT NULL,"
			+ "  PRIMARY KEY (`examId`),"
			+ "  KEY `FK_Reference_29` (`courseId`),"
			+ "  KEY `FK_Reference_34` (`teacherId`),"
			+ "  KEY `FK_Reference_35` (`paperId`),"
			+ "  CONSTRAINT `FK_Reference_29` FOREIGN KEY (`courseId`) REFERENCES `course` (`courseId`) ON DELETE CASCADE ON UPDATE CASCADE,"
			+ "  CONSTRAINT `FK_Reference_34` FOREIGN KEY (`teacherId`) REFERENCES `teacher` (`teacherId`) ON DELETE CASCADE ON UPDATE CASCADE,"
			+ "  CONSTRAINT `FK_Reference_35` FOREIGN KEY (`paperId`) REFERENCES `paper` (`paperId`) ON DELETE CASCADE ON UPDATE CASCADE"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

	public static final String CREATE_TABLE_PAPER = "CREATE TABLE `paper` ("
			+ "  `paperId` char(32) NOT NULL,"
			+ "  `paperName` varchar(100) NOT NULL,"
			+ "  `paperType` int(10) unsigned NOT NULL,"
			+ "  `teacherId` char(32) NOT NULL,"
			+ "  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,"
			+ "  `courseId` char(32) NOT NULL,"
			+ "  `paperTotalScore` float NOT NULL,"
			+ "  `paperExplain` mediumtext,"
			+ "	 PRIMARY KEY (`paperId`),"
			+ "	 KEY `FK_paper_1` (`teacherId`),"
			+ "	 KEY `FK_paper_2` (`courseId`),"
			+ "	 CONSTRAINT `FK_paper_1` FOREIGN KEY (`teacherId`) REFERENCES `teacher` (`teacherId`) ON DELETE CASCADE ON UPDATE CASCADE,"
			+ "	 CONSTRAINT `FK_paper_2` FOREIGN KEY (`courseId`) REFERENCES `course` (`courseId`) ON DELETE CASCADE ON UPDATE CASCADE"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

	public static final String CREATE_TABLE_QUESTION = "CREATE TABLE `question` ("
			+ "  `questionId` char(32) NOT NULL,"
			+ "  `questionContent` mediumtext NOT NULL,"
			+ "  `questionType` tinyint(4) NOT NULL,"
			+ "  `courseId` char(32) NOT NULL,"
			+ "  `questionStdAnswer` text,"
			+ "  PRIMARY KEY (`questionId`),"
			+ "  KEY `FK_question_1` (`courseId`),"
			+ "  CONSTRAINT `FK_question_1` FOREIGN KEY (`courseId`) REFERENCES `course` (`courseId`) ON DELETE CASCADE ON UPDATE CASCADE"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

	public static final String CREATE_TABLE_QUESTION_OPTION = "CREATE TABLE `question_option` ("
			+ "  `questionOptionId` char(32) NOT NULL,"
			+ "  `questionId` char(32) NOT NULL,"
			+ "  `questionOptionContent` mediumtext NOT NULL,"
			+ "  `isQuestionStdAnswer` tinyint(1) NOT NULL DEFAULT '0',"
			+ "  PRIMARY KEY (`questionOptionId`),"
			+ "  KEY `FK_Reference_2` (`questionId`),"
			+ "  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`questionId`) REFERENCES `question` (`questionId`) ON DELETE CASCADE ON UPDATE CASCADE"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

	public static final String CREATE_TABLE_RELATION_PAPER_QUESTION = "";

	public static final String CREATE_TABLE_SUBMIT_ANSWER = "CREATE TABLE `submit_answer` ("
			+ "  `submitAnswerId` char(32) NOT NULL,"
			+ "  `studentId` varchar(32) NOT NULL,"
			+ "  `questionId` char(32) NOT NULL,"
			+ "  `examId` char(32) NOT NULL,"
			+ "  `questionStdScore` float DEFAULT NULL,"
			+ "  `questionScore` float NOT NULL,"
			+ "  PRIMARY KEY (`submitAnswerId`),"
			+ "  KEY `FK_Reference_14` (`studentId`),"
			+ "  KEY `FK_Reference_16` (`questionId`),"
			+ "  KEY `FK_Reference_42` (`examId`),"
			+ "  CONSTRAINT `FK_Reference_14` FOREIGN KEY (`studentId`) REFERENCES `student` (`studentId`) ON DELETE CASCADE ON UPDATE CASCADE,"
			+ "  CONSTRAINT `FK_Reference_16` FOREIGN KEY (`questionId`) REFERENCES `question` (`questionId`) ON DELETE CASCADE ON UPDATE CASCADE,"
			+ "  CONSTRAINT `FK_Reference_42` FOREIGN KEY (`examId`) REFERENCES `exam` (`examId`) ON DELETE CASCADE ON UPDATE CASCADE"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

	public static final String CREATE_TABLE_SCORE = "CREATE TABLE `score` ("
			+ "  `scoreId` char(32) NOT NULL,"
			+ "  `examId` char(32) NOT NULL,"
			+ "  `studentId` varchar(32) NOT NULL,"
			+ "  `paperScore` float NOT NULL,"
			+ "  PRIMARY KEY (`scoreId`),"
			+ "  KEY `FK_Reference_41` (`examId`),"
			+ "  KEY `FK_Reference_43` (`studentId`),"
			+ "  CONSTRAINT `FK_Reference_41` FOREIGN KEY (`examId`) REFERENCES `exam` (`examId`) ON DELETE CASCADE ON UPDATE CASCADE,"
			+ "  CONSTRAINT `FK_Reference_43` FOREIGN KEY (`studentId`) REFERENCES `student` (`studentId`) ON DELETE CASCADE ON UPDATE CASCADE"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

	public static final String CREATE_TABLE_STUDENT = "";

	public static final String CREATE_TABLE_NOTICE = "CREATE TABLE `notice` ("
			+ "  `noticeId` char(32) NOT NULL,"
			+ "  `noticeName` varchar(100) NOT NULL,"
			+ "  `noticeContent` text NOT NULL,"
			+ "  `noticePublicTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,"
			+ "  PRIMARY KEY (`noticeId`)"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

	public static final String CREATE_TABLE_REMARK = "";
	
	public static final String INSERT = "INSERT INTO ? (?) VALUES (?)";
	
	public static final String DELETE = "DELETE FROM ? WHERE ?";
	
	public static final String UPDATE = "UPDATE ? SET ? WHERE ?";
	
	public static final String SELECT = "SELECT ? FROM ?";
}
