package org.scauhci.ExamSystem.android.dao;

import java.util.ArrayList;

import org.scauhci.ExamSystem.android.pojo.ExamPojo;
import org.scauhci.ExamSystem.android.pojo.QuestionPojo;
import org.scauhci.ExamSystem.android.pojo.StudentPojo;
import org.scauhci.ExamSystem.android.pojo.SubmitAnswerPojo;
import org.scauhci.ExamSystem.android.tool.ExecuteResultFlag;

public class SubmitAnswerDao {

	public int add(SubmitAnswerPojo submitAnswerPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}

	public int delete(SubmitAnswerPojo submitAnswerPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}

	public int change(SubmitAnswerPojo submitAnswerPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}
	
	public SubmitAnswerPojo getSubmitAnswerPojoByStudentPojoAndExamPojoAndQuestionPojo(StudentPojo studentPojo, ExamPojo examPojo, QuestionPojo questionPojo) {
		SubmitAnswerPojo submitAnswerPojo = null;
		return submitAnswerPojo;
	}
	
	public ArrayList<SubmitAnswerPojo> getSubmitAnswerPojosOfExamPojoByStudentPojo(StudentPojo studentPojo, ExamPojo examPojo){
		ArrayList<SubmitAnswerPojo> submitAnswerPojos = null;
		return submitAnswerPojos;
	}
	
	public int getTotalScoreOfExamIdByStudentId(StudentPojo studentPojo, ExamPojo examPojo) {
		int totalScore = 0;
		return totalScore;
	}
}
