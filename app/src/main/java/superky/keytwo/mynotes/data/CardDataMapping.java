package superky.keytwo.mynotes.data;

import java.util.HashMap;
import java.util.Map;

public class CardDataMapping {
    public static class Fields {
        public static final String NOTENAME = "NoteName";
        public static final String NOTEBODY = "NoteBody";
    }

    public static CardData toCardData(String id, Map<String, Object> document) {
        CardData cardData = new CardData((String) document.get(Fields.NOTENAME),
                (String) document.get(Fields.NOTEBODY));
        cardData.setId(id);
        return cardData;
    }

    public static Map<String, Object> toDocument(CardData cardData) {
        Map<String, Object> document = new HashMap<>();
        document.put(Fields.NOTENAME, cardData.getNote());
        document.put(Fields.NOTEBODY, cardData.getNoteBody());
        return document;
    }

}
