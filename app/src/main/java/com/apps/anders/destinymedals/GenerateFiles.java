package com.apps.anders.destinymedals;

import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * Created by Anders on 1/1/2016.
 */
public class GenerateFiles {
    public GenerateFiles(String gamertag){
        File medalFile = new File(Environment.getExternalStorageDirectory().getPath()+"/"+gamertag+"Medals.txt");
        File cachedFile = new File(Environment.getExternalStorageDirectory().getPath()+"/"+gamertag+"Cached.txt");
        try {
            medalFile.createNewFile();
            cachedFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Files already exist");
        }
    }
}
