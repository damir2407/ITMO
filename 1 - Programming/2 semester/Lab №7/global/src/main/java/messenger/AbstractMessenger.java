package messenger;

import data.SpaceMarine;

import java.io.Serializable;
import java.util.*;

/**
 * Abstract class for messenger
 */

public abstract class AbstractMessenger implements Messenger, Serializable {
    protected Map<String, String> commandsDescription = new HashMap<>();

    /**
     * for display our marine fields
     *
     * @param marinesCollection collection to read info by
     * @return marine in string format
     */
    public String getMarineFieldsInformation(Map<Integer, SpaceMarine> marinesCollection) {
        String info = "";
        String chapter;
        for (Integer i : marinesCollection.keySet()) {
            if (marinesCollection.get(i).getChapter() == null) {
                chapter = "";
            } else
                chapter = "\nChapter: Name: " + marinesCollection.get(i).getChapter().getName() + "\n\t     World: " + marinesCollection.get(i).getChapter().getWorld();
            info += "key: " + i + "\nid №" + marinesCollection.get(i).getId() +
                    "\nName: " + marinesCollection.get(i).getName() +
                    "\nCoordinates: X: " + marinesCollection.get(i).getCoordinates().getX() +
                    "\n\t\t\t Y: " + marinesCollection.get(i).getCoordinates().getY() +
                    "\ncreationDate: " + marinesCollection.get(i).getCreationDate() +
                    "\nHealth: " + marinesCollection.get(i).getHealth() +
                    "\nHeart Count: " + marinesCollection.get(i).getHeartCount() +
                    "\nHeight: " + marinesCollection.get(i).getHeight() +
                    "\nCategory: " + marinesCollection.get(i).getCategory() +
                    chapter + "\n\n\n";
        }
        return info;
    }

    /**
     * Returns information about commands
     *
     * @return information about the commands
     */
    public String getCommandsDescription() {
        StringBuilder description = new StringBuilder();
        for (String command : commandsDescription.keySet()) {
            description.append(command + ": " + commandsDescription.get(command) + ".\n");
        }
        return description.toString();
    }

    public abstract String successfullyExecuteCommandMessage(String commandName);
}
