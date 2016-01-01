package com.apps.anders.destinymedals;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Environment;
import android.preference.PreferenceManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * Created by Anders on 9/13/2015.
 */
public class AllMedalsRunner extends AsyncTask<String, String, String> {
    Context aa;
    public AsyncResponse delegate=null;
    public AllMedalsRunner(){}
    public static ArrayList<String> getMedals() {
        return medals;
    }
    public static ArrayList<String> getValues() {
        return values;
    }
    public static ArrayList<String> getDescriptions() {
        return descriptions;
    }
    public static ArrayList<String> getPrettynames() {
        return prettynames;
    }
    public static ArrayList<String>medals= new ArrayList<String>();
    public static ArrayList<String>values= new ArrayList<String>();
    public static ArrayList<String>descriptions = new ArrayList<String>();
    /*
    TODO
    - UI
    - Alarm
    - Maintain weekly but allow for refresh for current week
     */

    public static ArrayList<String>prettynames = new ArrayList<String>();
    public AllMedalsRunner(Context bb){
        aa=bb;
    }

    @Override
    protected String doInBackground(String... strings){
        try {
            test();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void test()throws IOException,ParseException {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(aa);
        String gamertag = settings.getString("Gamertag","");
        ArrayList<MedalObject> old = new ArrayList<MedalObject>();
        ArrayList<MedalObject> newer = new ArrayList<MedalObject>();
        BufferedReader oldread = null;
        //Read medals to establish a baseline, needs to be moved to work with a weekly baseline
        oldread = new BufferedReader(new FileReader(Environment.getExternalStorageDirectory().getPath()+"/"+gamertag+"Medals.txt"));
        String line = null;
        line = oldread.readLine();
        while (line != null)
        {  old.add(new MedalObject(line.split(":")[0],Integer.parseInt(line.split(":")[1])));
            line = oldread.readLine();
        }
        for(MedalObject a:old){
            System.out.println(a.medal+" "+a.number);
        }
        oldread.close();
        UpdateMedals update = new UpdateMedals();
        Object[] raw_medals = update.getUpdate(gamertag);
        //Write new data to Medals.txt
        FileWriter fileWriter= new FileWriter(Environment.getExternalStorageDirectory().getPath()+"/"+gamertag+"Medals.txt");
        PrintWriter out = new PrintWriter(new BufferedWriter(fileWriter));
            for(int i=0;i<raw_medals.length;i++){
            //Grab medal from JSON
            String medal = ((Map)raw_medals[i]).values().toArray()[0].toString().substring(6);
            try {
                //Value grab
                String value = ((Map) ((Map) raw_medals[i]).values().toArray()[3]).values().toArray()[0].toString();
                //Deprecated asset grabs from network, kept for future updates
                /*Document doc = Jsoup.connect("http://www.destinydb.com/medals/" + medal).get();
                String title = doc.title();
                Element description = doc.select("div.db-description").first();
                descriptions.add(description.text());
                prettynames.add(title.split("- ")[0].trim());*/
                out.println(MedalDictionary.dictionary_realnames.get(medal) + ":" + value);
                System.out.println("Wrote: "+MedalDictionary.dictionary_realnames.get(medal) + ":" + value);
                //Add grabbed medals to a new MedalObject array for later comparison
                newer.add(new MedalObject(MedalDictionary.dictionary_realnames.get(medal), Integer.parseInt(value)));
                AllMedals.getA().incrementProgressBy(1);
            }catch(ArrayIndexOutOfBoundsException ee){}
        }
        out.close();
        //Update old data, maybe
        /*if(old.size()>0) {
            PrintWriter n = new PrintWriter(new FileWriter(Environment.getExternalStorageDirectory().getPath() + "/"+gamertag+"New.txt"));
            PrintWriter cac = new PrintWriter(new FileWriter(Environment.getExternalStorageDirectory().getPath() + "/"+gamertag+"Cached.txt"));
            for (int i = 0; i < newer.size(); i++) {
                System.out.println(newer.get(i).medal);
                System.out.println(newer.get(i).number - old.get(i).number);
                if (newer.get(i).number - old.get(i).number != 0) {
                    n.println(newer.get(i).medal + ": " + (newer.get(i).number - old.get(i).number));
                    cac.println(newer.get(i).medal + ": " + (newer.get(i).number - old.get(i).number));
                }
            }
            n.close();
        }*/
        //Parse medal file and populate arrays
        new ParseMedalData(aa,"master");
    }

    //Create local databases of medal info
    /*public void addall() throws IOException{
        FileWriter descWriter= new FileWriter(Environment.getExternalStorageDirectory().getPath()+"/Descriptions.txt");
        PrintWriter de = new PrintWriter(new BufferedWriter(descWriter));
        FileWriter nameWriter= new FileWriter(Environment.getExternalStorageDirectory().getPath()+"/Names.txt");
        PrintWriter na = new PrintWriter(new BufferedWriter(nameWriter));
        for(int i = 0;i<medals.size();i++){
            na.println(medals.get(i)+":"+prettynames.get(i));
            de.println(medals.get(i)+":"+descriptions.get(i));
        }
        de.close();
        na.close();
    }*/
    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish();
    }
}