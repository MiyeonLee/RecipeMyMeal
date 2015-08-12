package sep.architecture.recipemymeal;

public class Tool{
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

    public void setCheckBoxState(boolean aState){
        CheckBoxState = aState;
    }
}