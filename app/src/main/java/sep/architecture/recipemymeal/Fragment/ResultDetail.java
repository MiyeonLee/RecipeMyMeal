package sep.architecture.recipemymeal.Fragment;

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

import sep.architecture.recipemymeal.Material;
import sep.architecture.recipemymeal.MaterialTextAdapter;
import sep.architecture.recipemymeal.R;

/**
 * Created by josephine.lee on 2015-08-11.
 */
public class ResultDetail extends Fragment {
    OnResultDetailFragmentSelectedListener mCallback;
    Context mContext;

    Button back;

    public ResultDetail() {
    }

    public interface OnResultDetailFragmentSelectedListener {
        public void onBackSelected();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_resultdetail, container, false);

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
