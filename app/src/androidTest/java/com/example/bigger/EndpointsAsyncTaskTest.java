package com.example.bigger;

import android.content.Context;
import android.content.Intent;

import androidx.test.InstrumentationRegistry;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.androidjokeslibrary.JokeActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;


@RunWith(AndroidJUnit4ClassRunner.class)
public class EndpointsAsyncTaskTest {

    private CountDownLatch mSignal;
    private String joke;
    private Context mConext;
    private EndpointsAsynkTask mEndpointsAsysTask;
    private Intent mIntent;
    private String checkBackGround;
    private static final String TAG  = EndpointsAsyncTaskTest.class.getSimpleName();


    @Before
    public void setUp() {

        //lets get a context
        mConext =  InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertNotNull(mConext);

        checkBackGround = null;

        //Reference Endpoint Task
        mEndpointsAsysTask = new EndpointsAsynkTask();
        assertNotNull(mEndpointsAsysTask);

        //Set Countdown
        mSignal = new CountDownLatch(1);
    }

    @Test
    public void PostExecute(){
        Intent mIntent = new Intent(mConext, JokeActivity.class);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mIntent.putExtra("joke", "testing again");
        assertNotNull(mIntent);
    }

    @Test
    public void doInBackground() {

        checkBackGround = new EndpointsAsynkTask().doInBackground(mConext);
        assertNotNull(checkBackGround);
    }

    @Test
    public void endPointAsyncTask() throws InterruptedException {

        String jokeReturn = null;

        try {
            mSignal = new CountDownLatch(2);

            jokeReturn  = mIntent.getStringExtra("AndroidLibActivity");
            mSignal.await(8, TimeUnit.SECONDS);
            assertNotSame(mIntent.getStringExtra("joke"), jokeReturn);
            assertNotNull("Empty String Return", jokeReturn);

        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    @Test
    public void testPostAsyncAfterResponse(){

        EndpointsAsynkTask postEndpointsAsyncTask = new EndpointsAsynkTask() {

            @Override
            public void onPostExecute(String result) {
                assertNotNull(result);
                assertTrue(result.length() > 0);
                mSignal.countDown();
            }
        };

        postEndpointsAsyncTask.execute(mConext);
        try {
            mSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

