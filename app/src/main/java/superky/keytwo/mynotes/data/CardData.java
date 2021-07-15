package superky.keytwo.mynotes.data;

public class CardData {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String note;
    private String noteBody;

    public CardData(String note, String noteBody) {
        this.note = note;
        this.noteBody = noteBody;
    }

    public String getNote() {
        return note;
    }

    public String getNoteBody() {
        return noteBody;
    }
}
