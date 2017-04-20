package com.github.mikephil.charting.interfaces.dataprovider;

import com.github.mikephil.charting.datas.ScatterData;

public interface ScatterDataProvider extends BarLineScatterCandleBubbleDataProvider {

    ScatterData getScatterData();
}
