package com.aa183.ayugustina;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {

    private Context context;
    private ArrayList<Film> dataFilm;

    public FilmAdapter(Context context, ArrayList<Film> dataFilm) {
        this.context = context;
        this.dataFilm = dataFilm;
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_film, parent, false);
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
        Film tempFilm = dataFilm.get(position);
        holder.idFilm = tempFilm.getIdFilm();
        holder.tvJudul.setText(tempFilm.getJudul());
        holder.tvHeadlineDurasi.setText(tempFilm.getDurasi());
        holder.tvHeadlineTahun.setText(tempFilm.getTahun());
        holder.gambar = tempFilm.getGambar();
        holder.genre = tempFilm.getGenre();
        holder.sinopsisFilm = tempFilm.getSinopsisFilm();
        holder.pemeranFilm = tempFilm.getPemeranFilm();

        try {
            File file = new File(holder.gambar);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            holder.imgFilm.setImageBitmap(bitmap);
            holder.imgFilm.setContentDescription(holder.gambar);
        }catch (FileNotFoundException er){
            er.printStackTrace();
            Toast.makeText(context, "Gagal mengambil gambar dari media penyimpanan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return dataFilm.size();
    }

    public class FilmViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        private ImageView imgFilm;
        private TextView tvJudul, tvHeadlineDurasi, tvHeadlineTahun;
        private int idFilm;
        private String gambar, genre, sinopsisFilm, pemeranFilm;

        public FilmViewHolder(@NonNull View itemView) {
            super(itemView);

            imgFilm = itemView.findViewById(R.id.iv_film);
            tvJudul = itemView.findViewById(R.id.tv_judul);
            tvHeadlineDurasi = itemView.findViewById(R.id.tv_headline_durasi);
            tvHeadlineTahun = itemView.findViewById(R.id.tv_headline_tahun);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Intent bukaFilm = new Intent(context, TampilActivity.class);
            bukaFilm.putExtra("ID", idFilm);
            bukaFilm.putExtra("JUDUL", tvJudul.getText().toString());
            bukaFilm.putExtra("GAMBAR", gambar);
            bukaFilm.putExtra("GENRE", genre);
            bukaFilm.putExtra("DURASI", tvHeadlineDurasi.getText().toString());
            bukaFilm.putExtra("TAHUN", tvHeadlineTahun.getText().toString());
            bukaFilm.putExtra("SINOPSIS_FILM", sinopsisFilm);
            bukaFilm.putExtra("PEMERAN_FILM", pemeranFilm);
            context.startActivity(bukaFilm);

        }

        @Override
        public boolean onLongClick(View v) {

            Intent bukaInput = new Intent(context, InputActivity.class);
            bukaInput.putExtra("OPERASI", "update");
            bukaInput.putExtra("ID", idFilm);
            bukaInput.putExtra("JUDUL", tvJudul.getText().toString());
            bukaInput.putExtra("GAMBAR", gambar);
            bukaInput.putExtra("GENRE", genre);
            bukaInput.putExtra("DURASI", tvHeadlineDurasi.getText().toString());
            bukaInput.putExtra("TAHUN", tvHeadlineTahun.getText().toString());
            bukaInput.putExtra("SINOPSIS_FILM", sinopsisFilm);
            bukaInput.putExtra("PEMERAN_FILM", pemeranFilm);
            context.startActivity(bukaInput);
            return true;
        }
    }
}
