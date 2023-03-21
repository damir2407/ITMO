package client_works;

import ask_works.LanguageKeeper;
import ask_works.Poll;
import ask_works.PollInterface;
import client_validate.ClientFieldsValidation;
import client_validate.ClientValidator;
import command_works.*;
import input_fields_works.ConsoleRepeater;
import input_fields_works.MarineSetter;
import input_fields_works.Repeater;
import input_fields_works.Setter;
import messenger.*;
import print_works.PrintInterface;
import print_works.PrintMachine;
import request_structure.RequestInterface;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;

public class Client {
    private final int SERVER_PORT = 2407;
    private DatagramChannel datagramChannel;
    private SocketAddress socketAddress;
    private String login;
    private String password;


    public static void main(String[] args) {
        Client client = new Client();
        client.startClient();
    }


    public void startClient() {
        try {
            this.datagramChannel = DatagramChannel.open();
            this.socketAddress = new InetSocketAddress("localhost", SERVER_PORT);
            PrintInterface printMachine = new PrintMachine();
            ClientSendInterface clientSender = new ClientSender(datagramChannel, socketAddress, printMachine);


            LanguageKeeper languageKeeper = new LanguageKeeper(new RussianMessenger(), new EnglishMessenger(), printMachine);

            languageKeeper.putLanguage();
            RequestInterface languageRequest = languageKeeper.inputLanguage();


            clientSender.send(languageRequest);

            Messenger messenger = languageKeeper.getChosenMessenger();

            ClientData clientData = new ClientData();

            ClientReceiver clientReceiver = new ClientReceiver(datagramChannel, socketAddress, printMachine, messenger, clientData);

            Scanner scanner = new Scanner(System.in);

            ClientReadInterface clientReadInterface = new ClientReader(scanner);

            ClientValidator clientValidator = new ClientFieldsValidation(messenger);

            PollInterface poll = new Poll(scanner, clientValidator, messenger, printMachine);

            Setter marineSetter = new MarineSetter(poll);
            Repeater repeater = new ConsoleRepeater(marineSetter, clientData);
            ClientCommandInterface clientCommandManager = new ClientCommandManager(messenger, printMachine, repeater, poll, clientData);

            clientCommandManager.pushCommands();

            clientCommandManager.clientCommands();

            clientCommandManager.mapOfAvailableCommands();

            clientCommandManager.mapOfAskCommands();

            ClientExecuteInterface clientExecuteInterface = new ClientExecutor(clientCommandManager, poll, messenger, printMachine, clientSender, clientData);

            clientReceiver.start();
            clientReadInterface.interactiveMode(clientExecuteInterface);


        } catch (IOException e) {
            System.out.println("Ошибка!");
        }

    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
