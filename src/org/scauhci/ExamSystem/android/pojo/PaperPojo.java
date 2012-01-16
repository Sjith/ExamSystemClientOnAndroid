package org.scauhci.ExamSystem.android.pojo;

import java.util.ArrayList;

import android.text.format.Time;

public class PaperPojo {
	
	private String paperId;
	private String paperName;
	private int paperType;
	private String teacherId;
	private Time createTime;
	private String courseId;
	private int paperTotalScore;
	private String paperExplain;

	public int getPaperType() {
		return paperType;
	}
	
	public void setPaperType(int paperType) {
		this.paperType = paperType;
	}
	
	public Time getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Time createTime) {
		this.createTime = createTime;
	}
	
	public int getTotalScore() {
		return paperTotalScore;
	}
	
	public void setTotalScore(int totalScore) {
		this.paperTotalScore = totalScore;
	}
	
	public String getPaperExplain() {
		return paperExplain;
	}
	
	public void setPaperExplain(String paperExplain) {
		this.paperExplain = paperExplain;
	}

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

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	                 
}
