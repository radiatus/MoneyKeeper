package ru.vsu.moneykeeper.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class CategoriesActivity extends AppCompatActivity {
    private Category category;
    CategoryService categoryService;
    EditText editText;
    Button buttonAdd;
    Button buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        categoryService = new CategoryService(this);

        setContentView(R.layout.activity_categories);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Категории");
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        editText = (EditText) findViewById(R.id.categoryEditText);
        buttonAdd = (Button) findViewById(R.id.addCategoryButton);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                if (name.equals("")) {
                    Toast toast = Toast.makeText(CategoriesActivity.this,
                            "Введите назване категории", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(CategoriesActivity.this,
                            "Категория добавлена", Toast.LENGTH_LONG);
                    toast.show();
                    categoryService.add(new Category(null, name, 0F));
                    finish();
                }
            }
        });


        Spinner dropdown = findViewById(R.id.categorySpinner);
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

        buttonDelete = (Button) findViewById(R.id.deleteCategoryButton);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryService.delete(category.getId());
                Toast toast = Toast.makeText(CategoriesActivity.this,
                        "Категория удалена", Toast.LENGTH_LONG);
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
