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

public class ToolTextAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    ArrayList<Tool> arrayTool;
    int numberOfCheckedItem;
    int layout;

    public ToolTextAdapter(Context context, int iLayout, ArrayList<Tool> array){
        mContext = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        arrayTool = array;
        layout = iLayout;
    }

    public int getCount(){
        return arrayTool.size();
    }

    public Object getItem(int position){
        return arrayTool.get(position).Name;
    }

    public long getItemId(int position){
        return position;
    }

    public boolean getCheckBoxState(int position){
        return arrayTool.get(position).getCheckBoxState();
    }

    public void setCheckBoxState(int position, boolean state){
        arrayTool.get(position).setCheckBoxState(state);
        if(state) {
            addNumberOfCheckedItem(true);
        }else{
            addNumberOfCheckedItem(false);
        }
    }

    public void addNumberOfCheckedItem(boolean add){
        if(add){
            if(numberOfCheckedItem < 1) {
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

        ImageView img = (ImageView) convertView.findViewById(R.id.thumbnail_tool);
        if(arrayTool.get(position).Url != null) {
            DownloadImage task = new DownloadImage(img);
            task.execute(new String[]{arrayTool.get(position).Url});
        }else{
            img.setImageResource(arrayTool.get(position).Image);
        }

        TextView txt = (TextView)convertView.findViewById(R.id.toolname);
        txt.setText(arrayTool.get(position).Name);

        CheckBox cbBox = (CheckBox)convertView.findViewById(R.id.cb_tool);
        cbBox.setClickable(false);
        cbBox.setFocusable(false);
        cbBox.setChecked(arrayTool.get(position).getCheckBoxState());

        return convertView;
    }
}