package org.scauhci.ExamSystem.android.service;

import java.util.ArrayList;

import org.scauhci.ExamSystem.android.dao.RemarkDao;
import org.scauhci.ExamSystem.android.dao.StudentDao;
import org.scauhci.ExamSystem.android.pojo.RemarkPojo;
import org.scauhci.ExamSystem.android.tool.Flag;

public class RemarkService {

	public ArrayList<RemarkPojo> getAllRemarkPojos() {
		ArrayList<RemarkPojo> remarkPojos = null;
	
		RemarkDao remarkDao = new RemarkDao();
		remarkPojos = remarkDao.getRemarkPojosByStudentPojo(StudentDao.getLatestStudentPojo());
		
		return remarkPojos;
	}
	
	public int addRemarkPojo(RemarkPojo remarkPojo) {
		int executeResult = Flag.ERROR;
		
		RemarkDao remarkDao = new RemarkDao();
		remarkDao.add(remarkPojo);
		
		return executeResult;
	}
	
	public int deleteRemarkPojo(RemarkPojo remarkPojo) {
		int executeResult = Flag.ERROR;
		
		RemarkDao remarkDao = new RemarkDao();
		remarkDao.delete(remarkPojo);
		
		return executeResult;
	}

}
