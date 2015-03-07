package com.lambda.weibo.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.lambda.weibo.adapters.UserAdapter;
import com.lambda.weibo.app.R;
import com.lambda.weibo.fields.Status;
import com.lambda.weibo.fields.User;
import com.lambda.weibo.viewholders.StatusViewHolder;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UserFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {
    // TODO;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String USER = "user";
    private static final String STATUSES = "statuses";

    public void setUser(User user) {
        this.user = user;
        adapter.setUser(user);
    }

    public void setStatuses(ArrayList<Status> statuses) {
        this.statuses = statuses;
        adapter.setStatuses(statuses);
    }

    private User user;
    private ArrayList<Status> statuses;

    private RecyclerView userView;
    private UserAdapter adapter;
    private LinearLayoutManager layoutManager;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserFragment.
     */
    // TODO;
    public static UserFragment newInstance(User user, ArrayList<Status> statuses) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putParcelable(USER, user);
        args.putParcelableArrayList(STATUSES, statuses);
        fragment.setArguments(args);
        return fragment;
    }

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args;
        if(savedInstanceState != null){
            args = savedInstanceState;
        }else{
            args = getArguments();
        }

        if (args != null) {
            user = args.getParcelable(USER);
            statuses = args.getParcelableArrayList(STATUSES);
        }

        adapter = new UserAdapter(user, statuses);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(USER, user);
        outState.putParcelableArrayList(STATUSES, statuses);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment  ]
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        userView = (RecyclerView) view.findViewById(R.id.user_recycler_view);

        layoutManager = new LinearLayoutManager(view.getContext());
        userView.setLayoutManager(layoutManager);
        userView.setAdapter(adapter);
        return view;
    }

    // TODO;
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            //mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

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
     * <p/>
     * See the Android Training lesson <a href=
     * "http;
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO;
        public void onFragmentInteraction(Uri uri);
    }

}
