package com.example.hoanganhken.app_htht;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListClass extends Activity {
	ListView ltvListClass;
	SQLiteDatabase database;

	ArrayList<String> list = null;
	ArrayAdapter<String> adapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_class);
		ltvListClass = (ListView) findViewById(R.id.ltvListClass);
		array();
		loadListClass();
	}

	// Mang
	public void array() {
		list = new ArrayList<String>();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, list);
		ltvListClass.setAdapter(adapter);
		ltvListClass.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(ListClass.this, list.get(arg2), Toast.LENGTH_SHORT).show();
				final String data = list.get(arg2);
				final int pos = arg2;
				Builder b = new Builder(ListClass.this);
				b.setTitle("Xóa");
				b.setMessage("Ban co muon xoa khong " + data.toString() + " Khong ?");
				b.setPositiveButton("Co",
						new OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub

								database.delete("tblClass", "stt=?",
										new String[] { data.toString() });

								Toast.makeText(ListClass.this, "Remove Ok",
										Toast.LENGTH_LONG).show();
								list.remove(pos);
								adapter.notifyDataSetChanged();

							}
						});
				b.setNegativeButton("Không",
						new OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								dialog.cancel();
							}
						});
				b.show();

				// */
				return false;
			}

		});
	}

	// Load Data
	public void loadListClass() {
		database = openOrCreateDatabase("qlsv.db", MODE_PRIVATE, null);
		Cursor c = database.query("tblClass", null, null, null, null, null,
				null);
		c.moveToFirst();
		while (c.isAfterLast() == false) {
			list.add("  " + c.getString(0) + "        -        "
					+ c.getString(1) + "        -        " + c.getString(2));
			c.moveToNext();
		}
		c.close();
		adapter.notifyDataSetChanged();
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
