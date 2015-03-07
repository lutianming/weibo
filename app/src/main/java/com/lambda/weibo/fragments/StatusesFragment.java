package com.lambda.weibo.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.lambda.weibo.adapters.StatusAdapter;
import com.lambda.weibo.app.R;
import com.lambda.weibo.fields.Status;
import com.lambda.weibo.listeners.StatusesRefreshListener;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class StatusesFragment extends Fragment{
    public  static final String TAG = "StatusesFragment";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String STATUSES = "statuses";
    private static final String ARG_PARAM2 = "param2";

    public void setStatuses(ArrayList<Status> statuses) {
        this.statuses = statuses;
        adapter.setStatuses(statuses);
    }

    // TODO: Rename and change types of parameters
    private ArrayList<Status> statuses;

    private OnFragmentInteractionListener mListener;

    private RecyclerView statusRecyclerView;
    private StatusAdapter adapter;
    private LinearLayoutManager layoutManager;

    // TODO: Rename and change types of parameters
    public static StatusesFragment newInstance(ArrayList<Status> statuses) {
        StatusesFragment fragment = new StatusesFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(STATUSES, statuses);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public StatusesFragment() {
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
            statuses = args.getParcelableArrayList(STATUSES);
        }

        adapter = new StatusAdapter(statuses);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STATUSES, statuses);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statuses_list, container, false);

        Activity activity = getActivity();
        statusRecyclerView = (RecyclerView)view.findViewById(R.id.statuses_recycler_view);

        layoutManager = new LinearLayoutManager(activity);
        statusRecyclerView.setLayoutManager(layoutManager);
        statusRecyclerView.setAdapter(adapter);
        //statusRecyclerView.setOnScrollListener(new StatusesScrollListener());

        SwipyRefreshLayout layout = (SwipyRefreshLayout) view.findViewById(R.id.swipyrefreshlayout);
        layout.setOnRefreshListener(new StatusesRefreshListener(activity));
        return view;
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
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onStatusSelected(JSONObject status);
    }

}
