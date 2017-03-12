package gr.unipi.androidsampleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView helloTextView;
    private Button blueButton;
    //Keep in mind that there is no need to add a reference to a red button!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helloTextView = (TextView) findViewById(R.id.txt_main_hello);
        blueButton = (Button) findViewById(R.id.btn_main_blue);

        //A listener is added to the blueButton. Once the button is clicked,
        //the text becomes blue
        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helloTextView.setTextColor(getResources().getColor(R.color.colorUnipiBlue));
            }
        });
    }

    /**
     * Makes the text color red.
     */
    public void changeColorToRed(View view) {
        helloTextView.setTextColor(getResources().getColor(R.color.colorUnipiRed));
    }
}
