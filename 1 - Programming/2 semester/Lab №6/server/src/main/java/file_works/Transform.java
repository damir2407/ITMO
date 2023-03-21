package file_works;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import data.SpaceMarine;
import messenger.Messenger;
import utility.Answer;
import utility.Error;
import utility.Success;
import server_works.ServerSendInterface;

import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.util.*;

public class Transform implements Transformer {
    private Loader fileManager;
    private Gson gson;
    private LoadCheck fileFieldsChecker;
    private Messenger messenger;
    private ServerSendInterface serverSender;
    private InetAddress inetAddress;
    private int port;

    public Transform(Loader fileManager, Gson gson, LoadCheck fileFieldsChecker, InetAddress inetAddress, int port, ServerSendInterface serverSender) {
        this.fileManager = fileManager;
        this.fileFieldsChecker = fileFieldsChecker;
        this.gson = gson;
        this.inetAddress = inetAddress;
        this.port = port;
        this.serverSender = serverSender;
    }

    @Override
    public Map<Integer, SpaceMarine> convertFromJson() {
        Map<Integer, SpaceMarine> marines = null;
        try {
            marines = gson.fromJson(fileManager.load(), new TypeToken<Map<Integer, SpaceMarine>>() {
            }.getType());
            if (marines == null) throw new NoSuchElementException();

            if (fileFieldsChecker.check(marines) instanceof Success) {
                serverSender.send(new Answer().ok(messenger.collectionSuccessfullyMessage()), inetAddress, port);
            } else if (fileFieldsChecker.check(marines) instanceof Error) {
                serverSender.send(new Answer().error(((Error) fileFieldsChecker.check(marines)).getErrorMessage()), inetAddress, port);
                marines.clear();
            }
        } catch (JsonSyntaxException | NumberFormatException exception) {
            serverSender.send(new Answer().error(messenger.jsonSyntaxMessage()), inetAddress, port);
            return null;
        } catch (NoSuchElementException e) {
            serverSender.send(new Answer().error(messenger.noSuchElementInFileMessage()), inetAddress, port);
            return null;
        } catch (FileNotFoundException | NullPointerException exception) {
            serverSender.send(new Answer().error(messenger.fileNotFoundMessage()), inetAddress, port);
            return null;
        } catch (SecurityException e) {
            serverSender.send(new Answer().error(messenger.securityErrorMessage()), inetAddress, port);
            return null;
        }
        return marines;
    }

    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }
}
