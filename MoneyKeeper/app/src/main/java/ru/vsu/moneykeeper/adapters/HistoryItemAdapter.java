package ru.vsu.moneykeeper.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.vsu.moneykeeper.Income.boundary.IncomeService;
import ru.vsu.moneykeeper.R;
import ru.vsu.moneykeeper.expense.boundary.ExpenseService;
import ru.vsu.moneykeeper.expense.entity.Expense;
import ru.vsu.moneykeeper.history.boundary.HistoryService;
import ru.vsu.moneykeeper.history.entity.HistoryItem;

public class HistoryItemAdapter extends RecyclerView.Adapter<HistoryItemAdapter.HistoryItemHolder> {

    private List<HistoryItem> items = new ArrayList<>();
    private Context context;
    private HistoryService historyService;

    public HistoryItemAdapter(List<HistoryItem> items, Context context) {
        this.items = items;
        this.context = context;
        historyService = new HistoryService(context);
    }

    @NonNull
    @Override
    public HistoryItemAdapter.HistoryItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        HistoryItemHolder holder = new HistoryItemHolder(
                LayoutInflater
                        .from(viewGroup.getContext())
                        .inflate(R.layout.item_history_item, viewGroup, false));

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryItemAdapter.HistoryItemHolder viewHolder, int i) {
        viewHolder.init(items.get(i));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class HistoryItemHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView value;
        TextView category;
        TextView date;
        LinearLayout layout;
        ImageButton deleteButton;

        HistoryItem item;

        public HistoryItemHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.historyItemName);
            value = (TextView) itemView.findViewById(R.id.historyItemValue);
            category = (TextView) itemView.findViewById(R.id.historyItemCategory);
            date = (TextView) itemView.findViewById(R.id.historyItemDate);
            layout = (LinearLayout) itemView.findViewById(R.id.historyItemLayout);


            deleteButton = (ImageButton)itemView.findViewById(R.id.deleteButton);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    historyService.deleteItem(item);

                }
            });
        }

        public void init(HistoryItem item) {
            this.item = item;
            name.setText(item.getName());
            value.setText(String.valueOf(item.getValue()));
            if (item instanceof Expense) {
                layout.setBackgroundColor(Color.YELLOW);
                category.setText(((Expense) item).getCategory().getName());
            } else {
                layout.setBackgroundColor(Color.CYAN);
                category.setText("Доход");
            }
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            date.setText(format.format(item.getDate()));

        }
    }


}
