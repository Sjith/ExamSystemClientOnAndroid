package org.scauhci.ExamSystem.android.dao;

import org.scauhci.ExamSystem.android.pojo.StudentPojo;
import org.scauhci.ExamSystem.android.tool.ExecuteResultFlag;

public class StudentDao {

	public int add(StudentPojo studentPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}

	public int delete(StudentPojo studentPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}

	public int change(StudentPojo studentPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}
	
	public StudentPojo getStudentPojoByStudentName(String studentName) {
		StudentPojo studentPojo = null;
		return studentPojo;	
	}
	
	public StudentPojo getStudentPojo() {
		StudentPojo studentPojo = null;
		return studentPojo;
	}
}
