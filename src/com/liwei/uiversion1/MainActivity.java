package com.liwei.uiversion1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @Description:主界面（登录界面）
 * @author:liwei
 * @time:2015-2-5 下午7:22:09
 */
public class MainActivity extends Activity {

	private Button loginBtn;
	private TextView mLogin_wangjimima, mLogin_zhuce;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题栏
		setContentView(R.layout.login);

		loginBtn = (Button) findViewById(R.id.Login_OK);
		mLogin_wangjimima = (TextView) findViewById(R.id.Login_wangjimima);
		mLogin_zhuce = (TextView) findViewById(R.id.Login_zhuce);

		loginBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MainActivity.this, MainFrame.class);

				startActivity(intent);
			}
		});

		mLogin_zhuce.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, Register.class);

				startActivity(intent);
			}
		});

		mLogin_wangjimima.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "忘记密码", 1).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
