package dungeon;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */




public class Warrior extends Hero
{
	//created fields to be used in the super call to make the construction more readable
	private static int hitPoints = 125;
	private static int attackSpeed = 4;
	private static double chanceToHit = .8;
	private static int damageMin = 35;
	private static int damageMax = 60;
	private static double chanceToBlock = .2;

    public Warrior()
	{

		super("Warrior", hitPoints, attackSpeed, chanceToHit, damageMin, damageMax, chanceToBlock);


    }//end constructor


	private void crushingBlow(DungeonCharacter opponent)
	{
		if (Math.random() <= .4) // 40% chance for crushingBlow attack
		{
			int blowPoints = (int)(Math.random() * 76) + 100;
			System.out.println(name + " lands a CRUSHING BLOW for " + blowPoints
								+ " damage!");
			opponent.subtractHitPoints(blowPoints);
		}//end blow succeeded
		else // 60% chance to skip the turn without attack
		{
			System.out.println(name + " failed to land a crushing blow");
			System.out.println();
		}//blow failed

	}//end crushingBlow method

	public void attack(DungeonCharacter opponent)
	{
		System.out.println(name + " swings a mighty sword at " +
							opponent.getName() + ":");
		super.attack(opponent);
	}//end override of attack method
   
   public void specialSkill(DungeonCharacter opponent)
	{
		crushingBlow(opponent);
	}
   


}//end Hero class