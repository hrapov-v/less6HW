package superky.keytwo.mynotes.data;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

import superky.keytwo.mynotes.R;

public class CardSourceImpl implements CardSource {
    private List<CardData> dataSource;
    private Resources resources;

    public CardSourceImpl(Resources resources) {
        this.resources = resources;
        dataSource = new ArrayList<>();
    }

    public List<CardData> init(){
        String[] notes = resources.getStringArray(R.array.notes);
        String[] noteBody = resources.getStringArray(R.array.noteBody);
        for (int i = 0; i < notes.length; i++) {
            dataSource.add(new CardData(notes[i], noteBody[i]));
        }
        return dataSource;
    }

    @Override
    public String getAddress() {
        return "null";
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

    }

    @Override
    public void updateCardData(int position, CardData cardData) {

    }

    @Override
    public void clearCardData() {

    }

    public List<CardData> getDataSource() {
        return dataSource;
    }
}
