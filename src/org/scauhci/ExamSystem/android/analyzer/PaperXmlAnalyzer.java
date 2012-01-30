package org.scauhci.ExamSystem.android.analyzer;

import java.io.InputStream;

import org.scauhci.ExamSystem.android.pojo.ExamPojo;
import org.scauhci.ExamSystem.android.pojo.PaperPojo;
import org.scauhci.ExamSystem.android.pojo.QuestionOptionPojo;
import org.scauhci.ExamSystem.android.pojo.QuestionPojo;
import org.scauhci.ExamSystem.android.pojo.RelationPaperQuestionPojo;
import org.scauhci.ExamSystem.android.pojo.SubmitAnswerPojo;
import org.scauhci.ExamSystem.android.service.ExamService;
import org.scauhci.ExamSystem.android.service.QuestionService;
import org.scauhci.ExamSystem.android.tool.ExecuteResultFlag;
import org.xmlpull.v1.XmlPullParser;

import android.net.Uri;
import android.text.format.Time;
import android.util.Xml;

public class PaperXmlAnalyzer {
	public PaperXmlAnalyzer() {
	}

	public int analysisXml(Uri xmlUri) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}

	public int analysisXml(InputStream xmlInputStream) throws Exception {
		int executeResult = ExecuteResultFlag.ERROR;
		ExamService examService = new ExamService();
		QuestionService questionService = new QuestionService();

		XmlPullParser xmlPullParser = Xml.newPullParser();
		xmlPullParser.setInput(xmlInputStream, "utf-8");

		int eventCode = xmlPullParser.getEventType();
		while (eventCode != XmlPullParser.END_DOCUMENT) {
			PaperPojo paperPojo = null;
			ExamPojo examPojo = null;
			QuestionPojo questionPojo = null;
			RelationPaperQuestionPojo relationPaperQuestionPojo = null;
			SubmitAnswerPojo submitAnswerPojo = null;
			QuestionOptionPojo questionOptionPojo = null;
			String questionStdAnswer = null;
			switch (eventCode) {
			case XmlPullParser.START_DOCUMENT:
				break;
			case XmlPullParser.START_TAG:
				if (xmlPullParser.getName().equals("paper")) {
					paperPojo = new PaperPojo();
					examPojo = new ExamPojo();
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
				} else if (xmlPullParser.getName().equals("question")) {
					questionPojo = new QuestionPojo();
					submitAnswerPojo = new SubmitAnswerPojo();
					relationPaperQuestionPojo = new RelationPaperQuestionPojo();
					questionPojo.setQuestionId(xmlPullParser.getAttributeValue(
							null, "questionId"));
					questionPojo.setQuestionType(Integer.parseInt(xmlPullParser
							.getAttributeValue(null, "type")));
					questionPojo.setQuestionContent(xmlPullParser
							.getAttributeValue(null, "content"));
					questionPojo.setQuestionStdAnswer(xmlPullParser
							.getAttributeValue(null, "questionStdAnswer"));
					questionStdAnswer = xmlPullParser.getAttributeValue(null,
							"stdAnswer");
					relationPaperQuestionPojo.setQuestionId(questionPojo
							.getQuestionId());
					relationPaperQuestionPojo.setQuestionIndex(Integer
							.parseInt(xmlPullParser.getAttributeValue(null,
									"index")));
					submitAnswerPojo.setQuestionScore(Float
							.parseFloat(xmlPullParser.getAttributeValue(null,
									"score")));
					submitAnswerPojo.setQuestionStdScore(Float
							.parseFloat(xmlPullParser.getAttributeValue(null,
									"stdScore")));
				} else if (xmlPullParser.getName().equals("option")) {
					questionOptionPojo = new QuestionOptionPojo();
					questionOptionPojo.setQuestionId(questionPojo
							.getQuestionId());
					questionOptionPojo.setQuestionOptionContent(xmlPullParser
							.getAttributeValue(null, "value"));
					if (questionStdAnswer.equals(xmlPullParser
							.getAttributeValue(null, "index"))) {
						questionOptionPojo.setQuestionStdAnswer(true);
					} else {
						questionOptionPojo.setQuestionStdAnswer(false);
					}
				}
				break;
			case XmlPullParser.END_TAG:
				if (xmlPullParser.getName().equals("paper")) {
					examService.addPaperPojo(paperPojo);
					examService.addExamPojo(examPojo);
				} else if (xmlPullParser.getName().equals("question")) {
					questionService.addQuestionPojo(questionPojo);
				} else if (xmlPullParser.getName().equals("option")) {
					questionService.addQuestionOptionPojo(questionOptionPojo);
				}
				break;
			default:
				break;
			}
		}

		return executeResult;
	}
}

