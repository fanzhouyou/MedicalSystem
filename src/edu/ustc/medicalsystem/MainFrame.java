package edu.ustc.medicalsystem;

import java.util.ArrayList;
import java.util.List;


import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainFrame extends ActivityGroup {

	private LinearLayout doctorBtn, pharmacistBtn, stateBtn, setBtn;
	private TextView mMyBottemSearchTxt, mMyBottemTuanTxt, mMyBottemMyTxt,
			mMyBottemMoreTxt;
	private List<View> list = new ArrayList<View>();// 相当于数据源
	private View view = null;
	private View view1 = null;
	private View view2 = null;
	private View view3 = null;
	private android.support.v4.view.ViewPager mViewPager;
	private PagerAdapter pagerAdapter = null;// 数据源和viewpager之间的桥梁

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_frame);
		initView();
	}

	// 初始化控件
	private void initView() {
		mViewPager = (ViewPager) findViewById(R.id.FramePager);
		// 查找以linearlayout为按钮作用的控件
		doctorBtn = (LinearLayout) findViewById(R.id.MyBottemDoctorBtn);
		pharmacistBtn = (LinearLayout) findViewById(R.id.MyBottemPharmacistBtn);
		stateBtn = (LinearLayout) findViewById(R.id.MyBottemStateBtn);
		setBtn = (LinearLayout) findViewById(R.id.MyBottemSetBtn);

		// 查找linearlayout中的textview
		mMyBottemSearchTxt = (TextView) findViewById(R.id.MyBottemSearchTxt);
		mMyBottemTuanTxt = (TextView) findViewById(R.id.MyBottemTuanTxt);
		mMyBottemMyTxt = (TextView) findViewById(R.id.MyBottemMyTxt);
		mMyBottemMoreTxt = (TextView) findViewById(R.id.MyBottemMoreTxt);
		createView();
		// 写一个内部类pageradapter
		pagerAdapter = new PagerAdapter() {
			// 判断再次添加的view和之前的view 是否是同一个view
			// arg0新添加的view，arg1之前的
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			// 返回数据源长度
			@Override
			public int getCount() {
				return list.size();
			}

			// 销毁被滑动走的view
			/**
			 * ViewGroup 代表了我们的viewpager 相当于activitygroup当中的view容器， 添加之前先移除。
			 * position 第几条数据 Object 被移出的view
			 * */
			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				// 移除view
				container.removeView(list.get(position));
			}

			/**
			 * instantiateItem viewpager要现实的view ViewGroup viewpager position
			 * 第几条数据
			 * */
			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				// 获取view添加到容器当中，并返回
				View v = list.get(position);
				container.addView(v);
				return v;
			}
		};
		// 设置我们的adapter
		mViewPager.setAdapter(pagerAdapter);

		MyBtnOnclick mytouchlistener = new MyBtnOnclick();
		doctorBtn.setOnClickListener(mytouchlistener);
		pharmacistBtn.setOnClickListener(mytouchlistener);
		stateBtn.setOnClickListener(mytouchlistener);
		setBtn.setOnClickListener(mytouchlistener);

		// 设置viewpager界面切换监听,监听viewpager切换第几个界面以及滑动的
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			// arg0 获取 viewpager里面的界面切换到第几个的
			@Override
			public void onPageSelected(int arg0) {
				// 先清除按钮样式
				initBottemBtn();
				// 按照对应的view的tag来判断到底切换到哪个界面。
				// 更改对应的button状态
				int flag = (Integer) list.get((arg0)).getTag();
				if (flag == 0) {
					mMyBottemSearchTxt.setTextColor(Color.parseColor("#FF8C00"));
				} else if (flag == 1) {
					mMyBottemTuanTxt.setTextColor(Color.parseColor("#FF8C00"));
				} else if (flag == 2) {
					mMyBottemMyTxt.setTextColor(Color.parseColor("#FF8C00"));
				} else if (flag == 3) {
					mMyBottemMoreTxt.setTextColor(Color.parseColor("#FF8C00"));
				}
			}

			/**
			 * 监听页面滑动的 arg0 表示当前滑动的view arg1 表示滑动的百分比 arg2 表示滑动的距离
			 * */
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			/**
			 * 监听滑动状态 arg0 表示我们的滑动状态 0:默认状态 1:滑动状态 2:滑动停止
			 * */
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

	}

	// 把viewpager里面要显示的view实例化出来，并且把相关的view添加到一个list当中
	private void createView() {
		view = this
				.getLocalActivityManager()
				.startActivity("search",
						new Intent(MainFrame.this, DoctorList.class))
				.getDecorView();
		// 用来更改下面button的样式的标志
		view.setTag(0);
		list.add(view);

		view1 = MainFrame.this
				.getLocalActivityManager()
				.startActivity("tuan",
						new Intent(MainFrame.this, PharmacistList.class))
				.getDecorView();
		view1.setTag(1);
		list.add(view1);

		view2 = MainFrame.this.getLocalActivityManager()
				.startActivity("my", new Intent(MainFrame.this, State.class))
				.getDecorView();
		view2.setTag(2);
		list.add(view2);

		view3 = MainFrame.this.getLocalActivityManager()
				.startActivity("more", new Intent(MainFrame.this, Set.class))
				.getDecorView();
		view3.setTag(3);
		list.add(view3);
	}

	/**
	 * 用linearlayout作为按钮的监听事件
	 * */
	private class MyBtnOnclick implements View.OnClickListener {
		@Override
		public void onClick(View arg0) {
			int mBtnid = arg0.getId();
			switch (mBtnid) {
			case R.id.MyBottemDoctorBtn:
				// //设置我们的viewpager跳转那个界面0这个参数和我们的list相关,相当于list里面的下标
				mViewPager.setCurrentItem(0);
				initBottemBtn();
				mMyBottemSearchTxt.setTextColor(Color.parseColor("#FF8C00"));
				break;
			case R.id.MyBottemPharmacistBtn:
				mViewPager.setCurrentItem(1);
				initBottemBtn();
				mMyBottemTuanTxt.setTextColor(Color.parseColor("#FF8C00"));
				break;
			case R.id.MyBottemStateBtn:
				mViewPager.setCurrentItem(2);
				initBottemBtn();
				mMyBottemMyTxt.setTextColor(Color.parseColor("#FF8C00"));
				break;
			case R.id.MyBottemSetBtn:
				mViewPager.setCurrentItem(3);
				initBottemBtn();
				mMyBottemMoreTxt.setTextColor(Color.parseColor("#FF8C00"));
				break;
			}

		}

	}

	/**
	 * 初始化控件的颜色
	 * */
	private void initBottemBtn() {
		mMyBottemSearchTxt.setTextColor(Color.parseColor("#000000"));
		mMyBottemTuanTxt.setTextColor(Color.parseColor("#000000"));
		mMyBottemMyTxt.setTextColor(Color.parseColor("#000000"));
		mMyBottemMoreTxt.setTextColor(Color.parseColor("#000000"));
	}
	
	/**
	 * 返回按钮的监听，用来询问用户是否退出程序
	 * */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				Builder builder = new Builder(MainFrame.this);
				builder.setTitle("提示");
				builder.setMessage("你确定要退出吗？");
				builder.setIcon(R.drawable.ic_launcher);

				DialogInterface.OnClickListener dialog = new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						if (arg1 == DialogInterface.BUTTON_POSITIVE) {
							arg0.cancel();
						} else if (arg1 == DialogInterface.BUTTON_NEGATIVE) {
							MainFrame.this.finish();
						}
					}
				};
				builder.setPositiveButton("取消", dialog);
				builder.setNegativeButton("确定", dialog);
				AlertDialog alertDialog = builder.create();
				alertDialog.show();

			}
		}
		return false;
	}
}
