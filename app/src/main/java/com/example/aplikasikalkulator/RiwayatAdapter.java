package com.example.aplikasikalkulator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.RiwayatViewHolder> {
    private final ArrayList<ListRiwayat> listriwayat;

    public static class RiwayatViewHolder extends RecyclerView.ViewHolder {
        public TextView TextViewNilai1;
        public TextView TextViewOperasi;
        public TextView TextViewNilai2;
        public TextView TextViewHasil;

        @SuppressLint("CutPasteId")
        public RiwayatViewHolder(View itemView) {
            super(itemView);
            TextViewNilai1 = itemView.findViewById(R.id.txtvNilai1);
            TextViewOperasi = itemView.findViewById(R.id.txtvoperasi);
            TextViewNilai2 = itemView.findViewById(R.id.txtvNilai2);
            TextViewHasil = itemView.findViewById(R.id.txtvhasil);
        }
    }

    public RiwayatAdapter(ArrayList<ListRiwayat> rwytList) {
        listriwayat = rwytList;
    }

    @NonNull
    @Override
    public RiwayatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);
        RiwayatViewHolder holder= new RiwayatViewHolder(inflater.inflate(R.layout.activity_detail_riwayat, parent, false));

        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(RiwayatViewHolder holder, int position) {
        ListRiwayat currentItem = listriwayat.get(position);

        holder.TextViewNilai1.setText(currentItem.getNilai1());
        holder.TextViewNilai2.setText(currentItem.getNilai2());
        holder.TextViewOperasi.setText(currentItem.getOperasi());
        holder.TextViewHasil.setText(currentItem.getHasil());
    }

    @Override
    public int getItemCount() {
        return listriwayat.size();
    }
}