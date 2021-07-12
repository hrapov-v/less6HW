package superky.keytwo.mynotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import superky.keytwo.mynotes.data.CardData;
import superky.keytwo.mynotes.data.CardSource;

public class SuperNotesAdapter extends RecyclerView.Adapter<SuperNotesAdapter.NotesViewHolder> {

    private CardSource dataSource;

    private OnMyClickListenner onMyClickListenner;

    public void SetOnMyClickListenner(OnMyClickListenner onMyClickListenner) {
        this.onMyClickListenner = onMyClickListenner;
    }

    public SuperNotesAdapter(CardSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.setData(dataSource.getCardData(position));
    }


    @Override
    public int getItemCount() {
        return dataSource.size();
    }


    public class NotesViewHolder extends RecyclerView.ViewHolder {

        private TextView noteName;
        private EditText noteBody;



        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            noteName = (itemView).findViewById(R.id.note_name);
            noteBody = (itemView).findViewById(R.id.note_body);
            noteName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onMyClickListenner.onMyClick(view, getAdapterPosition());
                }
            });
        }

        private void setData(CardData cardData) {
            noteName.setText(cardData.getNote());
            noteBody.setText(cardData.getNoteBody());

        }

    }
}
