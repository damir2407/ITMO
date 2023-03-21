package file_works;


import data.SpaceMarine;
import messenger.Messenger;
import server_works.ServerSender;
import utility.Answer;
import utility.Error;
import utility.Success;

import java.net.InetAddress;
import java.util.*;

public class Transform implements Transformer {
    private Loader fileManager;
    private LoadCheck fileFieldsChecker;
    private Messenger messenger;
    private ServerSender serverSender;
    private InetAddress inetAddress;
    private int port;

    public Transform(Loader fileManager, LoadCheck fileFieldsChecker, InetAddress inetAddress, int port, ServerSender serverSender) {
        this.fileManager = fileManager;
        this.fileFieldsChecker = fileFieldsChecker;
        this.inetAddress = inetAddress;
        this.port = port;
        this.serverSender = serverSender;
    }

    @Override
    public Map<Integer, SpaceMarine> convertFromJson() {
        Map<Integer, SpaceMarine> marines = null;
        try {
            marines = fileManager.load();

            if (marines == null) throw new NoSuchElementException();

            if (fileFieldsChecker.check(marines) instanceof Success) {
                serverSender.abstractServerSender.send(new Answer().ok(messenger.collectionSuccessfullyMessage()), inetAddress, port);
            } else if (fileFieldsChecker.check(marines) instanceof Error) {
                serverSender.abstractServerSender.send(new Answer().error(((Error) fileFieldsChecker.check(marines)).getErrorMessage()), inetAddress, port);
                marines.clear();
            }
        } catch (NumberFormatException exception) {
            serverSender.abstractServerSender.send(new Answer().error(messenger.jsonSyntaxMessage()), inetAddress, port);
            return null;
        } catch (NoSuchElementException e) {
            serverSender.abstractServerSender.send(new Answer().error(messenger.noSuchElementInFileMessage()), inetAddress, port);
            return null;
        } catch (NullPointerException exception) {
            serverSender.abstractServerSender.send(new Answer().error(messenger.fileNotFoundMessage()), inetAddress, port);
            return null;
        }
        return marines;
    }

    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }
}
