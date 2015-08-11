package sep.architecture.recipemymeal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MaterialTextAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    ArrayList<Material> arrayMaterial;
    int numberOfCheckedItem;
    int layout;

    public MaterialTextAdapter(Context context, int iLayout, ArrayList<Material> array){
        mContext = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        arrayMaterial = array;
        layout = iLayout;
    }

    public int getCount(){
        return arrayMaterial.size();
    }

    public Object getItem(int position){
        return arrayMaterial.get(position).Name;
    }

    public long getItemId(int position){
        return position;
    }

    public boolean getCheckBoxState(int position){
        return arrayMaterial.get(position).getCheckBoxState();
    }

    public void setCheckBoxState(int position, boolean state){
        arrayMaterial.get(position).setCheckBoxState(state);
        if(state) {
            addNumberOfCheckedItem(true);
        }else{
            addNumberOfCheckedItem(false);
        }
    }

    public void addNumberOfCheckedItem(boolean add){
        if(add){
            if(numberOfCheckedItem < 10) {
                numberOfCheckedItem++;
            }
        }else{
            if(numberOfCheckedItem !=0) {
                numberOfCheckedItem--;
            }
        }
    }

    public int getNumberOfCheckedItem(){
        return numberOfCheckedItem;
    }

    public View getView(final int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = inflater.inflate(layout, parent, false);
        }

        ImageView img = (ImageView) convertView.findViewById(R.id.thumbnail);
        if(arrayMaterial.get(position).Url != null) {
            DownloadImage task = new DownloadImage(img);
            task.execute(new String[]{arrayMaterial.get(position).Url});
        }else{
            img.setImageResource(arrayMaterial.get(position).Image);
        }

        TextView txt = (TextView)convertView.findViewById(R.id.recipename);
        txt.setText(arrayMaterial.get(position).Name);

        CheckBox cbBox = (CheckBox)convertView.findViewById(R.id.cb_recipe);
        cbBox.setClickable(false);
        cbBox.setFocusable(false);
        cbBox.setChecked(arrayMaterial.get(position).getCheckBoxState());
        /*
        cbBox.setOnClickListener(new CheckBox.OnClickListener() {
            public void onClick(View v) {
                if (arrayMaterial.get(position).getCheckBoxState()) {
                        arrayMaterial.get(position).setCheckBoxState(false);
                } else {
                    arrayMaterial.get(position).setCheckBoxState(true);
                }
            }
        });
        */
        return convertView;
    }

    // Reference source code for downloading image by URL
    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        ImageView img;

        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap map = null;
            for (String url : urls) {
                map = downloadImage(url);
            }
            return map;
        }

        public DownloadImage(ImageView imgView){
            img = imgView;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            img.setImageBitmap(result);
        }

        private Bitmap downloadImage(String url) {
            Bitmap bitmap = null;
            InputStream stream = null;
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inSampleSize = 1;

            try {
                stream = getHttpConnection(url);
                bitmap = BitmapFactory.decodeStream(stream, null, bmOptions);
                stream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return bitmap;
        }

        private InputStream getHttpConnection(String urlString)
                throws IOException {
            InputStream stream = null;
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();

            try {
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("GET");
                httpConnection.connect();

                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    stream = httpConnection.getInputStream();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return stream;
        }
    }

}