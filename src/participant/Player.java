package participant;

public class Player {
    String name;
    String sport;
    long collegeId;
    String course;
    Player(String sport, String name , long collegeId , String course){
        this.name = name;
        this.sport = sport;
        this.collegeId = collegeId;
        this.course = course;
    }
}