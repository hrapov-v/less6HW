package superky.keytwo.mynotes.data;

public interface CardSource {
    String getAddress();
    int size();
    CardData getCardData(int position);


}
