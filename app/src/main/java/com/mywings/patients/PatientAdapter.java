package com.mywings.patients;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientViewHolder> {

    private List<User> lstUser;

    public PatientAdapter(List<User> lstUser) {

        this.lstUser = lstUser;

    }


    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.patient_row, viewGroup, false);

        return new PatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientViewHolder patientViewHolder, final int i) {

        patientViewHolder.lblName.setText(lstUser.get(i).getName());

        patientViewHolder.cvContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserDataHolder.getInstance().setSelectedUser(lstUser.get(i));
                Intent intent = new Intent(view.getContext(), PatientInfoActivity.class);
                intent.putExtra("id", lstUser.get(i).getId());
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return lstUser.size();
    }

    static class PatientViewHolder extends RecyclerView.ViewHolder {

        private TextView lblName;
        private CardView cvContainer;

        public PatientViewHolder(@NonNull View itemView) {
            super(itemView);
            lblName = itemView.findViewById(R.id.lblName);
            cvContainer = itemView.findViewById(R.id.cvContainer);
        }
    }

}
