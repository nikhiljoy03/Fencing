package com.example.nikita;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class SplashActivity extends Activity implements OnTouchListener
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acticity_splash);
		findViewById(R.id.mainLayout).setOnTouchListener(this);
	}

	public boolean onTouch(View v, MotionEvent event)
	{
		if (event.getAction() == MotionEvent.ACTION_UP)
		{
			startActivity(new Intent(this, MainActivity.class));
			this.finish();
		}
		return true;
	}
}
