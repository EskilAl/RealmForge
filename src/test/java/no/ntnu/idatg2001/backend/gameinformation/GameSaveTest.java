package no.ntnu.idatg2001.backend.gameinformation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GameSaveTest {

  private Game game;
  private Passage openingPassage;
  private Passage lastSavedPassage;
  private GameSave gameSave;

  @BeforeEach
  public void setup() {
    game = new Game();
    openingPassage = new Passage("Opening Passage", new StringBuilder("Opening Passage"));
    lastSavedPassage = new Passage("Last Saved Passage", new StringBuilder("Last Saved Passage"));
    game.setStory(new Story("My Story", openingPassage));
    gameSave = new GameSave(game, "Player1")
        .setLastSavedPassage(lastSavedPassage);
  }

  @Test
  void testGetTimeOfSave() {
    LocalDateTime timeOfSave = LocalDateTime.now();
    gameSave.setTimeOfSave(timeOfSave);

    LocalDateTime savedTime = gameSave.getTimeOfSave();

    assertEquals(timeOfSave, savedTime);
  }

  @Test
  void testGetTimeOfSaveFormatted() {
    LocalDateTime timeOfSave = LocalDateTime.of(2023, 5, 22, 10, 30);
    gameSave.setTimeOfSave(timeOfSave);

    String formattedTime = gameSave.getTimeOfSaveFormatted();

    assertEquals("10:30 22.05.2023", formattedTime);
  }

  @Test
  void testGetPlayerName() {
    String playerName = gameSave.getPlayerName();

    assertEquals("Player1", playerName);
  }

  @Test
  void testGetGame() {
    Game savedGame = gameSave.getGame();

    assertEquals(game, savedGame);
  }

  @Test
  void testGetLastSavedPassage() {
    Passage savedPassage = gameSave.getLastSavedPassage();

    assertEquals(lastSavedPassage, savedPassage);
  }

  @Test
  void testSavePassage() {
    Passage newPassage = new Passage("New Passage", new StringBuilder("New Passage"));
    gameSave.savePassage(newPassage);

    Passage savedPassage = gameSave.getLastSavedPassage();

    assertEquals(newPassage, savedPassage);
  }

  @Test
  void testGetStoryAndLastPassageWithLastSavedPassage() {
    String storyAndLastPassage = gameSave.getStoryAndLastPassage();

    assertEquals("My Story - Last Saved Passage", storyAndLastPassage);
  }

  @Test
  void testGetStoryAndLastPassageWithNullLastSavedPassage() {
    gameSave.setLastSavedPassage(null);

    String storyAndLastPassage = gameSave.getStoryAndLastPassage();

    assertEquals("My Story - Opening Passage", storyAndLastPassage);
  }
}
