package fr.uca.info.ontheroad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Welcome extends AppCompatActivity implements Animation.AnimationListener {

    private Button login;
    private Button register;
    private Button quit;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        quit = (Button) findViewById(R.id.quit);


        ArrayList<Button> buttonList = new ArrayList<Button>();

        buttonList.add(login);
        buttonList.add(register);
        buttonList.add(quit);

        for(int i = 0; i < buttonList.size(); i++) {
            buttonList.get(i).setVisibility(View.VISIBLE);
            buttonList.get(i).setAlpha(0f);
            buttonList.get(i).animate().alpha(1f).setDuration(1500);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Welcome.this, Login.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("register");
                Intent intent = new Intent(Welcome.this, Register.class);
                startActivity(intent);
            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
