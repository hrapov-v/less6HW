package superky.keytwo.mynotes.data;

public interface CadrSource {
    String getAddress();
    int size();
    CardData getCardData(int position);


}
