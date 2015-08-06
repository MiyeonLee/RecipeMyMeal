package sep.architecture.recipemymeal.Fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import sep.architecture.recipemymeal.Material;
import sep.architecture.recipemymeal.MaterialTextAdapter;
import sep.architecture.recipemymeal.R;
import sep.architecture.recipemymeal.Recipe;
import sep.architecture.recipemymeal.RecipeTextAdapter;


/**
 * A placeholder fragment containing a simple view.
 */
public class ResultList extends Fragment {
    Context mContext;

    ArrayList<Recipe> recipeList;

    public ResultList() {
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        mContext = (Context)activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_resultlist, container, false);

        recipeList = new ArrayList<Recipe>();

        Recipe recipeData;
        for(int i = 0; i < 24; i++)
        {
            recipeData = new Recipe(R.drawable.material24-i, (String)("Recipe" + i));
            recipeList.add(recipeData);
        }

        RecipeTextAdapter adapter = new RecipeTextAdapter(mContext, R.layout.recipe_item, recipeList);

        ListView list;
        list = (ListView)rootView.findViewById(R.id.resultlist);
        list.setAdapter(adapter);

        return rootView;
    }

}