package superky.keytwo.mynotes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import superky.keytwo.mynotes.data.CardData;
import superky.keytwo.mynotes.data.CardSourceImpl;


public class SuperNotesFragment extends Fragment {

    private RecyclerView recyclerView;
    private CardSourceImpl data;
    private SuperNotesAdapter superNotesAdapter;

    public static SuperNotesFragment newInstance() {
        return new SuperNotesFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_super_notes, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        initList(recyclerView);
        //ЗДЕСЬ важно этот метод указывает на то что наш фрагмент имеет собственное меню.
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        //Запихиваем это меню в контейнер меню который приходит в этом методе.
        inflater.inflate(R.menu.fragment_menu, menu);
    }

    //переопределяю клики по меню.
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        return super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.action_add:
                data.addCardData(new CardData("Как то иначе " + (data.size() + 1), "Как то иначе описание " + (data.size() + 1)));
                // обновляем позицию data.size() - 1
                superNotesAdapter.notifyItemInserted(data.size() - 1);
                //метод позволяющий скролить до нужной позиции
                recyclerView.scrollToPosition(data.size() - 1);
                return true;
            case R.id.action_delete:
                //Благодаря интерфейсу из урока эти методы будут работать если данные поступят откуда угодно.
                data.clearCardData();
                //эта команда обнуляет все элементы
                superNotesAdapter.notifyDataSetChanged();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initList(RecyclerView recyclerView) {
        data = new CardSourceImpl(getResources());
        data.init();
        recyclerView.setHasFixedSize(true);
        //layoutManager их три вида линейный шахматный и ещё один.....
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext()); //указал в макете но оставил чисто как в уроке
        recyclerView.setLayoutManager(layoutManager);
        superNotesAdapter = new SuperNotesAdapter(data);
        superNotesAdapter.SetOnMyClickListenner(new OnMyClickListenner() {
            @Override
            public void onMyClick(View view, int position) {

            }
        });
        recyclerView.setAdapter(superNotesAdapter);
    }


}