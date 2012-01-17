package org.scauhci.ExamSystem.android.dao;

import java.util.ArrayList;

import org.scauhci.ExamSystem.android.pojo.QuestionOptionPojo;
import org.scauhci.ExamSystem.android.pojo.QuestionPojo;
import org.scauhci.ExamSystem.android.tool.ExecuteResultFlag;

public class QuestionOptionDao {

	public int add(QuestionOptionPojo questionOptionPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}

	public int delete(QuestionOptionPojo questionOptionPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}

	public int change(QuestionOptionPojo questionOptionPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}

	public QuestionOptionPojo getQuestionOptionPojoByQuestionOptionId(String questionOptionId) {
		QuestionOptionPojo questionOptionPojo = null;
		return questionOptionPojo;
	}
	
	public ArrayList<QuestionOptionPojo> getOptionPojosByQuestionPojo(QuestionPojo questionPojo) {
		ArrayList<QuestionOptionPojo> optionPojos = null;
		return optionPojos;
	}
}
