package com.example.nikita;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        
        TextView totalCost = (TextView) findViewById(R.id.res_fencecost);
        TextView bundles = (TextView) findViewById(R.id.res_barbed);
        TextView days = (TextView) findViewById(R.id.res_daysreq);
        
        Intent myIntent= getIntent();
        
        totalCost.setText(myIntent.getStringExtra("cost"));
        bundles.setText(myIntent.getStringExtra("bundle"));
        days.setText(myIntent.getStringExtra("days"));
        
        ImageView resButton = (ImageView)findViewById(R.id.resultButton);
        final Intent intent = new Intent(Intent.ACTION_CALL);
        resButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				intent.setData(Uri.parse("tel:" + "+919995200747"));
				ResultsActivity.this.startActivity(intent);
			}
		});
    }

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_results, menu);
        return true;
    }
}
