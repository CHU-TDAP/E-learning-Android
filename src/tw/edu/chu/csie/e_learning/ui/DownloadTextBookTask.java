package tw.edu.chu.csie.e_learning.ui;

import java.io.*;
import java.net.*;
import android.os.AsyncTask;

@SuppressWarnings("unused")
public class DownloadTextBookTask extends AsyncTask<String, Integer, Void>
{
	private HttpURLConnection url_con;
	private URL http_url;
	private InputStream is;
	private FileOutputStream output2SDCard;
	
	@Override
	protected Void doInBackground(String... params) {
		// TODO �۰ʲ��ͪ���k Stub
		return null;
	}

	@Override
	protected void onCancelled() {
		// TODO �۰ʲ��ͪ���k Stub
		super.onCancelled();
	}

	@Override
	protected void onPostExecute(Void result) {
		// TODO �۰ʲ��ͪ���k Stub
		super.onPostExecute(result);
	}

	@Override
	protected void onPreExecute() {
		// TODO �۰ʲ��ͪ���k Stub
		super.onPreExecute();
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO �۰ʲ��ͪ���k Stub
		super.onProgressUpdate(values);
	}
	
}
