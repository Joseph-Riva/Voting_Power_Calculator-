public class VPBody {
    private int numVotes;
    private int TotalVotes;
    private String Name;
    private String AgeRange;
    public VPBody(String Name, String AgeRange, int numVotes){
        this.Name = Name;
        this.AgeRange = AgeRange;
        this.numVotes = numVotes;
        this.TotalVotes = TotalVotes;
    }

    public int getNumVotes(){
        return numVotes;
    }
    public String getName() {
        return Name;
    }
    public String getAgeRange() {
        return AgeRange;
    }
    public String toString(){
        return "Voting Body" + Name + "(Age Range: " + AgeRange + ")has: " + numVotes + " votes in it";
    }
}
