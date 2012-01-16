package org.scauhci.ExamSystem.android.dao;

import java.util.ArrayList;

import org.scauhci.ExamSystem.android.pojo.RemarkPojo;
import org.scauhci.ExamSystem.android.pojo.StudentPojo;
import org.scauhci.ExamSystem.android.tool.ExecuteResultFlag;

public class RemarkDao {

	public int add(RemarkPojo remarkPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}

	public int delete(RemarkPojo remarkPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}

	public int change(RemarkPojo remarkPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}
	
	public RemarkPojo getRemarkPojoByRemarkId(String remarkId) {
		RemarkPojo remarkPojo = null;
		return remarkPojo;
	}
	
	public ArrayList<RemarkPojo> getRemarkPojosByStudentPojo(StudentPojo studentPojo) {
		ArrayList<RemarkPojo> remarkPojos = null;
		return remarkPojos;
	}
}
