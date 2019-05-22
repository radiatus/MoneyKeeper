package ru.vsu.moneykeeper.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.HashMap;

import ru.vsu.moneykeeper.Income.boundary.IncomeService;
import ru.vsu.moneykeeper.Income.entity.Income;
import ru.vsu.moneykeeper.R;
import ru.vsu.moneykeeper.category.entity.Category;
import ru.vsu.moneykeeper.expense.boundary.ExpenseService;
import ru.vsu.moneykeeper.expense.entity.Expense;


public class MainActivity extends AppCompatActivity {

    private IncomeService incomeService;
    private ExpenseService expenseService;
    private PieChart chart;

    @Override
    protected void onStart() {
        super.onStart();
        setData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chart = (PieChart)findViewById(R.id.chart1);
        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);
        chart.setDrawCenterText(true);
        //setData();

        LinearLayoutCompat chartLayout = (LinearLayoutCompat)findViewById(R.id.chartLayout);
        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

            @Override
            public void onValueSelected(Entry e, Highlight h) {
                startActivity(new Intent(MainActivity.this, HistoryActivity.class));
            }

            @Override
            public void onNothingSelected() {

            }
        });

        Button button_add_category = (Button) findViewById(R.id.button_add_category);
        button_add_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CategoriesActivity.class));
            }
        });

        Button button_add_constraint = (Button) findViewById(R.id.button_add_constraint);
        button_add_constraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ConstraintsActivity.class));
            }
        });
        Button button_add_income = (Button) findViewById(R.id.button_add_income);
        button_add_income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, IncomesActivity.class));
            }
        });

        Button button_add_expense = (Button) findViewById(R.id.button_add_expense);
        button_add_expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ExpensesActivity.class));
            }
        });

    }

    private void setData() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        expenseService = new ExpenseService(this);
        incomeService = new IncomeService(this);

        HashMap<Category, Float> category_sum = new HashMap<>();
        Float incomesSum = 0F;
        Float expensesSum = 0F;


        for (Income income: incomeService.getAll())
            incomesSum += income.getValue();

        for (Expense expense: expenseService.getAll()) {
            expensesSum += expense.getValue();
            if (category_sum.containsKey(expense.getCategory())){
                category_sum.put(expense.getCategory(), category_sum.get(expense.getCategory()) + expense.getValue());

            } else {
                category_sum.put(expense.getCategory(), expense.getValue());
            }
        }

        chart.setCenterTextSize(15F);
        chart.setCenterTextColor(Color.BLACK);
        chart.setCenterText("Доходы:\n" + String.valueOf(incomesSum) + " P\n" +
                "Расходы:\n" + String.valueOf(expensesSum) + " P");

        chart.setEntryLabelColor(Color.BLACK);

        for (Category category: category_sum.keySet())   {
            boolean add = entries.add(new PieEntry(category_sum.get(category)
                    , category.getName()));
        }

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter(chart));
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.BLACK);

        //data.setValueTypeface(tfLight);
        chart.setData(data);
        chart.highlightValues(null);

        chart.invalidate();
    }

}
