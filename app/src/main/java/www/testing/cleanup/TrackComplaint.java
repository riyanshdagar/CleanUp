package www.testing.cleanup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TrackComplaint extends AppCompatActivity {

    RecyclerView recyclerView;
    MyDatabseHelper myDB;
    ArrayList<String> complaint_id,problem,hostel,room;
    ImageView empty_imageView;
    TextView no_data;
    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_complaint);

        empty_imageView = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);

        recyclerView = findViewById(R.id.recyclerView);
        myDB = new MyDatabseHelper(TrackComplaint.this);
        complaint_id = new ArrayList<>();
        problem = new ArrayList<>();
        hostel = new ArrayList<>();
        room = new ArrayList<>();

        storeDataInArrays();
        customAdapter = new CustomAdapter(TrackComplaint.this,this,complaint_id,problem,hostel,room);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(TrackComplaint.this));
    }

    //this is used to refresh trackcomplaint page when data is updatd or deleted
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( requestCode == 1 ) {
            recreate();
        }
    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if( cursor.getCount() == 0 ) {
            empty_imageView.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }
        else {
            while( cursor.moveToNext() ) {
                complaint_id.add(cursor.getString(0));  // here 0,1,2,3 means cursor will read column 0,1,2,3
                problem.add(cursor.getString(1));
                hostel.add(cursor.getString(2));
                room.add(cursor.getString(3));
            }
            empty_imageView.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //to show three dots instead of delete basically drop down
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete_all) {
            confirmDialog(); //calling the below function here
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete all ?");
        builder.setMessage("Are you sure you want to delete all data ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabseHelper myDB = new MyDatabseHelper(TrackComplaint.this);
                myDB.deletAllData();
                //refresh activity
                Intent intent = new Intent(TrackComplaint.this, TrackComplaint.class);
                startActivity(intent);
                finish(); //this will automatically redirect you to the trackcomplaint acitivity
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
