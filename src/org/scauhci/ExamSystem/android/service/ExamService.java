package org.scauhci.ExamSystem.android.service;

import java.util.ArrayList;

import org.scauhci.ExamSystem.android.dao.ExamDao;
import org.scauhci.ExamSystem.android.dao.PaperDao;
import org.scauhci.ExamSystem.android.dao.QuestionDao;
import org.scauhci.ExamSystem.android.dao.RelationPaperQuestionDao;
import org.scauhci.ExamSystem.android.dao.ScoreDao;
import org.scauhci.ExamSystem.android.dao.StudentDao;
import org.scauhci.ExamSystem.android.dao.SubmitAnswerDao;
import org.scauhci.ExamSystem.android.pojo.ExamPojo;
import org.scauhci.ExamSystem.android.pojo.PaperPojo;
import org.scauhci.ExamSystem.android.pojo.QuestionPojo;
import org.scauhci.ExamSystem.android.pojo.RelationPaperQuestionPojo;
import org.scauhci.ExamSystem.android.pojo.ScorePojo;
import org.scauhci.ExamSystem.android.pojo.StudentPojo;
import org.scauhci.ExamSystem.android.pojo.SubmitAnswerPojo;
import org.scauhci.ExamSystem.android.tool.Flag;

import android.text.format.Time;

public class ExamService {

	ExamDao examDao = new ExamDao();
	PaperDao paperDao = new PaperDao();
	ScoreDao scoreDao = new ScoreDao();
	QuestionDao questionDao = new QuestionDao();
	SubmitAnswerDao submitAnswerDao = new SubmitAnswerDao();
	RelationPaperQuestionDao relationPaperQuestionDao = new RelationPaperQuestionDao();

	public Time getRemainTime(ExamPojo examPojo) {
		Time remainTime = new Time();
		Time targetTime = new Time();
		Time examEndTime = examPojo.getExamEndTime();
		Time examStartTime = examPojo.getExamStartTime();

		targetTime.setToNow();
		if (targetTime.before(examPojo.getExamStartTime())) {
			remainTime.set(targetTime.toMillis(false)
					- examStartTime.toMillis(false));
		} else {
			remainTime.set(examEndTime.toMillis(false)
					- targetTime.toMillis(false));
		}

		return remainTime;
	}

	/*
	 * public ExamPojo getLatestExamPojo() { return ExamDao.getLatestExamPojo();
	 * }
	 * 
	 * public PaperPojo getLatestPaperPojo() { return
	 * getPaperPojoByExamPojo(getLatestExamPojo()); }
	 */

	public ArrayList<ExamPojo> getAllExamPojo() {
		return examDao.getAllExamPojo();
	}

	public ArrayList<PaperPojo> getAllPaperPojo() {
		return paperDao.getAllPaperPojo();
	}

	public PaperPojo getPaperPojoByExamPojo(ExamPojo examPojo) {
		examPojo = examDao.completeExamPojo(examPojo);
		return paperDao.getPaperPojoByPaperId(examPojo.getPaperId());
	}

	public QuestionPojo getQuestionPojoByRelationPaperQuestionPojo(
			RelationPaperQuestionPojo relationPaperQuestionPojo) {

		relationPaperQuestionPojo = relationPaperQuestionDao
				.completeRelationPaperQuestionPojo(relationPaperQuestionPojo);

		return questionDao
				.getQuestionPojoByQuestionId(relationPaperQuestionPojo
						.getQuestionId());
	}

	public ArrayList<QuestionPojo> getQuestionPojosByPaperPojo(
			PaperPojo paperPojo) {
		ArrayList<RelationPaperQuestionPojo> relationPaperQuestionPojos = relationPaperQuestionDao
				.getRelationPaperQuestionPojosByPaperPojo(paperPojo);
		ArrayList<QuestionPojo> questionPojos = new ArrayList<QuestionPojo>();

		for (RelationPaperQuestionPojo relationPaperQuestionPojo : relationPaperQuestionPojos) {
			QuestionPojo questionPojo = questionDao
					.getQuestionPojoByQuestionId(relationPaperQuestionPojo
							.getQuestionId());
			questionPojos.add(questionPojo);
		}

		return questionPojos;
	}

	public SubmitAnswerPojo getStandardSubmitAnswerPojoByExamPojoAndQuestionPojo(
			ExamPojo examPojo, QuestionPojo questionPojo) {
		return submitAnswerDao
				.getStandardSubmitAnswerPojoByExamPojoAndQuestionPojo(examPojo,
						questionPojo);
	}

	public ArrayList<SubmitAnswerPojo> getStandardSubmitAnswerPojosOfExamPojo(
			ExamPojo examPojo) {
		return submitAnswerDao.getStandardSubmitAnswerPojosOfExamPojo(examPojo);
	}

	public SubmitAnswerPojo addSubmitAnswerPojo(SubmitAnswerPojo submitAnswerPojo) {

		if (submitAnswerPojo.getStudentId() == null) {
			submitAnswerPojo.setStudentId(StudentDao.getLatestStudentPojo()
					.getStudentId());
		}
		
		return submitAnswerDao.add(submitAnswerPojo);
	}

	public SubmitAnswerPojo deleteSubmitAnswerPojo(SubmitAnswerPojo submitAnswerPojo) {

		if (submitAnswerPojo.getStudentId() == null) {
			submitAnswerPojo.setStudentId(StudentDao.getLatestStudentPojo()
					.getStudentId());
		}

		return submitAnswerDao.delete(submitAnswerPojo);
	}

	/* incomplete */
	public ScorePojo finishExam(ArrayList<SubmitAnswerPojo> submitAnswerPojos) {
		/*
		 * Did't verify whether the value of submitAnswerPojo is identical and
		 * whether the value of studentPojo and submitAnswerPojo is identical.
		 */
		ScorePojo scorePojo = new ScorePojo();
		StudentPojo studentPojo = StudentDao.getLatestStudentPojo();
		ExamPojo examPojo = examDao.getExamPojoByExamId(submitAnswerPojos
				.get(0).getExamId());

		for (SubmitAnswerPojo submitAnswerPojo : submitAnswerPojos) {
			addSubmitAnswerPojo(submitAnswerPojo);
		}

		scorePojo.setExamId(examPojo.getExamId());
		scorePojo.setStudentId(studentPojo.getStudentId());
		scorePojo.setScore(submitAnswerDao
				.getTotalScoreOfExamPojoByStudentPojo(studentPojo, examPojo));
		
		return scoreDao.add(scorePojo);
	}

	public PaperPojo addPaperPojo(PaperPojo paperPojo) {

		return paperDao.add(paperPojo);
	}

	public PaperPojo deletePaperPojo(PaperPojo paperPojo) {

		return paperDao.delete(paperPojo);
	}

	public ExamPojo addExamPojo(ExamPojo examPojo) {

		return examDao.add(examPojo);
	}

	public ExamPojo deleteExamPojo(ExamPojo examPojo) {

		return examDao.delete(examPojo);
	}

	public RelationPaperQuestionPojo addRelationPaperQuestionPojo(
			RelationPaperQuestionPojo relationPaperQuestionPojo) {

		return relationPaperQuestionDao.add(relationPaperQuestionPojo);
	}

	public RelationPaperQuestionPojo deleteRelationPaperQuestionPojo(
			RelationPaperQuestionPojo relationPaperQuestionPojo) {

		return relationPaperQuestionDao.delete(relationPaperQuestionPojo);
	}
}
