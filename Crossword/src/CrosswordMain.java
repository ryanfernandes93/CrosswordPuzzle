import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class CrosswordMain {

	public static void main(String[] args) throws IOException 
	{
		// TODO Auto-generated method stub
		BufferedReader stream=new BufferedReader(new InputStreamReader(System.in));
		FillinPuzzle fp=new FillinPuzzle();
		System.out.println("load puzzle "+fp.loadPuzzle(stream));
		//fp.print();
		System.out.println("solve puzzle "+fp.solve());
		PrintWriter outstream=new PrintWriter(System.out);
		fp.print(outstream);
		System.out.println("number of choices "+fp.choices());
	}

}
