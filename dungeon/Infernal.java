package dungeon;

public class Infernal extends Monster
{
	 static String name = "Immolatus the Infernal";
	 static int hitPoints=180;
	 static int attackSpeed=1; 
	 static double chanceToHit=.5;
	 static double chanceToHeal =.1;
	 static  int damageMin=20;
	 static int damageMax=70;
	 static int minHeal = 5;
	 static int maxHeal = 20;
    public Infernal()
	{
		super(name, hitPoints, attackSpeed, chanceToHit, chanceToHeal,
				damageMin, damageMax, minHeal, maxHeal);

    }//end constructor

	public void attack(DungeonCharacter opponent)
	{
		System.out.println(name + " slams his rocky fist into " +
							opponent.getName() + ":");
		super.attack(opponent);

	}//end override of attack
   
   public void specialSkill(DungeonCharacter opponent)
	{
   	int damagePoints = (int)(Math.random() * 20) + 50;
		System.out.println(name + " makes sulphorous rain drench " + opponent.getName() + " causing their skin to burn and doing " + damagePoints
								+ " damage!");
		opponent.subtractHitPoints(damagePoints);
	}


}//end class Gremlin