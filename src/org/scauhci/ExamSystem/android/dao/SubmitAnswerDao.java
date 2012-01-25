package org.scauhci.ExamSystem.android.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.scauhci.ExamSystem.android.pojo.ExamPojo;
import org.scauhci.ExamSystem.android.pojo.QuestionPojo;
import org.scauhci.ExamSystem.android.pojo.StudentPojo;
import org.scauhci.ExamSystem.android.pojo.SubmitAnswerPojo;
import org.scauhci.ExamSystem.android.tool.ExecuteResultFlag;

import android.database.Cursor;

public class SubmitAnswerDao {

	DaoHelper daoHelper = new DaoHelper(null, "exam_online.db", null, 0);
	String tableName = "submitAnswer";

	public SubmitAnswerDao() {
		// update();
	}

	public int add(SubmitAnswerPojo submitAnswerPojo) {
		int executeResult = ExecuteResultFlag.ERROR;

		String[] keys = { "submitAnswerId", "questionId", "examId",
				"studentId", "questionStdScore", "questionScore" };
		String[] values = { submitAnswerPojo.getSubmitAnswerId(),
				submitAnswerPojo.getQuestionId(), submitAnswerPojo.getExamId(),
				submitAnswerPojo.getStudentId(),
				submitAnswerPojo.getQuestionStdScore() + "",
				submitAnswerPojo.getQuestionScore() + "" };

		daoHelper.insert(tableName, keys, values);

		return executeResult;
	}

	public int delete(SubmitAnswerPojo submitAnswerPojo) {
		int executeResult = ExecuteResultFlag.ERROR;

		String[] keys = { "submitAnswerId" };
		String[] values = { submitAnswerPojo.getSubmitAnswerId() };

		daoHelper.delete(tableName, keys, values);

		return executeResult;
	}

	public int change(SubmitAnswerPojo submitAnswerPojo) {
		int executeResult = ExecuteResultFlag.ERROR;

		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		if (submitAnswerPojo.getSubmitAnswerId() != null) {
			keyValueMap.put("submitAnswerId",
					submitAnswerPojo.getSubmitAnswerId());
		}
		if (submitAnswerPojo.getExamId() != null) {
			keyValueMap.put("examId", submitAnswerPojo.getExamId());
		}
		if (submitAnswerPojo.getQuestionId() != null) {
			keyValueMap.put("questionId", submitAnswerPojo.getQuestionId());
		}
		if (submitAnswerPojo.getStudentId() != null) {
			keyValueMap.put("studentId", submitAnswerPojo.getStudentId());
		}
		if (submitAnswerPojo.getQuestionStdScore() != 0) {
			keyValueMap.put("questionStdScore",
					submitAnswerPojo.getQuestionStdScore() + "");
		}
		if (submitAnswerPojo.getQuestionScore() != 0) {
			keyValueMap.put("questionScore",
					submitAnswerPojo.getQuestionScore() + "");
		}

		String[] keys = new String[keyValueMap.size()];
		keyValueMap.keySet().toArray(keys);
		String[] newValues = new String[keys.length];
		keyValueMap.values().toArray(newValues);
		String[] whereConditionKeys = { "submitAnswerId" };
		String[] whereConditionValues = { submitAnswerPojo.getSubmitAnswerId() };

		daoHelper.update(tableName, keys, newValues, whereConditionKeys,
				whereConditionValues);

		return executeResult;
	}

	public SubmitAnswerPojo getSubmitAnswerPojoByStudentPojoAndExamPojoAndQuestionPojo(
			StudentPojo studentPojo, ExamPojo examPojo,
			QuestionPojo questionPojo) {
		SubmitAnswerPojo submitAnswerPojo = new SubmitAnswerPojo();

		String[] keys = { "*" };
		String[] whereConditionKeys = { "studentId", "examId", "questionId" };
		String[] whereConditionValues = { studentPojo.getStudentId(),
				examPojo.getExamId(), questionPojo.getQuestionId() };

		Cursor submitAnswerCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		while (submitAnswerCursor.moveToNext()) {
			submitAnswerPojo.setSubmitAnswerId(submitAnswerCursor
					.getString(submitAnswerCursor
							.getColumnIndex("submitAnswerId")));
			submitAnswerPojo.setExamId(submitAnswerCursor
					.getString(submitAnswerCursor.getColumnIndex("examId")));
			submitAnswerPojo
					.setQuestionId(submitAnswerCursor
							.getString(submitAnswerCursor
									.getColumnIndex("questionId")));
			submitAnswerPojo.setStudentId(submitAnswerCursor
					.getString(submitAnswerCursor.getColumnIndex("studentId")));
			submitAnswerPojo.setQuestionScore(submitAnswerCursor
					.getFloat(submitAnswerCursor
							.getColumnIndex("questionScore")));
			submitAnswerPojo.setQuestionStdScore(submitAnswerCursor
					.getFloat(submitAnswerCursor
							.getColumnIndex("questionStdScore")));
		}

		return submitAnswerPojo;
	}

	public ArrayList<SubmitAnswerPojo> getSubmitAnswerPojosOfExamPojoByStudentPojo(
			StudentPojo studentPojo, ExamPojo examPojo) {
		ArrayList<SubmitAnswerPojo> submitAnswerPojos = new ArrayList<SubmitAnswerPojo>();

		String[] keys = { "*" };
		String[] whereConditionKeys = { "studentId", "examId" };
		String[] whereConditionValues = { studentPojo.getStudentId(),
				examPojo.getExamId() };

		Cursor submitAnswerCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		while (submitAnswerCursor.moveToNext()) {
			SubmitAnswerPojo submitAnswerPojo = new SubmitAnswerPojo();
			submitAnswerPojo.setSubmitAnswerId(submitAnswerCursor
					.getString(submitAnswerCursor
							.getColumnIndex("submitAnswerId")));
			submitAnswerPojo.setExamId(submitAnswerCursor
					.getString(submitAnswerCursor.getColumnIndex("examId")));
			submitAnswerPojo
					.setQuestionId(submitAnswerCursor
							.getString(submitAnswerCursor
									.getColumnIndex("questionId")));
			submitAnswerPojo.setStudentId(submitAnswerCursor
					.getString(submitAnswerCursor.getColumnIndex("studentId")));
			submitAnswerPojo.setQuestionScore(submitAnswerCursor
					.getFloat(submitAnswerCursor
							.getColumnIndex("questionScore")));
			submitAnswerPojo.setQuestionStdScore(submitAnswerCursor
					.getFloat(submitAnswerCursor
							.getColumnIndex("questionStdScore")));
			submitAnswerPojos.add(submitAnswerPojo);
		}

		return submitAnswerPojos;
	}

	public float getTotalScoreOfExamIdByStudentId(StudentPojo studentPojo,
			ExamPojo examPojo) {
		float totalScore = 0;

		String[] keys = { "SUM(questionScore)" };
		String[] whereConditionKeys = { "studentId", "examId" };
		String[] whereConditionValues = { studentPojo.getStudentId(),
				examPojo.getExamId() };

		Cursor submitAnswerCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		while (submitAnswerCursor.moveToNext()) {
			totalScore = submitAnswerCursor.getInt(submitAnswerCursor
					.getColumnIndex("SUM(questionScore)"));
		}

		return totalScore;
	}
}
