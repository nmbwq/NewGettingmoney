package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;

import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.chartss.Chart;
import com.github.mikephil.charting.chartss.CombinedChart;
import com.github.mikephil.charting.chartss.CombinedChart.DrawOrder;
import com.github.mikephil.charting.datas.ChartData;
import com.github.mikephil.charting.datas.CombinedData;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Renderer class that is responsible for rendering multiple different data-types.
 */
public class CombinedChartRenderer extends DataRenderer {

    /**
     * all rederers for the different kinds of data this combined-renderer can draw
     */
    protected List<DataRenderer> mRenderers;

    protected WeakReference<Chart> mChart;

    public CombinedChartRenderer(CombinedChart chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(animator, viewPortHandler);
        mChart = new WeakReference<Chart>(chart);
        createRenderers(chart, animator, viewPortHandler);
    }

    /**
     * Creates the renderers needed for this combined-renderer in the required order. Also takes the DrawOrder into
     * consideration.
     *
     * @param chart
     * @param animator
     * @param viewPortHandler
     */
    protected void createRenderers(CombinedChart chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {

        mRenderers = new ArrayList<>();

        DrawOrder[] orders = chart.getDrawOrder();

        for (DrawOrder order : orders) {

            switch (order) {
                case BAR:
                    if (chart.getBarData() != null)
                        mRenderers.add(new BarChartRenderer(chart, animator, viewPortHandler));
                    break;
                case BUBBLE:
                    if (chart.getBubbleData() != null)
                        mRenderers.add(new BubbleChartRenderer(chart, animator, viewPortHandler));
                    break;
                case LINE:
                    if (chart.getLineData() != null)
                        mRenderers.add(new LineChartRenderer(chart, animator, viewPortHandler));
                    break;
                case CANDLE:
                    if (chart.getCandleData() != null)
                        mRenderers.add(new CandleStickChartRenderer(chart, animator, viewPortHandler));
                    break;
                case SCATTER:
                    if (chart.getScatterData() != null)
                        mRenderers.add(new ScatterChartRenderer(chart, animator, viewPortHandler));
                    break;
            }
        }
    }

    @Override
    public void initBuffers() {

        for (DataRenderer renderer : mRenderers)
            renderer.initBuffers();
    }

    @Override
    public void drawData(Canvas c) {

        for (DataRenderer renderer : mRenderers)
            renderer.drawData(c);
    }

    @Override
    public void drawValues(Canvas c) {

        for (DataRenderer renderer : mRenderers)
            renderer.drawValues(c);
    }

    @Override
    public void drawExtras(Canvas c) {

        for (DataRenderer renderer : mRenderers)
            renderer.drawExtras(c);
    }

    @Override
    public void drawHighlighted(Canvas c, Highlight[] indices) {

        Chart chart = mChart.get();
        if (chart == null) 
            return;

        for (DataRenderer renderer : mRenderers) {
            ChartData data = null;

            if (renderer instanceof BarChartRenderer)
                data = ((BarChartRenderer)renderer).mChart.getBarData();
            else if (renderer instanceof LineChartRenderer)
                data = ((LineChartRenderer)renderer).mChart.getLineData();
            else if (renderer instanceof CandleStickChartRenderer)
                data = ((CandleStickChartRenderer)renderer).mChart.getCandleData();
            else if (renderer instanceof ScatterChartRenderer)
                data = ((ScatterChartRenderer)renderer).mChart.getScatterData();
            else if (renderer instanceof BubbleChartRenderer)
                data = ((BubbleChartRenderer)renderer).mChart.getBubbleData();

            int dataIndex = data == null
                    ? -1
                    : ((CombinedData)chart.getData()).getAllData().indexOf(data);

            ArrayList<Highlight> dataIndices = new ArrayList<>();
            for (Highlight h : indices) {
                if (h.getDataIndex() == dataIndex || h.getDataIndex() == -1)
                    dataIndices.add(h);
            }

            renderer.drawHighlighted(c, dataIndices.toArray(new Highlight[dataIndices.size()]));

        }
    }

    @Override
    public void calcXBounds(BarLineScatterCandleBubbleDataProvider chart, int xAxisModulus) {
        for (DataRenderer renderer : mRenderers)
            renderer.calcXBounds(chart, xAxisModulus);
    }

    /**
     * Returns the sub-renderer object at the specified index.
     *
     * @param index
     * @return
     */
    public DataRenderer getSubRenderer(int index) {
        if (index >= mRenderers.size() || index < 0)
            return null;
        else
            return mRenderers.get(index);
    }

    /**
     * Returns all sub-renderers.
     *
     * @return
     */
    public List<DataRenderer> getSubRenderers() {
        return mRenderers;
    }

    public void setSubRenderers(List<DataRenderer> renderers) {
        this.mRenderers = renderers;
    }
}
