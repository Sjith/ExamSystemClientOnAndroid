package org.scauhci.ExamSystem.android.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.scauhci.ExamSystem.android.pojo.CoursePojo;
import org.scauhci.ExamSystem.android.pojo.QuestionPojo;
import org.scauhci.ExamSystem.android.tool.Flag;
import org.scauhci.ExamSystem.android.tool.HashValue;

import android.database.Cursor;
import android.text.format.Time;

public class QuestionDao {

	DaoHelper daoHelper = new DaoHelper(null, "exam_online.db", null, 1);
	String tableName = "question";

	public QuestionDao() {
		// update();
	}

	public QuestionPojo add(QuestionPojo questionPojo) {

		if (completeQuestionPojo(questionPojo) == null) {
			long questionId;
			for (questionId = HashValue.getDJBHashValue(questionPojo
					.getQuestionContent()); getQuestionPojoByQuestionId(Long
					.toHexString(questionId)) != null; questionId++)
				;
			/*In this part, the questionId of questionPojo will be replaced by auto product.*/
			questionPojo.setQuestionId(Long.toHexString(questionId));

			String[] keys = { "questionId", "questionContent", "questionType",
					"courseId", "questionStdAnswer" };
			String[] values = {
					questionPojo.getQuestionId(),
					questionPojo.getQuestionContent(),
					questionPojo.getQuestionType() + "",
					questionPojo.getCourseId(),
					questionPojo.getQuestionStdAnswer() == null ? ""
							: questionPojo.getQuestionStdAnswer() };

			daoHelper.insert(tableName, keys, values);
		} else {
			questionPojo = null;
		}

		return questionPojo;
	}

	public QuestionPojo delete(QuestionPojo questionPojo) {

		if ((questionPojo = completeQuestionPojo(questionPojo)) != null) {
			String[] keys = { "questionId" };
			String[] values = { questionPojo.getQuestionId() };

			daoHelper.delete(tableName, keys, values);
		}

		return questionPojo;
	}

	public QuestionPojo change(QuestionPojo questionPojo) {
		HashMap<String, String> keyValueMap = getKeyValueMapByQuestionPojo(questionPojo);

		String[] keys = new String[keyValueMap.size()];
		keyValueMap.keySet().toArray(keys);
		String[] newValues = new String[keys.length];
		keyValueMap.values().toArray(newValues);
		String[] whereConditionKeys = { "questionId" };
		String[] whereConditionValues = { questionPojo.getQuestionId() };

		daoHelper.update(tableName, keys, newValues, whereConditionKeys,
				whereConditionValues);

		return questionPojo;
	}

	public HashMap<String, String> getKeyValueMapByQuestionPojo(
			QuestionPojo questionPojo) {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		if (questionPojo.getQuestionId() != null) {
			keyValueMap.put("questionId", questionPojo.getQuestionId());
		}
		if (questionPojo.getQuestionContent() != null) {
			keyValueMap.put("questionContent",
					questionPojo.getQuestionContent());
		}
		if (questionPojo.getCourseId() != null) {
			keyValueMap.put("courseId", questionPojo.getCourseId());
		}
		if (questionPojo.getQuestionStdAnswer() != null) {
			keyValueMap.put("questionStdAnswer",
					questionPojo.getQuestionStdAnswer());
		}
		if (questionPojo.getQuestionType() != -1) {
			keyValueMap
					.put("questionType", questionPojo.getQuestionType() + "");
		}

		return keyValueMap;
	}

	public QuestionPojo getQuestionPojoByQuestionId(String questionId) {
		QuestionPojo questionPojo = new QuestionPojo();

		String[] keys = { "*" };
		String[] whereConditionKeys = { "questionId" };
		String[] whereConditionValues = { questionId };

		Cursor questionCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		if (questionCursor.moveToFirst()) {
			questionPojo.setQuestionId(questionCursor.getString(questionCursor
					.getColumnIndex("questionId")));
			questionPojo
					.setQuestionContent(questionCursor.getString(questionCursor
							.getColumnIndex("questionContent")));
			questionPojo.setCourseId(questionCursor.getString(questionCursor
					.getColumnIndex("courseId")));
			questionPojo.setQuestionStdAnswer(questionCursor
					.getString(questionCursor
							.getColumnIndex("questionStdAnswer")));
			questionPojo.setQuestionType(questionCursor.getInt(questionCursor
					.getColumnIndex("questionType")));
		} else {
			questionPojo = null;
		}
		
		questionCursor.close();

		return questionPojo;
	}

	public QuestionPojo completeQuestionPojo(QuestionPojo questionPojo) {
		HashMap<String, String> keyValueMap = getKeyValueMapByQuestionPojo(questionPojo);

		String[] keys = { "*" };
		String[] whereConditionKeys = new String[keyValueMap.size()];
		keyValueMap.keySet().toArray(whereConditionKeys);
		String[] whereConditionValues = new String[whereConditionKeys.length];
		keyValueMap.values().toArray(whereConditionValues);

		Cursor questionCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		if (questionCursor.moveToFirst()) {
			questionPojo.setQuestionId(questionCursor.getString(questionCursor
					.getColumnIndex("questionId")));
			questionPojo
					.setQuestionContent(questionCursor.getString(questionCursor
							.getColumnIndex("questionContent")));
			questionPojo.setCourseId(questionCursor.getString(questionCursor
					.getColumnIndex("courseId")));
			questionPojo.setQuestionStdAnswer(questionCursor
					.getString(questionCursor
							.getColumnIndex("questionStdAnswer")));
			questionPojo.setQuestionType(questionCursor.getInt(questionCursor
					.getColumnIndex("questionType")));
		} else {
			questionPojo = null;
		}
		
		questionCursor.close();

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
			questionPojo
					.setQuestionContent(questionCursor.getString(questionCursor
							.getColumnIndex("questionContent")));
			questionPojo.setCourseId(questionCursor.getString(questionCursor
					.getColumnIndex("courseId")));
			questionPojo.setQuestionStdAnswer(questionCursor
					.getString(questionCursor
							.getColumnIndex("questionStdAnswer")));
			questionPojo.setQuestionType(questionCursor.getInt(questionCursor
					.getColumnIndex("questionType")));
			questionPojos.add(questionPojo);
		}
		
		questionCursor.close();

		return questionPojos;
	}
}
