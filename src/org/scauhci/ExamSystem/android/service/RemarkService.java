package org.scauhci.ExamSystem.android.service;

import java.util.ArrayList;

import org.scauhci.ExamSystem.android.pojo.RemarkPojo;
import org.scauhci.ExamSystem.android.pojo.StudentPojo;
import org.scauhci.ExamSystem.android.tool.ExecuteResultFlag;

public class RemarkService {

	public ArrayList<RemarkPojo> getAllRemarkPojos() {
		ArrayList<RemarkPojo> remarkPojos = null;
		return remarkPojos;
	}
	
	public int addRemarkPojo(String remarkTitle, String remarkContent) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}
	
	public int deleteRemarkPojo(RemarkPojo remarkPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}

}
