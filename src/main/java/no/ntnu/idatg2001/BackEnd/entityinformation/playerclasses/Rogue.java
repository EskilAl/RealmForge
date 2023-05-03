package no.ntnu.idatg2001.BackEnd.entityinformation.playerclasses;

import jakarta.persistence.Entity;
import no.ntnu.idatg2001.BackEnd.entityinformation.Unit;
import no.ntnu.idatg2001.BackEnd.entityinformation.PlayerClass;

@Entity
public class Rogue extends Unit {

  public Rogue(String playerName) {
    super(75, 75, playerName, 0, 75);
    super.addToInventory("Dagger");
    super.addToInventory("HealthPotion");
    super.addToInventory("PoisonBottle");
    super.addToInventory("Cloak");
    this.setPlayerClass(PlayerClass.ROGUE);
    super.setIntelligence(5);
    super.setDexterity(5);
    super.setStrength(2);
    super.setLuck(10);
  }

  public Rogue() {

  }
}
