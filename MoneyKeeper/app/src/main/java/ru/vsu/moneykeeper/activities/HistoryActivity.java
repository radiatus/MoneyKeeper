package ru.vsu.moneykeeper.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.vsu.moneykeeper.Income.entity.Income;
import ru.vsu.moneykeeper.R;
import ru.vsu.moneykeeper.adapters.HistoryItemAdapter;
import ru.vsu.moneykeeper.category.entity.Category;
import ru.vsu.moneykeeper.expense.entity.Expense;
import ru.vsu.moneykeeper.history.boundary.HistoryService;
import ru.vsu.moneykeeper.history.entity.HistoryItem;

public class HistoryActivity extends AppCompatActivity {
    HistoryService historyService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("История");
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        historyService = new HistoryService();

        List<HistoryItem> list = historyService.getAll();
        HistoryItemAdapter adapter = new HistoryItemAdapter(list);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
