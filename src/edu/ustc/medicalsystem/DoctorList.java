package edu.ustc.medicalsystem;

import com.liwei.uiversion1.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * @Description:TODO
 * @author:liwei
 * @time:2015-2-5 обнГ9:56:02
 */
public class DoctorList extends Activity {
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_doctorlist);
		
	}

}
