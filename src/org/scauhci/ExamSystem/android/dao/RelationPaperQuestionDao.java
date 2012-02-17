package org.scauhci.ExamSystem.android.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.scauhci.ExamSystem.android.pojo.CoursePojo;
import org.scauhci.ExamSystem.android.pojo.ExamPojo;
import org.scauhci.ExamSystem.android.pojo.PaperPojo;
import org.scauhci.ExamSystem.android.pojo.RelationPaperQuestionPojo;
import org.scauhci.ExamSystem.android.tool.Flag;
import org.scauhci.ExamSystem.android.tool.HashValue;

import android.database.Cursor;

public class RelationPaperQuestionDao {

	DaoHelper daoHelper = new DaoHelper(null, "exam_online.db", null, 0);
	String tableName = "relation_paper_question";

	public RelationPaperQuestionDao() {
		// update();
	}

	public RelationPaperQuestionPojo add(RelationPaperQuestionPojo relationPaperQuestionPojo) {
		
		if (completeRelationPaperQuestionPojo(relationPaperQuestionPojo) == null) {
			long relationPaperQuestionId;
			for (relationPaperQuestionId = HashValue
					.getDJBHashValue(relationPaperQuestionPojo.getPaperId()
							+ relationPaperQuestionPojo.getQuestionId()); getRelationPaperQuestionPojoByRelationPaperQuestionId(Long
									.toHexString(relationPaperQuestionId)) != null; relationPaperQuestionId++)
				;
			relationPaperQuestionPojo.setRelationPaperQuestionId(Long
					.toHexString(relationPaperQuestionId));
			
			String[] keys = { "relationPaperQuestionId", "examId", "paperId",
					"questionId", "questionIndex" };
			String[] values = {
					relationPaperQuestionPojo.getRelationPaperQuestionId(),
					relationPaperQuestionPojo.getExamId(),
					relationPaperQuestionPojo.getPaperId(),
					relationPaperQuestionPojo.getQuestionId(),
					relationPaperQuestionPojo.getQuestionIndex() + "" };
			
			daoHelper.insert(tableName, keys, values);
		} else {
			relationPaperQuestionPojo = null;
		}

		return relationPaperQuestionPojo;
	}

	public RelationPaperQuestionPojo delete(RelationPaperQuestionPojo relationPaperQuestionPojo) {
		if ((relationPaperQuestionPojo = completeRelationPaperQuestionPojo(relationPaperQuestionPojo)) != null) {
			String[] keys = { "relationPaperQuestionId" };
			String[] values = { relationPaperQuestionPojo
					.getRelationPaperQuestionId() };
			
			daoHelper.delete(tableName, keys, values);
		}

		return relationPaperQuestionPojo;
	}

	public RelationPaperQuestionPojo change(RelationPaperQuestionPojo relationPaperQuestionPojo) {
		HashMap<String, String> keyValueMap = getKeyValueMapByRelationPaperQuestionPojo(relationPaperQuestionPojo);

		String[] keys = new String[keyValueMap.size()];
		keyValueMap.keySet().toArray(keys);
		String[] newValues = new String[keys.length];
		keyValueMap.values().toArray(newValues);
		String[] whereConditionKeys = { "relationPaperQuestionId" };
		String[] whereConditionValues = { relationPaperQuestionPojo
				.getRelationPaperQuestionId() };

		daoHelper.update(tableName, keys, newValues, whereConditionKeys,
				whereConditionValues);
		relationPaperQuestionPojo = completeRelationPaperQuestionPojo(relationPaperQuestionPojo);

		return relationPaperQuestionPojo;
	}

	public HashMap<String, String> getKeyValueMapByRelationPaperQuestionPojo(
			RelationPaperQuestionPojo relationPaperQuestionPojo) {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		if (relationPaperQuestionPojo.getRelationPaperQuestionId() != null) {
			keyValueMap.put("relationPaperQuestionId", relationPaperQuestionPojo.getRelationPaperQuestionId());
		}
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

		return keyValueMap;
	}

	public RelationPaperQuestionPojo getRelationPaperQuestionPojoByRelationPaperQuestionId(
			String relationPaperQuestionId) {
		RelationPaperQuestionPojo relationPaperQuestionPojo = new RelationPaperQuestionPojo();

		String[] keys = { "*" };
		String[] whereConditionKeys = { "relationPaperQuestionId" };
		String[] whereConditionValues = { relationPaperQuestionId };

		Cursor relationPaperQuestionCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		if (relationPaperQuestionCursor.moveToFirst()) {
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
		} else {
			relationPaperQuestionPojo = null;
		}
		
		relationPaperQuestionCursor.close();

		return relationPaperQuestionPojo;
	}
	
	public RelationPaperQuestionPojo completeRelationPaperQuestionPojo(RelationPaperQuestionPojo relationPaperQuestionPojo) {
		HashMap<String, String> keyValueMap = getKeyValueMapByRelationPaperQuestionPojo(relationPaperQuestionPojo);

		String[] keys = { "*" };
		String[] whereConditionKeys = new String[keyValueMap.size()];
		keyValueMap.keySet().toArray(whereConditionKeys);
		String[] whereConditionValues = new String[whereConditionKeys.length];
		keyValueMap.values().toArray(whereConditionValues);

		Cursor relationPaperQuestionCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		if (relationPaperQuestionCursor.moveToFirst()) {
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
		} else {
			relationPaperQuestionPojo = null;
		}
		
		relationPaperQuestionCursor.close();

		return relationPaperQuestionPojo;
	}

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
		
		relationPaperQuestionCursor.close();

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
		
		relationPaperQuestionCursor.close();

		return relationPaperQuestionPojos;
	}
}
