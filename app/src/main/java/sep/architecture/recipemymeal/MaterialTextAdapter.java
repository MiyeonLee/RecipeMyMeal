package sep.architecture.recipemymeal;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


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

    public int getItemIndex(int position){ return arrayMaterial.get(position).BitPosition;}

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

          // TODO: image download cause of exception now -> need to fix
        if(arrayMaterial.get(position).Url != null) {
            Picasso.with(mContext).load(arrayMaterial.get(position).Url)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .networkPolicy(NetworkPolicy.NO_CACHE).into(img);
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
}