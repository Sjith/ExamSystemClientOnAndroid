package org.scauhci.ExamSystem.android.dao;

import java.util.HashMap;

import org.scauhci.ExamSystem.android.pojo.CoursePojo;
import org.scauhci.ExamSystem.android.pojo.StudentPojo;
import org.scauhci.ExamSystem.android.tool.Flag;
import org.scauhci.ExamSystem.android.tool.HashValue;

import android.database.Cursor;

public class StudentDao {

	DaoHelper daoHelper = new DaoHelper(null, "exam_online.db", null, 0);
	String tableName = "student";
	private static StudentPojo latestStudentPojo = null;

	public StudentDao(StudentPojo studentPojo) {
		updateLatestStudentPojo(studentPojo);
	}

	public StudentPojo add(StudentPojo studentPojo) {
		
		if (completeStudentPojo(studentPojo) == null) {
			String[] keys = { "studentId", "studentName", "studentPassword" };
			String[] values = { studentPojo.getStudentId(),
					studentPojo.getStudentName(), studentPojo.getStudentPassword() };
			
			daoHelper.insert(tableName, keys, values);
		} else {
			studentPojo = null;
		}

		return studentPojo;
	}

	public StudentPojo delete(StudentPojo studentPojo) {

		if ((studentPojo = completeStudentPojo(studentPojo)) != null) {
			String[] keys = { "studentId" };
			String[] values = { studentPojo.getStudentId() };

			daoHelper.delete(tableName, keys, values);
		}
		
		return studentPojo;
	}

	public StudentPojo change(StudentPojo studentPojo) {
		HashMap<String, String> keyValueMap = getKeyValueMapByStudentPojo(studentPojo);
		
		String[] keys = new String[keyValueMap.size()];
		keyValueMap.keySet().toArray(keys);
		String[] newValues = new String[keys.length];
		keyValueMap.values().toArray(newValues);
		String[] whereConditionKeys = { "studentId" };
		String[] whereConditionValues = { studentPojo.getStudentId() };
		
		daoHelper.update(tableName, keys, newValues, whereConditionKeys,
				whereConditionValues);
		studentPojo = completeStudentPojo(studentPojo);
		
		return studentPojo;
	}

	public HashMap<String, String> getKeyValueMapByStudentPojo(StudentPojo studentPojo){
		HashMap<String, String> keyValueMap = new HashMap<String, String>();
		
		if (studentPojo.getStudentId() != null) {
			keyValueMap.put("questionContent", studentPojo.getStudentId());
		}
		if (studentPojo.getStudentName() != null) {
			keyValueMap.put("studentName", studentPojo.getStudentName());
		}
		if (studentPojo.getStudentPassword() != null) {
			keyValueMap.put("studentPassword", studentPojo.getStudentPassword());
		}
		
		return keyValueMap;
	}
	
	public StudentPojo getStudentPojoByStudentId(String studentId) {
		StudentPojo studentPojo = new StudentPojo();
		
		String[] keys = { "*" };
		String[] whereConditionKeys = { "studentId" };
		String[] whereConditionValues = { studentId };

		Cursor studentCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		if (studentCursor.moveToFirst()) {
			studentPojo.setStudentId(studentCursor.getString(studentCursor
					.getColumnIndex("studentId")));
			studentPojo.setStudentName(studentCursor.getString(studentCursor
					.getColumnIndex("studentName")));
			studentPojo.setStudentPassword(studentCursor.getString(studentCursor
					.getColumnIndex("studentPassword")));
		} else {
			studentPojo = null;
		}
		
		return studentPojo;
	}
	
	public StudentPojo completeStudentPojo(StudentPojo studentPojo) {
		HashMap<String, String> keyValueMap = getKeyValueMapByStudentPojo(studentPojo);

		String[] keys = { "*" };
		String[] whereConditionKeys = new String[keyValueMap.size()];
		keyValueMap.keySet().toArray(whereConditionKeys);
		String[] whereConditionValues = new String[whereConditionKeys.length];
		keyValueMap.values().toArray(whereConditionValues);
		
		Cursor studentCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		if (studentCursor.moveToFirst()) {
			studentPojo.setStudentId(studentCursor.getString(studentCursor
					.getColumnIndex("studentId")));
			studentPojo.setStudentName(studentCursor.getString(studentCursor
					.getColumnIndex("studentName")));
			studentPojo.setStudentPassword(studentCursor.getString(studentCursor
					.getColumnIndex("studentPassword")));
		} else {
			studentPojo = null;
		}
		
		return studentPojo;
	}
	
	public StudentPojo getStudentPojoByStudentName(String studentName) {
		StudentPojo studentPojo = new StudentPojo();
		
		String[] keys = { "*" };
		String[] whereConditionKeys = { "studentName" };
		String[] whereConditionValues = { studentName };
		
		Cursor studentCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		if (studentCursor.moveToFirst()) {
			studentPojo.setStudentId(studentCursor.getString(studentCursor
					.getColumnIndex("studentId")));
			studentPojo.setStudentName(studentCursor.getString(studentCursor
					.getColumnIndex("studentName")));
			studentPojo.setStudentPassword(studentCursor.getString(studentCursor
					.getColumnIndex("studentPassword")));
		} else {
			studentPojo = null;
		}
		
		return studentPojo;
	}
	
	public void updateLatestStudentPojo(StudentPojo studentPojo) {
		StudentPojo rawQueryStudentPojo = getStudentPojoByStudentId(studentPojo.getStudentId());
		if (rawQueryStudentPojo.getStudentPassword().equals(studentPojo.getStudentPassword())) {
			StudentDao.latestStudentPojo = studentPojo;
		}
	}

	public static StudentPojo getLatestStudentPojo() {
		return latestStudentPojo;
	}
}
