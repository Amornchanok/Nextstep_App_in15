package com.amornchanok.nextstep_app;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.LayoutInflater;
import java.util.List;

public class SuggestListAdapter extends RecyclerView.Adapter<SuggestListAdapter.MyView> {

    private List<String> studiolist;

    public class MyView extends RecyclerView.ViewHolder {

        TextView studio_name;
        TextView studio_location;

        public MyView(View view)
        {
            super(view);
            studio_name = (TextView)view.findViewById(R.id.studio_name);
            studio_location = (TextView)view.findViewById(R.id.studio_location);
        }
    }

    public SuggestListAdapter(List<String> horizontalList)
    {
        this.studiolist = horizontalList;
    }


    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_suggest, parent, false);
        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position)
    {
        holder.studio_name.setText(studiolist.get(position));
    }

    @Override
    public int getItemCount()
    {
        return studiolist.size();
    }
}
