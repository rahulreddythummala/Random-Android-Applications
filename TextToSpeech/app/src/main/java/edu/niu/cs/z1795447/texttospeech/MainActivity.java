package edu.niu.cs.z1795447.texttospeech;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.locks.Lock;

public class MainActivity extends AppCompatActivity
{
    private static final int CODE_A = 1;
    private Button btnSpeak, btnListen;
    private EditText etSpeak;
    private TextView tvListen;
    private TextToSpeech ttsObj;
    private int result;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnListen = (Button)findViewById(R.id.buttonListen);
        btnSpeak = (Button)findViewById(R.id.buttonSpeak);
        etSpeak = (EditText)findViewById(R.id.editTextIn);
        tvListen = (TextView)findViewById(R.id.textViewOut);
        ttsObj = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status)
            {
               if(status == TextToSpeech.SUCCESS)
               {
                   result = ttsObj.setLanguage(Locale.US);
               }
               else
               {
                   Toast.makeText(getApplicationContext(), getString(R.string.message), Toast.LENGTH_LONG).show();
               }
            }
        }
        );

        btnSpeak.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (result == TextToSpeech.LANG_NOT_SUPPORTED  || result == TextToSpeech.LANG_MISSING_DATA)
                {
                    Toast.makeText(getApplicationContext(), getString(R.string.message), Toast.LENGTH_LONG).show();
                }
                else
                {
                    ttsObj.speak(etSpeak.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });

        btnListen.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                i.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.prompt));
                try
                {
                    startActivityForResult(i, CODE_A);
                }
                catch(ActivityNotFoundException anfe)
                {
                    Toast.makeText(getApplicationContext(), getString(R.string.message), Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == CODE_A && data != null && resultCode == RESULT_OK)
        {
            ArrayList<String> spokenText =
                    data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            tvListen.setText(spokenText.get(0));
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if(ttsObj != null)
        {
            ttsObj.stop();
            ttsObj.shutdown();
        }
    }
}
