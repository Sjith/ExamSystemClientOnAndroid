package org.scauhci.ExamSystem.android.dao;

import java.util.HashMap;

import org.scauhci.ExamSystem.android.pojo.StudentPojo;
import org.scauhci.ExamSystem.android.tool.ExecuteResultFlag;

import android.database.Cursor;

public class StudentDao {

	DaoHelper daoHelper = new DaoHelper(null, "exam_online.db", null, 0);
	String tableName = "student";
	private static StudentPojo latestStudentPojo = null;

	public StudentDao(StudentPojo studentPojo) {
		updateLatestStudentPojo(studentPojo);
	}

	public int add(StudentPojo studentPojo) {
		int executeResult = ExecuteResultFlag.ERROR;

		String[] keys = { "studentId", "studentName", "studentPassword" };
		String[] values = { studentPojo.getStudentId(),
				studentPojo.getStudentName(), studentPojo.getStudentPassword() };

		daoHelper.insert(tableName, keys, values);

		return executeResult;
	}

	public int delete(StudentPojo studentPojo) {
		int executeResult = ExecuteResultFlag.ERROR;

		String[] keys = { "studentId" };
		String[] values = { studentPojo.getStudentId() };

		daoHelper.delete(tableName, keys, values);
		
		return executeResult;
	}

	public int change(StudentPojo studentPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		
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
		
		String[] keys = new String[keyValueMap.size()];
		keyValueMap.keySet().toArray(keys);
		String[] newValues = new String[keys.length];
		keyValueMap.values().toArray(newValues);
		String[] whereConditionKeys = { "studentId" };
		String[] whereConditionValues = { studentPojo.getStudentId() };
		
		daoHelper.update(tableName, keys, newValues, whereConditionKeys,
				whereConditionValues);
		
		return executeResult;
	}

	public StudentPojo getStudentPojoByStudentName(String studentName) {
		StudentPojo studentPojo = new StudentPojo();
		
		String[] keys = { "*" };
		String[] whereConditionKeys = { "studentName" };
		String[] whereConditionValues = { studentName };

		Cursor studentCursor = daoHelper.select(tableName, keys,
				whereConditionKeys, whereConditionValues);
		while (studentCursor.moveToNext()) {
			studentPojo.setStudentId(studentCursor.getString(studentCursor
					.getColumnIndex("studentId")));
			studentPojo.setStudentName(studentCursor.getString(studentCursor
					.getColumnIndex("studentName")));
			studentPojo.setStudentPassword(studentCursor.getString(studentCursor
					.getColumnIndex("studentPassword")));
		}
		
		return studentPojo;
	}
	
	public void updateLatestStudentPojo(StudentPojo studentPojo) {
		StudentPojo rawQueryStudentPojo = getStudentPojoByStudentName(studentPojo.getStudentName());
		if (rawQueryStudentPojo.getStudentPassword() == studentPojo.getStudentPassword()) {
			this.latestStudentPojo = studentPojo;
		}
	}

	public static StudentPojo getLatestStudentPojo() {
		return latestStudentPojo;
	}
}
