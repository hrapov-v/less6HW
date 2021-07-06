package superky.keytwo.mynotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SuperNotesAdapter extends RecyclerView.Adapter<SuperNotesAdapter.NotesViewHolder> {

    private String[] dataSource;

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
        return 5;
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;


        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = ((LinearLayout) itemView).findViewById(R.id.list_note);
        }

        public TextView getTextView() {
            return textView;
        }
    }
}
