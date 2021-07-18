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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import superky.keytwo.mynotes.data.CardData;
import superky.keytwo.mynotes.data.CardSource;
import superky.keytwo.mynotes.data.CardSourceFirebaseImpl;
import superky.keytwo.mynotes.data.CardSourceResourceImpl;
import superky.keytwo.mynotes.data.CardSourceResponse;
import superky.keytwo.mynotes.observer.Observer;
import superky.keytwo.mynotes.observer.Publisher;


public class SuperNotesFragment extends Fragment {

    private RecyclerView recyclerView;
    private CardSource data;
    private SuperNotesAdapter superNotesAdapter;
    private static final int MY_DEFAULT_DURATION = 500;
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
        //data = new CardSourceResourceImpl(getResources()).init();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_super_notes, container, false);
        initView(view);
        //ЗДЕСЬ важно этот метод указывает на то что наш фрагмент имеет собственное меню.
        setHasOptionsMenu(true);
        //получаем данные и сохраняем в FireBase
        data = new CardSourceFirebaseImpl().init(new CardSourceResponse() {
            @Override
            public void initialized(CardSource cardSource) {
                superNotesAdapter.notifyDataSetChanged();
            }
        });
        superNotesAdapter.setDataSource(data);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity) context;
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
        switch (item.getItemId()) {
            case R.id.action_add:
                navigation.addFragment(CardFragment.newInstance(), true);
                publisher.subscribe(new Observer() {
                    @Override
                    public void updateCardData(CardData cardData) {
                        data.addCardData(cardData);
                        superNotesAdapter.notifyItemInserted(data.size() - 1);
                        // это сигнал, чтобы вызванный метод onCreateView
                        // перепрыгнул на конец списка
                        moveToLastPosition = true;
                    }
                });
                return true;
            case R.id.action_delete:
                int size = data.size();
                data.clearCardData();
                superNotesAdapter.notifyItemRangeRemoved(0, size);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        //Задали позицию в адаптере
        final int position = superNotesAdapter.getMenuPosition();
        switch (item.getItemId()) {
            case R.id.action_update:
                final int updatePosition = superNotesAdapter.getMenuPosition();
                navigation.addFragment(CardFragment.newInstance(data.getCardData(updatePosition)), true);
                publisher.subscribe(new Observer() {
                    @Override
                    public void updateCardData(CardData cardData) {
                        data.updateCardData(updatePosition, cardData);
                        superNotesAdapter.notifyItemChanged(updatePosition);
                    }
                });
                return true;
            case R.id.action_delete:
                data.deleteCardData(position);
                superNotesAdapter.notifyItemRemoved(position);
                return true;
        }
        return super.onContextItemSelected(item);
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        initRecyclerView();
    }

    private void initRecyclerView() {

        // Эта установка служит для повышения производительности системы
        recyclerView.setHasFixedSize(true);

        // Будем работать со встроенным менеджером
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // Установим адаптер
        superNotesAdapter = new SuperNotesAdapter(this);
        recyclerView.setAdapter(superNotesAdapter);

        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(MY_DEFAULT_DURATION);
        animator.setRemoveDuration(MY_DEFAULT_DURATION);
        recyclerView.setItemAnimator(animator);

        if (moveToLastPosition) {
            recyclerView.smoothScrollToPosition(data.size() - 1);
            moveToLastPosition = false;
        }
    }

}