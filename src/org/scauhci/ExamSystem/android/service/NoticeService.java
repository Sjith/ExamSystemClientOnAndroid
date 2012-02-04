package org.scauhci.ExamSystem.android.service;

import java.util.ArrayList;

import org.scauhci.ExamSystem.android.dao.NoticeDao;
import org.scauhci.ExamSystem.android.dao.StudentDao;
import org.scauhci.ExamSystem.android.pojo.NoticePojo;

public class NoticeService {

	NoticeDao noticeDao = new NoticeDao();

	public ArrayList<NoticePojo> getAllNoticePojosOfStudent() {
		ArrayList<NoticePojo> noticePojos = null;
	
		noticePojos = noticeDao.getNoticePojosByStudentPojo(StudentDao.getLatestStudentPojo());
		
		return noticePojos;
	}
	
	public NoticePojo addNoticePojo(NoticePojo noticePojo) {
		
		return noticeDao.add(noticePojo);
	}
	
	public NoticePojo deleteNoticePojo(NoticePojo noticePojo) {
		
		return noticeDao.delete(noticePojo);
	}

}
