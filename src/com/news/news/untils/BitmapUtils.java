package com.news.news.untils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.news.news.adapter.Holder.VideoItemView;
import com.news.news.app.App;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.widget.RelativeLayout.LayoutParams;
public class BitmapUtils  {
	public static void loadBitmap(final String url,final VideoItemView holder){ 
		String cacheName =url.substring(url.lastIndexOf("/"));
		final File file = new File(App.context.getCacheDir(), "images"+cacheName);
		Bitmap bitmap=loadBitmap(file);
		if(bitmap!=null){
			holder.imgBg.setImageBitmap(bitmap);
			LayoutParams params=new LayoutParams(bitmap.getWidth(),bitmap.getHeight());
			holder.vvVideo.setLayoutParams(params);
			holder.vvVideo.setLayoutParams(holder.imgBg.getLayoutParams());
			return;
		}
		ImageRequest imageRequest = new ImageRequest(  
				url,  
				new Response.Listener<Bitmap>() {  
					@Override  
					public void onResponse(Bitmap response) {  
						try {
							saveBitmap(response, file);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						holder.imgBg.setImageBitmap(response);  
						holder.vvVideo.setLayoutParams(holder.imgBg.getLayoutParams());
						LayoutParams params=new LayoutParams(response.getWidth(),response.getHeight());
						holder.vvVideo.setLayoutParams(params);
						holder.vvVideo.setLayoutParams(holder.imgBg.getLayoutParams());
					}  
				}, 0, 0, Config.RGB_565, new Response.ErrorListener() {  
					@Override  
					public void onErrorResponse(VolleyError error) {  
					}  
				}); 
		App.getmQueue().add(imageRequest);

	}


	public static void saveBitmap(Bitmap b,File file) throws Exception{
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		FileOutputStream out=new FileOutputStream(file);
		b.compress(CompressFormat.JPEG, 100, out);

	}
	/**  
     * 图片转成string  
     *   
     * @param bitmap  
     * @return  
     */  
    public static String IconToString(Bitmap bitmap)  
    {  
        ByteArrayOutputStream baos = new ByteArrayOutputStream();// outputstream  
        bitmap.compress(CompressFormat.PNG, 100, baos);  
        byte[] appicon = baos.toByteArray();// 转为byte数组  
        return Base64.encodeToString(appicon, Base64.DEFAULT);  
  
    }  
  
    /**  
     * string转成bitmap  
     *   
     * @param st  
     */  
    public static Bitmap StringToIcon(String st)  
    {  
        // OutputStream out;  
        Bitmap bitmap = null;  
        try  
        {  
            // out = new FileOutputStream("/sdcard/aa.jpg");  
            byte[] bitmapArray;  
            bitmapArray = Base64.decode(st, Base64.DEFAULT);  
            bitmap =  
                    BitmapFactory.decodeByteArray(bitmapArray, 0,  
                            bitmapArray.length);  
            // bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);  
            return bitmap;  
        }  
        catch (Exception e)  
        {  
            return null;  
        }  
    }  
	public static Bitmap loadBitmap(File file) {
		if(!file.exists()){
			return null;
		}
		Bitmap b=BitmapFactory.decodeFile(file.getAbsolutePath());
		return b;
	}
}
