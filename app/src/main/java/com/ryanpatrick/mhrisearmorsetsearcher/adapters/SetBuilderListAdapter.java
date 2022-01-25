package com.ryanpatrick.mhrisearmorsetsearcher.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ryanpatrick.mhrisearmorsetsearcher.databinding.SetBuilderListItemBinding;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.ArmorSet;

import java.util.List;

public class SetBuilderListAdapter extends RecyclerView.Adapter<SetBuilderListAdapter.ViewHolder> {
    private final Context context;
    private final List<ArmorSet> setList;

    public SetBuilderListAdapter(List<ArmorSet> setList, Context  context){
        this.context = context;
        this.setList = setList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(SetBuilderListItemBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //region define variables for use in the viewholder
        SetBuilderListItemBinding binding = holder.binding;
        ArmorSet set = setList.get(position);
        SkillsListAdapter adapter = new SkillsListAdapter(set.getTotalSkills(), context);
        //endregion

        binding.helmetText.setText(context.getResources().getIdentifier(set.getHead().getArmorName(), 
                "string", context.getPackageName()));
        binding.chestText.setText(context.getResources().getIdentifier(set.getChest().getArmorName(),
                "string", context.getPackageName()));
        binding.armsText.setText(context.getResources().getIdentifier(set.getArms().getArmorName(),
                "string", context.getPackageName()));
        binding.waistText.setText(context.getResources().getIdentifier(set.getWaist().getArmorName(),
                "string", context.getPackageName()));
        binding.legsText.setText(context.getResources().getIdentifier(set.getLegs().getArmorName(),
                "string", context.getPackageName()));

        binding.level1Slots.setText(Integer.toString(set.getTotalSlots()[0]));
        binding.level2Slots.setText(Integer.toString(set.getTotalSlots()[1]));
        binding.level3Slots.setText(Integer.toString(set.getTotalSlots()[2]));

        //binding.totalSkillListView.setLayoutManager(new LinearLayoutManager(context));
        binding.totalSkillListView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return setList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public SetBuilderListItemBinding binding;
        public ViewHolder(SetBuilderListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }


}
