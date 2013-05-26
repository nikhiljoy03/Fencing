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
import android.widget.EditText;
import android.widget.Spinner;

public class CustomActivity extends Activity implements OnClickListener {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_layout);
		getActionBar().setTitle("Standard Layout");
		getActionBar().setHomeButtonEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.activity_main, menu);
		MenuItem doneItem = menu.findItem(R.id.done);
		View view = doneItem.getActionView().findViewById(R.id.button_done);
		view.setOnClickListener(this);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {
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
		EditText area = (EditText) findViewById(R.id.areaid);
//		EditText len = (EditText) findViewById(R.id.len);
//		EditText width = (EditText) findViewById(R.id.wid);
		EditText barbePrice = (EditText) findViewById(R.id.barbed_price);
		EditText bracePrice = (EditText) findViewById(R.id.brace_price);
		EditText boltPrice = (EditText) findViewById(R.id.bolt_price);
		EditText polePrice = (EditText) findViewById(R.id.poledist_price);
		EditText labourCharges = (EditText) findViewById(R.id.labourCharges); 

		Spinner wireSpinner = (Spinner) findViewById(R.id.planets_spinner);
		Spinner unit = (Spinner) findViewById(R.id.spinner1);
		Spinner crossSpinner = (Spinner) findViewById(R.id.wires_spinner);
		Spinner barbedSpinner = (Spinner) findViewById(R.id.barbed_spinner);
		Spinner gateSpinner = (Spinner) findViewById(R.id.gates_spinner);
		Spinner poleSpinner = (Spinner) findViewById(R.id.pole_spinner);
		Spinner fenceSpinner = (Spinner) findViewById(R.id.fence_spinner);
		Spinner distanceSpinner = (Spinner) findViewById(R.id.dist_spinner);
		Spinner supportSpinner = (Spinner) findViewById(R.id.support_spinner);
		Spinner lineSpinner = (Spinner) findViewById(R.id.planets_spinner);
		Spinner labourSpinner = (Spinner) findViewById(R.id.number_spinner);

		int labourChargesValue = Integer.parseInt(labourCharges.getText().toString());
		String choice = unit.getSelectedItem().toString();
		int areaValue = Integer.parseInt(area.getText().toString());
//		int lenValue = Integer.parseInt(len.getText().toString());
//		int widthValue = Integer.parseInt(width.getText().toString());
		int wireValue = Integer.parseInt(wireSpinner.getSelectedItem()
				.toString());
		int barbedPriceValue = Integer
				.parseInt(barbePrice.getText().toString());
		int crossValue = Integer.parseInt(crossSpinner.getSelectedItem()
				.toString());
		String barbedValue = barbedSpinner.getSelectedItem().toString();
		int gateValue = Integer.parseInt(gateSpinner.getSelectedItem()
				.toString());
		String poleValue = poleSpinner.getSelectedItem().toString();
		int fenceValue = Integer.parseInt(fenceSpinner.getSelectedItem()
				.toString());
		int distanceValue = Integer.parseInt(distanceSpinner.getSelectedItem()
				.toString());
		String supportValue = supportSpinner.getSelectedItem().toString();
		int lineValue = Integer.parseInt(lineSpinner.getSelectedItem()
				.toString());
		int labourValue = Integer.parseInt(labourSpinner.getSelectedItem()
				.toString());
		int bracePriceValue = Integer.parseInt(bracePrice.getText().toString());
		int boltPriceValue = Integer.parseInt(boltPrice.getText().toString());
		int poleDistPriceValue = Integer.parseInt(polePrice.getText()
				.toString());

		Double unitTemp = new Double(3.281);
		if (choice.equals("feet"))
		{
			unitTemp = new Double(1);
		}
		Double l3 = (areaValue * unitTemp) - (gateValue * 6);
		
		Double l4 = l3 * wireValue;
		Double poles = l3 / (distanceValue + 0.328);
		Double l5 = Math
				.sqrt(((fenceValue * fenceValue) + (distanceValue * distanceValue)))
				* crossValue * (poles.intValue() - 1);
		Double tl = l4 + l5;
		int divValue = 20;
		if (barbedValue.equals("12x14")) {
			divValue = 28;
		} else if (barbedValue.equals("14x14")) {
			divValue = 36;
		}
		Double b = tl / divValue;
//		new AlertDialog.Builder(this)
//		.setTitle("Final Sumation")
//		.setMessage(
//				"gate : " + gateValue + "        area :" + Math.ceil(areaValue)
//						+ "         fance : " + fenceValue)
//		.setPositiveButton("Yes",
//				new DialogInterface.OnClickListener() {
//					public void onClick(DialogInterface dialog,
//							int which) {
//					}
//				}).show();
		Double bundles = b / 30;
		int support = 0;
		if (supportValue.equals("corners")) {
			support = 8 + gateValue*2;
		} else {
			support = poles.intValue() / Integer.parseInt(supportValue) * 2 + gateValue * 2;
		}
		Double jb = 0.1 * b;
		Double temp = (l3) / (int) (6.5 * labourValue * 8);
		Double days = temp;
		//Double cost_l = (double) (days * labourValue*labourChargesValue);
		Double cost_l = poles * labourChargesValue;
		Double cost_b = b * barbedPriceValue;
		Double cost_o = support * poleDistPriceValue + jb * boltPriceValue + poles
				* poleDistPriceValue;
		Double cost_t = cost_l + cost_b + cost_o;
		
		Intent i = new Intent(CustomActivity.this, ResultsActivity.class);
		i.putExtra("cost", Integer.toString(cost_t.intValue()));
    	i.putExtra("bundle",  Integer.toString(bundles.intValue()));
    	i.putExtra("days", Integer.toString(days.intValue()));
		startActivity(i);
    	
//		new AlertDialog.Builder(this)
//				.setTitle("Final Sumation")
//				.setMessage(
//						"COST : " + cost_t.intValue() + "        Days :" + Math.ceil(days)
//								+ "         Bundles : " + bundles.intValue())
//				.setPositiveButton("Yes",
//						new DialogInterface.OnClickListener() {
//							public void onClick(DialogInterface dialog,
//									int which) {
//							}
//						}).show();

	}

	public void onClick(View v) {
		if (v.getId() == R.id.button_done) {
			process();
		}
	}
}
