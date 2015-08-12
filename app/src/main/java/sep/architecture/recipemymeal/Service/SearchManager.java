package sep.architecture.recipemymeal.Service;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sep.architecture.recipemymeal.R;
import sep.architecture.recipemymeal.Recipe;

/**
 * Created by josephine.lee on 2015-08-11.
 */
public class SearchManager extends ClientManager {

    private static final String TAG = SearchManager.class.getSimpleName();

    protected static final String TAG_RECIPE = "recipe";
    protected static final String TAG_NAME = "name";
    protected static final String TAG_URL = "img_url";
    protected static final String TAG_MHASH = "mhash";
    protected static final String TAG_THASH = "thash";
    protected static final String TAG_CONTENT = "content";

    JSONArray recipeArray = null;

    public Recipe[] reqByIngredient(int mhash, String tool) {
        Recipe[] recipes = null;
        String subAddress = "reqByIngredient.php";
        String requestAddress = SERVER_ADDRESS.concat(subAddress);

        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        //params.add(new BasicNameValuePair("mhash", mhash));

        JSONObject json = jParser.makeHttpRequest(requestAddress, "POST", params);

        try {
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {


            } else {
                // failed to request
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return recipes;
    }

    public ArrayList<Recipe> reqByName(String name) {

        ArrayList<Recipe> recipeList = new ArrayList<>();

        String subAddress = "reqByName.php";
        String requestAddress = SERVER_ADDRESS.concat(subAddress);

        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("name", name));

        JSONObject json = jParser.makeHttpRequest(requestAddress, "POST", params);

        try {
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                recipeArray = json.getJSONArray(TAG_RECIPE);

                for (int i = 0; i < recipeArray.length(); i++) {
                    JSONObject c = recipeArray.getJSONObject(i);

                    String rname = c.getString(TAG_NAME);
                    String url = c.getString(TAG_URL);
                    String mhash = c.getString(TAG_MHASH);
                    String thash = c.getString(TAG_THASH);
                    String content = c.getString(TAG_CONTENT);

                    Log.d(TAG, rname+url+mhash+thash+content );

                    Recipe r = new Recipe(R.drawable.material01, rname);

                    // adding HashList to ArrayList
                    recipeList.add(r);
                }

            } else {
                // failed to request
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return recipeList;
    }
}
