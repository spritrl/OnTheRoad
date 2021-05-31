package fr.uca.info.ontheroad;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    private Button register;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        EditText username = (EditText) findViewById(R.id.username);
        EditText mail = (EditText) findViewById(R.id.mail);
        EditText password = (EditText) findViewById(R.id.password);

        username.getText();
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        register = (Button) findViewById(R.id.register);

        register.setVisibility(View.VISIBLE);
        register.setAlpha(0f);
        register.animate().alpha(1f).setDuration(1500);

        Button btnBack = (Button) findViewById(R.id.back);
        btnBack.setVisibility(View.VISIBLE);
        btnBack.setAlpha(0f);
        btnBack.animate().alpha(1f).setDuration(1500);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Welcome.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIMethode api;
                String url = "http://185.98.138.221:8080/api/register/username=\""
                        + username.getText() + "\"&mail=\""
                        + mail.getText() + "\"&password=\""
                        + password.getText() + "\"";
                api = new APIMethode(url);
                try {
                    if(api.sendGet()) {
                        Intent intent = new Intent(Register.this, Welcome.class);
                        startActivity(intent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
