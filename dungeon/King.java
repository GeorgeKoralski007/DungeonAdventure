package dungeon;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class King extends Hero
{
	
	//created fields to be used in the super call to make the construction more readable
	private static int hitPoints = 90;
	private static int attackSpeed = 5;
	private static double chanceToHit = .9;
	private static int damageMin = 20;
	private static int damageMax = 40;
	private static double chanceToBlock = .5;

    public King()
	{
		super("King", hitPoints , attackSpeed, chanceToHit, damageMin, damageMax, chanceToBlock);



    }//end constructor

	private void smiteAttack(DungeonCharacter opponent)
	{
		if (Math.random() <= .8) // 80% chance for smite attack
		{
			int smite = (int)(Math.random() * 76) + 100;
			System.out.println(name + " smites " + smite
								+ " damage!");
			opponent.subtractHitPoints(smite);
		}//end blow succeeded
		else // 20% chance to skip the turn without attack
		{
			System.out.println(name + " failed to smite");
			System.out.println();
		}
	}

   public void specialSkill(DungeonCharacter opponent)
	{
		smiteAttack(opponent);
	}


}