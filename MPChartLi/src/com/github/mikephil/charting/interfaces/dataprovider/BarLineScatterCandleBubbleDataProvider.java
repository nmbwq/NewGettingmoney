package com.github.mikephil.charting.interfaces.dataprovider;

import com.github.mikephil.charting.componentss.YAxis.AxisDependency;
import com.github.mikephil.charting.datas.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.utils.Transformer;

public interface BarLineScatterCandleBubbleDataProvider extends ChartInterface {

    Transformer getTransformer(AxisDependency axis);
    int getMaxVisibleCount();
    boolean isInverted(AxisDependency axis);
    
    int getLowestVisibleXIndex();
    int getHighestVisibleXIndex();

    BarLineScatterCandleBubbleData getData();
}
