package com.example.aplikasikalkulator;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RadioGroup rgOperasi;
    RadioButton rbtnTambah, rbtnKurang, rbtnKali, rbtnBagi;

    private ArrayList<ListRiwayat> listriwayat;
    private RecyclerView recyclerView;
    private RiwayatAdapter riwayatAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();
        buildRecyclerView();
        setCountButton();

        Button buttonDelete = findViewById(R.id.btnHapus);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
            }
        });

        rgOperasi = findViewById(R.id.rgOperasi);
        rbtnTambah = findViewById(R.id.rbtnTambah);
        rbtnKurang = findViewById(R.id.rbtnKurang);
        rbtnKali = findViewById(R.id.rbtnKali);
        rbtnBagi = findViewById(R.id.rbtnBagi);

        rgOperasi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rbtnTambah.isChecked()) {
                    Toast.makeText(MainActivity.this, "Tambah", Toast.LENGTH_SHORT).show();
                } else if (rbtnKurang.isChecked()) {
                    Toast.makeText(MainActivity.this, "Kurang", Toast.LENGTH_SHORT).show();
                } else if (rbtnKali.isChecked()) {
                    Toast.makeText(MainActivity.this, "Kali", Toast.LENGTH_SHORT).show();
                } else if (rbtnBagi.isChecked()) {
                    Toast.makeText(MainActivity.this, "Bagi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<ListRiwayat>>() {}.getType();
        listriwayat = gson.fromJson(json, type);

        if (listriwayat == null) {
            listriwayat = new ArrayList<>();
        }
    }

    private void buildRecyclerView() {
        recyclerView = findViewById(R.id.recRiwayat);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        riwayatAdapter = new RiwayatAdapter(listriwayat);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(riwayatAdapter);
    }

    private void setCountButton() {
        Button buttonCount = findViewById(R.id.btnHitung);
        buttonCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nilai1 = findViewById(R.id.edtNilai1);
                EditText nilai2 = findViewById(R.id.edtNilai2);
                CountItem(nilai1.getText().toString(), nilai2.getText().toString());
            }
        });
    }

    private void CountItem(String line1, String line2) {
        int hasil = 0;
        if (rbtnTambah.isChecked()) {
            hasil = Integer.parseInt(line1) + Integer.parseInt(line2);
        } else if (rbtnKurang.isChecked()) {
            hasil = Integer.parseInt(line1) - Integer.parseInt(line2);
        } else if (rbtnKali.isChecked()) {
            hasil = Integer.parseInt(line1) * Integer.parseInt(line2);
        } else if (rbtnBagi.isChecked()) {
            hasil = Integer.parseInt(line1) / Integer.parseInt(line2);
        }

        String operasi = "";
        if (rbtnTambah.isChecked()) {
            operasi = "+";
        } else if (rbtnKurang.isChecked()) {
            operasi = "-";
        } else if (rbtnKali.isChecked()) {
            operasi = "x";
        } else if (rbtnBagi.isChecked()) {
            operasi = ":";
        }

        listriwayat.add(new ListRiwayat(line1, operasi, line2, String.valueOf(hasil)));
        riwayatAdapter.notifyItemInserted(listriwayat.size());
    }

    @SuppressLint("NotifyDataSetChanged")
    private void deleteData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        listriwayat.clear();
        riwayatAdapter.notifyDataSetChanged();
    }

}