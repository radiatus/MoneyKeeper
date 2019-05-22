package ru.vsu.moneykeeper.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ru.vsu.moneykeeper.R;
import ru.vsu.moneykeeper.category.boundary.CategoryService;
import ru.vsu.moneykeeper.category.entity.Category;

public class CategoriesActivity extends AppCompatActivity {

    CategoryService categoryService;
    EditText editText;
    Button button;

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
        editText = (EditText)findViewById(R.id.categoryEditText);
        button = (Button)findViewById(R.id.addCategoryButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                categoryService.add(new Category(null, name, 0F));
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
