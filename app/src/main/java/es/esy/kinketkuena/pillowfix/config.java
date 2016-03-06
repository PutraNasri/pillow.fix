package es.esy.kinketkuena.pillowfix;

/**
 * Created by addd on 2/28/2016.
 */
public class config {
    //Address of our scripts of the CRUD
    public static final String URL_ADD="http://kinketkuena.esy.es/addEmp.php";
    public static final String URL_GET_ALL = "http://kinketkuena.esy.es/getAllEmp.php";
    public static final String URL_GET_EMP = "http://kinketkuena.esy.es/getEmp.php?id=";
    public static final String URL_UPDATE_EMP = "http://192.168.94.1/Android/CRUD/updateEmp.php";
    public static final String URL_DELETE_EMP = "http://192.168.94.1/Android/CRUD/deleteEmp.php?id=";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_NAME = "namakost";
    public static final String KEY_EMP_DESG = "namapemilik";
    public static final String KEY_EMP_SAL = "nohp";
    public static final String KEY_EMP_NIK = "nik";
    public static final String KEY_EMP_ALAMAT = "alamat";
    public static final String KEY_EMP_DAERAH = "daerah";
    public static final String KEY_EMP_HARGA = "harga";
    public static final String KEY_EMP_JANGKA = "jangka";
    public static final String KEY_EMP_DESKRIPSI = "deskripsi";
    public static final String KEY_EMP_LOKASI = "lokasi";
    public static final String KEY_EMP_FOTO = "foto";


    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "namakost";
    public static final String TAG_DESG = "namapemilik";
    public static final String TAG_SAL = "nohp";
    public static final String TAG_NIK = "nik";

    //employee id to pass with intent
    public static final String EMP_ID = "emp_id";
}
