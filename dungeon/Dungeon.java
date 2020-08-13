package dungeon;

// This will contain 2d array with rooms and will print the whole dungeon
public class Dungeon
{
	private static int DUNGEON_SIZE = 5;
	private Room[][] rooms;
	private int heroRow;
	private int heroCol;
	
	Dungeon()
	{
		rooms = new Room[DUNGEON_SIZE][DUNGEON_SIZE];
		for (int row = 0; row < DUNGEON_SIZE; row++) {
			for (int col = 0; col < DUNGEON_SIZE; col++) {
				boolean hasNorthDoor = row > 0;
				boolean hasEastDoor = col < DUNGEON_SIZE-1;
				boolean hasSouthDoor = row < DUNGEON_SIZE-1;
				boolean hasWestDoor = col > 0;
				rooms[row][col] = new Room(hasNorthDoor, hasEastDoor, hasSouthDoor, hasWestDoor);
			}
		}
		
		// while loop to assign random entrance
		
		// while loop to assign random exit
		
		// while loop to assign 4 pillars of OO
		
		// generate monsters in rooms
	}
	
	// Print the whole dungeon
	// each room is represented by 3x3 characters
	// Hidden key should be used to print the whole dungeon
	public void print()
	{
		System.out.println();
		for (int row = 0; row < DUNGEON_SIZE * 3; row++) {
			for (int col = 0; col < DUNGEON_SIZE; col++) {
				System.out.print(rooms[row / 3][col].getLine(row % 3));
			}
			System.out.println();
		}
		
		System.out.println();
	}
}