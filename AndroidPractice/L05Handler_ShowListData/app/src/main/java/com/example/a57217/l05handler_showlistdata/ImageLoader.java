package com.example.a57217.l05handler_showlistdata;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 运用三级缓存加载图片并显示的类
 * Created by 57217 on 2016/8/15.
 */

/*
	String iamgePath = http://192.168.253.1:8081//L05_Web/images/f10.jpg和ImageView对象
	1). 根据url从一级缓存中取对应的bitmap对象
		如果有, 显示(结束)
		如果没有, 进入2)
	2). 从二级缓存中查找: 得到文件名并在sd卡的缓存目录下加载对应的图片得到Bitmap对象
		如果有: 显示, 缓存到一级缓存中(结束)
		如果没有, 进入3)
	3). 显示代表提示正在加载的图片, 启动分线程联网请求得到Bitmap对象
			如果没有: 显示提示错误的图片(结束)
			如果有:
				显示
				缓存到一级缓存
				缓存到二级缓存
 */

public class ImageLoader {
    private Context context;
    private  int loadingImageRes;
    private  int errorImages;

    public ImageLoader(Context context, int loadingImageRes, int errorImages) {
        this.context = context;
        this.loadingImageRes = loadingImageRes;
        this.errorImages = errorImages;
    }

    //用于缓存bitmap的容器对象(一级缓存)
    private Map<String,Bitmap> cachemap=new HashMap<String,Bitmap>();


    /**
     *加载图片并显示
     * @param imagePath
     * @param imageView
     */
     public void loadImage(String imagePath, ImageView imageView){

           //将要显示的图片的url保存到视图上
            imageView.setTag(imagePath);

          /*
		 1). 根据url从一级缓存中取对应的bitmap对象
			如果有, 显示(结束)
			如果没有, 进入2)
		 */
         Bitmap bitmap =getFromFirstCache(imagePath);
         if (bitmap!=null){
             imageView.setImageBitmap(bitmap);
             return;
         }

         /*
		2). 从二级缓存中查找: 得到文件名并在sd卡的缓存目录下加载对应的图片得到Bitmap对象
				如果有: 显示, 缓存到一级缓存中(结束)
				如果没有, 进入3)

			/storage/sdcard/Android/data/packageName/files/图片文件名(xxx.jpg)
		 */
         bitmap=getFromSecondCache(imagePath);
         if (bitmap!=null){
             imageView.setImageBitmap(bitmap);
             cachemap.put(imagePath,bitmap);
             return;
         }

         /*
		 3). 显示代表提示正在加载的图片, 启动分线程联网请求得到Bitmap对象
			如果没有: 显示提示错误的图片(结束)
			如果有:
				缓存到一级缓存(分线程)
				缓存到二级缓存(分线程)
				显示(主线程)

		 */
         loadBitmapFromThirdCache(imagePath,imageView);
     }

    private void loadBitmapFromThirdCache(final String imagePath, final ImageView imageView) {
          new AsyncTask<Void,Void,Bitmap>(){

              @Override
              protected void onPreExecute() {
                  imageView.setImageResource(loadingImageRes);
              }

              //联网请求得到bitmap对象
              @Override
              protected Bitmap doInBackground(Void... voids) {
                   Bitmap bitmap=null;
                   try{
                       //在准备请求服务器图片之前，判断一下是否需要加载
                       String newImagePath = (String) imageView.getTag();
                       if(newImagePath!=imagePath) {//视图已经被复用了
                            return null;
                       }

                       //得到连接
                       URL url=new URL(imagePath);
                       HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                       //设置
                       connection.setConnectTimeout(5000);
                       connection.setReadTimeout(5000);
                       //连接
                       connection.connect();
                        //发送请求读取返回的数据并封装成bitmap
                       int responseCode=connection.getResponseCode();
                       if(responseCode==200){
                           InputStream is=connection.getInputStream();
                           //将is封装成bitmap
                           bitmap=BitmapFactory.decodeStream(is);
                           is.close();

                           if (bitmap!=null){
                               //缓存到一级缓存(分线程)
                               cachemap.put(imagePath,bitmap);
                               //缓存到二级缓存(分线程)
                               String filesPath=context.getExternalFilesDir(null).getAbsolutePath();
                               String fileName=imagePath.substring(imagePath.lastIndexOf("/")+1);
                               String filePath=filesPath+"/"+fileName;
                               bitmap.compress(Bitmap.CompressFormat.JPEG,100,new FileOutputStream(filePath));
                           }
                       }
                   }catch (Exception e){
                       e.printStackTrace();
                   }
                  return bitmap;
              }

              @Override
              protected void onPostExecute(Bitmap bitmap) {
                    //在主线程准备显示图片之前，需要判断是否需要显示
                    String newImagePath=(String)imageView.getTag();
                   if (newImagePath != imagePath){
                       return;
                   }


                   //如果没有:显示提示错误的信息
                  if (bitmap==null){
                      imageView.setImageResource(errorImages);
                  }else {
                      imageView.setImageBitmap(bitmap);
                  }
              }
          }.execute();



     }

    private Bitmap getFromSecondCache(String imagePath) {
        // /storage/sdcard/Android/data/packageName/files/
        String filesPath=context.getExternalFilesDir(null).getAbsolutePath();
        // http://192.168.10.165:8080//L05_Web/images/f10.jpg
         String fileName=imagePath.substring(imagePath.lastIndexOf("/")+1);
         String filepath=filesPath+"/"+fileName;
           //!!!此处存在一个文件可能不存在的异常
        return BitmapFactory.decodeFile(filepath);

    }

    private Bitmap getFromFirstCache(String imagePath) {
       return cachemap.get(imagePath);
    }
}
