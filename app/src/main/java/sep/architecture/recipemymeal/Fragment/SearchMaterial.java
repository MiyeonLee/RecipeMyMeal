package sep.architecture.recipemymeal.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import sep.architecture.recipemymeal.Material;
import sep.architecture.recipemymeal.MaterialTextAdapter;
import sep.architecture.recipemymeal.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class SearchMaterial extends Fragment {
    OnMainActivityFragmentSelectedListener mCallback;

    Button name;
    Button search;

    ArrayList<Material> materialList;

    Context mContext;

    // for search material and tools
    public SearchMaterial() {
    }

    public interface OnMainActivityFragmentSelectedListener {
        public void onNameSelected();
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        mContext = (Context)activity;

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnMainActivityFragmentSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnMainActivityFragmentSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_searchmaterial, container, false);

        materialList = new ArrayList<Material>();

        Material materialData;
        for(int i = 0; i < 24; i++)
        {
            materialData = new Material(R.drawable.material01+i, (String)("Material" + i));
            materialList.add(materialData);
        }

        MaterialTextAdapter adapter = new MaterialTextAdapter(mContext, R.layout.material_item, materialList);

        ListView list;
        list = (ListView)rootView.findViewById(R.id.materialList);
        list.setAdapter(adapter);

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
            }
        });

        return rootView;
    }

}