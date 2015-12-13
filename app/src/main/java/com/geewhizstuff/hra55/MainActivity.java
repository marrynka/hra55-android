package com.geewhizstuff.hra55;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    public String[] currentAnswers;
    public DBAccess db;
    public int questionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_main);
    }
    public void submit(View view) {
        Intent intent = new Intent(this, QuestionActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }


}
