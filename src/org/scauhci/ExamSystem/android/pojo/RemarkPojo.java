package org.scauhci.ExamSystem.android.pojo;

import android.text.format.Time;

public class RemarkPojo {

	private String remarkId;
	private String studentId;
	private String remarkName;
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

	public String getRemarkName() {
		return remarkName;
	}

	public void setRemarkName(String remarkName) {
		this.remarkName = remarkName;
	}

	public String getRemarkContent() {
		return remarkContent;
	}

	public void setRemarkContent(String remarkContent) {
		this.remarkContent = remarkContent;
	}

	public Time getRemarkCreateTime() {
		return remarkCreateTime;
	}

	public void setRemarkCreateTime(Time remarkCreateTime) {
		this.remarkCreateTime = remarkCreateTime;
	}

	public Time getRemarkUpdateTime() {
		return remarkUpdateTime;
	}

	public void setRemarkUpdateTime(Time remarkUpdateTime) {
		this.remarkUpdateTime = remarkUpdateTime;
	}

}
