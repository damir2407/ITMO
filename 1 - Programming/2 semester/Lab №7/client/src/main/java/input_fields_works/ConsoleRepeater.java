package input_fields_works;

import client_works.ClientData;
import data.Chapter;
import data.Coordinates;
import data.SpaceMarine;


import java.util.Date;


/**
 * class for commands with input fields
 */
public class ConsoleRepeater implements Repeater {

    private Setter marineSetter;
    private ClientData clientData;

    public ConsoleRepeater(Setter marineSetter, ClientData clientData) {
        this.marineSetter = marineSetter;
        this.clientData = clientData;
    }

    /**
     * need to read fields
     *
     * @param id to save id (for some commands)
     * @return SpaceMarine element
     */
    @Override
    public SpaceMarine repeatFields(Integer id) {
        SpaceMarine spaceMarine = new SpaceMarine();
        Coordinates coordinates = new Coordinates();
        Chapter chapter = new Chapter();
        repeatSetFieldName(spaceMarine);
        repeatSetFieldXCoordinate(coordinates);
        repeatSetFieldYCoordinate(coordinates);
        repeatSetFieldHealth(spaceMarine);
        repeatSetFieldHeartCount(spaceMarine);
        repeatSetFieldHeight(spaceMarine);
        repeatSetFieldCategory(spaceMarine);
        repeatSetFieldChapterName(chapter);
        repeatSetFieldChapterWorld(chapter);
        if (id == null) marineSetter.setFieldId(spaceMarine, (int) (Math.random() * 10000));
        else marineSetter.setFieldId(spaceMarine, id);
        marineSetter.setCoordinates(spaceMarine, coordinates);
        marineSetter.setChapter(spaceMarine, chapter);
        marineSetter.setCreationDate(spaceMarine, new Date());
        spaceMarine.setUserLogin(clientData.getLogin());
        return spaceMarine;
    }

    @Override
    public void repeatSetFieldName(SpaceMarine spaceMarine) {
        while (true) {
            if (marineSetter.setFieldName(spaceMarine))
                break;
        }
    }

    @Override
    public void repeatSetFieldXCoordinate(Coordinates coordinates) {
        while (true) {
            if (marineSetter.setFieldXCoordinate(coordinates))
                break;
        }
    }

    @Override
    public void repeatSetFieldYCoordinate(Coordinates coordinates) {
        while (true) {
            if (marineSetter.setFieldYCoordinate(coordinates))
                break;
        }
    }

    @Override
    public void repeatSetFieldHealth(SpaceMarine spaceMarine) {
        while (true) {
            if (marineSetter.setFieldHealth(spaceMarine))
                break;
        }
    }

    @Override
    public void repeatSetFieldHeartCount(SpaceMarine spaceMarine) {
        while (true) {
            if (marineSetter.setFieldHeartCount(spaceMarine))
                break;
        }
    }

    @Override
    public void repeatSetFieldHeight(SpaceMarine spaceMarine) {
        while (true) {
            if (marineSetter.setFieldHeight(spaceMarine))
                break;
        }
    }

    @Override
    public void repeatSetFieldCategory(SpaceMarine spaceMarine) {
        while (true) {
            if (marineSetter.setFieldCategory(spaceMarine))
                break;
        }
    }

    @Override
    public void repeatSetFieldChapterName(Chapter chapter) {
        while (true) {
            if (marineSetter.setFieldChapterName(chapter))
                break;
        }
    }

    @Override
    public void repeatSetFieldChapterWorld(Chapter chapter) {
        while (true) {
            if (marineSetter.setFieldChapterWorld(chapter))
                break;
        }
    }


}
