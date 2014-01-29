package tw.edu.chu.csie.e_learning.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import tw.edu.chu.csie.e_learning.config.Config;
import tw.edu.chu.csie.e_learning.provider.ClientDBProvider;
import tw.edu.chu.csie.e_learning.server.BaseSettings;
import tw.edu.chu.csie.e_learning.server.ServerAPIs;
import tw.edu.chu.csie.e_learning.server.ServerUtils;
import tw.edu.chu.csie.e_learning.server.exception.HttpException;
import tw.edu.chu.csie.e_learning.server.exception.ServerException;

/**
 * 學習相關的動作類別庫
 *
 */
public class LearningUtils 
{
	private BaseSettings bs;
	private ServerAPIs connect;
	private JSONDecodeUtils decode;
	private ClientDBProvider dbcon;
	private SettingUtils settings;
	
	/**
	 * 學習相關的動作類別庫
	 * @param context 帶入Android基底Context
	 */
	public LearningUtils(Context context)
	{
		settings = new SettingUtils(context);
		bs = new BaseSettings(settings.getRemoteURL());
		connect = new ServerAPIs(bs);
		decode = new JSONDecodeUtils();
		dbcon = new ClientDBProvider(context);
	}
	
	/**
	 * 此學習點是否為推薦的學習點
	 * <p>
	 * 此函式會檢查Client資料庫"chu_target"資料表裡，此標地是否為推薦的學習點
	 * 
	 * @param pointNumber 標地編號
	 * @return <code>true</code> 為推薦的學習點
	 */
	public boolean isInRecommandPoint(String pointNumber) {
		// 抓取資料庫中有無此學習點
		String[] query = dbcon.search("chu_target", "TID", "TID="+pointNumber);
		if(query.length>0) return true;
		else return false;
	}
	
	/**
	 * 此學習點是否為實體的學習點
	 * @param pointNumber 標地編號
	 * @return <code>true</code> 為實體的學習點
	 */
	public boolean isEntityMaterial(String pointNumber) {
		// 抓取資料庫中有無此學習點
		String[] query = dbcon.search("chu_target", "IsEntity", "TID="+pointNumber);
		// 如果不是實體教材
		if(query[0].equals("0")) {
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 * 通知伺服器此學習點標記加人數
	 * @param pointNumber 標地編號
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws HttpException
	 * @throws JSONException 
	 * @throws ServerException 
	 */
	public void addPeople(String pointNumber) throws ClientProtocolException, IOException, HttpException, JSONException, ServerException
	{
		connect.addPeople(pointNumber);
	}
	
	/**
	 * 通知伺服器此學習點標記減人數
	 * @param pointNumber 標地編號
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws HttpException
	 * @throws JSONException
	 * 請用Log.d();來Debug~!!
	 * @throws ServerException 
	 */
	public void subPeople(String pointNumber) throws ClientProtocolException, IOException, HttpException, JSONException, ServerException
	{
		connect.subPeople(pointNumber);
	}
	
	/**
	 * 取得系統推薦的下個學習點
	 * @param userID 使用者帳號ID (TODO 要改成登入碼)
	 * @param pointNumber 目前所在的標地編號
	 * @throws HttpException 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws JSONException 
	 * @throws ServerException
	 */
	public void getPointIdOfLearningPoint(String userID,String pointNumber) throws ServerException, JSONException, ClientProtocolException, IOException, HttpException 
	{
		String message = connect.getPointIdOfLearningPoint(userID, pointNumber);
		
		if(!message.equals("null")) {
			decode.DecodeJSONData(message,"first");
			dbcon.target_insert(decode.getNextPoint(),decode.getTargetName() ,decode.getMapURL(), decode.getMaterialURL(), decode.getEstimatedStudyTime(),decode.getIsEntity());
		}
		else {
			dbcon.target_insert(0, "", "", "", 0, 0);
		}
	}
	
	/**
	 * 取得已經學習的分鐘
	 * @return
	 */
	public Date getLearningDate() {
		// 取得現在時間
		Date nowDate = new Date(System.currentTimeMillis());
		
		// 取得開始學習時間
		String[] query = dbcon.search("chu_user", "In_Learn_Time", null);
		String startDateDB;
		if(query.length>0) startDateDB = query[0];
		else return null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = new Date();
		try {
			startDate = format.parse(startDateDB);
			
			// 回傳時間差
			Date date= new Date(nowDate.getTime()-startDate.getTime());
			return date;
			
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		} 
	}
}
