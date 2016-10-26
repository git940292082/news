package com.news.news.fragment;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.news.news.R;
import com.news.news.activity.LoginActivity;
import com.news.news.activity.RegisterActivity;
import com.news.news.app.App;
import com.news.news.entity.User;
import com.news.news.ui.CircleImageView;
import com.news.news.untils.BitmapUtils;
import com.news.news.activity.CollectionActivity;
import com.news.news.adapter.CollectionAdapter;
import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class FramMine extends Fragment implements OnClickListener{
	private View view;
	private User mi_user;
	@ViewInject(R.id.btn_shift_nightmode)
	ToggleButton tB;
	@ViewInject(R.id.btn_login_mine)
	Button btn_login_mine;
	@ViewInject(R.id.btn_regist_mine)
	Button btn_regist_mine;
	@ViewInject(R.id.li_setting_mine)
	LinearLayout li_setting_mine;
	@ViewInject(R.id.tv_collection_mine)
	TextView collection;
	@ViewInject(R.id.Ci_image)
	CircleImageView Ci_image;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if(view==null){
			view=inflater.inflate(R.layout.fram_mine, null);
			Log.i("123", "FramMine");
			x.view().inject(this,view);
			//初始数据在这里写
			setListener();
		}else{
			((ViewGroup) view.getParent()).removeView(view); 
		}
		SetClick();
		return view;
	}
	private void SetClick() {
		btn_login_mine.setOnClickListener(this);
		li_setting_mine.setOnClickListener(this);
		btn_regist_mine.setOnClickListener(this);
	}
	@Override
	public void onResume() {
		super.onResume();
		mi_user=App.app_user;
		String name=mi_user.getName();
		String image=mi_user.getIcon();
		if (!(name.equals(""))) {
			btn_login_mine.setText(name);
			btn_login_mine.setTextColor(Color.BLACK);
			btn_regist_mine.setVisibility(View.GONE);
			btn_login_mine.setClickable(false);
		}
		if (!(image.equals(""))) {
			BitmapUtils bitmapUtils=new BitmapUtils();
			Ci_image.setImageBitmap(bitmapUtils.StringToIcon(image));
		}
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_login_mine:
			startActivity(new Intent(getActivity(),LoginActivity.class));
			break;
		case R.id.li_setting_mine:
			startActivity(new Intent(getActivity(),LoginActivity.class));
			break;
		case R.id.btn_regist_mine:
			startActivity(new Intent(getActivity(),RegisterActivity.class));
			break;
		default:
			break;
		}
	}
	private void setListener() {
		// TODO Auto-generated method stub
		collection.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(),CollectionActivity.class);
				startActivity(intent);
			}
		});
	}
	
}
