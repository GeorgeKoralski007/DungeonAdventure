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
		heroRow = getRandom(0, DUNGEON_SIZE-1);
		heroCol = getRandom(0, DUNGEON_SIZE-1);
		this.rooms[heroRow][heroCol].setEntrance();
		
		// while loop to assign random exit
		int exitRow;
		int exitCol;
		do {
			exitRow = getRandom(0, DUNGEON_SIZE-1);
			exitCol = getRandom(0, DUNGEON_SIZE-1);
		} while (rooms[exitRow][exitCol].isEntrance());
		this.rooms[exitRow][exitCol].setExit();
		
		// while loop to assign 4 pillars of OO
		addPillar("Abstraction");
		addPillar("Polymorphism");
		addPillar("Encapsulation");
		addPillar("Inheritance");
		
		// generate monsters in rooms
		for (int i=0; i<DUNGEON_SIZE; i++) {
			addMonster();
		}
	}
	
	// Add a Monster to a randomly generated room that is not entrance or exit
	private void addMonster() {
		int row;
		int col;
		do {
			row = getRandom(0, DUNGEON_SIZE-1);
			col = getRandom(0, DUNGEON_SIZE-1);
		} while (rooms[row][col].isEntrance() || rooms[row][col].isExit() || rooms[row][col].hasMonster());
		Monster monster = DungeonCharacterFactory.createMonster(getRandom(1,6));
		this.rooms[row][col].setMonster(monster);
	}

	// Add pillar of OO to a random room that is not entry or exit
	private void addPillar(String pillar) {
		int row;
		int col;
		do {
			row = getRandom(0, DUNGEON_SIZE-1);
			col = getRandom(0, DUNGEON_SIZE-1);
		} while (rooms[row][col].isEntrance() || rooms[row][col].isExit() || rooms[row][col].hasPillarOfOO());
		this.rooms[row][col].setPillarOfOO(pillar);
	}
	
	// private 

	// Get Room where Hero is currently located
	// Use the Room interface to get its content
	public Room getHeroRoom() {
		return this.rooms[heroRow][heroCol];
	}
	
	// Helper method to generate random integer between min and max inclusive
	private int getRandom(int min, int max) {
		return (int)(Math.random() * (max-min+1)) + min;
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
	
	// Print only the room where the hero is
	public void printHeroRoom()
	{
		System.out.println(this.rooms[heroRow][heroCol]);
	}
	
	// print the hero room and the surrounding 8 rooms if any
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
	protected Room[][] roomGetter()
	{
		return rooms;
	}
}