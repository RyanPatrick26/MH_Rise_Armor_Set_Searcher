package com.ryanpatrick.mhrisearmorsetsearcher.workers;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.ryanpatrick.mhrisearmorsetsearcher.model.pojos.ArmorSet;
import com.ryanpatrick.mhrisearmorsetsearcher.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class SetBuilderWorker extends Worker {
    public SetBuilderWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        List<ArmorSet> armorSetList = new ArrayList<>();

        Log.i("here", "doWork: " + getInputData().getString(Constants.SEARCH_SKILLS));

        if(armorSetList.isEmpty())
            return Result.failure();
        else
            return Result.success();
    }
}
