package tw.edu.chu.csie.e_learning.ui;

import tw.edu.chu.csie.e_learning.R;
import tw.edu.chu.csie.e_learning.R.layout;
import tw.edu.chu.csie.e_learning.R.menu;
import tw.edu.chu.csie.e_learning.provider.ClientDBProvider;
import tw.edu.chu.csie.e_learning.util.FileUtils;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class TesterActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tester);
		
		Button btn1 = (Button)findViewById(R.id.tester_btn1);
		btn1.setText("取得學習點01教材路徑");
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getBaseContext(), new FileUtils().getMaterialFilePath(getBaseContext(), 19), 0).show();
			}
		});
		
		Button btn2 = (Button)findViewById(R.id.tester_btn2);
		btn2.setText("寫入標地1進SQLite");
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClientDBProvider db = new ClientDBProvider(getBaseContext());
				db.target_insert(01, "Test!!!", "map_01_02_03.png", "01.html", 3,0);
			}
		});
		
		Button btn3 = (Button)findViewById(R.id.tester_btn3);
		btn3.setText("清除標地SQLite");
		btn3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClientDBProvider db = new ClientDBProvider(getBaseContext());
				db.delete(null, "chu_target");
			}
		});
		
		// ---------------------------------------------------------------------------------------------------------------------------------
		Button sendStopSendAll = (Button)findViewById(R.id.tester_send_stop_all_send);
		sendStopSendAll.setOnClickListener(this);
		
		Button snedAddPeople = (Button)findViewById(R.id.tester_send_addpeople);
		snedAddPeople.setOnClickListener(this);
		
		Button sendSubPeople = (Button)findViewById(R.id.tester_send_subpeople);
		sendSubPeople.setOnClickListener(this);
		
		Button sendSaveUserStatus = (Button)findViewById(R.id.tester_send_save_user_status);
		sendSaveUserStatus .setOnClickListener(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tester, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.tester_send_stop_all_send:
			break;
		case R.id.tester_send_addpeople:
			break;
		case R.id.tester_send_subpeople:
			break;
		case R.id.tester_send_save_user_status:
			break;
		}
	}

}
