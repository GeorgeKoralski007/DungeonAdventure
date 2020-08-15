package dungeon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HeroTests {

	Hero test;
	
	@BeforeEach
	void setUp() throws Exception {
		System.out.println("Name Your Character \"Jimmy\" in order to properly test readName method");
		test = DungeonCharacterFactory.createHero(2);
	}

	@Test
	void testHealthPotion() {
		assertEquals(2,test.getHealthPotion());
		int hp = test.getHitPoints();
		test.useHealthPotion();
		assertEquals(hp+50,test.getHitPoints());
		assertEquals(1,test.getHealthPotion());
		test.healthPotionPickedUp(10);
		assertEquals(2,test.getHealthPotion());
	}
	
	@Test
	void testReadName() {
		
		String name = test.getName();
		assertTrue(name.equalsIgnoreCase("jimmy"));
	}

}
