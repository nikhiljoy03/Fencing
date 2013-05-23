package com.example.nikita;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class ImagePickerActivity extends Activity {

	Intent myIntent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_picker);
        myIntent = new Intent(this, StandardActivity.class);
        ImageButton b1 = (ImageButton) findViewById(R.id.imageButton1);
        ImageButton b2 = (ImageButton) findViewById(R.id.imageButton2);
        ImageButton b3 = (ImageButton) findViewById(R.id.imageButton3);
        ImageButton b4 = (ImageButton) findViewById(R.id.imageButton4);
        ImageButton b5 = (ImageButton) findViewById(R.id.imageButton5);
        
        addListener(b1, 5, 0);
        addListener(b2, 4, 0);
        addListener(b3, 3, 0);
        addListener(b4, 5, 2);
        addListener(b5, 4, 2);
    }
    
    public void addListener(ImageButton button, final int line, final int cross)
    {
        button.setOnClickListener(new View.OnClickListener()
        {
			public void onClick(View v)
			{
				gotoScreen(line, cross);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_image_picker, menu);
        return true;
    }
    
    public void gotoScreen(int line, int cross)
    {
    	myIntent.putExtra("line", line);
    	myIntent.putExtra("cross", cross);
    	
    	startActivity(myIntent);
    }
}
