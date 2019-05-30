package ru.vsu.moneykeeper.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        nameEditText = (EditText) findViewById(R.id.incomeNameEditText);
        valueEditText = (EditText) findViewById(R.id.incomeEditText);
        button = (Button) findViewById(R.id.addCategoryButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String value_str = valueEditText.getText().toString();
                Date date = new Date();

                if (name.equals("")) {
                    Toast toast = Toast.makeText(IncomesActivity.this,
                            "Введите название", Toast.LENGTH_LONG);
                    toast.show();
                } else if (value_str.equals("") || value_str.equals("0")) {
                    Toast toast = Toast.makeText(IncomesActivity.this,
                            "Введите значение", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    incomeService.add(new Income(null, name, Float.valueOf(value_str), date));
                    Toast toast = Toast.makeText(IncomesActivity.this,
                            "Доход добавлен", Toast.LENGTH_LONG);
                    toast.show();
                    finish();
                }
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
