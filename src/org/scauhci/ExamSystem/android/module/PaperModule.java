package org.scauhci.ExamSystem.android.module;

import java.util.ArrayList;
import java.util.HashMap;

import org.scauhci.ExamSystem.android.pojo.PaperPojo;
import org.scauhci.ExamSystem.android.pojo.QuestionOptionPojo;
import org.scauhci.ExamSystem.android.pojo.QuestionPojo;
import org.scauhci.ExamSystem.android.pojo.SubmitAnswerPojo;
import org.scauhci.ExamSystem.android.service.ExamService;
import org.scauhci.ExamSystem.android.service.QuestionService;
import org.scauhci.ExamSystem.android.tool.Flag;

public class PaperModule {
	ArrayList<HashMap<String, Object>> questionDatas;
	ArrayList<QuestionPojo> questionPojos;
	PaperPojo paperPojo;
	ExamService examService;
	QuestionService questionService;
	ExamModule examModule;

	public PaperModule(PaperPojo paperPojo, ExamModule examModule) {
		this.paperPojo = paperPojo;
		this.examModule = examModule;
		examService = examModule.getExamService();
		questionService = new QuestionService();
		initQuestionPojos();
		questionDatas = getAllQuestionData();
	}
	
	public void initQuestionPojos() {		
		questionPojos = examService.getQuestionPojosByPaperPojo(paperPojo);
	}

	public ArrayList<HashMap<String, Object>> getAllQuestionData() {
		questionDatas = new ArrayList<HashMap<String, Object>>();

		for (QuestionPojo questionPojo : questionPojos) {
			HashMap<String, Object> questionData = new HashMap<String, Object>();

			questionData.put("questionId", questionPojo.getQuestionId());
			questionData.put("questionContent",
					questionPojo.getQuestionContent());
			questionData.put("isQuestionStarted", false);
			questionData.put("questionAnswer", null);

			questionDatas.add(questionData);
		}

		return questionDatas;
	}
	
	private QuestionPojo getQuestionPojoByQuestionId(String questionId) {
		
		for (QuestionPojo questionPojo : questionPojos) {
			if (questionPojo.getQuestionId().equals(questionId)) {
				return questionPojo;
			}
		}
		
		return null;
	}
	
	public ArrayList<HashMap<String, Object>> getQuestionOptionDatasByQuestionId(String questionId){
		ArrayList<HashMap<String, Object>> questionOptionDatas = new ArrayList<HashMap<String, Object>>();
		QuestionPojo questionPojo = getQuestionPojoByQuestionId(questionId);
		
		ArrayList<QuestionOptionPojo> questionOptionPojos = questionService.getQuestionOptionPojosByQuestionPojo(questionPojo);
		
		for (QuestionOptionPojo questionOptionPojo : questionOptionPojos) {
			HashMap<String, Object> questionOptionData = new HashMap<String, Object>();
			
			questionOptionData.put("questionOptionId", questionOptionPojo.getQuestionOptionContent());
			questionOptionData.put("questionOptionContent", questionOptionPojo.getQuestionOptionContent());
			questionOptionData.put("isQuestionStdAnswer", questionOptionPojo.isQuestionStdAnswer());
			
			questionOptionDatas.add(questionOptionData);
		}
		
		return questionOptionDatas;
	}
	
	public ArrayList<SubmitAnswerPojo> submitPaper(){
		ArrayList<SubmitAnswerPojo> submitAnswerPojos = new ArrayList<SubmitAnswerPojo>();
		
		for (HashMap<String, Object> questionData : questionDatas) {
			SubmitAnswerPojo submitAnswerPojo = new SubmitAnswerPojo();
			
			submitAnswerPojo.setQuestionId(questionData.get("questionId") + "");
			String submitAnswerContent = questionData.get("questionAnswer") + "";
			submitAnswerPojo.setSubmitAnswerContent(submitAnswerContent);
			
			submitAnswerPojos.add(submitAnswerPojo);
		}
		
		return submitAnswerPojos;
	}
}
