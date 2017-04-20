package com.github.mikephil.charting.interfaces.dataprovider;

import com.github.mikephil.charting.datas.CandleData;

public interface CandleDataProvider extends BarLineScatterCandleBubbleDataProvider {

    CandleData getCandleData();
}
