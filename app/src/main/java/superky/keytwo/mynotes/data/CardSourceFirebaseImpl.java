package superky.keytwo.mynotes.data;

import android.content.res.Resources;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import superky.keytwo.mynotes.R;

public class CardSourceFirebaseImpl implements CardSource {

    String CARDS_COLLECTION = "cards";
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    CollectionReference collectionReference = firebaseFirestore.collection(CARDS_COLLECTION);


    private List<CardData> cardsData = new ArrayList<>();

    @Override
    public CardSource init(CardSourceResponse cardSourceResponse) {
        collectionReference.orderBy(CardDataMapping.Fields.NOTENAME, Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(Task<QuerySnapshot> task) {
                cardsData = new ArrayList<>();
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Map<String, Object> documentMap = document.getData();
                        String id = document.getId();
                        CardData cardData = CardDataMapping.toCardData(id, documentMap);
                        cardsData.add(cardData);
                    }
                }
                cardSourceResponse.initialized(CardSourceFirebaseImpl.this);
            }
        });
        return this;
    }

    @Override
    public int size() {
        return cardsData.size();
    }

    @Override
    public CardData getCardData(int position) {
        return cardsData.get(position);
    }

    @Override
    public void addCardData(CardData cardData) {
        collectionReference.add(CardDataMapping.toDocument(cardData));
        cardsData.add(cardData);
    }

    @Override
    public void deleteCardData(int position) {
        collectionReference.document(cardsData.get(position).getId()).delete();
        cardsData.remove(position);
    }

    @Override
    public void updateCardData(int position, CardData cardData) {
        collectionReference.document(cardsData.get(position).getId()).set(CardDataMapping.toDocument(cardData));
        cardsData.set(position, cardData);
    }

    @Override
    public void clearCardData() {
        for(CardData cardData : cardsData) {
            collectionReference.document(cardData.getId()).delete();
        }
        cardsData.clear();
    }

    public List<CardData> getDataSource() {
        return cardsData;
    }
}
