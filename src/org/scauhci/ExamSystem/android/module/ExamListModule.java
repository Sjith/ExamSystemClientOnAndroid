package org.scauhci.ExamSystem.android.module;

import java.util.ArrayList;
import java.util.HashMap;

import org.scauhci.ExamSystem.android.pojo.ExamPojo;
import org.scauhci.ExamSystem.android.service.ExamService;
import org.scauhci.ExamSystem.android.tool.Flag;

import android.text.format.Time;
import android.util.Log;

public class ExamListModule {
	ArrayList<HashMap<String, Object>> examListItemDatas;
	ArrayList<ExamPojo> examPojos;
	ExamService examService;
	
	private static ExamListModule instance = new ExamListModule();

	private ExamListModule(){
		examService = new ExamService();		
	}
	
	public static ExamListModule getInstance(){
		return instance;
	}
	
	public ExamService getExamService() {
		return examService;
	}

	public ArrayList<HashMap<String, Object>> getExamListItemDatas() {
		return examListItemDatas;
	}

	public ArrayList<HashMap<String, Object>> getAllExamListItemData() {
		examListItemDatas = new ArrayList<HashMap<String, Object>>();
		examPojos = examService.getAllExamPojo();

		for (ExamPojo examPojo : examPojos) {
			HashMap<String, Object> examListItemData = new HashMap<String, Object>();
			examListItemData.put("examId", examPojo.getExamId());
			examListItemData.put("examName", examPojo.getExamName());
			examListItemData.put("examLastTime",
					examService.getLastTime(examPojo).toMillis(false));
			examListItemData.put("examStartTime",
					examPojo.getExamStartTime() == null ? "" : examPojo
							.getExamStartTime().format("%n月%j日 %H:%M"));
			examListItemData.put("examEndTime",
					examPojo.getExamEndTime() == null ? "" : examPojo
							.getExamEndTime().format("%n月%j日 %H:%M"));
			examListItemData.put("paperId", examPojo.getPaperId());
			examListItemDatas.add(examListItemData);
		}

		return examListItemDatas;
	}
	
	public ExamModule getExamModule(String examId){
		ExamModule examModule = null;
		
		for (ExamPojo examPojo : examPojos) {
			if (examPojo.getExamId().equals(examId)){
				examModule = new ExamModule(examPojo, this);
			}
		}
		
		return examModule;
	}
	
	/* TODO Incomplete Method. This method should notify corresponding method to update.*/
	public void addExamPojo(String examName, Time examStartTime, Time examEndTime, String paperId){
		ExamPojo examPojo = new ExamPojo();
		
		Time examCreateTime = new Time();
		examCreateTime.setToNow();
		
		examPojo.setExamName(examName);
		examPojo.setExamCreateTime(examCreateTime);
		examPojo.setExamStartTime(examStartTime);
		examPojo.setExamEndTime(examEndTime);
		examPojo.setPaperId(paperId);
		
		examService.addExamPojo(examPojo);
	}
}
