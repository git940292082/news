package com.news.news.adapter;

import inject.I;

import java.util.List;
import java.util.Random;

import org.xutils.x;
import org.xutils.image.ImageOptions;

import com.news.news.R;
import com.news.news.adapter.Holder.FunnyItemView;
import com.news.news.adapter.Holder.NewItemView;
import com.news.news.adapter.Holder.VideoItemView;
import com.news.news.entity.FunnyItem;
import com.news.news.entity.Video;
import com.news.news.entity.news.Toutiao;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
public class CollectionAdapter extends MyAdapter<Object>{
	Handler handler=new Handler();
	public static final int TPYE_VIDEO=0;
	public static final int TPYE_NEWS=1;
	public static final int TPYE_FUNNY=2;
	public CollectionAdapter(Context context, List<Object> data) {

		super(context, data);
		// TODO Auto-generated constructor stub
	}
	public View getLayout(int i,Object item){
		View view=getInflater().inflate(R.layout.news_item, null);
		return view;
	}
	@Override
	public View getView(int position, View layout, ViewGroup parent) {
		Object object = getItem(position);
//		TODO Auto-generated method stub
		if(object instanceof Toutiao){
			//新闻模板
			Toutiao toutiao=(Toutiao) object;
			NewItemView newItemView=null;
			layout=getInflater().inflate(R.layout.news_item, null);
			newItemView=new NewItemView();
			I.invect(newItemView, layout);
			loadNew(newItemView,toutiao);
		}else if(object instanceof FunnyItem){
			//搞笑模板
			FunnyItem funnyItem=(FunnyItem) object;
			FunnyItemView funnyItemView=null;
			layout=getInflater().inflate(R.layout.item_funny_fragment_list, null);
			funnyItemView =new FunnyItemView();
			I.invect(funnyItemView, layout);
			loadFunny();
		}else if(object instanceof Video){
			//视频模板
			Video video=(Video) object;
			VideoItemView videoItemView=null;
			layout=getInflater().inflate(R.layout.video_item, null);
			videoItemView=new VideoItemView();
			I.invect(videoItemView, layout);
			VideoLoad.load(videoItemView, video,getContext());
			videoItemView.btnCollection.setVisibility(View.INVISIBLE);
		}
		
		return layout;
	}
	private void loadViewo() {
		// TODO Auto-generated method stub

	}
	private void loadFunny() {
		// TODO Auto-generated method stub

	}
	private void loadNew(NewItemView holder,Toutiao toutiao) {
		// TODO Auto-generated method stub
		holder.tvTitle.setText(toutiao.getTitle());
		holder.tvSource.setText(toutiao.getAuthor_name());
		holder.tvComment.setText("阅读：" + new Random().nextInt(1000));
		ImageOptions options=new ImageOptions.Builder().setRadius(30).build();
		if (toutiao.getThumbnail_pic_s() != null) {
			x.image().bind(holder.ivNewsimage, toutiao.getThumbnail_pic_s(),options);
		} else {
			holder.ivNewsimage.setImageResource(R.drawable.ic_launcher);
		}
	}
	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 3;
	}
	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		Object object=getItem(position);
		if(object instanceof Video){
			return TPYE_VIDEO;
		}else if(object instanceof FunnyItem){
			return TPYE_FUNNY;
		}else if(object instanceof Toutiao){
			return TPYE_NEWS;
		}
		return super.getItemViewType(position);
	}
}
