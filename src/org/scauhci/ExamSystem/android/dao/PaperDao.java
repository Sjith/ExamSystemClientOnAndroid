package org.scauhci.ExamSystem.android.dao;

import java.util.HashMap;

import org.scauhci.ExamSystem.android.pojo.ExamPojo;
import org.scauhci.ExamSystem.android.pojo.PaperPojo;
import org.scauhci.ExamSystem.android.tool.ExecuteResultFlag;

import android.database.Cursor;
import android.text.format.Time;

public class PaperDao {

	DaoHelper daoHelper = new DaoHelper(null, "exam_online.db", null, 0);
	String tableName = "paper";
	private PaperPojo latestPaperPojo = null;

	public PaperDao() {
		// update();
	}

	public int add(PaperPojo paperPojo) {
		int executeResult = ExecuteResultFlag.ERROR;

		String[] keys = { "paperId", "paperName", "paperType", "teacherId",
				"paperCreateTime", "courseId", "paperTotalScore",
				"paperExplain" };
		String[] values = { paperPojo.getPaperId(), paperPojo.getPaperName(),
				paperPojo.getPaperType() + "", paperPojo.getTeacherId(),
				paperPojo.getPaperCreateTime().format3339(true),
				paperPojo.getCourseId(), paperPojo.getPaperTotalScore() + "",
				paperPojo.getPaperExplain() };

		daoHelper.insert(tableName, keys, values);

		return executeResult;
	}

	public int delete(PaperPojo paperPojo) {
		int executeResult = ExecuteResultFlag.ERROR;

		String[] keys = { "paperId" };
		String[] values = { paperPojo.getPaperId() };

		daoHelper.delete(tableName, keys, values);

		return executeResult;
	}

	public int change(PaperPojo paperPojo) {
		int executeResult = ExecuteResultFlag.ERROR;

		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		/*if (paperPojo.getPaperId() != null) {
			keyValueMap.put("paperId", paperPojo.getPaperId());
		}*/
		if (paperPojo.getPaperName() != null) {
			keyValueMap.put("paperName", paperPojo.getPaperName());
		}
		if (paperPojo.getPaperType() != -1) {
			keyValueMap.put("teacherId", paperPojo.getPaperType() + "");
		}
		if (paperPojo.getTeacherId() != null) {
			keyValueMap.put("paperCreateTime", paperPojo.getPaperCreateTime()
					.format3339(true));
		}
		if (paperPojo.getCourseId() != null) {
			keyValueMap.put("courseId", paperPojo.getCourseId());
		}
		if (paperPojo.getPaperTotalScore() != 0) {
			keyValueMap.put("paperTotalScore", paperPojo.getPaperTotalScore()
					+ "");
		}
		if (paperPojo.getPaperExplain() != null) {
			keyValueMap.put("paperExplain", paperPojo.getPaperExplain());
		}

		String[] keys = new String[keyValueMap.size()];
		keyValueMap.keySet().toArray(keys);
		String[] newValues = new String[keys.length];
		keyValueMap.values().toArray(newValues);
		String[] whereConditionKeys = { "paperId" };
		String[] whereConditionValues = { paperPojo.getPaperId() };

		daoHelper.update(tableName, keys, newValues, whereConditionKeys,
				whereConditionValues);

		return executeResult;
	}

	public PaperPojo getPaperPojoByPaperId(String paperId) {
		PaperPojo paperPojo = new PaperPojo();

		String[] keys = { "*" };
		String[] whereConditionKeys = { "paperId" };
		String[] whereConditionValues = { paperId };

		Cursor paperCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		while (paperCursor.moveToNext()) {
			paperPojo.setPaperId(paperCursor.getString(paperCursor
					.getColumnIndex("paperId")));
			paperPojo.setPaperName(paperCursor.getString(paperCursor
					.getColumnIndex("paperName")));
			paperPojo.setPaperType(paperCursor.getInt(paperCursor
					.getColumnIndex("paperType")));
			paperPojo.setTeacherId(paperCursor.getString(paperCursor
					.getColumnIndex("teacherId")));
			paperPojo.setCourseId(paperCursor.getString(paperCursor
					.getColumnIndex("courseId")));
			paperPojo.setPaperTotalScore(paperCursor.getFloat(paperCursor
					.getColumnIndex("paperTotalScore")));
			paperPojo.setPaperExplain(paperCursor.getString(paperCursor
					.getColumnIndex("paperExplain")));
			Time paperCreateTime = new Time();
			paperCreateTime.parse3339(paperCursor.getString(paperCursor
					.getColumnIndex("paperCreateTime")));
			paperPojo.setPaperCreateTime(paperCreateTime);
		}

		return paperPojo;
	}
}
