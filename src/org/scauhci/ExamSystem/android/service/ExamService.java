package org.scauhci.ExamSystem.android.service;

import java.util.ArrayList;

import org.scauhci.ExamSystem.android.dao.ExamDao;
import org.scauhci.ExamSystem.android.dao.PaperDao;
import org.scauhci.ExamSystem.android.pojo.ExamPojo;
import org.scauhci.ExamSystem.android.pojo.PaperPojo;
import org.scauhci.ExamSystem.android.pojo.QuestionPojo;
import org.scauhci.ExamSystem.android.pojo.SubmitAnswerPojo;
import org.scauhci.ExamSystem.android.tool.ExecuteResultFlag;

import android.text.format.Time;

public class ExamService {

	ExamDao examDao = null;
	ExamPojo examPojo = null;
	PaperDao paperDao = null;

	public ExamPojo getExamPojo() {
		return examDao.getExamPojo();
	}
	
	public Time getRemainTime() {
		return examDao.getRemainTime();
	}
	
	public PaperPojo getPaperPojoByExamPojo(ExamPojo examPojo) {
		return paperDao.getPaperPojoByPaperId(examPojo.getPaperId());
	}

	public ArrayList<QuestionPojo> getQuestionPojosByPaperPojo(PaperPojo paperPojo) {
		ArrayList<QuestionPojo> questionPojos = null;
		return questionPojos;
	}
	
	public int addSubmitAnswerPojo(SubmitAnswerPojo submitAnswerPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}
	
	public int deleteSubmitAnswerPojo(SubmitAnswerPojo submitAnswerPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}
	
	public int addPaperPojo(PaperPojo paperPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}
	
	public int deletePaperPojo(PaperPojo paperPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}
	
	public int addExamPojo(ExamPojo examPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}
	
	public int deleteExamPojo(ExamPojo examPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;

	}
}
