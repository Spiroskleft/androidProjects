package gr.unipi.androidsampleapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ImplicitIntentsActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "SavedValues";
    private static final String PREFS_PHONE = "SavedValues_Phone";

    private EditText urlText;
    private Button urlButton;
    private EditText phoneText;
    private Button phoneButton;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intents);

        preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        urlText = (EditText) findViewById(R.id.txt_implicit_intents_url);
        urlButton = (Button) findViewById(R.id.btn_implicit_intents_url);
        phoneText = (EditText) findViewById(R.id.txt_implicit_intents_phone);
        phoneButton = (Button) findViewById(R.id.btn_implicit_intents_phone);

        urlText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    openUrl();
                    handled = true;
                }
                return handled;
            }
        });
        urlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl();
            }
        });
        phoneText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    callPhone();
                    handled = true;
                }
                return handled;
            }
        });
        phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        String number = preferences.getString(PREFS_PHONE, "");
        if (!"".equals(number)) {
            phoneText.setText(number);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PREFS_NAME, phoneText.getText().toString());
        editor.commit();
    }

    /**
     * Opens the url entered in the urlText
     */
    private void openUrl() {
        Uri uri = Uri.parse(urlText.getText().toString());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        // Verify it resolves
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        boolean isIntentSafe = activities.size() > 0;

        // Start an activity if it's safe
        if (isIntentSafe) {
            startActivity(intent);
        } else {
            String title = getResources().getString(R.string.implicit_intent_choose);
            Intent chooser = Intent.createChooser(intent, title);

            // Verify the intent will resolve to at least one activity
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(chooser);
            } else {
                Toast.makeText(this, R.string.implicit_intent_action_not_supported, Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Calls the number entered in the phoneText
     */
    private void callPhone() {
        Uri uri = Uri.parse("tel:" + phoneText.getText().toString());
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);

        // Verify it resolves
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        boolean isIntentSafe = activities.size() > 0;

        // Start an activity if it's safe
        if (isIntentSafe) {
            startActivity(intent);
        } else {
            String title = getResources().getString(R.string.implicit_intent_choose);
            Intent chooser = Intent.createChooser(intent, title);

            // Verify the intent will resolve to at least one activity
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(chooser);
            } else {
                Toast.makeText(this, R.string.implicit_intent_action_not_supported, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
