package org.scauhci.ExamSystem.android.tool;

import android.app.Activity;

public class GetThing {

	private static Activity activity;

	public static Activity getActivity() {
		return activity;
	}
	
	public static void setActivity(Activity activity){
		GetThing.activity = activity;
	}
}
