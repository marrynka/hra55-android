package com.geewhizstuff.hra55;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.TextView;

import com.mixpanel.android.mpmetrics.MixpanelAPI;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
import android.media.MediaPlayer;
import java.util.Random;


public class QuestionActivity extends AppCompatActivity {

    public String[] currentAnswers;
    public DBAccess db;
    public int questionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        String projectToken = "889f8ea7b0077cf3ef1e0338e9914873"; // e.g.: "1ef7e30d2a58d27f4b90c42e31d6d7ad"
        MixpanelAPI mixpanel = MixpanelAPI.getInstance(this, projectToken);

        questionId = 1;
        currentAnswers = new String[]{"bird1", "bird2", "bird3", "bird4", "bird4", "bird5", "bird6"};
        db = new DBAccess(getApplicationContext());
        db.ping();

        new CountDownTimer(15000, 1000) {
            TextView timer = (TextView) findViewById(R.id.timer);
            public void onTick(long millisUntilFinished){
                timer.setText("Time left: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                changeQuestion();
                this.start();
            }
        }.start();

        try {
            JSONObject props = new JSONObject();
            props.put("pohlavie", "zena");
            props.put("rocnik", 5);
            props.put("mesto", "Bratiska");
            props.put("stress", 7);
            mixpanel.track("stress", props);
        } catch (JSONException e) {
            Log.e("MYAPP", "Unable to add properties to JSONObject", e);
        }
    }

    private void changeQuestion(){
        TextView question = (TextView) findViewById(R.id.welcome);
        String answer;
        if(questionId == 1) {
            currentAnswers = new String[]{"dog1", "dog2", "dog3", "dog4", "dog5", "dog6"};
            question.setText("What's the animal?");
            answer="dog";
            questionId=2;
        }
        else {
            currentAnswers = new String[]{"pencil1", "pencil2", "pencil3", "pencil4", "pencil5", "pencil6"};
            question.setText("What's the object?");
            answer="pencil";
            questionId=1;
        }
        for(int i=0;i<currentAnswers.length;i++) {
            int id;
            id = getResources().getIdentifier("answer"+(i+1), "id", getPackageName());
            TextView answerView = (TextView) findViewById(id);
            answerView.setText("The answer is " + answer);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void submit(View view) {
        TextView responseView = (TextView) findViewById(R.id.response);
        String response = responseView.getText().toString();
        boolean found=false;
        int i;
        for(i=0;i<currentAnswers.length;i++) {
            if (currentAnswers[i].equals(response)) {
                found = true;
                break;
            }
        }

        if (found) {
            int resource = R.raw.bravo1;
            Random rand = new Random(System.currentTimeMillis());
            int random = rand.nextInt(3);
            switch(random){
                case 0: resource = R.raw.bravo1; break;
                case 1: resource = R.raw.bravo2; break;
                case 2: resource = R.raw.bravo3; break;

            }
            MediaPlayer mediaPlayer = MediaPlayer.create(this, resource);
            mediaPlayer.start();
            int id;
            id = getResources().getIdentifier("answer"+(i+1), "id", getPackageName());
            TextView answerView = (TextView) findViewById(id);
            answerView.setText(response);
        } else {
            MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.sad);
            mediaPlayer.start();
        }

    }

}
