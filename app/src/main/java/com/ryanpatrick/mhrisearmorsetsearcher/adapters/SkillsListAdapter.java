package com.ryanpatrick.mhrisearmorsetsearcher.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ryanpatrick.mhrisearmorsetsearcher.R;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Skill;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SkillsListAdapter extends RecyclerView.Adapter<SkillsListAdapter.ViewHolder> {
    HashMap<String, Integer> skills;
    Context context;
    private OnSkillClickListener onSkillClickListener;
    private String[] skillNames;

    public SkillsListAdapter(HashMap<String, Integer> skills, Context context) {
        this.context = context;
        this.skills = skills;

        skillNames = skills.keySet().toArray(new String[0]);
    }

    @NonNull
    @Override
    public SkillsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.skills_list_item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull SkillsListAdapter.ViewHolder holder, int position) {
        String skillName = skillNames[position];
        holder.skillNameText.setText(context.getResources().getIdentifier(skillName, "string", context.getPackageName()));
        holder.skillLevelText.setText(Integer.toString(skills.get(skillName)));
    }

    @Override
    public int getItemCount() {
        return skills.size();
    }

    public void setOnSkillClickListener(OnSkillClickListener onSkillClickListener) {
        this.onSkillClickListener = onSkillClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView skillNameText;
        public TextView skillLevelText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            skillNameText = itemView.findViewById(R.id.skill_name);
            skillLevelText = itemView.findViewById(R.id.skill_level);

            itemView.setOnClickListener(v ->{
                if(onSkillClickListener != null){
                    onSkillClickListener.onSkillClick(skillNameText.getText().toString(), getAbsoluteAdapterPosition());
                }
            });
        }
    }

    public interface OnSkillClickListener{
        void onSkillClick(String skillName, int position);
    }
}
