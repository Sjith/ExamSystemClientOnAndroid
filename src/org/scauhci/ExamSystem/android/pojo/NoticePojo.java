package org.scauhci.ExamSystem.android.pojo;

import android.text.format.Time;

public class NoticePojo {

	private String noticeId;
	private String noticeName;
	private String noticeContent;
	private Time noticePublicTime;

	public String getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	public String getNoticeName() {
		return noticeName;
	}

	public void setNoticeName(String noticeName) {
		this.noticeName = noticeName;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public Time getNoticePublicTime() {
		return noticePublicTime;
	}

	public void setNoticePublicTime(Time noticePublicTime) {
		this.noticePublicTime = noticePublicTime;
	}

}
