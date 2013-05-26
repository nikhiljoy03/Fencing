package com.example.nikita;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class StandardActivity extends Activity implements OnClickListener
{
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.standard_layout);
		getActionBar().setTitle("Standard Layout");
		getActionBar().setDisplayHomeAsUpEnabled(true);
//		Button done = (Button) findViewById(R.id.done);
//		done.setOnClickListener(new View.OnClickListener()
//		{
//
//			public void onClick(View v)
//			{
//				process();
//			}
//		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.activity_main, menu);
		MenuItem doneItem = menu.findItem(R.id.done);
		View view = doneItem.getActionView().findViewById(R.id.button_done);
		view.setOnClickListener(this);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem)
	{
		switch (menuItem.getItemId()) {
			case R.id.button_done:
				return true;

			default:
				super.onBackPressed();
				return true;
		}
	}

	@SuppressLint("UseValueOf")
	public void process() {
		Intent myIntent= getIntent();
		
		
		EditText area = (EditText) findViewById(R.id.areaid);
//		EditText len = (EditText) findViewById(R.id.len);
//		EditText width = (EditText) findViewById(R.id.wid);
		EditText barbePrice = (EditText) findViewById(R.id.barbed_price);
		EditText bracePrice = (EditText) findViewById(R.id.brace_price);
		EditText boltPrice = (EditText) findViewById(R.id.bolt_price);
		EditText polePrice = (EditText) findViewById(R.id.poledist_price);
		EditText labourCharges = (EditText) findViewById(R.id.labourCharges); 

		//Spinner wireSpinner = (Spinner) findViewById(R.id.planets_spinner);
		Spinner unit = (Spinner) findViewById(R.id.spinner1);
		//Spinner crossSpinner = (Spinner) findViewById(R.id.wires_spinner);
		Spinner barbedSpinner = (Spinner) findViewById(R.id.barbed_spinner);
		Spinner gateSpinner = (Spinner) findViewById(R.id.gates_spinner);
		//Spinner poleSpinner = (Spinner) findViewById(R.id.pole_spinner);

		Spinner supportSpinner = (Spinner) findViewById(R.id.support_spinner);
		//Spinner lineSpinner = (Spinner) findViewById(R.id.planets_spinner);
		//Spinner labourSpinner = (Spinner) findViewById(R.id.number_spinner);

		//int labourChargesValue = Integer.parseInt(labourCharges.getText().toString());
		String choice = unit.getSelectedItem().toString();
		int areaValue = Integer.parseInt(area.getText().toString());

		int wireValue = myIntent.getIntExtra("line", 1);
		//int barbedPriceValue = Integer
				//.parseInt(barbePrice.getText().toString());
		int crossValue = myIntent.getIntExtra("cross", 1);
		String barbedValue = barbedSpinner.getSelectedItem().toString();
		int gateValue = Integer.parseInt(gateSpinner.getSelectedItem()
				.toString());
		//String poleValue = poleSpinner.getSelectedItem().toString();
		//int fenceValue = 6;//Integer.parseInt(fenceSpinner.getSelectedItem()
				//.toString());
		//int distanceValue = 8;//Integer.parseInt(distanceSpinner.getSelectedItem()
				//.toString());
		String supportValue = supportSpinner.getSelectedItem().toString();
		//int lineValue = Integer.parseInt(lineSpinner.getSelectedItem()
		//		.toString());
		//int labourValue = Integer.parseInt(labourSpinner.getSelectedItem()
		//		.toString());
		//int bracePriceValue = Integer.parseInt(bracePrice.getText().toString());
		//int boltPriceValue = Integer.parseInt(boltPrice.getText().toString());
		//int poleDistPriceValue = Integer.parseInt(polePrice.getText()
		//		.toString());

//		Double unitTemp = 43560.0;
//		if (choice.equals("hectare")) {
//			unitTemp = 107639.0;
//		} else if (choice.equals("sq meter")) {
//			unitTemp = 10.7639;
//		}
//		Double a1 = areaValue * unitTemp;
//		//Double ratio = (double) (lenValue / widthValue);
//
//		Double l1 = (Math.sqrt(a1 / ratio)) * ratio;
//		Double l2 = Math.sqrt(a1 / ratio);
		Double unitTemp = new Double(3.281);
		if (choice.equals("feet"))
		{
			unitTemp = new Double(1);
		}
		Double l3 = (areaValue * unitTemp) - (gateValue * 6);
		Double l_len = l3 * wireValue ;
		Double poles = l3/(8.328);
		Double c_len=10*crossValue*(poles-1);
		Double t_len=c_len+l_len;
		
		int divValue = 20;
		if (barbedValue.equals("12x14")) {
			divValue = 28;
		} else if (barbedValue.equals("14x14")) {
			divValue = 36;
		}
		Double barb = t_len / divValue;
		Double bundle=barb/30;
		int brace = 0;
		if (supportValue.equals("corners")) {
			brace = 8 + gateValue*2;
		} else {
			brace = poles.intValue() / Integer.parseInt(supportValue) * 2 + gateValue * 2;
		}
		Double binding=0.1*barb;
		Double days=(l3)/(520);
		Double cost_l=poles*60;
		Double cost_b=barb*70;
		Double cost_o=brace*180+binding*70+poles*180;
		Double cost_t=cost_l+ cost_b+ cost_o;
		
//		new AlertDialog.Builder(this)
//				.setTitle("Final Sumation")
//				.setMessage(
//						"COST : " + cost_t.intValue() + "        Days :" + Math.ceil(days)
//								+ "         Bundles : " + bundle.intValue())
//				.setPositiveButton("Yes",
//						new DialogInterface.OnClickListener() {
//							public void onClick(DialogInterface dialog,
//									int which) {
//							}
//						}).show();
		Intent i = new Intent(StandardActivity.this, ResultsActivity.class);
		i.putExtra("cost", Integer.toString(cost_t.intValue()));
    	i.putExtra("bundle",  Integer.toString(bundle.intValue()));
    	i.putExtra("days", Integer.toString(days.intValue()));
		startActivity(i);
	}

	public void onClick(View v) {
		if (v.getId() == R.id.button_done) {
			process();
		}
	}
}
