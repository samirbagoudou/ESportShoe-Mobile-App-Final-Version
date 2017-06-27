package gl52.utbm.esportshoe;

/**
 * Created by root on 08/06/17.
 */

public class Constants {

    public static final String DB_NAME = "esportshoe";
    //ADMIN TABLE FIELDS
    public static final String ADMIN_KEY = "id_player";
    public static final String ADMIN_FIRSTNAME = "firstname";
    public static final String ADMIN_LASTNAME = "lastname";
    public static final String ADMIN_LOGIN = "login";
    public static final String ADMIN_PASSWORD = "password";
    //SHOE TABLE FIELDS
    public static final String SHOE_KEY = "id_shoe";
    public static final String SHOE_NAME = "nameshoe";
    //SENSOR1 TABLE FIELDS
    public static final String SENSOR1_KEY = "id_sensor1";
    public static final String SENSOR1_VALUE = "valuesensor1";
    //SENSOR2 TABLE FIELDS
    public static final String SENSOR2_KEY = "id_sensor2";
    public static final String SENSOR2_VALUE = "valuesensor2";
    //SENSOR3 TABLE FIELDS
    public static final String SENSOR3_KEY = "id_sensor3";
    public static final String SENSOR3_VALUE = "valuesensor3";
    //SENSOR4 TABLE FIELDS
    public static final String SENSOR4_KEY = "id_sensor4";
    public static final String SENSOR4_VALUE = "valuesensor4";


    //TABLE NAMES
    public static final String ADMIN_TABLE_NAME = "admin";
    public static final String SHOE_TABLE_NAME = "shoe";
    public static final String SENSOR1_TABLE_NAME = "sensor1";
    public static final String SENSOR2_TABLE_NAME = "sensor2";
    public static final String SENSOR3_TABLE_NAME = "sensor3";
    public static final String SENSOR4_TABLE_NAME = "sensor4";

    //TABLE CREATION
    //--TABLE ADMIN
    public static final String ADMIN_CREATE_TABLE =
            "CREATE TABLE " + ADMIN_TABLE_NAME + " (" +
                    ADMIN_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ADMIN_FIRSTNAME + " TEXT, " +
                    ADMIN_LASTNAME + " TEXT, " +
                    ADMIN_LOGIN + " TEXT, " +
                    ADMIN_PASSWORD + " TEXT);";
    //--TABLE SHOE
    public static final String SHOE_CREATE_TABLE =
            "CREATE TABLE " + SHOE_TABLE_NAME + " (" +
                    SHOE_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    SHOE_NAME + " TEXT);";
    //--TABLE SENSOR1
    public static final String SENSOR1_CREATE_TABLE =
            "CREATE TABLE " + SENSOR1_TABLE_NAME + " (" +
                    SENSOR1_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    SENSOR1_VALUE + " TEXT);";
    //--TABLE SENSOR2
    public static final String SENSOR2_CREATE_TABLE =
            "CREATE TABLE " + SENSOR2_TABLE_NAME + " (" +
                    SENSOR2_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    SENSOR2_VALUE + " TEXT);";
    //--TABLE SENSOR3
    public static final String SENSOR3_CREATE_TABLE =
            "CREATE TABLE " + SENSOR3_TABLE_NAME + " (" +
                    SENSOR3_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    SENSOR3_VALUE + " TEXT);";
    //--TABLE SENSOR4
    public static final String SENSOR4_CREATE_TABLE =
            "CREATE TABLE " + SENSOR4_TABLE_NAME + " (" +
                    SENSOR4_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    SENSOR4_VALUE + " TEXT);";

    //TABLE CONFIGURATION
    public static final String ADMIN_TABLE_CONFIGURATION = ADMIN_TABLE_NAME+"("+ADMIN_KEY+","+ADMIN_FIRSTNAME+","+ADMIN_LASTNAME+","+ADMIN_LOGIN+","+ADMIN_PASSWORD+")";
    public static final String SHOE_TABLE_CONFIGURATION = ADMIN_TABLE_NAME+"("+SHOE_KEY+","+SHOE_NAME+")";

    //DROP TABLE
    public static final String ADMIN_TABLE_DROP = "DROP TABLE IF EXISTS " + ADMIN_TABLE_NAME + ";";
    public static final String SHOE_TABLE_DROP = "DROP TABLE IF EXISTS " + SHOE_TABLE_NAME + ";";
    public static final String SENSOR1_TABLE_DROP = "DROP TABLE IF EXISTS " + SENSOR1_TABLE_NAME + ";";
    public static final String SENSOR2_TABLE_DROP = "DROP TABLE IF EXISTS " + SENSOR2_TABLE_NAME + ";";
    public static final String SENSOR3_TABLE_DROP = "DROP TABLE IF EXISTS " + SENSOR3_TABLE_NAME + ";";
    public static final String SENSOR4_TABLE_DROP = "DROP TABLE IF EXISTS " + SENSOR4_TABLE_NAME + ";";

}

