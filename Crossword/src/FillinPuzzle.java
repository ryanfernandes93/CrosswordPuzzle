import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class FillinPuzzle 
{	
	ArrayList<Set<String>> words;
    int[][] count;
    char[][] puzzleGrid;
    Stack<Placeholder> placeholders;
    int column;
    int row;
    int backtrackCounter=0;
	public boolean loadPuzzle(BufferedReader stream)
	{
		try
		{
		
			// TODO Auto-generated method stub
			BufferedReader in = stream;
			//Get size of array
			String line = in.readLine();
	        String[] parts = line.split(" ");
	        column = Integer.parseInt(parts[0]);
	        row = Integer.parseInt(parts[1]);
	        int wordcount = Integer.parseInt(parts[2]);        
	        puzzleGrid = new char[row+2][column+2];
	        
	        //initialize the array and fill all places with blank
	        for(int i=row+1;i>=0;i--)
	        {
	        	for(int j=0;j<=column+1;j++)
	        	{
	        		puzzleGrid[i][j]=' ';
	        	}
	        }
	        
	        //Set 0 where ever character is expected
	        //read input values
	      		for(int i=0;i<Integer.valueOf(parts[2]);i++)
	      		{
	      			String [] crossword=in.readLine().split(" ");
	      			//if input text is of horizontal co-ordinates
	      			if(crossword[3].equals("h"))
	      			{
	      				int counter=Integer.valueOf(crossword[2]);
	      				int increment=Integer.valueOf(crossword[0])+1;
	      				while(counter!=0)
	      				{
	      					puzzleGrid[Integer.valueOf(crossword[1])+1][increment]='0';
	      					increment++;
	      					counter--;
	      				}
	      			}
	      			//if input text is of vertical co rodinates
	      			else
	      			{
	      				int counter=Integer.valueOf(crossword[2]);
	      				int increment=Integer.valueOf(crossword[1])+1;
	      				while(counter!=0)
	      				{
	      					puzzleGrid[increment][Integer.valueOf(crossword[0])+1]='0';
	      					increment--;
	      					counter--;
	      				}
	      			}			
	      		}
	      		
	      		//flip the array vertically 
	      		for(int i = 0; i < (puzzleGrid.length / 2); i++) 
	    	    {
	    	        char[] temp = puzzleGrid[i];
	    	        puzzleGrid[i] = puzzleGrid[puzzleGrid.length - i - 1];
	    	        puzzleGrid[puzzleGrid.length - i - 1] = temp;
	    	    }
	        
	      		//input the words
	      		words = new ArrayList<>();
	            count = new int[row+2][column+2];
	            for (int i = 2; i <= 10; i++)
	            {
	            	words.add(new HashSet<String>());
	            }
	            
	            //add the words to the set where each word length is represented by array index
	            for (int i = 0; i < wordcount; i++) 
	            {
	                line = in.readLine();
	                words.get(line.length()).add(line);
	            }
	      		
	            //Iterate over horizontal and vertical positions
	            placeholders = new Stack<>();
	            for (int i = 1; i <= row; i++)
	                for (int j = 1; j <= column; j++)
	                    if (puzzleGrid[i][j-1] == ' ' && puzzleGrid[i][j] == '0' && puzzleGrid[i][j+1] == '0') 
	                    {
	                        int xx = j; int ll = 0;
	                        while (puzzleGrid[i][j] == '0') 
	                        {
	                            ll++;
	                            j++;
	                        }
	                        placeholders.push(new Placeholder(xx, i, ll, true));
	                    }
	                      
	            for (int j = 1; j <= column; j++)
	                for (int i = 1; i <= row; i++)
	                    if (puzzleGrid[i-1][j] == ' ' && puzzleGrid[i][j] == '0' && puzzleGrid[i+1][j] == '0') 
	                    {
	                        int yy = i; int ll = 0;
	                        while (puzzleGrid[i][j] == '0') 
	                        {
	                            ll++;
	                            i++;
	                        }
	                        placeholders.push(new Placeholder(j, yy, ll, false));
	                    }
	            return true;
			
		}
		catch(IOException e)
		{
			return false;
		}		
	}
	
	//call recursive solve method 
	public boolean solve()
	{
		puzzleGrid = solveRecursive(puzzleGrid, placeholders);  
		if(puzzleGrid!=null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
		
	//solve puzzle
	public char[][] solveRecursive (char[][] puzzleGrid, Stack<Placeholder> placeholders)
    {
        if (placeholders.isEmpty())
            return puzzleGrid;
 
        Placeholder pl = placeholders.pop();
        for (String word : words.get(pl.l)) {
            if (addToGrid(puzzleGrid, word, pl)) {
                char[][] returnGrid = solveRecursive(puzzleGrid, placeholders);
                if (returnGrid != null)
                    return returnGrid;
                removeFromGrid(puzzleGrid, pl);
            }
        }
        placeholders.push(pl);
        return null;
    }
 
	public boolean addToGrid (char[][] puzzleGrid, String word, Placeholder pl)
    {
        if (pl.h)
        {
            for (int i = pl.x; i < pl.x + pl.l; i++)
                if (puzzleGrid[pl.y][i] != '0' && puzzleGrid[pl.y][i] != word.charAt(i - pl.x))
                    return false;
            for (int i = pl.x; i < pl.x + pl.l; i++)
            {
            	puzzleGrid[pl.y][i] = word.charAt(i - pl.x);
                count[pl.y][i]++;
            }
            return true;
 
        } else {
            for (int i = pl.y; i < pl.y + pl.l; i++)
                if (puzzleGrid[i][pl.x] != '0' && puzzleGrid[i][pl.x] != word.charAt(i - pl.y))
                    return false;
            for (int i = pl.y; i < pl.y + pl.l; i++) {
            	puzzleGrid[i][pl.x] = word.charAt(i - pl.y);
                count[i][pl.x]++;
            }
            return true;
        }
    }
 
    public void removeFromGrid(char[][] puzzleGrid, Placeholder pl)
    {
        if (pl.h)
        {
            for (int i = pl.x; i < pl.x + pl.l; i++)
                if (--count[pl.y][i] == 0)
                	puzzleGrid[pl.y][i] = '0';
        }
        else
        {
            for (int i = pl.y; i < pl.y + pl.l; i++)
                if (--count[i][pl.x] == 0)
                	puzzleGrid[i][pl.x] = '0';
        }        
        backtrackCounter++;
    }
    //method showing number of backtracking decisions made
    public int choices()
    {
    	return backtrackCounter;
    }
    
  //print the final crossword
  	public void print(PrintWriter outstream)
  	{
  		PrintWriter out=outstream;  		
  		for (int i = 1; i <= row; i++) 
			{
				for (int j = 1; j <= column; j++)
				{      
					if(puzzleGrid[i][j]==' ')
					{
						out.print(" ");
					}
					else
					{
						out.print(puzzleGrid[i][j]);
					}  
					out.flush();
				}                
				out.println();
				out.flush();
			}
  	}
}