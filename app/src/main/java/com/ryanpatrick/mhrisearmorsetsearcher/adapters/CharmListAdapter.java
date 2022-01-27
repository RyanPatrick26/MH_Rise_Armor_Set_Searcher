package com.ryanpatrick.mhrisearmorsetsearcher.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ryanpatrick.mhrisearmorsetsearcher.R;
import com.ryanpatrick.mhrisearmorsetsearcher.databinding.CharmListItemBinding;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Charm;
import com.ryanpatrick.mhrisearmorsetsearcher.repositories.CharmRepository;

import java.util.List;

public class CharmListAdapter extends RecyclerView.Adapter<CharmListAdapter.ViewHolder>{
    List<Charm> charmList;
    Context context;
    CharmRepository repository;

    public CharmListAdapter(List<Charm> charmList, Context context) {
        Log.i("here", "CharmListAdapter: ");
        this.charmList = charmList;
        this.context = context;
        repository = new CharmRepository(context);
    }

    @NonNull
    @Override
    public CharmListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(CharmListItemBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CharmListAdapter.ViewHolder holder, int position) {
        //region set variables for the view holder
        CharmListItemBinding binding = holder.binding;
        Charm charm = charmList.get(position);
        ImageView[] slotImageViews = new ImageView[]{binding.slotLayout.slot1, binding.slotLayout.slot2, binding.slotLayout.slot3};
        //endregion

        //region setup the text for the charm's skill names
        //set to default ---- if no skill is listed, otherwise get the
        // string resource corresponding to the skill's name
        if(charm.getSkill1().getSkillName().equals("----")){
            binding.skill1Layout.skillName.setText("----");
            binding.skill1Layout.skillName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }
        else{
            binding.skill1Layout.skillName.setText(context.getResources()
                    .getIdentifier(charm.getSkill1().getSkillName(), "string", context.getPackageName()));
        }
        if (charm.getSkill2().getSkillName().equals("----")) {
            binding.skill2Layout.skillName.setText("----");
            binding.skill2Layout.skillName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }
        else{
            binding.skill2Layout.skillName.setText(context.getResources()
                    .getIdentifier(charm.getSkill2().getSkillName(), "string", context.getPackageName()));
        }
        //endregion

        //set the text for the charm's skill levels, leaving these fields blank if the skill's level is 0
        if(charm.getSkill1().getSkillLevel() > 0)
            binding.skill1Layout.skillLevel.setText(Integer.toString(charm.getSkill1().getSkillLevel()));
        if(charm.getSkill2().getSkillLevel() > 0)
            binding.skill2Layout.skillLevel.setText(Integer.toString(charm.getSkill2().getSkillLevel()));

        //set the images for each of the skills slots
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

        //delete the charm from the table when the user clicks the delete button
        binding.deleteCharmButton.setOnClickListener(v -> repository.deleteCharm(charm));

    }


    @Override
    public int getItemCount() {
        return charmList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CharmListItemBinding binding;
        public ViewHolder(@NonNull CharmListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
