package com.liwei.uiversion1;

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

/**
 * @Description:����ܽ��棨ҳ��ײ��ĸ���������
 * @author:liwei
 * @time:2015-2-5 ����7:25:36
 */
public class MainFrame extends ActivityGroup {

	private LinearLayout doctorBtn, pharmacistBtn, stateBtn, setBtn;
	private TextView mMyBottemSearchTxt, mMyBottemTuanTxt, mMyBottemMyTxt,
			mMyBottemMoreTxt;
	private List<View> list = new ArrayList<View>();// �൱������Դ
	private View view = null;
	private View view1 = null;
	private View view2 = null;
	private View view3 = null;
	private android.support.v4.view.ViewPager mViewPager;
	private PagerAdapter pagerAdapter = null;// ����Դ��viewpager֮�������

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_frame);
		initView();
	}

	// ��ʼ���ؼ�
	private void initView() {
		mViewPager = (ViewPager) findViewById(R.id.FramePager);
		// ������linearlayoutΪ��ť���õĿؼ�
		doctorBtn = (LinearLayout) findViewById(R.id.MyBottemDoctorBtn);
		pharmacistBtn = (LinearLayout) findViewById(R.id.MyBottemPharmacistBtn);
		stateBtn = (LinearLayout) findViewById(R.id.MyBottemStateBtn);
		setBtn = (LinearLayout) findViewById(R.id.MyBottemSetBtn);

		// ����linearlayout�е�textview
		mMyBottemSearchTxt = (TextView) findViewById(R.id.MyBottemSearchTxt);
		mMyBottemTuanTxt = (TextView) findViewById(R.id.MyBottemTuanTxt);
		mMyBottemMyTxt = (TextView) findViewById(R.id.MyBottemMyTxt);
		mMyBottemMoreTxt = (TextView) findViewById(R.id.MyBottemMoreTxt);
		createView();
		// дһ���ڲ���pageradapter
		pagerAdapter = new PagerAdapter() {
			// �ж��ٴ���ӵ�view��֮ǰ��view �Ƿ���ͬһ��view
			// arg0����ӵ�view��arg1֮ǰ��
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			// ��������Դ����
			@Override
			public int getCount() {
				return list.size();
			}

			// ���ٱ������ߵ�view
			/**
			 * ViewGroup ���������ǵ�viewpager �൱��activitygroup���е�view������ ���֮ǰ���Ƴ���
			 * position �ڼ������� Object ���Ƴ���view
			 * */
			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				// �Ƴ�view
				container.removeView(list.get(position));
			}

			/**
			 * instantiateItem viewpagerҪ��ʵ��view ViewGroup viewpager position
			 * �ڼ�������
			 * */
			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				// ��ȡview��ӵ��������У�������
				View v = list.get(position);
				container.addView(v);
				return v;
			}
		};
		// �������ǵ�adapter
		mViewPager.setAdapter(pagerAdapter);

		MyBtnOnclick mytouchlistener = new MyBtnOnclick();
		doctorBtn.setOnClickListener(mytouchlistener);
		pharmacistBtn.setOnClickListener(mytouchlistener);
		stateBtn.setOnClickListener(mytouchlistener);
		setBtn.setOnClickListener(mytouchlistener);

		// ����viewpager�����л�����,����viewpager�л��ڼ��������Լ�������
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			// arg0 ��ȡ viewpager����Ľ����л����ڼ�����
			@Override
			public void onPageSelected(int arg0) {
				// �������ť��ʽ
				initBottemBtn();
				// ���ն�Ӧ��view��tag���жϵ����л����ĸ����档
				// ���Ķ�Ӧ��button״̬
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
			 * ����ҳ�滬���� arg0 ��ʾ��ǰ������view arg1 ��ʾ�����İٷֱ� arg2 ��ʾ�����ľ���
			 * */
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			/**
			 * ��������״̬ arg0 ��ʾ���ǵĻ���״̬ 0:Ĭ��״̬ 1:����״̬ 2:����ֹͣ
			 * */
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

	}

	// ��viewpager����Ҫ��ʾ��viewʵ�������������Ұ���ص�view��ӵ�һ��list����
	private void createView() {
		view = this
				.getLocalActivityManager()
				.startActivity("search",
						new Intent(MainFrame.this, DoctorList.class))
				.getDecorView();
		// ������������button����ʽ�ı�־
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
	 * ��linearlayout��Ϊ��ť�ļ����¼�
	 * */
	private class MyBtnOnclick implements View.OnClickListener {
		@Override
		public void onClick(View arg0) {
			int mBtnid = arg0.getId();
			switch (mBtnid) {
			case R.id.MyBottemDoctorBtn:
				// //�������ǵ�viewpager��ת�Ǹ�����0������������ǵ�list���,�൱��list������±�
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
	 * ��ʼ���ؼ�����ɫ
	 * */
	private void initBottemBtn() {
		mMyBottemSearchTxt.setTextColor(Color.parseColor("#000000"));
		mMyBottemTuanTxt.setTextColor(Color.parseColor("#000000"));
		mMyBottemMyTxt.setTextColor(Color.parseColor("#000000"));
		mMyBottemMoreTxt.setTextColor(Color.parseColor("#000000"));
	}
	
	/**
	 * ���ذ�ť�ļ���������ѯ���û��Ƿ��˳�����
	 * */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				Builder builder = new Builder(MainFrame.this);
				builder.setTitle("��ʾ");
				builder.setMessage("��ȷ��Ҫ�˳���");
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
				builder.setPositiveButton("ȡ��", dialog);
				builder.setNegativeButton("ȷ��", dialog);
				AlertDialog alertDialog = builder.create();
				alertDialog.show();

			}
		}
		return false;
	}
}
