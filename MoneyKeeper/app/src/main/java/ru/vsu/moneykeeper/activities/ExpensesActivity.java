package ru.vsu.moneykeeper.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import ru.vsu.moneykeeper.R;
import ru.vsu.moneykeeper.category.boundary.CategoryService;
import ru.vsu.moneykeeper.category.entity.Category;
import ru.vsu.moneykeeper.expense.boundary.ExpenseService;
import ru.vsu.moneykeeper.expense.entity.Expense;

public class ExpensesActivity extends AppCompatActivity {

    private CategoryService categoryService;
    private ExpenseService expenseService;
    private Category category;
    private EditText nameEditText;
    private EditText valueEditText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_expenses);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Расходы");
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        expenseService = new ExpenseService(this);
        categoryService = new CategoryService(this);
        category = new Category();

        Spinner dropdown = findViewById(R.id.categoryExpenseSpinner);
        List<Category> categoryList = categoryService.getAll();

        final ArrayAdapter<Category> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, categoryList);

        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = adapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        nameEditText = (EditText)findViewById(R.id.expenseNameEditText);
        valueEditText = (EditText)findViewById(R.id.expenseEditText);

        button = (Button) findViewById(R.id.addConstraintButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String value_str = valueEditText.getText().toString();
                Date date = new Date();

                if (name.equals("")){
                    Toast toast = Toast.makeText(ExpensesActivity.this,
                            "Введите название", Toast.LENGTH_LONG);
                    toast.show();
                } else if (value_str.equals("") || value_str.equals("0")) {
                    Toast toast = Toast.makeText(ExpensesActivity.this,
                            "Введите значение", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    expenseService.add(new Expense(null, name, Float.valueOf(value_str), date, category));
                    Toast toast = Toast.makeText(ExpensesActivity.this,
                            "Расход добавлен", Toast.LENGTH_LONG);
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
