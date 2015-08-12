package sep.architecture.recipemymeal.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import sep.architecture.recipemymeal.Material;
import sep.architecture.recipemymeal.MaterialTextAdapter;
import sep.architecture.recipemymeal.R;
import sep.architecture.recipemymeal.Recipe;
import sep.architecture.recipemymeal.Service.SearchManager;
import sep.architecture.recipemymeal.Tool;
import sep.architecture.recipemymeal.ToolTextAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class SearchMaterial extends Fragment {
    OnSearchMaterialFragmentSelectedListener mCallback;

    Button name;
    Button search;

    SearchMaterialAsyncTask searchMaterialAsyncTask;
    ArrayList<Recipe> result;

    ArrayList<Material> materialList;
    ArrayList<Tool>toolList;

    Context mContext;

    // for search material and tools
    public SearchMaterial() {
    }

    @SuppressLint("ValidFragment")
    public SearchMaterial(ArrayList<Material> mList, ArrayList<Tool> tList) {
        materialList = mList;
        toolList = tList;
    }

    public interface OnSearchMaterialFragmentSelectedListener {
        public void onNameSelected();
        public void onMaterialSearchResult(ArrayList<Recipe> result);
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        mContext = (Context)activity;

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnSearchMaterialFragmentSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnSearchMaterialFragmentSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_searchmaterial, container, false);

        /*
        materialList = new ArrayList<Material>();
        toolList = new ArrayList<Tool>();
        */
        //-------------------------------------
        /*
        Material materialData;

        for(int i = 0; i < 24; i++) // TODO: total number of materials from database
        {
            // TODO: name, url from database
            materialData = new Material(R.drawable.material01 + i, (String) ("Material" + i), "https://portasilo.com/portasilo/wp-content/uploads/sites/7/2015/02/icon-food.png", i);
            materialList.add(materialData);
        }
        */

        final MaterialTextAdapter materialAdapter = new MaterialTextAdapter(mContext, R.layout.material_item, materialList);

        ListView materialList;
        materialList = (ListView)rootView.findViewById(R.id.materialList);
        materialList.setAdapter(materialAdapter);

        materialList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(materialAdapter.getNumberOfCheckedItem() == 10) {
                    if (materialAdapter.getCheckBoxState(position)) {
                        materialAdapter.setCheckBoxState(position, false);
                    } else {
                        // not possible
                    }
                }else {
                    if (materialAdapter.getCheckBoxState(position)) {
                        materialAdapter.setCheckBoxState(position, false);
                    } else {
                        materialAdapter.setCheckBoxState(position, true);
                    }
                }
                materialAdapter.getView(position, view, parent);
            }
        });
        //-------------------------------------

        //-------------------------------------
        /*
        Tool toolData;
        for(int i = 0; i < 5; i++) // TODO: total number of tools from database
        {
            // TODO: name, url from database
            toolData = new Tool(R.drawable.material01 + i, (String) ("Tool" + i), "http://www.cuisines-kocher-metz.fr/images/icon-pan.png", i);
            toolList.add(toolData);
        }
        */

        final ToolTextAdapter adapter = new ToolTextAdapter(mContext, R.layout.tool_item, toolList);

        ListView list;
        list = (ListView)rootView.findViewById(R.id.toolList);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(adapter.getNumberOfCheckedItem() == 1) {
                    if (adapter.getCheckBoxState(position)) {
                        adapter.setCheckBoxState(position, false);
                    } else {
                        // not possible
                    }
                }else {
                    if (adapter.getCheckBoxState(position)) {
                        adapter.setCheckBoxState(position, false);
                    } else {
                        adapter.setCheckBoxState(position, true);
                    }
                }
                adapter.getView(position, view, parent);
            }
        });
        //-------------------------------------

        // buttons
        name = (Button)rootView.findViewById(R.id.btn_name);
        search = (Button)rootView.findViewById(R.id.btn_searchbyname);

        // button events
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onNameSelected();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO: clickbox check routine
                SearchManager searchManager = new SearchManager();
                int selectedMaterial = 0;
                for(int i = 0; i < 24; i++){
                    if(materialAdapter.getCheckBoxState(i))
                        selectedMaterial+=materialAdapter.getItemIndex(i);
                }

                int selectedTool = 0;
                for(int i = 0; i < 5; i++){
                    if(adapter.getCheckBoxState(i))
                        selectedTool+=adapter.getItemIndex(i);
                }
                searchMaterialAsyncTask = new SearchMaterialAsyncTask();
                searchMaterialAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, selectedMaterial, selectedTool);
            }
        });

        return rootView;
    }

    private class SearchMaterialAsyncTask extends AsyncTask<Integer, Void, ArrayList<Recipe>> {

        @Override
        protected ArrayList<Recipe> doInBackground(Integer... params) {
            SearchManager s = new SearchManager();
            result = s.reqByIngredient(params[0], params[1]);
            return result;
        }

        protected void onPostExecute(ArrayList<Recipe> r) {
            super.onPostExecute(r);
            for(int i=0; i < r.size(); i++) {
                Log.d("Sync", r.get(i).getName());
            }
            mCallback.onMaterialSearchResult(r);
        }
    }

}