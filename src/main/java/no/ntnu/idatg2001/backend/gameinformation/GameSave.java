package no.ntnu.idatg2001.backend.gameinformation;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import no.ntnu.idatg2001.backend.entityinformation.Unit;
import no.ntnu.idatg2001.backend.gameinformation.Game;
import no.ntnu.idatg2001.backend.gameinformation.Passage;
import no.ntnu.idatg2001.backend.goals.Goal;
import no.ntnu.idatg2001.dao.GameSaveDAO;

@Entity
@Table(name = "game_save")
public class GameSave {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String saveName;
  private LocalDateTime timeOfSave;
  private String playerName;
  @OneToOne
  @JoinColumn(name = "game_id")
  private Game game;
  @OneToOne
  @JoinColumn(name = "passage_id")
  private Passage lastSavedPassage;


  public GameSave(Game gameToSave, String playerName) {
    this.game = gameToSave;
    this.saveName = gameToSave.getStory().getTitle();
    this.playerName = playerName;
    this.timeOfSave = LocalDateTime.now();
  }

  public GameSave() {}

  public Long getId() {
    return id;
  }
  public Long setId(Long id) {
    return this.id = id;
  }

  public String getSaveName() {
    return saveName;
  }

  public void setSaveName(String saveName) {
    this.saveName = saveName;
  }

  public GameSave setLastSavedPassage(
      Passage lastSavedPassage) {
    this.lastSavedPassage = lastSavedPassage;
    return this;
  }

  public GameSave setTimeOfSave(LocalDateTime timeOfSave) {
    this.timeOfSave = timeOfSave;
    return this;
  }

  public GameSave setPlayerName(String playerName) {
    this.playerName = playerName;
    return this;
  }

  public GameSave setGame(Game game) {
    this.game = game;
    return this;
  }


  public LocalDateTime getTimeOfSave() {
    return timeOfSave;
  }

  public String getTimeOfSaveFormatted() {
    return timeOfSave.format(DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy"));
  }

  public String getPlayerName() {
    return playerName;
  }

  public Game getGame() {
    return game;
  }

  public Passage getLastSavedPassage() {
    return lastSavedPassage;
  }

  public void savePassage(Passage lastSavedPassage) {
    this.lastSavedPassage = lastSavedPassage;
  }

  public Unit getUnit() {
     return this.game.getUnit();
  }

  public Story getStory() {
     return this.game.getStory();
  }

  public List<Goal> getGoals() {
     return this.game.getGoals();
  }

  public void setUnit(Unit unit) {
     this.game.setUnit(unit);
  }

  public void setStory(Story story) {
     this.game.setStory(story);
  }

  public void setGoals(List<Goal> goals) {
     this.game.setGoals(goals);
  }

  public void setStoredInitialImprint(GameSave gameSave) {
    // Set the relevant properties from the original game save to the initial imprint
    this.setGame(gameSave.getGame()); // Set the game to the original game
    this.setUnit(gameSave.getUnit()); // Set the unit to the original unit
    this.setStory(gameSave.getStory()); // Set the story to the original story
    this.setGoals(gameSave.getGoals()); // Set the goals to the original goals
    this.setTimeOfSave(gameSave.getTimeOfSave()); // Set the time of save to the original time of save
    this.setPlayerName(gameSave.getPlayerName()); // Set the player name to the original player name
    this.setId(gameSave.getId()); // Set the id to the original id

    // Set the time of save and player name to appropriate values if needed
  }

  public GameSave getInitialGameSave() {
    GameSave initialGameSave = new GameSave();
    initialGameSave.setStoredInitialImprint(this);
    return initialGameSave;
  }

  public String getStoryAndLastPassage() {
    StringBuilder location = new StringBuilder(this.saveName + " - ");
    if (this.lastSavedPassage == null) {
      location.append(this.game.begin().getTitle());
    } else {
      location.append(this.lastSavedPassage.getTitle());
    }
    return location.toString();
  }
}
