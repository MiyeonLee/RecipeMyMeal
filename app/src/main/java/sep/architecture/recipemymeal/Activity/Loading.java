package sep.architecture.recipemymeal.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import sep.architecture.recipemymeal.DownloadImage;
import sep.architecture.recipemymeal.R;

/**
 * Created by josephine.lee on 2015-08-12.
 */
public class Loading extends Activity {
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        ImageView img = (ImageView)findViewById(R.id.title_image);
        img.setImageResource(R.drawable.title_image);

        Handler handler = new Handler(){
            public void handleMessage(Message msg){
                super.handleMessage(msg);
                startActivity(new Intent(Loading.this, RecipeClient.class));
                finish();;
            }
        };
        handler.sendEmptyMessageDelayed(0, 3000);
    }
}
