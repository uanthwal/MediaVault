package com.roger.mediavault;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getSharedPreferences(AppConstants.SHARED_PREF_FILE_NAME, 0);
//        sharedPreferences.edit().putBoolean("isFirstTime", true).commit();
        if(sharedPreferences.getBoolean("isFirstTime", true))
        {
            File folder = getFilesDir();
            File[] files = folder.listFiles();
            if(!checkIfFolderExists(files)) {
                File f= new File(folder, AppConstants.MEDIA_FOLDER);
                f.mkdir();
            }

            sharedPreferences.edit().putBoolean("isFirstTime", false).commit();
            //Redirect user to first time activity
            Intent goToNextActivity = new Intent(getApplicationContext(), NewUserActivity.class);
            startActivity(goToNextActivity);
            finish();
        } else {
            //Redirect user to passcode screen
            Intent goToNextActivity = new Intent(getApplicationContext(), PassCodeActivity.class);
            startActivity(goToNextActivity);
            finish();
        }
    }

    private boolean checkIfFolderExists(File[] files) {
        boolean folderExisits = false;
        for(File f : files) {
            if(f.isDirectory() && f.getName().equals(AppConstants.MEDIA_FOLDER)) {
                folderExisits = true;
                break;
            }
        }
        return folderExisits;
    }
}
