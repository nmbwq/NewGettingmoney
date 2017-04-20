package money.com.gettingmoney.fragment;

import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.chartss.BarChart;
import com.github.mikephil.charting.chartss.Chart;
import com.github.mikephil.charting.chartss.CombinedChart;
import com.github.mikephil.charting.componentss.Legend;
import com.github.mikephil.charting.componentss.XAxis;
import com.github.mikephil.charting.componentss.YAxis;
import com.github.mikephil.charting.datas.BarData;
import com.github.mikephil.charting.datas.BarDataSet;
import com.github.mikephil.charting.datas.BarEntry;
import com.github.mikephil.charting.datas.CandleData;
import com.github.mikephil.charting.datas.CandleDataSet;
import com.github.mikephil.charting.datas.CandleEntry;
import com.github.mikephil.charting.datas.CombinedData;
import com.github.mikephil.charting.datas.Entry;
import com.github.mikephil.charting.datas.LineData;
import com.github.mikephil.charting.datas.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import money.com.gettingmoney.R;
import money.com.gettingmoney.Test_Kline.MyUtils;
import money.com.gettingmoney.Test_Kline.VolFormatter;
import money.com.gettingmoney.bai.model.PlateStock_bean;
import money.com.gettingmoney.bean.Indexdetials_Klinebean;
import money.com.gettingmoney.charts.CoupleChartGestureListener;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.webutil.hangqing.indexuti;
import money.com.gettingmoney.weiget.LoadingDialog;

/**
 * Created by Administrator on 2017/4/17.
 */
public class Day_KlineFragment extends Fragment {


    CombinedChart combinedchart_byday;
    BarChart barChart_byday;
    XAxis xAxisBar, xAxisK;
    YAxis axisLeftBar, axisLeftK;
    YAxis axisRightBar, axisRightK;
    BarDataSet barDataSet;
    String stockId, type;//从板块的详情页面传递过来的股票id....    type 用来判断是否是从那个界面传递过来的--例如注释列表详情，搜索列表详情，板块详情等

    private LoadingDialog dialogbyday;
    float volmax;//万手里的平均值
    private List<PlateStock_bean.ListBean> stockHistoryListBeans = new ArrayList<>();
    private List<Indexdetials_Klinebean.ListBean> listBeans = new ArrayList<>();

    TextView open;//测试开盘价

    float sum = 0;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //日k线
            barChart_byday.setAutoScaleMinMaxEnabled(true);
            combinedchart_byday.setAutoScaleMinMaxEnabled(true);

            combinedchart_byday.notifyDataSetChanged();
            barChart_byday.notifyDataSetChanged();

            combinedchart_byday.invalidate();
            barChart_byday.invalidate();


        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fg_dayline, container, false);
        open = (TextView) view.findViewById(R.id.open);

        combinedchart_byday = (CombinedChart) view.findViewById(R.id.combinedchart_byday);
        barChart_byday = (BarChart) view.findViewById(R.id.barChart_byday);

        Bundle bundle = getArguments();//从activity传过来的Bundle
        if (bundle != null) {
            stockId = bundle.getString("stockId");
            type = bundle.getString("type");
            dialogbyday = new LoadingDialog(getActivity(), "正在加载日K线图");
            dialogbyday.show();
            if ("板块".equals(type) && !("").equals(String.valueOf(stockId))) {

                K_getByDay();
                initChart();

            } else if ("指数".equals(type) && !("").equals(String.valueOf(stockId))) {
                K_shocked();
                initChart();
            } else if ("港股指数".equals(type) && !("").equals(String.valueOf(stockId))) {
                K_shockedGang();
                initChart();
            }else if ("沪深指数".equals(type) && !("").equals(String.valueOf(stockId))) {
                K_shocked();
                initChart();
            }



        }


        return view;

    }


    //板块的日k线图股票网络
    void K_getByDay() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                indexuti indexuti = new indexuti();
                indexuti.domainStockgetByDay(dialogbyday, stockId, new MyXutils.XCallBack() {
                    @Override
                    public void onResponse(String result) {

                        //解析
                        PlateStock_bean Bean = new Gson().fromJson(result, new TypeToken<PlateStock_bean>() {
                        }.getType());
                        stockHistoryListBeans = Bean.getList();
                        //把值传递到K线图里
                        setData(stockHistoryListBeans);

                        stockHistoryListBeans.clear();

                        dialogbyday.close();
                    }
                });
            }
        };
        new Thread(runnable).start();

    }


    //指数的日K线图股票网络---指数列表的K线图和港股指数的K线图以及沪深指数的K线图是同一个接口但是如果是港股指数的话就需要多上传hongType字段
    void K_shocked() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                indexuti indexuti = new indexuti();

                indexuti.indexbranchTime(dialogbyday, stockId, "day", new MyXutils.XCallBack() {
                    @Override
                    public void onResponse(String result) {
                        Log.i("指数的日k线图股票接口111111", result);
                        //解析
                        Indexdetials_Klinebean Bean = new Gson().fromJson(result, new TypeToken<Indexdetials_Klinebean>() {
                        }.getType());
                        listBeans = Bean.getList();
                        setDataIndex(listBeans);
                        dialogbyday.close();

                    }
                });
            }
        };
        new Thread(runnable).start();
    }

    //指数的日K线图股票网络---指数列表的K线图和港股指数的K线图以及沪深指数的K线图是同一个接口但是如果是港股指数的话就需要多上传hongType字段
    //港股的日K线图
    void K_shockedGang() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                indexuti indexuti = new indexuti();

                indexuti.indexbranchTimeGang(dialogbyday, stockId, "day", 5, new MyXutils.XCallBack() {
                    @Override
                    public void onResponse(String result) {
                        Log.i("港股指数的日k线图", result);
                        //解析
                        Indexdetials_Klinebean Bean = new Gson().fromJson(result, new TypeToken<Indexdetials_Klinebean>() {
                        }.getType());
                        listBeans = Bean.getList();
                        setDataIndex(listBeans);
                        dialogbyday.close();

                    }
                });
            }
        };
        new Thread(runnable).start();
    }

    /*
    *
    * 板块的k线图的设置
    * */


    //初始表
    private void initChart() {
        barChart_byday.setDrawBorders(true);
        barChart_byday.setBorderWidth(1);
        barChart_byday.setBorderColor(getResources().getColor(R.color.minute_grayLine));
        barChart_byday.setDescription("");
        barChart_byday.setDragEnabled(true);
        barChart_byday.setScaleYEnabled(false);

        Legend barChartLegend = barChart_byday.getLegend();
        barChartLegend.setEnabled(false);

        //BarYAxisFormatter  barYAxisFormatter=new BarYAxisFormatter();
        //bar x y轴
        xAxisBar = barChart_byday.getXAxis();
        xAxisBar.setDrawLabels(true);
        xAxisBar.setDrawGridLines(false);
        xAxisBar.setDrawAxisLine(false);
        xAxisBar.setTextColor(getResources().getColor(R.color.minute_zhoutv));
        xAxisBar.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxisBar.setGridColor(getResources().getColor(R.color.minute_grayLine));

        axisLeftBar = barChart_byday.getAxisLeft();
        axisLeftBar.setAxisMinValue(0);
        axisLeftBar.setDrawGridLines(false);
        axisLeftBar.setDrawAxisLine(false);
        axisLeftBar.setTextColor(getResources().getColor(R.color.minute_zhoutv));
        axisLeftBar.setDrawLabels(true);
        axisLeftBar.setSpaceTop(0);
        axisLeftBar.setShowOnlyMinMax(true);
        axisRightBar = barChart_byday.getAxisRight();
        axisRightBar.setDrawLabels(false);
        axisRightBar.setDrawGridLines(false);
        axisRightBar.setDrawAxisLine(false);
        /****************************************************************/
        combinedchart_byday.setDrawBorders(true);
        combinedchart_byday.setBorderWidth(1);
        combinedchart_byday.setBorderColor(getResources().getColor(R.color.minute_grayLine));
        combinedchart_byday.setDescription("");
        combinedchart_byday.setDragEnabled(true);
        combinedchart_byday.setScaleYEnabled(false);

        Legend combinedchartLegend = combinedchart_byday.getLegend();
        combinedchartLegend.setEnabled(false);
        //K线图 x y轴
        xAxisK = combinedchart_byday.getXAxis();
        xAxisK.setDrawLabels(true);
        xAxisK.setDrawGridLines(false);
        xAxisK.setDrawAxisLine(false);
        xAxisK.setTextColor(getResources().getColor(R.color.minute_zhoutv));
        xAxisK.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxisK.setGridColor(getResources().getColor(R.color.minute_grayLine));

        axisLeftK = combinedchart_byday.getAxisLeft();
        axisLeftK.setDrawGridLines(true);
        axisLeftK.setDrawAxisLine(false);
        axisLeftK.setDrawLabels(true);
        axisLeftK.setTextColor(getResources().getColor(R.color.minute_zhoutv));
        axisLeftK.setGridColor(getResources().getColor(R.color.minute_grayLine));
        axisLeftK.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        axisRightK = combinedchart_byday.getAxisRight();
        axisRightK.setDrawLabels(false);
        axisRightK.setDrawGridLines(true);
        axisRightK.setDrawAxisLine(false);
        axisRightK.setGridColor(getResources().getColor(R.color.minute_grayLine));
        combinedchart_byday.setDragDecelerationEnabled(true);
        barChart_byday.setDragDecelerationEnabled(true);
        combinedchart_byday.setDragDecelerationFrictionCoef(0.2f);
        barChart_byday.setDragDecelerationFrictionCoef(0.2f);

        // 将K线控的滑动事件传递给交易量控件
        combinedchart_byday.setOnChartGestureListener(new CoupleChartGestureListener(combinedchart_byday, new Chart[]{barChart_byday}));
        // 将交易量控件的滑动事件传递给K线控件
        barChart_byday.setOnChartGestureListener(new CoupleChartGestureListener(barChart_byday, new Chart[]{combinedchart_byday}));
        /*
        * 长按出现高亮联动
        * */
        barChart_byday.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                Log.e("%%%%", h.getXIndex() + "");
                combinedchart_byday.highlightValues(new Highlight[]{h});
            }

            @Override
            public void onNothingSelected() {
                combinedchart_byday.highlightValue(null);
            }
        });
        combinedchart_byday.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

                barChart_byday.highlightValues(new Highlight[]{h});

                open.setText(String.valueOf(h.getValue()));
            }

            @Override
            public void onNothingSelected() {
                barChart_byday.highlightValue(null);
            }
        });

    }

    //计算从当前天往前推5天（包含当天），每天的收盘价之和除以5，得到当天的MA5值
    private float getSum(Integer a, Integer b) {

        for (int i = a; i <= b; i++) {
            sum += stockHistoryListBeans.get(i).getClose();
        }
        return sum;
    }

    private float culcMaxscale(float count) {
        float max = 1;
        max = count / 127 * 5;
        return max;
    }

    private void setData(List<PlateStock_bean.ListBean> stockHistoryListBeans) {
        int size = stockHistoryListBeans.size();

        for (int i = 0; i < stockHistoryListBeans.size(); i++) {
            volmax = Math.max(stockHistoryListBeans.get(i).getVol(), volmax);
        }
        String unit = MyUtils.getVolUnit(volmax);
        int u = 1;
        if ("万手".equals(unit)) {
            u = 4;
        } else if ("亿手".equals(unit)) {
            u = 8;
        }
        axisLeftBar.setValueFormatter(new VolFormatter((int) Math.pow(10, u)));
        Log.e("@@@", volmax + "da");

        ArrayList<String> xVals = new ArrayList<>();
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<CandleEntry> candleEntries = new ArrayList<>();
        ArrayList<Entry> line5Entries = new ArrayList<>();
        ArrayList<Entry> line10Entries = new ArrayList<>();
        ArrayList<Entry> line30Entries = new ArrayList<>();
        for (int i = 0, j = 0; i < stockHistoryListBeans.size(); i++, j++) {
            xVals.add(stockHistoryListBeans.get(i).getUpdateTime() + "");
            barEntries.add(new BarEntry(stockHistoryListBeans.get(i).getVol(), i));
            candleEntries.add(new CandleEntry(i, stockHistoryListBeans.get(i).getHigh(), stockHistoryListBeans.get(i).getLow(), stockHistoryListBeans.get(i).getOpen(), stockHistoryListBeans.get(i).getClose()));
            if (i >= 4) {
                sum = 0;
                line5Entries.add(new Entry(getSum(i - 4, i) / 5, i));
            }
            if (i >= 9) {
                sum = 0;
                line10Entries.add(new Entry(getSum(i - 9, i) / 10, i));
            }
            if (i >= 29) {
                sum = 0;
                line30Entries.add(new Entry(getSum(i - 29, i) / 30, i));
            }

        }
        barDataSet = new BarDataSet(barEntries, "成交量");
        barDataSet.setBarSpacePercent(50); //bar空隙
        barDataSet.setHighlightEnabled(true);
        barDataSet.setHighLightAlpha(255);
        barDataSet.setHighLightColor(Color.WHITE);
        barDataSet.setDrawValues(false);
        barDataSet.setColor(Color.RED);
        BarData barData = new BarData(xVals, barDataSet);
        barChart_byday.setData(barData);
        final ViewPortHandler viewPortHandlerBar = barChart_byday.getViewPortHandler();
        viewPortHandlerBar.setMaximumScaleX(culcMaxscale(xVals.size()));
        Matrix touchmatrix = viewPortHandlerBar.getMatrixTouch();
        final float xscale = 3;
        touchmatrix.postScale(xscale, 1f);


        CandleDataSet candleDataSet = new CandleDataSet(candleEntries, "KLine");
        candleDataSet.setDrawHorizontalHighlightIndicator(false);
        candleDataSet.setHighlightEnabled(true);
        candleDataSet.setHighLightColor(Color.WHITE);
        candleDataSet.setValueTextSize(10f);
        candleDataSet.setDrawValues(false);
        candleDataSet.setColor(Color.RED);
        candleDataSet.setShadowWidth(1f);
        candleDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        CandleData candleData = new CandleData(xVals, candleDataSet);


        ArrayList<ILineDataSet> sets = new ArrayList<>();

        /******此处修复如果显示的点的个数达不到MA均线的位置所有的点都从0开始计算最小值的问题******************************/
        if (size >= 30) {
            sets.add(setMaLine(5, xVals, line5Entries));
            sets.add(setMaLine(10, xVals, line10Entries));
            sets.add(setMaLine(30, xVals, line30Entries));
        } else if (size >= 10 && size < 30) {
            sets.add(setMaLine(5, xVals, line5Entries));
            sets.add(setMaLine(10, xVals, line10Entries));
        } else if (size >= 5 && size < 10) {
            sets.add(setMaLine(5, xVals, line5Entries));
        }


        CombinedData combinedData = new CombinedData(xVals);
        LineData lineData = new LineData(xVals, sets);
        combinedData.setData(candleData);
        combinedData.setData(lineData);
        combinedchart_byday.setData(combinedData);
        combinedchart_byday.moveViewToX(stockHistoryListBeans.size() - 1);
        final ViewPortHandler viewPortHandlerCombin = combinedchart_byday.getViewPortHandler();
        viewPortHandlerCombin.setMaximumScaleX(culcMaxscale(xVals.size()));
        Matrix matrixCombin = viewPortHandlerCombin.getMatrixTouch();
        final float xscaleCombin = 3;
        matrixCombin.postScale(xscaleCombin, 1f);

        combinedchart_byday.moveViewToX(stockHistoryListBeans.size() - 1);
        barChart_byday.moveViewToX(stockHistoryListBeans.size() - 1);
        setOffset();

/****************************************************************************************
 此处解决方法来源于CombinedChartDemo，k线图y轴显示问题，图表滑动后才能对齐的bug，希望有人给出解决方法
 (注：此bug现已修复，感谢和chenguang79一起研究)
 ****************************************************************************************/

        handler.sendEmptyMessageDelayed(0, 300);

    }

    @NonNull
    private LineDataSet setMaLine(int ma, ArrayList<String> xVals, ArrayList<Entry> lineEntries) {
        LineDataSet lineDataSetMa = new LineDataSet(lineEntries, "ma" + ma);
        if (ma == 5) {
            lineDataSetMa.setHighlightEnabled(true);
            lineDataSetMa.setDrawHorizontalHighlightIndicator(false);
            lineDataSetMa.setHighLightColor(Color.WHITE);
        } else {/*此处必须得写*/
            lineDataSetMa.setHighlightEnabled(false);
        }
        lineDataSetMa.setDrawValues(false);
        if (ma == 5) {
            lineDataSetMa.setColor(Color.GREEN);
        } else if (ma == 10) {
            lineDataSetMa.setColor(Color.GRAY);
        } else {
            lineDataSetMa.setColor(Color.YELLOW);
        }
        lineDataSetMa.setLineWidth(1f);
        lineDataSetMa.setDrawCircles(false);
        lineDataSetMa.setAxisDependency(YAxis.AxisDependency.LEFT);
        return lineDataSetMa;
    }

    /*设置量表对齐*/
    private void setOffset() {
        float lineLeft = combinedchart_byday.getViewPortHandler().offsetLeft();
        float barLeft = barChart_byday.getViewPortHandler().offsetLeft();
        float lineRight = combinedchart_byday.getViewPortHandler().offsetRight();
        float barRight = barChart_byday.getViewPortHandler().offsetRight();
        float barBottom = barChart_byday.getViewPortHandler().offsetBottom();
        float offsetLeft, offsetRight;
        float transLeft = 0, transRight = 0;
 /*注：setExtraLeft...函数是针对图表相对位置计算，比如A表offLeftA=20dp,B表offLeftB=30dp,则A.setExtraLeftOffset(10),并不是30，还有注意单位转换*/
        if (barLeft < lineLeft) {
           /* offsetLeft = Utils.convertPixelsToDp(lineLeft - barLeft);
            barChart.setExtraLeftOffset(offsetLeft);*/
            transLeft = lineLeft;
        } else {
            offsetLeft = Utils.convertPixelsToDp(barLeft - lineLeft);
            combinedchart_byday.setExtraLeftOffset(offsetLeft);
            transLeft = barLeft;
        }
  /*注：setExtraRight...函数是针对图表绝对位置计算，比如A表offRightA=20dp,B表offRightB=30dp,则A.setExtraLeftOffset(30),并不是10，还有注意单位转换*/
        if (barRight < lineRight) {
          /*  offsetRight = Utils.convertPixelsToDp(lineRight);
            barChart.setExtraRightOffset(offsetRight);*/
            transRight = lineRight;
        } else {
            offsetRight = Utils.convertPixelsToDp(barRight);
            combinedchart_byday.setExtraRightOffset(offsetRight);
            transRight = barRight;
        }
        barChart_byday.setViewPortOffsets(transLeft, 15, transRight, barBottom);
    }






    /*
    *
    * 指数的k线图设置
    *
    * */


    //计算从当前天往前推5天（包含当天），每天的收盘价之和除以5，得到当天的MA5值
    private float getSumIndex(Integer a, Integer b) {

        for (int i = a; i <= b; i++) {
            sum += listBeans.get(i).getClose();
        }
        return sum;
    }

    private float culcMaxscaleIndex(float count) {
        float max = 1;
        max = count / 127 * 5;
        return max;
    }

    private void setDataIndex(List<Indexdetials_Klinebean.ListBean> stockHistoryListBeans) {
        int size = stockHistoryListBeans.size();

        for (int i = 0; i < stockHistoryListBeans.size(); i++) {
            volmax = Math.max(stockHistoryListBeans.get(i).getVol(), volmax);
        }
        String unit = MyUtils.getVolUnit(volmax);
        int u = 1;
        if ("万手".equals(unit)) {
            u = 4;
        } else if ("亿手".equals(unit)) {
            u = 8;
        }
        axisLeftBar.setValueFormatter(new VolFormatter((int) Math.pow(10, u)));
        Log.e("@@@", volmax + "da");

        ArrayList<String> xVals = new ArrayList<>();
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<CandleEntry> candleEntries = new ArrayList<>();
        ArrayList<Entry> line5Entries = new ArrayList<>();
        ArrayList<Entry> line10Entries = new ArrayList<>();
        ArrayList<Entry> line30Entries = new ArrayList<>();
        for (int i = 0, j = 0; i < stockHistoryListBeans.size(); i++, j++) {
            xVals.add(stockHistoryListBeans.get(i).getTime() + "");
            barEntries.add(new BarEntry(stockHistoryListBeans.get(i).getVol(), i));
            candleEntries.add(new CandleEntry(i, stockHistoryListBeans.get(i).getHigh(), stockHistoryListBeans.get(i).getLow(), stockHistoryListBeans.get(i).getOpen(), stockHistoryListBeans.get(i).getClose()));
            if (i >= 4) {
                sum = 0;
                line5Entries.add(new Entry(getSumIndex(i - 4, i) / 5, i));
            }
            if (i >= 9) {
                sum = 0;
                line10Entries.add(new Entry(getSumIndex(i - 9, i) / 10, i));
            }
            if (i >= 29) {
                sum = 0;
                line30Entries.add(new Entry(getSumIndex(i - 29, i) / 30, i));
            }

        }
        barDataSet = new BarDataSet(barEntries, "成交量");
        barDataSet.setBarSpacePercent(50); //bar空隙
        barDataSet.setHighlightEnabled(true);
        barDataSet.setHighLightAlpha(255);
        barDataSet.setHighLightColor(Color.WHITE);
        barDataSet.setDrawValues(false);
        barDataSet.setColor(Color.RED);
        BarData barData = new BarData(xVals, barDataSet);
        barChart_byday.setData(barData);
        final ViewPortHandler viewPortHandlerBar = barChart_byday.getViewPortHandler();
        viewPortHandlerBar.setMaximumScaleX(culcMaxscaleIndex(xVals.size()));
        Matrix touchmatrix = viewPortHandlerBar.getMatrixTouch();
        final float xscale = 3;
        touchmatrix.postScale(xscale, 1f);


        CandleDataSet candleDataSet = new CandleDataSet(candleEntries, "KLine");
        candleDataSet.setDrawHorizontalHighlightIndicator(false);
        candleDataSet.setHighlightEnabled(true);
        candleDataSet.setHighLightColor(Color.WHITE);
        candleDataSet.setValueTextSize(10f);
        candleDataSet.setDrawValues(false);
        candleDataSet.setColor(Color.RED);
        candleDataSet.setShadowWidth(1f);
        candleDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        CandleData candleData = new CandleData(xVals, candleDataSet);


        ArrayList<ILineDataSet> sets = new ArrayList<>();

        /******此处修复如果显示的点的个数达不到MA均线的位置所有的点都从0开始计算最小值的问题******************************/
        if (size >= 30) {
            sets.add(setMaLine(5, xVals, line5Entries));
            sets.add(setMaLine(10, xVals, line10Entries));
            sets.add(setMaLine(30, xVals, line30Entries));
        } else if (size >= 10 && size < 30) {
            sets.add(setMaLine(5, xVals, line5Entries));
            sets.add(setMaLine(10, xVals, line10Entries));
        } else if (size >= 5 && size < 10) {
            sets.add(setMaLine(5, xVals, line5Entries));
        }


        CombinedData combinedData = new CombinedData(xVals);
        LineData lineData = new LineData(xVals, sets);
        combinedData.setData(candleData);
        combinedData.setData(lineData);
        combinedchart_byday.setData(combinedData);
        combinedchart_byday.moveViewToX(stockHistoryListBeans.size() - 1);
        final ViewPortHandler viewPortHandlerCombin = combinedchart_byday.getViewPortHandler();
        viewPortHandlerCombin.setMaximumScaleX(culcMaxscaleIndex(xVals.size()));
        Matrix matrixCombin = viewPortHandlerCombin.getMatrixTouch();
        final float xscaleCombin = 3;
        matrixCombin.postScale(xscaleCombin, 1f);

        combinedchart_byday.moveViewToX(stockHistoryListBeans.size() - 1);
        barChart_byday.moveViewToX(stockHistoryListBeans.size() - 1);
        setOffset();

/****************************************************************************************
 此处解决方法来源于CombinedChartDemo，k线图y轴显示问题，图表滑动后才能对齐的bug，希望有人给出解决方法
 (注：此bug现已修复，感谢和chenguang79一起研究)
 ****************************************************************************************/

        handler.sendEmptyMessageDelayed(0, 300);

    }


}
