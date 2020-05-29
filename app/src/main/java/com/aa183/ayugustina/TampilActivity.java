package com.aa183.ayugustina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TampilActivity extends AppCompatActivity {

    private ImageView imgFilm;
    private TextView tvJudul, tvGenre, tvDurasi, tvTahun, tvSinopsisFilm, tvPemeranFilm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil);

        imgFilm = findViewById(R.id.iv_film);
        tvJudul = findViewById(R.id.tv_judul);
        tvGenre = findViewById(R.id.tv_genre);
        tvDurasi = findViewById(R.id.tv_durasi);
        tvTahun = findViewById(R.id.tv_tahun);
        tvSinopsisFilm = findViewById(R.id.tv_sinopsis_film);
        tvPemeranFilm = findViewById(R.id.tv_pemeran_film);

        Intent terimaData = getIntent();
        tvJudul.setText(terimaData.getStringExtra("JUDUL"));
        tvGenre.setText(terimaData.getStringExtra("GENRE"));
        tvDurasi.setText(terimaData.getStringExtra("DURASI"));
        tvTahun.setText(terimaData.getStringExtra("TAHUN"));
        tvSinopsisFilm.setText(terimaData.getStringExtra("SINOPSIS_FILM"));
        tvPemeranFilm.setText(terimaData.getStringExtra("PEMERAN_FILM"));
        String imgLocation = terimaData.getStringExtra("GAMBAR");

        try {
            File file = new File(imgLocation);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            imgFilm.setImageBitmap(bitmap);
            imgFilm.setContentDescription(imgLocation);
        }catch (FileNotFoundException er){
            er.printStackTrace();
            Toast.makeText(this, "Gagal mengambil gambar dari media penyimpanan", Toast.LENGTH_SHORT).show();
        }
    }
}
