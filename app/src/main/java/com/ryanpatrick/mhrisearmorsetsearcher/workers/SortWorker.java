package com.ryanpatrick.mhrisearmorsetsearcher.workers;

import android.content.Context;
import android.util.Log;

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
        WorkerDataHolder.getInstance().setSetListString(Convertors.fromSetList(armorSetList));
        WorkerDataHolder.getInstance().setSetList(new ArrayList<>());
        return Result.success();
    }
}
