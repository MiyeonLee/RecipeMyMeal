package sep.architecture.recipemymeal.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import sep.architecture.recipemymeal.DownloadImage;
import sep.architecture.recipemymeal.Material;
import sep.architecture.recipemymeal.R;
import sep.architecture.recipemymeal.Recipe;
import sep.architecture.recipemymeal.Tool;

/**
 * Created by josephine.lee on 2015-08-11.
 */
public class ResultDetail extends Fragment {
    OnResultDetailFragmentSelectedListener mCallback;
    Context mContext;

    Button back;
    Recipe recipe;

    ArrayList<Material> materialList;
    ArrayList<Tool> toolList;

    public ResultDetail() {
    }

    @SuppressLint("ValidFragment")
    public ResultDetail(Recipe selectedRecipe) {
        recipe = selectedRecipe;
    }

    public interface OnResultDetailFragmentSelectedListener {
        public void onBackSelected();
        public ArrayList<Material> getMaterialList();
        public ArrayList<Tool> getToolList();
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        mContext = (Context)activity;

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnResultDetailFragmentSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnResultDetailFragmentSelectedListener");
        }
        materialList = mCallback.getMaterialList();
        toolList = mCallback.getToolList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_resultdetail, container, false);

        TextView txt = (TextView)rootView.findViewById(R.id.recipe_title);
        txt.setText(recipe.getName());

        ArrayList<String> materialIncluded = new ArrayList<String>();
        int mHash = recipe.getMaterialHash();
        for(int i = (materialList.size()-1); i >= 0 ; i--){
            if(materialList.get(i).getBitposition() > mHash)
            {
                // do nothing
            }else{
                materialIncluded.add(materialList.get(i).getName());
                mHash -= materialList.get(i).getBitposition();
            }
        }

        txt = (TextView)rootView.findViewById(R.id.recipe_material_lists);
        String printedMaterial = materialIncluded.get(0);
        for(int i = 1; i < materialIncluded.size(); i++){
            printedMaterial = printedMaterial + " " + materialIncluded.get(i);
        }
        txt.setText(printedMaterial);

        ArrayList<String> toolIncluded = new ArrayList<String>();
        int tHash = recipe.getToolHash();
        for(int i = (toolList.size()-1); i >= 0 ; i--){
            if(toolList.get(i).getBitposition() > tHash)
            {
                // do nothing
            }else{
                toolIncluded.add(toolList.get(i).getName());
                mHash -= toolList.get(i).getBitposition();
            }
        }

        txt = (TextView)rootView.findViewById(R.id.recipe_tool_lists);
        String printedTool = toolIncluded.get(0);
        for(int i = 1; i < toolIncluded.size(); i++){
            printedTool.concat(" ");
            printedTool.concat(toolIncluded.get(i));
        }
        txt.setText(printedTool);

        txt = (TextView)rootView.findViewById(R.id.recipe_material_process);
        txt.setText(recipe.getProcess());

        ImageView img = (ImageView) rootView.findViewById(R.id.thumbnail_food);
        if(recipe.getUrl() != null) {
            DownloadImage task = new DownloadImage(img);
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{recipe.getUrl()});
        }else{
            img.setImageResource(recipe.getImageId());
        }

        // buttons
        back = (Button)rootView.findViewById(R.id.btn_back);

        // button events
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onBackSelected();
            }
        });

        return rootView;
    }
}
