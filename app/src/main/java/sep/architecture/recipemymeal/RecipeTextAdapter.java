
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

public class RecipeTextAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    ArrayList<Recipe> arrayRecipe;
    int layout;

    public RecipeTextAdapter(Context context, int iLayout, ArrayList<Recipe> array){
        mContext = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        arrayRecipe = array;
        layout = iLayout;
    }

    public int getCount(){
        return arrayRecipe.size();
    }

    public Object getItem(int position){
        return arrayRecipe.get(position).Name;
    }

    public Recipe getRecipe(int position) {return arrayRecipe.get(position);}

    public long getItemId(int position){
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = inflater.inflate(layout, parent, false);
        }
        ImageView img;
        img = (ImageView)convertView.findViewById(R.id.thumbnail);
        img.setImageResource(arrayRecipe.get(position).Image);

        TextView txt = (TextView)convertView.findViewById(R.id.recipename);
        txt.setText(arrayRecipe.get(position).Name);

        return convertView;
    }
}