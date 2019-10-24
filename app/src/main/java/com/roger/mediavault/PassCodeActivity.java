package com.roger.mediavault;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PassCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pass_code_activity);
        final String TAG = "PassCodeActivity";
        final EditText passCode = findViewById(R.id.pinCode);
        final Button submitBtn = findViewById(R.id.submitBtnPC);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(AppConstants.SHARED_PREF_FILE_NAME, 0);
                sharedPreferences.getString("APP_PIN", "83983469");
                if (passCode.getText().toString().equals(sharedPreferences.getString("APP_PIN", "83983469"))) {
                    Intent goToNextActivity = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(goToNextActivity);
                    finish();
                } else {
                    Log.i(TAG, "Wrong PIN");
                    passCode.setText("");
                    Toast.makeText(getApplicationContext(), AppConstants.INCORRECT_PIN, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onClickForgotPassword(View v) {
        Intent goToNextActivity = new Intent(getApplicationContext(), ForgotPassActivity.class);
        startActivity(goToNextActivity);
    }
}
