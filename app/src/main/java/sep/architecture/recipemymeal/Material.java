package sep.architecture.recipemymeal;

public class Material{
    int Image;
    String Url;
    String Name;
    boolean CheckBoxState;

    public Material(int aIcon, String aName, String aUrl){
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