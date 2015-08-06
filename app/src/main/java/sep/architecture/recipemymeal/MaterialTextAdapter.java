package sep.architecture.recipemymeal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MaterialTextAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    ArrayList<Material> arrayMaterial;
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

    public View getView(final int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = inflater.inflate(layout, parent, false);
        }
        ImageView img;
        img = (ImageView)convertView.findViewById(R.id.thumbnail);
        img.setImageResource(arrayMaterial.get(position).Image);

        TextView txt = (TextView)convertView.findViewById(R.id.recipename);
        txt.setText(arrayMaterial.get(position).Name);

        CheckBox cbBox = (CheckBox)convertView.findViewById(R.id.cb_recipe);
        cbBox.setClickable(false);
        cbBox.setChecked(arrayMaterial.get(position).getCheckBoxState());
        cbBox.setOnClickListener(new CheckBox.OnClickListener() {
            public void onClick(View v) {
                if (arrayMaterial.get(position).getCheckBoxState()) {
                    arrayMaterial.get(position).setCheckBoxState(false);
                } else {
                    arrayMaterial.get(position).setCheckBoxState(true);
                }
            }
        });
        return convertView;
    }
}