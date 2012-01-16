package org.scauhci.ExamSystem.android.pojo;

import java.util.ArrayList;

public class QuestionPojo {

	private String questionId;
	private String questionContent;
	private int questionType;
	private String courseId;
	private String questionStdAnswer;

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public int getQuestionType() {
		return questionType;
	}

	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getQuestionStdAnswer() {
		return questionStdAnswer;
	}

	public void setQuestionStdAnswer(String questionStdAnswer) {
		this.questionStdAnswer = questionStdAnswer;
	}

}
