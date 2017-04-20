package com.github.mikephil.charting.datas.realm.implementation;

import com.github.mikephil.charting.datas.ScatterData;
import com.github.mikephil.charting.datas.realm.base.RealmUtils;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;

import java.util.List;

import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by Philipp Jahoda on 19/12/15.
 */
public class RealmScatterData extends ScatterData {

    public RealmScatterData(RealmResults<? extends RealmObject> result, String xValuesField, List<IScatterDataSet> dataSets) {
        super(RealmUtils.toXVals(result, xValuesField), dataSets);
    }
}
