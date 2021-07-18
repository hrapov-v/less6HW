package superky.keytwo.mynotes;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

    private int position;

    private AdapterView.OnItemClickListener itemClickListener;  // Слушатель будет устанавливаться извне

    private int menuPosition;

    public int getPosition() {
        return position;
    }

    Fragment fragment;

    private OnMyClickListenner onMyClickListenner;

    public void SetOnMyClickListenner(OnMyClickListenner onMyClickListenner) {
        this.onMyClickListenner = onMyClickListenner;
    }

    public SuperNotesAdapter(CardSource dataSource, Fragment fragment) {
        this.dataSource = dataSource;
        this.fragment = fragment;
    }

    public SuperNotesAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    public void setDataSource(CardSource dataSource) {
        this.dataSource = dataSource;
        notifyDataSetChanged();
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


    // Сеттер слушателя нажатий
    public void SetOnItemClickListener(AdapterView.OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public int getMenuPosition() {
        return menuPosition;
    }


    public class NotesViewHolder extends RecyclerView.ViewHolder {

        private TextView noteName;
        private EditText noteBody;


        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            this.noteName = (itemView).findViewById(R.id.note_name);
            this.noteBody = (itemView).findViewById(R.id.note_body);
            fragment.registerForContextMenu(itemView);
            // можно использовать onLongClickListenner для долгого нажатия.
            this.noteName.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View view) {
//                    onMyClickListenner.onMyClick(view, getAdapterPosition());
                    menuPosition = getLayoutPosition();
                    itemView.showContextMenu();
                }
            });
            this.noteName.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    menuPosition = getLayoutPosition();
                    itemView.showContextMenu(10, 10);
                    return true;
                }
            });
        }

        private void registerContextMenu(@NonNull View itemView) {
            if (fragment != null) {
                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        menuPosition = getLayoutPosition();
                        return false;
                    }
                });
                fragment.registerForContextMenu(itemView);
            }
        }


        private void setData(CardData cardData) {
            this.noteName.setText(cardData.getNote());
            this.noteBody.setText(cardData.getNoteBody());
        }


    }
}
