package project.CoffeeShop;

public class Research {
    public String name;
    public int cost;
    public int researchPoints;
    public int type;
    public String typeName;
    public int completionPoints;
    public boolean completed;

public Research(String name, int cost, int researchPoints, int type, String typeName){
    this.name = name;
    this.cost = cost;
    this.researchPoints = researchPoints;
    this.type = type;
    this.typeName = typeName;
    this.completionPoints = 0;
    this.completed = false;
}

public boolean processResearch_points(int points){
    completionPoints += points;
    if(completionPoints>=researchPoints){
        completed = true;
        return true;
    }
    return false;
}

public double researchPercentage(){
    return ((double) completionPoints / researchPoints) * 100;
}

}

