package com.example.nikita;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getActionBar().setTitle("Fence Calculator");
		Button custom = (Button) findViewById(R.id.button1);
		Button standard = (Button) findViewById(R.id.Button01);

		custom.setOnClickListener(new View.OnClickListener()
		{

			public void onClick(View v)
			{
				gotoCustom();
			}
		});

		standard.setOnClickListener(new View.OnClickListener()
		{

			public void onClick(View v)
			{
				gotoStandard();
			}
		});
	}

	public void gotoCustom()
	{
		Intent next = new Intent(MainActivity.this, CustomActivity.class);
		startActivity(next);
	}

	public void gotoStandard()
	{
		Intent next = new Intent(MainActivity.this, StandardActivity.class);
		startActivity(next);
	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu)
//	{
//		getMenuInflater().inflate(R.menu.activity_main, menu);
//		return true;
//	}
}
