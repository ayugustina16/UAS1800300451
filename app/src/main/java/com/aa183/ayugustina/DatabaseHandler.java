package com.aa183.ayugustina;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 2;
    private final static String DATABASE_NAME = "db_film";
    private final static String TABLE_FILM = "t_film";
    private final static String KEY_ID_FILM = "ID_Film";
    private final static String KEY_JUDUL = "Judul";
    private final static String KEY_GAMBAR = "Gambar";
    private final static String KEY_GENRE = "Genre";
    private final static String KEY_DURASI = "Durasi";
    private final static String KEY_TAHUN = "Tahun";
    private final static String KEY_SINOPSIS_FILM = "Sinopsis_Film";
    private final static String KEY_PEMERAN_FILM = "Pemeran_Film";
    private Context context;


    public  DatabaseHandler(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = ctx;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_FILM = "CREATE TABLE " + TABLE_FILM
                + "(" + KEY_ID_FILM + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_JUDUL + " TEXT, " + KEY_GAMBAR + " TEXT, "
                + KEY_GENRE + " TEXT, " + KEY_DURASI + " TEXT, "
                + KEY_TAHUN + " TEXT, " + KEY_SINOPSIS_FILM + " TEXT, "
                + KEY_PEMERAN_FILM + " TEXT);";

        db.execSQL(CREATE_TABLE_FILM);
        inisialisasiFilmAwal(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_FILM;
        db.execSQL(DROP_TABLE);
        onCreate(db);

    }

    public void tambahFilm(Film dataFilm) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataFilm.getJudul());
        cv.put(KEY_GAMBAR, dataFilm.getGambar());
        cv.put(KEY_GENRE, dataFilm.getGenre());
        cv.put(KEY_DURASI, dataFilm.getDurasi());
        cv.put(KEY_TAHUN, dataFilm.getTahun());
        cv.put(KEY_SINOPSIS_FILM, dataFilm.getSinopsisFilm());
        cv.put(KEY_PEMERAN_FILM, dataFilm.getPemeranFilm());

        db.insert(TABLE_FILM, null, cv);
        db.close();
    }

    public void tambahFilm(Film dataFilm, SQLiteDatabase db) {
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataFilm.getJudul());
        cv.put(KEY_GAMBAR, dataFilm.getGambar());
        cv.put(KEY_GENRE, dataFilm.getGenre());
        cv.put(KEY_DURASI, dataFilm.getDurasi());
        cv.put(KEY_TAHUN, dataFilm.getTahun());
        cv.put(KEY_SINOPSIS_FILM, dataFilm.getSinopsisFilm());
        cv.put(KEY_PEMERAN_FILM, dataFilm.getPemeranFilm());

        db.insert(TABLE_FILM, null, cv);
    }

    public void editFilm(Film dataFilm) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataFilm.getJudul());
        cv.put(KEY_GAMBAR, dataFilm.getGambar());
        cv.put(KEY_GENRE, dataFilm.getGenre());
        cv.put(KEY_DURASI, dataFilm.getDurasi());
        cv.put(KEY_TAHUN, dataFilm.getTahun());
        cv.put(KEY_SINOPSIS_FILM, dataFilm.getSinopsisFilm());
        cv.put(KEY_PEMERAN_FILM, dataFilm.getPemeranFilm());

        db.update(TABLE_FILM, cv, KEY_ID_FILM + "=?", new String[]{String.valueOf(dataFilm.getIdFilm())});
        db.close();

    }

    public void hapusFilm(int idFilm){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_FILM, KEY_ID_FILM + "=?", new String[]{String.valueOf(idFilm)});
        db.close();
    }

    public ArrayList<Film> getAllFilm() {
        ArrayList<Film> dataFilm = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_FILM;
        SQLiteDatabase db = getReadableDatabase();
        Cursor csr = db.rawQuery(query, null);
        if (csr.moveToFirst()){
            do {
                Film tempFilm = new Film(
                        csr.getInt(0),
                        csr.getString(1),
                        csr.getString(2),
                        csr.getString(3),
                        csr.getString(4),
                        csr.getString(5),
                        csr.getString(6),
                        csr.getString(7)
                );

                dataFilm.add(tempFilm);
            }while (csr.moveToNext());
        }

        return dataFilm;
    }

    private String storeImageFile(int id){
        String location;
        Bitmap image = BitmapFactory.decodeResource(context.getResources(), id);
        location = InputActivity.saveImageToInternalStorage(image, context);
        return location;
    }

    private void inisialisasiFilmAwal(SQLiteDatabase db){
        int idFilm = 0;

        //Menambah data Film ke 1
        Film film1 = new Film(
                idFilm,
                "Milea: Suara dari Dilan",
                storeImageFile(R.drawable.gambar1),
                "Drama Romantis",
                "1 j 42 m ",
                "2020",
                "Dilan (Iqbaal Ramadhan), panglima tempur sebuah geng motor di Bandung awal 90-an, menjalin hubungan dengan seorang siswi baru dari Jakarta bernama Milea (Vanesha Prescilla). \n" +
                        "\n" +
                        "Dilan selalu bahagia saat bersama Milea, namun teman-teman geng motor merasa Dilan makin menjauh dari kelompoknya karena Milea. Terjadi peristiwa yang mengerikan, salah satu anggota mereka, Akew (Gusti Rayhan), meninggal akibat dikeroyok oleh sekelompok orang. Peristiwa itu membuat Milea khawatir akan keselamatan Dilan. Milea membuat keputusan untuk berpisah dengan Dilan sebagai peringatan agar Dilan menjauh dari geng motor. \n" +
                        "\n" +
                        "Peristiwa Akew menyeret Dilan ke pihak berwajib bersama teman-temannya. Perpisahan yang tadinya hanya gertakan Milea menjadi perpisahan yang berlangsung lama sampai mereka lulus kuliah dan dewasa. Mereka berdua masih membawa perasaan yang sama saat mereka kembali bertemu di reuni, namun masing-masing saat itu sudah memiliki pasangan.\n",
                "Vanesha Prescilla sebagai Milea\n" +
                        "Ira Wibowo sebagai ibu Dilan\n" +
                        "Bucek Depp sebagai ayah Dilan\n" +
                        "Happy Salma sebagai ibu Milea\n" +
                        "Farhan sebagai ayah Milea\n" +
                        "Adhisty Zara sebagai Disa\n" +
                        "Yoriko Angeline sebagai Wati\n" +
                        "Debo Andryos sebagai Nandan\n" +
                        "Zulfa Maharani sebagai Rani\n" +
                        "Gusti Rayhan sebagai Akew\n" +
                        "Omara Esteghlal sebagai Piyan\n" +
                        "Giulio Parengkuan sebagai Anhar\n" +
                        "Andovi da Lopez sebagai Verdi\n" +
                        "Jerome Kurnia sebagai Yugo\n" +
                        "Tike Priyatna sebagai Eem\n" +
                        "Bima Azriel sebagai Dilan kecil\n"
        );

        tambahFilm(film1, db);
        idFilm++;

        //Data Film ke 2
        Film film2 = new Film(
                idFilm,
                "Dua Garis Biru ",
                storeImageFile(R.drawable.gambar2),
                "Drama/Remaja",
                "1 j 53 m",
                "2019",
                "Bima dan Dara adalah sepasang kekasih yang masih duduk di bangku SMA. Bima memiliki nilai akademis yang rendah, dan itu sebaliknya bagi Dara.\n" +
                        "\n" +
                        " Meskipun demikian, mereka sangatlah mencintai sesama dan sering bermain bersama, hingga suatu saat, mereka melampaui batas dengan cara bersetubuh di luar nikah. Setelah kejadian itu, Bima dan Dara tidak berbicara satu sama lain, hingga suatu saat, ketika Dara dikonfirmasi hamil. Mengetahui apa yang terjadi, Bima ingin mencoba mengatakannya kepada orangtuanya, namun tidak mampu.\n" +
                        "\n" +
                        "Ketegangan hubungan mereka semakin naik; mereka tidak berbicara terhadap sesama, dan bahkan Bima juga mencoba menghindari Dara, takut emosi akan meluap. Ketika Bima meminta maaf, dia menyarankan Dara untuk aborsi saja, saran yang takkan Dara patuhi.\n" +
                        "\n" +
                        "Suatu hari di lapangan basket sekolah, Bima dan teman-temannya sedang bermain bola basket, ketika salah satu temannya tak sengaja melempar bola, mengenai kepala Dara dan membuat sakit di perutnya. Dara yang kesakitan meneriakkan \"Bayi kita gimana?!\" di depan semua murid, mengejutkan semuanya dan menakuti Bima. Kepala sekolah pun mengubungi orangtua keduanya dan merekapun datang. Orangtua Bima dan Dara tidak terkendali, dan merekapun berantam di UKS. Ibu Dara mengatakan bahwa Dara di-DO dari sekolah, sedangkan Bima tidak. Ibunya pun menghukum Dara, dengan tidak mengijinkan Dara tinggal di rumahnya. Alhasil, Ibu Bima menamparnya.\n" +
                        "\n" +
                        "Dara pun tinggal di rumah Bima. Selama menyusuri kampung, terdengar gosip-gosip mengenai kehamilan Dara. Dara pun tidur di kamarnya Bima bersama Bima. Malam itu, kakak Bima, Dewi, memasuki kamar Bima dan memarahi Bima karena tidak menyadari resiko hubungan seks di usia remaja.\n" +
                        "\n" +
                        "Dara pun diijinkan pulang balik. Ketika sampai di rumah, adik Dara diam-diam mengatakan bahwa orangtua Dara ingin menyerahkan bayinya kepada pamannya, Adi, dan tantenya, Lia. Ketika Dara memarahi orangtuanya, Ibunya mengatakan bahwa menjadi orangtua muda itu beresiko, namun Dara mengambil resiko itu, sumpah yang orangtuanya tidak menjamin. Bima pun mengatakan berita itu kepada orangtuanya, dan terdapat pendapat berbeda: Ibunya tidak suka perlakuan orangtua Dara, sedangkan Bapak Bima sebaliknya. Ibunya juga setuju agar mereka berdua menikah.\n" +
                        "\n" +
                        "Akhirnya, merekapun menikah. Setelah menikah, Bima mendapatkan pekerjaan sebagai waitress di restoran bapaknya Dara. Beberapa pekan kemudian, Bapak Dara berempati terhadap Bima yang kesusahan dan menyuruhnya untuk tidak usah bekerja lagi, sekalipun hasil kerjanya bagus.\n" +
                        "\n" +
                        "Setelah menikah, hubungan mereka bergoyang emosinya. Kadang senang, kadang marah. Namun yang paling terjadi adalah marah. Dara yang ambisius ingin belajar di Korea, dihadapi Bima yang khawatir mengenai anaknya; siapa yang akan menjaganya. Selama masa-masa itu juga, teman-temannya Dara datang ke rumahnya untuk menyemangati Dara.\n",
                "Angga Yunanda sebagai Bima\n" +
                        "Zara JKT48 sebagai Dara Yunika\n" +
                        "Lulu Tobing sebagai Rika, ibu Dara\n" +
                        "Dwi Sasono sebagai David Farhadi, ayah Dara\n" +
                        "Cut Mini sebagai Yuni, ibu Bima\n" +
                        "Arswendy Bening Swara sebagai ayah Bima\n" +
                        "Rachel Amanda sebagai Dewi, kakak Bima\n" +
                        "Maisha Kanna sebagai Putri alias Puput, adik Dara\n" +
                        "Shakira Jasmine sebagai Vini\n" +
                        "Ariel JKT48 sebagai Melly\n" +
                        "Cindy JKT48 sebagai Lika\n" +
                        "Irgi Fahrezi sebagai Om Adi\n" +
                        "Rahma Alia sebagai Tante Lia\n" +
                        "Ligwina Hananto sebagai dr. Fiza Hatta\n" +
                        "Asri Welas sebagai ibu hamil\n" +
                        "Bintang Emon sebagai supir ojek online\n"
        );

        tambahFilm(film2, db);
        idFilm++;

        //Data Film ke 3
        Film film3 = new Film(
                idFilm,
                "5 CM",
                storeImageFile(R.drawable.gambar3),
                "Drama",
                "2 j 6 m",
                "2012",
                "Genta (Fedi Nuril), Arial (Denny Sumargo), Zafran (Herjunot Ali), Riani (Raline Shah) dan Ian (Igor Saykoji) adalah lima remaja yang telah menjalin persahabatan sepuluh tahun lamanya. Mereka memiliki karakter yang berbeda-beda. Zafran yang puitis, sedikit \"gila\", apa adanya, idealis, agak narsis, dan memiliki bakat untuk menjadi orang terkenal. Riani yang merupakan gadis cerdas, cerewet, dan mempunyai ambisi untuk cita-citanya. Genta, pria yang tidak senang mementingkan dirinya sendiri sehingga memiliki jiwa pemimpin dan mampu membuat orang lain nyaman di sekitarnya. Arial, pria termacho di antara pemain lainnya, hobi berolah raga, paling taat aturan, namun paling canggung kenalan dengan wanita. Ian, dia memiliki badan yang paling subur dibandingkan teman-temannya, penggemar indomie dan bola, paling telat wisuda. Ada pula Dinda (Pevita Pearce) yang merupakan adik dari Arial, seorang mahasiswi cantik yang sebenarnya dicintai Zafran. Suatu hari mereka berlima merasa “jenuh” dengan persahabatan mereka dan akhirnya kelimanya memutuskan untuk berpisah, tidak saling berkomunikasi satu sama lain selama tiga bulan lamanya.\n" +
                        "\n" +
                        "Selama tiga bulan berpisah penuh kerinduan, banyak yang terjadi dalam kehidupan mereka berlima, sesuatu yang mengubah diri mereka masing-masing untuk lebih baik dalam menjalani kehidupan. Setelah tiga bulan berselang mereka berlima pun bertemu kembali dan merayakan pertemuan mereka dengan sebuah perjalanan penuh impian dan tantangan. Sebuah perjalanan hati demi mengibarkan sang saka merah putih di puncak tertinggi Jawa yaitu di puncak Mahameru pada tanggal 17 Agustus. Sebuah perjalanan penuh perjuangan yang membuat mereka semakin mencintai Indonesia. Petualangan dalam kisah ini, bukanlah petualangan yang menantang adrenalin, demi melihat kebesaran sang Ilahi dari atas puncak gunung. Tetapi petualangan ini, juga perjalanan hati. Hati untuk mencintai persahabatan yang erat, dan hati yang mencintai negeri ini.\n" +
                        "\n" +
                        "Segala rintangan dapat mereka hadapi, karena mereka memiliki impian. Impian yang ditaruh 5cm dari depan kening.\n",
                "Herjunot Ali\n" +
                        "Raline Shah\n" +
                        "Fedi Nuril\n" +
                        "Pevita Pearce\n" +
                        "Denny Sumargo\n" +
                        "Saykoji\n"
        );

        tambahFilm(film3, db);
        idFilm++;

        //Data Film ke 4
        Film film4 = new Film(
                idFilm,
                "Headshot",
                storeImageFile(R.drawable.gambar4),
                "Laga/Drama",
                "1 j 58 m",
                "2016",
                "Headshot mengisahkan tentang pria yang menderita amnesia dengan masa lalu yang misterius. Ia kemudian menjadi ‘mesin pembunuh’ yang mematikan saat harus berhadapan dengan seorang gembong narkoba yang sangat berbahaya. ",
                "Iko Uwais sebagai Ismail/Abdi\n" +
                        "Chelsea Islan sebagai Ailin\n" +
                        "Julie Estelle sebagai Rika\n" +
                        "Zack Lee sebagai Tano\n" +
                        "Sunny Pang sebagai Lee\n" +
                        "Yayu Unru sebagai Romli\n" +
                        "David Hendrawan sebagai Tejo\n" +
                        "Raihan Khan sebagai Ismail/Abdi Kecil\n" +
                        "Very Tri Yulisman sebagai Besi\n" +
                        "Ganindra Bimo sebagai Bondhi\n"
        );

        tambahFilm(film4, db);
    }
}
