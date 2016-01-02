package com.apps.anders.destinymedals;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Environment;
import android.preference.PreferenceManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Anders on 11/29/2015.
 */
public class CachedRunner extends AsyncTask<String,String,String> {
    public AsyncResponse delegate=null;
    public static ArrayList<String> cMedals = new ArrayList<String>();
    public static ArrayList<String> cValues = new ArrayList<String>();
    public static ArrayList<String> getMedals() {
        return cMedals;
    }
    public static ArrayList<String> getValues() {
        return cValues;
    }
    Context cRunContext;
    public CachedRunner(){}
    public CachedRunner(Context runContext){
        cRunContext = runContext;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            cMedals.clear();
            cValues.clear();
            new ParseMedalData(cRunContext,"cached");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish();
    }
}
