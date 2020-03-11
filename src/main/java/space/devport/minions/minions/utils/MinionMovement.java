package space.devport.minions.minions.utils;

import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import space.devport.minions.MinionsPlugin;

public class MinionMovement {
    public static EulerAngle[] rightArmUp;
    public static EulerAngle[] leftArmUp;
    public static EulerAngle[] moveHeadBack;
    public static EulerAngle[] moveRightHandUpSlightly;
    public static EulerAngle[] rightHandPickaxeMovement;

    public static void performStartingAnimation(final ArmorStand armorStand) {
        new BukkitRunnable() {
            int counter = 0;
            int counter2 = MinionMovement.rightArmUp.length;
            final int val$animationsToPerform = MinionMovement.rightArmUp.length;

            public void run() {
                if (this.counter >= this.val$animationsToPerform) {
                    if (this.counter2 <= 0) {
                        this.cancel();
                        return;
                    }
                    --this.counter2;
                    armorStand.setRightArmPose(MinionMovement.rightArmUp[this.counter2]);
                    armorStand.setLeftArmPose(MinionMovement.leftArmUp[this.counter2]);
                    armorStand.setHeadPose(MinionMovement.moveHeadBack[this.counter2]);
                }
                else {
                    armorStand.setRightArmPose(MinionMovement.rightArmUp[this.counter]);
                    armorStand.setLeftArmPose(MinionMovement.leftArmUp[this.counter]);
                    armorStand.setHeadPose(MinionMovement.moveHeadBack[this.counter]);
                    ++this.counter;
                }
            }
        }.runTaskTimer(MinionsPlugin.getInstance(), 0L, 1L);
    }

    static {
        MinionMovement.rightArmUp = new EulerAngle[] { new EulerAngle(0.03, 0.05, 0.1), new EulerAngle(0.05, 0.1, 0.2), new EulerAngle(0.1, 0.15, 0.3), new EulerAngle(0.12, 0.2, 0.4), new EulerAngle(0.13, 0.26, 0.5), new EulerAngle(0.15, 0.27, 0.6), new EulerAngle(0.18, 0.28, 0.7), new EulerAngle(0.2, 0.29, 0.9), new EulerAngle(0.22, 0.3, 1.0), new EulerAngle(0.23, 0.31, 1.1), new EulerAngle(0.24, 0.32, 1.3), new EulerAngle(0.25, 0.34, 1.5), new EulerAngle(0.26, 0.37, 1.6), new EulerAngle(0.27, 0.4, 1.8), new EulerAngle(0.29, 0.46, 1.9), new EulerAngle(0.3, 0.5, 2.1), new EulerAngle(0.3, 0.52, 2.15), new EulerAngle(0.31, 0.57, 2.2), new EulerAngle(0.32, 0.6, 2.23), new EulerAngle(0.34, 0.57, 2.25) };
        MinionMovement.leftArmUp = new EulerAngle[] { new EulerAngle(0.03, 0.05, 6.28), new EulerAngle(0.05, 0.1, 6.2), new EulerAngle(0.1, 0.15, 6.1), new EulerAngle(0.12, 0.2, 6.0), new EulerAngle(0.13, 0.26, 5.9), new EulerAngle(0.15, 0.27, 5.8), new EulerAngle(0.18, 0.28, 5.5), new EulerAngle(0.2, 0.29, 5.4), new EulerAngle(0.22, 0.3, 5.3), new EulerAngle(0.23, 0.31, 5.2), new EulerAngle(0.24, 0.32, 5.1), new EulerAngle(0.25, 0.34, 5.0), new EulerAngle(0.26, 0.37, 4.9), new EulerAngle(0.27, 0.4, 4.8), new EulerAngle(0.29, 0.46, 4.7), new EulerAngle(0.3, 0.5, 4.6), new EulerAngle(0.3, 0.52, 4.55), new EulerAngle(0.31, 0.57, 4.5), new EulerAngle(0.32, 0.6, 4.45), new EulerAngle(0.34, 0.57, 4.4) };
        MinionMovement.moveHeadBack = new EulerAngle[] { new EulerAngle(6.28, 0.0, 0.0), new EulerAngle(6.25, 0.02, 0.01), new EulerAngle(6.2, 0.04, 0.02), new EulerAngle(6.15, 0.06, 0.03), new EulerAngle(6.1, 0.08, 0.04), new EulerAngle(6.05, 0.1, 0.05), new EulerAngle(6.0, 0.12, 0.06), new EulerAngle(5.95, 0.14, 0.07), new EulerAngle(5.9, 0.16, 0.08), new EulerAngle(5.85, 0.18, 0.09), new EulerAngle(5.8, 0.2, 0.1), new EulerAngle(5.75, 0.22, 0.11), new EulerAngle(5.7, 0.24, 0.12), new EulerAngle(5.6, 0.26, 0.13), new EulerAngle(5.5, 0.3, 0.14), new EulerAngle(5.4, 0.32, 0.15), new EulerAngle(5.3, 0.34, 0.16), new EulerAngle(5.28, 0.34, 0.18), new EulerAngle(5.25, 0.34, 0.2), new EulerAngle(5.25, 0.34, 0.2) };
        MinionMovement.moveRightHandUpSlightly = new EulerAngle[] { new EulerAngle(6.24, 0.0, 0.0), new EulerAngle(6.2, 0.0, 0.0), new EulerAngle(6.1, 0.0, 0.0), new EulerAngle(6.0, 0.0, 0.0), new EulerAngle(5.8, 0.0, 0.0), new EulerAngle(5.7, 0.0, 0.0), new EulerAngle(5.6, 0.0, 0.0), new EulerAngle(5.58, 0.0, 0.0) };
        MinionMovement.rightHandPickaxeMovement = new EulerAngle[] { new EulerAngle(5.6, 0.0, 0.0), new EulerAngle(5.5, 0.0, 0.0), new EulerAngle(5.4, 0.0, 0.0), new EulerAngle(5.3, 0.0, 0.0), new EulerAngle(5.2, 0.0, 0.0), new EulerAngle(5.1, 0.0, 0.0), new EulerAngle(5.0, 0.0, 0.0), new EulerAngle(4.9, 0.0, 0.0), new EulerAngle(4.8, 0.0, 0.0), new EulerAngle(4.7, 0.0, 0.0), new EulerAngle(4.6, 0.0, 0.0), new EulerAngle(4.5, 0.0, 0.0), new EulerAngle(4.4, 0.0, 0.0), new EulerAngle(4.3, 0.0, 0.0), new EulerAngle(4.5, 0.0, 0.0), new EulerAngle(4.6, 0.0, 0.0), new EulerAngle(4.7, 0.0, 0.0), new EulerAngle(4.8, 0.0, 0.0), new EulerAngle(4.9, 0.0, 0.0), new EulerAngle(5.0, 0.0, 0.0), new EulerAngle(5.1, 0.0, 0.0), new EulerAngle(5.2, 0.0, 0.0), new EulerAngle(5.3, 0.0, 0.0), new EulerAngle(5.4, 0.0, 0.0), new EulerAngle(5.5, 0.0, 0.0), new EulerAngle(5.6, 0.0, 0.0) };
    }
}
