package com.apps.anders.destinymedals;


import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";
    public final static String E2 = "message2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        String gamertag = settings.getString("Gamertag","");
        new MedalDictionary();
        if(gamertag.equals("")){
            setContentView(R.layout.activity_main);
        }else{
            setContentView(R.layout.existing);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.S

        Intent i = new Intent(this, SettingsClass.class);
        startActivity(i);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        String gamertag = settings.getString("Gamertag","");
        System.out.println(gamertag);
        return super.onOptionsItemSelected(item);
    }

    public void send(View view){
        Intent intent = new Intent(this, AllMedals.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        new GenerateFiles(message);
        intent.putExtra(EXTRA_MESSAGE, message);
        intent.putExtra(E2,"new");
        startActivity(intent);
    }
    public void sendOld(View view){
        Intent intent = new Intent(this, AllMedals.class);
        intent.putExtra(EXTRA_MESSAGE, "");
        intent.putExtra(E2,"");
        startActivity(intent);
    }
    public void sendCached(View view){
        Intent intent = new Intent(this, Cached.class);
        startActivity(intent);
    }
    public void sendWeekly(View view){
        Intent intent = new Intent(this, WeeklyMedals.class);
        startActivity(intent);
    }
    public void sendGenerate(View view){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        String gamertag = settings.getString("Gamertag", "");
        new GenerateFiles(gamertag);
    }
}
