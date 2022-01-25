package com.ryanpatrick.mhrisearmorsetsearcher.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ryanpatrick.mhrisearmorsetsearcher.R;
import com.ryanpatrick.mhrisearmorsetsearcher.databinding.ArmorListItemBinding;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Armor;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Skill;

import java.util.List;

public class ArmorListAdapter extends RecyclerView.Adapter<ArmorListAdapter.ViewHolder>{
    private final List<Armor> armorList;
    private Context context;

    public ArmorListAdapter(List<Armor> armorList, Context context){
        this.armorList = armorList;
        this.context = context;
    }

    @NonNull
    @Override
    public ArmorListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ArmorListItemBinding.inflate(LayoutInflater.from(context), parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull ArmorListAdapter.ViewHolder holder, int position) {
        //region variables for the view holder
        ArmorListItemBinding armorListBinding = holder.binding;
        ImageView[] slotImageViews = new ImageView[]{armorListBinding.slot1, armorListBinding.slot2, armorListBinding.slot3};
        Armor armor = armorList.get(position);
        SkillsListAdapter adapter = new SkillsListAdapter(armor.getSkills(), context);
        //endregion

        //region set the textviews in the viewholder
        armorListBinding.armorNameText.setText(context.getResources().getIdentifier(armor.getArmorName(),
                "string", context.getPackageName()));
        switch (armor.getGender()){
            case Male:
                armorListBinding.genderTextView.setText(R.string.male);
                break;
            case Female:
                armorListBinding.genderTextView.setText(R.string.female);
                break;
            case Both:
                armorListBinding.genderTextView.setText(R.string.both);
                break;
            default:
                break;
        }
        switch (armor.getType()){
            case Head:
                armorListBinding.armorTypeTextView.setText(R.string.helmet);
                break;
            case Chest:
                armorListBinding.armorTypeTextView.setText(R.string.chest);
                break;
            case Arms:
                armorListBinding.armorTypeTextView.setText(R.string.arms);
                break;
            case Waist:
                armorListBinding.armorTypeTextView.setText(R.string.waist);
                break;
            case Legs:
                armorListBinding.armorTypeTextView.setText(R.string.legs);
                break;
            default:
                break;
        }

        for (int i = 0; i < slotImageViews.length; i++) {
            switch (armor.getSlots()[i]){
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

        armorListBinding.defenseLayout.defenseText.setText(String.format("%d/%d", armor.getBaseDefense(), armor.getMaxDefense()));
        armorListBinding.defenseLayout.fireResText.setText(String.format("%d", armor.getFireRes()));
        armorListBinding.defenseLayout.waterResText.setText(String.format("%d", armor.getWaterRes()));
        armorListBinding.defenseLayout.thunderResText.setText(String.format("%d", armor.getThunderRes()));
        armorListBinding.defenseLayout.iceResText.setText(String.format("%d", armor.getIceRes()));
        armorListBinding.defenseLayout.dragonResText.setText(String.format("%d", armor.getDragonRes()));

        armorListBinding.skillList.setAdapter(adapter);
        //endregion


    }


    @Override
    public int getItemCount() {
        return armorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ArmorListItemBinding binding;

        public ViewHolder(ArmorListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
