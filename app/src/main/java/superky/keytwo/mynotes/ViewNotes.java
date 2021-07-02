package superky.keytwo.mynotes;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ViewNotes extends Fragment {

    public static final String KEY_INDEX = "index";
    private int index;

    public ViewNotes() {
        // Required empty public constructor
    }

    public static ViewNotes newInstance(int index) {
        ViewNotes fragment = new ViewNotes();
        Bundle args = new Bundle();
        args.putInt(KEY_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            index = getArguments().getInt(KEY_INDEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_notes, container, false);
        TextView textView = view.findViewById(R.id.note_name_view);
        EditText editText = view.findViewById(R.id.view_note);
        String[] sName = getResources().getStringArray(R.array.notes);
        String[] sBody = getResources().getStringArray(R.array.noteBody);
        textView.setText(sName[index]);
        editText.setText(sBody[index]);
        return view;
    }
}