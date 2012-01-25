package org.scauhci.ExamSystem.android.analyzer;

import java.io.InputStream;

import org.scauhci.ExamSystem.android.tool.ExecuteResultFlag;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.net.Uri;
import android.util.Xml;

public class PaperXmlAnalyzer {
	public PaperXmlAnalyzer() {
	}
	
	public int analysisXml(Uri xmlUri) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}
	
	public int analysisXml(InputStream xmlInputStream) throws Exception {
		int executeResult = ExecuteResultFlag.ERROR;
		
		XmlPullParser xmlPullParser = Xml.newPullParser();
		xmlPullParser.setInput(xmlInputStream, "utf-8");
		
		return executeResult;
	}
}
