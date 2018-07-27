import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

public class TestTableService {

	private Table table; 
	private TableService service = new TableService();
	@Before
	public void setUp() {
		table = createTable();
	 }

	@Test
	public void testFirst() {
		service.print(table);
	 }


 }
