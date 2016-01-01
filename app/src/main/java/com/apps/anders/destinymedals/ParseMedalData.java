package com.apps.anders.destinymedals;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Anders on 12/28/2015.
 */
public class ParseMedalData {
    //id = class invoker / usage category
    public ParseMedalData(Context c,String id) throws IOException {
        parse(c,id);
    }
    public void parse(Context c, String id)throws IOException{
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
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(c);
        String gamertag = settings.getString("Gamertag","");
        //iterate over all colors
        for(int i=0;i<5;i++) {
            BufferedReader read = null;
            //Create reader based on method call id
            switch(id) {
                case "master":
                    read = new BufferedReader(new FileReader(Environment.getExternalStorageDirectory().getPath() + "/" + gamertag + "Medals.txt"));
                    break;
                case "cached":
                    read = new BufferedReader(new FileReader(Environment.getExternalStorageDirectory().getPath() + "/" + gamertag + "Cached.txt"));
                    break;
                case "weekly":

                    break;
            }
            String line = read.readLine();
            while (line != null) {
                //if medal is in current color check, format it and enter it
                if (colors.get(i).contains(line.split(":")[0])) {
                    String tempo;
                    tempo = line.split(":")[0];
                    tempo = tempo.replaceAll(" ", "_");
                    tempo = tempo.replaceAll("!", "");
                    tempo = tempo.toLowerCase();
                    tempo = tempo.replaceAll("-", "_");
                    tempo = tempo.replaceAll("\'", "");
                    System.out.println(tempo);
                    //enter into arrays based on id
                    switch(id){
                        case "cached":
                            CachedRunner.cValues.add(line.split(":")[1]);
                            CachedRunner.cMedals.add(tempo);
                            break;
                        case "master":
                            AllMedalsRunner.values.add(line.split(":")[1]);
                            AllMedalsRunner.medals.add(tempo);
                            break;
                        case "weekly":

                            break;
                    }

                }
                line = read.readLine();
            }
            read.close();
        }
    }
}
