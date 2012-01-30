package org.scauhci.ExamSystem.android.pojo;

import java.util.ArrayList;

import android.text.format.Time;

public class PaperPojo {
	
	private String paperId;
	private String paperName;
	private int paperType;
	private String teacherId;
	private Time paperCreateTime;
	private String courseId;
	private float paperTotalScore;
	private String paperExplain;
	
	public String getPaperId() {
		return paperId;
	}
	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	public int getPaperType() {
		return paperType;
	}
	public void setPaperType(int paperType) {
		this.paperType = paperType;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public Time getPaperCreateTime() {
		return paperCreateTime;
	}
	public void setPaperCreateTime(Time paperCreateTime) {
		this.paperCreateTime = paperCreateTime;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public float getPaperTotalScore() {
		return paperTotalScore;
	}
	public void setPaperTotalScore(float paperTotalScore) {
		this.paperTotalScore = paperTotalScore;
	}
	public String getPaperExplain() {
		return paperExplain;
	}
	public void setPaperExplain(String paperExplain) {
		this.paperExplain = paperExplain;
	}

}
