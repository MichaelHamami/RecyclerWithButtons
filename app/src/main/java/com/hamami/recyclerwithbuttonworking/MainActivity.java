package com.hamami.recyclerwithbuttonworking;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private ArrayList<ExampleItem> mExampleList;

    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button buttonInsert;
    private Button buttonRemove;
    private EditText editTextInsert;
    private EditText editTextRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createExampleList();
        buildRecyclerView();
        setButtons();
    }

    public void insertItem(int position){
        mExampleList.add(position,new ExampleItem(R.drawable.ic_android,"New item at Position: "+position,"This is Line 2"));
        mAdapter.notifyItemInserted(position);
    }
    public void removeItem(int position){
        mExampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    public void changeItem(int position,String text){
        mExampleList.get(position).changeText1(text);
        mAdapter.notifyItemChanged(position);
    }

    private void createExampleList() {

        mExampleList = new ArrayList<>();
        mExampleList.add(new ExampleItem(R.drawable.ic_android,"Line 1","Line 2"));
        mExampleList.add(new ExampleItem(R.drawable.ic_android,"Line 3","Line 4"));
        mExampleList.add(new ExampleItem(R.drawable.ic_android,"Line 5","Line 6"));

    }
    private void buildRecyclerView() {

        mRecyclerView = findViewById(R.id.Recycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                changeItem(position,"Clicked yaa");
            }

            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }

            @Override
            public void showPopupMenu(int position) {
                showPopup(position);
            }
        });
    }
    private void setButtons() {
        buttonInsert = findViewById(R.id.button_insert);
        buttonRemove = findViewById(R.id.button_remove);
        editTextInsert = findViewById(R.id.edittext_insert);
        editTextRemove = findViewById(R.id.edittext_remove);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(editTextInsert.getText().toString());
                MainActivity.this.insertItem(position);
            }
        });
        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(editTextRemove.getText().toString());
                MainActivity.this.removeItem(position);
            }
        });
    }
    public void showPopup(int postion){
        PopupMenu popup = new PopupMenu(this,findViewById(R.id.Recycler));
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.items1:
                Toast.makeText(this,"item 1 clicked",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.items2:
                Toast.makeText(this,"item 2 clicked",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.items3:
                Toast.makeText(this,"item 3 clicked",Toast.LENGTH_SHORT).show();
                return true;
                default:
                    return false;
        }
    }
}

