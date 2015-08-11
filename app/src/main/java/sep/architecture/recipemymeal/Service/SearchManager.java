package sep.architecture.recipemymeal.Service;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sep.architecture.recipemymeal.Recipe;

/**
 * Created by josephine.lee on 2015-08-11.
 */
public class SearchManager extends ClientManager {

    private static final String TAG = SearchManager.class.getSimpleName();
    private int success;

    public Recipe[] reqByIngredient(String[] recipe, String tool) {
        Recipe[] recipes = null;
        String subAddress = "reqByIngredient.php";
        String requestAddress = SERVER_ADDRESS.concat(subAddress);

        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        //params.add(new BasicNameValuePair("name", name));

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

    public Recipe reqByName(String name) {
        Recipe recipe = null;
        String subAddress = "reqByName.php";
        String requestAddress = SERVER_ADDRESS.concat(subAddress);

        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("name", name));
        //params.add(new BasicNameValuePair("price", price));
        //params.add(new BasicNameValuePair("description", description));

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

        return recipe;
    }

}
