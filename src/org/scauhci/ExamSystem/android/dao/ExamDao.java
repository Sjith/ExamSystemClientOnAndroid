package org.scauhci.ExamSystem.android.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.scauhci.ExamSystem.android.pojo.ExamPojo;
import org.scauhci.ExamSystem.android.tool.Flag;
import org.scauhci.ExamSystem.android.tool.HashValue;

import android.database.Cursor;
import android.text.format.Time;
import android.util.Log;

public class ExamDao {

	DaoHelper daoHelper = new DaoHelper(null, "exam_online.db", null, 1);
	String tableName = "exam";

	public ExamDao() {
		// updateLatestExamPojo();
	}

	public ExamPojo add(ExamPojo examPojo) {

		if (completeExamPojo(examPojo) == null) {
			long examId;
			for (examId = HashValue.getDJBHashValue(examPojo.getExamName()); getExamPojoByExamId(Long
					.toHexString(examId)) != null; examId++)
				;
			examPojo.setExamId(Long.toHexString(examId));

			String[] keys = { "examId", "courseId", "teacherId", "paperId",
					"examExplain", "examName", "examCreateTime",
					"examStartTime", "examEndTime" };
			String[] values = {
					examPojo.getExamId(),
					examPojo.getCourseId(),
					examPojo.getTeacherId(),
					examPojo.getPaperId(),
					examPojo.getExamExplain() == null ? "" : examPojo
							.getExamExplain(),
					examPojo.getExamName(),
					examPojo.getExamCreateTime() == null ? "" : examPojo
							.getExamCreateTime().format3339(true),
					examPojo.getExamStartTime() == null ? "" : examPojo
							.getExamStartTime().format3339(true),
					examPojo.getExamEndTime() == null ? "" : examPojo
							.getExamEndTime().format3339(true) };

			daoHelper.insert(tableName, keys, values);
		} else {
			examPojo = null;
		}

		return examPojo;
	}

	public ExamPojo delete(ExamPojo examPojo) {

		if ((examPojo = completeExamPojo(examPojo)) != null) {
			String[] keys = { "examId" };
			String[] values = { examPojo.getExamId() };

			daoHelper.delete(tableName, keys, values);
		}

		return examPojo;
	}

	public ExamPojo change(ExamPojo examPojo) {
		HashMap<String, String> keyValueMap = getKeyValueMapByExamPojo(examPojo);

		String[] keys = new String[keyValueMap.size()];
		keyValueMap.keySet().toArray(keys);
		String[] newValues = new String[keys.length];
		keyValueMap.values().toArray(newValues);
		String[] whereConditionKeys = { "examId" };
		String[] whereConditionValues = { examPojo.getExamId() };

		daoHelper.update(tableName, keys, newValues, whereConditionKeys,
				whereConditionValues);
		examPojo = completeExamPojo(examPojo);

		return examPojo;
	}

	public HashMap<String, String> getKeyValueMapByExamPojo(ExamPojo examPojo) {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		if (examPojo.getExamId() != null) {
			keyValueMap.put("examId", examPojo.getExamId());
		}
		if (examPojo.getCourseId() != null) {
			keyValueMap.put("courseId", examPojo.getCourseId());
		}
		if (examPojo.getTeacherId() != null) {
			keyValueMap.put("teacherId", examPojo.getTeacherId());
		}
		if (examPojo.getPaperId() != null) {
			keyValueMap.put("paperId", examPojo.getPaperId());
		}
		if (examPojo.getExamExplain() != null) {
			keyValueMap.put("examExplain", examPojo.getExamExplain());
		}
		if (examPojo.getExamName() != null) {
			keyValueMap.put("examName", examPojo.getExamName());
		}
		if (examPojo.getExamCreateTime() != null) {
			keyValueMap.put("examCreateTime", examPojo.getExamCreateTime()
					.format3339(true));
		}
		if (examPojo.getExamStartTime() != null) {
			keyValueMap.put("examStartTime", examPojo.getExamStartTime()
					.format3339(true));
		}
		if (examPojo.getExamEndTime() != null) {
			keyValueMap.put("examEndTime", examPojo.getExamEndTime()
					.format3339(true));
		}

		return keyValueMap;
	}

	public ExamPojo getExamPojoByExamId(String examId) {
		ExamPojo examPojo = new ExamPojo();

		String[] keys = { "*" };
		String[] whereConditionKeys = { "examId" };
		String[] whereConditionValues = { examId };

		Cursor examCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		if (examCursor.moveToFirst()) {
			examPojo.setExamId(examCursor.getString(examCursor
					.getColumnIndex("examId")));
			examPojo.setCourseId(examCursor.getString(examCursor
					.getColumnIndex("courseId")));
			examPojo.setTeacherId(examCursor.getString(examCursor
					.getColumnIndex("teacherId")));
			examPojo.setPaperId(examCursor.getString(examCursor
					.getColumnIndex("paperId")));
			examPojo.setExamExplain(examCursor.getString(examCursor
					.getColumnIndex("examExplain")));
			examPojo.setExamName(examCursor.getString(examCursor
					.getColumnIndex("examName")));
			Time examCreateTime = new Time();
			if (examCursor.getString(examCursor
					.getColumnIndex("examCreateTime")) == null) {
				examPojo.setExamCreateTime(null);
			} else {
				examCreateTime.parse3339(examCursor.getString(examCursor
						.getColumnIndex("examCreateTime")));
				examPojo.setExamCreateTime(examCreateTime);
			}
			Time examStartTime = new Time();
			if (examCursor
					.getString(examCursor.getColumnIndex("examStartTime")) == null) {
				examPojo.setExamCreateTime(null);
			} else {
				examStartTime.parse3339(examCursor.getString(examCursor
						.getColumnIndex("examStartTime")));
				examPojo.setExamStartTime(examStartTime);
			}
			Time examEndTime = new Time();
			if (examCursor.getString(examCursor.getColumnIndex("examEndTime")) == null) {
				examPojo.setExamCreateTime(null);
			} else {
				examEndTime.parse3339(examCursor.getString(examCursor
						.getColumnIndex("examEndTime")));
				examPojo.setExamEndTime(examEndTime);
			}
		} else {
			examPojo = null;
		}

		examCursor.close();

		return examPojo;
	}

	public ExamPojo completeExamPojo(ExamPojo examPojo) {
		HashMap<String, String> keyValueMap = getKeyValueMapByExamPojo(examPojo);

		String[] keys = { "*" };
		String[] whereConditionKeys = new String[keyValueMap.size()];
		keyValueMap.keySet().toArray(whereConditionKeys);
		String[] whereConditionValues = new String[whereConditionKeys.length];
		keyValueMap.values().toArray(whereConditionValues);

		Cursor examCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		if (examCursor.moveToFirst()) {
			examPojo.setExamId(examCursor.getString(examCursor
					.getColumnIndex("examId")));
			examPojo.setCourseId(examCursor.getString(examCursor
					.getColumnIndex("courseId")));
			examPojo.setTeacherId(examCursor.getString(examCursor
					.getColumnIndex("teacherId")));
			examPojo.setPaperId(examCursor.getString(examCursor
					.getColumnIndex("paperId")));
			examPojo.setExamExplain(examCursor.getString(examCursor
					.getColumnIndex("examExplain")));
			examPojo.setExamName(examCursor.getString(examCursor
					.getColumnIndex("examName")));
			Time examCreateTime = new Time();
			if (examCursor.getString(examCursor
					.getColumnIndex("examCreateTime")) == null) {
				examPojo.setExamCreateTime(null);
			} else {
				examCreateTime.parse3339(examCursor.getString(examCursor
						.getColumnIndex("examCreateTime")));
				examPojo.setExamCreateTime(examCreateTime);
			}
			Time examStartTime = new Time();
			if (examCursor
					.getString(examCursor.getColumnIndex("examStartTime")) == null) {
				examPojo.setExamCreateTime(null);
			} else {
				examStartTime.parse3339(examCursor.getString(examCursor
						.getColumnIndex("examStartTime")));
				examPojo.setExamStartTime(examStartTime);
			}
			Time examEndTime = new Time();
			if (examCursor.getString(examCursor.getColumnIndex("examEndTime")) == null) {
				examPojo.setExamCreateTime(null);
			} else {
				examEndTime.parse3339(examCursor.getString(examCursor
						.getColumnIndex("examEndTime")));
				examPojo.setExamEndTime(examEndTime);
			}
		} else {
			examPojo = null;
		}

		examCursor.close();

		return examPojo;
	}

	public ArrayList<ExamPojo> getAllExamPojo() {
		ArrayList<ExamPojo> examPojos = new ArrayList<ExamPojo>();

		String[] keys = { "*" };
		String[] whereConditionKeys = null;
		String[] whereConditionValues = null;

		Cursor examCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		while (examCursor.moveToNext()) {
			ExamPojo examPojo = new ExamPojo();
			examPojo.setExamId(examCursor.getString(examCursor
					.getColumnIndex("examId")));
			examPojo.setCourseId(examCursor.getString(examCursor
					.getColumnIndex("courseId")));
			examPojo.setTeacherId(examCursor.getString(examCursor
					.getColumnIndex("teacherId")));
			examPojo.setPaperId(examCursor.getString(examCursor
					.getColumnIndex("paperId")));
			examPojo.setExamExplain(examCursor.getString(examCursor
					.getColumnIndex("examExplain")));
			examPojo.setExamName(examCursor.getString(examCursor
					.getColumnIndex("examName")));
			Time examCreateTime = new Time();
			if (examCursor.getString(examCursor
					.getColumnIndex("examCreateTime")) == null) {
				examPojo.setExamCreateTime(null);
			} else {
				examCreateTime.parse3339(examCursor.getString(examCursor
						.getColumnIndex("examCreateTime")));
				examPojo.setExamCreateTime(examCreateTime);
			}
			Time examStartTime = new Time();
			if (examCursor
					.getString(examCursor.getColumnIndex("examStartTime")) == null) {
				examPojo.setExamCreateTime(null);
			} else {
				examStartTime.parse3339(examCursor.getString(examCursor
						.getColumnIndex("examStartTime")));
				examPojo.setExamStartTime(examStartTime);
			}
			Time examEndTime = new Time();
			if (examCursor.getString(examCursor.getColumnIndex("examEndTime")) == null) {
				examPojo.setExamCreateTime(null);
			} else {
				examEndTime.parse3339(examCursor.getString(examCursor
						.getColumnIndex("examEndTime")));
				examPojo.setExamEndTime(examEndTime);
			}
			examPojos.add(examPojo);
		}

		examCursor.close();

		return examPojos;
	}

	public int getNumberOfExam() {
		int numberOfExam = 0;

		String[] keys = { "count(*)" };

		Cursor examCursor = daoHelper.select(tableName, keys, null, null);
		if (examCursor.isFirst()) {
			numberOfExam = Integer.parseInt(examCursor.getString(examCursor
					.getColumnIndex("count(*)")));
		}

		examCursor.close();

		return numberOfExam;
	}
}
