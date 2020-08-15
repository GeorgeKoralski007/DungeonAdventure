package dungeon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DungeonCharacterTests {

	DungeonCharacter testHero;
	DungeonCharacter testMonster;
	
	@BeforeEach
	void setUp() throws Exception {
		testHero = DungeonCharacterFactory.createHero(1);
		testMonster = DungeonCharacterFactory.createMonster(1);
	}
	
	@Test
	void testAddHitpointsSubtract() {
		int HP = testHero.getHitPoints();
		assertEquals(HP, testHero.getHitPoints());
		testHero.addHitPoints(5);
		assertEquals(HP + 5,testHero.getHitPoints());
		
		HP = testMonster.getHitPoints();
		assertEquals(HP, testMonster.getHitPoints());
		testMonster.addHitPoints(HP);
		assertEquals(HP*2,testMonster.getHitPoints());
		
		testMonster.subtractHitPoints(HP);
		assertEquals(HP,testMonster.getHitPoints());
		
	}
	
	@Test
	void testIsAlive() {
		assertTrue(testHero.isAlive());
		testHero.subtractHitPoints(testHero.getHitPoints());
		assertFalse(testHero.isAlive());
	}

}
