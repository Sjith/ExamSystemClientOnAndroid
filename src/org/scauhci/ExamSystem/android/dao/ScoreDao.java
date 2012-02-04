package org.scauhci.ExamSystem.android.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.scauhci.ExamSystem.android.pojo.CoursePojo;
import org.scauhci.ExamSystem.android.pojo.ExamPojo;
import org.scauhci.ExamSystem.android.pojo.ScorePojo;
import org.scauhci.ExamSystem.android.pojo.StudentPojo;
import org.scauhci.ExamSystem.android.tool.Flag;
import org.scauhci.ExamSystem.android.tool.HashValue;

import android.database.Cursor;

public class ScoreDao {

	DaoHelper daoHelper = new DaoHelper(null, "exam_online.db", null, 0);
	String tableName = "score";

	public ScoreDao() {
		// update();
	}

	public ScorePojo add(ScorePojo scorePojo) {
		long scoreId;
		for (scoreId = HashValue.getDJBHashValue(scorePojo.getExamId()
				+ scorePojo.getStudentId()); getScorePojoByScoreId(Long
				.toHexString(scoreId)) != null; scoreId++)
			;
		scorePojo.setScoreId(Long.toHexString(scoreId));

		String[] keys = { "scoreId", "examId", "studentId", "paperScore" };
		String[] values = { scorePojo.getScoreId(), scorePojo.getExamId(),
				scorePojo.getStudentId(), scorePojo.getPaperScore() + "" };

		daoHelper.insert(tableName, keys, values);

		return scorePojo;
	}

	public ScorePojo delete(ScorePojo scorePojo) {

		if ((scorePojo = completeScorePojo(scorePojo)) != null) {
			String[] keys = { "scoreId" };
			String[] values = { scorePojo.getScoreId() };

			daoHelper.delete(tableName, keys, values);
		}

		return scorePojo;
	}

	public ScorePojo change(ScorePojo scorePojo) {
		HashMap<String, String> keyValueMap = getKeyValueMapByScorePojo(scorePojo);

		String[] keys = new String[keyValueMap.size()];
		keyValueMap.keySet().toArray(keys);
		String[] newValues = new String[keys.length];
		keyValueMap.values().toArray(newValues);
		String[] whereConditionKeys = { "scoreId" };
		String[] whereConditionValues = { scorePojo.getScoreId() };

		daoHelper.update(tableName, keys, newValues, whereConditionKeys,
				whereConditionValues);
		scorePojo = completeScorePojo(scorePojo);

		return scorePojo;
	}

	public HashMap<String, String> getKeyValueMapByScorePojo(ScorePojo scorePojo) {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		if (scorePojo.getScoreId() != null) {
			keyValueMap.put("scoreId", scorePojo.getScoreId());
		}
		if (scorePojo.getExamId() != null) {
			keyValueMap.put("examId", scorePojo.getExamId());
		}
		if (scorePojo.getStudentId() != null) {
			keyValueMap.put("studentId", scorePojo.getStudentId());
		}
		if (scorePojo.getPaperScore() != 0) {
			keyValueMap.put("studentId", scorePojo.getPaperScore() + "");
		}

		return keyValueMap;
	}

	public ScorePojo getScorePojoByScoreId(String scoreId) {
		ScorePojo scorePojo = new ScorePojo();

		String[] keys = { "*" };
		String[] whereConditionKeys = { "scoreId" };
		String[] whereConditionValues = { scoreId };

		Cursor scoreCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		if (scoreCursor.moveToFirst()) {
			scorePojo.setScoreId(scoreCursor.getString(scoreCursor
					.getColumnIndex("scoreId")));
			scorePojo.setStudentId(scoreCursor.getString(scoreCursor
					.getColumnIndex("studentId")));
			scorePojo.setExamId(scoreCursor.getString(scoreCursor
					.getColumnIndex("examId")));
			scorePojo.setScore(scoreCursor.getFloat(scoreCursor
					.getColumnIndex("score")));
		} else {
			scorePojo = null;
		}

		return scorePojo;
	}

	public ScorePojo completeScorePojo(ScorePojo scorePojo) {
		HashMap<String, String> keyValueMap = getKeyValueMapByScorePojo(scorePojo);

		String[] keys = { "*" };
		String[] whereConditionKeys = new String[keyValueMap.size()];
		keyValueMap.keySet().toArray(whereConditionKeys);
		String[] whereConditionValues = new String[whereConditionKeys.length];
		keyValueMap.values().toArray(whereConditionValues);

		Cursor scoreCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		if (scoreCursor.moveToFirst()) {
			scorePojo.setScoreId(scoreCursor.getString(scoreCursor
					.getColumnIndex("scoreId")));
			scorePojo.setStudentId(scoreCursor.getString(scoreCursor
					.getColumnIndex("studentId")));
			scorePojo.setExamId(scoreCursor.getString(scoreCursor
					.getColumnIndex("examId")));
			scorePojo.setScore(scoreCursor.getFloat(scoreCursor
					.getColumnIndex("score")));
		} else {
			scorePojo = null;
		}

		return scorePojo;
	}

	public ScorePojo getScorePojoByExamPojoAndStudentPojo(
			StudentPojo studentPojo, ExamPojo examPojo) {
		ScorePojo scorePojo = new ScorePojo();

		String[] keys = { "*" };
		String[] whereConditionKeys = { "studentId", "examId" };
		String[] whereConditionValues = { studentPojo.getStudentId(),
				examPojo.getExamId() };

		Cursor scoreCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		if (scoreCursor.moveToFirst()) {
			scorePojo.setScoreId(scoreCursor.getString(scoreCursor
					.getColumnIndex("scoreId")));
			scorePojo.setStudentId(scoreCursor.getString(scoreCursor
					.getColumnIndex("studentId")));
			scorePojo.setExamId(scoreCursor.getString(scoreCursor
					.getColumnIndex("examId")));
			scorePojo.setScore(scoreCursor.getFloat(scoreCursor
					.getColumnIndex("score")));
		} else {
			scorePojo = null;
		}

		return scorePojo;
	}

	public ArrayList<ScorePojo> getScorePojosByStudentPojo(
			StudentPojo studentPojo) {
		ArrayList<ScorePojo> scorePojos = new ArrayList<ScorePojo>();

		String[] keys = { "*" };
		String[] whereConditionKeys = { "studentId" };
		String[] whereConditionValues = { studentPojo.getStudentId() };

		Cursor scoreCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		while (scoreCursor.moveToNext()) {
			ScorePojo scorePojo = new ScorePojo();
			scorePojo.setScoreId(scoreCursor.getString(scoreCursor
					.getColumnIndex("scoreId")));
			scorePojo.setExamId(scoreCursor.getString(scoreCursor
					.getColumnIndex("examId")));
			scorePojo.setStudentId(scoreCursor.getString(scoreCursor
					.getColumnIndex("studentId")));
			scorePojo.setScore(scoreCursor.getFloat(scoreCursor
					.getColumnIndex("score")));
			scorePojos.add(scorePojo);
		}

		return scorePojos;
	}
}
