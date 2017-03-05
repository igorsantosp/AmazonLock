package com.example.igor.amazonlock;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
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
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;
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
    InputStream mmInputStream;
    FloatingActionButton deletebtn;
    FloatingActionButton sendBtn;
    FloatingActionButton bluetoothBtn;
    FloatingActionButton closeBtn;
    TextView passText;
    final BluetoothAdapter bluetooth = BluetoothAdapter.getDefaultAdapter();
    BluetoothDevice device = null;
    BluetoothSocket socket = null;
    final int bluetoothRequest = 1;
    final int bluetoothPair = 2;
    boolean connect = false;
    private static String MAC = null;
    private boolean pressed;
    private String senha="";
    public String data;
    UUID meuUUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
    Thread workerThread;
    volatile boolean stopWorker;
    byte[] readBuffer;
    int readBufferPosition;
    TextToSpeech tts;
    int result;
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
        closeBtn =(FloatingActionButton) findViewById(R.id.closeBtn);
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
                //sendBtn.setBackgroundColor(Color.parseColor("ff99cc00"));
                if (connect) {
                    //disconnect
                    try {
                        for(int i=0;i<senha.length();i++){
                            char temp = senha.charAt(i);
                            String temp1= String.valueOf(temp);
                            outputStream.write(temp1.getBytes());
                        }
                        outputStream.write("p".getBytes());
                        //Toast.makeText(getApplicationContext(), senha, Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }}}


                );

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sendBtn.setBackgroundColor(Color.parseColor("ff99cc00"));
                if (connect) {
                    //disconnect
                    try {
                        outputStream.write("c".getBytes());
                        Toast.makeText(getApplicationContext(), "enviando 'c'", Toast.LENGTH_LONG).show();
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
                            senha=senha.subSequence(0,senha.length() - 1).toString();
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

        tts= new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i== TextToSpeech.SUCCESS){
                    result = tts.setLanguage(Locale.getDefault());
                }else{
                    Toast.makeText(MainActivity.this,"Seu dispositivo não suporta a fala",Toast.LENGTH_SHORT).show();
                }
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
       /* public Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {

                        data = (String) msg.obj;
                        passText.setText("Message : " + data);


            }

        };*/



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
                        if(socket!=null){
                            closeBT();
                        }
                        socket = device.createRfcommSocketToServiceRecord(meuUUID);
                        socket.connect();
                        outputStream = socket.getOutputStream();
                        mmInputStream = socket.getInputStream();
                        beginListenForData();
                        //InputStream input = socket.getInputStream();
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
    void beginListenForData()
    {
        final Handler handler = new Handler();
        final byte delimiter = 10; //This is the ASCII code for a newline character

        stopWorker = false;
        readBufferPosition = 0;
        readBuffer = new byte[1024];
        workerThread = new Thread(new Runnable()
        {
            public void run()
            {
                while(!Thread.currentThread().isInterrupted() && !stopWorker)
                {
                    try
                    {
                        int bytesAvailable = mmInputStream.available();
                        if(bytesAvailable > 0)
                        {
                            byte[] packetBytes = new byte[bytesAvailable];
                            mmInputStream.read(packetBytes);
                            for(int i=0;i<bytesAvailable;i++)
                            {
                                byte b = packetBytes[i];
                                if(b == delimiter)
                                {
                                    byte[] encodedBytes = new byte[readBufferPosition];
                                    System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);
                                    final String data = new String(encodedBytes, "US-ASCII");
                                    readBufferPosition = 0;

                                    handler.post(new Runnable()
                                    {
                                        public void run()
                                        {
                                           Toast.makeText(getApplicationContext(), "Data recebida:"+data, Toast.LENGTH_LONG).show();

                    //                        Toast.makeText(getApplicationContext(), "Dado recebido"+data, Toast.LENGTH_LONG).show();
                                            if(data.charAt(0)=='s'){
                                                Toast.makeText(getApplicationContext(), "Você entrou em modo super usuário, digite a nova senha", Toast.LENGTH_LONG).show();
                                                falar("Você entrou em modo super usuário");
                                            }else{
                                                if(data.charAt(0)=='n'){
                                                    Toast.makeText(getApplicationContext(), "Modo comum, digite a senha para abrir", Toast.LENGTH_LONG).show();
                                                    falar("Digite a senha para abrir a porta");
                                                }else{
                                                    if(data.charAt(0)=='e'){
                                                        Toast.makeText(getApplicationContext(), "SENHA ERRADA", Toast.LENGTH_LONG).show();
                                                        falar("Senha errada");
                                                        //sendBtn.setBackgroundColor(Color.parseColor("ffcc0000"));
                                                    }
                                                    else{
                                                        if(data.charAt(0)=='g'){
                                                            Toast.makeText(getApplicationContext(), "Nova senha gravada", Toast.LENGTH_LONG).show();
                                                            falar("Senha gravada");
                                                        }else{
                                                            if(data.charAt(0)=='a'){
                                                                Toast.makeText(getApplicationContext(), "Abrindo a porta", Toast.LENGTH_LONG).show();
                                                                falar("Abrindo a porta");
                                                            }else{
                                                                if(data.charAt(0)=='f'){
                                                                    Toast.makeText(getApplicationContext(), "Fechando a porta", Toast.LENGTH_LONG).show();
                                                                    falar("Fechando a porta");
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    });
                                }
                                else
                                {
                                    readBuffer[readBufferPosition++] = b;
                                }
                            }
                        }
                    }
                    catch (IOException ex)
                    {
                        stopWorker = true;
                    }
                }
            }
        });

        workerThread.start();
    }
    void closeBT() throws IOException
    {
        stopWorker = true;
        outputStream.close();
        mmInputStream.close();
        socket.close();
        //myLabel.setText("Bluetooth Closed");
    }
    void falar(String fala){
        if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
            Toast.makeText(MainActivity.this,"Problema na inicialização da Linguagem",Toast.LENGTH_LONG).show();
            if(result==TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(MainActivity.this,"Lingua não suportada      ",Toast.LENGTH_LONG).show();}

        }
        else{
            tts.speak(fala,TextToSpeech.QUEUE_ADD,null);
        }
    }
}
