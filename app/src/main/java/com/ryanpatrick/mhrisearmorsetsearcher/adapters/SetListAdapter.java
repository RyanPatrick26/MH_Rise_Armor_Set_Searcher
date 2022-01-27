package com.ryanpatrick.mhrisearmorsetsearcher.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ryanpatrick.mhrisearmorsetsearcher.databinding.SetBuilderListItemBinding;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.ArmorSet;

import java.util.List;

public class SetListAdapter extends RecyclerView.Adapter<SetListAdapter.ViewHolder> {
    private final Context context;
    private final List<ArmorSet> setList;
    private OnSetClickListener onSetClickListener;

    public SetListAdapter(List<ArmorSet> setList, Context context, OnSetClickListener onSetClickListener){
        this.context = context;
        this.setList = setList;
        this.onSetClickListener = onSetClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(SetBuilderListItemBinding.
                inflate(LayoutInflater.from(context), parent, false), onSetClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //region define variables for use in the viewholder
        SetBuilderListItemBinding binding = holder.binding;
        ArmorSet set = setList.get(position);
        SkillsListAdapter adapter = new SkillsListAdapter(set.getTotalSkills(), context);
        //endregion

        //region set the text views for each of the set's individual pieces
        binding.piecesLayout.helmetText.setText(context.getResources().getIdentifier(set.getHead().getArmorName(),
                "string", context.getPackageName()));
        binding.piecesLayout.chestText.setText(context.getResources().getIdentifier(set.getChest().getArmorName(),
                "string", context.getPackageName()));
        binding.piecesLayout.armsText.setText(context.getResources().getIdentifier(set.getArms().getArmorName(),
                "string", context.getPackageName()));
        binding.piecesLayout.waistText.setText(context.getResources().getIdentifier(set.getWaist().getArmorName(),
                "string", context.getPackageName()));
        binding.piecesLayout.legsText.setText(context.getResources().getIdentifier(set.getLegs().getArmorName(),
                "string", context.getPackageName()));
        //endregion

        //display how many of each level of slot the set has
        binding.level1Slots.setText(Integer.toString(set.getTotalSlots()[0]));
        binding.level2Slots.setText(Integer.toString(set.getTotalSlots()[1]));
        binding.level3Slots.setText(Integer.toString(set.getTotalSlots()[2]));

        //set the adapter for the sets skills
        binding.totalSkillListView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return setList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public SetBuilderListItemBinding binding;
        OnSetClickListener onSetClickListener;
        public ViewHolder(SetBuilderListItemBinding binding, OnSetClickListener onSetClickListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.onSetClickListener = onSetClickListener;

            binding.getRoot().setOnClickListener(this);
        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            onSetClickListener.onSetClick(getAbsoluteAdapterPosition(), setList);
        }
    }

    public interface OnSetClickListener{
        void onSetClick(int position, List<ArmorSet> setList);
    }

}
