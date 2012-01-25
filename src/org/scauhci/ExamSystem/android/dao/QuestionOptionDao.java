package org.scauhci.ExamSystem.android.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.scauhci.ExamSystem.android.pojo.QuestionOptionPojo;
import org.scauhci.ExamSystem.android.pojo.QuestionPojo;
import org.scauhci.ExamSystem.android.tool.ExecuteResultFlag;

import android.database.Cursor;
import android.text.format.Time;

public class QuestionOptionDao {

	DaoHelper daoHelper = new DaoHelper(null, "exam_online.db", null, 0);
	String tableName = "question_option";
	private QuestionOptionPojo latestQuestionOptionPojo = null;

	public int add(QuestionOptionPojo questionOptionPojo) {
		int executeResult = ExecuteResultFlag.ERROR;

		String[] keys = { "questionOptionId", "questionId",
				"questionOptionContent", "isQuestionStdAnswer" };
		String[] values = { questionOptionPojo.getQuestionOptionId(),
				questionOptionPojo.getQuestionId(),
				questionOptionPojo.getQuestionOptionContent(),
				questionOptionPojo.isQuestionStdAnswer() + "" };

		daoHelper.insert(tableName, keys, values);

		return executeResult;
	}

	public int delete(QuestionOptionPojo questionOptionPojo) {
		int executeResult = ExecuteResultFlag.ERROR;

		String[] keys = { "questionOptionId" };
		String[] values = { questionOptionPojo.getQuestionOptionId() };

		daoHelper.delete(tableName, keys, values);
		
		return executeResult;
	}

	public int change(QuestionOptionPojo questionOptionPojo) {
		int executeResult = ExecuteResultFlag.ERROR;

		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		if (questionOptionPojo.getQuestionId() != null) {
			keyValueMap.put("questionId", questionOptionPojo.getQuestionId());
		}
		if (questionOptionPojo.getQuestionOptionContent() != null) {
			keyValueMap.put("questionOptionContent",
					questionOptionPojo.getQuestionOptionContent());
		}
		keyValueMap.put("isQuestionStdAnswer",
				questionOptionPojo.isQuestionStdAnswer() + "");

		String[] keys = new String[keyValueMap.size()];
		keyValueMap.keySet().toArray(keys);
		String[] newValues = new String[keys.length];
		keyValueMap.values().toArray(newValues);
		String[] whereConditionKeys = { "questionOptionId" };
		String[] whereConditionValues = { questionOptionPojo.getQuestionOptionId() };

		daoHelper.update(tableName, keys, newValues, whereConditionKeys,
				whereConditionValues);
		
		return executeResult;
	}

	public QuestionOptionPojo getQuestionOptionPojoByQuestionOptionId(
			String questionOptionId) {
		QuestionOptionPojo questionOptionPojo = new QuestionOptionPojo();
		
		String[] keys = { "*" };
		String[] whereConditionKeys = { "questionOptionId" };
		String[] whereConditionValues = { questionOptionId };

		Cursor questionOptionCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		while (questionOptionCursor.moveToNext()) {
			questionOptionPojo.setQuestionOptionId(questionOptionCursor.getString(questionOptionCursor
					.getColumnIndex("questionOptionId")));
			questionOptionPojo.setQuestionOptionContent(questionOptionCursor.getString(questionOptionCursor
					.getColumnIndex("questionOptionContent")));
			questionOptionPojo.setQuestionStdAnswer(Boolean.getBoolean(questionOptionCursor.getString(questionOptionCursor
					.getColumnIndex("questionOptionContent"))));
		}
		
		return questionOptionPojo;
	}

	public ArrayList<QuestionOptionPojo> getQuestionOptionPojosByQuestionPojo(
			QuestionPojo questionPojo) {
		ArrayList<QuestionOptionPojo> questionOptionPojos = new ArrayList<QuestionOptionPojo>();
		
		String[] keys = { "*" };
		String[] whereConditionKeys = { "questionOptionId" };
		String[] whereConditionValues = { questionPojo.getQuestionId() };
		
		Cursor questionOptionCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		while (questionOptionCursor.moveToNext()) {
			QuestionOptionPojo questionOptionPojo = new QuestionOptionPojo();
			questionOptionPojo.setQuestionOptionId(questionOptionCursor.getString(questionOptionCursor
					.getColumnIndex("questionOptionId")));
			questionOptionPojo.setQuestionId(questionOptionCursor.getString(questionOptionCursor
					.getColumnIndex("questionId")));
			questionOptionPojo.setQuestionOptionContent(questionOptionCursor.getString(questionOptionCursor
					.getColumnIndex("questionOptionContent")));
			questionOptionPojo.setQuestionStdAnswer(Boolean.getBoolean(questionOptionCursor.getString(questionOptionCursor
					.getColumnIndex("isQuestionStdAnswer"))));
			questionOptionPojos.add(questionOptionPojo);
		}
		
		return questionOptionPojos;
	}
}
