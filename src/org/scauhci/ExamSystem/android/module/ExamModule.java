package org.scauhci.ExamSystem.android.module;

import java.util.ArrayList;
import java.util.HashMap;

import org.scauhci.ExamSystem.android.pojo.ExamPojo;
import org.scauhci.ExamSystem.android.pojo.PaperPojo;
import org.scauhci.ExamSystem.android.pojo.ScorePojo;
import org.scauhci.ExamSystem.android.pojo.SubmitAnswerPojo;
import org.scauhci.ExamSystem.android.service.ExamService;

public class ExamModule {
	ExamService examService;
	ExamListModule examListModule;
	PaperModule paperModule;
	ExamPojo examPojo;
	HashMap<String, Object> examData = new HashMap<String, Object>();
	PaperPojo paperPojo;

	public ExamModule(ExamPojo examPojo, ExamListModule examListModule) {
		this.examListModule = examListModule;
		this.examPojo = examPojo;
		examService = examListModule.getExamService();
		paperPojo = examService.getPaperPojoByExamPojo(examPojo);
		paperModule = new PaperModule(paperPojo, this);
		examData.put("examId", examPojo.getExamId());
		examData.put("examName", examPojo.getExamName());
		examData.put("examLastTime", examService.getLastTime(examPojo)
				.toMillis(false));
		examData.put("examStartTime", examPojo.getExamStartTime() == null ? ""
				: examPojo.getExamStartTime().format("%n月%j日 %H:%M"));
		examData.put("examEndTime", examPojo.getExamEndTime() == null ? ""
				: examPojo.getExamEndTime().format("%n月%j日 %H:%M"));
		examData.put("paperId", examPojo.getPaperId());
	}

	public PaperModule getPaperModule() {
		return paperModule;
	}

	public ExamPojo getExamPojo() {
		return examPojo;
	}

	protected ExamService getExamService() {
		return examService;
	}

	public HashMap<String, Object> getExamData() {
		return examData;
	}

	public float finishExam() {
		ScorePojo scorePojo;
		ArrayList<SubmitAnswerPojo> submitAnswerPojos = paperModule
				.submitPaper();

		for (SubmitAnswerPojo submitAnswerPojo : submitAnswerPojos) {
			submitAnswerPojo.setExamId(examPojo.getExamId());
		}

		scorePojo = examService.finishExam(submitAnswerPojos);

		return scorePojo.getExamScore();
	}
}
