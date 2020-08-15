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
		this.rooms = new Room[DUNGEON_SIZE][DUNGEON_SIZE];
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
		heroRow = 1;
		heroCol = 1;
		
		// while loop to assign random exit
		
		// while loop to assign 4 pillars of OO
		
		// generate monsters in rooms
	}
	
	// Get Room where Hero is currently located
	// Use the Room interface to get its content
	public Room getHeroRoom() {
		return this.rooms[heroRow][heroCol];
	}
	
	public boolean MoveHero(String direction) {
		boolean invalidMove = false;
		Room heroRoom = getHeroRoom();
		switch(direction.toUpperCase())
		{
			case "N": 
				if (heroRow > 0 && heroRoom.hasNorthDoor())
					heroRow--;
				else
					invalidMove  = true;
				break;
			case "E": 
				if (heroCol < DUNGEON_SIZE - 1 && heroRoom.hasEastDoor())
					heroCol++;
				else
					invalidMove  = true;
				break;
			case "S":
				if (heroRow < DUNGEON_SIZE - 1 && heroRoom.hasSouthDoor())
					heroRow++;
				else
					invalidMove  = true;
				break;
			case "W": 
				if (heroCol > 0 && heroRoom.hasWestDoor())
					heroCol--;
				else
					invalidMove  = true;
				break;	
			default:
				invalidMove = true;
		}
		
		if(invalidMove)
			System.out.println("Invalid Move");
		
		return !invalidMove;
	}
	
	// Print the whole dungeon
	// each room is represented by 3x3 characters
	// Hidden key should be used to print the whole dungeon
	public void printAllRooms()
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
	
	public void printHeroRoom()
	{
		System.out.println(this.rooms[heroRow][heroCol]);
	}
	
	public void printHeroRoomWithVisibility()
	{
		System.out.println();
		int startRow = Math.max(0, heroRow-1);
		int endRow = Math.min(DUNGEON_SIZE-1, heroRow+1);		
		int startCol = Math.max(0, heroCol-1);
		int endCol = Math.min(DUNGEON_SIZE-1, heroCol+1);	
		for (int row = startRow * 3; row < (endRow+1) *3; row++) {
			for (int col = startCol; col <= endCol; col++) {
				System.out.print(rooms[row / 3][col].getLine(row % 3));
			}
			System.out.println();
		}
		System.out.println();
	}
}