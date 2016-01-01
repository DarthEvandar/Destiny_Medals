package com.apps.anders.destinymedals;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Anders on 12/31/2015.
 */
public class UpdateMedals {

    public UpdateMedals() {

    }
    public Object[]getUpdate(String gamertag) throws  IOException, ParseException{
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
        return h;
    }
}
