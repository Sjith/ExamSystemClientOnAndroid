package org.scauhci.ExamSystem.android.dao;

import java.util.ArrayList;

import org.scauhci.ExamSystem.android.pojo.CoursePojo;
import org.scauhci.ExamSystem.android.pojo.QuestionPojo;
import org.scauhci.ExamSystem.android.tool.ExecuteResultFlag;

public class QuestionDao {

	public int add(QuestionPojo questionPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}

	public int delete(QuestionPojo questionPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}

	public int change(QuestionPojo questionPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}
	
	public QuestionPojo getQuestionPojoByQuestionId(String questionId) {
		QuestionPojo questionPojo = null;
		return questionPojo;
	}
	
	public ArrayList<QuestionPojo> getQuestionPojosByCoursePojo(CoursePojo coursePojo) {
		ArrayList<QuestionPojo> questionPojos = null;
		return questionPojos;
	}
}
