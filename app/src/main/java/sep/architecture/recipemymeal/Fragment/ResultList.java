package sep.architecture.recipemymeal.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import sep.architecture.recipemymeal.R;
import sep.architecture.recipemymeal.Recipe;
import sep.architecture.recipemymeal.RecipeTextAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class ResultList extends Fragment {
    OnResultListSelectedListener mCallback;
    Context mContext;

    ArrayList<Recipe> recipeList;
    Recipe recipeResult;
    Button back;

    public ResultList() {
    }

    @SuppressLint("ValidFragment")
    public ResultList(Recipe result) {
        recipeResult = result;
    }

    @SuppressLint("ValidFragment")
    public ResultList(ArrayList<Recipe> result) {
        recipeList = result;
    }

    public interface OnResultListSelectedListener {
        public void onListItemSelected(Recipe selectedRecipe);
        public void onBackFromListSelected();
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        mContext = (Context)activity;

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnResultListSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnResultListSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_resultlist, container, false);
/*
        recipeList = new ArrayList<Recipe>();

        Recipe recipeData;
        for(int i = 0; i < 24; i++)
        {
            recipeData = new Recipe(R.drawable.material24-i, (String)("Recipe" + i));
            recipeList.add(recipeData);
        }
*/
        final RecipeTextAdapter adapter = new RecipeTextAdapter(mContext, R.layout.recipe_item, recipeList);

        ListView list;
        list = (ListView)rootView.findViewById(R.id.resultlist);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCallback.onListItemSelected(adapter.getRecipe(position));
            }
        });

        // buttons
        back = (Button)rootView.findViewById(R.id.btn_back2);

        // button events
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onBackFromListSelected();
            }
        });

        return rootView;
    }

}