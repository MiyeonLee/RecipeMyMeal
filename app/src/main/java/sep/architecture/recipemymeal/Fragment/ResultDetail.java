package sep.architecture.recipemymeal.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import sep.architecture.recipemymeal.R;
import sep.architecture.recipemymeal.Recipe;

/**
 * Created by josephine.lee on 2015-08-11.
 */
public class ResultDetail extends Fragment {
    OnResultDetailFragmentSelectedListener mCallback;
    Context mContext;

    Button back;
    Recipe recipe;

    public ResultDetail() {
    }

    @SuppressLint("ValidFragment")
    public ResultDetail(Recipe selectedRecipe) {
        recipe = selectedRecipe;
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

        TextView txt = (TextView)rootView.findViewById(R.id.recipe_title);
        txt.setText(recipe.getName());

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