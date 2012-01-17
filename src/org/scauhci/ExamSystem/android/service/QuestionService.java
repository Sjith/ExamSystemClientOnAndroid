package org.scauhci.ExamSystem.android.service;

import java.util.ArrayList;

import org.scauhci.ExamSystem.android.dao.QuestionOptionDao;
import org.scauhci.ExamSystem.android.pojo.QuestionOptionPojo;
import org.scauhci.ExamSystem.android.pojo.QuestionPojo;
import org.scauhci.ExamSystem.android.tool.ExecuteResultFlag;

public class QuestionService {

	QuestionOptionDao questionOptionDao = null;
	ExamService b = new ExamService();
	
	/*public ArrayList<QuestionPojo> getQuestionPojosByCoursePojo(CoursePojo coursePojo) {
		ArrayList<QuestionPojo> questionPojos = null;
		return questionPojos;
	}*/
	
	public ArrayList<QuestionOptionPojo> getQuestionOptionPojosByQuestionPojo(QuestionPojo questionPojo) {
		ArrayList<QuestionOptionPojo> questionOptionPojos = null;
		return questionOptionPojos;
	}

	public int addQuestionPojo(QuestionPojo questionPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}
	
	public int deleteQuestionPojo(QuestionPojo questionPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}

	public int addQuestionOptionPojo(QuestionOptionPojo questionOptionPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}
	
	public int deleteQuestionOptionPojo(QuestionOptionPojo questionOptionPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}
}