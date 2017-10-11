package com.example.hoanganhken.app_htht;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    GridView gridView;
    ArrayList<Item> gridArray = new ArrayList<Item>();
    CustomGridViewAdapter customGridViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // set Grid View Id
        Bitmap studentIcon = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.student);
        Bitmap mapsIcon = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.maps);
        Bitmap newsIcon = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.news);
        Bitmap facebookIcon = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.social);

        // Add to ArrayList

        gridArray.add(new Item(studentIcon, "Student"));
        gridArray.add(new Item(mapsIcon, "Maps"));
        gridArray.add(new Item(newsIcon, "News"));
        gridArray.add(new Item(facebookIcon, "Social"));

        // add to adapter
        gridView = (GridView) findViewById(R.id.gridView);
        customGridViewAdapter = new CustomGridViewAdapter(this,
                R.layout.row_grid, gridArray);
        gridView.setAdapter(customGridViewAdapter);
        gridView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // TODO Auto-generated method stub
        switch (position) {
            case 0:
                Intent intent = new Intent(MainActivity.this, Student.class);
                startActivity(intent);
                break;
            case 1:
                Intent intent2 = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent2);
                break;
            case 2:
                Intent intent3 = new Intent(MainActivity.this, New.class);
                startActivity(intent3);
                break;
            case 3:
                Intent intent4 = new Intent(MainActivity.this, Social.class);
                startActivity(intent4);
                break;

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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


    public static void sharePhoto(Bitmap bitmap, String capTion) {
    }

    public static void shareLinkFB(String title, String linkShare, String imageThumnal) {
    }
}
