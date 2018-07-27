import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

public class TestTable {
    private Table table;
    private String[][] tableArray;
    
    @Before
    public void setUpTable() {
		table = new Table(10,15);   
	    tableArray = table.getTable().clone(); 	
    	
     }
  	@After
  	public void emptyTable() {
  		table = null;
  		tableArray = null;
  	 }
    @Test 
    public void testTableConstructor() {
        int row = table.getRow();
        int col = table.getCol();
        //Test dimensions
        assertEquals(10, row);
    	assertEquals(15, col);
    	assertNotEquals(3.5,row);
    	assertNotEquals(2.5,col);
    	assertFalse(9 == row);
    	assertFalse(14 == col);
    
    }
    
    // table edit correct input
    @Test
    public void testEditCorrectInput1() {
    	String word = tableArray[4][9];
    	assertEquals(word,(table.getTable())[4][9]); 	//equate the cloned table to the main table to check if equal 	
    	assertTrue(table.edit(4,9,"aBc"));				//basic test edit. Edit the selected coordinate
    	assertNotEquals(word,(table.getTable())[4][9]); //check if edit side-effect was implemented
     }
    
    @Test
    public void testEditCorrectInput2() {
    	String word = tableArray[9][14];
    	assertEquals(word,(table.getTable())[9][14]); 	//equate the cloned table to the main table to check if equal 	
    	assertTrue(table.edit(9,14,"aBc")); 			//extremes test. Edit the selected coordinate
    	assertNotEquals(word,(table.getTable())[9][14]);//check if edit side-effect was implemented
     }
     
    @Test
    public void testEditCorrectInput3() {
    	String word = tableArray[7][6];
    	assertEquals(word,(table.getTable())[7][6]);	//check first if equal
    	assertTrue(table.edit(7,6,"!3A"));				//symbol input test	
     	assertNotEquals(word,(table.getTable())[7][6]);	//check if now unequal
     }
     
     
    @Test
    public void testEditCorrectInput4() {
    	String word = tableArray[3][11];
    	assertEquals(word,(table.getTable())[3][11]); 	//equate the cloned table to the main table to check if equal 	
    	assertTrue(table.edit(3,11,"4b%"));  			//number input test
    	assertNotEquals(word,(table.getTable())[3][11]); //check if edit side-effect was implemented
     }
    
    
    // 	table .edit() wrong input testing
    @Test
    public void testEditWrongInput1() {
    	assertFalse(table.edit(7,3,""));		//empty string
    	
     }
     
    @Test
    public void testEditWrongInput2() {
    	assertFalse(table.edit(2,12,"e w"));	//string with spaces
    
     }
     
    @Test
    public void testEditWrongInput3() {			//string with more than 3 characters
    	assertFalse(table.edit(4,1,"sadqwesxzc"));
     }
    
    @Test
    public void testEditWrongInput4() {
    	assertFalse(table.edit(2,12,null));		//null values for edit
     }
 	
 	// table .edit() accessing out of bound indices
    @Test
    public void testEditIndexOutOfBounds1() {
    	assertFalse(table.edit(10,9,"wer"));	//Index out of bounds, row
     }
     
    @Test
    public void testEditIndexOutOfBounds2() {
    	assertFalse(table.edit(4,15,"ave"));	//index out of bounds, column
    
     }
    
    // table .search() correct input testing
    @Test
    public void testSearchCorrectInput1(){
    	assertTrue(table.search("abc"));		//letters
    }
    
    @Test
    public void testSearchCorrectInput2(){
    	assertTrue(table.search("1"));			//numbers
    }	
	    
    @Test
    public void testSearchCorrectInput3(){
    	assertTrue(table.search("#"));			//symbols
    }
    	
		    
    // table search() wrong input testing
    @Test
    public void testSearchWrongInput1() {
    	assertFalse(table.search(""));			//empty string
     }
    @Test
    public void testSearchWrongInput2() {
    	assertFalse(table.search("a b"));		//string with spaces
     }
    @Test
    public void testSearchWrongInput3() {
    	assertFalse(table.search("jeoiq24"));	//string more than 3 characters
     }
    @Test
    public void testSearchWrongInput4() {
    	assertFalse(table.search(null));		//null value
     }
    
   
    @Test
    public void testReset() {
  		int row = Integer.valueOf(table.getRow());
  		int col = Integer.valueOf(table.getCol());
  		
  		table = table.reset(3,5);
  		assertNotEquals(row,table.getRow());
  		assertNotEquals(col,table.getCol());  	
    
    }
}
