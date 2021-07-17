package superky.keytwo.mynotes.data;

import android.os.Parcel;
import android.os.Parcelable;

public class CardData implements Parcelable{

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

    protected CardData(Parcel in) {
        note = in.readString();
        noteBody = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(note);
        dest.writeString(noteBody);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<CardData> CREATOR = new Parcelable.Creator<CardData>() {
        @Override
        public CardData createFromParcel(Parcel in) {
            return new CardData(in);
        }

        @Override
        public CardData[] newArray(int size) {
            return new CardData[size];
        }
    };

    public String getNote() {
        return note;
    }

    public String getNoteBody() {
        return noteBody;
    }
}
