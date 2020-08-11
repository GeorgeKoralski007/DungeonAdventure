package dungeon;

public class Ghoul extends Monster {




	 static String name = "Gavirug The ghoul";
	 static int hitPoints=50;
	 static int attackSpeed=7; 
	 static double chanceToHit=.7;
	 static double chanceToHeal =.3;
	 static  int damageMin=15;
	 static int damageMax=35;
	 static int minHeal = 5;
	 static int maxHeal = 30;
    public Ghoul()
	{
		super(name, hitPoints, attackSpeed, chanceToHit, chanceToHeal,
				damageMin, damageMax, minHeal, maxHeal);


    }//end constructor

	public void attack(DungeonCharacter opponent)
	{
		System.out.println(name + " quickly lunges at " +
							opponent.getName() + ":");
		super.attack(opponent);

	}//end override of attack
   
   public void specialSkill(DungeonCharacter opponent)
	{
   	int damagePoints = (int)(Math.random() * 30) + 30;
		System.out.println(name + " morphs into a unimaginable horror, turning  " + opponent.getName() + "'s stomach inside out, revealing todays lunch, causing " + damagePoints
								+ " damage!");
		opponent.subtractHitPoints(damagePoints);
	}


}//end Monster class