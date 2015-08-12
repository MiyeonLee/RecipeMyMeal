package sep.architecture.recipemymeal.Fragment;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import sep.architecture.recipemymeal.R;
import sep.architecture.recipemymeal.Recipe;
import sep.architecture.recipemymeal.Service.SearchManager;


/**
 * A placeholder fragment containing a simple view.
 */
public class SearchName extends Fragment {
    OnSearchNameSelectedListener mCallback;
    Button material;
    Button search;


    SearchNameAsyncTask searchNameAsyncTask;
    ArrayList<Recipe> result;
    // for search material and tools
    public SearchName() {
    }

    public interface OnSearchNameSelectedListener {
        public void onMaterialSelected();
        public void onNameSearchResult(ArrayList<Recipe> r);
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnSearchNameSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnSearchNameSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_searchname, container, false);

        // buttons
        material = (Button)rootView.findViewById(R.id.btn_material);
        search = (Button)rootView.findViewById(R.id.btn_searchbymaterial);
        // button events
        material.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onMaterialSelected();
            }
        });
        search.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                EditText editText = (EditText)rootView.findViewById(R.id.edit_message);
                String foodName = editText.getText().toString();
                if(foodName != null){
                    // TODO: try searching by name
                    searchNameAsyncTask = new SearchNameAsyncTask();
                    searchNameAsyncTask.execute(foodName);
                }
                else{
                    // TODO: alert user to enter the name
                }
            }
        });

        return rootView;
    }

    private class SearchNameAsyncTask extends AsyncTask<String, String, ArrayList<Recipe>> {

        @Override
        protected ArrayList<Recipe> doInBackground(String... params) {
            SearchManager s = new SearchManager();
            result = s.reqByName(params[0]);
            return result;
        }

        protected void onPostExecute(ArrayList<Recipe> r) {
            super.onPostExecute(r);
            for(int i=0; i < r.size(); i++) {
                Log.d("Sync", r.get(i).getName());
            }
            mCallback.onNameSearchResult(r);
        }
    }
}