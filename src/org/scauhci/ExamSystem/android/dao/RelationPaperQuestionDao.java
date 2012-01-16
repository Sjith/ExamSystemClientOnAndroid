package org.scauhci.ExamSystem.android.dao;

import java.util.ArrayList;

import org.scauhci.ExamSystem.android.pojo.ExamPojo;
import org.scauhci.ExamSystem.android.pojo.PaperPojo;
import org.scauhci.ExamSystem.android.pojo.RelationPaperQuestionPojo;
import org.scauhci.ExamSystem.android.tool.ExecuteResultFlag;

public class RelationPaperQuestionDao {

	public int add(RelationPaperQuestionPojo relationPaperQuestionPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}

	public int delete(RelationPaperQuestionPojo relationPaperQuestionPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}

	public int change(RelationPaperQuestionPojo relationPaperQuestionPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}
	
	public RelationPaperQuestionPojo getRelationPaperQuestionPojoByRelationPaperQuestionId(String relationPaperQuestionId) {
		RelationPaperQuestionPojo relationPaperQuestionPojo = null;
		return relationPaperQuestionPojo;
	}
	
	public ArrayList<RelationPaperQuestionPojo> getRelationPaperQuestionPojosByPaperPojo(PaperPojo paperPojo) {
		ArrayList<RelationPaperQuestionPojo> relationPaperQuestionPojos = null;
		return relationPaperQuestionPojos;
	}
	
	public ArrayList<RelationPaperQuestionPojo> getRelationPaperQuestionPojosByExamPojo(ExamPojo examPojo) {
		ArrayList<RelationPaperQuestionPojo> relationPaperQuestionPojos = null;
		return relationPaperQuestionPojos;
	}
}
