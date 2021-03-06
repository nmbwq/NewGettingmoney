package com.github.mikephil.charting.datas.realm.implementation;

import com.github.mikephil.charting.datas.PieData;
import com.github.mikephil.charting.datas.realm.base.RealmUtils;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;

import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by Philipp Jahoda on 19/12/15.
 */
public class RealmPieData extends PieData {

    public RealmPieData(RealmResults<? extends RealmObject> result, String xValuesField, IPieDataSet dataSet) {
        super(RealmUtils.toXVals(result, xValuesField), dataSet);
    }
}
