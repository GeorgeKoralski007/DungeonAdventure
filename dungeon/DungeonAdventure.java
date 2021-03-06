
package dungeon;

public class DungeonAdventure
{  
	Hero theHero;
   public static void main(String[] args)
   {
	   DungeonAdventure dungeonGame = new DungeonAdventure();
	 
	   dungeonGame.Play();
    }//end main method
    
    public void Play()
    {
     
      do
      {
         theHero = chooseHero();
         
         System.out.println("You are at the entrance to a strange place. \nYour mission is clear, find the four pillars of OO and escape.\n"
         		+ "Where would you like to go?");
         
         start(theHero);

      } while (playAgain());
    }

private void start(Hero hero) {
	
    Dungeon game = new Dungeon();
 
    int numberOfPillarsFound = 0;
    Room room;
	while(hero.isAlive()) {
		room = game.getHeroRoom();
		game.printHeroRoom();
		
		if(room.hasPillarOfOO()) {
			System.out.println("You have found a pillar of OO: " + room.getPillarOfOO());
			numberOfPillarsFound++;
		} 
		
		if(room.hasPit()) {
			double temp = hero.chanceToBlock;
			hero.chanceToBlock = 0;
			System.out.println(hero.name + " has fallen into a pit!");
			hero.subtractHitPoints(room.getPitDamagePoints());
			hero.chanceToBlock = temp;
		}
		
		if(room.hasPotion()) {
			System.out.println("You have picked up a potion");
			hero.healthPotionPickedUp(room.getPotionHealingPoints());
			
		} 
		if(room.hasVisionPotion()) {
			System.out.println("You have picked up a Vision Potion!");
			hero.visionPotionPickedUp();
		}
		
		room.CleanObjects(); // remove potions, pits and pillars
		
		if(room.hasMonster()) {
			
			Monster monster = room.getMonster();
			System.out.println("You have encountered a monster, you must fight, running is for cowards.");
			battle(hero, monster,game);

			room.setMonster(null); // Remove the monster after the battle
			if (!hero.isAlive()) {
				break;
			}
		}

		if(room.isExit()) {
			System.out.println(hero.name + " has reached the exit");
			if(numberOfPillarsFound >= 4) {
				System.out.println("You have won the game!");
				break;
			} else {
				System.out.println("You don't have all the pillars. Keep searching!");
			}
		}
		move(game);
	}	
}

private void move(Dungeon game) {
	
	System.out.println("Choose action:");
	if (game.getHeroRoom().hasNorthDoor()) {
		System.out.println("N: Move North");		
	}
	if (game.getHeroRoom().hasSouthDoor()) {
		System.out.println("S: Move South");		
	}
	if (game.getHeroRoom().hasEastDoor()) {
		System.out.println("E: Move East");		
	}
	if (game.getHeroRoom().hasWestDoor()) {
		System.out.println("W: Move West");		
	}
	if(this.theHero.getVisionPotion() > 0)
		System.out.println("V: To see your surroundings:  Vision potions Remaining: "+ this.theHero.getVisionPotion());
	
	System.out.println("Q to quit\n?: See all rooms (if you want to cheat)\nHEALTH: Cheat to give player a terrabyte of health");

	String choice = Keyboard.readString().toUpperCase();
	if (choice.equals("?")) {
		game.printAllRooms();
	} else if (choice.equals("V") && this.theHero.getVisionPotion()>0) {
		this.theHero.useVisionPotion();
		game.printHeroRoomWithVisibility();
	} else if(choice.equals("Q")) { 
		System.exit(-1);
	} else if(choice.equals("HEALTH")) {
		theHero.addHitPoints(1024);
		System.out.println("Cheat activated, you now have " + theHero.getHitPoints() + " hit points");
	}
	else {
		game.MoveHero(choice);
	}
	
}

/*-------------------------------------------------------------------
chooseHero allows the user to select a hero, creates that hero, and
returns it.  It utilizes a polymorphic reference (Hero) to accomplish
this task
---------------------------------------------------------------------*/
	private Hero chooseHero()
	{
		int choice;
      do
      {

    	  
		System.out.println("Choose a hero:\n" +
   					       "1. Warrior\n" +
   					       "2. Sorceress\n" +
       	                   "3. Thief\n" +
                           "4. King\n" +
       	                   "5. Druid\n" + 
                           "6. Quit");
		   choice = Keyboard.readInt();
      } while (choice < 1 || choice > 6);
      if(choice != 6)
    	  return DungeonCharacterFactory.createHero(choice);
      else
    	  return DungeonCharacterFactory.createHero(6);
      	  

	}//end chooseHero method



/*-------------------------------------------------------------------
playAgain allows gets choice from user to play another game.  It returns
true if the user chooses to continue, false otherwise.
---------------------------------------------------------------------*/
	private boolean playAgain()
	{
		char input;
      do
      {
   		System.out.println("Play again (y/n)?");
   		input = Character.toLowerCase(Keyboard.readChar());
      } while (input != 'y' && input != 'n');
		return input == 'y';
	}//end playAgain method


/*-------------------------------------------------------------------
battle is the actual combat portion of the game.  It requires a Hero
and a Monster to be passed in.  Battle occurs in rounds.  The Hero
goes first, then the Monster.  At the conclusion of each round, the
user has the option of quitting.
---------------------------------------------------------------------*/
	private void battle(Hero theHero, Monster theMonster, Dungeon room)
	{
		char choice = 'y';
		System.out.println(theHero.getName() + " battles " + theMonster.getName());
		System.out.println("---------------------------------------------");

		//do battle
		do
		{
		    //hero goes first
			theHero.battleChoices(theMonster);

			//monster's turn (provided it's still alive!)
			if (theMonster.isAlive())
	         {
	            if (Math.random() <= 0.3) // 30% chance for special skill attack
				      theMonster.specialSkill(theHero);
	            else
	               theMonster.attack(theHero);
	         }

		} while  (theHero.isAlive() && theMonster.isAlive()); //end battle loop

		if (!theMonster.isAlive())
		    System.out.println(theHero.getName() + " was victorious!");
		else if (!theHero.isAlive())
			System.out.println(theHero.getName() + " was defeated :-(");

	}//end battle method

}//end Dungeon class