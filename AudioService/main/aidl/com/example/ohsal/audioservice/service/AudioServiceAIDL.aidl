// AudioServiceAIDL.aidl
package com.example.ohsal.audioservice.service;

// Declare any non-default types here with import statements

interface AudioServiceAIDL {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     void playClip(int clip);
     void pauseClip();
     void resumeClip();
     void stopClip();
}
