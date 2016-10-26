package com.news.news.untils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by hfx on 15-1-28.
 */
public class PhotoUtil {

    public static final int SELECT_PIC = 2001;
    public static final int SELECT_PIC_KITKAT = 2002;
    private static final int ICON_SIZE = 96;
    public static final int CAMERA_WITH_DATAA = 3023;
    public static final String FOLDER_IMAGES_THUMBNAILS="/Yun/Images/Thumbnails/";//鑱婂ぉ鏀跺埌鐨勭缉鐣ュ浘
    public static final String FOLDER_IMAGES_ORIGINAL="/Yun/Images/Original/";//鑱婂ぉ鏀跺埌鐨勫師鍥�

    /**
     * 鎷嶇収閫夋嫨
     * @param activity
     */
    public static String doTakePhoto(Activity activity){
        String state = Environment.getExternalStorageState(); // 鍒ゆ柇鏄惁瀛樺湪sd鍗�
        if (state.equals(Environment.MEDIA_MOUNTED)) { // 璋冪敤绯荤粺鐨勭収鐩告満
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            String fileName = getFileName(activity);
//            String filePath = activity.getApplicationContext().getFilesDir().getAbsolutePath()+"/Yun/Images/"+fileName;
            String filePath = Environment.getExternalStorageDirectory()+"/Yun/Images/"+fileName;
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(filePath)));
            intent.putExtra("image_path",filePath);
            activity.startActivityForResult(intent, CAMERA_WITH_DATAA);
            return filePath;
        } else {
            Toast.makeText(activity, "璇锋鏌ユ墜鏈烘槸鍚︽湁sd鍗�", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    /**
     * 鎷嶇収浜х敓涓存椂鏂囦欢鍚�
     * @return
     */
    private static String getFileName(Activity activity) {
        String saveDir = Environment.getExternalStorageDirectory()+ "/Yun/Images";
        File dir = new File(saveDir);
        if (!dir.exists()) {
            dir.mkdirs(); // 鍒涘缓鏂囦欢澶�
        }
        return getRandomFileName();
    }

    /**
     * 鐢熸垚涓�涓殢鏈虹殑鏂囦欢鍚�
     * @return
     */
    public static String getRandomFileName() {
        String rel="";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date curDate = new Date(System.currentTimeMillis());
        rel = formatter.format(curDate);
        rel = rel+new Random().nextInt(1000);
        return rel;
    }

    /**
     * 浠庣浉鍐岄�夋嫨鍥剧墖(涓轰簡瑙ｅ喅4.4涓嶈兘杩斿洖path鐨勯棶棰�)
     * @param activity
     */
    public static void selectImgFromGallery(Activity activity){
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);//ACTION_OPEN_DOCUMENT
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/jpeg");
        if(android.os.Build.VERSION.SDK_INT >= 19){
            activity.startActivityForResult(intent, SELECT_PIC_KITKAT);
        }else{
            activity.startActivityForResult(intent, SELECT_PIC);
        }
    }

    /**
     * 璇诲彇鏈湴鍥剧墖
     * @param filePath
     * @return
     */
    public static Bitmap readBitmapFromPath(Activity context,String filePath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        options.inJustDecodeBounds = false;
        int be = calculateInSampleSize(context,outWidth,outHeight);
        options.inSampleSize = be;
        options.inPurgeable =true;
        options.inInputShareable = true;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        try {
            return BitmapFactory.decodeFile(filePath, options);
        }catch (OutOfMemoryError e){
            System.gc();
            try {
                options.inSampleSize = be+1;
                return BitmapFactory.decodeFile(filePath, options);

            }catch (OutOfMemoryError e2){
                return null;
            }
        }
    }

    /**
     * 璁＄畻鍥剧墖缂╂斁姣斾緥
     * @param outWidth
     * @param outHeight
     * @return
     */
    public static int calculateInSampleSize(Activity context,int outWidth,int outHeight){
        int screenWidth = context.getWindowManager().getDefaultDisplay().getWidth();
        int screenHeight = context.getWindowManager().getDefaultDisplay().getHeight();
        int be;
        if(outWidth>screenWidth || outHeight>1.5*screenHeight){
            int heightRatio = Math.round(((float) outHeight) / ((float) 1.5 * screenHeight));
            int widthRatio = Math.round(((float) outWidth) / ((float) screenWidth));
            int sample = heightRatio > widthRatio ? heightRatio : widthRatio;
            if (sample < 3)
                be = sample;
            else if (sample < 6.5)
                be = 4;
            else if (sample < 8)
                be = 8;
            else
                be = sample;
        }else{
            be = 1;
        }
        if(be <= 0){
            be = 1;
        }
        return be;
    }


    /**
     * 鍥剧墖璐ㄩ噺鍘嬬缉锛屽崟寮犲浘鐗囧ぇ灏忎笉瓒呰繃瀹氬��
     * @param image
     * @return
     */
    public static  byte[] compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 90, baos);//璐ㄩ噺鍘嬬缉鏂规硶锛岃繖閲�100琛ㄧず涓嶅帇缂╋紝鎶婂帇缂╁悗鐨勬暟鎹瓨鏀惧埌baos涓�
        int options = 90;
        while ( baos.toByteArray().length / 1024>150) {	//寰幆鍒ゆ柇濡傛灉鍘嬬缉鍚庡浘鐗囨槸鍚﹀ぇ浜�150kb,澶т簬缁х画鍘嬬缉
            if(options<0){
                break;
            }
            baos.reset();//閲嶇疆baos鍗虫竻绌篵aos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//杩欓噷鍘嬬缉options%锛屾妸鍘嬬缉鍚庣殑鏁版嵁瀛樻斁鍒癰aos涓�
            options -= 10;//姣忔閮藉噺灏�10
        }
        Log.i("image_size", baos.toByteArray().length + "");
        return baos.toByteArray();
    }

    public static Bitmap compressImage(Activity activity,Bitmap image, int screenWidth, int screenHight) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if( baos.toByteArray().length / 1024>1024) {//鍒ゆ柇濡傛灉鍥剧墖澶т簬1M,杩涜鍘嬬缉閬垮厤鍦ㄧ敓鎴愬浘鐗囷紙BitmapFactory.decodeStream锛夋椂婧㈠嚭
            baos.reset();//閲嶇疆baos鍗虫竻绌篵aos
            image.compress(Bitmap.CompressFormat.JPEG, 60, baos);//杩欓噷鍘嬬缉50%锛屾妸鍘嬬缉鍚庣殑鏁版嵁瀛樻斁鍒癰aos涓�
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //寮�濮嬭鍏ュ浘鐗囷紝姝ゆ椂鎶妎ptions.inJustDecodeBounds 璁惧洖true浜�
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        float hh = screenHight;
        float ww = screenWidth;
        //缂╂斁姣斻�傜敱浜庢槸鍥哄畾姣斾緥缂╂斁锛屽彧鐢ㄩ珮鎴栬�呭鍏朵腑涓�涓暟鎹繘琛岃绠楀嵆鍙�
        int be = 1;//be=1琛ㄧず涓嶇缉鏀�
        if (w > h && w > ww) {//濡傛灉瀹藉害澶х殑璇濇牴鎹搴﹀浐瀹氬ぇ灏忕缉鏀�
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//濡傛灉楂樺害楂樼殑璇濇牴鎹珮搴﹀浐瀹氬ぇ灏忕缉鏀�
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//璁剧疆缂╂斁姣斾緥
        //閲嶆柊璇诲叆鍥剧墖锛屾敞鎰忔鏃跺凡缁忔妸options.inJustDecodeBounds 璁惧洖false浜�
        isBm = new ByteArrayInputStream(baos.toByteArray());
        try {
            bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        }catch (OutOfMemoryError e){
            try {
                newOpts.inPreferredConfig = Bitmap.Config.RGB_565;//闄嶄綆鍥剧墖浠嶢RGB888鍒癛GB565
                bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
            }catch (OutOfMemoryError o){
                System.gc();
                bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
            }

        }
        int bitWidth = bitmap.getWidth();
        if(!(screenWidth - bitWidth < 20)){
            bitmap = zoomImgToFitScreen(bitmap,activity);
        }
        return bitmap;

    }

    /**
     * 璋冩暣鍥剧墖瀹介珮锛岄粯璁や互灞忓箷瀹戒负姣斾緥锛岃嫢鏀惧ぇ鍚庨珮搴﹁秴杩囧睆骞曪紝鍒欎互灞忓箷楂樺害涓烘瘮渚�
     * @param bm 鍘熷鍥剧墖
     * @param context Activity
     * @return 缂╂斁鍚庣殑鏂板浘鐗�
     */
    public static Bitmap zoomImgToFitScreen(Bitmap bm,Activity context){
        // 鑾峰緱鍥剧墖鐨勫楂�
        int width = bm.getWidth();
        int height = bm.getHeight();
        int screenWidth = context.getWindowManager().getDefaultDisplay().getWidth() - 10;
        int screenHeightt = context.getWindowManager().getDefaultDisplay().getHeight() - 10;
        int newWidth = screenWidth;
        int newHeight = (screenWidth*height)/width;
        if(newHeight > screenHeightt){
            newHeight = screenHeightt;
            newWidth = (newHeight*width)/height;
        }
        // 璁＄畻缂╂斁姣斾緥
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 鍙栧緱鎯宠缂╂斁鐨刴atrix鍙傛暟
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 寰楀埌鏂扮殑鍥剧墖
        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return newbm;
    }

    /**
     * 鑾峰彇jpeg鐨勬棆杞俊鎭�
     * @param filepath
     * @return
     */
    public static int getExifOrientation(String filepath) {
        int degree = 0;
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(filepath);
        } catch (IOException ex) {
            //LogUtil.i("test", "cannot read exif");
        }
        if (exif != null) {
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
            Log.i("ORIENTATION", orientation + "");
            if (orientation != -1) {
                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        degree = 90;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        degree = 180;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        degree = 270;
                        break;
                }
            }
        }
        return degree;
    }

    /**
     * 鏃嬭浆鍥剧墖
     * @param bitmap 鍘熷浘
     * @param angle2 鏃嬭浆瑙掑害
     * @return
     */
    public static Bitmap rotaingImageView(Bitmap bitmap, int angle2) {
        Matrix matrix = new Matrix();
        // 鏃嬭浆鍥剧墖 鍔ㄤ綔
        matrix.postRotate(angle2);
        System.out.println("angle2=" + angle2);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        bitmap = BitmapFactory.decodeStream(bitmap2IS(bitmap), null, options);
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        // 鍒涘缓鏂扮殑鍥剧墖
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        bitmap.recycle();
        return resizedBitmap;
    }

    /**
     * Bitmap杞崲鎴怚nputStream鏂规硶
     *
     * @param bm
     * @return
     */
    public static InputStream bitmap2IS(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        InputStream sbs = new ByteArrayInputStream(baos.toByteArray());
        return sbs;
    }

    /**
     * 淇濆瓨鍥剧墖鍒皊d鍗�
     * @param bm
     * @param mContext
     * @param fileDir "/Yun/Images/Thumbnails"--鏀跺埌鐨勮亰澶╃缉鐣ュ浘  "/Yun/Images/Original"--鏀跺埌鐨勮亰澶╁師鍥�  "/Yun/Images/"--鍏朵粬
     * @return
     */
    public static String saveBitmaptoSdCard(Bitmap bm,Activity mContext,String fileDir,String url) {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            File sdCardDir = Environment.getExternalStorageDirectory();
            File yaoYanDir = new File(sdCardDir.getPath()+fileDir);
            if(!yaoYanDir.exists()){
                yaoYanDir.mkdirs();
            }
            String filename="";
            if(FOLDER_IMAGES_ORIGINAL.equals(fileDir)){
                filename=url.split("/")[url.split("/").length-1];
            }else{
                filename = getRandomFileName();
            }
            FileOutputStream fos;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 90, baos);
            byte[] buffer = baos.toByteArray();
            File f = new File(yaoYanDir,filename);
            if (!f.exists()) {
                try {
                    f.createNewFile();
                    fos = new FileOutputStream(f);
                    fos.write(buffer,0,buffer.length);
                    fos.flush();
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return filename;
        }else{
            Toast.makeText(mContext, "sd鍗′笉瀛樺湪", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    /**
     * 淇濆瓨鍥剧墖鍒皊d鍗�
     * @param bm
     * @return
     */
    public static String saveBitmaptoSdCard(Bitmap bm,String fileDir) {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            File sdCardDir = Environment.getExternalStorageDirectory();
            File yaoYanDir = new File(sdCardDir.getPath()+fileDir);
            if(!yaoYanDir.exists()){
                yaoYanDir.mkdirs();
            }
            String filename = getRandomFileName();
            FileOutputStream fos;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 90, baos);
            byte[] buffer = baos.toByteArray();
            File f = new File(yaoYanDir,filename);
            if (!f.exists()) {
                try {
                    f.createNewFile();
                    fos = new FileOutputStream(f);
                    fos.write(buffer,0,buffer.length);
                    fos.flush();
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return filename;
        }else{
//            Toast.makeText(mContext,"sd鍗′笉瀛樺湪",Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    /**
     * 閫氳繃uri鑾峰彇鍥剧墖鐨勭粷瀵硅矾寰�
     * @param context
     * @param uri
     * @return
     */
    public static String getFilePathByContentResolver(Context context, Uri uri) {
        if (null == uri) {
            return null;
        }
        Cursor c = context.getContentResolver().query(uri, null, null, null, null);
        String filePath  = null;
        if (null == c) {
            throw new IllegalArgumentException(
                    "Query on " + uri + " returns null result.");
        }
        try {
            if ((c.getCount() != 1) || !c.moveToFirst()) {
            } else {
                filePath = c.getString(
                        c.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA));
            }
        } finally {
            c.close();
        }
        return filePath;
    }
}
