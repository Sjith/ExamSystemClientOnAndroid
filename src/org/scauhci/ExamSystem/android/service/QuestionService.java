package org.scauhci.ExamSystem.android.service;

import java.util.ArrayList;

import org.scauhci.ExamSystem.android.dao.OptionDao;
import org.scauhci.ExamSystem.android.pojo.OptionPojo;
import org.scauhci.ExamSystem.android.pojo.QuestionPojo;
import org.scauhci.ExamSystem.android.tool.ExecuteResultFlag;

public class QuestionService {

	OptionDao optionDao = null;
	ExamService b = new ExamService();
	
	/*public ArrayList<QuestionPojo> getQuestionPojosByCoursePojo(CoursePojo coursePojo) {
		ArrayList<QuestionPojo> questionPojos = null;
		return questionPojos;
	}*/
	
	public ArrayList<OptionPojo> getOptionPojosByQuestionPojo(QuestionPojo questionPojo) {
		ArrayList<OptionPojo> optionPojos = null;
		return optionPojos;
	}

	public int addQuestionPojo(QuestionPojo questionPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}
	
	public int deleteQuestionPojo(QuestionPojo questionPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}

	public int addOptionPojo(OptionPojo optionPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}
	
	public int deleteOptionPojo(OptionPojo optionPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}
}
