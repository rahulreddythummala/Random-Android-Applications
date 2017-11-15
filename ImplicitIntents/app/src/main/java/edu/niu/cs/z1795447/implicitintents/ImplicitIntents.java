package edu.niu.cs.z1795447.implicitintents;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import edu.niu.cs.z1795447.implicitintents.R;

public class ImplicitIntents extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
private Spinner spinner;
   private final static int CODE_A = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intents);
        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.intents,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        Intent i = null;
        switch (position)
        {
            case 1:
                i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.cs.niu.edu"));
                break;
            case 2:
                i = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:(345)3453456"));
                break;
            case 3:
                i = new Intent(Intent.ACTION_VIEW,Uri.parse("geo:50.123,7.1434?z=19"));
                break;
            case 4:
                i = new Intent(Intent.ACTION_VIEW,Uri.parse("geo:0,0?q=query"));
                break;
            case 5:
                i = new Intent("android.media.action.IMAGE_CAPTURE");
                break;
            case 6:
                i = new Intent(Intent.ACTION_VIEW,Uri.parse("content://contacts/people/"));


        }
        if(i != null)
            startActivityForResult(i,CODE_A);


    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {

    }
}
