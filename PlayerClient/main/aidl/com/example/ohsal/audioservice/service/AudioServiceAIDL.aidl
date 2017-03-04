// AudioServiceAIDL.aidl
package com.example.ohsal.audioservice.service;

// Declare any non-default types here with import statements

interface AudioServiceAIDL {
   void playClip(int clip);
   void pauseClip();
   void resumeClip();
   void stopClip();
}
