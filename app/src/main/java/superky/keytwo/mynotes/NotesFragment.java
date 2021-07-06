package superky.keytwo.mynotes;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class NotesFragment extends Fragment {

    private boolean isLandscape;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (isLandscape) {
            showLandNote(0);

        }
    }

    public static NotesFragment newInstance(String param1, String param2) {
        NotesFragment fragment = new NotesFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        createTextView((LinearLayout) view);
    }

    private void createTextView(@NonNull LinearLayout linearLayout) {
        String[] notes = getResources().getStringArray(R.array.notes);
        LayoutInflater layoutInflater = getLayoutInflater();
        for (int i = 0; i < notes.length; i++) {
            Log.d("superlog",i + " " + notes[i]);
            View item = layoutInflater.inflate(R.layout.list_item, linearLayout, false);
            TextView textView = item.findViewById(R.id.list_note);
            textView.setText(notes[i]);
            final int finalI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isLandscape) {
                        showLandNote(finalI);
                    } else {
                        showPortNote(finalI);
                    }
                }
            });
            textView.setTextSize(45);
            linearLayout.addView(item);
        }
    }

    private void showPortNote(int finalI) {
        Intent intent = new Intent(getActivity(), NotesViewPort.class);
        intent.putExtra(ViewNotes.KEY_INDEX, finalI);
        startActivity(intent);
    }

    private void showLandNote(int index) {
        ViewNotes viewNotes = ViewNotes.newInstance(index);
        getActivity().getSupportFragmentManager().beginTransaction().replace(
                R.id.notes_container_land, viewNotes).commit();
    }
}