package com.example.d;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files

public class Puzzle {

    public class Card{
        public List<Integer> having;
        public List<Integer> winning;
        public Card(){
            having = new ArrayList<>();
            winning = new ArrayList<>();
        }
    }
    
    
    public static List<String[]> parse(){
        List<String[]> arr = new ArrayList<>();
        try {
            File myObj = new File("input");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String repl = "";
                //minimize spaces
                for(int o=0; o<=data.length()-1; o++){
                    if(data.charAt(o) == ' '){
                         
                    }else{
                        repl += data.charAt(o);
                    }
                }
                System.err.println(repl);
                arr.add(repl.split("/"));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return arr;
    }

    
    public void solve1(){
        List<String[]> arr = parse();
        List<Card> cards = new ArrayList<>();

        for(int i=0; i<=arr.size()-1; i++){
            boolean divide = false;
            Card c = new Card();
            for(int j=2; j<=arr.get(i).length-1; j++){
                System.err.println("lll");
                System.err.println(arr.get(i)[j]);
                if(arr.get(i)[j] == "|"){
                    divide = true;
                }else{
                    if(divide){
                        //the having
                        c.having.add(Integer.parseInt(arr.get(i)[j]));
                    }else{
                        //the winning
                        c.winning.add(Integer.parseInt(arr.get(i)[j]));
                    }
                }
            }
            cards.add(c);
        }
    }
}
