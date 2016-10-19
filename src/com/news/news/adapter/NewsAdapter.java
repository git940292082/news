package com.news.news.adapter;

import inject.I;

import java.util.List;
import java.util.Random;

import org.xutils.x;
import org.xutils.image.ImageOptions;

import com.news.news.R;
import com.news.news.adapter.Holder.NewItemView;
import com.news.news.entity.news.Toutiao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class NewsAdapter extends BaseAdapter {
	Context context;
	List<Toutiao> toutiao;
	LayoutInflater inflater;

	public NewsAdapter(Context context, List<Toutiao> toutiao) {
		super();
		this.context = context;
		this.toutiao = toutiao;
		this.inflater = LayoutInflater.from(context);
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
		Toutiao toutiaos = toutiao.get(position);
		holder.tvTitle.setText(toutiaos.getTitle());
		holder.tvSource.setText(toutiaos.getAuthor_name());
		holder.tvComment.setText("ÔÄ¶Á£º" + new Random().nextInt(1000));
		ImageOptions options=new ImageOptions.Builder().setRadius(30).build();
		if (toutiaos.getThumbnail_pic_s() != null) {
			x.image().bind(holder.ivNewsimage, toutiaos.getThumbnail_pic_s(),options);
		} else {
			holder.ivNewsimage.setImageResource(R.drawable.ic_launcher);
		}

		return convertView;
	}

}
