package org.scauhci.ExamSystem.android.dao;

import java.util.HashMap;

import org.scauhci.ExamSystem.android.pojo.ExamPojo;
import org.scauhci.ExamSystem.android.tool.ExecuteResultFlag;
import org.scauhci.ExamSystem.android.tool.HashValue;

import android.database.Cursor;
import android.text.format.Time;

public class ExamDao {

	DaoHelper daoHelper = new DaoHelper(null, "exam_online.db", null, 0);
	String tableName = "exam";
	private static ExamPojo latestExamPojo = null;

	public ExamDao() {
		updateLatestExamPojo();
	}

	public int add(ExamPojo examPojo) {
		int executeResult = ExecuteResultFlag.ERROR;

		long examId;
		for (examId = HashValue.getDJBHashValue(examPojo.getExamName()); getExamPojoByExamId(Long
				.toHexString(examId)) != null; examId++)
			;

		String[] keys = { "examId", "courseId", "teacherId", "paperId",
				"examExplain", "examName", "examCreateTime", "examStartTime",
				"examEndTime" };
		String[] values = { Long.toHexString(examId), examPojo.getCourseId(),
				examPojo.getTeacherId(), examPojo.getPaperId(),
				examPojo.getExamExplain(), examPojo.getExamName(),
				examPojo.getExamCreateTime().format3339(true),
				examPojo.getExamStartTime().format3339(true),
				examPojo.getExamEndTime().format3339(true) };

		daoHelper.insert(tableName, keys, values);

		return executeResult;
	}

	public int delete(ExamPojo examPojo) {
		int executeResult = ExecuteResultFlag.ERROR;

		String[] keys = { "examId" };
		String[] values = { examPojo.getExamId() };

		daoHelper.delete(tableName, keys, values);

		return executeResult;
	}

	public int change(ExamPojo examPojo) {
		int executeResult = ExecuteResultFlag.ERROR;

		HashMap<String, String> keyValueMap = new HashMap<String, String>();

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

		String[] keys = new String[keyValueMap.size()];
		keyValueMap.keySet().toArray(keys);
		String[] newValues = new String[keys.length];
		keyValueMap.values().toArray(newValues);
		String[] whereConditionKeys = { "examId" };
		String[] whereConditionValues = { examPojo.getExamId() };

		daoHelper.update(tableName, keys, newValues, whereConditionKeys,
				whereConditionValues);

		return executeResult;
	}

	public ExamPojo getExamPojoByExamId(String examId) {
		ExamPojo examPojo = new ExamPojo();

		String[] keys = { "*" };
		String[] whereConditionKeys = { "examId" };
		String[] whereConditionValues = { examId };

		Cursor examCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		while (examCursor.moveToNext()) {
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
			examCreateTime.parse3339(examCursor.getString(examCursor
					.getColumnIndex("examCreateTime")));
			examPojo.setExamCreateTime(examCreateTime);
			Time examStartTime = new Time();
			examStartTime.parse3339(examCursor.getString(examCursor
					.getColumnIndex("examStartTime")));
			examPojo.setExamCreateTime(examStartTime);
			Time examEndTime = new Time();
			examEndTime.parse3339(examCursor.getString(examCursor
					.getColumnIndex("examEndTime")));
			examPojo.setExamCreateTime(examEndTime);
		}

		return examPojo;
	}

	public static ExamPojo getLatestExamPojo() {
		return latestExamPojo;
	}

	public int updateLatestExamPojo() {
		int executeResult = ExecuteResultFlag.ERROR;
		ExamPojo examPojo = new ExamPojo();

		String[] keys = { "*" };

		Cursor examCursor = daoHelper.select(tableName, keys, null, null);
		if (examCursor.isFirst()) {
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
			examCreateTime.parse3339(examCursor.getString(examCursor
					.getColumnIndex("examCreateTime")));
			examPojo.setExamCreateTime(examCreateTime);
			Time examStartTime = new Time();
			examStartTime.parse3339(examCursor.getString(examCursor
					.getColumnIndex("examStartTime")));
			examPojo.setExamCreateTime(examStartTime);
			Time examEndTime = new Time();
			examEndTime.parse3339(examCursor.getString(examCursor
					.getColumnIndex("examEndTime")));
			examPojo.setExamCreateTime(examEndTime);
			this.latestExamPojo = examPojo;
			executeResult = ExecuteResultFlag.SUCCESS;
		}

		return executeResult;
	}

	public int getNumberOfExam() {
		int numberOfExam = 0;

		String[] keys = { "count(*)" };

		Cursor examCursor = daoHelper.select(tableName, keys, null, null);
		if (examCursor.isFirst()) {
			numberOfExam = Integer.parseInt(examCursor.getString(examCursor
					.getColumnIndex("count(*)")));
		}

		return numberOfExam;
	}

	// targetTime初始化为系统当前时间，当注意系统当前时间是否与网络时间对对应。
	public Time getRemainTime() {
		Time targetTime = new Time();
		targetTime.setToNow();
		return getRemainTime(targetTime);
	}

	public Time getRemainTime(Time targetTime) {
		Time remainTime = new Time();
		Time examStartTime = this.latestExamPojo.getExamStartTime();

		remainTime.set(targetTime.toMillis(false)
				- examStartTime.toMillis(false));

		return remainTime;
	}
}
