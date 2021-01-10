package sakura.kooi.BridgingAnalyzer.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sakura.kooi.BridgingAnalyzer.BridgingAnalyzer;
import sakura.kooi.BridgingAnalyzer.Counter;
import sakura.kooi.BridgingAnalyzer.utils.SendMessageUtils;

public class BridgeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§b§BridgePractice §7>> §cThis command is used to configure plugin parameters, and only players can execute it.");
            return true;
        }
        if (args.length != 1) {
            SendMessageUtils.sendMessage(sender,
                    "§b§BridgePractice §7>> §b§lBridgingAnalyzer | By.SakuraKooi",
                    "§b§BridgePractice §7>> §e/bridge highlight   §aEnable/disable side ride assist indication",
                    "§b§BridgePractice §7>> §e/bridge pvp         §aEnable/Disable PVP",
                    "§b§BridgePractice §7>> §e/bridge speed       §aEnable/disable bridge speed statistics",
                    "§b§BridgePractice §7>> §e/bridge stand       §aEnable/disable walking position indication",
                    "§b§BridgePractice §7>> §dThe configured parameters are only valid for you, other players are not affected",
                    "§b§BridgePractice §7>> §bhttps://github.com/SakuraKoi/BridgingAnalyzer"
            );
            return true;
        }
        Counter counter = BridgingAnalyzer.getCounter((Player) sender);
        switch (args[0].toLowerCase()) {
            case "highlight": {
                counter.setHighlightEnabled(!counter.isHighlightEnabled());
                sender.sendMessage("§b§BridgePractice §7>> §aSide ride assist instructions have been" + (counter.isHighlightEnabled() ? "Turned on" : "Turned off"));
                break;
            }
            case "pvp": {
                counter.setPvPEnabled(!counter.isPvPEnabled());
                sender.sendMessage("§b§BridgePractice §7>> §aPvP已" + (counter.isPvPEnabled() ? "Turned on" : "Turned off"));
                break;
            }
            case "speed": {
                counter.setSpeedCountEnabled(!counter.isSpeedCountEnabled());
                sender.sendMessage("§b§BridgePractice §7>> §aThe bridging speed statistics have been" + (counter.isSpeedCountEnabled() ? "Turned on" : "Turned off"));
                break;
            }
            case "stand": {
                counter.setStandBridgeMarkerEnabled(!counter.isStandBridgeMarkerEnabled());
                sender.sendMessage("§b§BridgePractice §7>> §a Walk position indication has been" + (counter.isStandBridgeMarkerEnabled() ? "Turned on" : "Turned off"));
                break;
            }
            default: {
                sender.sendMessage("§b§BridgePractice §7>> §aFunction to try to switch " + args[0] + "does not exist");
                break;
            }
        }
        return true;
    }
}
