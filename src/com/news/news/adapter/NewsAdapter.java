package com.news.news.adapter;

import inject.I;

import java.util.List;
import java.util.Random;

import org.xutils.x;
import org.xutils.image.ImageOptions;

import com.news.news.R;
import com.news.news.adapter.Holder.NewItemView;
import com.news.news.app.App;
import com.news.news.entity.news.Toutiao;
import com.tencent.a.a.a.c;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

public class NewsAdapter extends BaseAdapter {
	Context context;
	List<Toutiao> toutiao;
	LayoutInflater inflater;
	private Tencent mTencent;

	public NewsAdapter(Context context, List<Toutiao> toutiao) {
		super();
		this.context = context;
		this.toutiao = toutiao;
		this.inflater = LayoutInflater.from(context);
		mTencent = Tencent.createInstance(App.QQID,context);
	}

	@Override
	public int getCount() {
		return toutiao.size();
	}

	@Override
	public Toutiao getItem(int position) {
		return toutiao.get(position);
	}

	@Override
	public long getItemId(int position) {
		return toutiao.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		NewItemView holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.news_item, null);
			holder = new NewItemView();
			I.invect(holder, convertView);
			convertView.setTag(holder);
			
		}
		holder = (NewItemView) convertView.getTag();
		final Toutiao toutiaos = toutiao.get(position);
		holder.tvTitle.setText(toutiaos.getTitle());
		holder.tvSource.setText(toutiaos.getAuthor_name());
		holder.tvComment.setText("阅读：" + new Random().nextInt(1000));
		ImageOptions options=new ImageOptions.Builder().setRadius(30).build();
		if (toutiaos.getThumbnail_pic_s() != null) {
			x.image().bind(holder.ivNewsimage, toutiaos.getThumbnail_pic_s(),options);
		} else {
			holder.ivNewsimage.setImageResource(R.drawable.ic_launcher);
		}
		holder.btnFenxiang.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				fenxiang(toutiaos);
			}
		});
		return convertView;
	}
	public void fenxiang(Toutiao t) {
		final Bundle params = new Bundle();
		params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
		params.putString(QQShare.SHARE_TO_QQ_TITLE, "牛逼新闻");//标题
		params.putString(QQShare.SHARE_TO_QQ_SUMMARY, t.getTitle()+"\n"+t.getAuthor_name());
		params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, t.getUrl());
		params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,t.getThumbnail_pic_s());
		IUiListener myListener=new IUiListener() {
			@Override
			public void onError(UiError arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(context, arg0.toString(), 0).show();
			}
			@Override
			public void onComplete(Object arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "分享成功", 0).show();
			}
			
			@Override
			public void onCancel() {
				// TODO Auto-generated method stub
				Toast.makeText(context, "取消分享", 0).show();
			}
		};
		mTencent.shareToQQ((Activity) context, params, myListener);
	}
}
