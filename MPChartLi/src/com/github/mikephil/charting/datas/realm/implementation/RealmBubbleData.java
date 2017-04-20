package com.github.mikephil.charting.datas.realm.implementation;

import com.github.mikephil.charting.datas.BubbleData;
import com.github.mikephil.charting.datas.realm.base.RealmUtils;
import com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet;

import java.util.List;

import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by Philipp Jahoda on 19/12/15.
 */
public class RealmBubbleData extends BubbleData {

    public RealmBubbleData(RealmResults<? extends RealmObject> result, String xValuesField, List<IBubbleDataSet> dataSets) {
        super(RealmUtils.toXVals(result, xValuesField), dataSets);
    }
}
