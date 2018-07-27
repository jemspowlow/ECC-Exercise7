/***
***Author: James Paolo W. Menguito
***Description: A test class that test TableService class
				Methods tested are: edit,search,addRow,reset
				Overload methods were created to simulate user input
				Test cases include correct input, incorrect input, null input				
***/
package services;
import models.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class TestTableService {

	private Table table; 
	private TableService service = new TableService();
	private int row;
	private int col;
	private int index;
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setUp() {
	 	try{
			table = service.createTable2();
	 	}catch(IOException e) {
	 	
	 		e.printStackTrace();
	 	}
	 	row = 0;
	 	col = 0;
	 	index=0;
	 }
	
	@After
	public void after() {
		row = 0;
	 	col = 0;
	 	index=0;
	 }
	
	//CONTRUCTOR TEST
	@Test
	public void testServiceConstructor() {
		assertNotNull(service);
	 }
	 @Test
	public void testTableConstructor() {
		assertNotNull(table);
	
	 }
	
	//RESET TEST
	 
	 @Test
	public void testReset1() {										//test if row is changed
		row = table.getRow();		
		service.reset(table,3,3);
		assertNotEquals(row,table.getRow());
	 }
	
	 @Test
	public void testReset2() {										//test if col is changed
		col = table.getCol();		
		service.reset(table,3,3);
		assertNotEquals(col,table.getCol());
	 }
	 @Test
	public void testReset3() {
		assertFalse(service.reset(null,3,3));
	 }	
	
	
	//EDIT TEST
	 @Test
	public void testEditCorrectInput1() {							//Tests replacing the Key at 0 0
		row = 0;
		col = 0;
		index = row * table.getCol() + col;
		service.edit(table,row,col,1,"AAA","");
		assertEquals("AAA",table.getTable().get(index).getKey());
     }
    
     @Test
    public void testEditCorrectInput2() {							//Tests replacing the Value at 0 0
		row = 0;
		col = 0;
		index = row * table.getCol() + col;
		service.edit(table,row,col,2,"BBB","");
		assertEquals("BBB",table.getTable().get(index).getVal());
     }
     @Test
    public void testEditCorrectInput3() {							//Tests replacing the Key and value at 0 0
		row = 0;
		col = 0;
		index = row * table.getCol() + col;
		service.edit(table,row,col,3,"AAA","BBB");
		assertEquals("AAABBB",table.getTable().get(index).getWord());
     }
     @Test
	public void testEditCorrectInput4() {							//Tests replacing the Key at 3 7
		row = 3;
		col = 4;
		index = row * table.getCol() + col;
		service.edit(table,row,col,1,"AAA","");
		assertEquals("AAA",table.getTable().get(index).getKey());
     }
    
    @Test
    public void testEditCorrectInput5() {
    	row = 6;
		col = 4;
		index = row * table.getCol() + col;							//Tests replacing the Value at 3 7
		service.edit(table,row,col,2,"BBB","");
		assertEquals("BBB",table.getTable().get(index).getVal());
     }
     @Test
    public void testEditCorrectInput6() {						//Tests replacing the Key and value at 3 7
		row = 6;
		col = 4;
		index = row * table.getCol() + col;
		service.edit(table,row,col,3,"AAA","BBB");
		assertEquals("AAABBB",table.getTable().get(index).getWord());
     }
    @Test
	public void testEditCorrectInput7() {							//Tests replacing the Key at 7 5 (EXTREMES)
		row = 6;
		col = 4;
		index = row * table.getCol() + col;
		service.edit(table,row,col,1,"AAA","");
		assertEquals("AAA",table.getTable().get(index).getKey());
     }
    
    @Test
    public void testEditCorrectInput8() {							//Tests replacing the Value at 7 5 (EXTREMES)
		row = 7;
		col = 5;
		index = row * table.getCol() + col;
		service.edit(table,row,col,2,"BBB","");
		assertEquals("BBB",table.getTable().get(index).getVal());
     }
     @Test
    public void testEditCorrectInput9() {							//Tests replacing the Key and value at 7 5 (EXTREMES)
		row = 7;
		col = 5;
		index = row * table.getCol() + col;
		service.edit(table,row,col,3,"AAA","BBB");
		assertEquals("AAABBB",table.getTable().get(index).getWord());
     }
     
     @Test
	public void testEditCorrectInput10() {							//Testing input with numbers in key
		row = 3;
		col = 4;
		index = row * table.getCol() + col;
		service.edit(table,row,col,1,"123","");
		assertEquals("123",table.getTable().get(index).getKey());
     }
    
    @Test
    public void testEditCorrectInput11() {							//Testing input with numbers in value
		row = 3;
		col = 4;
		index = row * table.getCol() + col;
		service.edit(table,row,col,2,"456","");
		assertEquals("456",table.getTable().get(index).getVal());
     }
    @Test
    public void testEditCorrectInput12() {							//Tests replacing the with numbers in Key and value
		row = 3;
		col = 4;
		index = row * table.getCol() + col;
		service.edit(table,row,col,3,"123","456");
		assertEquals("123456",table.getTable().get(index).getWord());
     }
     
     @Test
    public void testEditCorrectInput13() {							//Symbols testing - key
		row = 2;
		col = 0;
		index = row * table.getCol() + col;
		service.edit(table,row,col,1,"%\\,","");
		assertEquals("%\\,",table.getTable().get(index).getKey());
     }
     @Test
    public void testEditCorrectInput14() {							//Symbols testing - value
		row = 2;
		col = 0;
		index = row * table.getCol() + col;
		service.edit(table,row,col,2,".'!","");
		assertEquals(".'!",table.getTable().get(index).getVal());
     }
     @Test
    public void testEditCorrectInput15() {							//Symbols testing - key and value
		row = 0;
		col = 0;
		index = row * table.getCol() + col;
		service.edit(table,row,col,3,"$@#","<>/");
		assertEquals("$@#<>/",table.getTable().get(index).getWord());
     }
     
     //EDIT TEST INCORRECT INPUT
   	 @Test
   	public void testEditIncorrectInput1() {
   		row = 0;
   		col = 0;
   		index = row * table.getCol() + col;							//empty input test
   	 	
		assertTrue(service.edit(table,row,col,1,"",""));
   	 }
   	 @Test
   	public void testEditIncorrectInput2() {							//empty input test
   		row = 0;
   		col = 0;
   		index = row * table.getCol() + col;
   	 	
		assertTrue(service.edit(table,row,col,2,"",""));
   	 }
   	 @Test
   	public void testEditIncorrectInput3() {							//space test
   		row = 0;
   		col = 0;
   		index = row * table.getCol() + col;
   	 	
		assertTrue(service.edit(table,row,col,1,"b a",""));
   	 }
   	 @Test
   	public void testEditIncorrectInput4() {							//space test
   		row = 0;
   		col = 0;
   		index = row * table.getCol() + col;
   	 	
		assertTrue(service.edit(table,row,col,2,"b a",""));
   	 } 
   	 @Test
   	public void testEditIncorrectInput5() {
   		row = 0;
   		col = 0;
   		index = row * table.getCol() + col;
   	 	
		assertTrue(service.edit(table,row,col,1,"ba ",""));			//2 characters and space test for key
   	 }
   	 @Test
   	public void testEditIncorrectInput6() {
   		row = 0;
   		col = 0;
   		index = row * table.getCol() + col;
   	 	
		assertTrue(service.edit(table,row,col,2,"ba ",""));        //2 characters and space test
   	 }
     @Test
   	public void testEditIncorrectInput7() {
   		row = 0;
   		col = 0;
   		index = row * table.getCol() + col;
   	 	
		assertTrue(service.edit(table,row,col,1,"asdasdasd",""));   //greater than 3 chars test
   	 }
   	 @Test
   	public void testEditIncorrectInput8() {
   		row = 0;
   		col = 0;
   		index = row * table.getCol() + col;
   	 	
		assertTrue(service.edit(table,row,col,2,"asdasdasd",""));	//greater than 3 chars test
   	 }
   	 @Test
   	public void testEditIncorrectInput9() {
   		row = 0;
   		col = 0;
   		index = row * table.getCol() + col;
   	 	
		assertTrue(service.edit(table,row,col,3,"abc","ab "));
   	 }
   	 @Test
   	public void testEditIncorrectInput10() {						//2 characters or space test key and value
   		row = 0;
   		col = 0;
   		index = row * table.getCol() + col;
   	 	
		assertTrue(service.edit(table,row,col,3,"ab ","abc"));
   	 }
   	 @Test
   	public void testEditIncorrectInput11() {						//greater than 3 chars test
   		row = 0;
   		col = 0;
   		index = row * table.getCol() + col;
   	 	
		assertTrue(service.edit(table,row,col,3,"abasd","asdasd"));
   	 }
   	 @Test
   	public void testEditIncorrectInput12() {						//colon test
   		row = 0;
   		col = 0;
   		index = row * table.getCol() + col;
   	 	
		assertTrue(service.edit(table,row,col,1,":za",""));
   	 }
   	 @Test
   	public void testEditIncorrectInput13() {						//colon test
   		row = 0;
   		col = 0;
   		index = row * table.getCol() + col;
   	 	
		assertTrue(service.edit(table,row,col,2,":za",""));
   	 }
   	 @Test
   	public void testEditIncorrectInput14() {						//colon (:) test
   		row = 0;
   		col = 0;
   		index = row * table.getCol() + col;
   	 	
		assertTrue(service.edit(table,row,col,3,":za","a:a"));
   	 }
   	@Test
	public void testEditIncorrectInput15() {							//null table test
		row = 0;
		col = 0;
		index = row * table.getCol() + col;
		assertFalse(service.edit(null,row,col,1,"AAA",""));

     }
     
     //Tests for handling indices out of bounds
     //Method returns false when it catches an out of bound index
     @Test
    public void testEditIndexOutOfBounds1() {						//Test negative row indices
		assertFalse(service.edit(table,-1,0,1,"AAA",""));
     }
     
     @Test
    public void testEditIndexOutOfBounds2() {						//Test negative col indices
		assertFalse(service.edit(table,0,-1,1,"AAA",""));
     }
     
     @Test
    public void testEditIndexOutOfBounds3() {						//Test negative row & col indices 
    	assertFalse(service.edit(table,-2,-2,3,"AAA","BBB"));
    }
    
     @Test
    public void testEditIndexOutOfBounds4() {						//Test >row indices
    	assertFalse(service.edit(table,10,0,1,"AAA",""));
    }
    
     @Test
    public void testEditIndexOutOfBounds5() {						//Test >col indices 
    	assertFalse(service.edit(table,0,20,2,"AAA",""));
    }
     @Test
    public void testEditIndexOutOfBounds6() {						//Test >row and >col indices 
    	assertFalse(service.edit(table,10,20,3,"AAA","BB"));
    }
    
    @Test
    public void testEditIndexOutOfBounds7() {						//Test >row and >col indices 
    	assertFalse(service.edit(table,5,5,1,null,"BBB"));
    }
    @Test
    public void testEditIndexOutOfBounds8() {						//Test >row and >col indices 
    	assertFalse(service.edit(table,5,5,1,"AAA",null));
    }
   
   	//ADD ROW - TEST
  	
  	 @Test
  	public void testAddRow1() {										//normal test
  	 	String tableRow = "abc def ghi jkl mno pqr stu vwx yzA BCD EFG HIJ";
  	 	assertTrue(service.addRow(table,tableRow));
  	 }   
    
     @Test
    public void testAddRow2() {										//null test
        String tableRow = "abc def ghi jkl mno pqr stu vwx yzA BCD EFG HIJ";
  		assertFalse(service.addRow(null,tableRow));  	
     }
     @Test
    public void testAddRow3() {
    	String tableRow = null;										//null test
    	assertFalse(service.addRow(table,tableRow));
    }
     @Test
    public void testAddRow4() {										//wrong input test. program requests input again
    	String tableRow = "abc def g:i jkl ghi jkl mno pqr stu vwx yzA BCD EFG HIJ";
    	assertTrue(service.addRow(table,tableRow));
     }
  	
  	//SEARCH TEST CORRECT INPUT
  	
  	 @Test
  	public void testSearchCorrectInput1() {
  		String word = "shi";					//letters
  		assertTrue(service.search(table,word));
  	}
  	
  	 @Test
  	public void testSearchCorrectInput2() {
  		String word = "1ai";					//numbers
  		assertTrue(service.search(table,word));
  	 }
  	 @Test
  	public void testSearchCorrectInput3() {
  		String word = "!<>";					//symbols
  		assertTrue(service.search(table,word));
  	 }
  	 @Test
  	public void testSearchCorrectInput4() {
  		String word = "shitak";					//6 chars
  		assertTrue(service.search(table,word));
  	 }
  	 
  	 //SEARCH TEST INCORRECT INPUT
  	 @Test
  	public void testSearchIncorrectInput1() {
  		String word = "!<>asdertqwe";			//above 6 chars
  		assertFalse(service.search(table,word));
  	 }
  	 @Test
  	public void testSearchIncorrectInput2() {
  		String word = "";						// empty string
  		assertFalse(service.search(table,word));
  	 }
  	 @Test
  	public void testSearchIncorrectInput3() {
  		String word = "a bsaq";					//with spaces
  		assertFalse(service.search(table,word));
  	 }
  	 
  	 @Test
  	public void testSearchIncorrectInput4() {
  		String word = "asaq";					//null
  		assertFalse(service.search(null,word));
  	 }
  	 @Test
  	public void testSearchIncorrectInput5() {
  		String word = null;						//null
  		assertFalse(service.search(table,word));
  	 }
  		
 }
