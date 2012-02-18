package org.scauhci.ExamSystem.android.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.scauhci.ExamSystem.android.pojo.CoursePojo;
import org.scauhci.ExamSystem.android.pojo.QuestionOptionPojo;
import org.scauhci.ExamSystem.android.pojo.QuestionPojo;
import org.scauhci.ExamSystem.android.tool.Flag;
import org.scauhci.ExamSystem.android.tool.HashValue;

import android.database.Cursor;
import android.text.format.Time;

public class QuestionOptionDao {

	DaoHelper daoHelper = new DaoHelper(null, "exam_online.db", null, 1);
	String tableName = "question_option";

	public QuestionOptionPojo add(QuestionOptionPojo questionOptionPojo) {
		
		if (completeQuestionOptionPojo(questionOptionPojo) == null) {
			long questionOptionId;
			for (questionOptionId = HashValue.getDJBHashValue(questionOptionPojo
					.getQuestionOptionContent()); getQuestionOptionPojoByQuestionOptionId(Long
							.toHexString(questionOptionId)) != null; questionOptionId++)
				;
			questionOptionPojo.setQuestionOptionId(Long
					.toHexString(questionOptionId));
			
			String[] keys = { "questionOptionId", "questionId",
					"questionOptionContent", "isQuestionStdAnswer" };
			String[] values = { questionOptionPojo.getQuestionOptionId(),
					questionOptionPojo.getQuestionId(),
					questionOptionPojo.getQuestionOptionContent(),
					questionOptionPojo.isQuestionStdAnswer() + "" };
			
			daoHelper.insert(tableName, keys, values);
		} else {
			questionOptionPojo = null;
		}

		return questionOptionPojo;
	}

	public QuestionOptionPojo delete(QuestionOptionPojo questionOptionPojo) {

		if ((questionOptionPojo = completeQuestionOptionPojo(questionOptionPojo)) != null) {
			String[] keys = { "questionOptionId" };
			String[] values = { questionOptionPojo.getQuestionOptionId() };

			daoHelper.delete(tableName, keys, values);
		}

		return questionOptionPojo;
	}

	public QuestionOptionPojo change(QuestionOptionPojo questionOptionPojo) {
		HashMap<String, String> keyValueMap = getKeyValueMapByQuestionOptionPojo(questionOptionPojo);

		String[] keys = new String[keyValueMap.size()];
		keyValueMap.keySet().toArray(keys);
		String[] newValues = new String[keys.length];
		keyValueMap.values().toArray(newValues);
		String[] whereConditionKeys = { "questionOptionId" };
		String[] whereConditionValues = { questionOptionPojo
				.getQuestionOptionId() };

		daoHelper.update(tableName, keys, newValues, whereConditionKeys,
				whereConditionValues);
		questionOptionPojo = completeQuestionOptionPojo(questionOptionPojo);

		return questionOptionPojo;
	}

	public HashMap<String, String> getKeyValueMapByQuestionOptionPojo(
			QuestionOptionPojo questionOptionPojo) {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		if (questionOptionPojo.getQuestionOptionId() != null) {
			keyValueMap.put("questionOptionId",
					questionOptionPojo.getQuestionOptionId());
		}
		if (questionOptionPojo.getQuestionId() != null) {
			keyValueMap.put("questionId", questionOptionPojo.getQuestionId());
		}
		if (questionOptionPojo.getQuestionOptionContent() != null) {
			keyValueMap.put("questionOptionContent",
					questionOptionPojo.getQuestionOptionContent());
		}
		keyValueMap.put("isQuestionStdAnswer",
				questionOptionPojo.isQuestionStdAnswer() + "");

		return keyValueMap;
	}

	public QuestionOptionPojo getQuestionOptionPojoByQuestionOptionId(
			String questionOptionId) {
		QuestionOptionPojo questionOptionPojo = new QuestionOptionPojo();

		String[] keys = { "*" };
		String[] whereConditionKeys = { "questionOptionId" };
		String[] whereConditionValues = { questionOptionId };

		Cursor questionOptionCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		if (questionOptionCursor.moveToFirst()) {
			questionOptionPojo.setQuestionOptionId(questionOptionCursor
					.getString(questionOptionCursor
							.getColumnIndex("questionOptionId")));
			questionOptionPojo.setQuestionOptionContent(questionOptionCursor
					.getString(questionOptionCursor
							.getColumnIndex("questionOptionContent")));
			questionOptionPojo.setQuestionStdAnswer(Boolean
					.getBoolean(questionOptionCursor
							.getString(questionOptionCursor
									.getColumnIndex("questionOptionContent"))));
		} else {
			questionOptionPojo = null;
		}
		
		questionOptionCursor.close();

		return questionOptionPojo;
	}

	public QuestionOptionPojo completeQuestionOptionPojo(
			QuestionOptionPojo questionOptionPojo) {
		HashMap<String, String> keyValueMap = getKeyValueMapByQuestionOptionPojo(questionOptionPojo);

		String[] keys = { "*" };
		String[] whereConditionKeys = new String[keyValueMap.size()];
		keyValueMap.keySet().toArray(whereConditionKeys);
		String[] whereConditionValues = new String[whereConditionKeys.length];
		keyValueMap.values().toArray(whereConditionValues);

		Cursor questionOptionCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		if (questionOptionCursor.moveToFirst()) {
			questionOptionPojo.setQuestionOptionId(questionOptionCursor
					.getString(questionOptionCursor
							.getColumnIndex("questionOptionId")));
			questionOptionPojo.setQuestionOptionContent(questionOptionCursor
					.getString(questionOptionCursor
							.getColumnIndex("questionOptionContent")));
			questionOptionPojo.setQuestionStdAnswer(Boolean
					.getBoolean(questionOptionCursor
							.getString(questionOptionCursor
									.getColumnIndex("questionOptionContent"))));
		} else {
			questionOptionPojo = null;
		}

		questionOptionCursor.close();
		
		return questionOptionPojo;
	}

	public ArrayList<QuestionOptionPojo> getQuestionOptionPojosByQuestionPojo(
			QuestionPojo questionPojo) {
		ArrayList<QuestionOptionPojo> questionOptionPojos = new ArrayList<QuestionOptionPojo>();

		String[] keys = { "*" };
		String[] whereConditionKeys = { "questionId" };
		String[] whereConditionValues = { questionPojo.getQuestionId() };

		Cursor questionOptionCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		while (questionOptionCursor.moveToNext()) {
			QuestionOptionPojo questionOptionPojo = new QuestionOptionPojo();
			questionOptionPojo.setQuestionOptionId(questionOptionCursor
					.getString(questionOptionCursor
							.getColumnIndex("questionOptionId")));
			questionOptionPojo.setQuestionId(questionOptionCursor
					.getString(questionOptionCursor
							.getColumnIndex("questionId")));
			questionOptionPojo.setQuestionOptionContent(questionOptionCursor
					.getString(questionOptionCursor
							.getColumnIndex("questionOptionContent")));
			questionOptionPojo.setQuestionStdAnswer(Boolean
					.getBoolean(questionOptionCursor
							.getString(questionOptionCursor
									.getColumnIndex("isQuestionStdAnswer"))));
			questionOptionPojos.add(questionOptionPojo);
		}
		
		questionOptionCursor.close();

		return questionOptionPojos;
	}
}
