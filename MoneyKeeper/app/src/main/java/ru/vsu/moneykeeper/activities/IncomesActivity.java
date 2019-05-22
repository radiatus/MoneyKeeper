package ru.vsu.moneykeeper.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

import ru.vsu.moneykeeper.Income.boundary.IncomeService;
import ru.vsu.moneykeeper.Income.entity.Income;
import ru.vsu.moneykeeper.R;

public class IncomesActivity extends AppCompatActivity {

    private IncomeService incomeService;
    private EditText nameEditText;
    private EditText valueEditText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incomes);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Доходы");
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        incomeService = new IncomeService(this);

        nameEditText = (EditText)findViewById(R.id.incomeNameEditText);
        valueEditText = (EditText)findViewById(R.id.incomeEditText);
        button = (Button) findViewById(R.id.addCategoryButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                Float value = Float.valueOf(valueEditText.getText().toString());
                Date date = new Date();
                incomeService.add(new Income(null, name, value, date));
            }
        });

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
