package edu.niu.cs.z1795447.assignment_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.lang.String;

import static java.lang.Math.round;

public class MainActivity extends AppCompatActivity {

    private TextView tipTV, totalTV;
    private EditText billTxt, tipPercTxt;
    private Button btn;
    double bill,tipperc,tipamount,totalamount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        billTxt = (EditText)findViewById(R.id.TextBill);
        tipPercTxt = (EditText)findViewById(R.id.TextTip);
        tipTV = (TextView)findViewById(R.id.textViewTip);
        totalTV = (TextView)findViewById(R.id.textViewTotal);
        btn = (Button)findViewById(R.id.calBtn);
        btn.setOnClickListener(calculate);

            }

            private View.OnClickListener calculate = new View.OnClickListener()
            {
                @Override
                public void onClick(View view) {
                    bill   = Float.parseFloat(billTxt.getText().toString());
                    tipperc = Float.parseFloat(tipPercTxt.getText().toString());
                    tipamount = bill * (tipperc / 100);
                    totalamount = bill + tipamount;

                    tipTV.setText(String.format("%.2f",tipamount));
                    totalTV.setText(String.format("%.2f",totalamount));



                }
            };


}
