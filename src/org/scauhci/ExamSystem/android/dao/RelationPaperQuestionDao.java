package org.scauhci.ExamSystem.android.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.scauhci.ExamSystem.android.pojo.ExamPojo;
import org.scauhci.ExamSystem.android.pojo.PaperPojo;
import org.scauhci.ExamSystem.android.pojo.RelationPaperQuestionPojo;
import org.scauhci.ExamSystem.android.tool.ExecuteResultFlag;

import android.database.Cursor;

public class RelationPaperQuestionDao {

	DaoHelper daoHelper = new DaoHelper(null, "exam_online.db", null, 0);
	String tableName = "relation_paper_question";

	public RelationPaperQuestionDao() {
		// update();
	}

	public int add(RelationPaperQuestionPojo relationPaperQuestionPojo) {
		int executeResult = ExecuteResultFlag.ERROR;

		String[] keys = { "relationPaperQuestionId", "examId", "paperId",
				"questionId", "questionIndex" };
		String[] values = {
				relationPaperQuestionPojo.getRelationPaperQuestionId(),
				relationPaperQuestionPojo.getExamId(),
				relationPaperQuestionPojo.getPaperId(),
				relationPaperQuestionPojo.getQuestionId(),
				relationPaperQuestionPojo.getQuestionIndex() + "" };

		daoHelper.insert(tableName, keys, values);

		return executeResult;
	}

	public int delete(RelationPaperQuestionPojo relationPaperQuestionPojo) {
		int executeResult = ExecuteResultFlag.ERROR;

		String[] keys = { "relationPaperQuestionId" };
		String[] values = { relationPaperQuestionPojo
				.getRelationPaperQuestionId() };

		daoHelper.delete(tableName, keys, values);

		return executeResult;
	}

	public int change(RelationPaperQuestionPojo relationPaperQuestionPojo) {
		int executeResult = ExecuteResultFlag.ERROR;

		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		if (relationPaperQuestionPojo.getExamId() != null) {
			keyValueMap.put("examId", relationPaperQuestionPojo.getExamId());
		}
		if (relationPaperQuestionPojo.getPaperId() != null) {
			keyValueMap.put("paperId", relationPaperQuestionPojo.getPaperId());
		}
		if (relationPaperQuestionPojo.getQuestionId() != null) {
			keyValueMap.put("questionId",
					relationPaperQuestionPojo.getQuestionId());
		}
		if (relationPaperQuestionPojo.getQuestionIndex() != -1) {
			keyValueMap.put("questionIndex",
					relationPaperQuestionPojo.getQuestionIndex() + "");
		}

		String[] keys = new String[keyValueMap.size()];
		keyValueMap.keySet().toArray(keys);
		String[] newValues = new String[keys.length];
		keyValueMap.values().toArray(newValues);
		String[] whereConditionKeys = { "relationPaperQuestionId" };
		String[] whereConditionValues = { relationPaperQuestionPojo
				.getRelationPaperQuestionId() };

		daoHelper.update(tableName, keys, newValues, whereConditionKeys,
				whereConditionValues);

		return executeResult;
	}

	/*
	 * public RelationPaperQuestionPojo
	 * getRelationPaperQuestionPojoByRelationPaperQuestionId( String
	 * relationPaperQuestionId) { RelationPaperQuestionPojo
	 * relationPaperQuestionPojo = null; return relationPaperQuestionPojo; }
	 */

	public ArrayList<RelationPaperQuestionPojo> getRelationPaperQuestionPojosByPaperPojo(
			PaperPojo paperPojo) {
		ArrayList<RelationPaperQuestionPojo> relationPaperQuestionPojos = new ArrayList<RelationPaperQuestionPojo>();

		String[] keys = { "*" };
		String[] whereConditionKeys = { "paperId" };
		String[] whereConditionValues = { paperPojo.getPaperId() };

		Cursor relationPaperQuestionCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		while (relationPaperQuestionCursor.moveToNext()) {
			RelationPaperQuestionPojo relationPaperQuestionPojo = new RelationPaperQuestionPojo();
			relationPaperQuestionPojo
					.setRelationPaperQuestionId(relationPaperQuestionCursor
							.getString(relationPaperQuestionCursor
									.getColumnIndex("relationPaperQuestionId")));
			relationPaperQuestionPojo.setExamId(relationPaperQuestionCursor
					.getString(relationPaperQuestionCursor
							.getColumnIndex("examId")));
			relationPaperQuestionPojo.setPaperId(relationPaperQuestionCursor
					.getString(relationPaperQuestionCursor
							.getColumnIndex("paperId")));
			relationPaperQuestionPojo.setQuestionId(relationPaperQuestionCursor
					.getString(relationPaperQuestionCursor
							.getColumnIndex("questionId")));
			relationPaperQuestionPojo
					.setQuestionIndex(relationPaperQuestionCursor
							.getInt(relationPaperQuestionCursor
									.getColumnIndex("questionIndex")));
			relationPaperQuestionPojos.add(relationPaperQuestionPojo);
		}

		return relationPaperQuestionPojos;
	}

	public ArrayList<RelationPaperQuestionPojo> getRelationPaperQuestionPojosByExamPojo(
			ExamPojo examPojo) {
		ArrayList<RelationPaperQuestionPojo> relationPaperQuestionPojos = new ArrayList<RelationPaperQuestionPojo>();

		String[] keys = { "*" };
		String[] whereConditionKeys = { "examId" };
		String[] whereConditionValues = { examPojo.getExamId() };

		Cursor relationPaperQuestionCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		while (relationPaperQuestionCursor.moveToNext()) {
			RelationPaperQuestionPojo relationPaperQuestionPojo = new RelationPaperQuestionPojo();
			relationPaperQuestionPojo
					.setRelationPaperQuestionId(relationPaperQuestionCursor
							.getString(relationPaperQuestionCursor
									.getColumnIndex("relationPaperQuestionId")));
			relationPaperQuestionPojo.setExamId(relationPaperQuestionCursor
					.getString(relationPaperQuestionCursor
							.getColumnIndex("examId")));
			relationPaperQuestionPojo.setPaperId(relationPaperQuestionCursor
					.getString(relationPaperQuestionCursor
							.getColumnIndex("paperId")));
			relationPaperQuestionPojo.setQuestionId(relationPaperQuestionCursor
					.getString(relationPaperQuestionCursor
							.getColumnIndex("questionId")));
			relationPaperQuestionPojo
					.setQuestionIndex(relationPaperQuestionCursor
							.getInt(relationPaperQuestionCursor
									.getColumnIndex("questionIndex")));
			relationPaperQuestionPojos.add(relationPaperQuestionPojo);
		}

		return relationPaperQuestionPojos;
	}
}
