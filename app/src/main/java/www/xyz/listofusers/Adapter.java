package www.xyz.listofusers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    LayoutInflater inflater;
    List<Users> users;

    public Adapter(Context ctx, List<Users> users){
        this.inflater = LayoutInflater.from(ctx);
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_list_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // bind the data
        holder.name.setText(users.get(position).getName());
        holder.email.setText(users.get(position).getEmail());
        holder.gender.setText(users.get(position).getGender());
        holder.status.setText(users.get(position).getStatus());

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{
        TextView name,gender,email,status;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            gender = itemView.findViewById(R.id.gender);
            status = itemView.findViewById(R.id.status);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Item is Clicked", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
