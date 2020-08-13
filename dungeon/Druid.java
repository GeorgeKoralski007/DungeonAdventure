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

	    public void battleChoices(DungeonCharacter opponent)
		{
			initializeTurns(opponent);
			
			int choice;
			
			do
			{
			   choice = super.attackMenu("Special Attack on " + opponent.getName(), opponent);
			   
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

			} while(numTurns > 0);

	    }
	}
