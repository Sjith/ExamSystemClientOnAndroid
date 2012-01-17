package org.scauhci.ExamSystem.android.dao;

import org.scauhci.ExamSystem.android.tool.LogLabelFlag;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DaoHelper extends SQLiteOpenHelper {

	public DaoHelper(Context context, String databasePath, CursorFactory cursorFactory,
			int databaseVersion) {
		super(context, databasePath, cursorFactory, databaseVersion);
		Log.e(LogLabelFlag.DEBUG, "The database is create sucessfully.");
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
}
