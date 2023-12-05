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
        public List<Integer> winner;
        public Card(){
            having = new ArrayList<>();
            winning = new ArrayList<>();
            winner = new ArrayList<>();
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
                        do{
                            o++;
                        }while(data.charAt(o) == ' ');
                        o--;
                        repl+=' ';
                    }else{
                        repl += data.charAt(o);
                    }
                }
                arr.add(repl.split(" "));
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
                if(arr.get(i)[j].equals("|")){
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
        int sum = 0;
        List<List<Integer>> winningNums = new ArrayList<>();

        for(int i=0; i<=cards.size()-1; i++){
            winningNums.add(new ArrayList<>());

            for(int j=0; j<=cards.get(i).winning.size()-1; j++){
                for(int k=0; k<=cards.get(i).having.size()-1; k++){
                    if(cards.get(i).winning.get(j) == cards.get(i).having.get(k)){
                        winningNums.get(i).add(cards.get(i).having.get(k));
                    }
                }
            }
        }

        for(int f = 0; f<=winningNums.size()-1; f++){
            int points = 0;
            for(int j=0; j<=winningNums.get(f).size()-1; j++){
                if(j==0){
                    points += 1;
                }else{
                    points += (points * 2);
                }
            }
            sum += points;
        }
        
        System.err.println("RESULT: " + sum);
    }
}
