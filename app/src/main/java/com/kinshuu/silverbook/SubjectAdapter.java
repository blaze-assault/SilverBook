package com.kinshuu.silverbook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.viewholder> {

    itemclicked activity;
    private ArrayList<Subject> subjects;
    public interface itemclicked{
        void onItemClicked(int index);
    }

    public SubjectAdapter(Context context, ArrayList<Subject> list){
        subjects=list;
        activity=(itemclicked)context;
    }

    public class viewholder extends RecyclerView.ViewHolder{

        TextView TVsubjectname,TVSGPI,TVattendance;
        Button BTNpresent,BTNabsent;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            TVattendance=itemView.findViewById(R.id.TVattendance);
            TVSGPI=itemView.findViewById(R.id.TVSGPI);
            TVsubjectname=itemView.findViewById(R.id.TVsubjectName);
            BTNpresent=itemView.findViewById(R.id.BTNpresent);
            BTNabsent=itemView.findViewById(R.id.BTNabsent);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onItemClicked(subjects.indexOf((Subject)v.getTag()));
                }
            });
        }
    }

    @NonNull
    @Override
    public SubjectAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_items,viewGroup,false);
        return new viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final SubjectAdapter.viewholder viewHolder, final int i) {
        viewHolder.itemView.setTag(subjects.get(i));
        viewHolder.TVsubjectname.setText(subjects.get(i).getSub_name());
        viewHolder.TVSGPI.setText( Double.toString(subjects.get(i).getSGPI()));
        viewHolder.TVattendance.setText(Double.toString(subjects.get(i).getAttendancePercent()));
        viewHolder.BTNpresent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subjects.get(i).setPresent(subjects.get(i).getPresent()+1);
                subjects.get(i).setTotaldays(subjects.get(i).getTotaldays()+1);
                subjects.get(i).setAttendancePercent(((subjects.get(i).getPresent())*100)/(subjects.get(i).getTotaldays()));
                viewHolder.TVattendance.setText(Double.toString(subjects.get(i).getAttendancePercent()));
                //calculate attendance percentage here and set it to the textbox.
            }
        });
        viewHolder.BTNabsent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subjects.get(i).setTotaldays(subjects.get(i).getTotaldays()+1);
                subjects.get(i).setAttendancePercent(((subjects.get(i).getPresent())*100)/(subjects.get(i).getTotaldays()));
                viewHolder.TVattendance.setText(Double.toString(subjects.get(i).getAttendancePercent()));
                //calculate attendance percentage here and set it to the textbox.
            }
        });
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }
}