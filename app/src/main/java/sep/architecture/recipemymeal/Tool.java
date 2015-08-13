package sep.architecture.recipemymeal;

import java.io.Serializable;

public class Tool implements Serializable{
    int Image;
    String Url;
    String Name;
    int BitPosition;
    boolean CheckBoxState;

    public Tool(int aIcon, String aName, String aUrl, int position){
        Image = aIcon;
        Name = aName;
        Url = aUrl;
        CheckBoxState = false;
        BitPosition = position;
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