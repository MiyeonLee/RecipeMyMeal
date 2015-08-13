package sep.architecture.recipemymeal;

public class Recipe{
    String Url;
    int Image;
    String Name;

    public Recipe(String aUrl, String aName){
        Image = R.drawable.food_general;
        Url=aUrl;
        Name = aName;
    }

    public String getName(){ return Name; }
    public String getUrl(){ return Url; }
    public int getImageId(){ return Image; }
}