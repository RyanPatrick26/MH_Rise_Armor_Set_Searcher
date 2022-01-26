package com.ryanpatrick.mhrisearmorsetsearcher.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ryanpatrick.mhrisearmorsetsearcher.R;
import com.ryanpatrick.mhrisearmorsetsearcher.databinding.CharmListItemBinding;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Charm;

import java.util.List;

public class CharmListAdapter extends RecyclerView.Adapter<CharmListAdapter.ViewHolder>{
    List<Charm> charmList;
    Context context;

    public CharmListAdapter(List<Charm> charmList, Context context) {
        this.charmList = charmList;
        this.context = context;
    }

    @NonNull
    @Override
    public CharmListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(CharmListItemBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CharmListAdapter.ViewHolder holder, int position) {
        CharmListItemBinding binding = holder.binding;
        Charm charm = charmList.get(position);
        ImageView[] slotImageViews = new ImageView[]{binding.slotLayout.slot1, binding.slotLayout.slot2, binding.slotLayout.slot3};

        binding.skill1Layout.skillName.setText(context.getResources()
                .getIdentifier(charm.getSkill1().getSkillName(), "string", context.getPackageName()));
        binding.skill1Layout.skillLevel.setText(Integer.toString(charm.getSkill1().getSkillLevel()));
        binding.skill2Layout.skillName.setText(context.getResources()
                .getIdentifier(charm.getSkill2().getSkillName(), "string", context.getPackageName()));
        binding.skill2Layout.skillLevel.setText(Integer.toString(charm.getSkill2().getSkillLevel()));

        for (int i = 0; i < slotImageViews.length; i++) {
                switch (charm.getSlots()[i]){
                    case 0:
                        slotImageViews[i].setImageResource(R.drawable.no_slots);
                        break;
                    case 1:
                        slotImageViews[i].setImageResource(R.drawable.slot_lvl_1);
                        break;
                    case 2:
                        slotImageViews[i].setImageResource(R.drawable.slot_lvl_2);
                        break;
                    case 3:
                        slotImageViews[i].setImageResource(R.drawable.slot_lvl_3);
                        break;
                    default:
                        break;
                }
            }

    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CharmListItemBinding binding;
        public ViewHolder(@NonNull CharmListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
