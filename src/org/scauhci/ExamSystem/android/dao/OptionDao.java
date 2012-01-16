package org.scauhci.ExamSystem.android.dao;

import java.util.ArrayList;

import org.scauhci.ExamSystem.android.pojo.OptionPojo;
import org.scauhci.ExamSystem.android.pojo.QuestionPojo;
import org.scauhci.ExamSystem.android.tool.ExecuteResultFlag;

public class OptionDao {

	public int add(OptionPojo optionPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}

	public int delete(OptionPojo optionPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}

	public int change(OptionPojo optionPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}

	public OptionPojo getOptionPojoByOptionId(String optionId) {
		OptionPojo optionPojo = null;
		return optionPojo;
	}
	
	public ArrayList<OptionPojo> getOptionPojosByQuestionPojo(QuestionPojo questionPojo) {
		ArrayList<OptionPojo> optionPojos = null;
		return optionPojos;
	}
}
