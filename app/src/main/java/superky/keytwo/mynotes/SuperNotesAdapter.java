package superky.keytwo.mynotes;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import superky.keytwo.mynotes.data.CardData;
import superky.keytwo.mynotes.data.CardSource;

public class SuperNotesAdapter extends RecyclerView.Adapter<SuperNotesAdapter.NotesViewHolder> {

    private CardSource dataSource;

    Fragment fragment;

    private OnMyClickListenner onMyClickListenner;

    public void SetOnMyClickListenner(OnMyClickListenner onMyClickListenner) {
        this.onMyClickListenner = onMyClickListenner;
    }

    public SuperNotesAdapter(CardSource dataSource, Fragment fragment) {
        this.dataSource = dataSource;
        this.fragment = fragment;
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
            this.noteName = (itemView).findViewById(R.id.note_name);
            this.noteBody = (itemView).findViewById(R.id.note_body);
            fragment.registerForContextMenu(itemView);
            this.noteName.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View view) {
//                    onMyClickListenner.onMyClick(view, getAdapterPosition());
                    //координаты где будет отображатся контекстное меню
                    view.showContextMenu(0,0);
                }
            });
        }

        private void setData(CardData cardData) {
            this.noteName.setText(cardData.getNote());
            this.noteBody.setText(cardData.getNoteBody());
        }

    }
}
