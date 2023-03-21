package ask_works;

import data.AstartesCategory;

import java.util.Scanner;

/**
 * interface for Poll class to ask fields
 */
public interface PollInterface {


    String claimName();

    void setUserScanner(Scanner userScanner);

    Scanner getUserScanner();

    void setUserMode();

    void setFileMode(boolean fileMode);

    Float claimXCoordinate();

    Double claimYCoordinate();

    Float claimHealth();

    Integer claimHeartCount();

    float claimHeight();

    AstartesCategory claimCategory();

    String claimChapterName();

    String claimChapterWorld();

    String claimLogin();

    String claimPassword();
}
