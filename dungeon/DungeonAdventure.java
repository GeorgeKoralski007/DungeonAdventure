
package dungeon;

public class DungeonAdventure
{
   public static void main(String[] args)
   {
      DungeonAdventure dungeonGame = new DungeonAdventure();
      dungeonGame.Play();
    }//end main method
    
    public void Play()
    {
      Hero theHero;
      Monster theMonster;
      do
      {
         theHero = chooseHero();
         theMonster = generateMonster();
         battle(theHero, theMonster);
      } while (playAgain());
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
       	                   "3. Thief\n"+
                            "4. King\n" +
       	                   "5. Druid");
		   choice = Keyboard.readInt();
      } while (choice < 1 || choice > 5);
      
      return DungeonCharacterFactory.createHero(choice);

	}//end chooseHero method

/*-------------------------------------------------------------------
generateMonster randomly selects a Monster and returns it.  It utilizes
a polymorphic reference (Monster) to accomplish this task.
---------------------------------------------------------------------*/
	private Monster generateMonster()
	{

		int choice = (int)(Math.random() * 6) + 1;

      
      return DungeonCharacterFactory.createMonster(choice);
   }//end generateMonster method

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
	private void battle(Hero theHero, Monster theMonster)
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
         
			//let the player bail out if desired only if both are alive
         if (theHero.isAlive() && theMonster.isAlive())
         do {
   			System.out.print("\nContinue battle (y/n): ");
   			choice = Character.toLowerCase(Keyboard.readChar());
         } while (choice != 'y' && choice != 'n');

		} while  (theHero.isAlive() && theMonster.isAlive() && choice == 'y'); //end battle loop

		if (!theMonster.isAlive())
		    System.out.println(theHero.getName() + " was victorious!");
		else if (!theHero.isAlive())
			System.out.println(theHero.getName() + " was defeated :-(");
		else//both are alive so user quit the game
			System.out.println("Quitters never win ;-)");

	}//end battle method


}//end Dungeon class