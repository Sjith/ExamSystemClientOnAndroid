package org.scauhci.ExamSystem.android.analyzer;

import java.io.InputStream;

import org.scauhci.ExamSystem.android.dao.CourseDao;
import org.scauhci.ExamSystem.android.dao.ExamDao;
import org.scauhci.ExamSystem.android.dao.PaperDao;
import org.scauhci.ExamSystem.android.dao.QuestionDao;
import org.scauhci.ExamSystem.android.dao.QuestionOptionDao;
import org.scauhci.ExamSystem.android.dao.RelationPaperQuestionDao;
import org.scauhci.ExamSystem.android.dao.SubmitAnswerDao;
import org.scauhci.ExamSystem.android.pojo.CoursePojo;
import org.scauhci.ExamSystem.android.pojo.ExamPojo;
import org.scauhci.ExamSystem.android.pojo.PaperPojo;
import org.scauhci.ExamSystem.android.pojo.QuestionOptionPojo;
import org.scauhci.ExamSystem.android.pojo.QuestionPojo;
import org.scauhci.ExamSystem.android.pojo.RelationPaperQuestionPojo;
import org.scauhci.ExamSystem.android.pojo.SubmitAnswerPojo;
import org.scauhci.ExamSystem.android.tool.Flag;
import org.xmlpull.v1.XmlPullParser;

import android.net.Uri;
import android.text.format.Time;
import android.util.Xml;

public class PaperXmlAnalyzer {
	
	CourseDao courseDao = new CourseDao();
	PaperDao paperDao = new PaperDao();
	ExamDao examDao = new ExamDao();
	QuestionDao questionDao = new QuestionDao();
	RelationPaperQuestionDao relationPaperQuestionDao = new RelationPaperQuestionDao();
	SubmitAnswerDao submitAnswerDao = new SubmitAnswerDao();
	QuestionOptionDao questionOptionDao = new QuestionOptionDao();
	
	public PaperXmlAnalyzer() {
	}

	public int analysisXml(Uri xmlUri) {
		int executeResult = Flag.ERROR;
		return executeResult;
	}

	public int analysisXml(InputStream xmlInputStream) throws Exception {
		int executeResult = Flag.ERROR;

		XmlPullParser xmlPullParser = Xml.newPullParser();
		xmlPullParser.setInput(xmlInputStream, "utf-8");

		int eventCode = xmlPullParser.getEventType();
		while (eventCode != XmlPullParser.END_DOCUMENT) {
			PaperPojo paperPojo = null;
			ExamPojo examPojo = null;
			QuestionPojo questionPojo = null;
			RelationPaperQuestionPojo relationPaperQuestionPojo = null;
			SubmitAnswerPojo standardSubmitAnswerPojo = null;
			QuestionOptionPojo questionOptionPojo = null;
			CoursePojo coursePojo = null;
			String questionStdAnswer = null;
			switch (eventCode) {
			case XmlPullParser.START_DOCUMENT:
				break;
			case XmlPullParser.START_TAG:
				if (xmlPullParser.getName().equals("paper")) {
					
					paperPojo = new PaperPojo();
					
					paperPojo.setPaperType(Integer.parseInt(xmlPullParser
							.getAttributeValue(null, "paperType")));
					paperPojo.setPaperName(xmlPullParser.getAttributeValue(
							null, "paperName"));
					paperPojo.setTeacherId(xmlPullParser.getAttributeValue(
							null, "teacherId"));
					
					Time paperCreateTime = new Time();
					paperCreateTime.parse3339(xmlPullParser.getAttributeValue(
							null, "createTime"));
					paperPojo.setPaperCreateTime(paperCreateTime);
					
					paperPojo.setPaperTotalScore(Float.parseFloat(xmlPullParser
							.getAttributeValue(null, "totalScore")));
					paperPojo.setPaperExplain(xmlPullParser.getAttributeValue(
							null, "paperExplain"));
					
					coursePojo = new CoursePojo();
					coursePojo.setCourseName(xmlPullParser.getAttributeValue(
							null, "courseName"));
					if ((courseDao.completeCoursePojo(coursePojo)) == null){
						courseDao.add(coursePojo);
					} else {
						coursePojo = courseDao.completeCoursePojo(coursePojo);
					}
					
					paperPojo.setCourseId(coursePojo.getCourseId());
					
					paperDao.add(paperPojo);
					
					examPojo = new ExamPojo();
					
					examPojo.setPaperId(paperPojo.getPaperId());
					examPojo.setCourseId(paperPojo.getCourseId());
					examPojo.setTeacherId(paperPojo.getTeacherId());
					examPojo.setExamName(xmlPullParser.getAttributeValue(null,
							"examName"));
					examPojo.setExamExplain(xmlPullParser.getAttributeValue(
							null, "examExplain"));
					
					Time examCreateTime = new Time();
					examCreateTime.parse3339(xmlPullParser.getAttributeValue(
							null, "examCreateTime"));
					examPojo.setExamCreateTime(examCreateTime);
					
					Time examStartTime = new Time();
					examStartTime.parse3339(xmlPullParser.getAttributeValue(
							null, "examStartTime"));
					examPojo.setExamStartTime(examStartTime);
					
					Time examEndTime = new Time();
					examEndTime.parse3339(xmlPullParser.getAttributeValue(null,
							"examEndTime"));
					examPojo.setExamEndTime(examEndTime);
					
					examDao.add(examPojo);
					
				} else if (xmlPullParser.getName().equals("question")) {
					
					questionPojo = new QuestionPojo();
					
					questionPojo.setQuestionId(xmlPullParser.getAttributeValue(
							null, "questionId"));
					questionPojo.setCourseId(paperPojo.getCourseId());
					questionPojo.setQuestionContent(xmlPullParser
							.getAttributeValue(null, "content"));
					questionPojo.setQuestionType(Integer.parseInt(xmlPullParser
							.getAttributeValue(null, "type")));
					questionPojo.setQuestionStdAnswer(xmlPullParser
							.getAttributeValue(null, "questionStdAnswer"));
					
					questionStdAnswer = xmlPullParser.getAttributeValue(null,
							"stdAnswer");
					
					questionDao.add(questionPojo);
					
					relationPaperQuestionPojo = new RelationPaperQuestionPojo();

					relationPaperQuestionPojo.setExamId(examPojo.getExamId());
					relationPaperQuestionPojo.setPaperId(paperPojo.getPaperId());
					relationPaperQuestionPojo.setQuestionId(questionPojo
							.getQuestionId());
					relationPaperQuestionPojo.setQuestionIndex(Integer
							.parseInt(xmlPullParser.getAttributeValue(null,
									"index")));
					
					relationPaperQuestionDao.add(relationPaperQuestionPojo);
					
					standardSubmitAnswerPojo = new SubmitAnswerPojo();
					
					standardSubmitAnswerPojo.setExamId(examPojo.getExamId());
					standardSubmitAnswerPojo.setQuestionId(questionPojo.getQuestionId());
					standardSubmitAnswerPojo.setStudentId(Flag.STANDARD);
					standardSubmitAnswerPojo.setQuestionScore(Float
							.parseFloat(xmlPullParser.getAttributeValue(null,
									"score")));
					standardSubmitAnswerPojo.setQuestionStdScore(Float
							.parseFloat(xmlPullParser.getAttributeValue(null,
									"stdScore")));
					
					submitAnswerDao.add(standardSubmitAnswerPojo);
					
				} else if (xmlPullParser.getName().equals("option")) {
					
					questionOptionPojo = new QuestionOptionPojo();
					
					questionOptionPojo.setQuestionId(questionPojo
							.getQuestionId());
					questionOptionPojo.setQuestionOptionContent(xmlPullParser
							.getAttributeValue(null, "value"));
					if (questionStdAnswer.equals(xmlPullParser
							.getAttributeValue(null, "index"))) {
						questionOptionPojo.setQuestionStdAnswer(true);
					}
					
					questionOptionDao.add(questionOptionPojo);
					
				}
				break;
			case XmlPullParser.END_TAG:
				break;
			default:
				break;
			}
			
			eventCode = xmlPullParser.next();
		}

		return executeResult;
	}
}

