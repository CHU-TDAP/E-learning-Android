package tw.edu.chu.csie.e_learning.ui;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class DownloadTextBookService extends Service
{
	@Override
	public IBinder onBind(Intent intent) {
		// TODO �۰ʲ��ͪ���k Stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO �۰ʲ��ͪ���k Stub
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		// TODO �۰ʲ��ͪ���k Stub
		super.onDestroy();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO �۰ʲ��ͪ���k Stub
		return super.onUnbind(intent);
	}
	
}
