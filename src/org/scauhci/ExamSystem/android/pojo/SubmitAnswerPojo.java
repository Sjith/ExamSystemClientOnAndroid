package org.scauhci.ExamSystem.android.pojo;

public class SubmitAnswerPojo {

	private String submitAnswerId;
	private String questionId;
	private String examId;
	private String studentId;
	private float questionStdScore;
	private float questionScore;

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

	public float getStdScore() {
		return questionStdScore;
	}

	public void setStdScore(float stdScore) {
		this.questionStdScore = stdScore;
	}

	public float getScore() {
		return questionScore;
	}

	public void setScore(float score) {
		this.questionScore = score;
	}

}
