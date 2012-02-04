package org.scauhci.ExamSystem.android.service;

import java.util.ArrayList;

import org.scauhci.ExamSystem.android.dao.RemarkDao;
import org.scauhci.ExamSystem.android.dao.StudentDao;
import org.scauhci.ExamSystem.android.pojo.RemarkPojo;
import org.scauhci.ExamSystem.android.tool.Flag;

public class RemarkService {

	RemarkDao remarkDao = new RemarkDao();

	public ArrayList<RemarkPojo> getAllRemarkPojosOfStudent() {
		ArrayList<RemarkPojo> remarkPojos = null;
	
		remarkPojos = remarkDao.getRemarkPojosByStudentPojo(StudentDao.getLatestStudentPojo());
		
		return remarkPojos;
	}
	
	public RemarkPojo addRemarkPojo(RemarkPojo remarkPojo) {

		if (remarkPojo.getStudentId() == null) {
			remarkPojo.setStudentId(StudentDao.getLatestStudentPojo().getStudentId());
		}
		
		return remarkDao.add(remarkPojo);
	}
	
	public RemarkPojo deleteRemarkPojo(RemarkPojo remarkPojo) {

		if (remarkPojo.getStudentId() == null) {
			remarkPojo.setStudentId(StudentDao.getLatestStudentPojo().getStudentId());
		}
		
		return remarkDao.delete(remarkPojo);
	}
	
	public RemarkPojo changeRemarkPojo(RemarkPojo remarkPojo) {
		
		return remarkDao.change(remarkPojo);
	}

}
