package es.esy.kinketkuena.pillowfix;

/**
 * Created by addd on 2/28/2016.
 */
public class config {
    //String Alamat untuk CRUD
    public static final String URL_ADD="http://kinketkuena.esy.es/addEmp.php";
    public static final String URL_GET_ALL_LOCATION="http://kinketkuena.esy.es/getAlllocation.php";

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //syahkuala
    public static final String URL_GET_ALL = "http://kinketkuena.esy.es/getAllEmp.php";
    public static final String URL_GET_EMP = "http://kinketkuena.esy.es/getEmp.php?id=";
    //kutaraja
    public static final String URL_GET_ALL_KUTARAJA="http://kinketkuena.esy.es/getAllKutaraja.php";
    public static final String URL_GET_KUTARAJA="http://kinketkuena.esy.es/getKutaraja.php";
    //ulekareng
    public static final String URL_GET_ALL_ULEKARENG="http://kinketkuena.esy.es/getAllUlekareng.php";
    public static final String URL_GET_ULEKARENG="http://kinketkuena.esy.es/getUlekareng.php";
    //bandaraya
    public static final String URL_GET_ALL_BANDARAYA="http://kinketkuena.esy.es/getAllBandaraya.php";
    public static final String URL_GET_BANDARAYA="http://kinketkuena.esy.es/getBandaraya.php";
    //jayabaru
    public static final String URL_GET_ALL_JAYABARU="http://kinketkuena.esy.es/getAllJayabaru.php";
    public static final String URL_GET_JAYABARU="http://kinketkuena.esy.es/getJayabaru.php";
    //kutaalam
    public static final String URL_GET_ALL_KUTAALAM="http://kinketkuena.esy.es/getAllKutaalam.php";
    public static final String URL_GET_KUTAALAM="http://kinketkuena.esy.es/getKutaalam.php";
    //luengbata
    public static final String URL_GET_ALL_LUENGBATA="http://kinketkuena.esy.es/getAllLuengbata.php";
    public static final String URL_GET_LUENGBATA="http://kinketkuena.esy.es/getLuengbata.php";
    //meuraxa
    public static final String URL_GET_ALL_MEURAXA="http://kinketkuena.esy.es/getAllMeuraxa.php";
    public static final String URL_GET_MEURAXA="http://kinketkuena.esy.es/getMeuraxa.php";

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

   // public static final String URL_UPDATE_EMP = "http://192.168.94.1/Android/CRUD/updateEmp.php";
    //public static final String URL_DELETE_EMP = "http://192.168.94.1/Android/CRUD/deleteEmp.php?id=";

    //Kunci untuk melakukan pemanggilan data dari scrip
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_ALAMAT = "alamat";
    public static final String KEY_EMP_DAERAH = "daerah";
    public static final String KEY_EMP_HARGA = "harga";
    public static final String KEY_EMP_JANGKA = "jangka";
    public static final String KEY_EMP_DESKRIPSI = "deskripsi";
    public static final String KEY_EMP_LOKASILAT = "lokasiLAT";
    public static final String KEY_EMP_LOKASILONG = "lokasiLONG";
    public static final String KEY_EMP_FOTO = "foto";


    public static final String KEY_EMP_NAME = "namakost";
    public static final String KEY_EMP_DESG = "namapemilik";
    public static final String KEY_EMP_SAL = "nohp";
    public static final String KEY_EMP_NIK = "nik";
    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "namakost";
    public static final String TAG_DESG = "namapemilik";
    public static final String TAG_SAL = "nohp";
    public static final String TAG_NIK = "nik";
    public static final String TAG_DESKRIPSI = "deskripsi";
    public static final String TAG_HARGA = "harga";
    public static final String TAG_JANGKA = "jangka";
    public static final String TAG_ALAMAT = "alamat";
    public static final String TAG_FOTO = "foto";
    public static final String TAG_LOKASILAT = "lokasiLAT";
    public static final String TAG_LOKASILONG = "lokasiLONG";



    //employee id untuk di gunakan membuka halaman intent lain
    public static final String EMP_ID = "emp_id";
    public static final String EMP_LOKASI = "emp_lokasi";
}
