package command_structure;

import base.Repeater;

/**
 * interface for execute method AskCommands
 */
public interface AskCommand {

    boolean execute(String argument, Repeater repeater);
}
