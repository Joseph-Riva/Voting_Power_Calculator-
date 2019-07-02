import javax.lang.model.type.ArrayType;
import java.util.ArrayList;
import java.io.*;

public class Calculator {
    private VPBody[] Bodies;
    private static ArrayList<String> Coalitions4 = new ArrayList<String>();
    private static ArrayList<String> WinningCoalitions4 = new ArrayList<String>();
    private static int TotalVotes =  33987640;
    //first total = 33987640
    //theoretical total = 37,880,358
    private static int votesNeeded = (int)(TotalVotes * .51);
    //real = (int)(TotalVotes * .51)
    //theoretical votesNeeded = 19,318,982
    public Calculator(){
        Bodies = new VPBody[7];
        fillBodies1();
    }
    public void fillBodies1(){
        //actual voter turnout
        Bodies[0] = new VPBody("A", "18-24", 3089369);
        Bodies[1] = new VPBody("B", "25-34", 4816152);
        Bodies[2] = new VPBody("C", "35-44", 5346304);
        Bodies[3] = new VPBody("D", "45-54", 6383259);
        Bodies[4] = new VPBody("E", "55-64", 5776684);
        Bodies[5] = new VPBody("F", "65-74", 5061108);
        Bodies[6] = new VPBody("G", "75+", 3514764);
    }
    public void fillBodies2(){
        //theoretical voter turnout
        Bodies[0] = new VPBody("A", "18-24", 4663199);
        Bodies[1] = new VPBody("B", "25-34", 7135040);
        Bodies[2] = new VPBody("C", "35-44", 5346304);
        Bodies[3] = new VPBody("D", "45-54", 6383259);
        Bodies[4] = new VPBody("E", "55-64", 5776684);
        Bodies[5] = new VPBody("F", "65-74", 5061108);
        Bodies[6] = new VPBody("G", "75+", 3514764);
    }
    public void checkCoalition(String a){
        VPBody[] coalition = new VPBody[a.length()];
        int counter = 0;
        for(int i = 0; i < Bodies.length;i++){
            String curName = Bodies[i].getName();
            for(int g = 0; g < a.length(); g++){
                if(curName.charAt(0) == a.charAt(g)){
                    coalition[counter] = Bodies[i];
                    counter++;
                }
            }
        }
        int sum = 0;
        for(int i = 0; i < coalition.length; i++){
            int curVotes = coalition[i].getNumVotes();
            sum += curVotes;
        }
        if(sum >= votesNeeded){
            WinningCoalitions4.add(a);
        }
    }
    public ArrayList<String> CoalitionWithChar(char a){
        ArrayList<String> Coalitions = new ArrayList<String>();
        for(int i = 0; i < WinningCoalitions4.size(); i++){
            String curCo = WinningCoalitions4.get(i);
            for(int t = 0; t < curCo.length(); t++){
                if(curCo.charAt(t) == a){
                    Coalitions.add(WinningCoalitions4.get(i));
                }
            }
        }
        return Coalitions;
    }
    /* arr[] ---> Input Array
	data[] ---> Temporary array to store current combination
	start & end ---> Staring and Ending indexes in arr[]
	index ---> Current index in data[]
	r ---> Size of a combination to be printed */
    static void combinationUtil(VPBody arr[], String data[], int n,
                                int i, int x,int r)
    {
        // Current combination is ready to be printed, print it
        if (x == r)
        {
            String a = "";
            for (int j=0; j<r; j++) {
                a += data[j];
                //prints combinations
                //System.out.print(data[j] + " ");
            }
            Coalitions4.add(a);
            //System.out.println("");
            return;
        }
        if (x > r)return;

        // replace index with all possible elements. The condition
        // "end-i+1 >= r-index" makes sure that including one element
        // at index will make a combination with remaining elements
        // at remaining positions
        for (int j=i; j<n ; j++)
        {
            data[x] = arr[j].getName();
            combinationUtil(arr, data, n, j+1,x+1,r);
        }
    }

    // The main function that prints all combinations of size r
    // in arr[] of size n. This function mainly uses combinationUtil()
    static void printCombination(VPBody arr[], int n, int r)
    {
        // A temporary array to store all combination one by one
        String data[] = new String[r];


        // Print all combination using temporary array 'data[]'
        combinationUtil(arr, data, n, 0, 0,r);
    }
    public VPBody[] getBodies() {
        return Bodies;
    }
    public void printArrayList(ArrayList<String> s) {
        for(int i = 0; i < s.size(); i++) {
            System.out.print(s.get(i));
            System.out.println(" ");
        }
        System.out.println("Number of Coalitions: " + s.size());
    }
    public static void main(String[] args){
        Calculator calc = new Calculator();
        VPBody[] Bods = calc.getBodies();
        //char[] test = {'A','B','C','D','E','F','G'};
        calc.printCombination(Bods, Bods.length, 6);
        for(int i = 0; i < Coalitions4.size(); i++){
            calc.checkCoalition(Coalitions4.get(i));
        }
        ArrayList<String> CoalitionWChar = new ArrayList<String>();
        calc.printArrayList(WinningCoalitions4);
        System.out.println("Total Coalitions");
        char c = 'A';
        for(int i = 0; i < 7; i++){
            CoalitionWChar = calc.CoalitionWithChar(c);
            calc.printArrayList(CoalitionWChar);
            System.out.println("Coalitions with " + c);
            c += 1;
        }
    }
}

