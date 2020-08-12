package dungeon;

public class DeathWebSpider extends Monster {
	
		 static String name = "Immolatus the Infernal";
		 static int hitPoints=90;
		 static int attackSpeed=5; 
		 static double chanceToHit=.6;
		 static double chanceToHeal =.4;
		 static  int damageMin=20;
		 static int damageMax=80;
		 static int minHeal = 5;
		 static int maxHeal = 50;
	    public DeathWebSpider()
		{
			super(name, hitPoints, attackSpeed, chanceToHit, chanceToHeal,
					damageMin, damageMax, minHeal, maxHeal);

	    }//end constructor

		public void attack(DungeonCharacter opponent)
		{
			System.out.println(name + " bites with venom fangs " +
								  ":");
			super.attack(opponent);

		}//end override of attack
	   
	   public void specialSkill(DungeonCharacter opponent)
		{
	   	int damagePoints = (int)(Math.random() * 20) + 50;
			System.out.println(name + " Spins  " + opponent.getName() + " into a venomous web, doing " + damagePoints
									+ " damage!");
			opponent.subtractHitPoints(damagePoints);
		}


	}//end class DeathWebSpider

