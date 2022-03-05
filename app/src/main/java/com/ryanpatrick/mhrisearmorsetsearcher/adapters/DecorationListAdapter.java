package com.ryanpatrick.mhrisearmorsetsearcher.adapters;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ryanpatrick.mhrisearmorsetsearcher.databinding.ArmorListItemBinding;
import com.ryanpatrick.mhrisearmorsetsearcher.databinding.SkillsListItemBinding;
import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.Decoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;

public class DecorationListAdapter extends RecyclerView.Adapter<DecorationListAdapter.ViewHolder>{
    Context context;
    List<Pair<String, Integer>> decorationsList;

    public DecorationListAdapter(HashMap<String, Integer> decorations, Context context){
        this.context = context;
        decorationsList = new ArrayList<>();
        decorations.forEach((s, integer) -> decorationsList.add(new Pair<>(s, integer)));
    }

    @NonNull
    @Override
    public DecorationListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(SkillsListItemBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DecorationListAdapter.ViewHolder holder, int position) {
        Pair<String, Integer> decoration = decorationsList.get(position);
        holder.binding.skillName.setText(decoration.first);
        holder.binding.skillLevel.setText(Integer.toString(decoration.second));
    }

    @Override
    public int getItemCount() {
        return decorationsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public SkillsListItemBinding binding;
        public ViewHolder(SkillsListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
