package com.example.hoanganhken.app_htht;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AddStudent extends Activity implements OnItemSelectedListener {

	Spinner spnListClass;
	EditText txtStudentID, txtStudentName;
	ListView ltvListStudent;

	SQLiteDatabase database;

	ArrayList<String> list = null;
	ArrayAdapter<String> adapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_student);
		init();
		loadDataClass();
	}

	public void init() {
		spnListClass = (Spinner) findViewById(R.id.spnListClass);
		txtStudentID = (EditText) findViewById(R.id.txtStudentID);
		txtStudentName = (EditText) findViewById(R.id.txtStudentName);
		ltvListStudent = (ListView) findViewById(R.id.ltvListStudent);
	}

	// Load data class
	public void loadDataClass() {
		// Array
		database = openOrCreateDatabase("qlsv.db", MODE_PRIVATE, null);
		list = new ArrayList<String>();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, list);
		adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		spnListClass.setAdapter(adapter);
		spnListClass.setOnItemSelectedListener(this);
		// Load
		Cursor c = database.query("tblClass", null, null, null, null, null,
				null);
		c.moveToFirst();
		while (c.isAfterLast() == false) {
			list.add(c.getString(2));
			c.moveToNext();
		}
		c.close();
		adapter.notifyDataSetChanged();
	}

	// Add Student
	public void addStudent(View v) {
		database = openOrCreateDatabase("qlsv.db", MODE_PRIVATE, null);
		ContentValues values = new ContentValues();
		values.put("idStudent", txtStudentID.getText().toString());
		values.put("nameStudent", txtStudentName.getText().toString());
		values.put("nameClass", String.valueOf(spnListClass.getSelectedItem()));

		// add
		if (database.insert("tblStudent", null, values) != -1) {
			Toast.makeText(this, "Add Student Success", Toast.LENGTH_LONG)
					.show();
			loadListStudent();
			txtStudentID.setText("");
			txtStudentName.setText("");
			txtStudentID.requestFocus();
		}
	}

	public void loadListStudent() {
		// Array
		list = new ArrayList<String>();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, list);
		ltvListStudent.setAdapter(adapter);

		// load
		list.clear();
		Cursor c = database.query("tblStudent", null, null, null, null, null,
				null);
		c.moveToFirst();
		while (c.isAfterLast() == false) {
			list.add(" " + c.getString(0) + "   -   " + c.getString(1)
					+ "   -   " + c.getString(2) + "   -   "
					+ c.getString(3));
			c.moveToNext();
		}
		c.close();
		adapter.notifyDataSetChanged();
	}

	// Back
	public void back(View v) {
		finish();
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
}
