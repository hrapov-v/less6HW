package superky.keytwo.mynotes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import superky.keytwo.mynotes.data.CardSourceImpl;


public class SuperNotesFragment extends Fragment {

    public static SuperNotesFragment newInstance() {
        return new SuperNotesFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_super_notes, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        initList(recyclerView);
        return view;
    }

    private void initList(RecyclerView recyclerView) {
        CardSourceImpl data = new CardSourceImpl(getResources());
        data.init();
        recyclerView.setHasFixedSize(true);
        //layoutManager их три вида линейный шахматный и ещё один.....
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext()); //указал в макете но оставил чисто как в уроке
        recyclerView.setLayoutManager(layoutManager);
        SuperNotesAdapter superNotesAdapter = new SuperNotesAdapter(data);
        superNotesAdapter.SetOnMyClickListenner(new OnMyClickListenner() {
            @Override
            public void onMyClick(View view, int position) {

            }
        });
        recyclerView.setAdapter(superNotesAdapter);
    }


}