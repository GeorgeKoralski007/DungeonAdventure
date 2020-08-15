package dungeon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class testMonster {

	Monster test;
	
	@BeforeEach
	void setUp() throws Exception {
		test = DungeonCharacterFactory.createMonster(1);
	}

	
	//A lot more complex than need be due to privacy of methods
	@Test
	void testHealing() {
		int cur;
		int hp = test.getHitPoints();
		
		while(test.isAlive()) {
			cur = test.getHitPoints();
			test.subtractHitPoints(1);
			hp = test.getHitPoints();
			
			if(cur < hp) {
				assertTrue(test.getHitPoints() == (cur + (hp-cur)));
				return;
			}
			
			
			
		}
		if(!test.isAlive()) fail("Health Has been depleated, healing is a random chance, run test again");
	}
	
	@Test
	void testSubtractHitPoints() {
		int hp = test.getHitPoints();
		
		while(test.isAlive()) {
			test.subtractHitPoints(hp);
		}
		
		assertTrue(test.getHitPoints()<1);
		
	}

}
