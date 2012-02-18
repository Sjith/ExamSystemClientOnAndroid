package org.scauhci.ExamSystem.android.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.scauhci.ExamSystem.android.pojo.CoursePojo;
import org.scauhci.ExamSystem.android.pojo.RemarkPojo;
import org.scauhci.ExamSystem.android.pojo.StudentPojo;
import org.scauhci.ExamSystem.android.tool.Flag;
import org.scauhci.ExamSystem.android.tool.HashValue;

import android.database.Cursor;
import android.text.format.Time;

public class RemarkDao {

	DaoHelper daoHelper = new DaoHelper(null, "exam_online.db", null, 1);
	String tableName = "remark";

	public RemarkPojo add(RemarkPojo remarkPojo) {
		
		if (completeRemarkPojo(remarkPojo) == null) {
			long remarkId;
			for (remarkId = HashValue.getDJBHashValue(remarkPojo.getRemarkName()); getRemarkPojoByRemarkId(Long
					.toHexString(remarkId)) != null; remarkId++)
				;
			remarkPojo.setRemarkId(Long.toHexString(remarkId));
			
			String[] keys = { "remarkId", "studentId", "remarkName",
					"remarkContent", "remarkCreateTime", "remarkUpdateTime" };
			String[] values = { remarkPojo.getRemarkId(),
					remarkPojo.getStudentId(), remarkPojo.getRemarkName(),
					remarkPojo.getRemarkContent(),
					remarkPojo.getRemarkCreateTime().format3339(true),
					remarkPojo.getRemarkUpdateTime().format3339(true) };
			
			daoHelper.insert(tableName, keys, values);
		} else {
			remarkPojo = null;
		}

		return remarkPojo;
	}

	public RemarkPojo delete(RemarkPojo remarkPojo) {
		if ((remarkPojo = completeRemarkPojo(remarkPojo)) != null) {
			String[] keys = { "remarkId" };
			String[] values = { remarkPojo.getRemarkId() };
			
			daoHelper.delete(tableName, keys, values);
		}

		return remarkPojo;
	}

	public RemarkPojo change(RemarkPojo remarkPojo) {
		HashMap<String, String> keyValueMap = getKeyValueMapByRemarkPojo(remarkPojo);

		String[] keys = new String[keyValueMap.size()];
		keyValueMap.keySet().toArray(keys);
		String[] newValues = new String[keys.length];
		keyValueMap.values().toArray(newValues);
		String[] whereConditionKeys = { "remarkId" };
		String[] whereConditionValues = { remarkPojo.getRemarkId() };

		daoHelper.update(tableName, keys, newValues, whereConditionKeys,
				whereConditionValues);
		remarkPojo = completeRemarkPojo(remarkPojo);

		return remarkPojo;
	}

	public HashMap<String, String> getKeyValueMapByRemarkPojo(
			RemarkPojo remarkPojo) {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		if (remarkPojo.getRemarkId() != null) {
			keyValueMap.put("remarkId", remarkPojo.getRemarkId());
		}
		if (remarkPojo.getStudentId() != null) {
			keyValueMap.put("studentId", remarkPojo.getStudentId());
		}
		if (remarkPojo.getRemarkName() != null) {
			keyValueMap.put("remarkName", remarkPojo.getRemarkName());
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

		return keyValueMap;
	}

	public RemarkPojo getRemarkPojoByRemarkId(String remarkId) {
		RemarkPojo remarkPojo = new RemarkPojo();

		String[] keys = { "*" };
		String[] whereConditionKeys = { "remarkId" };
		String[] whereConditionValues = { remarkId };

		Cursor remarkCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		if (remarkCursor.moveToFirst()) {
			remarkPojo.setRemarkId(remarkCursor.getString(remarkCursor
					.getColumnIndex("remarkId")));
			remarkPojo.setRemarkName(remarkCursor.getString(remarkCursor
					.getColumnIndex("remarkName")));
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
		} else {
			remarkPojo = null;
		}
		
		remarkCursor.close();

		return remarkPojo;
	}

	public RemarkPojo completeRemarkPojo(RemarkPojo remarkPojo) {
		HashMap<String, String> keyValueMap = getKeyValueMapByRemarkPojo(remarkPojo);

		String[] keys = { "*" };
		String[] whereConditionKeys = new String[keyValueMap.size()];
		keyValueMap.keySet().toArray(whereConditionKeys);
		String[] whereConditionValues = new String[whereConditionKeys.length];
		keyValueMap.values().toArray(whereConditionValues);

		Cursor remarkCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		if (remarkCursor.moveToFirst()) {
			remarkPojo.setRemarkId(remarkCursor.getString(remarkCursor
					.getColumnIndex("remarkId")));
			remarkPojo.setRemarkName(remarkCursor.getString(remarkCursor
					.getColumnIndex("remarkName")));
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
		} else {
			remarkPojo = null;
		}
		
		remarkCursor.close();

		return remarkPojo;
	}

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
			remarkPojo.setRemarkName(remarkCursor.getString(remarkCursor
					.getColumnIndex("remarkName")));
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
		
		remarkCursor.close();

		return remarkPojos;
	}
}
