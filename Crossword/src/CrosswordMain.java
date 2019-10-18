import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CrosswordMain {

	public static void main(String[] args) throws IOException 
	{
		// TODO Auto-generated method stub
		BufferedReader stream=new BufferedReader(new InputStreamReader(System.in));
		FillinPuzzle fp=new FillinPuzzle();
		fp.loadPuzzle(stream);
		fp.print();
		fp.solve();
		fp.print();
	}

}
