package com.ryanpatrick.mhrisearmorsetsearcher.workers;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.ArmorSet;
import com.ryanpatrick.mhrisearmorsetsearcher.util.Constants;
import com.ryanpatrick.mhrisearmorsetsearcher.util.Convertors;

import java.util.ArrayList;
import java.util.List;

public class SortWorker extends Worker {
    public SortWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        List<ArmorSet> armorSetList = WorkerDataHolder.getInstance().getSetList();
        WorkerDataHolder.getInstance().setSetList(new ArrayList<>());
        Data outputData = new Data.Builder()
                .putString(Constants.SET_LIST_TAG, Convertors.fromSetList(armorSetList))
                .build();
        return Result.success(outputData);
    }
}
