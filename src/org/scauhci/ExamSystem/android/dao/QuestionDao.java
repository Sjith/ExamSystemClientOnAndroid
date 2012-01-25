package org.scauhci.ExamSystem.android.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.scauhci.ExamSystem.android.pojo.CoursePojo;
import org.scauhci.ExamSystem.android.pojo.QuestionPojo;
import org.scauhci.ExamSystem.android.tool.ExecuteResultFlag;

import android.database.Cursor;
import android.text.format.Time;

public class QuestionDao {

	DaoHelper daoHelper = new DaoHelper(null, "exam_online.db", null, 0);
	String tableName = "question";

	public QuestionDao() {
		// update();
	}

	public int add(QuestionPojo questionPojo) {
		int executeResult = ExecuteResultFlag.ERROR;

		String[] keys = { "questionId", "questionContent", "questionType",
				"courseId", "questionStdAnswer" };
		String[] values = { questionPojo.getQuestionId(),
				questionPojo.getQuestionContent(),
				questionPojo.getQuestionType() + "",
				questionPojo.getCourseId(), questionPojo.getQuestionStdAnswer() };

		daoHelper.insert(tableName, keys, values);

		return executeResult;
	}

	public int delete(QuestionPojo questionPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		
		String[] keys = { "questionId" };
		String[] values = { questionPojo.getQuestionId() };

		daoHelper.delete(tableName, keys, values);
		
		return executeResult;
	}

	public int change(QuestionPojo questionPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		if (questionPojo.getQuestionContent() != null) {
			keyValueMap.put("questionContent", questionPojo.getQuestionContent());
		}
		if (questionPojo.getCourseId() != null) {
			keyValueMap.put("courseId", questionPojo.getCourseId());
		}
		if (questionPojo.getQuestionStdAnswer() != null) {
			keyValueMap.put("questionStdAnswer", questionPojo.getQuestionStdAnswer());
		}
		if (questionPojo.getQuestionType() != -1) {
			keyValueMap.put("questionType", questionPojo.getQuestionType() + "");
		}
		
		String[] keys = new String[keyValueMap.size()];
		keyValueMap.keySet().toArray(keys);
		String[] newValues = new String[keys.length];
		keyValueMap.values().toArray(newValues);
		String[] whereConditionKeys = { "questionId" };
		String[] whereConditionValues = { questionPojo.getQuestionId() };
		
		daoHelper.update(tableName, keys, newValues, whereConditionKeys,
				whereConditionValues);
		
		return executeResult;
	}

	public QuestionPojo getQuestionPojoByQuestionId(String questionId) {
		QuestionPojo questionPojo = new QuestionPojo();
		
		String[] keys = { "*" };
		String[] whereConditionKeys = { "questionId" };
		String[] whereConditionValues = { questionId };

		Cursor questionCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		while (questionCursor.moveToNext()) {
			questionPojo.setQuestionId(questionCursor.getString(questionCursor
					.getColumnIndex("questionId")));
			questionPojo.setQuestionContent(questionCursor.getString(questionCursor
					.getColumnIndex("questionContent")));
			questionPojo.setCourseId(questionCursor.getString(questionCursor
					.getColumnIndex("courseId")));
			questionPojo.setQuestionStdAnswer(questionCursor.getString(questionCursor
					.getColumnIndex("questionStdAnswer")));
			questionPojo.setQuestionType(questionCursor.getInt(questionCursor
					.getColumnIndex("questionType")));
		}
		
		return questionPojo;
	}

	public ArrayList<QuestionPojo> getQuestionPojosByCoursePojo(
			CoursePojo coursePojo) {
		ArrayList<QuestionPojo> questionPojos = new ArrayList<QuestionPojo>();
		
		String[] keys = { "*" };
		String[] whereConditionKeys = { "courseId" };
		String[] whereConditionValues = { coursePojo.getCourseId() };

		Cursor questionCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		while (questionCursor.moveToNext()) {
			QuestionPojo questionPojo = new QuestionPojo();
			questionPojo.setQuestionId(questionCursor.getString(questionCursor
					.getColumnIndex("questionId")));
			questionPojo.setQuestionContent(questionCursor.getString(questionCursor
					.getColumnIndex("questionContent")));
			questionPojo.setCourseId(questionCursor.getString(questionCursor
					.getColumnIndex("courseId")));
			questionPojo.setQuestionStdAnswer(questionCursor.getString(questionCursor
					.getColumnIndex("questionStdAnswer")));
			questionPojo.setQuestionType(questionCursor.getInt(questionCursor
					.getColumnIndex("questionType")));
			questionPojos.add(questionPojo);
		}
		
		return questionPojos;
	}
}
