package www.testing.cleanup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Complaints.db";    //this is basically a constant
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_complaints";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_PROBLEM = "problem";
    private   static final String COLUMN_HOSTEL = "hostel";
    private static final String COLUMN_ROOMNO = "room";

    MyDatabseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_PROBLEM + " TEXT, " +
                        COLUMN_HOSTEL + " TEXT, " +
                        COLUMN_ROOMNO + " INTEGER);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addBook( String problem, String hostel, int room ) {
        SQLiteDatabase db = this.getWritableDatabase();  //this refers to funtions of SQLiteOpenHelper class   // function is used to write in our table
        ContentValues cv = new ContentValues(); // this is used to store data from our application

        cv.put(COLUMN_PROBLEM,problem ); //here first parameter is column and other is data nd third is type of integer to store pages
        cv.put(COLUMN_HOSTEL,hostel );
        cv.put(COLUMN_ROOMNO,room );

        long result = db.insert(TABLE_NAME, null, cv ); //first parameter is table name 2nd is null 3rd is the data we generated using put
        if( result == -1 ) {
            Toast.makeText(context,"Failed", Toast.LENGTH_SHORT).show();  //first parameter is context object from constructor
        }
        else {
            Toast.makeText(context,"Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }
    // cursor object returns cursor which contains all the data in the row
    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if( db != null ) {
            //this will be called in TrackComplaint activity
            cursor = db.rawQuery(query, null); //on passing query as a parameter we see color changing off above written query
        }
        return cursor;
    }

    void updateData(String row_id, String problem, String hostel, String room) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PROBLEM, problem);
        cv.put(COLUMN_HOSTEL, hostel);
        cv.put(COLUMN_ROOMNO, room);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[] {row_id});
        if( result == -1 ) {
            Toast.makeText(context, "Failed to Update.", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Successfully Updated!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow(String row_id) { // call this method in update activity
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[] {row_id});
        if( result == -1 ) {
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
        }
    }

    void deletAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}
