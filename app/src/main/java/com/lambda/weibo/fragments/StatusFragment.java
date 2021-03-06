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
import com.lambda.weibo.adapters.StatusWithCommentAdapter;
import com.lambda.weibo.app.R;
import com.lambda.weibo.fields.Comment;
import com.lambda.weibo.fields.Status;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StatusFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StatusFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatusFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String TAG = "StatusFragment";
    private static final String STATUS = "status";
    private static final String COMMENTS = "comments";

    // TODO: Rename and change types of parameters
    private RecyclerView commentsView;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager layoutManager;

    private Status status;
    private ArrayList<Comment> comments;
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param status
     * @param comments
     * @return A new instance of fragment StatusFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatusFragment newInstance(Status status, ArrayList<Comment> comments) {
        StatusFragment fragment = new StatusFragment();
        Bundle args = new Bundle();
        args.putParcelable(STATUS, status);
        args.putParcelableArrayList(COMMENTS, comments);
        fragment.setArguments(args);
        return fragment;
    }

    public StatusFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args;
        if(savedInstanceState != null) {
            args = savedInstanceState;
        }else{
            args = getArguments();
        }

        if (args != null) {
            status = args.getParcelable(STATUS);
            comments = args.getParcelableArrayList(COMMENTS);
        }
        adapter = new StatusWithCommentAdapter(status, comments);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(STATUS, status);
        outState.putParcelableArrayList(COMMENTS, comments);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_status, container, false);

        Activity activity = getActivity();
        commentsView = (RecyclerView) view.findViewById(R.id.status_comments_recycle_view);

        layoutManager = new LinearLayoutManager(activity);
        commentsView.setLayoutManager(layoutManager);
        commentsView.setAdapter(adapter);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
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
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
