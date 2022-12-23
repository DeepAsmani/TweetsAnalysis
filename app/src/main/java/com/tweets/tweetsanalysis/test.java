package com.tweets.tweetsanalysis;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class test extends AppCompatActivity implements View.OnClickListener {

    TextView textView1,textView2,textView3;
    Button chooseAnotherTopic;
    public float f;
    public double d;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.test_layout);

        BarChart chart1 = (BarChart) findViewById(R.id.chart);
        BarChart chart2 = (BarChart) findViewById(R.id.chart2);
        BarChart chart3 = (BarChart) findViewById(R.id.chart3);
        textView1=(TextView)findViewById(R.id.textView2);
        textView2=(TextView)findViewById(R.id.textView8);
        textView3=(TextView)findViewById(R.id.textView9);
        chooseAnotherTopic =(Button)findViewById(R.id.button3);

        chooseAnotherTopic.setOnClickListener(this);

        Bundle p = getIntent().getExtras();
        String choice = p.getString("choice");
        String naivbyas=Main.main(getApplicationContext().getApplicationContext(),choice,"a");
        String knn=Main.main(getApplicationContext().getApplicationContext(),choice,"b");

        f=(Float.valueOf(naivbyas)+Float.parseFloat(knn))/2;
        BarData data = new BarData(getYAxisValues(), getDataSet());
        chart1.getAxis(YAxis.AxisDependency.RIGHT).setEnabled(false);
        chart1.getAxis(YAxis.AxisDependency.LEFT).setAxisMaxValue(100);
        chart1.setData(data);
        YAxis yAxi = chart1.getAxis(YAxis.AxisDependency.LEFT);
        yAxi.setAxisMaxValue(100);
        chart1.getXAxis().setEnabled(false);
        chart1.setDescription(String.valueOf(f));
        chart1.animateXY(2000, 2000);
        chart1.invalidate();

        f=Float.valueOf(naivbyas);
        data = new BarData(getYAxisValues(), getDataSet());
        chart2.getAxis(YAxis.AxisDependency.RIGHT).setEnabled(false);
        chart2.getAxis(YAxis.AxisDependency.LEFT).setAxisMaxValue(100);
        chart2.setData(data);
        yAxi = chart2.getAxis(YAxis.AxisDependency.LEFT);
        yAxi.setAxisMaxValue(100);
        chart2.getXAxis().setEnabled(false);
        chart2.setDescription(String.valueOf(f));
        chart2.animateXY(2000, 2000);
        chart2.invalidate();

        f=Float.parseFloat(knn);
        data = new BarData(getYAxisValues(), getDataSet());
        chart3.getAxis(YAxis.AxisDependency.RIGHT).setEnabled(false);
        chart3.getAxis(YAxis.AxisDependency.LEFT).setAxisMaxValue(100);
        chart3.setData(data);
        yAxi = chart3.getAxis(YAxis.AxisDependency.LEFT);
        yAxi.setAxisMaxValue(100);
        chart3.getXAxis().setEnabled(false);
        chart3.setDescription(String.valueOf(f));
        chart3.animateXY(2000, 2000);
        chart3.invalidate();

        int lengthnav = naivbyas.length();
        int lengthknn = knn.length();
        int length= (((Float.valueOf(naivbyas)+Float.parseFloat(knn))/2)+" ").length()-1;
        String result=f+"% of the tweets has positive reactionBase on words has";
        SpannableString res = new SpannableString(result);
        if (length == 5) {
            res.setSpan(new RelativeSizeSpan(1f), 0, 5, 0);
            res.setSpan(new RelativeSizeSpan(1f), 25, 33, 0);
            res.setSpan(new ForegroundColorSpan(Color.GREEN), 25, 33, 0);
            textView3.setText(res);
        }
        else if (length == 6){
            res.setSpan(new RelativeSizeSpan(1f), 0, 6, 0);
            res.setSpan(new RelativeSizeSpan(1f), 26, 34, 0);
            res.setSpan(new ForegroundColorSpan(Color.GREEN), 26, 34, 0);
            textView3.setText(res);
        }
        else if (length == 4){
            res.setSpan(new RelativeSizeSpan(1f), 0, 5, 0);
            res.setSpan(new RelativeSizeSpan(1f), 24, 32, 0);
            res.setSpan(new ForegroundColorSpan(Color.GREEN), 24, 32, 0);
            textView3.setText(res);
        }
        else if (length == 3){
            res.setSpan(new RelativeSizeSpan(1f), 0, 4, 0);
            res.setSpan(new RelativeSizeSpan(1f), 23, 31, 0);
            res.setSpan(new ForegroundColorSpan(Color.GREEN), 23, 31, 0);
            textView3.setText(res);
        }



        result =naivbyas+"% of the tweets has positive reaction Base Naïve Bayes";
        res = new SpannableString(result);

        if (lengthnav == 5) {
            res.setSpan(new RelativeSizeSpan(1f), 0, 5, 0);
            res.setSpan(new RelativeSizeSpan(1f), 25, 33, 0);
            res.setSpan(new ForegroundColorSpan(Color.GREEN), 25, 33, 0);
            textView1.setText(res);
        }
        else if (lengthnav == 6){
            res.setSpan(new RelativeSizeSpan(1f), 0, 6, 0);
            res.setSpan(new RelativeSizeSpan(1f), 26, 34, 0);
            res.setSpan(new ForegroundColorSpan(Color.GREEN), 26, 34, 0);
            textView1.setText(res);
        }
        else if (lengthnav == 4){
            res.setSpan(new RelativeSizeSpan(1f), 0, 5, 0);
            res.setSpan(new RelativeSizeSpan(1f), 24, 32, 0);
            res.setSpan(new ForegroundColorSpan(Color.GREEN), 24, 32, 0);
            textView1.setText(res);
        }
        else if (lengthnav == 3){
            res.setSpan(new RelativeSizeSpan(1f), 0, 4, 0);
            res.setSpan(new RelativeSizeSpan(1f), 23, 31, 0);
            res.setSpan(new ForegroundColorSpan(Color.GREEN), 23, 31, 0);
            textView1.setText(res);
        }
        result =knn+"% of the tweets has positive reaction Base KNN";
        res = new SpannableString(result);

        if (lengthknn == 5) {
            res.setSpan(new RelativeSizeSpan(1f), 0, 5, 0);
            res.setSpan(new RelativeSizeSpan(1f), 25, 33, 0);
            res.setSpan(new ForegroundColorSpan(Color.GREEN), 25, 33, 0);
            textView2.setText(res);
        }
        else if (lengthknn == 6){
            res.setSpan(new RelativeSizeSpan(1f), 0, 6, 0);
            res.setSpan(new RelativeSizeSpan(1f), 26, 34, 0);
            res.setSpan(new ForegroundColorSpan(Color.GREEN), 26, 34, 0);
            textView2.setText(res);
        }
        else if (lengthknn == 4){
            res.setSpan(new RelativeSizeSpan(1f), 0, 5, 0);
            res.setSpan(new RelativeSizeSpan(1f), 24, 32, 0);
            res.setSpan(new ForegroundColorSpan(Color.GREEN), 24, 32, 0);
            textView2.setText(res);
        }
        else if (lengthknn == 3){
            res.setSpan(new RelativeSizeSpan(1f), 0, 4, 0);
            res.setSpan(new RelativeSizeSpan(1f), 23, 31, 0);
            res.setSpan(new ForegroundColorSpan(Color.GREEN), 23, 31, 0);
            textView2.setText(res);
        }
    }

    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(f, 0); // Jan
        valueSet1.add(v1e1);
        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Positive");
        barDataSet1.setColor(Color.rgb(0, 155, 0));

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);

        return dataSets;
    }
    private  ArrayList<String> getYAxisValues(){
        ArrayList<String> yAxis = new ArrayList<>();
        yAxis.add(" ");

        return yAxis;
    }

    @Override
    public void onClick(View v) {
        if (chooseAnotherTopic.equals(v)){
            Intent goback = new Intent(test.this,Topic.class);
            startActivity(goback);
        }
    }
}
