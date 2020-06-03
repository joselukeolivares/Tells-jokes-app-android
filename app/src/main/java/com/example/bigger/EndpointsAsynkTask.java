package com.example.bigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.androidjokeslibrary.JokeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;



import java.io.IOException;

public class EndpointsAsynkTask extends AsyncTask<Context,Void,String> implements JokeInterface {

    private static MyApi myApiService=null;
    Context context;

    @Override
    protected String doInBackground(Context... contexts) {


        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();




                }

        context=contexts[0];

        try{
            String joke=myApiService.sayHi().execute().getData();
            Log.i(this.getClass().getName(),"doingBackground: data "+joke);
            return joke;
        }catch (IOException e){
            Log.i(this.getClass().getName(),"doingBackground: "+e.getLocalizedMessage());

        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        //super.onPostExecute(s);
        Log.i(this.getClass().getName(),"onPostExecute: "+s);
        if(isNullOrEmpty(s)){
            Intent intent=new Intent(context, JokeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("joke",s);
            startJokeIntent(intent);


        }

    }

    private boolean isNullOrEmpty(String str) {
        if (str != null && !str.isEmpty())
            return true;
        return false;
    }

    private void displayToast() {
        Toast.makeText(context, "No Jokes Returned from Library", Toast.LENGTH_LONG).show();
    }

    @Override
    public void startJokeIntent(Intent intent) {
        context.startActivity(intent);
    }
}
