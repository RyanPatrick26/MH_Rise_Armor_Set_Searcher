package com.ryanpatrick.mhrisearmorsetsearcher.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ryanpatrick.mhrisearmorsetsearcher.databinding.SetBuilderListItemBinding;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.ArmorSet;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Charm;

import java.util.HashMap;
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
        SkillsListAdapter skillsAdapter = new SkillsListAdapter(set.getAllSkills(), context);
        DecorationListAdapter decorationsAdapter = new DecorationListAdapter(set.getDecorations(), context);
        Charm charm = set.getCharm();
        HashMap<String, Integer> decorations = set.getDecorations();
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

        //set the text view for the sets charm, if one exists, otherwise hides the view
        if(charm != null){
            if(!charm.getSkill1().getSkillName().equals("----")) {
                binding.charmLayout.skill1Name.setText(context.getResources().getIdentifier(charm.getSkill1().getSkillName(),
                        "string", context.getPackageName()));
                binding.charmLayout.skill1Level.setText(Integer.toString(charm.getSkill1().getSkillLevel()));
            }
            else{
                binding.charmLayout.skill1Name.setVisibility(View.GONE);
                binding.charmLayout.skill1Level.setVisibility(View.GONE);
            }

            if(!charm.getSkill2().getSkillName().equals("----")){
                binding.charmLayout.skill2Name.setText(context.getResources().getIdentifier(charm.getSkill2().getSkillName(),
                        "string", context.getPackageName()));
                binding.charmLayout.skill2Level.setText(Integer.toString(charm.getSkill2().getSkillLevel()));
            }
            else{
                binding.charmLayout.skill2Name.setVisibility(View.GONE);
                binding.charmLayout.skill2Level.setVisibility(View.GONE);
            }
        }
        else{
            binding.charmLayout.getRoot().setVisibility(View.GONE);
        }

        //display how many of each level of slot the set has
        binding.level1Slots.setText(Integer.toString(set.getTotalSpareSlots()[0]));
        binding.level2Slots.setText(Integer.toString(set.getTotalSpareSlots()[1]));
        binding.level3Slots.setText(Integer.toString(set.getTotalSpareSlots()[2]));

        binding.decorationsList.setLayoutManager(new LinearLayoutManager(context));
        binding.decorationsList.setAdapter(decorationsAdapter);

        //set the adapter for the sets skills
        binding.totalSkillListView.setAdapter(skillsAdapter);
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
