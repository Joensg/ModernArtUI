package com.example.modernartui1;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private static final String[] RED = {"#ffff0000","#ffff2000","#ffff4000","#ffff5000","#ffff6000","#ffff9000","#ffffb000","#ffffd000","#ffffe000","#ffffff00"};
	private static final String[] GREEN = {"#ff00ff00","#ff20fd00","#ff30fb00","#ff40fa00","#ff50f800","#ff60f600","#ff70f500","#ff80f300","#ff90f200","#ffa0f100"};
	private static final String[] BLUE = {"#ff0000ff","#ff0120ff","#ff0340ff","#ff0560ff","#ff0780ff","#ff09a0ff","#ff0ab0ff","#ff0cd0ff","#ff0de0ff","#ff0ef0ff"};
	private static final String[] YELLOW = {"#ffffff00","#ffeeee0e","#ffdddd0d","#ffcccc0c","#ffbbbb0b","#ffaaaa0a","#ff999909","#ff888808","#ff777707","#ff555505"};

	
	private List<ColorComponent> colorComponents = new ArrayList<ColorComponent>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        colorComponents.add(new ColorComponent(RED));
		colorComponents.add(new ColorComponent(GREEN));
		colorComponents.add(new ColorComponent(BLUE));
		colorComponents.add(new ColorComponent(YELLOW));
		
        updateColors(0);

		SeekBar seekBar = (SeekBar) findViewById(R.id.changeColor);
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				updateColors(progress);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
		});
	}

	private void updateColors(int sliderValue) {
		findViewById(R.id.button1Left).setBackgroundColor(colorComponents.get(0).getColor(sliderValue/10));
		findViewById(R.id.button2Left).setBackgroundColor(colorComponents.get(1).getColor(sliderValue/10));
		findViewById(R.id.button1Right).setBackgroundColor(colorComponents.get(2).getColor(sliderValue/10));
		findViewById(R.id.button3Right).setBackgroundColor(colorComponents.get(3).getColor(sliderValue/10));	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_more_info) {
			showDialog();
		}
		return super.onOptionsItemSelected(item);
	}

	private void showDialog() {
		View dialogView = this.getLayoutInflater().inflate(R.layout.dialog, null);
		final AlertDialog dialog = new AlertDialog.Builder(this).setView(dialogView).create();

		TextView visitMoma = (TextView) dialogView.findViewById(R.id.visit_moma);
		visitMoma.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				dialog.dismiss();
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse("http://www.moma.org/m#home"));
				startActivity(i);
			}
		});

		TextView notNow = (TextView) dialogView.findViewById(R.id.not_now);
		notNow.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				dialog.dismiss();
			}
		});

		dialog.show();
	}
}