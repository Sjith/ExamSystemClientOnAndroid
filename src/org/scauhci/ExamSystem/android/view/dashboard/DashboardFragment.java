/*
 * Copyright 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.scauhci.ExamSystem.android.view.dashboard;

import org.scauhci.ExamSystem.android.R;
import org.scauhci.ExamSystem.android.tool.Flag;
import org.scauhci.ExamSystem.android.view.exam.ExamListActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DashboardFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_dashboard, container);

		// Attach event handlers
		root.findViewById(R.id.dashboard_button_course).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View view) {
						Log.e(Flag.DEBUG, "You are click the dashboard_button_course in DashboardFragment.");
						/*startActivity(new Intent(getActivity(),
								CourseActivity.class));*/
					}

				});

		root.findViewById(R.id.dashboard_button_exam).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View view) {
						Log.e(Flag.DEBUG, "You are click the dashboard_button_exam in DashboardFragment.");
						startActivity(new Intent(getActivity(),
								ExamListActivity.class));
						
					}
					
				});
		
		root.findViewById(R.id.dashboard_button_statistics).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View view) {
						Log.e(Flag.DEBUG, "You are click the dashboard_button_statistics in DashboardFragment.");
						/*startActivity(new Intent(getActivity(),
								CourseActivity.class));*/
						
					}
					
				});
		
		root.findViewById(R.id.dashboard_button_remark).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View view) {
						Log.e(Flag.DEBUG, "You are click the dashboard_button_remark in DashboardFragment.");
						/*startActivity(new Intent(getActivity(),
								CourseActivity.class));*/
						
					}
					
				});
		
		return root;
	}
}
