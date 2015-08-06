package sep.architecture.recipemymeal;

public class Material{
    int Image;
    String Name;
    boolean CheckBoxState;

    public Material(int aIcon, String aName){
        Image = aIcon;
        Name = aName;
        CheckBoxState = false;
    }

    public boolean getCheckBoxState() {
        return CheckBoxState;
    }

    public void setCheckBoxState(boolean aState){
        CheckBoxState = aState;
    }
}