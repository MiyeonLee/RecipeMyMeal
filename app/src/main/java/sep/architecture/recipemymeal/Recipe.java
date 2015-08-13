package sep.architecture.recipemymeal;

public class Recipe{
    String Url;
    int Image;
    String Name;
    int MaterialHash;
    int ToolHash;
    String Process;

    public Recipe(String aUrl, String aName, String aHash, String aThash, String aProcess){
        Image = R.drawable.food_general;
        Url=aUrl;
        Name = aName;
        MaterialHash = Integer.parseInt(aHash);
        ToolHash = Integer.parseInt(aThash);
        Process = aProcess;
    }

    public String getName(){ return Name; }
    public String getUrl(){ return Url; }
    public int getImageId(){ return Image; }
    public String getProcess(){ return Process; }
    public int getMaterialHash(){ return MaterialHash; }
    public int getToolHash(){ return ToolHash; }
}