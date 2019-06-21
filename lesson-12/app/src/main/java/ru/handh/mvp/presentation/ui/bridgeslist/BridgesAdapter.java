package ru.handh.mvp.presentation.ui.bridgeslist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import ru.handh.mvp.R;
import ru.handh.mvp.data.model.Bridge;

/**
 * Created by Igor Glushkov on 19.08.18.
 */
public class BridgesAdapter extends RecyclerView.Adapter<BridgesAdapter.BridgeViewHolder> {

    private List<Bridge> bridges = new ArrayList<>();


    @Override
    public BridgeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BridgeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bridge, parent, false));
    }

    @Override
    public void onBindViewHolder(BridgeViewHolder holder, int position) {
        holder.bind(bridges.get(position));
    }

    @Override
    public int getItemCount() {
        return bridges.size();
    }

    public void setBridges(List<Bridge> bridges) {
        this.bridges = bridges;
        notifyDataSetChanged();
    }

    static class BridgeViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle;

        public BridgeViewHolder(View itemView) {
            super(itemView);
            this.textViewTitle = itemView.findViewById(R.id.textViewTitle);
        }

        public void bind(Bridge bridge) {
            textViewTitle.setText(bridge.getName());
        }

    }
}
