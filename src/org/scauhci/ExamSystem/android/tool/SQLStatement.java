package org.scauhci.ExamSystem.android.tool;

public class SQLStatement {

	public static final String CREATE_TABLE_COURSE = "CREATE TABLE `course` ("
			+ "  `courseId` char(32) NOT NULL,"
			+ "  `courseName` varchar(32) NOT NULL,"
			+ "  `courseType` tinyint(4) DEFAULT NULL,"
			+ "  PRIMARY KEY (`courseId`)" + ") ;";

	public static final String CREATE_TABLE_EXAM = "CREATE TABLE `exam` ("
			+ "  `examId` char(32) NOT NULL,"
			+ "  `courseId` char(32) NOT NULL,"
			+ "  `teacherId` char(32) NOT NULL,"
			+ "  `paperId` char(32) NOT NULL,"
			+ "  `examExplain` mediumtext NULL,"
			+ "  `examName` varchar(100) NOT NULL,"
			+ "  `examCreateTime` datetime NULL,"
			+ "  `examStartTime` datetime NULL,"
			+ "  `examEndTime` datetime NULL,"
			+ "  PRIMARY KEY (`examId`),"
			+ "  CONSTRAINT `FK_Reference_29` FOREIGN KEY (`courseId`) REFERENCES `course` (`courseId`) ON DELETE CASCADE ON UPDATE CASCADE,"
			+ "  CONSTRAINT `FK_Reference_34` FOREIGN KEY (`teacherId`) REFERENCES `teacher` (`teacherId`) ON DELETE CASCADE ON UPDATE CASCADE,"
			+ "  CONSTRAINT `FK_Reference_35` FOREIGN KEY (`paperId`) REFERENCES `paper` (`paperId`) ON DELETE CASCADE ON UPDATE CASCADE"
			+ ") ;";

	public static final String CREATE_TABLE_PAPER = "CREATE TABLE `paper` ("
			+ "  `paperId` char(32) NOT NULL,"
			+ "  `paperName` varchar(100) NOT NULL,"
			+ "  `paperType` int(10) NOT NULL,"
			+ "  `teacherId` char(32) NOT NULL,"
			+ "  `paperCreateTime` datetime NOT NULL,"
			+ "  `courseId` char(32) NOT NULL,"
			+ "  `paperTotalScore` float NOT NULL,"
			+ "  `paperExplain` mediumtext,"
			+ "	 PRIMARY KEY (`paperId`),"
			+ "	 CONSTRAINT `FK_paper_1` FOREIGN KEY (`teacherId`) REFERENCES `teacher` (`teacherId`) ON DELETE CASCADE ON UPDATE CASCADE,"
			+ "	 CONSTRAINT `FK_paper_2` FOREIGN KEY (`courseId`) REFERENCES `course` (`courseId`) ON DELETE CASCADE ON UPDATE CASCADE"
			+ ") ;";

	public static final String CREATE_TABLE_QUESTION = "CREATE TABLE `question` ("
			+ "  `questionId` char(32) NOT NULL,"
			+ "  `questionContent` mediumtext NOT NULL,"
			+ "  `questionType` tinyint(4) NOT NULL,"
			+ "  `courseId` char(32) NOT NULL,"
			+ "  `questionStdAnswer` text,"
			+ "  PRIMARY KEY (`questionId`),"
			+ "  CONSTRAINT `FK_question_1` FOREIGN KEY (`courseId`) REFERENCES `course` (`courseId`) ON DELETE CASCADE ON UPDATE CASCADE"
			+ ") ;";

	public static final String CREATE_TABLE_QUESTION_OPTION = "CREATE TABLE `question_option` ("
			+ "  `questionOptionId` char(32) NOT NULL,"
			+ "  `questionId` char(32) NOT NULL,"
			+ "  `questionOptionContent` mediumtext NOT NULL,"
			+ "  `isQuestionStdAnswer` tinyint(1) NOT NULL DEFAULT '0',"
			+ "  PRIMARY KEY (`questionOptionId`),"
			+ "  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`questionId`) REFERENCES `question` (`questionId`) ON DELETE CASCADE ON UPDATE CASCADE"
			+ ") ;";

	public static final String CREATE_TABLE_RELATION_PAPER_QUESTION = "CREATE TABLE `relation_paper_question` ("
			+ "  `relationPaperQuestionId` char(32) NOT NULL,"
			+ "  `examId` char(32) NOT NULL,"
			+ "  `paperId` char(32) NOT NULL,"
			+ "  `questionId` char(32) NOT NULL,"
			+ "  `questionIndex` tinyint(4) NOT NULL,"
			+ "  PRIMARY KEY (`relationPaperQuestionId`),"
			+ "  CONSTRAINT `FK_Reference_46` FOREIGN KEY (`examId`) REFERENCES `exam` (`examId`) ON DELETE CASCADE ON UPDATE CASCADE,"
			+ "  CONSTRAINT `FK_Reference_46` FOREIGN KEY (`paperId`) REFERENCES `paper` (`paperId`) ON DELETE CASCADE ON UPDATE CASCADE,"
			+ "  CONSTRAINT `FK_Reference_47` FOREIGN KEY (`questionId`) REFERENCES `question` (`questionId`) ON DELETE CASCADE ON UPDATE CASCADE"
			+ ") ;";

	public static final String CREATE_TABLE_SUBMIT_ANSWER = "CREATE TABLE `submit_answer` ("
			+ "  `submitAnswerId` char(32) NOT NULL,"
			+ "  `studentId` varchar(32) NOT NULL,"
			+ "  `questionId` char(32) NOT NULL,"
			+ "  `examId` char(32) NOT NULL,"
			+ "  `submitAnswerContent` mediumtext DEFAULT NULL,"
			+ "  `questionStdScore` float DEFAULT NULL,"
			+ "  `questionScore` float NOT NULL,"
			+ "  PRIMARY KEY (`submitAnswerId`),"
			+ "  CONSTRAINT `FK_Reference_14` FOREIGN KEY (`studentId`) REFERENCES `student` (`studentId`) ON DELETE CASCADE ON UPDATE CASCADE,"
			+ "  CONSTRAINT `FK_Reference_16` FOREIGN KEY (`questionId`) REFERENCES `question` (`questionId`) ON DELETE CASCADE ON UPDATE CASCADE,"
			+ "  CONSTRAINT `FK_Reference_42` FOREIGN KEY (`examId`) REFERENCES `exam` (`examId`) ON DELETE CASCADE ON UPDATE CASCADE"
			+ ") ;";

	public static final String CREATE_TABLE_SCORE = "CREATE TABLE `score` ("
			+ "  `scoreId` char(32) NOT NULL,"
			+ "  `examId` char(32) NOT NULL,"
			+ "  `studentId` varchar(32) NOT NULL,"
			+ "  `paperScore` float NOT NULL,"
			+ "  PRIMARY KEY (`scoreId`),"
			+ "  CONSTRAINT `FK_Reference_41` FOREIGN KEY (`examId`) REFERENCES `exam` (`examId`) ON DELETE CASCADE ON UPDATE CASCADE,"
			+ "  CONSTRAINT `FK_Reference_43` FOREIGN KEY (`studentId`) REFERENCES `student` (`studentId`) ON DELETE CASCADE ON UPDATE CASCADE"
			+ ") ;";

	public static final String CREATE_TABLE_STUDENT = "CREATE TABLE `student` ("
			+ "  `studentId` varchar(32) NOT NULL,"
			+ "  `studentName` varchar(32) DEFAULT NULL,"
			+ "  `studentPassword` varchar(32) NOT NULL,"
			+ "  PRIMARY KEY (`studentId`)" + ") ;";

	public static final String CREATE_TABLE_NOTICE = "CREATE TABLE `notice` ("
			+ "  `noticeId` char(32) NOT NULL,"
			+ "  `noticeName` varchar(100) NOT NULL,"
			+ "  `noticeContent` text NOT NULL,"
			+ "  `noticePublicTime` datetime NOT NULL,"
			+ "  PRIMARY KEY (`noticeId`)" + ") ;";

	public static final String CREATE_TABLE_REMARK = "CREATE TABLE `remark` ("
			+ "  `remarkId` char(32) NOT NULL,"
			+ "  `studentId` varchar(32) NOT NULL,"
			+ "  `remarkName` varchar(100) NOT NULL,"
			+ "  `remarkContent` text NOT NULL,"
			+ "  `remarkCreateTime` datetime NOT NULL,"
			+ "  `remarkUpdateTime` datetime NOT NULL,"
			+ "  PRIMARY KEY (`remarkId`),"
			+ "  CONSTRAINT `FK_Reference_67` FOREIGN KEY (`studentId`) REFERENCES `student` (`studentId`) ON DELETE CASCADE ON UPDATE CASCADE"
			+ ") ;";

	public static final String INSERT = "INSERT INTO ? (?) VALUES (?);";

	public static final String DELETE = "DELETE FROM ? WHERE ?;";

	public static final String UPDATE = "UPDATE ? SET ? WHERE ?;";

	public static final String SELECT = "SELECT ? FROM ?";
}
