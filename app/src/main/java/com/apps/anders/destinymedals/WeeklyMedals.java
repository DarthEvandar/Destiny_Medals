package com.apps.anders.destinymedals;

/**
 * Created by Anders on 10/11/2015.
 */


import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;


public class WeeklyMedals extends AppCompatActivity implements AsyncResponse{
    Toast myToast;
    GridView gridview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Intent intent = getIntent();
        myToast = Toast.makeText(this, "", Toast.LENGTH_LONG);
        WeeklyRunner weekly = new WeeklyRunner(this);
        weekly.delegate = this;
        weekly.execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void Show() {

    }

    @Override
    public void processFinish() {

        gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new WeeklyAdapter(this,WeeklyRunner.getMedals().size()));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                System.out.println("Press");
                try {
                    myToast.setText(MedalDictionary.dictionary_names.get(WeeklyRunner.getMedals().get(position)) + "\n" + MedalDictionary.dictionary_descriptions.get(WeeklyRunner.getMedals().get(position)) + "\nReceived This Week: " + WeeklyRunner.getValues().get(position));
                } catch (java.lang.IndexOutOfBoundsException exce) {
                    myToast.setText("Nothing New!");
                }
                TextView vv = (TextView) myToast.getView().findViewById(android.R.id.message);
                if (vv != null) vv.setGravity(Gravity.CENTER);
                myToast.show();
                //showImage(position);
            }
        });
    }
}