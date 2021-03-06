package dungeon;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */



public class Sorceress extends Hero
{
	//created fields to be used in the super call to make the construction more readable
	public final int MIN_ADD = 25;
	public final int MAX_ADD = 50;
	private static int hitPoints = 75;
	private static int attackSpeed = 5;
	private static double chanceToHit = .7;
	private static int damageMin = 25;
	private static int damageMax = 50;
	private static double chanceToBlock = .3;

//-----------------------------------------------------------------
    public Sorceress()
	{
		super("Sorceress", hitPoints , attackSpeed, chanceToHit, damageMin, damageMax, chanceToBlock);


    }//end constructor

//-----------------------------------------------------------------
	private void increaseHitPoints()
    {
	    int hPoints;

		 hPoints = (int)(Math.random() * (MAX_ADD - MIN_ADD + 1)) + MIN_ADD;
		 addHitPoints(hPoints);
		 System.out.println(name + " added [" + hPoints + "] points.\n"
							+ "Total hit points remaining are: "
							+ hitPoints);
		 System.out.println();

    }//end increaseHitPoints method

//-----------------------------------------------------------------
	public void attack(DungeonCharacter opponent)
	{
		System.out.println(name + " casts a spell of fireball at " +
							opponent.getName() + ":");
		super.attack(opponent);
	}//end override of attack method

   public void specialSkill(DungeonCharacter opponent)
	{
		increaseHitPoints();
	}
   


}//end class