package com.github.mikephil.charting.interfaces.dataprovider;

import com.github.mikephil.charting.datas.BarData;

public interface BarDataProvider extends BarLineScatterCandleBubbleDataProvider {

    BarData getBarData();
    boolean isDrawBarShadowEnabled();
    boolean isDrawValueAboveBarEnabled();
    boolean isDrawHighlightArrowEnabled();
}
