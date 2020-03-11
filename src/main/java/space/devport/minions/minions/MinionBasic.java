package space.devport.minions.minions;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.Inventory;
import space.devport.minions.minions.minion.mEntity;
import space.devport.minions.utils.ActionType;

import java.util.ArrayList;
import java.util.List;

public class MinionBasic {

    @Getter @Setter private String Author;
    @Getter @Setter private String Id;

    @Getter @Setter private mEntity minionEntity;
    @Getter @Setter private boolean isSpawning;

    @Getter @Setter private boolean hasVisibleName = false;
    @Getter @Setter private String visibleName;
    @Getter @Setter private boolean isSmall = true;
    @Getter @Setter private boolean isVisible = true;
    @Getter @Setter private int direction;
    @Getter @Setter private boolean invincible = true;

    @Getter @Setter private int radius;
    @Getter @Setter private int level;
    @Getter @Setter private int maxLevel;
    @Getter @Setter private int exp;

    @Getter @Setter private int health = 0;
    @Getter @Setter private boolean isHealthBased;
    @Getter @Setter private int actionsSinceLastHealthDrop;
    @Getter @Setter private int allowedActionsPerHealthUnit;

    @Getter @Setter private int itemsCollected;
    @Getter @Setter private int actionsPerformed;

    @Getter @Setter private Inventory upgradeGui;
    @Getter @Setter private Inventory inventory;

    private List<ActionType> _Actions;

    protected MinionBasic() {
        _Actions = new ArrayList<ActionType>();
    }

    protected void onLoad() {
        if(Author.isEmpty() || Id.isEmpty()) {
            System.out.println("Failed to load minion!!");
            return;
        }
    }

    protected void onSpawn() {
    }

    public boolean canDoAction() {
        if(this.isSpawning ||
                (this.isHealthBased && this.health <= 0) ||
                this.getMinionEntity().getArmorStand() == null || this.getMinionEntity().getArmorStand().isDead()) {
            System.out.println("Minion can't perform action");
            return false;
        }
        return true;
    }

    protected void doAction() {
        this.actionsPerformed++;
        if(this.isHealthBased) {
            this.actionsSinceLastHealthDrop++;
            if(this.actionsSinceLastHealthDrop >= this.allowedActionsPerHealthUnit) {
                this.health--;
                this.actionsSinceLastHealthDrop = 0;
                if(this.health >= 0) {

                }
            }
        }

    }

    protected void onDespawn() {
    }

    protected void onUnload() {

    }


}
