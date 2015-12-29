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


        //Connection stuff
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
        //API Key
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
        //Ugly JSON formattting
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

        //Read the names from file and put them into a dictionary
        Map<String, String> realnames = new HashMap<String, String>();
        try {
            BufferedReader read = new BufferedReader(new FileReader(Environment.getExternalStorageDirectory().getPath() + "/RealNames.txt"));
            String linel = read.readLine();
            while (linel != null) {
                realnames.put(linel.split(":")[0],linel.split(":")[1]);
                linel = read.readLine();
            }
        }catch(IOException e1){}

        //Write new data to Medals.txt,
        FileWriter fileWriter= new FileWriter(Environment.getExternalStorageDirectory().getPath()+"/"+gamertag+"Medals.txt");
        PrintWriter out = new PrintWriter(new BufferedWriter(fileWriter));
            for(int i=0;i<h.length;i++){
            //Grab medal from JSON
            String medal = ((Map)h[i]).values().toArray()[0].toString().substring(6);
            try {
                String value = ((Map) ((Map) h[i]).values().toArray()[3]).values().toArray()[0].toString();
                //Deprecated asset grabs from network, kept for future updates
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
        //Update old data
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

    //Format raw data
    public void gett()throws IOException{
        //Red
        ArrayList<String> color_red = new ArrayList<String>();
        color_red.add("Automatic");
        color_red.add("Avenger");
        color_red.add("B-Line");
        color_red.add("Back in Action");
        color_red.add("Buckshot Bruiser");
        color_red.add("Bulldozer");
        color_red.add("Bullseye");
        color_red.add("Dead Man\'s Hand");
        color_red.add("Domination");
        color_red.add("Double Down");
        color_red.add("Enemy of My Enemy");
        color_red.add("Finger on the Pulse");
        color_red.add("First Blood");
        color_red.add("From the Brink");
        color_red.add("Get it Off!");
        color_red.add("Hazard Pay");
        color_red.add("I See You");
        color_red.add("Marksman");
        color_red.add("Master Blaster");
        color_red.add("Merciless");
        color_red.add("Nail in the Coffin");
        color_red.add("Narrow Escape");
        color_red.add("Never Say Die");
        color_red.add("Overwatch");
        color_red.add("Payback");
        color_red.add("Postmortem");
        color_red.add("Scout\'s Honor");
        color_red.add("Sidekick");
        color_red.add("Skewered");
        color_red.add("Stick Around");
        color_red.add("Trial By Fire");
        color_red.add("Unsung Hero");
        color_red.add("Victory");
        //Orange
        ArrayList<String> color_orange = new ArrayList<String>();
        color_orange.add("Ace");
        color_orange.add("Alone at the Top");
        color_orange.add("Angel of Light");
        color_orange.add("Blast Shield");
        color_orange.add("Breaker");
        color_orange.add("Clean Sweep");
        color_orange.add("Comeback");
        color_orange.add("Cry Havoc");
        color_orange.add("Decisive Victory");
        color_orange.add("Denied");
        color_orange.add("End of the Line");
        color_orange.add("Gutted");
        color_orange.add("Hammer and Tongs");
        color_orange.add("Im-probe-able");
        color_orange.add("Immovable Object");
        color_orange.add("Lockdown");
        color_orange.add("Never Speak of This Again");
        color_orange.add("Reign of Terror");
        color_orange.add("Relentless");
        color_orange.add("Salvage Crew");
        color_orange.add("Scorched Earth");
        color_orange.add("Shutout");
        color_orange.add("Slayer");
        color_orange.add("Space Magic");
        color_orange.add("Storm Bringer");
        color_orange.add("The Cycle");
        color_orange.add("Unstoppable Force");
        color_orange.add("Uprising");
        color_orange.add("Way of the Gun");
        color_orange.add("Wild Hunt");
        color_orange.add("Won\'t Be Beat");
        color_orange.add("Wrecking Ball");
        //Teal
        ArrayList<String> color_teal = new ArrayList<String>();
        color_teal.add("...And Stay Down!");
        color_teal.add("At Any Cost");
        color_teal.add("Chariot of Fire");
        color_teal.add("Clear a Path");
        color_teal.add("Defender");
        color_teal.add("Disruption");
        color_teal.add("Enforcer");
        color_teal.add("Fallen Angel");
        color_teal.add("Gunner");
        color_teal.add("Hat Trick");
        color_teal.add("Heartbraker");
        color_teal.add("Lone Wolf");
        color_teal.add("Machine Lord");
        color_teal.add("Medic!");
        color_teal.add("Objectively Correct");
        color_teal.add("On The Bright Side...");
        color_teal.add("Relic Hunter");
        color_teal.add("Saboteur");
        color_teal.add("Splash Damage");
        color_teal.add("Sword at a Gun Fight");
        color_teal.add("The Best...Around");
        color_teal.add("The Heist");
        color_teal.add("Triple Down");
        color_teal.add("Zero Hour");
        //Blue
        ArrayList<String> color_blue = new ArrayList<String>();
        color_blue.add("Annihilation");
        color_blue.add("Armed and Dangerous");
        color_blue.add("Clutch");
        color_blue.add("Perfect Runner");
        color_blue.add("Reaper");
        color_blue.add("Strength of the Wolf");
        //Gold
        ArrayList<String> color_gold = new ArrayList<String>();
        color_gold.add("Bulletproof");
        color_gold.add("Mark of the Unbroken");
        color_gold.add("No Mercy");
        color_gold.add("Phantom");
        color_gold.add("Seventh Column");
        color_gold.add("Sum of All Tears");
        color_gold.add("We Ran out of Medals");
        ArrayList<ArrayList> colors = new ArrayList<ArrayList>();
        colors.add(color_red);
        colors.add(color_orange);
        colors.add(color_teal);
        colors.add(color_blue);
        colors.add(color_gold);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(aa);
        String gamertag = settings.getString("Gamertag","");
        for(int i=0;i<5;i++) {
            BufferedReader read = new BufferedReader(new FileReader(Environment.getExternalStorageDirectory().getPath() + "/" + gamertag + "Medals.txt"));
            String line = read.readLine();
            while (line != null) {
                if (colors.get(i).contains(line)) {
                    String tempo;
                    tempo = line.split(":")[0];
                    tempo = tempo.replaceAll(" ", "_");
                    tempo = tempo.replaceAll("!", "");
                    tempo = tempo.toLowerCase();
                    // tempo = tempo.replaceAll(".", "");
                    tempo = tempo.replaceAll("-", "_");
                    tempo = tempo.replaceAll("\'", "");
                    System.out.println(tempo);
                    values.add(line.split(":")[1]);
                    //System.out.println(line.split(":")[0].toLowerCase()/*.replaceAll(" ","_").replaceAll("!","").replaceAll(".","").replaceAll("-","_").replaceAll("'","")*/);
                    medals.add(tempo);
                }
                line = read.readLine();
            }
            read.close();
        }
    }
    //create local databases of medal info
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