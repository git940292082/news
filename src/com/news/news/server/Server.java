package com.news.news.server;

import java.util.Random;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.news.news.R;
import com.news.news.activity.NewsWebActivity;
import com.news.news.entity.news.News;
import com.news.news.entity.news.Toutiao;
import com.news.news.model.NewsCallBack;
import com.news.news.model.impl.NewsModel;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.Notification.Builder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.util.Log;

public class Server extends Service {
Context context;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		context=this;
		new KeepAlive();
	}
	//伉柳淫
	public class KeepAlive extends Thread{
		boolean isRunning=true;
		NewsModel  model;
		protected Toutiao toutiao;
		public KeepAlive() {
			model=new NewsModel();
			
			this.start();
		}
		@Override
		public void run() {
			while(isRunning)
			{
				try {
					//System.currentTimeMillis()-lastSendTime>4*60*1000
					Log.i("容僕", "容僕容僕容僕容僕容僕容僕容僕容僕容僕容僕容僕容僕容僕容僕容僕容僕容僕容僕容僕容僕容僕容僕容僕容僕容僕容僕容僕容僕容僕");
					model.getToutiao("top", new NewsCallBack() {

						@Override
						public void onNewsLoader(Object object) {
							// TODO Auto-generated method stub
							try {
								News news=(News) object;
								toutiao = news.getResult().getData().get(new Random().nextInt(news.getResult().getData().size()));
								AsyncHttpClient client=new AsyncHttpClient();
								client.get(toutiao.getThumbnail_pic_s(), new AsyncHttpResponseHandler() {
									
									@Override
									public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
										// TODO Auto-generated method stub
										Bitmap bitmap = BitmapFactory.decodeByteArray(arg2, 0, arg2.length);
										msg(bitmap);
									}
									
									@Override
									public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
										// TODO Auto-generated method stub
										
									}
								});
							} catch (Exception e) {
								// TODO: handle exception
							}
						}
					});
					sleep(1000*60*30);
				} catch (Exception e) {

				}
				
			}
			
		}
		
		public void msg(Bitmap bitmap){
			final NotificationManager noMsg=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
			Builder builder =new Builder(context);
			Intent i=new Intent(context,NewsWebActivity.class);
			i.putExtra("news", toutiao);
			int flags=PendingIntent.FLAG_UPDATE_CURRENT;
			PendingIntent intent=PendingIntent.getActivity(context,0, i, flags);
			builder
			.setContentText(toutiao.getAuthor_name())
			.setContentTitle(toutiao.getTitle())
			.setSmallIcon(R.drawable.ic_launcher)
			.setLargeIcon(bitmap)
			.setTicker("嚔赤仟療")
			.setContentIntent(intent)
			.setAutoCancel(true);
			Notification n=builder.build();
			noMsg.notify(toutiao.hashCode(),n);
		}
	}
}
