package com.apps.anders.destinymedals;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * Created by Anders on 9/13/2015.
 */
public class AsyncRunner extends AsyncTask<String, String, String> {
    Context aa;
    public AsyncResponse delegate=null;
    public AsyncRunner(){}
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
    - Icon sorting
     */

    public static ArrayList<String>prettynames = new ArrayList<String>();
    public AsyncRunner (Context bb){
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
        ArrayList<Pair> old = new ArrayList<Pair>();
        ArrayList<Pair> newer = new ArrayList<Pair>();
        BufferedReader oldread = null;
       // aa.getResources().openRawResource(R.raw.medals)
        oldread = new BufferedReader(new FileReader(Environment.getExternalStorageDirectory().getPath()+"/"+gamertag+"Medals.txt"));
        String line = null;
        line = oldread.readLine();
        while (line != null)
        {  old.add(new Pair(line.split(":")[0],Integer.parseInt(line.split(":")[1])));
            line = oldread.readLine();
        }
        for(Pair a:old){
            System.out.println(a.medal+" "+a.number);
        }
        oldread.close();
        Gson gson = new Gson();
        JsonParser jp = new JsonParser();
        String idURL = "http://www.bungie.net/Platform/Destiny/SearchDestinyPlayer/1/"+gamertag;
        URL url1;
        url1 = new URL(idURL);
        HttpURLConnection request1 = null;
        request1 = (HttpURLConnection) url1.openConnection();
        request1.setRequestProperty("X-API-Key", "3e45c013467c4de69b4225328ada2ade");
        request1.connect();

        JsonElement root1 = null;
        root1 = jp.parse(new InputStreamReader((InputStream) request1.getContent()));
        String json1 = gson.toJson(root1);
        System.out.println(json1);
        JSONParser parser1 = new JSONParser();
        String obj1 = (((Map)((List)((Map)(parser1.parse(json1))).values().toArray()[1]).get(0))).values().toArray()[0].toString().trim();
        System.out.println(obj1);
        //3e45c013467c4de69b4225328ada2ade
        String sURL = "http://www.bungie.net/Platform/Destiny/Stats/1/"+obj1+"/0/?groups=Medals&modes=allPvP";
        URL url;
        url = new URL(sURL);
        HttpURLConnection request = null;
        request = (HttpURLConnection) url.openConnection();
        request.setRequestProperty("X-API-Key", "3e45c013467c4de69b4225328ada2ade");
        request.connect();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        String json = gson.toJson(root);
        System.out.println(json);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(json);
        System.out.println(obj);
        Map array = (Map)obj;
        Collection a = array.values();
        System.out.println(a);
        Object[] b = a.toArray();
        Collection c = ((Map)b[1]).values();
        Object[] d = c.toArray();
        System.out.println(c);
        Collection e = ((Map)d[0]).values();
        Object[] f = e.toArray();
        Collection g = ((Map)f[0]).values();
        Object[] h = g.toArray();
        //PrintWriter r = new PrintWriter(new FileWriter("app/src/Medals.txt"));
        /*System.out.println("Medal: "+((Map)h[0]).values().toArray()[0].toString().substring(6)+"\nValue: "+
        ((Map)((Map)h[0]).values().toArray()[2]).values().toArray()[0]);*/
        Map<String, String> realnames = new HashMap<String, String>();
        try {
            BufferedReader read = new BufferedReader(new FileReader(Environment.getExternalStorageDirectory().getPath() + "/RealNames.txt"));
            String linel = read.readLine();
            while (linel != null) {
                realnames.put(linel.split(":")[0],linel.split(":")[1]);
                linel = read.readLine();
            }
        }catch(IOException e1){}

        FileWriter fileWriter= new FileWriter(Environment.getExternalStorageDirectory().getPath()+"/"+gamertag+"Medals.txt");
        PrintWriter out = new PrintWriter(new BufferedWriter(fileWriter));
            for(int i=0;i<h.length;i++){
            String medal = ((Map)h[i]).values().toArray()[0].toString().substring(6);
            //System.out.println(((Map)h[i]).values());
            try {
                String value = ((Map) ((Map) h[i]).values().toArray()[3]).values().toArray()[0].toString();
                /*Document doc = Jsoup.connect("http://www.destinydb.com/medals/" + medal).get();
                String title = doc.title();
                Element description = doc.select("div.db-description").first();
                descriptions.add(description.text());
                prettynames.add(title.split("- ")[0].trim());*/
                out.println(realnames.get(medal) + ":" + value);
                System.out.println("Wrote: "+realnames.get(medal) + ":" + value);
                newer.add(new Pair(realnames.get(medal), Integer.parseInt(value)));
                Receive.getA().incrementProgressBy(1);
            }catch(ArrayIndexOutOfBoundsException ee){}
        }
        out.close();
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
        gett();
        //addall();
    }
    public void gett()throws IOException{
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(aa);
        String gamertag = settings.getString("Gamertag","");
        BufferedReader read = new BufferedReader(new FileReader(Environment.getExternalStorageDirectory().getPath()+"/"+gamertag+"Medals.txt"));
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
            values.add(line.split(":")[1]);
            //System.out.println(line.split(":")[0].toLowerCase()/*.replaceAll(" ","_").replaceAll("!","").replaceAll(".","").replaceAll("-","_").replaceAll("'","")*/);
            medals.add(tempo);
            line  = read.readLine();
        }
        read.close();
    }
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