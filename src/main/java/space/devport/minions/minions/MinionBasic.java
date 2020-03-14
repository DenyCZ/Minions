package space.devport.minions.minions;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.Inventory;
import space.devport.minions.MinionsPlugin;
import space.devport.minions.minions.minion.mEntity;

public class MinionBasic {

    @Getter @Setter private String Author;
    @Getter @Setter private String Id;
    @Getter private boolean isInit = false;

    @Getter @Setter private mEntity minionEntity;
    @Getter @Setter private boolean isSpawning;

    @Getter @Setter private boolean hasVisibleName = false;
    @Getter @Setter private String visibleName;
    @Getter @Setter private boolean isSmall = true;
    @Getter @Setter private boolean isVisible = true;
    @Getter @Setter private int direction;
    @Getter @Setter private boolean invincible = true;

    @Getter private MinionProperties mProperties;

    @Getter @Setter private int itemsCollected;
    @Getter @Setter private int actionsPerformed;

    @Getter @Setter private Inventory upgradeGui;
    @Getter @Setter private Inventory inventory;

    protected MinionBasic() {
        mProperties = new MinionProperties();

        this.isInit = true;
    }

    protected void onLoad() {
        if(!this.isInit || Author.isEmpty() || Id.isEmpty()) {
            MinionsPlugin.getInstance().getConsoleOutput().debug("Failed to load minion!!");
            return;
        }
    }

    protected void onSpawn() {
        if(!this.isInit) {
            return;
        }
    }

    public boolean canDoAction() {
        if(this.isSpawning ||
                (this.mProperties.isHealthBased() && this.mProperties.getHealth() <= 0) ||
                this.getMinionEntity().getArmorStand() == null || this.getMinionEntity().getArmorStand().isDead()) {
            MinionsPlugin.getInstance().getConsoleOutput().debug("Minion can't perform action");
            return false;
        }
        return true;
    }

    protected void doAction() {
        if(!canDoAction()) {
            return;
        }

        this.actionsPerformed++;
        if(this.mProperties.isHealthBased()) {
            this.mProperties.setActionsSinceLastHealthDrop(this.mProperties.getActionsSinceLastHealthDrop()+1);
            if(this.mProperties.getActionsSinceLastHealthDrop() >= this.mProperties.getAllowedActionsPerHealthUnit()) {
                this.mProperties.setHealth(this.mProperties.getHealth()-1);
                this.mProperties.setActionsSinceLastHealthDrop(0);
            }
        }

    }

    protected void onDespawn() {
    }

    protected void onUnload() {

    }


}
