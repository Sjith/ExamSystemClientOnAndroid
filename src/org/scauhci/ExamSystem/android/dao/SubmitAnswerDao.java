package org.scauhci.ExamSystem.android.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.scauhci.ExamSystem.android.pojo.CoursePojo;
import org.scauhci.ExamSystem.android.pojo.ExamPojo;
import org.scauhci.ExamSystem.android.pojo.QuestionPojo;
import org.scauhci.ExamSystem.android.pojo.StudentPojo;
import org.scauhci.ExamSystem.android.pojo.SubmitAnswerPojo;
import org.scauhci.ExamSystem.android.tool.Flag;
import org.scauhci.ExamSystem.android.tool.HashValue;

import android.database.Cursor;

public class SubmitAnswerDao {

	DaoHelper daoHelper = new DaoHelper(null, "exam_online.db", null, 1);
	String tableName = "submit_answer";

	public SubmitAnswerDao() {
		// update();
	}

	public SubmitAnswerPojo add(SubmitAnswerPojo submitAnswerPojo) {
		
		if (completeSubmitAnswerPojo(submitAnswerPojo) == null) {
			long submitAnswerId;
			for (submitAnswerId = HashValue.getDJBHashValue(submitAnswerPojo
					.getExamId()
					+ submitAnswerPojo.getQuestionId()
					+ submitAnswerPojo.getStudentId()); getSubmitAnswerPojoBySubmitAnswerId(Long
							.toHexString(submitAnswerId)) != null; submitAnswerId++)
				;
			submitAnswerPojo.setSubmitAnswerId(Long.toHexString(submitAnswerId));
			
			String[] keys = { "submitAnswerId", "questionId", "examId",
					"studentId", "questionStdScore", "questionScore" };
			String[] values = { submitAnswerPojo.getSubmitAnswerId(),
					submitAnswerPojo.getQuestionId(), submitAnswerPojo.getExamId(),
					submitAnswerPojo.getStudentId(),
					submitAnswerPojo.getQuestionStdScore() + "",
					submitAnswerPojo.getQuestionScore() + "" };
			
			daoHelper.insert(tableName, keys, values);
		} else {
			submitAnswerPojo = null;
		}

		return submitAnswerPojo;
	}

	public SubmitAnswerPojo delete(SubmitAnswerPojo submitAnswerPojo) {

		if ((completeSubmitAnswerPojo(submitAnswerPojo)) != null) {
			String[] keys = { "submitAnswerId" };
			String[] values = { submitAnswerPojo.getSubmitAnswerId() };

			daoHelper.delete(tableName, keys, values);
		}
		
		return submitAnswerPojo;
	}

	public SubmitAnswerPojo change(SubmitAnswerPojo submitAnswerPojo) {
		HashMap<String, String> keyValueMap = getKeyValueMapBySubmitAnswerPojo(submitAnswerPojo);

		String[] keys = new String[keyValueMap.size()];
		keyValueMap.keySet().toArray(keys);
		String[] newValues = new String[keys.length];
		keyValueMap.values().toArray(newValues);
		String[] whereConditionKeys = { "submitAnswerId" };
		String[] whereConditionValues = { submitAnswerPojo.getSubmitAnswerId() };

		daoHelper.update(tableName, keys, newValues, whereConditionKeys,
				whereConditionValues);
		submitAnswerPojo = completeSubmitAnswerPojo(submitAnswerPojo);

		return submitAnswerPojo;
	}

	public HashMap<String, String> getKeyValueMapBySubmitAnswerPojo(
			SubmitAnswerPojo submitAnswerPojo) {
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

		return keyValueMap;
	}

	public SubmitAnswerPojo getSubmitAnswerPojoBySubmitAnswerId(
			String submitAnswerId) {
		SubmitAnswerPojo submitAnswerPojo = new SubmitAnswerPojo();

		String[] keys = { "*" };
		String[] whereConditionKeys = { "submitAnswerId" };
		String[] whereConditionValues = { submitAnswerId };

		Cursor submitAnswerCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		if (submitAnswerCursor.moveToFirst()) {
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
		} else {
			submitAnswerPojo = null;
		}
		
		submitAnswerCursor.close();

		return submitAnswerPojo;
	}

	public SubmitAnswerPojo completeSubmitAnswerPojo(
			SubmitAnswerPojo submitAnswerPojo) {
		HashMap<String, String> keyValueMap = getKeyValueMapBySubmitAnswerPojo(submitAnswerPojo);

		String[] keys = { "*" };
		String[] whereConditionKeys = new String[keyValueMap.size()];
		keyValueMap.keySet().toArray(whereConditionKeys);
		String[] whereConditionValues = new String[whereConditionKeys.length];
		keyValueMap.values().toArray(whereConditionValues);

		Cursor submitAnswerCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		if (submitAnswerCursor.moveToFirst()) {
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
		} else {
			submitAnswerPojo = null;
		}
		
		submitAnswerCursor.close();

		return submitAnswerPojo;
	}

	public SubmitAnswerPojo getStandardSubmitAnswerPojoByExamPojoAndQuestionPojo(
			ExamPojo examPojo, QuestionPojo questionPojo) {

		StudentPojo studentPojo = new StudentPojo();
		studentPojo.setStudentId(Flag.STANDARD);
		
		return getSubmitAnswerPojoByStudentPojoAndExamPojoAndQuestionPojo(studentPojo, examPojo, questionPojo);
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
		if (submitAnswerCursor.moveToFirst()) {
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
		} else {
			submitAnswerPojo = null;
		}
		
		submitAnswerCursor.close();

		return submitAnswerPojo;
	}

	public ArrayList<SubmitAnswerPojo> getStandardSubmitAnswerPojosOfExamPojo(ExamPojo examPojo) {
		
		StudentPojo studentPojo = new StudentPojo();
		studentPojo.setStudentId(Flag.STANDARD);
		
		return getSubmitAnswerPojosOfExamPojoByStudentPojo(studentPojo, examPojo);
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
		
		submitAnswerCursor.close();

		return submitAnswerPojos;
	}

	public float getTotalScoreOfExamPojoByStudentPojo(StudentPojo studentPojo,
			ExamPojo examPojo) {
		float totalScore = 0;

		String[] keys = { "SUM(questionScore)" };
		String[] whereConditionKeys = { "studentId", "examId" };
		String[] whereConditionValues = { studentPojo.getStudentId(),
				examPojo.getExamId() };

		Cursor submitAnswerCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		if (submitAnswerCursor.moveToFirst()) {
			totalScore = submitAnswerCursor.getInt(submitAnswerCursor
					.getColumnIndex("SUM(questionScore)"));
		} else {
			totalScore = 0;
		}
		
		submitAnswerCursor.close();

		return totalScore;
	}
}
