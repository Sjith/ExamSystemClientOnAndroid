package org.scauhci.ExamSystem.android.module;

import java.util.ArrayList;
import java.util.HashMap;

import org.scauhci.ExamSystem.android.pojo.ExamPojo;
import org.scauhci.ExamSystem.android.pojo.PaperPojo;
import org.scauhci.ExamSystem.android.pojo.QuestionOptionPojo;
import org.scauhci.ExamSystem.android.pojo.QuestionPojo;
import org.scauhci.ExamSystem.android.service.ExamService;
import org.scauhci.ExamSystem.android.service.QuestionService;

public class ExamModule {
	ExamService examService = new ExamService();
	QuestionService questionService = new QuestionService();

	public ArrayList<HashMap<String,Object>> getAllExamListItemData(){
		ArrayList<HashMap<String, Object>> examListItemDatas = new ArrayList<HashMap<String, Object>>();
		ArrayList<ExamPojo> examPojos = examService.getAllExamPojo();
		
		for (ExamPojo examPojo : examPojos) {
			HashMap<String, Object> examListItemData = new HashMap<String, Object>();
			examListItemData.put("examName", examPojo.getExamName());
			examListItemData.put("examLastTime", examService.getLastTime(examPojo).toMillis(false));
			examListItemData.put("examStartTime", examPojo.getExamStartTime() == null ? "" : examPojo.getExamStartTime().format("%n月%j日 %H:%M"));
			examListItemData.put("examEndTime", examPojo.getExamEndTime() == null ? "" : examPojo.getExamEndTime().format("%n月%j日 %H:%M"));
			examListItemData.put("paperId", examPojo.getPaperId());
			examListItemDatas.add(examListItemData);
		}
		
		return examListItemDatas;
	}
	
	public ArrayList<HashMap<String, Object>> getQuestionDatasByPaperId(String paperId){
		PaperPojo paperPojo = new PaperPojo();
		ArrayList<HashMap<String, Object>> questionDatas = new ArrayList<HashMap<String, Object>>();
		
		paperPojo.setPaperId(paperId);
		ArrayList<QuestionPojo> questionPojos = examService.getQuestionPojosByPaperPojo(paperPojo);
		for (QuestionPojo questionPojo : questionPojos) {
			HashMap<String, Object> questionData = new HashMap<String, Object>();
			
			questionData.put("questionId", questionPojo.getQuestionId());
			questionData.put("questionContent", questionPojo.getQuestionContent());
			
			questionDatas.add(questionData);
		}
		
		return questionDatas;
	}
	
	public ArrayList<HashMap<String, Object>> getQuestionOptionDatasByQuestionId(String questionId){
		ArrayList<HashMap<String, Object>> questionOptionDatas = new ArrayList<HashMap<String, Object>>();
		QuestionPojo questionPojo = new QuestionPojo();
		
		questionPojo.setQuestionId(questionId);
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
}
