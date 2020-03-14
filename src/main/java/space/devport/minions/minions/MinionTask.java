package space.devport.minions.minions;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;
import space.devport.minions.MinionsPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MinionTask {

    private List<Integer> taskIds;

    public MinionTask() {
        taskIds = new ArrayList<>();
    }

    public void startTasks() {
        startActionTask();
    }

    private void startActionTask() {
        for(MinionGroup mGroup : MinionsPlugin.getInstance().getMinionManager().getMinionGroups()) {
            BukkitTask runnable = Bukkit.getScheduler().runTaskTimer(MinionsPlugin.getInstance(), new Runnable() {
                @Override
                public void run() {
                    final List<Class<? extends MinionBasic>> minions = mGroup.getMinions();
                    for(Class<? extends MinionBasic> minion : minions) {
                        try {
                            minion.getDeclaredMethod("doAction").invoke(minion);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, (50 + new Random().nextInt(100)), 20L);

            taskIds.add(runnable.getTaskId());
        }
    }

    public void stopTasks() {
        for(Integer id : taskIds) {
            Bukkit.getScheduler().cancelTask(id);
        }
    }
}
