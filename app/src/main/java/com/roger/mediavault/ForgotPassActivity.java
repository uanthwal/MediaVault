package com.roger.mediavault;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ForgotPassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_pass_activity);
        final String TAG = "ForgotPassActivity";
        Spinner securityQuesDrpDwn = (Spinner) findViewById(R.id.secQuesDrpDwn);
        List<String> list = new ArrayList<String>();
        list.add("What is the name of your first pet?");
        list.add("What is the name of your favourite sport?");
        list.add("What was your first mobile number?");
        list.add("What is the name of your childhood friend?");
        list.add("What is the mother's maiden name?");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        securityQuesDrpDwn.setAdapter(adapter);
        final String selectedSQ = securityQuesDrpDwn.getSelectedItem().toString();
        final EditText answerEditTxt = findViewById(R.id.answerEditTxt);
        final Button submitBtn = findViewById(R.id.submtBtnFP);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(AppConstants.SHARED_PREF_FILE_NAME, 0);
                String answerTxt = answerEditTxt.getText().toString();
                answerTxt = answerTxt.trim();
                String secQues = sharedPreferences.getString("SecurityQuestion", "-1");
                String secAns = sharedPreferences.getString("SecurityQuestionAnswer", "-1");
                if (secQues.equals(selectedSQ) && secAns.equals(answerTxt)) {
                    Intent goToNextActivity = new Intent(getApplicationContext(), NewUserActivity.class);
                    goToNextActivity.putExtra("CALLING_ACTIVITY", "FORGOT_PASSWORD");
                    startActivity(goToNextActivity);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), AppConstants.INCORRECT_SEC_Q_AND_A, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
