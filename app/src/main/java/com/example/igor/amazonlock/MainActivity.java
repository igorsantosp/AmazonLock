package com.example.igor.amazonlock;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TableLayout;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import static com.example.igor.amazonlock.R.drawable.amazon;


public class MainActivity extends AppCompatActivity {
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btn0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        btn0.setOnTouchListener(touching);
        btn1.setOnTouchListener(touching);
        btn2.setOnTouchListener(touching);
        btn3.setOnTouchListener(touching);
        btn4.setOnTouchListener(touching);
        btn5.setOnTouchListener(touching);
        btn6.setOnTouchListener(touching);
        btn7.setOnTouchListener(touching);
        btn8.setOnTouchListener(touching);
        btn9.setOnTouchListener(touching);
        btn0.getBackground().setAlpha(0);
        btn1.getBackground().setAlpha(0);
        btn2.getBackground().setAlpha(0);
        btn3.getBackground().setAlpha(0);
        btn4.getBackground().setAlpha(0);
        btn5.getBackground().setAlpha(0);
        btn6.getBackground().setAlpha(0);
        btn7.getBackground().setAlpha(0);
        btn8.getBackground().setAlpha(0);
        btn9.getBackground().setAlpha(0);

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

    View.OnTouchListener touching = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.getBackground().setAlpha(255);
                    break;
                case MotionEvent.ACTION_UP:
                    v.getBackground().setAlpha(0);
                    break;
                case MotionEvent.ACTION_CANCEL:
                    v.getBackground().setAlpha(0);
                    break;
            }
            return false;
        }
    };


}
