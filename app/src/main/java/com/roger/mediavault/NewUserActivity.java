package com.roger.mediavault;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String callingActivity = getIntent().getStringExtra("CALLING_ACTIVITY");
        setContentView(R.layout.new_user_activity);
        final String TAG = "NewUserActivity";
        final EditText passCode = findViewById(R.id.passCode);
        final EditText confirmPassCode = findViewById(R.id.confirmPassCode);
        final Button submitBtn = findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "passCode.getText().toString() " + passCode.getText().toString());
                if (passCode.getText().toString().equals(confirmPassCode.getText().toString())) {
                    Log.i(TAG, "passCode and confirmPassCode are matching");
                    SharedPreferences sharedPreferences = getSharedPreferences(AppConstants.SHARED_PREF_FILE_NAME, 0);
                    sharedPreferences.edit().putString("APP_PIN", passCode.getText().toString()).commit();
                    Toast.makeText(getApplicationContext(), AppConstants.PASSCODE_SAVED, Toast.LENGTH_SHORT).show();
                    if (null != callingActivity && callingActivity.equals("FORGOT_PASSWORD")) {
                        Intent goToNextActivity = new Intent(getApplicationContext(), PassCodeActivity.class);
                        startActivity(goToNextActivity);
                        finish();
                    } else {
                        Intent goToNextActivity = new Intent(getApplicationContext(), SecurityQuesActivity.class);
                        startActivity(goToNextActivity);
                        finish();
                    }
                } else {
                    Log.i(TAG, "passCode and confirmPassCode are not matching");
                    Toast.makeText(getApplicationContext(), AppConstants.PASSCODE_CONFIRM_PASSCODE_NOT_MATCHING, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
