package org.scauhci.ExamSystem.android.pojo;

public class SubmitAnswerPojo {

	private String submitAnswerId;
	private String questionId;
	private String examId;
	private String studentId;
	private float questionStdScore = 0;
	private float questionScore;
	private String submitAnswerContent;

	public String getSubmitAnswerContent() {
		return submitAnswerContent;
	}

	public void setSubmitAnswerContent(String submitAnswerContent) {
		this.submitAnswerContent = submitAnswerContent;
	}

	public String getSubmitAnswerId() {
		return submitAnswerId;
	}

	public void setSubmitAnswerId(String submitAnswerId) {
		this.submitAnswerId = submitAnswerId;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public float getQuestionStdScore() {
		return questionStdScore;
	}

	public void setQuestionStdScore(float questionStdScore) {
		this.questionStdScore = questionStdScore;
	}

	public float getQuestionScore() {
		return questionScore;
	}

	public void setQuestionScore(float questionScore) {
		this.questionScore = questionScore;
	}

}
