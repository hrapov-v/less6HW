package superky.keytwo.mynotes;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SuperNotesFragment extends Fragment {

    public static SuperNotesFragment newInstance() {
        return new SuperNotesFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_super_notes, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        String[] data = getResources().getStringArray(R.array.notes);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext()); //указал в макете но оставил чисто как в уроке
        recyclerView.setLayoutManager(layoutManager);
        SuperNotesAdapter superNotesAdapter = new SuperNotesAdapter(data);
        superNotesAdapter.SetOnMyClickListenner(new OnMyClickListenner() {
            @Override
            public void onMyClick(View view, int position) {

                viewNotes(position, inflater, container);
            }
        });
        recyclerView.setAdapter(superNotesAdapter);
        return view;
    }

    private void viewNotes(int position, LayoutInflater inflater, ViewGroup container) {
        View view;
        view = inflater.inflate(R.layout.fragment_view_notes, container, false);
        TextView textView = view.findViewById(R.id.note_name_view);
        EditText editText = view.findViewById(R.id.view_note);
        String[] sName = getResources().getStringArray(R.array.notes);
        String[] sBody = getResources().getStringArray(R.array.noteBody);
        textView.setText(sName[position]);
        editText.setText(sBody[position]);
    }

}