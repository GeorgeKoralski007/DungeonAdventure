package dungeon;

// This will contain room data
public class Room
{
	private static final double CHANCE_SPAWN = 0.1;  // 10% change of spawning objects (pit, potion, vision potion)
	private static final int POTION_MIN_POINTS = 5; 
	private static final int POTION_MAX_POINTS = 15; 
	
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
		this.hasVisionPotion = Math.random() <= CHANCE_SPAWN;
		
		//Healing potion generation
		if (Math.random() <= CHANCE_SPAWN)
		{
			this.potionHealingPoints = (int)(Math.random() * (POTION_MAX_POINTS-POTION_MIN_POINTS+1)) + POTION_MIN_POINTS;
		}
		
		// Pit generation
		if (Math.random() <= CHANCE_SPAWN)
		{
			this.potionHealingPoints = (int)(Math.random() * (POTION_MAX_POINTS-POTION_MIN_POINTS+1)) + POTION_MIN_POINTS;
		}
	}
	
	// Methods for entance and exit
	public void setEntrance() {
		this.isEntrance = true;
		CleanObjects();
	}
	public void setExit() {
		this.isExit = true;
		CleanObjects();
	}
	public boolean isExit() { return this.isExit; }
	public boolean isEntrance() { return this.isEntrance; }
	
	// Methods for checking the doors
	public boolean hasNorthDoor() { return this.hasNorthDoor; }
	public boolean hasSouthDoor() { return this.hasSouthDoor; }
	public boolean hasWestDoor() { return this.hasWestDoor; }
	public boolean hasEastDoor() { return this.hasEastDoor; }
	
	public boolean hasPotion() { return this.potionHealingPoints > 0; }
	public int getPotionHealingPoints() { return this.potionHealingPoints; }
	
	public boolean hasPit() { return this.pitDamagePoints > 0; }
	public int getPitDamagePoints() { return this.pitDamagePoints; }
	
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
	
	@Override
	public String toString() {
		return "TO DO";
	}
}