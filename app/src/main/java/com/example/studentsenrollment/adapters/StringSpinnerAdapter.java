 package com.example.studentsenrollment.adapters;

 import android.content.Context;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.ArrayAdapter;
 import android.widget.TextView;

 import androidx.annotation.NonNull;
 import androidx.annotation.Nullable;

 import com.example.studentsenrollment.R;

 import java.util.ArrayList;

 /**
  * Created by harman on 15/04/17
  */

 public class StringSpinnerAdapter extends ArrayAdapter<String> {

     private ArrayList<String> items;
 //    private static int selectedIndex = -1;

     private static class ViewHolder {
         private TextView spinnerView;
     }

     public StringSpinnerAdapter(Context context, int textViewResourceId, ArrayList<String> items) {
         super(context, textViewResourceId, items);
         this.items = items;
     }

     public View getView(int position, View convertView, ViewGroup parent) {
         View vi = convertView;
         final ViewHolder holder;
         try {
             if (convertView == null) {
                 LayoutInflater inflater = LayoutInflater.from(getContext());
                 vi = inflater.inflate(R.layout.spinner_text, parent, false);
                 holder = new ViewHolder();

                 holder.spinnerView = vi.findViewById(R.id.spinner_textview);

                 vi.setTag(holder);
             } else {
                 holder = (ViewHolder) vi.getTag();
             }
             holder.spinnerView.setText(items.get(position));

         } catch (Exception e) {
             e.printStackTrace();
         }
         return vi;
     }

     @Override
     public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
         View vi = convertView;
         final ViewHolder holder;
         try {
             if (convertView == null) {
                 LayoutInflater inflater = LayoutInflater.from(getContext());
                 vi = inflater.inflate(R.layout.spinner_text, parent, false);
                 holder = new ViewHolder();

                 holder.spinnerView = vi.findViewById(R.id.spinner_textview);

                 vi.setTag(holder);
             } else {
                 holder = (ViewHolder) vi.getTag();
             }
 //            if(position == selectedIndex){
 //                vi.setBackgroundResource(R.color.intermediate_grey);
 //            }else{
 //                vi.setBackgroundResource(R.color.white);
 //            }
             holder.spinnerView.setText(items.get(position));

         } catch (Exception e) {
             e.printStackTrace();
         }
         return vi;
     }
 }
