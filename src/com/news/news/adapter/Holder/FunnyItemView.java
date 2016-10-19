package com.news.news.adapter.Holder;

import com.news.news.R;

import inject.ZRView;
import android.widget.ImageView;
import android.widget.TextView;

public class FunnyItemView {
	@ZRView(R.id.tv_title_item_funny)
	public TextView tvTitle;
	@ZRView(R.id.iv_funny_pic_item_funny)
	public ImageView ivFunnyPic;
}
