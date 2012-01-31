package org.scauhci.ExamSystem.android.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.scauhci.ExamSystem.android.pojo.CoursePojo;
import org.scauhci.ExamSystem.android.pojo.NoticePojo;
import org.scauhci.ExamSystem.android.pojo.StudentPojo;
import org.scauhci.ExamSystem.android.tool.ExecuteResultFlag;
import org.scauhci.ExamSystem.android.tool.HashValue;

import android.database.Cursor;
import android.text.format.Time;

public class NoticeDao {

	DaoHelper daoHelper = new DaoHelper(null, "exam_online.db", null, 0);
	String tableName = "notice";

	public int add(NoticePojo noticePojo) {
		int executeResult = ExecuteResultFlag.ERROR;

		long noticeId;
		for (noticeId = HashValue.getDJBHashValue(noticePojo.getNoticeName()); getNoticePojoByNoticeId(Long
				.toHexString(noticeId)) != null; noticeId++)
			;
		noticePojo.setNoticeId(Long.toHexString(noticeId));

		String[] keys = { "noticeId", "noticeName", "noticeContent",
				"noticePublicTime" };
		String[] values = { noticePojo.getNoticeId(),
				noticePojo.getNoticeName(), noticePojo.getNoticeContent(),
				noticePojo.getNoticePublicTime().format3339(true) };

		daoHelper.insert(tableName, keys, values);

		return executeResult;
	}

	public int delete(NoticePojo noticePojo) {
		int executeResult = ExecuteResultFlag.ERROR;

		String[] keys = { "noticeId" };
		String[] values = { noticePojo.getNoticeId() };

		daoHelper.delete(tableName, keys, values);

		return executeResult;
	}

	public int change(NoticePojo noticePojo) {
		int executeResult = ExecuteResultFlag.ERROR;

		HashMap<String, String> keyValueMap = getKeyValueMapByNoticePojo(noticePojo);

		String[] keys = new String[keyValueMap.size()];
		keyValueMap.keySet().toArray(keys);
		String[] newValues = new String[keys.length];
		keyValueMap.values().toArray(newValues);
		String[] whereConditionKeys = { "noticeId" };
		String[] whereConditionValues = { noticePojo.getNoticeId() };

		daoHelper.update(tableName, keys, newValues, whereConditionKeys,
				whereConditionValues);

		return executeResult;
	}
	
	public HashMap<String, String> getKeyValueMapByNoticePojo(NoticePojo noticePojo){
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		if (noticePojo.getNoticeName() != null) {
			keyValueMap.put("noticeName", noticePojo.getNoticeName());
		}
		if (noticePojo.getNoticeContent() != null) {
			keyValueMap.put("noticeContent", noticePojo.getNoticeContent());
		}
		if (noticePojo.getNoticePublicTime() != null) {
			keyValueMap.put("noticePublicTime", noticePojo
					.getNoticePublicTime().format3339(true));
		}
		
		return keyValueMap;
	}

	public NoticePojo getNoticePojoByNoticeId(String noticeId) {
		NoticePojo noticePojo = new NoticePojo();

		String[] keys = { "*" };
		String[] whereConditionKeys = { "noticeId" };
		String[] whereConditionValues = { noticeId };

		Cursor noticeCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		while (noticeCursor.moveToNext()) {
			noticePojo.setNoticeId(noticeCursor.getString(noticeCursor
					.getColumnIndex("noticeId")));
			noticePojo.setNoticeName(noticeCursor.getString(noticeCursor
					.getColumnIndex("noticeName")));
			noticePojo.setNoticeContent(noticeCursor.getString(noticeCursor
					.getColumnIndex("noticeContent")));
			Time noticePublicTime = new Time();
			noticePublicTime.parse3339(noticeCursor.getString(noticeCursor
					.getColumnIndex("noticePublicTime")));
			noticePojo.setNoticePublicTime(noticePublicTime);
		}

		return noticePojo;
	}
	
	public NoticePojo completeNoticePojo(NoticePojo noticePojo) {
		HashMap<String, String> keyValueMap = getKeyValueMapByNoticePojo(noticePojo);

		String[] keys = { "*" };
		String[] whereConditionKeys = new String[keyValueMap.size()];
		keyValueMap.keySet().toArray(whereConditionKeys);
		String[] whereConditionValues = new String[whereConditionKeys.length];
		keyValueMap.values().toArray(whereConditionValues);

		Cursor noticeCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		while (noticeCursor.moveToNext()) {
			noticePojo.setNoticeId(noticeCursor.getString(noticeCursor
					.getColumnIndex("noticeId")));
			noticePojo.setNoticeName(noticeCursor.getString(noticeCursor
					.getColumnIndex("noticeName")));
			noticePojo.setNoticeContent(noticeCursor.getString(noticeCursor
					.getColumnIndex("noticeContent")));
			Time noticePublicTime = new Time();
			noticePublicTime.parse3339(noticeCursor.getString(noticeCursor
					.getColumnIndex("noticePublicTime")));
			noticePojo.setNoticePublicTime(noticePublicTime);
		}

		return noticePojo;
	}

	public ArrayList<NoticePojo> getNoticePojosByStudentPojo(
			StudentPojo studentPojo) {
		ArrayList<NoticePojo> noticePojos = new ArrayList<NoticePojo>();

		String[] keys = { "*" };
		String[] whereConditionKeys = { "studentId" };
		String[] whereConditionValues = { studentPojo.getStudentId() };

		Cursor noticeCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		while (noticeCursor.moveToNext()) {
			NoticePojo noticePojo = new NoticePojo();
			noticePojo.setNoticeId(noticeCursor.getString(noticeCursor
					.getColumnIndex("noticeId")));
			noticePojo.setNoticeName(noticeCursor.getString(noticeCursor
					.getColumnIndex("noticeName")));
			noticePojo.setNoticeContent(noticeCursor.getString(noticeCursor
					.getColumnIndex("noticeContent")));
			Time noticePublicTime = new Time();
			noticePublicTime.parse3339(noticeCursor.getString(noticeCursor
					.getColumnIndex("noticePublicTime")));
			noticePojo.setNoticePublicTime(noticePublicTime);
			noticePojos.add(noticePojo);
		}

		return noticePojos;
	}
}
