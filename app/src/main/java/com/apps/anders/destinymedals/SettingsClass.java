package com.apps.anders.destinymedals;

import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.io.IOException;

public class SettingsClass extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
       addPreferencesFromResource(R.layout.settings);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals("Gamertag")){
            File medalFile = new File(Environment.getExternalStorageDirectory().getPath()+"/"+sharedPreferences.getString(key,"")+"Medals.txt");
            File newMedalFile = new File(Environment.getExternalStorageDirectory().getPath()+"/"+sharedPreferences.getString(key,"")+"New.txt");
            try {
                medalFile.createNewFile();
                newMedalFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
