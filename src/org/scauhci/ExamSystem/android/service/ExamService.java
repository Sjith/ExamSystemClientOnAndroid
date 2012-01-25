package org.scauhci.ExamSystem.android.service;

import java.util.ArrayList;

import org.scauhci.ExamSystem.android.dao.ExamDao;
import org.scauhci.ExamSystem.android.dao.PaperDao;
import org.scauhci.ExamSystem.android.dao.QuestionDao;
import org.scauhci.ExamSystem.android.dao.RelationPaperQuestionDao;
import org.scauhci.ExamSystem.android.dao.SubmitAnswerDao;
import org.scauhci.ExamSystem.android.pojo.ExamPojo;
import org.scauhci.ExamSystem.android.pojo.PaperPojo;
import org.scauhci.ExamSystem.android.pojo.QuestionPojo;
import org.scauhci.ExamSystem.android.pojo.RelationPaperQuestionPojo;
import org.scauhci.ExamSystem.android.pojo.SubmitAnswerPojo;
import org.scauhci.ExamSystem.android.tool.ExecuteResultFlag;

import android.text.format.Time;

public class ExamService {

	ExamDao examDao = new ExamDao();
	ExamPojo examPojo = new ExamPojo();
	PaperDao paperDao = new PaperDao();

	public ExamPojo getExamPojo() {
		return examDao.getLatestExamPojo();
	}

	public Time getRemainTime() {
		return examDao.getRemainTime();
	}

	public PaperPojo getPaperPojoByExamPojo(ExamPojo examPojo) {
		return paperDao.getPaperPojoByPaperId(examPojo.getPaperId());
	}

	public ArrayList<QuestionPojo> getQuestionPojosByPaperPojo(
			PaperPojo paperPojo) {
		RelationPaperQuestionDao relationPaperQuestionDao = new RelationPaperQuestionDao();
		ArrayList<RelationPaperQuestionPojo> relationPaperQuestionPojos = relationPaperQuestionDao
				.getRelationPaperQuestionPojosByPaperPojo(paperPojo);
		QuestionDao questionDao = new QuestionDao();
		ArrayList<QuestionPojo> questionPojos = new ArrayList<QuestionPojo>();
		
		for (RelationPaperQuestionPojo relationPaperQuestionPojo : relationPaperQuestionPojos) {
			QuestionPojo questionPojo = questionDao
					.getQuestionPojoByQuestionId(relationPaperQuestionPojo
							.getQuestionId());
			questionPojos.add(questionPojo);
		}
		
		return questionPojos;
	}

	public int addSubmitAnswerPojo(SubmitAnswerPojo submitAnswerPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		
		SubmitAnswerDao submitAnswerDao = new SubmitAnswerDao();
		submitAnswerDao.add(submitAnswerPojo);
		
		return executeResult;
	}

	public int deleteSubmitAnswerPojo(SubmitAnswerPojo submitAnswerPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		
		SubmitAnswerDao submitAnswerDao = new SubmitAnswerDao();
		submitAnswerDao.delete(submitAnswerPojo);
		
		return executeResult;
	}

	public int addPaperPojo(PaperPojo paperPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		
		PaperDao paperDao = new PaperDao();
		paperDao.add(paperPojo);
		
		return executeResult;
	}

	public int deletePaperPojo(PaperPojo paperPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		
		PaperDao paperDao = new PaperDao();
		paperDao.delete(paperPojo);
		
		return executeResult;
	}

	public int addExamPojo(ExamPojo examPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		
		ExamDao examDao = new ExamDao();
		examDao.add(examPojo);
		
		return executeResult;
	}

	public int deleteExamPojo(ExamPojo examPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		
		ExamDao examDao = new ExamDao();
		examDao.delete(examPojo);
		
		return executeResult;
	}
}
