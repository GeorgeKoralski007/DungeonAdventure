
package dungeon;

import java.util.ArrayList;

public abstract class Hero extends DungeonCharacter
{
	protected double chanceToBlock;
	protected int numTurns;

	private ArrayList<Integer> healthPotions = new ArrayList<Integer>();
	private int visionPotions = 0;
//-----------------------------------------------------------------
//calls base constructor and gets name of hero from user
  public Hero(String name, int hitPoints, int attackSpeed,
				     double chanceToHit, int damageMin, int damageMax,
					 double chanceToBlock)
  {
	super(name, hitPoints, attackSpeed, chanceToHit, damageMin, damageMax);
	this.chanceToBlock = chanceToBlock;
	readName();
  }

/*-------------------------------------------------------
readName obtains a name for the hero from the user

Receives: nothing
Returns: nothing

This method calls: nothing
This method is called by: hero constructor
---------------------------------------------------------*/
  public void readName()
  {
		System.out.print("Enter character name: ");
		this.name = Keyboard.readString();
  }//end readName method

/*-------------------------------------------------------
defend determines if hero blocks attack

Receives: nothing
Returns: true if attack is blocked, false otherwise

This method calls: Math.random()
This method is called by: subtractHitPoints()
---------------------------------------------------------*/
  private boolean defend()
  {
		return Math.random() <= chanceToBlock;

  }//end defend method

/*-------------------------------------------------------
subtractHitPoints checks to see if hero blocked attack, if so a message
is displayed, otherwise base version of this method is invoked to
perform the subtraction operation.  This method overrides the method
inherited from DungeonCharacter promoting polymorphic behavior

Receives: hit points to subtract
Returns: nothing

This method calls: defend() or base version of method
This method is called by: attack() from base class
---------------------------------------------------------*/
public void subtractHitPoints(int hitPoints)
	{
		if (defend())
		{
			System.out.println(name + " BLOCKED the attack!");
		}
		else
		{
			super.subtractHitPoints(hitPoints);
		}


	}//end method

	public void battleChoices(DungeonCharacter opponent)
	{
		initializeTurns(opponent);
		
		int choice;
		
		do
		{
		   choice = attackMenu("Smite Attack on " + opponent.getName(), opponent);
		   
		    switch (choice)
		    {
			    case 1: attack(opponent);
			        break;
			    case 2: specialSkill(opponent);
			        break;
			    case 3: useHealthPotion();
			    	System.out.println(this.name + " has " + this.getHitPoints() + " health after the potion");
			    	break;
			    default:
			        System.out.println("invalid choice!");
		    }//end switch
	
			numTurns--;
			if (numTurns > 0)
			    System.out.println("Number of turns remaining is: " + numTurns);
	
		} while(numTurns > 0 && isAlive() && opponent.isAlive());
	
	}
   
/*-------------------------------------------------------
InitializeTurns.  It computes the
number of turns a hero will get per round based on the opponent that is
being fought.  The number of turns is reported to the user. 

Receives: opponent
Returns: nothing

This method calls: getAttackSpeed()
This method is called by: external sources
---------------------------------------------------------*/
	protected void initializeTurns(DungeonCharacter opponent)
	{
	    numTurns = attackSpeed/opponent.getAttackSpeed();

		if (numTurns == 0)
			numTurns++;

		System.out.println("Number of turns this round is: " + numTurns);

	}
	
	//Changed the way battleChoices works in Hero
	//Can be overloaded if a new hero ends up having 4 moves
	public int attackMenu(String specialMove, DungeonCharacter opponent){
		int choice;
      do {
			System.out.println("1. Attack Opponent");
			System.out.println("2. " + specialMove);
			if (getHealthPotion() > 0)
				System.out.println("3. Use A HealthPotion:" + getHealthPotion() + " Remaining");
			System.out.print("Choose an option: ");
			choice = Keyboard.readInt();
      } while (choice < 1 || choice > 3);
		return choice;
	}
	/* this method is used to retrieve the amount of health potions the hero has
	 * 
	 */
	protected int getHealthPotion()
	{
		return healthPotions.size();
	}
	protected void useHealthPotion()
	{
		if (!healthPotions.isEmpty()) {
			
			hitPoints += healthPotions.remove(0);;
		}
	}
	protected void healthPotionPickedUp(int healthPoints)
	{
		healthPotions.add(healthPoints);
	}

	public void useVisionPotion() {
		if (visionPotions > 0)
			visionPotions--;
	}
	
	protected void visionPotionPickedUp() {
		this.visionPotions++;
	}
	protected int getVisionPotion() {
		return this.visionPotions;
	}

}//end Hero class