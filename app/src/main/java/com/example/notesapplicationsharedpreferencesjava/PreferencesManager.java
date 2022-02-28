package com.example.notesapplicationsharedpreferencesjava;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class PreferencesManager {

    private static PreferencesManager preferencesManager;
    private final SharedPreferences sharedPreferences;
    private static Set<String> set;

    public static PreferencesManager getInstance(Context context) {
        if (preferencesManager == null) {
            preferencesManager = new PreferencesManager(context);
        }
        return preferencesManager;
    }

    private PreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        set = new LinkedHashSet<>();
    }

    public void saveData(String key, String value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString(key, value);
        prefsEditor.apply();
    }

    public String getData(String key) {
        return sharedPreferences.getString(key, "");
    }

    public void removeData(String key) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.remove(key);
        prefsEditor.apply();
    }

    public void clearAll() {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.clear();
        prefsEditor.apply();
    }

    public void saveObjectList(String key, ArrayList<Note> notes) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(notes);
        prefsEditor.putString(key, json);
        prefsEditor.apply();
    }

    public ArrayList<Note> getObjectList(String key){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(key, null);
        Type type = new TypeToken<ArrayList<Note>>(){}.getType();
        return gson.fromJson(json, type);
    }
}
