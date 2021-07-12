package superky.keytwo.mynotes.data;

public interface CardSource {
    String getAddress();
    int size();
    CardData getCardData(int position);

    void addCardData(CardData cardData);
    void deleteCardData(int position);
    void updateCardData(int position, CardData cardData);
    void clearCardData();

}
