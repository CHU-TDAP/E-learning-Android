package tw.edu.chu.csie.e_learning.ui;

import java.io.*;
import java.net.*;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import tw.edu.chu.csie.e_learning.R;

/**
 * Created by yuan on 2013/6/3.
 */
public class TextbookDownloaderActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textbook_downloader);
    }
    /**
     * 
     * @author Tony 2013/08/06
     * 
     *
     */
    @SuppressWarnings("unused")
    public class DownloadTextBookTask extends AsyncTask<String, Integer, Void>
    {
    	
		private HttpURLConnection url_con;
    	private URL http_url;
    	private InputStream is;
    	private FileOutputStream output2SDCard;
    	
    	@Override
    	protected Void doInBackground(String... params) {
    		// TODO 自動產生的方法 Stub
    		return null;
    	}

    	@Override
    	protected void onCancelled() {
    		// TODO 自動產生的方法 Stub
    		super.onCancelled();
    	}

    	@Override
    	protected void onPostExecute(Void result) {
    		// TODO 自動產生的方法 Stub
    		super.onPostExecute(result);
    	}

    	@Override
    	protected void onPreExecute() {
    		// TODO 自動產生的方法 Stub
    		super.onPreExecute();
    	}

    	@Override
    	protected void onProgressUpdate(Integer... values) {
    		// TODO 自動產生的方法 Stub
    		super.onProgressUpdate(values);
    	}
    	
    }
}