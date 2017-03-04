package com.example.ohsal.playerclient;


import android.app.Activity ;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.ohsal.audioservice.service.AudioServiceAIDL;

import java.util.ArrayList;

public class PlayerClientApp extends Activity {

	protected static final String TAG = "PlayerClientApp";
	private AudioServiceAIDL AudioServiceClient;
	private boolean mIsBound = false;
	String clipName;
	int n; //song selection
	ArrayList<String> items = new ArrayList<String>();
	ArrayAdapter<String> adapter;
	ListView listView;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.main);

		final Button goButton = (Button) findViewById(R.id.go_button);
		final Button pauseButton = (Button) findViewById(R.id.button);
		final Button resumeButton = (Button) findViewById(R.id.button2);
		final Button stopButton = (Button) findViewById(R.id.button3);
		final Button Button5 = (Button) findViewById(R.id.button5);
		final Button Button4 = (Button) findViewById(R.id.button4);

		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
		listView = (ListView) findViewById(R.id.litems);
		listView.setAdapter(adapter);

		goButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				try {
					if (mIsBound){
						n = 1;
						clipName = "1";
						AudioServiceClient.playClip(n);
						items.add(0, "Clip " + n + " - " + clipName + " playing!");
						adapter.notifyDataSetChanged();
					}
					else {
						Log.i(TAG, "Not bound to service");
					}

				} catch (RemoteException e) {

					Log.e(TAG, e.toString());

				}
			}
		});
		Button5.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				try {
					if (mIsBound){
						n = 2;
						clipName = "2";
						AudioServiceClient.playClip(n);
						items.add(0, "Clip " + n + " - " + clipName + " playing!");
						adapter.notifyDataSetChanged();
					}
					else {
						Log.i(TAG, "Not bound to service");
					}

				} catch (RemoteException e) {

					Log.e(TAG, e.toString());

				}
			}
		});
		Button4.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				try {
					if (mIsBound){
						n = 3;
						clipName = "3";
						AudioServiceClient.playClip(n);
						items.add(0, "Clip " + n + " - " + clipName + " playing!");
						adapter.notifyDataSetChanged();
					}
					else {
						Log.i(TAG, "Not bound to service");
					}

				} catch (RemoteException e) {

					Log.e(TAG, e.toString());

				}
			}
		});
		pauseButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					AudioServiceClient.pauseClip();
					items.add(0, "Clip " + clipName + " paused!");
					adapter.notifyDataSetChanged();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
		resumeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					AudioServiceClient.resumeClip();
					items.add(0, "Clip " + clipName + " resumed playing!");
					adapter.notifyDataSetChanged();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
		stopButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					AudioServiceClient.stopClip();
					items.add(0, "Clip " + clipName + " stopped!");
					adapter.notifyDataSetChanged();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Bind to AudioServiceAIDL Service
	@Override
	protected void onResume() {
		super.onResume();

		if (!mIsBound) {

			boolean b = false;
			Intent i = new Intent(AudioServiceAIDL.class.getName());

			ResolveInfo info = getPackageManager().resolveService(i, Context.BIND_AUTO_CREATE);
			i.setComponent(new ComponentName(info.serviceInfo.packageName, info.serviceInfo.name));

			b = bindService(i, this.mConnection, Context.BIND_AUTO_CREATE);
			if (b) {
				Log.i(TAG, "binding succeeded");
			} else {
				Log.i(TAG, "failed to bind");
			}

		}
	}

	// Unbind from AudioServiceAIDL Service
	@Override
	protected void onPause() {

		if (mIsBound) {

			unbindService(this.mConnection);

		}

		super.onPause();
	}

	//connect to AIDL
	private final ServiceConnection mConnection = new ServiceConnection() {

		public void onServiceConnected(ComponentName className, IBinder iservice) {

			AudioServiceClient = AudioServiceAIDL.Stub.asInterface(iservice);

			mIsBound = true;

		}

		public void onServiceDisconnected(ComponentName className) {

			AudioServiceClient = null;

			mIsBound = false;

		}
	};

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onStop() {
		super.onStop();
	}
}
