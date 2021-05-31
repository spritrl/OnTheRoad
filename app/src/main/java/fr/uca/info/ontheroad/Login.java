package fr.uca.info.ontheroad;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class Login extends AppCompatActivity {
    private Button login;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        login = (Button) findViewById(R.id.login);

        login.setVisibility(View.VISIBLE);
        login.setAlpha(0f);
        login.animate().alpha(1f).setDuration(1500);

        Button btnBack = (Button) findViewById(R.id.back);
        btnBack.setVisibility(View.VISIBLE);
        btnBack.setAlpha(0f);
        btnBack.animate().alpha(1f).setDuration(1500);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Welcome.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIMethode api;
                String url = "http://185.98.138.221:8080/api/login/username=\"" + username.getText() + "\"&password=\"" + password.getText() + "\"";
                api = new APIMethode(url);
                try {
                    if(api.sendGet()) {
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
