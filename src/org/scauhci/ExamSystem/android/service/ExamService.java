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

	// targetTime初始化为系统当前时间，当注意系统当前时间是否与网络时间对对应。
	public Time getRemainTime(ExamPojo examPojo) {
		Time targetTime = new Time();
		targetTime.setToNow();

		return getRemainTime(examPojo, targetTime);
	}

	public Time getLastTime(ExamPojo examPojo) {
		return getRemainTime(examPojo, examPojo.getExamStartTime());
	}

	public Time getRemainTime(ExamPojo examPojo, Time targetTime) {
		Time remainTime = new Time();
		Time examEndTime = examPojo.getExamEndTime();
		Time examStartTime = examPojo.getExamStartTime();

		if (examPojo.getExamStartTime() != null && examStartTime != null
				&& examEndTime != null) {
			if (targetTime.before(examPojo.getExamStartTime())) {
				remainTime.set(targetTime.toMillis(false)
						- examStartTime.toMillis(false));
			} else {
				remainTime.set(examEndTime.toMillis(false)
						- targetTime.toMillis(false));
			}
		}

		return remainTime;
	}

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

	public SubmitAnswerPojo addSubmitAnswerPojo(
			SubmitAnswerPojo submitAnswerPojo) {

		if (submitAnswerPojo.getStudentId() == null) {
			submitAnswerPojo.setStudentId(StudentDao.getLatestStudentPojo()
					.getStudentId());
		}
		if (submitAnswerPojo.getQuestionStdScore() == 0) {
			ExamPojo examPojo = examDao.getExamPojoByExamId(submitAnswerPojo.getExamId());
			QuestionPojo questionPojo = questionDao.getQuestionPojoByQuestionId(submitAnswerPojo.getQuestionId());
			float questionStdScore = getStandardSubmitAnswerPojoByExamPojoAndQuestionPojo(examPojo, questionPojo).getQuestionStdScore();
			submitAnswerPojo.setQuestionStdScore(questionStdScore);
		}
		if (submitAnswerPojo.getQuestionScore() == 0) {
			ExamPojo examPojo = examDao.getExamPojoByExamId(submitAnswerPojo.getExamId());
			QuestionPojo questionPojo = questionDao.getQuestionPojoByQuestionId(submitAnswerPojo.getQuestionId());
			String standardSubmitAnswerContent = getStandardSubmitAnswerPojoByExamPojoAndQuestionPojo(examPojo, questionPojo).getSubmitAnswerContent();
			if (submitAnswerPojo.getSubmitAnswerContent().equals(standardSubmitAnswerContent)) {
				submitAnswerPojo.setQuestionScore(submitAnswerPojo.getQuestionStdScore());
			}
		}

		return submitAnswerDao.add(submitAnswerPojo);
	}

	public SubmitAnswerPojo deleteSubmitAnswerPojo(
			SubmitAnswerPojo submitAnswerPojo) {

		if (submitAnswerPojo.getStudentId() == null) {
			submitAnswerPojo.setStudentId(StudentDao.getLatestStudentPojo()
					.getStudentId());
		}

		return submitAnswerDao.delete(submitAnswerPojo);
	}

	public ScorePojo finishExam(ArrayList<SubmitAnswerPojo> submitAnswerPojos) {
		ScorePojo scorePojo = new ScorePojo();
		StudentPojo studentPojo = StudentDao.getLatestStudentPojo();
		ExamPojo examPojo = examDao.getExamPojoByExamId(submitAnswerPojos
				.get(0).getExamId());

		for (SubmitAnswerPojo submitAnswerPojo : submitAnswerPojos) {
			addSubmitAnswerPojo(submitAnswerPojo);
		}

		scorePojo.setExamId(examPojo.getExamId());
		scorePojo.setStudentId(studentPojo.getStudentId());
		scorePojo.setExamScore(submitAnswerDao
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
