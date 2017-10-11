package com.example.hoanganhken.app_htht;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateClass extends Activity {

	EditText txtClassID, txtClassName;
	Button btnClear, btnCreateClass;

	SQLiteDatabase database;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_class);
		init();
	}
	
	public void init() {
		txtClassID = (EditText) findViewById(R.id.txtClassID);
		txtClassName = (EditText) findViewById(R.id.txtClassName);
		btnClear = (Button) findViewById(R.id.btnClear);
		btnCreateClass = (Button) findViewById(R.id.btnCreateClass);
	}

	public void createClass(View v) {
		database = openOrCreateDatabase("qlsv.db", MODE_PRIVATE, null);

		ContentValues values = new ContentValues();
		values.put("idClass", txtClassID.getText().toString());
		values.put("nameClass", txtClassName.getText().toString());
		String msg = "";
		if (database.insert("tblClass", null, values) != -1) {
			msg = "Create Class Success";
		} else {
			msg = "Create Class Faile";
		}
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
		finish();

	}

	public void clear(View v) {
		txtClassID.setText("");
		txtClassName.setText("");
		txtClassID.requestFocus();
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
