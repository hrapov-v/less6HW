package superky.keytwo.mynotes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SuperNotesAdapter extends RecyclerView.Adapter<SuperNotesAdapter.NotesViewHolder> {

    private String[] dataSource;

    private OnMyClickListenner onMyClickListenner;

    public void SetOnMyClickListenner(OnMyClickListenner onMyClickListenner) {
        this.onMyClickListenner = onMyClickListenner;
    }

    public SuperNotesAdapter(String[] dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.getTextView().setText(this.dataSource[position]);
    }

    @Override
    public int getItemCount() {
        return dataSource.length;
    }


    public class NotesViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;


        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = ((LinearLayout) itemView).findViewById(R.id.list_note);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onMyClickListenner.onMyClick(view, getAdapterPosition());
                }
            });
        }

        public TextView getTextView() {
            return textView;
        }
    }
}
