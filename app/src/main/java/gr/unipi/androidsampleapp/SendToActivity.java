package gr.unipi.androidsampleapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class SendToActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_to);

        // Get the intent that started this activity
        Intent intent = getIntent();

        // Figure out what to do based on the intent type
        if (intent.getType().contains("image/")) {
            Uri imageUri = intent.getParcelableExtra(Intent.EXTRA_STREAM);
            ImageView img = (ImageView) findViewById(R.id.img_send_to);
            img.setImageURI(imageUri);

        } else if (intent.getType().equals("text/plain")) {
            TextView txt = (TextView) findViewById(R.id.txt_send_to);
            txt.setText(intent.getStringExtra(Intent.EXTRA_TEXT));
        }
    }
}
