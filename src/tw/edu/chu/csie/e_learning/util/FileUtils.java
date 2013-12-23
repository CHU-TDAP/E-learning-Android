/**
 * @author kobayashi
 * @description 一切對Android儲存裝置的存取都從這個類別呼叫
 */
package tw.edu.chu.csie.e_learning.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import tw.edu.chu.csie.e_learning.config.Config;
import android.content.Context;
import android.os.*;
import android.util.Log;

public class FileUtils 
{
	private File BasicSDPath;  //SD卡的根目錄（會依照Android版本不同而有所變化）
	private Context BasicInternalPath;		//內部儲存裝置的根目錄(預設是/data/data/[package.name]/files/)
	private ZipEntry entry;
	
	public FileUtils() 
	{
		BasicSDPath = Environment.getExternalStorageDirectory();
	}
	
	/**
	 * isSDCardInsert
	 * @param			None
	 * @return			Boolean
	 * 偵測這個裝置有沒有插入記憶卡
	 */
	public boolean isSDCardInsert() 
	{
		if(!BasicSDPath.equals(Environment.MEDIA_REMOVED))
		{
			return true;  //SD卡已經插上去了
		}
		else return false;	//沒插入SD卡
	}
	
	/**
	 * getSDPath
	 * @param			None
	 * @return 		學習教材在SD卡上的路徑
	 * 取得在SD卡上的教材路徑
	 */
	public String getPath()
	{
		if(isSDCardInsert())
		{
			File path = new File(BasicSDPath+"/"+Config.APP_DIRECTORY);
			if(!path.exists()) 
			{
				path.mkdirs();
				return path.getAbsolutePath();
			}
			else return BasicSDPath+"/"+Config.APP_DIRECTORY;
		}
		else return BasicInternalPath+"/";
	}
	
	/**
	 * saveFile
	 * @param 			path
	 * @return			None
	 * @throws 		IOException 
	 * 下載檔案時存檔用
	 */
	public void saveFile(String path,InputStream is,HttpURLConnection con) throws IOException
	{
		File savePath = new File(path);
		FileOutputStream write = new FileOutputStream(path);
		int str1 = 0;;
		byte[] data = new byte[1024];
		while((str1=is.read(data)) != -1)
		{
			write.write(data,0,str1);
		}
		write.flush();
		write.close();
		is.close();
		con.disconnect();
	}
	
	/**
	 * decompressFile
	 * 解壓縮檔案
	 * @throws IOException
	 */
	public void decompressFile() throws IOException
	{
		InputStream is = null;
       BufferedInputStream bi = null;
       BufferedOutputStream bo = null;
		File zipFile = new File(getPath()+Config.ZIP_FILE_NAME_OF_MATERIAL); //取得壓縮檔
		ZipFile unzip = new ZipFile(zipFile);
		Enumeration<? extends ZipEntry> entryEnum  = unzip.entries();   //取得壓縮黨內的第一個目錄或檔案
		
		//如果壓縮黨內還有目錄或檔案的話
		while(entryEnum.hasMoreElements()) {
			entry = entryEnum.nextElement(); //取得下一個檔案或目錄
			//存檔程序
			File outFile = new File(getPath(), entry.getName()); //開啟要存的檔案或目錄
          if(entry.isDirectory()) {  //如果entry的值是一個目錄 
              Log.d("decompress", "Add a folder: " + outFile.getAbsolutePath());
              outFile.mkdir();	//建立資料夾
              if (!outFile.exists()) //確認資料夾是否有建立成功
            	  	Log.e("decompress","Can't create this path: "+ outFile.getAbsolutePath());
            }
          else {	//entry的值是一個檔案
                Log.d("decompress", "Add a file: " + outFile.getAbsolutePath());
                is = unzip.getInputStream(entry);	//取得壓縮檔的輸入串流
                bi = new BufferedInputStream(is);
                bo = new BufferedOutputStream(new FileOutputStream(outFile));
                int data = 0;
                while ((data = bi.read()) != -1) bo.write(data);
                bo.flush();		//將Buffer的空間清空
                bo.close(); bi.close(); is.close();  //關閉所有的I/O串流
            }
		}
		zipFile.delete(); //將壓縮檔刪除
	}
}
