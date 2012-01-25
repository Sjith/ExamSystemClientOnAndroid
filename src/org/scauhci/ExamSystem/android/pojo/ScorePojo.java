package org.scauhci.ExamSystem.android.pojo;

public class ScorePojo {

	private String scoreId;
	private String examId;
	private String studentId;
	private float paperScore = 0;

	public String getScoreId() {
		return scoreId;
	}

	public void setScoreId(String scoreId) {
		this.scoreId = scoreId;
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

	public float getPaperScore() {
		return paperScore;
	}

	public void setScore(float score) {
		this.paperScore = score;
	}

}
