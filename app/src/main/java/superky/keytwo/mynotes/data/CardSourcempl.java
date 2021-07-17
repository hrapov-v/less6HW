package superky.keytwo.mynotes.data;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

import superky.keytwo.mynotes.R;

public class CardSourcempl implements CardSource {
    private List<CardData> dataSource;
    private Resources resources;

    public CardSourcempl(Resources resources) {
        this.resources = resources;
        dataSource = new ArrayList<>();
    }

    public List<CardData> init() {
        String[] notes = resources.getStringArray(R.array.notes);
        String[] noteBody = resources.getStringArray(R.array.noteBody);
        for (int i = 0; i < notes.length; i++) {
            dataSource.add(new CardData(notes[i], noteBody[i]));
        }
        return dataSource;
    }


    @Override
    public CardSource init(CardSourceResponse cardSourceResponse) {
        return null;
    }

    @Override
    public int size() {
        return dataSource.size();
    }

    @Override
    public CardData getCardData(int position) {
        return dataSource.get(position);
    }

    @Override
    public void addCardData(CardData cardData) {
        dataSource.add(cardData);
    }

    @Override
    public void deleteCardData(int position) {
        dataSource.remove(position);
    }

    @Override
    public void updateCardData(int position, CardData cardData) {
        dataSource.set(position, cardData);
    }

    @Override
    public void clearCardData() {
        dataSource.clear();
    }

    public List<CardData> getDataSource() {
        return dataSource;
    }
}
