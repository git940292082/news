package com.news.news.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.xutils.x;

import com.news.news.entity.news.Toutiao;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class ImgPageAdapter extends PagerAdapter {
	List<Toutiao> lists;
	private Context context;
	private List<ImageView> mImageViewCacheList;
	protected ImageCycleViewListener mImageCycleViewListener;
	private ViewPager mAdvPager;
	private boolean isStop;
	private TextView tv;
	public ImgPageAdapter(ViewPager pager,Context context ,List<Toutiao> lists,TextView textView,ImageCycleViewListener mImageCycleViewListener) {
		// TODO Auto-generated constructor stub
		mAdvPager=pager;
		this.context=context;
		this.lists=lists;
		this.tv=textView;
		this.mImageCycleViewListener=mImageCycleViewListener;
		mImageViewCacheList=new ArrayList<ImageView>();
		startImageTimerTask();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}
	@Override
	public Object instantiateItem(ViewGroup container, final int position) {
		// TODO Auto-generated method stub
		final int index=position%lists.size();
        String imageUrl = lists.get(index).getThumbnail_pic_s02();
        String title = lists.get(index).getTitle();
        Log.i("imageUrl",imageUrl+title+(index));
        tv.setText(title);
        ImageView imageView = null;
        if (mImageViewCacheList.isEmpty()) {
                imageView = new ImageView(context);
                imageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
               //之前设置图片点击监听写在了这里�?�是错误�?
                //test
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                
        } 
        else {
                imageView = mImageViewCacheList.remove(0);
        }
   // 设置图片点击监听
        imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                        mImageCycleViewListener.onImageClick(lists.get(index+1));
                }
        });
        imageView.setTag(imageUrl);
        container.addView(imageView);
        x.image().bind(imageView, imageUrl);
        return imageView;
	}
	/**
	 * 轮播控件的监听事�?
	 * 
	 * @author minking
	 */
	public static interface ImageCycleViewListener {

		/**
		 * 单击图片事件
		 * 
		 * @param position
		 * @param imageView
		 */
		public void onImageClick(Toutiao toutiao);
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		ImageView view = (ImageView) object;
		mAdvPager.removeView(view);
		mImageViewCacheList.add(view);

	}
	private void startImageTimerTask() {
		// 图片滚动
		mHandler.postDelayed(mImageTimerTask, 3000);
	}

	/**
	 * 停止图片滚动任务
	 */
	public void stopImageTimerTask() {
		isStop=true;
		mHandler.removeCallbacks(mImageTimerTask);
	}

	private Handler mHandler = new Handler();

	/**
	 * 图片自动轮播Task
	 */
	private Runnable mImageTimerTask = new Runnable() {
		@Override
		public void run() {
			if (lists != null) {
				mAdvPager.setCurrentItem(mAdvPager.getCurrentItem()+1);
				if(!isStop){  //if  isStop=true   //当你�?出后 要把这个给停下来 不然 这个�?直存�? 就一直在后台循环 
					mHandler.postDelayed(mImageTimerTask, 3000);
				}
			}
		}
	};

}
