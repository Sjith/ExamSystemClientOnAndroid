package org.scauhci.ExamSystem.android.pojo;

import android.text.format.Time;

public class RemarkPojo {

	private String remarkId;
	private String studentId;
	private String remarkTitle;
	private String remarkContent;
	private Time remarkCreateTime;
	private Time remarkUpdateTime;

	public String getRemarkId() {
		return remarkId;
	}

	public void setRemarkId(String remarkId) {
		this.remarkId = remarkId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getRemarkTitle() {
		return remarkTitle;
	}

	public void setRemarkTitle(String remarkTitle) {
		this.remarkTitle = remarkTitle;
	}

	public String getContent() {
		return remarkContent;
	}

	public void setContent(String content) {
		this.remarkContent = content;
	}

	public Time getCreateTime() {
		return remarkCreateTime;
	}

	public void setCreateTime(Time createTime) {
		this.remarkCreateTime = createTime;
	}

	public Time getUpdateTime() {
		return remarkUpdateTime;
	}

	public void setUpdateTime(Time updateTime) {
		this.remarkUpdateTime = updateTime;
	}

}
