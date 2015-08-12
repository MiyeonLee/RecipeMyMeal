package sep.architecture.recipemymeal.Service;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sep.architecture.recipemymeal.Material;
import sep.architecture.recipemymeal.R;
import sep.architecture.recipemymeal.Tool;

/**
 * Created by JEFF on 2015-08-12.
 */
public class MaterialToolManager extends ClientManager {

    protected static final String TAG_MATERIAL = "material";
    protected static final String TAG_NAME = "name";
    protected static final String TAG_URL = "url";
    protected static final String TAG_MHASH = "mhash";
    protected static final String TAG_TOOL = "tool";

    public ArrayList<Material> getMaterialList() {
        ArrayList<Material> materialList = new ArrayList<>();
        JSONArray materialArray;

        String subAddress = "getMaterialList.php";
        String requestAddress = SERVER_ADDRESS.concat(subAddress);

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("list","all"));

        JSONObject json = jParser.makeHttpRequest(requestAddress, "POST", params);

        try {
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {

                materialArray = json.getJSONArray(TAG_MATERIAL);

                for (int i = 0; i < materialArray.length(); i++) {
                    JSONObject c = materialArray.getJSONObject(i);

                    String rname = c.getString(TAG_NAME);
                    String url = c.getString(TAG_URL);
                    String mhash = c.getString(TAG_MHASH);

                    // Jeffrey.cho
                    // Need to modify Material constructor
                    Material m = new Material(R.drawable.material01, rname, url, Integer.parseInt(mhash));

                    // adding HashList to ArrayList
                    materialList.add(m);
                }
            } else {
                // failed to request
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return materialList;

    }


    public ArrayList<Tool> getToolList() {

        ArrayList<Tool> toolList = new ArrayList<>();
        JSONArray toolArray;

        String subAddress = "getToolList.php";
        String requestAddress = SERVER_ADDRESS.concat(subAddress);

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("list","all"));

        JSONObject json = jParser.makeHttpRequest(requestAddress, "POST", params);

        try {
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {

                toolArray = json.getJSONArray(TAG_TOOL);

                for (int i = 0; i < toolArray.length(); i++) {
                    JSONObject c = toolArray.getJSONObject(i);

                    String rname = c.getString(TAG_NAME);
                    String url = c.getString(TAG_URL);
                    String mhash = c.getString(TAG_MHASH);

                    // Jeffrey.cho
                    // Need to modify Tool constructor
                    Tool t = new Tool(R.drawable.material01, rname, url);

                    // adding HashList to ArrayList
                    toolList.add(t);
                }
            } else {
                // failed to request
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return toolList;
    }
}
