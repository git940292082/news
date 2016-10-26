package com.news.news.adapter.Holder;

import inject.ZRView;

import com.news.news.R;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class NewItemView {
	@ZRView(R.id.iv_news_item_image)
	public ImageView ivNewsimage;
	@ZRView(R.id.tv_news_item_title)
	public TextView tvTitle;
	@ZRView(R.id.tv_news_item_source)
	public TextView tvSource;
	@ZRView(R.id.tv_news_item_comment)
	public TextView tvComment;
	@ZRView(R.id.news_item_fenxiang)
	public ImageButton btnFenxiang;
}
