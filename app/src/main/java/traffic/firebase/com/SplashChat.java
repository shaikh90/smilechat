package traffic.firebase.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import traffic.firebase.com.loginsetup.LogInActivity;

public class SplashChat extends Activity {
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_chat);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashChat.this,LogInActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }




    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
    }


