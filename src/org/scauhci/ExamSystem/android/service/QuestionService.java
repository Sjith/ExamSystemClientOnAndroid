package org.scauhci.ExamSystem.android.service;

import java.util.ArrayList;

import org.scauhci.ExamSystem.android.dao.QuestionDao;
import org.scauhci.ExamSystem.android.dao.QuestionOptionDao;
import org.scauhci.ExamSystem.android.pojo.QuestionOptionPojo;
import org.scauhci.ExamSystem.android.pojo.QuestionPojo;
import org.scauhci.ExamSystem.android.tool.Flag;

public class QuestionService {

	QuestionOptionDao questionOptionDao = new QuestionOptionDao();
	QuestionDao questionDao = new QuestionDao();
	
	/*This method is incomplete.*/
	/*public ArrayList<QuestionPojo> getQuestionPojosByCoursePojo(CoursePojo coursePojo) {
		ArrayList<QuestionPojo> questionPojos = null;
		return questionPojos;
	}*/
	
	public ArrayList<QuestionOptionPojo> getQuestionOptionPojosByQuestionPojo(QuestionPojo questionPojo) {
		ArrayList<QuestionOptionPojo> questionOptionPojos = null;
		
		questionOptionPojos = questionOptionDao.getQuestionOptionPojosByQuestionPojo(questionPojo);
		
		return questionOptionPojos;
	}

	public QuestionPojo addQuestionPojo(QuestionPojo questionPojo) {
		
		return questionDao.add(questionPojo);
	}
	
	public QuestionPojo deleteQuestionPojo(QuestionPojo questionPojo) {

		return questionDao.delete(questionPojo);
	}

	public QuestionOptionPojo addQuestionOptionPojo(QuestionOptionPojo questionOptionPojo) {
		
		return questionOptionDao.add(questionOptionPojo);
	}
	
	public QuestionOptionPojo deleteQuestionOptionPojo(QuestionOptionPojo questionOptionPojo) {

		return questionOptionDao.delete(questionOptionPojo);
	}
}
