package edu.ustc.medicalsystem;

import edu.ustc.medicalsystem.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class Set extends Activity {

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_set);

	}

}
