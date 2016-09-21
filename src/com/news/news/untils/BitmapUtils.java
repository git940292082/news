package com.news.news.untils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import com.news.news.app.App;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.ImageView;
public class BitmapUtils  {
	public static Map<ImageView, AsyncTask<String, String, Bitmap>> mapAsy=new HashMap<ImageView, AsyncTask<String,String,Bitmap>>();
	public static void loadBitmap(final String url,final ImageView imgBg){
		AsyncTask<String, String, Bitmap> task = mapAsy.get(imgBg);
		if(task!=null){
			Log.i("123", "取消取消取消取消取消取消取消取消取消取消取消取消取消取消取消取消取消");
			task.cancel(true);
		}
		task = new AsyncTask<String, String, Bitmap>(){
			protected Bitmap doInBackground(String... params) {
				try {
					//先从文件缓存中找，是否已经下载过
					String cacheName	 =url.substring(url.lastIndexOf("/"));
					File file = new File(App.context.getCacheDir(), "images"+cacheName);
					Bitmap b = loadBitmap(file);
					if(b!=null){ //文件里有
						Log.i("123", "从文件中读取");
						return b;
					}
					InputStream is = HttpUntils.getInputStream(url);
					b=BitmapFactory.decodeStream(is);
					//把图片存入文件缓存
					saveBitmap(b, file);
					Log.i("123", "下载的");
					return b;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
			protected void onPostExecute(Bitmap result) {
				if(result!=null){
					imgBg.setImageBitmap(result);
				}
			}
		};
		task.execute();
		mapAsy.put(imgBg, task);
	}
	
	public static void saveBitmap(Bitmap b,File file) throws Exception{
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		FileOutputStream out=new FileOutputStream(file);
		b.compress(CompressFormat.JPEG, 100, out);

	}

	public static Bitmap loadBitmap(File file) {
		if(!file.exists()){
			return null;
		}
		Bitmap b=BitmapFactory.decodeFile(file.getAbsolutePath());
		return b;
	}
}
