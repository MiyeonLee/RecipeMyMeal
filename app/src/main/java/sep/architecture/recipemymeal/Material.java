package sep.architecture.recipemymeal;

import java.io.Serializable;

public class Material implements Serializable{
    int Image;
    int BitPosition;
    String Url;
    String Name;
    boolean CheckBoxState;

    public Material(int aIcon, String aName, String aUrl, int aBitPosition){
        Image = aIcon;
        Name = aName;
        Url = aUrl;
        CheckBoxState = false;
        BitPosition = aBitPosition;
    }

    public boolean getCheckBoxState() {
        return CheckBoxState;
    }
    public int getBitposition() {
        return BitPosition;
    }
    public String getName() {
        return Name;
    }

    public void setCheckBoxState(boolean aState){
        CheckBoxState = aState;
    }
}