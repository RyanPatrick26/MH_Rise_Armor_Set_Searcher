package com.ryanpatrick.mhrisearmorsetsearcher.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
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

import java.util.List;

public class SkillDetailsAdapter extends RecyclerView.Adapter<SkillDetailsAdapter.ViewHolder>{
    private List<String> descriptionList;
    private Context context;
    public int skillLevel;

    public SkillDetailsAdapter(List<String> descriptionList, Context context){
        this.descriptionList = descriptionList;
        this.context = context;
    }

    @NonNull
    @Override
    public SkillDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.spinner_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillDetailsAdapter.ViewHolder holder, int position) {
        String description = descriptionList.get(position);
        holder.descriptionTextView.setText(description);
        if(position > skillLevel){
            holder.descriptionTextView.setTextColor(Color.GRAY);
        }
        else{
            int color = context.getResources().getConfiguration().uiMode == Configuration.UI_MODE_NIGHT_NO ?
                    android.R.color.secondary_text_light : android.R.color.secondary_text_dark;
            holder.descriptionTextView.setTextColor(context.getResources()
                    .getColor(color, context.getTheme()));
        }
    }

    @Override
    public int getItemCount() {
        return descriptionList.size();
    }

    public void setSkillLevel(int skillLevel){
        this.skillLevel = skillLevel;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView descriptionTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            descriptionTextView = itemView.findViewById(R.id.spinner_text);
        }
    }
}
