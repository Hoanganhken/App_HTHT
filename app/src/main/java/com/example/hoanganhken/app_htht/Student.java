package com.example.hoanganhken.app_htht;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Student extends Activity {
	SQLiteDatabase database;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student);
		createDatabase();
	}

	public void createDatabase() {
		database = openOrCreateDatabase("qlsv.db", MODE_PRIVATE, null);
		Toast.makeText(this, "Create Database Success", Toast.LENGTH_LONG)
				.show();
		createTable();
	}

	public void createTable() {
		String sqlClass = "create table if not exists tblClass(stt integer primary key, idClass text, nameClass text)";
		String sqlStudent = "create table if not exists tblStudent(stt integer primary key, idStudent text, nameStudent text, nameClass text)";
		database.execSQL(sqlClass);
		database.execSQL(sqlStudent);
		Toast.makeText(this, "Create Table Success", Toast.LENGTH_LONG).show();
	}

	public void reasetDatabase(View v) {
		if (deleteDatabase("qlsv.db")) {
			Toast.makeText(this, "Delete Database Success", Toast.LENGTH_LONG)
					.show();
			createDatabase();
		} else {
			Toast.makeText(this, "Delete Database Failse", Toast.LENGTH_LONG)
					.show();
		}
	}

	// Lien ket activity
	public void themLop(View v) {
		Intent intent = new Intent(this, CreateClass.class);
		startActivity(intent);
	}

	public void xemDanhSachLop(View v) {
		Intent intent = new Intent(this, ListClass.class);
		startActivity(intent);
	}

	public void quanLySinhVien(View v) {
		Intent intent = new Intent(this, AddStudent.class);
		startActivity(intent);
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
}
