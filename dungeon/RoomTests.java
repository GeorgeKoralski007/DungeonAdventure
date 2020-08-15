package dungeon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoomTests {

	Room test;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	test = new Room(true,true,true,true);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCreateRoom() {
		Room room = new Room(false, false, false, false);
		assertEquals(false, room.hasEastDoor());
		assertEquals(false, room.hasWestDoor());
		assertEquals(false, room.hasSouthDoor());
		assertEquals(false, room.hasNorthDoor());
		
		room = new Room(true, true, true, true);
		assertEquals(true, room.hasEastDoor());
		assertEquals(true, room.hasWestDoor());
		assertEquals(true, room.hasSouthDoor());
		assertEquals(true, room.hasNorthDoor());
	}
	@Test
	void testEntranceAndExit() {
		Room room = new Room(false, false, false, false);
		room.setEntrance();
		assertEquals(true, room.isEntrance());
		assertEquals(false, room.isExit());
		assertEquals(false, room.hasPotion());
		assertEquals(false, room.hasVisionPotion());
		assertEquals(false, room.hasPit());

		room = new Room(false, false, false, false);
		room.setExit();
		assertEquals(false, room.isEntrance());
		assertEquals(true, room.isExit());
		assertEquals(false, room.hasPotion());
		assertEquals(false, room.hasVisionPotion());
		assertEquals(false, room.hasPit());
	}
	
	@Test
	void testGeneratePit() {
		assertFalse(test.hasPit());
		test.generatePit();
		assertTrue(test.hasPit());
	}
	
	@Test
	void testPotions() {
		assertFalse(test.hasVisionPotion());
		test.generateVisionPotion();
		assertTrue(test.hasVisionPotion());
		
		assertFalse(test.hasPotion());
		test.generatePotion();
		assertTrue(test.hasPotion());
	}
	
	@Test
	void testSetMonster() {
		assertFalse(test.hasMonster());
		test.setMonster(DungeonCharacterFactory.createMonster(1));
		assertTrue(test.hasMonster());
	}

}
