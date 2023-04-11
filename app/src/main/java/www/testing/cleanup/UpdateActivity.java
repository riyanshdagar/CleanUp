package www.testing.cleanup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText problem_input,hostel_input,room_input;
    Button update_button,delete_button;

    String id,problem,hostel,room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        problem_input = findViewById(R.id.problemInput2);
        hostel_input = findViewById(R.id.hostelInput2);
        room_input = findViewById(R.id.roomInput2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //this is important to call and then updateData
        getAndSetIntentData();

        //set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(problem);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // that update query function written in MyDatabseHelper will be called here
                MyDatabseHelper myDB = new MyDatabseHelper(UpdateActivity.this);
                problem = problem_input.getText().toString().trim();
                hostel = hostel_input.getText().toString().trim();
                room = room_input.getText().toString().trim();
                myDB.updateData(id, problem, hostel, room);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog(); //this method is called here on click
            }
        });

    }

    void getAndSetIntentData() {
        if( getIntent().hasExtra("id") && getIntent().hasExtra("problem") && getIntent().hasExtra("hostel") && getIntent().hasExtra("room") ) {
            //getting data from intent
            id = getIntent().getStringExtra("id");
            problem = getIntent().getStringExtra("problem");
            hostel = getIntent().getStringExtra("hostel");
            room = getIntent().getStringExtra("room");

            //setting intent data
            problem_input.setText(problem);
            hostel_input.setText(hostel);
            room_input.setText(room);
        }
        else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + problem + " ?");
        builder.setMessage("Are you sure you want to delete " + problem + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabseHelper myDB = new MyDatabseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish(); //this will automatically redirect you to the main acitivity
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