import java.awt.EventQueue;

public class MyMain {
	public static void main(String[] args) {
		Runnable r = new Runnable() {
			public void run() {
				new Sudoku();
			}
		};
		
		EventQueue.invokeLater(r);
	}
}
