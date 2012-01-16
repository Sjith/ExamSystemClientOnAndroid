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

	public String getContent() {
		return noticeContent;
	}

	public void setContent(String content) {
		this.noticeContent = content;
	}

	public Time getPublicTime() {
		return noticePublicTime;
	}

	public void setPublicTime(Time publicTime) {
		this.noticePublicTime = publicTime;
	}

}
