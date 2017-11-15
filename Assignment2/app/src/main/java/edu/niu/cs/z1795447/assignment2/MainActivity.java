package edu.niu.cs.z1795447.assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class MainActivity extends Activity
{
    private Spinner pic_choice;
    private ImageView show_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pic_choice = (Spinner) findViewById(R.id.choice);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_main, SpinnerInfo.valueArray);

        pic_choice.setAdapter(adapter);

        pic_choice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int choice = parent.getSelectedItemPosition();

                show_item = (ImageView) findViewById(R.id.imageTeam);

                switch (choice) {
                    case 0:
                        show_item.setImageResource(R.drawable.dc);
                        break;

                    case 1:
                        show_item.setImageResource(R.drawable.rcb);
                        break;

                    case 2:
                        show_item.setImageResource(R.drawable.rr);
                        break;

                    case 3:
                        show_item.setImageResource(R.drawable.dd);
                        break;

                    case 4:
                        show_item.setImageResource(R.drawable.csk);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void goTeam(View view)
    {
        int call_choice = pic_choice.getSelectedItemPosition();

        switch(call_choice)
        {
            case 0:
                Intent target = new Intent(MainActivity.this, DC.class);
                startActivity(target);
                break;

            case 1:
                target = new Intent(MainActivity.this, RCB.class);
                startActivity(target);
                break;

            case 2:
                target = new Intent(MainActivity.this, RR.class);
                startActivity(target);
                break;

            case 3:
                target = new Intent(MainActivity.this, DD.class);
                startActivity(target);
                break;

            case 4:
                target = new Intent(MainActivity.this, CSK.class);
                startActivity(target);
                break;

        }
    }

}
