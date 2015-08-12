package sep.architecture.recipemymeal;

import java.io.Serializable;

public class Material implements Serializable{
    int Image;
    int BitPosition;
    String Url;
    String Name;
    boolean CheckBoxState;

    public Material(int aIcon, String aName, String aUrl, int BitPosition){
        Image = aIcon;
        Name = aName;
        Url = aUrl;
        CheckBoxState = false;
    }

    public boolean getCheckBoxState() {
        return CheckBoxState;
    }

    public void setCheckBoxState(boolean aState){
        CheckBoxState = aState;
    }
}