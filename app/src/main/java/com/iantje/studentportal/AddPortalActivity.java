package com.iantje.studentportal;

import android.app.Activity;
import android.content.Intent;
import android.net.sip.SipSession;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddPortalActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_portal);

        Button confirmButton = findViewById(R.id.addPortalButton);
        final TextView nameField = findViewById(R.id.nameField);
        final TextView urlField = findViewById(R.id.urlField);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addPortalIntent = new Intent();
                PortalItem pItem = new PortalItem(nameField.getText().toString(), urlField.getText().toString());
                addPortalIntent.putExtra(MainActivity.EXTRA_PORTAL_ITEM_INTENT, pItem);

                setResult(Activity.RESULT_OK, addPortalIntent);
                finish();
            }
        });
    }
}
