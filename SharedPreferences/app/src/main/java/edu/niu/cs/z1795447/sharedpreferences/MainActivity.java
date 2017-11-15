package edu.niu.cs.z1795447.sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    EditText etName;
    SharedPreferences preferences;
    String p;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getApplicationContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        p = preferences.getString("key", "Rahul");
        etName = (EditText)findViewById(R.id.editTextName);
        Toast.makeText(this, "The name is" + p, Toast.LENGTH_LONG).show();
    }

    public void savePrefs(View v)
    {
        SharedPreferences.Editor editor = preferences.edit();
        String name = etName.getText().toString();
        editor.putString("key", name);
        editor.commit();
    }
}
