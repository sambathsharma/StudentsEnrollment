package com.example.studentsenrollment.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.studentsenrollment.R;
import com.example.studentsenrollment.models.StudentDetailsModel;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by UITOUX5 on 20/06/17.
 */

public class StudentDetailsAdapter extends RecyclerView.Adapter<StudentDetailsAdapter.MyViewHolder> {
    Context context;
    ArrayList<StudentDetailsModel> arrayList;


    public StudentDetailsAdapter(Context context, ArrayList<StudentDetailsModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.row_student_enrollment, parent, false);
        MyViewHolder ph = new MyViewHolder(v);
        return ph;

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final StudentDetailsModel studentDetailsModel = arrayList.get(position);

        holder.tv_name.setText(studentDetailsModel.getName());
        holder.tv_mobile.setText(studentDetailsModel.getMobile());
        holder.tv_gender.setText(studentDetailsModel.getGender());
        holder.tv_dob.setText(studentDetailsModel.getDob());
        holder.tv_department.setText(studentDetailsModel.getDepartment());
        holder.tv_year.setText(studentDetailsModel.getYear());
        holder.tv_address.setText(studentDetailsModel.getAddress());

        }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name, tv_mobile, tv_gender, tv_dob, tv_department, tv_year, tv_address;

        private MyViewHolder(final View view) {
            super(view);

            tv_name = view.findViewById(R.id.tv_name);
            tv_mobile = view.findViewById(R.id.tv_mobile);
            tv_gender = view.findViewById(R.id.tv_gender);
            tv_dob = view.findViewById(R.id.tv_dob);
            tv_department = view.findViewById(R.id.tv_department);
            tv_year = view.findViewById(R.id.tv_year);
            tv_address = view.findViewById(R.id.tv_address);

        }

    }
}