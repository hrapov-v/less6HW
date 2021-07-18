package superky.keytwo.mynotes;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import superky.keytwo.mynotes.data.CardData;
import superky.keytwo.mynotes.observer.Publisher;

public class CardFragment extends Fragment {

    private static final String ARG_CARD_DATA = "Param_CardData";

    private CardData cardData;      // Данные по карточке
    private Publisher publisher;    // Паблишер, с его помощью обмениваемся данными

    private TextInputEditText noteName;
    private TextInputEditText noteBody;

    // Для релактирования данных
    public static CardFragment newInstance(CardData cardData) {
        CardFragment fragment = new CardFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_CARD_DATA, cardData);
        fragment.setArguments(args);
        return fragment;
    }

    // Для добавления новых данных
    public static CardFragment newInstance() {
        CardFragment fragment = new CardFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cardData = getArguments().getParcelable(ARG_CARD_DATA);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity)context;
        publisher = activity.getPublisher();
    }

    @Override
    public void onDetach() {
        publisher = null;
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card, container, false);
        initView(view);
        // если cardData пустая, то это добавление
        if (cardData != null) {
            populateView();
        }
        return view;
    }

    // Здесь соберем данные из views
    @Override
    public void onStop() {
        super.onStop();
        cardData = collectCardData();
    }

    // Здесь передадим данные в паблишер
    @Override
    public void onDestroy() {
        super.onDestroy();
        publisher.notifySingle(cardData);
    }

    private CardData collectCardData(){
        String noteName = this.noteName.getText().toString();
        String noteBody = this.noteBody.getText().toString();
        if (cardData != null) {
            CardData answer = new CardData(noteName, noteBody);
            answer.setId(cardData.getId());
            return answer;
        } else {
            return new CardData(noteName, noteBody);
        }
    }

    private void initView(View view) {
        noteName = view.findViewById(R.id.inputName);
        noteBody = view.findViewById(R.id.inputNoteBody);
    }

    private void populateView(){
        noteName.setText(cardData.getNote());
        noteBody.setText(cardData.getNoteBody());
    }

}