package superky.keytwo.mynotes;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import superky.keytwo.mynotes.data.CardData;
import superky.keytwo.mynotes.data.CardSourceResourceImpl;
import superky.keytwo.mynotes.observer.Publisher;


public class SuperNotesFragment extends Fragment {

    private RecyclerView recyclerView;
    private CardSourceResourceImpl data;
    private SuperNotesAdapter superNotesAdapter;
    private static final int MY_DEFAULT_DURATION = 1000;
    private Navigation navigation;
    private Publisher publisher;
    private boolean moveToLastPosition;

    public static SuperNotesFragment newInstance() {
        return new SuperNotesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Получим источник данных для списка
        // Поскольку onCreateView запускается каждый раз,
        // при возврате в фрагмент, данные надо создавать один раз
        data = new CardSourceResourceImpl(getResources()).init();
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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity)context;
        navigation = activity.getNavigation();
        publisher = activity.getPublisher();
    }

    @Override
    public void onDetach() {
        navigation = null;
        publisher = null;
        super.onDetach();
    }

    //Внедряем options meny
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        //Запихиваем это меню в контейнер меню который приходит в этом методе.
        inflater.inflate(R.menu.fragment_menu, menu);
    }

    //Внедрили контекстное меню, только на вход не было inflater вызвал через getActivity().getMenuInflater().
    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    //переопределяю клики по меню.
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.action_add:
                data.addCardData(new CardData("Как то иначе " + (data.size() + 1), "Как то иначе описание " + (data.size() + 1)));
                //Заново задаём связь списка с адаптером
                recyclerView.setAdapter(superNotesAdapter);
                // !! НЕ РАБОТАЕТ С ЭТИМ МЕТОДОМ!! обновляем позицию data.size() - 1
                //superNotesAdapter.notifyItemInserted(data.size() - 1);
                //метод позволяющий скролить до нужной позиции
                recyclerView.smoothScrollToPosition(data.size() - 1);
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

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        //Задали позицию в адаптере
        int position = superNotesAdapter.getPosition();
        switch (item.getItemId()) {
            case R.id.action_add:
                data.addCardData(new CardData("Свеже добавленная заметка " + data.getCardData(position).getNote(),
                        "Свеже добавленное описание " + data.getCardData(position).getNoteBody()));
                // обновляем позицию data.size() - 1
                superNotesAdapter.notifyItemInserted(data.size() - 1);
                //метод позволяющий скролить до нужной позиции
                recyclerView.smoothScrollToPosition(data.size() - 1);
                return true;
            case R.id.action_update:
                data.updateCardData(position,new CardData("Измененная заметка " + data.getCardData(position).getNote(),
                        "Измененное описание " + data.getCardData(position).getNoteBody()));
                // обновляем позицию data.size() - 1
                superNotesAdapter.notifyItemChanged(position);
                return true;
            case R.id.action_delete:
                data.deleteCardData(position);
                //не забываем обновлять
                superNotesAdapter.notifyItemRemoved(position);
                return true;

        }
        return super.onContextItemSelected(item);
    }

    private void initList(RecyclerView recyclerView) {
        data = new CardSourceResourceImpl(getResources());
        data.init();
        recyclerView.setHasFixedSize(true);
        //layoutManager их три вида линейный шахматный и ещё один.....
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext()); //указал в макете но оставил чисто как в уроке
        recyclerView.setLayoutManager(layoutManager);
        superNotesAdapter = new SuperNotesAdapter(data, this);
        superNotesAdapter.SetOnMyClickListenner(new OnMyClickListenner() {
            @Override
            public void onMyClick(View view, int position) {

            }
        });
        recyclerView.setAdapter(superNotesAdapter);
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setChangeDuration(500);
        animator.setRemoveDuration(500);
        animator.setAddDuration(500);
        recyclerView.setItemAnimator(animator);
    }


}