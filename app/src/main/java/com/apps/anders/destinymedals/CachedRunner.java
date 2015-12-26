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
            load();
        }catch(IOException e){}
        return null;
    }
    public void load() throws IOException {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(cRunContext);
        String gamertag = settings.getString("Gamertag","");
        BufferedReader read = new BufferedReader(new FileReader(Environment.getExternalStorageDirectory().getPath()+"/"+gamertag+"Cached.txt"));
        String line = read.readLine();
        while(line != null) {
            String tempo;
            tempo = line.split(":")[0];
            tempo = tempo.replaceAll(" ","_");
            tempo = tempo.replaceAll("!","");
            tempo = tempo.toLowerCase();
            // tempo = tempo.replaceAll(".", "");
            tempo = tempo.replaceAll("-", "_");
            tempo = tempo.replaceAll("\'","");
            System.out.println(tempo);
            cValues.add(line.split(":")[1]);
            //System.out.println(line.split(":")[0].toLowerCase()/*.replaceAll(" ","_").replaceAll("!","").replaceAll(".","").replaceAll("-","_").replaceAll("'","")*/);
            cMedals.add(tempo);
            line  = read.readLine();
        }
        read.close();
    }
    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish();
    }
}
