package dungeon;

public class Druid extends Hero{

		
		//created fields to be used in the super call to make the construction more readable
		private static int hitPoints = 120;
		private static int attackSpeed = 6;
		private static double chanceToHit = .85;
		private static int damageMin = 15;
		private static int damageMax = 60;
		private static double chanceToBlock = .5;

	    public Druid()
		{
			super("Druid", hitPoints , attackSpeed, chanceToHit, damageMin, damageMax, chanceToBlock);



	    }//end constructor

		private void shapeShift(DungeonCharacter opponent)
		{
			if (Math.random() <= .8) // 80% chance for smite attack
			{
				int smite = (int)(Math.random() * 76) + 100;
				System.out.println(name + " transforms into a bear and slashes at " + opponent.getName() + " causing " + smite
									+ " damage!");
				opponent.subtractHitPoints(smite);
			}//end blow succeeded
			else // 20% chance to skip the turn without attack
			{
				System.out.println(name + " failed to transform");
				System.out.println();
			}
		}

	   public void specialSkill(DungeonCharacter opponent)
		{
		   shapeShift(opponent);
		}

	 
	}

