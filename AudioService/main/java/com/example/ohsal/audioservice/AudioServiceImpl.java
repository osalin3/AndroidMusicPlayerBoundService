package com.example.ohsal.audioservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.example.ohsal.audioservice.service.AudioServiceAIDL;

public class AudioServiceImpl extends Service {

	private MediaPlayer mPlayer;


	// Implement the Stub for this Object
	private final AudioServiceAIDL.Stub mBinder = new AudioServiceAIDL.Stub(){

		public void playClip(int clip){
			System.out.println("Play clip: " + clip);
			switch (clip) //set which clip to play
			{
				case 1: mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.one);
						break;
				case 2: mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.two);
					break;
				case 3: mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.three);
					break;
			}
			if (null != mPlayer) {
				if (mPlayer.isPlaying()) {
					// Rewind to beginning of song
					mPlayer.seekTo(0);

				} else {

					// Start playing song
					mPlayer.start();

				}
			}
		}
		public void pauseClip(){ //pauses clip
			System.out.println("Pauseclip");
			if(mPlayer.isPlaying())
			{
				mPlayer.pause();
			}
		}
		public void resumeClip(){//resumes clip
			System.out.println("Resumeclip");
			if(!mPlayer.isPlaying())
			{
				mPlayer.start();
			}

		}
		public void stopClip(){//stops clip
			System.out.println("Stopclip");
			if(mPlayer.isPlaying())
			{
				mPlayer.stop();
				mPlayer.reset();
			}
		}
	};

	@Override
	public void onDestroy() {
		if(mPlayer.isPlaying())
		{
			mPlayer.stop();
			mPlayer.reset();
		}
	}

	// Return the Stub defined above
	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}
}
