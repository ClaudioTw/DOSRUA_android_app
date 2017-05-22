package cominsert.example.lenovo.menu_ordering;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Default.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Default#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Default extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Default() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Default.
     */
    // TODO: Rename and change types and number of parameters
    public static Default newInstance(String param1, String param2) {
        Default fragment = new Default();
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
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_default, container, false);
        ImageButton apatizer = (ImageButton) view.findViewById(R.id.apatizer);
        ImageButton soup = (ImageButton) view.findViewById(R.id.soup);
        ImageButton pasta = (ImageButton) view.findViewById(R.id.pasta);
        ImageButton pizza = (ImageButton) view.findViewById(R.id.pizza);
        //For Apetizer
        apatizer.setClickable(true);
        apatizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //Fragment fragment=new DetailsApatizer();

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                DetailsApatizer llf = new DetailsApatizer();
                ft.replace(R.id.content_frame, llf);
                ft.commit();

            }
        });

        //For Soup

        soup.setClickable(true);
        soup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //Fragment fragment=new DetailsApatizer();

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                DetailsSoup llf = new DetailsSoup();
                ft.replace(R.id.content_frame, llf);
                ft.commit();

            }
        });

        //For Pasta

        pasta.setClickable(true);
        pasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //Fragment fragment=new DetailsApatizer();

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                DetailsPasta llf = new DetailsPasta();
                ft.replace(R.id.content_frame, llf);
                ft.commit();

            }
        });

        //For Soup

        pizza.setClickable(true);
        pizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //Fragment fragment=new DetailsApatizer();

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                DetailsPizza llf = new DetailsPizza();
                ft.replace(R.id.content_frame, llf);
                ft.commit();

            }
        });
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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

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


}
