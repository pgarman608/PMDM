package com.example.myapplication.modelos;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

public class ApiCNTRL {
    private static String URL_BASE = "https://lexica.art/api/v1/search?=";
    private static String KEY = "1c61f71df4bc98f9734734e2583cc8ca147e3b01";
    private JSONArray arrayjson;
    private HttpURLConnection httpurl;

    public static void generarPrediccion(String msg){
        String content=null;
        HttpURLConnection httpConn = null;
        try {
            URL url = new URL("https://lexica.art/api/v1/search?q=" + msg);
            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setDoOutput(true);
            httpConn.setRequestProperty("Content-Type", "application/json");
            httpConn.setRequestProperty("Accept", "application/json");
            if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK){

            }

            Log.i(TAG, "Termina");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if( httpConn != null ) httpConn.disconnect();
        }

    }
}
