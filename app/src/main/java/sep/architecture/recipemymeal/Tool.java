package sep.architecture.recipemymeal;

public class Tool{
    int Image;
    String Url;
    String Name;
    boolean CheckBoxState;

    public Tool(int aIcon, String aName, String aUrl){
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