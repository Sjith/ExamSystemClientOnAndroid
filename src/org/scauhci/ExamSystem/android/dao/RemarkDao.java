package org.scauhci.ExamSystem.android.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.scauhci.ExamSystem.android.pojo.RemarkPojo;
import org.scauhci.ExamSystem.android.pojo.StudentPojo;
import org.scauhci.ExamSystem.android.tool.ExecuteResultFlag;

import android.database.Cursor;
import android.text.format.Time;

public class RemarkDao {

	DaoHelper daoHelper = new DaoHelper(null, "exam_online.db", null, 0);
	String tableName = "remark";

	public int add(RemarkPojo remarkPojo) {
		int executeResult = ExecuteResultFlag.ERROR;

		String[] keys = { "remarkId", "studentId", "remarkTitle",
				"remarkContent", "remarkCreateTime", "remarkUpdateTime" };
		String[] values = { remarkPojo.getRemarkId(),
				remarkPojo.getStudentId(), remarkPojo.getRemarkTitle(),
				remarkPojo.getRemarkContent(),
				remarkPojo.getRemarkCreateTime().format3339(true),
				remarkPojo.getRemarkUpdateTime().format3339(true) };

		daoHelper.insert(tableName, keys, values);

		return executeResult;
	}

	public int delete(RemarkPojo remarkPojo) {
		int executeResult = ExecuteResultFlag.ERROR;

		String[] keys = { "remarkId" };
		String[] values = { remarkPojo.getRemarkId() };

		daoHelper.delete(tableName, keys, values);

		return executeResult;
	}

	public int change(RemarkPojo remarkPojo) {
		int executeResult = ExecuteResultFlag.ERROR;

		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		if (remarkPojo.getRemarkId() != null) {
			keyValueMap.put("remarkId", remarkPojo.getRemarkId());
		}
		if (remarkPojo.getStudentId() != null) {
			keyValueMap.put("studentId", remarkPojo.getStudentId());
		}
		if (remarkPojo.getRemarkTitle() != null) {
			keyValueMap.put("remarkTitle", remarkPojo.getRemarkId());
		}
		if (remarkPojo.getRemarkContent() != null) {
			keyValueMap.put("remarkContent", remarkPojo.getRemarkContent());
		}
		if (remarkPojo.getRemarkCreateTime() != null) {
			keyValueMap.put("remarkCreateTime", remarkPojo
					.getRemarkCreateTime().format3339(true));
		}
		if (remarkPojo.getRemarkUpdateTime() != null) {
			keyValueMap.put("remarkUpdateTime", remarkPojo
					.getRemarkUpdateTime().format3339(true));
		}

		String[] keys = new String[keyValueMap.size()];
		keyValueMap.keySet().toArray(keys);
		String[] newValues = new String[keys.length];
		keyValueMap.values().toArray(newValues);
		String[] whereConditionKeys = { "remarkId" };
		String[] whereConditionValues = { remarkPojo.getRemarkId() };

		daoHelper.update(tableName, keys, newValues, whereConditionKeys,
				whereConditionValues);

		return executeResult;
	}

	/*
	 * public RemarkPojo getRemarkPojoByRemarkId(String remarkId) { RemarkPojo
	 * remarkPojo = null; return remarkPojo; }
	 */

	public ArrayList<RemarkPojo> getRemarkPojosByStudentPojo(
			StudentPojo studentPojo) {
		ArrayList<RemarkPojo> remarkPojos = new ArrayList<RemarkPojo>();

		String[] keys = { "*" };
		String[] whereConditionKeys = { "studentId" };
		String[] whereConditionValues = { studentPojo.getStudentId() };

		Cursor remarkCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		while (remarkCursor.moveToNext()) {
			RemarkPojo remarkPojo = new RemarkPojo();
			remarkPojo.setRemarkId(remarkCursor.getString(remarkCursor
					.getColumnIndex("remarkId")));
			remarkPojo.setRemarkTitle(remarkCursor.getString(remarkCursor
					.getColumnIndex("remarkTitle")));
			remarkPojo.setStudentId(remarkCursor.getString(remarkCursor
					.getColumnIndex("studentId")));
			remarkPojo.setRemarkContent(remarkCursor.getString(remarkCursor
					.getColumnIndex("remarkContent")));
			Time remarkCreateTime = new Time();
			remarkCreateTime.parse3339(remarkCursor.getString(remarkCursor
					.getColumnIndex("remarkCreateTime")));
			remarkPojo.setRemarkCreateTime(remarkCreateTime);
			Time remarkUpdateTime = new Time();
			remarkUpdateTime.parse3339(remarkCursor.getString(remarkCursor
					.getColumnIndex("remarkUpdateTime")));
			remarkPojo.setRemarkUpdateTime(remarkUpdateTime);
			remarkPojos.add(remarkPojo);
		}

		return remarkPojos;
	}
}
