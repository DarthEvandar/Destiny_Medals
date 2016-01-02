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
public class WeeklyRunner extends AsyncTask<String,String,String> {
    public AsyncResponse delegate=null;
    public static ArrayList<String> wMedals = new ArrayList<String>();
    public static ArrayList<String> wValues = new ArrayList<String>();
    public static ArrayList<String> getMedals() {
        return wMedals;
    }
    public static ArrayList<String> getValues() {
        return wValues;
    }
    Context wRunContext;
    public WeeklyRunner(){}
    public WeeklyRunner(Context runContext){
        wRunContext = runContext;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            new ParseMedalData(wRunContext,"weekly");
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
