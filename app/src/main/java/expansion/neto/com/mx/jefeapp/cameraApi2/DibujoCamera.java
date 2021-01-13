package expansion.neto.com.mx.jefeapp.cameraApi2;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import expansion.neto.com.mx.jefeapp.R;

public class DibujoCamera extends AppCompatActivity {
    public static Activity fa;
    public static String uri = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dibujo_camera);
        fa = this;
        if(null == savedInstanceState){
            getSupportFragmentManager().beginTransaction().replace(R.id.container, Camera2BasicFragment.newInstance()).commit();
        }
    }

    public String dibujoCamera(){

        return uri;
    }
}
