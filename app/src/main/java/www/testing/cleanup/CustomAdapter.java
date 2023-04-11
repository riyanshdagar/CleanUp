package www.testing.cleanup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;

    Activity activity;

    private ArrayList complaint_id,problem,hostel,room;


    CustomAdapter(Activity activity,Context context, ArrayList complaint_id, ArrayList problem, ArrayList hostel, ArrayList room) {
        this.activity = activity;
        this.context = context;
        this.complaint_id = complaint_id;
        this.problem = problem;
        this.hostel = hostel;
        this.room = room;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.complaint_id_txt.setText(String.valueOf(complaint_id.get(position)));
        holder.problem_txt.setText(String.valueOf(problem.get(position)));
        holder.hostel_txt.setText(String.valueOf(hostel.get(position)));
        holder.room_txt.setText(String.valueOf(room.get(position)));

        //the below is so that if we click on an individual row we gets directed to the update and delete menu
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id",String.valueOf(complaint_id.get(holder.getAdapterPosition())));
                intent.putExtra("problem",String.valueOf(problem.get(holder.getAdapterPosition())));
                intent.putExtra("hostel",String.valueOf(hostel.get(holder.getAdapterPosition())));
                intent.putExtra("room",String.valueOf(room.get(holder.getAdapterPosition())));
                activity.startActivityForResult(intent,1);
            }
        });

    }

    @Override
    public int getItemCount() {
         return complaint_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView complaint_id_txt,problem_txt,hostel_txt,room_txt;

        //linear layout for update function
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            complaint_id_txt = itemView.findViewById(R.id.complaint_id_txt);
            problem_txt = itemView.findViewById(R.id.problem_txt);
            hostel_txt = itemView.findViewById(R.id.hostel_txt);
            room_txt = itemView.findViewById(R.id.room_txt);

            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
