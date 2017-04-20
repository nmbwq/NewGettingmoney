package com.github.mikephil.charting.interfaces.dataprovider;

import com.github.mikephil.charting.componentss.YAxis;
import com.github.mikephil.charting.datas.LineData;

public interface LineDataProvider extends BarLineScatterCandleBubbleDataProvider {

    LineData getLineData();

    YAxis getAxis(YAxis.AxisDependency dependency);
}
