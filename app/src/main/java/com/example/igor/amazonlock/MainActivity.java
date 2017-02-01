package com.example.igor.amazonlock;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

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
    private OutputStream outputStream;
    FloatingActionButton deletebtn;
    FloatingActionButton sendBtn;
    FloatingActionButton bluetoothBtn;
    TextView passText;
    final BluetoothAdapter bluetooth = BluetoothAdapter.getDefaultAdapter();
    BluetoothDevice device = null;
    BluetoothSocket socket = null;
    final int bluetoothRequest = 1;
    final int bluetoothPair = 2;
    boolean connect = false;
    private static String MAC = null;
    private boolean pressed;
    private String senha;
    UUID meuUUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
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
        deletebtn= (FloatingActionButton) findViewById(R.id.deleteButton);
        bluetoothBtn= (FloatingActionButton) findViewById(R.id.bluetoothBtn);
        sendBtn= (FloatingActionButton) findViewById(R.id.sendButton);
        passText= (TextView) findViewById(R.id.passTextView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.);
        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        if (bluetooth == null) {
            Toast.makeText(getApplicationContext(), "Seu dispositivo não suporta bluetooth", Toast.LENGTH_LONG).show();
    //       finish();
        }
        //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //      .setAction("Action", null).show();
        else {
            if (!bluetooth.isEnabled()) {
                Intent ativaBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(ativaBluetooth, bluetoothRequest);
            }
        }
        bluetoothBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (connect) {
                    //disconnect
                    try {
                        socket.close();
                        bluetoothBtn.setBackgroundColor(Color.RED);
                        connect = false;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    //connect
                    Intent abreLista = new Intent(MainActivity.this, DeviceList.class);
                    startActivityForResult(abreLista, bluetoothPair);
                }


            }
        });

        btn0.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.getBackground().setAlpha(255);
                        passText.setText(passText.getText()+"*");
                        senha+="0";
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
        });
        btn1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.getBackground().setAlpha(255);
                        passText.setText(passText.getText()+"*");
                        senha+="1";
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
        });
        btn2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.getBackground().setAlpha(255);
                        passText.setText(passText.getText()+"*");
                        senha+="2";
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
        });
        btn3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.getBackground().setAlpha(255);
                        passText.setText(passText.getText()+"*");
                        senha+="3";
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
        });
        btn4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.getBackground().setAlpha(255);
                        passText.setText(passText.getText()+"*");
                        senha+="4";
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
        });
        btn5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.getBackground().setAlpha(255);
                        passText.setText(passText.getText()+"*");
                        senha+="5";
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
        });
        btn6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.getBackground().setAlpha(255);
                        passText.setText(passText.getText()+"*");
                        senha+="6";
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
        });
        btn7.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.getBackground().setAlpha(255);
                        passText.setText(passText.getText()+"*");
                        senha+="7";
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
        });
        btn8.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.getBackground().setAlpha(255);
                        passText.setText(passText.getText()+"*");
                        senha+="8";
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
        });
        btn9.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.getBackground().setAlpha(255);
                        passText.setText(passText.getText()+"*");
                        senha+="9";
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
        });
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (connect) {
                    //disconnect
                    try {
                   outputStream.write("o".getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }}}


                );
        deletebtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if(passText.getText().charAt(passText.getText().length()-1)=='*'){
                          passText.setText(passText.getText().subSequence(0,passText.getText().length() - 1));
                        }
                        break;
                 /*   case MotionEvent.ACTION_UP:
                        v.getBackground().setAlpha(0);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        v.getBackground().setAlpha(0);
                        break;*/
                }
                return false;
            }
        });

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case bluetoothRequest:
                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(getApplicationContext(), "Bluetooth Ativado", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "O Bluetooth NÃO Foi Ativado, encerrando a aplicação", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
            case bluetoothPair:
                if (resultCode == RESULT_OK) {
                    MAC = data.getExtras().getString(DeviceList.MAC);
                    //  Toast.makeText(getApplicationContext(),"MAC: "+MAC,Toast.LENGTH_LONG).show();
                    device = bluetooth.getRemoteDevice(MAC);
                    try {
                        socket = device.createRfcommSocketToServiceRecord(meuUUID);
                        socket.connect();
                        outputStream = socket.getOutputStream();
                        Toast.makeText(getApplicationContext(), "Conectado com: " + MAC, Toast.LENGTH_LONG).show();
                        connect = true;
                    } catch (IOException e) {
                        Toast.makeText(getApplicationContext(), "Ocorreu um erro \n " + e, Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Conexão não estabelecida", Toast.LENGTH_LONG).show();

                }
        }
    }

}
