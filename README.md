Overview
--------

This program is used to solve a crossword puzzle. That solution
is printed to the screen.  A summary of the formatting requirements
are in the csci 3901 course assignment #4 information in the course's
brightspace space.

Files and external data
-----------------------

There are two main files:
  - CrosswordMain.java  -- main for the program that prompts the user to provide operation to be performed
  - FillinPuzzle.java -- class that is responsible for performing operations on the puzzle grid
  - Placeholder.java -- class that maintains information of array positions to be added to stack

The directory also contains past versions of the code, to demonstrate that code isn't developed all at once:

v1 -- Accept input and create the puzzle grid along with crossword words
directory version -- Updated code to work on bluenose.cs.dal.ca

Data structures & methods:
---------------------------
-The crossword grid is stored in an array of type character
-Maintain the input crossword words in a list where index corresponds with length 
-For a given length maintain all the words with the same length at the same index

Method:
------
  loadPuzzle -- load/create the puzzle

The method accepts the size of the crossword puzzle,number of words in the crossword puzzle,positions where words are expected in the crossword
based upon above input provided the crossword puzzle grid is created

  solve -- solve the puzzlegrid recursively

The method uses a stack to implement backtracking recursively to solve the puzzle

  print -- print the solved puzzle grid 

This method is used to print out the solved puzzle using a printwriter object


Data Structures
 puzzleGrid -- used to store the crossword puzzle
 Stack placeholders -- used to store the position where the words will fit in the grid
 List Set -- used to maintain all the crossword words in a single set

Assumptions
-----------

  - All horizontal words will be added from left to right.
  - All vertical words will be filled from top to bottom.
  - No word is repeated in the crossword
  - The puzzle words are case insensitive
  
Choices
-------

  - User first selects to load the puzzle.
  - User selects to solve the puzzle.
  - User selects to print the puzzle.

Key algorithms and design elements
----------------------------------

Accept the first line of input of the form "column row numberOfWords"
Store each of these elements in integers
Declare an array of size [row][column] and initialize each element with ' '<space>

On the basis of numberOfWords accept the places where crossword words will fit
Accept the crossword word positions of the form "0 0 5  h" where 0,0 is starting position 5 is the length and h is horizontal position and v is vertical position
Horizontal words move left to right whereas vertical words move top to bottom
In each of these positions ,insert '0' at each of the position where a crossword alphabet fits in

Accept the crossword words and store them in a arraylist of type set and data type string
Store the words in the arraylist basis of their length where the lengtth of the word corresponds with the arraylist index
Hence all words of length 5 will be stored in  the same set at array list index 5

Iterate over the puzzlegrid and identify the horizontal & vertical positions along with the length of the word and place these in a object
Push these objects into a stack for recursive processing

Pop the first element of the Stack and check the length of the space
Pick a word of the same length and add it to the position
While adding at position check if the position has 0's inserted if yes add the word to the position
if there are <spaces> in the cells it means that the word does not fit the positions
Maintain a counter to keep tab of how many alphabets of the word have been added
If there is a conflict between the words undo the alphabets added to the crossword using the counter array and push the placeholder object back to the stack
Decrement the counter object along the way.
Replace the alphabets at the position back to 0.
Continue this cycle recursively for each position till all positions are filled


Limitations
-----------

Max word length is 10

