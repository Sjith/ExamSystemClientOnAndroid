package org.scauhci.ExamSystem.android.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

import org.scauhci.ExamSystem.android.R;
import org.scauhci.ExamSystem.android.pojo.ExamPojo;
import org.scauhci.ExamSystem.android.tool.ExecuteResultFlag;

import android.database.Cursor;
import android.text.format.Time;

public class ExamDao {

	DaoHelper daoHelper = new DaoHelper(null, "exam_online.db", null, 0);
	String tableName = "exam";
	ExamPojo examPojo = null;

	public int add(ExamPojo examPojo) {
		int executeResult = ExecuteResultFlag.ERROR;

		String[] keys = { "examId", "courseId", "teacherId", "paperId",
				"examExplain", "examName", "examCreateTime", "examStartTime",
				"examEndTime" };
		String[] values = { examPojo.getExamId(), examPojo.getCourseId(),
				examPojo.getTeacherId(), examPojo.getPaperId(),
				examPojo.getExamExplain(), examPojo.getExamName(),
				examPojo.getExamCreateTime() + "",
				examPojo.getExamStartTime() + "",
				examPojo.getExamEndTime() + "" };

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
		/*
		 * if{ "examId", "courseId", "teacherId", "paperId", "examExplain",
		 * "examName", "examCreateTime", "examStartTime", "examEndTime" }
		 */
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
			keyValueMap
					.put("examCreateTime", examPojo.getExamCreateTime() + "");
		}
		if (examPojo.getExamStartTime() != null) {
			keyValueMap.put("examStartTime", examPojo.getExamStartTime() + "");
		}
		if (examPojo.getExamEndTime() != null) {
			keyValueMap.put("examEndTime", examPojo.getExamEndTime() + "");
		}
		
		String[] keys = new String[keyValueMap.size()];
		keyValueMap.keySet().toArray(keys);
		String[] newValues = new String[keys.length];
		keyValueMap.values().toArray(newValues);
		String[] whereConditionKeys = { "examId" };
		String[] whereConditionValues = { examPojo.getExamId() };

		daoHelper.update(tableName, keys, newValues, whereConditionKeys, whereConditionValues);
		
		return executeResult;
	}

	public ExamPojo getExamPojoByExamId(String examId) {
		ExamPojo examPojo = new ExamPojo();
		
		String[] keys = { "*" };
		String[] whereConditionKeys = { "examId" };
		String[] whereConditionValues = { examId };
		
		Cursor examCursor = daoHelper.select(tableName, keys, whereConditionKeys, whereConditionValues);
		while (examCursor.moveToNext()) {
			examPojo.setExamId(examCursor.getString(examCursor.getColumnIndex("examId")));
			examPojo.setCourseId(examCursor.getString(examCursor.getColumnIndex("courseId")));
			examPojo.setTeacherId(examCursor.getString(examCursor.getColumnIndex("teacherId")));
			examPojo.setPaperId(examCursor.getString(examCursor.getColumnIndex("paperId")));
			examPojo.setExamExplain(examCursor.getString(examCursor.getColumnIndex("examExplain")));
			examPojo.setExamName(examCursor.getString(examCursor.getColumnIndex("examName")));
			//examCreateTime,examStartTime,examEndTime aren't parsed into exampojo.
		}
		
		return examPojo;
	}

	public ExamPojo getExamPojo() {
		ExamPojo examPojo = new ExamPojo();

		String[] keys = { "*" };
		
		Cursor examCursor = daoHelper.select(tableName, keys, null, null);
		if (examCursor.isFirst()) {
			examPojo.setExamId(examCursor.getString(examCursor.getColumnIndex("examId")));
			examPojo.setCourseId(examCursor.getString(examCursor.getColumnIndex("courseId")));
			examPojo.setTeacherId(examCursor.getString(examCursor.getColumnIndex("teacherId")));
			examPojo.setPaperId(examCursor.getString(examCursor.getColumnIndex("paperId")));
			examPojo.setExamExplain(examCursor.getString(examCursor.getColumnIndex("examExplain")));
			examPojo.setExamName(examCursor.getString(examCursor.getColumnIndex("examName")));
			//examCreateTime,examStartTime,examEndTime aren't parsed into exampojo.
		}
		
		return examPojo;
	}
	
	public ExamPojo getLatestExamPojo() {
		ExamPojo latestExamPojo = new ExamPojo();

		String[] keys = { "*" };
		
		Cursor examCursor = daoHelper.select(tableName, keys, null, null);
		if (examCursor.isFirst()) {
			latestExamPojo.setExamId(examCursor.getString(examCursor.getColumnIndex("examId")));
			latestExamPojo.setCourseId(examCursor.getString(examCursor.getColumnIndex("courseId")));
			latestExamPojo.setTeacherId(examCursor.getString(examCursor.getColumnIndex("teacherId")));
			latestExamPojo.setPaperId(examCursor.getString(examCursor.getColumnIndex("paperId")));
			latestExamPojo.setExamExplain(examCursor.getString(examCursor.getColumnIndex("examExplain")));
			latestExamPojo.setExamName(examCursor.getString(examCursor.getColumnIndex("examName")));
			//examCreateTime,examStartTime,examEndTime aren't parsed into exampojo.
		}
		
		return latestExamPojo;
	}

	public int getNumberOfExam() {
		int numberOfExam = 0;
		
		String[] keys = { "count(*)" };
		
		Cursor examCursor = daoHelper.select(tableName, keys, null, null);
		if (examCursor.isFirst()) {
			numberOfExam = Integer.parseInt(examCursor.getString(examCursor.getColumnIndex("count(*)")));
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
		Time remainTime = null;
		
		remainTime = Time.
		
		return remainTime;
	}
}
