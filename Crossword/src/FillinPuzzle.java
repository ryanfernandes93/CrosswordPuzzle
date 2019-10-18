import java.io.BufferedReader;
import java.io.IOException;
public class FillinPuzzle 
{
	
	String [][] puzzleGrid;
	String [] size;
	String [] words;
	int counter;
	//read the input array
	public boolean loadPuzzle(BufferedReader stream) throws IOException
	{
		BufferedReader br=stream;
		size=br.readLine().split(" "); //stored 654
		//initialize grid based on positions input
		puzzleGrid=new String[Integer.valueOf(size[1])][Integer.valueOf(size[0])];
		//initialize the words n the array
		words=new String[Integer.valueOf(size[2])];
		//read input values
		for(int i=0;i<Integer.valueOf(size[2]);i++)
		{
			String [] crossword=br.readLine().split(" ");
			if(crossword[3].equals("h"))
			{
				int counter=Integer.valueOf(crossword[2]);
				int increment=Integer.valueOf(crossword[0]);
				while(counter!=0)
				{
					puzzleGrid[Integer.valueOf(crossword[1])][increment]="0";
					increment++;
					counter--;
				}
			}
			else
			{
				int counter=Integer.valueOf(crossword[2]);
				int increment=Integer.valueOf(crossword[1]);
				while(counter!=0)
				{
					puzzleGrid[increment][Integer.valueOf(crossword[0])]="0";
					increment--;
					counter--;
				}
			}			
		}
		
		//accept words input
		for (int i=0;i<Integer.valueOf(size[2]);i++)
		{
			words[i]=stream.readLine();
		}
		
		return false;
	}
	
//print the nodes
	public void print()
	{
		for (int i=Integer.valueOf(size[1])-1;i>=0;i--)
		{
			
			for (int j=0;j<Integer.valueOf(size[0]);j++)
			{
				if (puzzleGrid[i][j]==null)
				{
					System.out.print(" ");
				}
				else
				{
					System.out.print(puzzleGrid[i][j]);
				}
			}
			System.out.println();
		}
	}
	
	public boolean solve()
	{
		//solveRecursive(words,puzzleGrid,0);
		String [][] puzzleGridClone =puzzleGrid.clone();
		
		return true;
	}
		
	
	
	
	

}
