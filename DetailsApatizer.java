package cominsert.example.lenovo.menu_ordering;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailsApatizer.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailsApatizer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsApatizer extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String myJSON;

    private static final String TAG_RESULTS="result";
    private static final String TAG_ID = "id";
    private static final String TAG_MENUNAME = "mainmenu";
    private static final String TAG_NAME ="name";
    private static final String TAG_DESCR="descr";
    private static final String TAG_CALORIES="calories";
    private static final String TAG_PRICE="price";
    JSONArray peoples = null;

    ArrayList<HashMap<String, String>> personList;

    ListView list;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DetailsApatizer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailsApatizer.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailsApatizer newInstance(String param1, String param2) {
        DetailsApatizer fragment = new DetailsApatizer();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        System.out.println("###################################In Details apitizer");
        View view=inflater.inflate(R.layout.fragment_details_apatizer, container, false);
        list = (ListView)view.findViewById(R.id.listView);
        personList = new ArrayList<HashMap<String,String>>();
        getData();
        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

   /* @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
*/
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost(AppConfig.RET_APATIZER);

                // Depends on your web service
                httppost.setHeader("Content-type", "application/json");

                InputStream inputStream = null;
                String result = null;
                try {
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();

                    inputStream = entity.getContent();
                    // json is UTF-8 by default
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();


                    System.out.println("$$$$$$$$$$$$$$$$$$$$$$$"+result);
                } catch (Exception e) {
                    // Oops
                }
                finally {
                    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result){
                myJSON=result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();
    }

    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);
                String id = c.getString(TAG_ID);
                String menuname = c.getString(TAG_MENUNAME);
                String name = c.getString(TAG_NAME);
                String descr=c.getString(TAG_DESCR);
                String calories=c.getString(TAG_CALORIES);
                String price=c.getString(TAG_PRICE);

                HashMap<String,String> persons = new HashMap<String,String>();

                persons.put(TAG_ID,id);
                persons.put(TAG_MENUNAME,menuname);
                persons.put(TAG_NAME,name);
                persons.put(TAG_DESCR,descr);
                persons.put(TAG_CALORIES,calories);
                persons.put(TAG_PRICE,price);
                personList.add(persons);
                System.out.println("Personlist"+personList);
            }

          // ListAdapter adapter = new SimpleAdapter(DetailsApatizer.this, personList, R.layout.list_item,new String[]{TAG_ID,TAG_MENUNAME,TAG_NAME,TAG_DESCR,TAG_CALORIES,TAG_PRICE},new int[]{R.id.id, R.id.mainmenu,R.id.name,R.id.descr,R.id.calories,R.id.price});
            ListAdapter adapter = new SimpleAdapter(getActivity(), personList, R.layout.list_item,new String[]{TAG_ID,TAG_MENUNAME,TAG_NAME,TAG_DESCR,TAG_CALORIES,TAG_PRICE},new int[]{R.id.id,R.id.mainmenu, R.id.name,R.id.descr,R.id.calories,R.id.price});

            list.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
