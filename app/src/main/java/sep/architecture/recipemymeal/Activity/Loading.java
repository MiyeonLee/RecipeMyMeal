package sep.architecture.recipemymeal.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;

import sep.architecture.recipemymeal.DownloadImage;
import sep.architecture.recipemymeal.Material;
import sep.architecture.recipemymeal.R;
import sep.architecture.recipemymeal.Recipe;
import sep.architecture.recipemymeal.Service.MaterialToolManager;
import sep.architecture.recipemymeal.Service.SearchManager;
import sep.architecture.recipemymeal.Tool;

/**
 * Created by josephine.lee on 2015-08-12.
 */
public class Loading extends Activity {

    MaterialRequestAsyncTask materialRequestAsyncTask;
    ToolRequestAsyncTask toolRequestAsyncTask;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        ImageView img = (ImageView)findViewById(R.id.title_image);
        img.setImageResource(R.drawable.title_image);

        /*
        Handler handler = new Handler(){
            public void handleMessage(Message msg){
                super.handleMessage(msg);
                startActivity(new Intent(Loading.this, RecipeClient.class));
                finish();;
            }
        };
        handler.sendEmptyMessageDelayed(0, 3000);
        */
        materialRequestAsyncTask = new MaterialRequestAsyncTask();
        materialRequestAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 0, 0);

        toolRequestAsyncTask = new ToolRequestAsyncTask();
        toolRequestAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 0, 0);
    }

    private class MaterialRequestAsyncTask extends AsyncTask<Integer, Void, ArrayList<Material>> {
        protected ArrayList<Material> doInBackground(Integer... params) {
            MaterialToolManager s = new MaterialToolManager();
            ArrayList<Material> result = s.getMaterialList();
            return result;
        }

        protected void onPostExecute(ArrayList<Material> r) {
            super.onPostExecute(r);
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

    private class ToolRequestAsyncTask extends AsyncTask<Integer, Void, ArrayList<Tool>> {
        protected ArrayList<Tool> doInBackground(Integer... params) {
            MaterialToolManager s = new MaterialToolManager();
            ArrayList<Tool> result = s.getToolList();
            return result;
        }

        protected void onPostExecute(ArrayList<Tool> r) {
            super.onPostExecute(r);
        }
    }
}
