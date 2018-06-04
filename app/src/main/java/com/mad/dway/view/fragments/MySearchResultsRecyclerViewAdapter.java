package com.mad.dway.view.fragments;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mad.dway.R;
import com.mad.dway.model.places.Place;
import com.mad.dway.view.fragments.SearchResultsFragment.OnListFragmentInteractionListener;
import com.mad.dway.view.fragments.dummy.DummyContent.DummyItem;

import java.util.ArrayList;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MySearchResultsRecyclerViewAdapter extends RecyclerView.Adapter<MySearchResultsRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<Place> mPlaces;
    private final OnListFragmentInteractionListener mListener;

    public MySearchResultsRecyclerViewAdapter(ArrayList<Place> places, OnListFragmentInteractionListener listener) {
        mPlaces = places;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_search_results, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mPlace = mPlaces.get(position);
        holder.mContentView.setText(mPlaces.get(position).getName());

        Log.d("set", "onclick");
        holder.mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mPlace);
                    Log.d("clicked", "it is clicked");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPlaces.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final Button mContentView;
        public Place mPlace;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (Button) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
