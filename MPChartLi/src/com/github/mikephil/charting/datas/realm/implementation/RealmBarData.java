package com.github.mikephil.charting.datas.realm.implementation;

import com.github.mikephil.charting.datas.BarData;
import com.github.mikephil.charting.datas.realm.base.RealmUtils;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.List;

import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by Philipp Jahoda on 19/12/15.
 */
public class RealmBarData extends BarData {

    public RealmBarData(RealmResults<? extends RealmObject> result, String xValuesField, List<IBarDataSet> dataSets) {
        super(RealmUtils.toXVals(result, xValuesField), dataSets);
    }
}
