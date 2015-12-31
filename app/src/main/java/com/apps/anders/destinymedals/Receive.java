package com.apps.anders.destinymedals;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class Receive extends AppCompatActivity implements AsyncResponse{
    Toast myToast;
    public static ProgressDialog getA() {
        return a;
    }
    GridView gridview;
    static ProgressDialog a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setTitle("All Medals");
        myToast = Toast.makeText(this, "", Toast.LENGTH_LONG);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String newness = intent.getStringExtra(MainActivity.E2);

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();
        if(!message.equals("")) {
            editor.putString("Gamertag", message);
            editor.commit();
        }
        File medalFile = new File(Environment.getExternalStorageDirectory().getPath()+"/"+settings.getString("Gamertag","")+"Medals.txt");
        File newMedalFile = new File(Environment.getExternalStorageDirectory().getPath()+"/"+settings.getString("Gamertag","")+"New.txt");
        File cachedMedalFile = new File(Environment.getExternalStorageDirectory().getPath()+"/"+settings.getString("Gamertag","")+"Cached.txt");
        try {
            medalFile.createNewFile();
            newMedalFile.createNewFile();
            cachedMedalFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AsyncRunner runner = new AsyncRunner(this);
        runner.delegate = this;
        runner.execute();
        //try {
        a = new ProgressDialog(this);
        a.setTitle("Loading");
        a.setMessage("Parsing User Data");
        a.setProgressStyle(a.STYLE_HORIZONTAL);
        a.setProgress(0);
        a.setMax(102);
        a.show();
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

    @Override
    public void processFinish() {
        a.dismiss();
        gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
        // final AsyncRunner async = new AsyncRunner();
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                System.out.println("Press");
                try {
                    myToast.setText(MedalDictionary.dictionary_names.get(AsyncRunner.getMedals().get(position)) + "\n" + MedalDictionary.dictionary_descriptions.get(AsyncRunner.getMedals().get(position)) + "\nReceived This Week: " + AsyncRunner.getValues().get(position));
                }catch(java.lang.IndexOutOfBoundsException exce){
                    myToast.setText("Nothing New!");
                }
                    TextView vv = (TextView) myToast.getView().findViewById(android.R.id.message);
                if( vv != null) vv.setGravity(Gravity.CENTER);
                myToast.show();
                //showImage(position);
            }
        });
    }

    //Future popup code maybe
    /*public void showImage(int pos) {
        Dialog builder = new Dialog(this);
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.WHITE));
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                //nothing;
            }
        });
        TextView textView = new TextView(this);
        textView.setTextSize(22);
        textView.setTextColor(Color.BLACK);
        textView.setText(AsyncRunner.getPrettynames().get(pos) + "\n" + AsyncRunner.getDescriptions().get(pos) + "\nReceived This Week: " + AsyncRunner.getValues().get(pos));
        textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.we_ran_out_of_medals, 0, 0);
        ImageView imageView = new ImageView(this);
        //imageView.setImageResource((TextView)gridview.getItemAtPosition(pos).get);
        builder.addContentView(textView, new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        builder.show();
    }*/
}
