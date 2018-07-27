/**
**Author: James Paolo W. Menguito
**Description: 2D array of randomly generated strings
**/
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Optional;
public class Table{

	private String[][] stringTable;
	private int row;
	private int col;
	
	//constructor of Table object
	public Table (int row, int col){
		this.stringTable = buildTable(row,col);
		this.row = row;
		this.col = col;
	 }	    
    public static void main (String[] args){
       
       //Accepts two integers upon executing code
       int rows = Integer.parseInt(args[0]);
       int cols = Integer.parseInt(args[1]);
       
       
       //build table
       Table table = new Table(rows,cols);
       
       //print rows and columns
       System.out.println("Rows: "+rows);
       System.out.println("Cols: "+cols);
 
       int choice = 3;
       
       do {	
       		//Take an input
       		Scanner reader = new Scanner(System.in);
       		printMenu();
       		System.out.print("Enter choice: ");
       		
       		//switch statement 	
       		try {
       			choice = reader.nextInt();
       			
       			switch(choice){
       				case 0: 
       					break;
       				case 1: table.search();
       					break;
       				case 2: table.edit();
       					break;
       				case 3: table.print();
       					break;
       				case 4: table = table.reset();
       					break;
       				case 5: System.exit(0);
       				default: 
       					break;
       			}
       						
       		} catch(InputMismatchException e) {
       			
       			//Upon entering an invalid input (non-integer), print error
       			System.out.println("Error: Invalid input.");
       			
       		}
       		
       }while(choice != 5);       
       
        
        /**
        *** [1]Search
        *** [2]Edit
        *** [3]Print
        *** [4]Reset
        *** [5]Exit
        **/
    }
    
    //method to print menu
    public static void printMenu(){
    	System.out.println("");
    	System.out.println("===Menu==");
    	System.out.println("[1]Search");
		System.out.println("[2]Edit");
		System.out.println("[3]Print");
		System.out.println("[4]Reset");
		System.out.println("[5]Exit");    
    }
    
    //initialize the table   
 	public String[][] buildTable(int row, int col){
 		
 		String[][] stringTable = new String[row][col];
 		StringBuilder sb = new StringBuilder();
 		String word;
 		
 		//randomizer
 		Random random = new Random();
 		
 		for(int i=0;i<row;i++){
 			
 			for(int j=0;j<col;j++){
 				
 				
 				for(int k=0;k<3;k++){
 				
 					//generate a random ascii value from 33 to 126;
 					char randomChar = (char) (random.nextInt(93) + 33);
 					sb.append(randomChar);		//append character j times
 				}
 				
 				//convert the stringbuilder to a string and store it in an array 
 				word = sb.toString(); 
 				stringTable[i][j] = word;
 				
 				//make a new stringbuilder
 				sb = new StringBuilder();	
 			}
 		}
 		return stringTable;
 	}
 	
 	//method that searches the table for a provided term   
    public void search(){
    	Scanner reader = new Scanner(System.in);
    	String [][] stringTable = getTable();
    	ArrayList<String> indices = new ArrayList<String>();
		int row = getRow();
		int col = getCol();
		int count=0;
		
		System.out.print("Search for: ");
		String term = reader.nextLine();		//term to search for
    	term = getOptional(term);
    	//if-statements for incorrect input
    	if(term.length() > 3){
    		System.out.println("Search term exceeds character limit.");
    		return;
    	}else if((term.trim()).isEmpty()){
    		System.out.println("Search term is empty");
    		return;
    	}else if((term.contains(" "))){
    		System.out.println("Search term cannot contain spaces");
    		return;
    	}
    	
    	//search the table for the string provided
    	for(int i=0;i<row;i++){
    		for(int j=0;j<col;j++){
    			//search for the term in each index;
    			if(stringTable[i][j].contains(term.trim())){
    				count++;						//increase the counter
    				indices.add("["+i+","+j+"]");	//add to the list of indices where the substring was found;
    			}
    		}
    	}
    	
    	//after counting, print count and the indices list
    	System.out.println("Matches: "+count);
    	System.out.println("At indices: " + indices.toString());
    
    }
    
    //method that lets the user edit a designated cell in the table by providing the row and column
    public void edit(){
    	Scanner read = new Scanner(System.in);
    	System.out.print("Edit row: ");
    	int row = read.nextInt();
    	
    	System.out.print("Edit col: ");
    	int col = read.nextInt();
    	
    	//check the provided row and column if it exceeds the table's dimension
    	if(row > getRow() || col > getCol()){
    		System.out.println("Array out of bounds");
    		return;
    	}
    	
    	//enter a new string to replace the selected table cell
    	System.out.print("Enter a new string (max length of 3): ");
    	String word = read.next();
    	word = getOptional(word);
    	//checks the input if it's correct
    	if(word.length() > 3){
    		System.out.println("New string exceeds character limit.");
    		return;
    	}else if((word.trim()).isEmpty()){
    		System.out.println("New string is empty");
    		return;
    	}else if((word.contains(" "))){
    		System.out.println("New string cannot contain spaces");
    		return;
    	}
    	
    	//place the new string in the selected cell
    	this.stringTable[row][col] = word;
    	
    }
 	
 	public String getOptional(String word) {
    	
    	Optional<String> optionalWord = Optional.ofNullable(word);
		word = optionalWord.orElse("");
		
		return word; 
     }
 	
 	//prints the whole table   
    public void print(){
    	System.out.println("");
    	System.out.println("");
    	for(int i=0;i<getRow();i++){
    		for(int j=0;j<getCol();j++){
    			System.out.print(this.stringTable[i][j]+" ");
    		}
    		System.out.println("");
    	}
    	System.out.println("");
    	System.out.println("");
    }
 	
 	//lets the user reset the table, requests a new dimension and generates new strings
    public Table reset() {
 		Scanner reader = new Scanner(System.in);
 		System.out.print("Enter desired number of rows: ");
 		int row = reader.nextInt();
 		System.out.print("Enter desired number of cols: ");
 		int col = reader.nextInt();
 		return new Table(row,col);
     } 
    public void exit(){
    	System.exit(0);
     }
    
    //returns the row 
    public int getRow(){
    	return this.row;
     }
    //returns the column
    public int getCol(){
    	return this.col;
     }
    //returns the 2d String array
	public String[][] getTable(){
		return this.stringTable;
	 }
	
	//added
	public void setRow(int row) { 
		this.row = row;
	}
	
	public void setCol(int col) { 
		this.col = col;
	}
	
	//method overload for edit()
	public boolean edit(int row, int col, String word){
    	//check the provided row and column if it exceeds the table's dimension
    	if(row >= getRow() || col >= getCol() || row < 0|| col < 0){
    		System.out.println("Array out of bounds");
    		return false;
    	}
    	word = getOptional(word);
    	if(word.length() > 3){
    		System.out.println("New string exceeds character limit.");
    		return false;
    	}else if((word.trim()).isEmpty()){
    		System.out.println("New string is empty");
    		return false;
    	}else if((word.contains(" "))){
    		System.out.println("New string cannot contain spaces");
    		return false;
    	}
    	
    	//place the new string in the selected cell
    	this.stringTable[row][col] = word;
    	return true;
    }
    
    //lets the user reset the table, requests a new dimension and generates new strings
    public Table reset(int row, int col) {
 		return new Table(row,col);
     }
    
     public boolean search(String word) {
    	
    	String [][] stringTable = getTable();
    	ArrayList<String> indices = new ArrayList<String>();
		int row = getRow();
		int col = getCol();
		int count=0;
		word = getOptional(word);	
    	
    	//if-statements for incorrect input
    	if(word.length() > 3){
    		System.out.println("Search term exceeds character limit.");
    		return false;
    	}else if((word.trim()).isEmpty()){
    		System.out.println("Search term is empty");
    		return false;
    	}else if((word.contains(" "))){
    		System.out.println("Search term cannot contain spaces");
    		return false;
    	}
    	//search the table for the string provided
    	for(int i=0;i<row;i++){
    		for(int j=0;j<col;j++){
    			//search for the term in each index;
    			if(stringTable[i][j].contains(word.trim())){
    				count++;						//increase the counter
    				indices.add("["+i+","+j+"]");	//add to the list of indices where the substring was found;
    			}
    		}
    	}
    	
    	//after counting, print count and the indices list
    	System.out.println("Matches: "+count);
    	System.out.println("At indices: " + indices.toString());
		return true;
    }
    
   
}
