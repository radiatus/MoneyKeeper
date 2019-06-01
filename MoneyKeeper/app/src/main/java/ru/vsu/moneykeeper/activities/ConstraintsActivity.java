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

import java.util.List;

import ru.vsu.moneykeeper.R;
import ru.vsu.moneykeeper.category.boundary.CategoryService;
import ru.vsu.moneykeeper.category.entity.Category;

public class ConstraintsActivity extends AppCompatActivity {

    private CategoryService categoryService;

    private Category category;
    private EditText EditText;
    private Button buttonAdd;
    private Button buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_constraints);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Пороги");
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        categoryService = new CategoryService(this);
        category = new Category();

        Spinner dropdown = findViewById(R.id.categoryConstraintSpinner);
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

        EditText = (EditText) findViewById(R.id.constraintEditText);

        buttonAdd = (Button) findViewById(R.id.addConstraintButton);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = EditText.getText().toString();
                if (str.equals("")) {
                    Toast toast = Toast.makeText(ConstraintsActivity.this,
                            "Введите значение.", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Float value = Float.valueOf(str);
                    category.setConstraint(value);
                    categoryService.update(category);
                    Toast toast = Toast.makeText(ConstraintsActivity.this,
                            "Порог обновлен", Toast.LENGTH_LONG);
                    toast.show();
                    finish();
                }
            }
        });

        buttonDelete = (Button) findViewById(R.id.deleteConstraintButton);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category.setConstraint(0F);
                categoryService.update(category);
                Toast toast = Toast.makeText(ConstraintsActivity.this,
                        "Порог удален", Toast.LENGTH_LONG);
                toast.show();
                finish();

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
