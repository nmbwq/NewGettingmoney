package money.com.gettingmoney.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.componentss.Legend;
import com.github.mikephil.charting.componentss.LimitLine;
import com.github.mikephil.charting.componentss.XAxis;
import com.github.mikephil.charting.componentss.YAxis;
import com.github.mikephil.charting.datas.BarData;
import com.github.mikephil.charting.datas.BarDataSet;
import com.github.mikephil.charting.datas.BarEntry;
import com.github.mikephil.charting.datas.Entry;
import com.github.mikephil.charting.datas.LineData;
import com.github.mikephil.charting.datas.LineDataSet;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import money.com.gettingmoney.MInutesLine.MyBarChart;
import money.com.gettingmoney.MInutesLine.MyBottomMarkerView;
import money.com.gettingmoney.MInutesLine.MyLeftMarkerView;
import money.com.gettingmoney.MInutesLine.MyLineChart;
import money.com.gettingmoney.MInutesLine.MyRightMarkerView;
import money.com.gettingmoney.MInutesLine.MyXAxis;
import money.com.gettingmoney.MInutesLine.MyYAxis;
import money.com.gettingmoney.R;
import money.com.gettingmoney.Test_Kline.MyUtils;
import money.com.gettingmoney.Test_Kline.VolFormatter;
import money.com.gettingmoney.bean.TimeKlineBean;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.webutil.hangqing.indexuti;
import money.com.gettingmoney.weiget.LoadingDialog;

/**
 * Created by Administrator on 2017/4/17.
 */
public class Time_KlineFragment extends Fragment {

    MyLineChart lineChart;

    MyBarChart barChart;
    private LineDataSet d1, d2;
    MyXAxis xAxisLine;
   // MyYAxis axisRightLine;
    MyYAxis axisLeftLine;
    BarDataSet barDataSet;
    String stockId,type;//从板块的详情页面传递过来的股票id

    MyXAxis xAxisBar;
    MyYAxis axisLeftBar;
    MyYAxis axisRightBar;
    SparseArray<String> stringSparseArray;
    Integer sum = 0;
    private LoadingDialog dialogbytime;
    //DataParse     mData;
    List<TimeKlineBean.ListBean> beanList = new ArrayList<>();
    float volmax;
    String unit;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.time_kline, container, false);

        lineChart = (MyLineChart) view.findViewById(R.id.line_chart);
        barChart = (MyBarChart) view.findViewById(R.id.bar_chart);

        stringSparseArray = setXLabels();

        Bundle bundle = getArguments();//从activity传过来的Bundle
        /*网络数据*/
        if (bundle != null) {
            stockId = bundle.getString("stockId");
            type=bundle.getString("type");
            dialogbytime = new LoadingDialog(getActivity(), "正在加载分时图");
            dialogbytime.show();
            if (!("").equals(String.valueOf(stockId))) {
                K_getByTime();
                initChart();
                Toast.makeText(getContext(), "板块详情进入", Toast.LENGTH_SHORT).show();
            }
        }
        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

                barChart.highlightValue(new Highlight(h.getXIndex(), 0));

            }

            @Override
            public void onNothingSelected() {
                barChart.highlightValue(null);
            }
        });
        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                lineChart.highlightValue(new Highlight(h.getXIndex(), 0));

            }

            @Override
            public void onNothingSelected() {
                lineChart.highlightValue(null);
            }
        });


        return view;
    }

    //板块的分时图股票网络
    void K_getByTime() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                indexuti indexuti = new indexuti();
                indexuti.domainStockHistory(dialogbytime, stockId, new MyXutils.XCallBack() {
                    @Override
                    public void onResponse(String result) {
                        Log.i("板块的分时股票接口111111", result);

                        //解析
                        TimeKlineBean Bean = new Gson().fromJson(result, new TypeToken<TimeKlineBean>() {
                        }.getType());
                        beanList = Bean.getList();
                        //把值传递到K线图里
                        //  setData(stockHistoryListBeans);
                        Log.i("板块的分时股票接口beanList", beanList.toString());

                        setData(beanList);
                        dialogbytime.close();
                    }
                });
            }
        };
        new Thread(runnable).start();

    }



    //初始表
    private void initChart() {
        lineChart.setScaleEnabled(false);
        lineChart.setDrawBorders(true);
        lineChart.setBorderWidth(1);
        lineChart.setBorderColor(getResources().getColor(R.color.minute_grayLine));
        lineChart.setDescription("");
        Legend lineChartLegend = lineChart.getLegend();
        lineChartLegend.setEnabled(false);

        barChart.setScaleEnabled(false);
        barChart.setDrawBorders(true);
        barChart.setBorderWidth(1);
        barChart.setBorderColor(getResources().getColor(R.color.minute_grayLine));
        barChart.setDescription("");


        Legend barChartLegend = barChart.getLegend();
        barChartLegend.setEnabled(false);
        //x轴
        xAxisLine = lineChart.getXAxis();
        xAxisLine.setDrawLabels(true);
        xAxisLine.setPosition(XAxis.XAxisPosition.BOTTOM);
        // xAxisLine.setLabelsToSkip(59);


        //左边y
        axisLeftLine = lineChart.getAxisLeft();
        /*折线图y轴左没有basevalue，调用系统的*/
        axisLeftLine.setLabelCount(5, true);
        axisLeftLine.setDrawLabels(true);
        axisLeftLine.setDrawGridLines(false);
        /*轴不显示 避免和border冲突*/
        axisLeftLine.setDrawAxisLine(false);



        //背景线
        xAxisLine.setGridColor(getResources().getColor(R.color.minute_grayLine));
        xAxisLine.enableGridDashedLine(10f, 5f, 0f);
        xAxisLine.setAxisLineColor(getResources().getColor(R.color.minute_grayLine));
        xAxisLine.setTextColor(getResources().getColor(R.color.minute_zhoutv));
        axisLeftLine.setGridColor(getResources().getColor(R.color.minute_grayLine));
        axisLeftLine.setTextColor(getResources().getColor(R.color.minute_zhoutv));


        //bar x y轴
        xAxisBar = barChart.getXAxis();
        xAxisBar.setDrawLabels(false);
        xAxisBar.setDrawGridLines(true);
        xAxisBar.setDrawAxisLine(false);
        // xAxisBar.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxisBar.setGridColor(getResources().getColor(R.color.minute_grayLine));
        axisLeftBar = barChart.getAxisLeft();
        axisLeftBar.setAxisMinValue(0);
        axisLeftBar.setDrawGridLines(false);
        axisLeftBar.setDrawAxisLine(false);
        axisLeftBar.setTextColor(getResources().getColor(R.color.minute_zhoutv));


        axisRightBar = barChart.getAxisRight();
        axisRightBar.setDrawLabels(false);
        axisRightBar.setDrawGridLines(false);
        axisRightBar.setDrawAxisLine(false);
        //y轴样式
        this.axisLeftLine.setValueFormatter(new YAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, YAxis yAxis) {
                DecimalFormat mFormat = new DecimalFormat("#0.00");
                return mFormat.format(value);
            }
        });

    }

    //
    private void setData(List<TimeKlineBean.ListBean> beanList) {
        setMarkerView(beanList);
        setShowLabels(stringSparseArray);

        if (beanList.size() == 0) {
            lineChart.setNoDataText("暂无数据");
            return;
        }

        for (int i = 0; i < beanList.size(); i++) {
            volmax = Math.max(beanList.get(i).getNowMarketValue(), volmax);
        }
        axisLeftBar.setAxisMaxValue(volmax*30);
        /*单位*/
        String unit = MyUtils.getVolUnit(volmax);
        int u = 1;
        if ("万手".equals(unit)) {
            u = 4;
        } else if ("亿手".equals(unit)) {
            u = 8;
        }
        /*次方*/
        axisLeftBar.setValueFormatter(new VolFormatter((int) Math.pow(10, u)));
        axisLeftBar.setShowMaxAndUnit(unit);
        axisLeftBar.setDrawLabels(true);
        //axisLeftBar.setAxisMinValue(0);//即使最小是不是0，也无碍
        axisLeftBar.setShowOnlyMinMax(true);
        axisRightBar.setAxisMaxValue(volmax);


        //基准线
        LimitLine ll = new LimitLine(0);
        ll.setLineWidth(1f);
        ll.setLineColor(getResources().getColor(R.color.minute_jizhun));
        ll.enableDashedLine(10f, 10f, 0f);
        ll.setLineWidth(1);


        ArrayList<Entry> lineCJEntries = new ArrayList<>();
        ArrayList<Entry> lineJJEntries = new ArrayList<>();
        ArrayList<String> dateList = new ArrayList<>();
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<String> xVals = new ArrayList<>();
        Log.e("##", Integer.toString(xVals.size()));
        for (int i = 0, j = 0; i < beanList.size(); i++, j++) {
           /* //避免数据重复，skip也能正常显示
            if (mData.getDatas().get(i).time.equals("13:30")) {
                continue;
            }*/
            TimeKlineBean.ListBean t = beanList.get(j);

            if (t == null) {
                lineCJEntries.add(new Entry(Float.NaN, i));
                lineJJEntries.add(new Entry(Float.NaN, i));
                barEntries.add(new BarEntry(Float.NaN, i));
                continue;
            }
            if (!TextUtils.isEmpty(stringSparseArray.get(i)) &&
                    stringSparseArray.get(i).contains(" ")) {
                i++;
            }
            lineCJEntries.add(new Entry(beanList.get(i).getNowMarketValue(), i));
            lineJJEntries.add(new Entry(beanList.get(i).getArg(), i));
           barEntries.add(new BarEntry(beanList.get(i).getRiseAndFallValue(), i));

        }
        d1 = new LineDataSet(lineCJEntries, "成交价");
        d2 = new LineDataSet(lineJJEntries, "均价");
        d1.setDrawValues(false);
        d2.setDrawValues(false);
        barDataSet = new BarDataSet(barEntries, "成交量");

        d1.setCircleRadius(0);
        d2.setCircleRadius(0);
        d1.setColor(getResources().getColor(R.color.minute_blue));
        d2.setColor(getResources().getColor(R.color.minute_yellow));
        d1.setHighLightColor(Color.WHITE);
        d2.setHighlightEnabled(false);
        d1.setDrawFilled(true);


        barDataSet.setBarSpacePercent(50); //bar空隙
        barDataSet.setHighLightColor(Color.WHITE);
        barDataSet.setHighLightAlpha(255);
        barDataSet.setDrawValues(false);
        barDataSet.setHighlightEnabled(true);
        barDataSet.setColor(Color.RED);
        List<Integer> list=new ArrayList<>();
        list.add(Color.RED);
        list.add(Color.GREEN);
        barDataSet.setColors(list);
        //谁为基准
        d1.setAxisDependency(YAxis.AxisDependency.LEFT);
        // d2.setAxisDependency(YAxis.AxisDependency.RIGHT);
        ArrayList<ILineDataSet> sets = new ArrayList<>();
        sets.add(d1);
        sets.add(d2);
        /*注老版本LineData参数可以为空，最新版本会报错，修改进入ChartData加入if判断*/
        LineData cd = new LineData(getMinutesCount(), sets);
        lineChart.setData(cd);


        BarData barData = new BarData(getMinutesCount(), barDataSet);
        barChart.setData(barData);

        setOffset();
        lineChart.invalidate();//刷新图
        barChart.invalidate();


    }


    private SparseArray<String> setXLabels() {
        SparseArray<String> xLabels = new SparseArray<>();
        xLabels.put(0, "09:30");
        xLabels.put(30, "10:30");
        xLabels.put(60, "11:30/13:00");
        xLabels.put(120, "14:00");
        xLabels.put(160, "15:00");
        return xLabels;
    }


    /*设置量表对齐*/
    private void setOffset() {
        float lineLeft = lineChart.getViewPortHandler().offsetLeft();
        float barLeft = barChart.getViewPortHandler().offsetLeft();
        float lineRight = lineChart.getViewPortHandler().offsetRight();
        float barRight = barChart.getViewPortHandler().offsetRight();
        float barBottom = barChart.getViewPortHandler().offsetBottom();
        float offsetLeft, offsetRight;
        float transLeft = 0, transRight = 0;
 /*注：setExtraLeft...函数是针对图表相对位置计算，比如A表offLeftA=20dp,B表offLeftB=30dp,则A.setExtraLeftOffset(10),并不是30，还有注意单位转换*/
        if (barLeft < lineLeft) {

            transLeft = lineLeft;

        } else {
            offsetLeft = Utils.convertPixelsToDp(barLeft - lineLeft);
            lineChart.setExtraLeftOffset(offsetLeft);
            transLeft = barLeft;
        }

  /*注：setExtraRight...函数是针对图表绝对位置计算，比如A表offRightA=20dp,B表offRightB=30dp,则A.setExtraLeftOffset(30),并不是10，还有注意单位转换*/
        if (barRight < lineRight) {

            transRight = lineRight;
        } else {
            offsetRight = Utils.convertPixelsToDp(barRight);
            lineChart.setExtraRightOffset(offsetRight);
            transRight = barRight;
        }
        barChart.setViewPortOffsets(transLeft, 5, transRight, barBottom);
    }

    public void setShowLabels(SparseArray<String> labels) {
        xAxisLine.setXLabels(labels);
        xAxisBar.setXLabels(labels);
    }



    private void setMarkerView(List<TimeKlineBean.ListBean> beans) {
        MyLeftMarkerView leftMarkerView = new MyLeftMarkerView(getContext(), R.layout.mymarkerview);
        MyRightMarkerView rightMarkerView = new MyRightMarkerView(getContext(), R.layout.mymarkerview);
        MyBottomMarkerView bottomMarkerView = new MyBottomMarkerView(getContext(), R.layout.mymarkerview);
        lineChart.setMarker(leftMarkerView, rightMarkerView, bottomMarkerView, beans);
        barChart.setMarker(leftMarkerView, rightMarkerView, bottomMarkerView, beans);
    }

    public String[] getMinutesCount() {
        return new String[242];
    }
}
