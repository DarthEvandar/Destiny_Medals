package com.apps.anders.destinymedals;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anders on 12/31/2015.
 */
// Converts condensed medal names to other information
public class MedalDictionary {
    public static Map<String, String> dictionary_names = new HashMap<String, String>();
    public static Map<String, String> dictionary_descriptions = new HashMap<String, String>();
    public static Map<String, String> dictionary_realnames = new HashMap<String, String>();
    public MedalDictionary(){
        //Condensed names to real names
        try {
            BufferedReader read = new BufferedReader(new FileReader(Environment.getExternalStorageDirectory().getPath() + "/Names.txt"));
            String line = read.readLine();
            while (line != null) {
                dictionary_names.put(line.split(":")[0],line.split(":")[1]);
                line = read.readLine();
            }
        }catch(IOException e){}
        //Condensed names to descriptions
        try {
            BufferedReader read = new BufferedReader(new FileReader(Environment.getExternalStorageDirectory().getPath() + "/Descriptions.txt"));
            String line = read.readLine();
            while (line != null) {
                dictionary_descriptions.put(line.split(":")[0],line.split(":")[1]);
                line = read.readLine();
            }
        }catch(IOException e){}
        //Strange medal names from API into real medal names
        try {
            BufferedReader read = new BufferedReader(new FileReader(Environment.getExternalStorageDirectory().getPath() + "/RealNames.txt"));
            String line = read.readLine();
            while (line != null) {
                dictionary_realnames.put(line.split(":")[0],line.split(":")[1]);
                line = read.readLine();
            }
        }catch(IOException e1){}
    }
}
