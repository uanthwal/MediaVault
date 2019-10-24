package com.roger.mediavault;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SecurityQuesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.security_ques_activity);
        final String TAG = "SecurityQuesActivity";
        Spinner securityQuesDrpDwn = (Spinner) findViewById(R.id.secQuesDrpDwn);
        List<String> list = new ArrayList<String>();
        list.add("What is the name of your first pet?");
        list.add("What is the name of your favourite sport?");
        list.add("What was your first mobile number?");
        list.add("What is the name of your childhood friend?");
        list.add("What is the mother's maiden name?");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        securityQuesDrpDwn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v(TAG, (String) parent.getItemAtPosition(position));
                SharedPreferences sharedPreferences = getSharedPreferences(AppConstants.SHARED_PREF_FILE_NAME, 0);
                sharedPreferences.edit().putString("SecurityQuestion", (String) parent.getItemAtPosition(position)).commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                SharedPreferences sharedPreferences = getSharedPreferences(AppConstants.SHARED_PREF_FILE_NAME, 0);
                sharedPreferences.edit().putString("SecurityQuestion", "-1").commit();
            }
        });
        securityQuesDrpDwn.setAdapter(adapter);
        final EditText answerEditTxt = findViewById(R.id.answerEditTxt);
        final Button submitBtn = findViewById(R.id.submtBtnSQ);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(AppConstants.SHARED_PREF_FILE_NAME, 0);
                String answerTxt = answerEditTxt.getText().toString();
                answerTxt = answerTxt.trim();
                String secQues = sharedPreferences.getString("SecurityQuestion", "-1");
                if (answerTxt != "" || !secQues.equals("-1")) {
                    Log.i(TAG, "passCode and confirmPassCode are matching");
                    sharedPreferences.edit().putString("SecurityQuestionAnswer", answerTxt).commit();
                    Toast.makeText(getApplicationContext(), AppConstants.SECURITY_Q_AND_A, Toast.LENGTH_SHORT).show();
                    Intent goToNextActivity = new Intent(getApplicationContext(), PassCodeActivity.class);
                    startActivity(goToNextActivity);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), AppConstants.SECURITY_A_EMPTY, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
