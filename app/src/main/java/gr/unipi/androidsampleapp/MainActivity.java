package gr.unipi.androidsampleapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView helloTextView;
    private Button blueButton;
    private Locale locale = Locale.getDefault();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_main_language:
                changeLocale();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Makes the text color red.
     */
    public void changeColorToRed(View view) {
        helloTextView.setTextColor(getResources().getColor(R.color.colorUnipiRed));
    }

    /**
     * Changes the {@link Locale} of the application and updates the {@link Configuration}
     */
    private void changeLocale() {
        if (locale.getLanguage().equals("el")) {
            locale = new Locale("en");
        } else if (locale.getLanguage().equals("en")) {
            locale = new Locale("el");
        }

        Locale.setDefault(locale);

        Resources resources = getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, displayMetrics);

        //We can check the version api with
        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
        //}

        Toast.makeText(this, R.string.main_language_changed_message, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    /**
     * Starts the {@link ImplicitIntentsActivity}
     */
    public void goToImplicitIntents(View view) {
        Intent intent = new Intent(this, ImplicitIntentsActivity.class);
        startActivity(intent);
    }
}
