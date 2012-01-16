package org.scauhci.ExamSystem.android.pojo;

import android.text.format.Time;

public class ExamPojo {

	private String examId;
	private String courseId;
	private String teacherId;
	private String paperId;
	private String examExplain;
	private String examName;
	private Time examCreateTime;
	private Time examStartTime;
	private Time examEndTime;

	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getPaperId() {
		return paperId;
	}

	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}

	public String getExamExplain() {
		return examExplain;
	}

	public void setExamExplain(String examExplain) {
		this.examExplain = examExplain;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public Time getExamCreateTime() {
		return examCreateTime;
	}

	public void setExamCreateTime(Time examCreateTime) {
		this.examCreateTime = examCreateTime;
	}

	public Time getExamStartTime() {
		return examStartTime;
	}

	public void setExamStartTime(Time examStartTime) {
		this.examStartTime = examStartTime;
	}

	public Time getExamEndTime() {
		return examEndTime;
	}

	public void setExamEndTime(Time examEndTime) {
		this.examEndTime = examEndTime;
	}

}
