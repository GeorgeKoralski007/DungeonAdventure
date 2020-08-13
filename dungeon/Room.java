package dungeon;

// This will contain room data
public class Room
{
	private static final double CHANCE_SPAWN = 0.1;  // 10% change of spawning objects (pit, potion, vision potion)
	private static final int POTION_MIN_POINTS = 5; 
	private static final int POTION_MAX_POINTS = 15; 
	private static final int PIT_MIN_POINTS = 1; 
	private static final int PIT_MAX_POINTS = 20; 
	
	private boolean hasNorthDoor;
	private boolean hasSouthDoor;
	private boolean hasWestDoor;
	private boolean hasEastDoor;
	
	private int potionHealingPoints = 0;
	private boolean hasVisionPotion = false;
	private int pitDamagePoints = 0;
	private boolean isEntrance;
	private boolean isExit;
	private String pillarOfOO;
	private Monster monster;

	
	// Creates a room specifying the doors.
	// Other room content will be generated randomly
	public Room(boolean hasNorthDoor, boolean hasEastDoor, boolean hasSouthDoor, boolean hasWestDoor)
	{
		this.hasNorthDoor = hasNorthDoor;
		this.hasEastDoor = hasEastDoor;
		this.hasSouthDoor = hasSouthDoor;
		this.hasWestDoor = hasWestDoor;
		
		// Vision potion generation
		if (Math.random() <= CHANCE_SPAWN)
		{
			generateVisionPotion();
		}
		
		//Healing potion generation
		if (Math.random() <= CHANCE_SPAWN)
		{
			generatePotion();
		}
		
		// Pit generation
		if (Math.random() <= CHANCE_SPAWN)
		{
			generatePit();
		}
	}
	
	// Methods for entrance and exit
	public void setEntrance() {
		this.isEntrance = true;
		this.isExit = false;
		CleanObjects();
	}
	public void setExit() {
		this.isExit = true;
		this.isEntrance = false;
		CleanObjects();
	}
	public boolean isExit() { return this.isExit; }
	public boolean isEntrance() { return this.isEntrance; }
	
	// Methods for checking the doors
	public boolean hasNorthDoor() { return this.hasNorthDoor; }
	public boolean hasSouthDoor() { return this.hasSouthDoor; }
	public boolean hasWestDoor() { return this.hasWestDoor; }
	public boolean hasEastDoor() { return this.hasEastDoor; }
	
	public void generatePotion() {
		this.potionHealingPoints = (int)(Math.random() * (POTION_MAX_POINTS-POTION_MIN_POINTS+1)) + POTION_MIN_POINTS;
	}
	public boolean hasPotion() { return this.potionHealingPoints > 0; }
	public int getPotionHealingPoints() { return this.potionHealingPoints; }
	
	public void generatePit() {
		this.pitDamagePoints = (int)(Math.random() * (PIT_MAX_POINTS-PIT_MIN_POINTS+1)) + PIT_MIN_POINTS;
	}
	public boolean hasPit() { return this.pitDamagePoints > 0; }
	public int getPitDamagePoints() { return this.pitDamagePoints; }
	
	public void generateVisionPotion() {
		this.hasVisionPotion = true;
	}
	public boolean hasVisionPotion() { return this.hasVisionPotion; }

	// Remove all objects if any (potion, vision potion and pit)
	public void CleanObjects() {
		this.potionHealingPoints = 0;
		this.hasVisionPotion = false;
		this.pitDamagePoints = 0;
	}
	
	public void setMonster(Monster monster ) {
		this.monster = monster;
	}
	public boolean hasMonster() { return this.monster != null; }
	public Monster getMonster() {
		return this.monster;
	}
	
	/***********************************
	toString method builds a 2D Graphical representation of the room
	The (command line) representation is as follows (variations on a theme are ok as long as things are easy
	to read and understand):
	o * - * will represent a north/south door (the - represents the door). If the room is
	on a boundary of the maze (upper or lower), then that will be represented with
	***
	o East/west doors will be represented in a similar fashion with the door being the |
	character as opposed to a -.
	o In the center of the room you will display a letter that represents what the room
	contains. Here are the letters to use and what they represent:
	▪ M - Multiple Items
	▪ P - Pit
	▪ I - Entrance (In)
	▪ O - Exit (Out)
	▪ V - Vision Potion
	▪ H - Healing Potion
	▪ E - Empty Room
	▪ X - Monster
	Example: Room 1,1 might look like
	* - *
	| P |
	* - *
	Room 0,0 might look like
	* * *
	* E |
	* - *
	************************************/
	@Override
	public String toString() {
		String northDoor = hasNorthDoor ? "-" : "*";
		String southDoor = hasSouthDoor ? "-" : "*";
		String eastDoor = hasEastDoor ? "|" : "*";
		String westDoor = hasWestDoor ? "|" : "*";
		String roomContent = "E";
		int numItem = 0;
		if (hasPit()){
			numItem++;
			roomContent = "P";
		}
		if (isEntrance()){
			numItem++;
			roomContent = "I";
		}
		if (isExit()){
			numItem++;
			roomContent = "O";
		}
		if (hasVisionPotion()){
			numItem++;
			roomContent = "V";
		}
		if (hasPotion()){
			numItem++;
			roomContent = "H";
		}
		if (hasMonster()){
			numItem++;
			roomContent = "X";
		}
		if (numItem > 1){
			roomContent = "M";
		}
		return String.format("*%s*\n%s%s%s\n*%s*", northDoor, westDoor, roomContent, eastDoor, southDoor);
	}
}