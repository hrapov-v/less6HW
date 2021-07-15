package superky.keytwo.mynotes.data;

public interface CardSource {
    CardSource init(CardSourceResponse cardSourceResponse);
    int size();
    CardData getCardData(int position);

    void addCardData(CardData cardData);
    void deleteCardData(int position);
    void updateCardData(int position, CardData cardData);
    void clearCardData();

}
