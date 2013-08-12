package tw.edu.chu.csie.e_learning.ui;

import java.io.*;
import java.net.*;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import tw.edu.chu.csie.e_learning.R;

/**
 * Created by yuan on 2013/6/3.
 */
public class TextbookDownloaderActivity extends Activity implements OnClickListener {
	
	private Button textbook_update;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textbook_downloader);
        textbook_update = (Button)findViewById(R.id.updateButton);
        textbook_update.setOnClickListener(this);
    }
    
    @Override
	public void onClick(View v) {
		// TODO �۰ʲ��ͪ���k Stub
    	DownloadTextBookTask downloadTextBook = new DownloadTextBookTask();
    	downloadTextBook.execute();
	}
    
    /**
     * 
     * @author Tony 2013/08/06
     * 
     *
     */
    @SuppressWarnings("unused")
    public class DownloadTextBookTask extends AsyncTask<Void, Integer, Void>
    {
    	
		private HttpURLConnection url_con;
    	private URL http_url;
    	private InputStream is;
    	private File filepath;
    	private FileOutputStream output2SDCard;
    	private final String HTTP_URL = "http://140.126.11.163/test.html";
    	private ProgressDialog updateProgress;
    	
    	@Override
    	protected Void doInBackground(Void... params) {
    		// TODO �۰ʲ��ͪ���k Stub
    		try {
				http_url = new URL(HTTP_URL);
			} catch (MalformedURLException e1) {
				// TODO �۰ʲ��ͪ� catch �϶�
				e1.printStackTrace();
			}
    		try {
				url_con = (HttpURLConnection)http_url.openConnection();
				url_con.setRequestMethod("POST");
				url_con.setDoInput(true);
				url_con.setConnectTimeout(10000);
				if(url_con.getResponseCode() == HttpURLConnection.HTTP_OK)
				{
					is =  url_con.getInputStream();
					filepath = new File(android.os.Environment.getExternalStorageDirectory()+"/textbook");
					output2SDCard = new FileOutputStream(filepath);
				}
			} catch (IOException e) {
				// TODO �۰ʲ��ͪ� catch �϶�
				e.printStackTrace();
			}
    		return null;
    	}

    	@Override
    	protected void onPostExecute(Void result) {
    		// TODO �۰ʲ��ͪ���k Stub
    		updateProgress.dismiss();
    		super.onPostExecute(result);
    	}

    	@Override
    	protected void onPreExecute() {
    		// TODO �۰ʲ��ͪ���k Stub
    		super.onPreExecute();
    		updateProgress = ProgressDialog.show(TextbookDownloaderActivity.this, "�U���Ч�", "�U���Ч���....");
    	}

    	@Override
    	protected void onProgressUpdate(Integer... values) {
    		// TODO �۰ʲ��ͪ���k Stub
    		super.onProgressUpdate(values);
    	}
    	
    }
}