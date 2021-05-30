package fr.uca.info.ontheroad;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class UserProfil extends AppCompatActivity {

    ArrayList<HistoryElement> historyElementArrayList = new ArrayList<HistoryElement>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profil);

        ListView historyList = (ListView) findViewById(R.id.historyListView);
        Button btnAskExam = (Button) findViewById(R.id.btnAskExam);
        Button btnBack = (Button) findViewById(R.id.backBtn);

        btnAskExam.setBackgroundColor(Color.parseColor("#a02c5d"));
        btnBack.setBackgroundColor(Color.parseColor("#ffc047"));

        try{
            readJSON(getApplicationContext());
        }catch (JSONException e){
            e.printStackTrace();
        }

        String[] test = new String[historyElementArrayList.size()];
        for(int i = 0; i < test.length; i++){
            test[i] = historyElementArrayList.get(i).getSectionName();
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.history_list_layout, R.id.testText, test){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView sectionName =(TextView) view.findViewById(R.id.testText);
                TextView sectionResult =(TextView) view.findViewById(R.id.resultText);
                ImageView sectionIcon = (ImageView) view.findViewById(R.id.sectionIcon);

                Resources resources = getApplication().getResources();
                final int resourcesImage = resources.getIdentifier(historyElementArrayList.get(position).getSectionIcon(), "drawable",
                        getApplication().getPackageName());

                sectionIcon.setImageResource(resourcesImage);

                GradientDrawable border = new GradientDrawable();
                border.setColor(Color.parseColor(historyElementArrayList.get(position).getSectionColor()));
                border.setStroke(2,Color.parseColor(historyElementArrayList.get(position).getSectionColor()));
                border.setCornerRadius(20);

                LinearLayout mainLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
                mainLayout.setBackground(border);

                sectionName.setText(""+historyElementArrayList.get(position).getSectionName());
                sectionResult.setText(""+historyElementArrayList.get(position).getSectionResult());

                return view;
            }
        };
        historyList.setAdapter(adapter);
    }

    public void readJSON(Context context) throws JSONException {
        String stringJson = null;
        try{
            InputStream is = context.getAssets().open("history.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            stringJson = new String(buffer, "UTF-8");
            JSONObject jsonArray = new JSONObject(stringJson);
            int historySize = jsonArray.getJSONArray("history").length();

            for(int histories = 0; histories < historySize; histories++){
                HistoryElement historyElement = new HistoryElement(
                        jsonArray.getJSONArray("history").getJSONObject(histories).getString("section_name"),
                        jsonArray.getJSONArray("history").getJSONObject(histories).getString("section_result"),
                        jsonArray.getJSONArray("history").getJSONObject(histories).getString("section_icon"),
                        jsonArray.getJSONArray("history").getJSONObject(histories).getString("section_color")
                );
                historyElementArrayList.add(historyElement);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
