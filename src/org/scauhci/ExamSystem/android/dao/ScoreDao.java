package org.scauhci.ExamSystem.android.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.scauhci.ExamSystem.android.pojo.ExamPojo;
import org.scauhci.ExamSystem.android.pojo.ScorePojo;
import org.scauhci.ExamSystem.android.pojo.StudentPojo;
import org.scauhci.ExamSystem.android.tool.ExecuteResultFlag;

import android.database.Cursor;

public class ScoreDao {

	DaoHelper daoHelper = new DaoHelper(null, "exam_online.db", null, 0);
	String tableName = "score";

	public ScoreDao() {
		// update();
	}

	public int add(ScorePojo scorePojo) {
		int executeResult = ExecuteResultFlag.ERROR;

		String[] keys = { "scoreId", "examId", "studentId", "paperScore" };
		String[] values = { scorePojo.getScoreId(), scorePojo.getExamId(),
				scorePojo.getStudentId(), scorePojo.getPaperScore() + "" };

		daoHelper.insert(tableName, keys, values);

		return executeResult;
	}

	public int delete(ScorePojo scorePojo) {
		int executeResult = ExecuteResultFlag.ERROR;

		String[] keys = { "scoreId" };
		String[] values = { scorePojo.getScoreId() };

		daoHelper.delete(tableName, keys, values);

		return executeResult;
	}

	public int change(ScorePojo scorePojo) {
		int executeResult = ExecuteResultFlag.ERROR;

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

		String[] keys = new String[keyValueMap.size()];
		keyValueMap.keySet().toArray(keys);
		String[] newValues = new String[keys.length];
		keyValueMap.values().toArray(newValues);
		String[] whereConditionKeys = { "scoreId" };
		String[] whereConditionValues = { scorePojo.getScoreId() };

		daoHelper.update(tableName, keys, newValues, whereConditionKeys,
				whereConditionValues);

		return executeResult;
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
		while (scoreCursor.moveToNext()) {
			scorePojo.setScoreId(scoreCursor.getString(scoreCursor
					.getColumnIndex("scoreId")));
			scorePojo.setStudentId(scoreCursor.getString(scoreCursor
					.getColumnIndex("studentId")));
			scorePojo.setExamId(scoreCursor.getString(scoreCursor
					.getColumnIndex("examId")));
			scorePojo.setScore(scoreCursor.getFloat(scoreCursor
					.getColumnIndex("score")));
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
