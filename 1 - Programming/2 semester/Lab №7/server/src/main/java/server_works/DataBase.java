package server_works;

import data.AstartesCategory;
import data.Chapter;
import data.Coordinates;
import data.SpaceMarine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class DataBase {
    private Connection connection;
    private Statement statement;
    public static final Logger logger = LogManager.getLogger(DataBase.class);


    public DataBase(String login, String password) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("Ошибка!", e);
        }

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ballab7", login, password);
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            logger.error("Ошибка!", throwables);
        }

        logger.info("Подключение к базе данных успешно установлено!");
    }


    public void addMarineToDataBase(Integer key, SpaceMarine spaceMarine) {
        String name = spaceMarine.getName();
        Coordinates coordinates = spaceMarine.getCoordinates();
        Float xCoordinate = coordinates.getX();
        Double yCoordinate = coordinates.getY();
        Date creationDate = spaceMarine.getCreationDate();
        Float health = spaceMarine.getHealth();
        Integer heartCount = spaceMarine.getHeartCount();
        AstartesCategory category = spaceMarine.getCategory();
        Chapter chapter = spaceMarine.getChapter();
        String userLogin = spaceMarine.getUserLogin();
        int chapterNumber;
        int coordinatesNumber;
        float height = spaceMarine.getHeight();
        String chapterName = null;
        String chapterWorld = null;
        if (chapter != null) {
            chapterName = chapter.getName();
            chapterWorld = chapter.getWorld();
        }

        try {
            PreparedStatement marineToAdd = connection.prepareStatement(
                    "insert into spacemarine (key, name, coordinatesnumber, creationdate, health, heartcount, height, category, chapternumber, userlogin ) values  (?,?,?,?,?,?,?,?,?,?)");

            marineToAdd.setInt(1, key);
            marineToAdd.setString(2, name);
            coordinatesNumber = putCoordinates(xCoordinate, yCoordinate);
            marineToAdd.setInt(3, coordinatesNumber);
            String date = new SimpleDateFormat("dd.MM.yyyy HH:mm").format(creationDate);
            marineToAdd.setString(4, date);
            marineToAdd.setFloat(5, health);
            marineToAdd.setInt(6, heartCount);
            marineToAdd.setFloat(7, height);
            marineToAdd.setString(8, category.toString());
            if (chapter != null) {
                chapterNumber = putChapter(chapterName, chapterWorld);
                marineToAdd.setInt(9, chapterNumber);
            }
            marineToAdd.setString(10, userLogin);
            marineToAdd.executeUpdate();
            logger.info("Элемент был успешно добавлен!");
        } catch (SQLException throwables) {
            logger.error("Ошибка при добавлении элемента в базу данных!", throwables);
        }
    }


    public int putCoordinates(Float x, Double y) {
        try {
            PreparedStatement coordinatesInsert = connection.prepareStatement(
                    "insert into coordinates(x,y) values (?,?) returning number");
            coordinatesInsert.setFloat(1, x);
            coordinatesInsert.setDouble(2, y);

            if (coordinatesInsert.execute()) {
                ResultSet resultSet = coordinatesInsert.getResultSet();
                if (resultSet.next()) {
                    return resultSet.getInt("number");
                }
            }

        } catch (SQLException throwables) {
            logger.error("Ошибка!", throwables);
        }
        return 0;
    }

    public int putChapter(String chapterName, String chapterWorld) {
        try {
            PreparedStatement chapterInsert = connection.prepareStatement("insert into chapter(chaptername, chapterworld) values (?, ?) returning number");
            chapterInsert.setString(1, chapterName);
            chapterInsert.setString(2, chapterWorld);

            if (chapterInsert.execute()) {
                ResultSet resultSet = chapterInsert.getResultSet();
                if (resultSet.next()) {
                    return resultSet.getInt("number");
                }
            }
        } catch (SQLException throwables) {
            logger.error("Ошибка!", throwables);
        }
        return 0;
    }


    public Map<Integer, SpaceMarine> getInformation() {
        try {
            Integer key;
            Integer id;
            String name;
            Coordinates coordinates;
            Integer heartCount;
            Float health;
            Float height;
            Date creationDate;
            AstartesCategory category;
            Chapter chapter;
            String user;
            Map<Integer, SpaceMarine> spaceMarineMap = new TreeMap<>();
            ResultSet marineSet = statement.executeQuery("select * from spacemarine");
            while (marineSet.next()) {
                key = marineSet.getInt("key");
                id = marineSet.getInt("id");
                name = marineSet.getString("name");
                coordinates = getCoordinates(marineSet.getInt("coordinatesnumber"));
                heartCount = marineSet.getInt("heartcount");
                health = marineSet.getFloat("health");
                height = marineSet.getFloat("height");
                SimpleDateFormat format = new SimpleDateFormat();
                format.applyPattern("dd.MM.yyyy HH:mm");
                creationDate = format.parse(marineSet.getString("creationdate"));
                category = AstartesCategory.valueOf(marineSet.getString("category"));
                chapter = getChapter(marineSet.getInt("chapternumber"));
                user = marineSet.getString("userlogin");
                SpaceMarine spaceMarine = new SpaceMarine(id, name, coordinates, creationDate, health, heartCount, height, category, chapter, user);
                spaceMarineMap.put(key, spaceMarine);
            }
            marineSet.close();
            return spaceMarineMap;
        } catch (SQLException | ParseException throwables) {
            logger.error("Ошибка!", throwables);
        }
        return new TreeMap<>();
    }


    public void removeElement(Integer key) {
        Integer coordinatesNumber = null;
        Integer chapterNumber = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from spacemarine where key = ?");
            preparedStatement.setInt(1, key);
            if (preparedStatement.execute()) {
                ResultSet resultSet = preparedStatement.getResultSet();
                if (resultSet.next()) {
                    coordinatesNumber = resultSet.getInt("coordinatesnumber");
                    chapterNumber = resultSet.getInt("chapternumber");

                }
            }
            preparedStatement = connection.prepareStatement("delete from spacemarine where key = ?");
            preparedStatement.setInt(1, key);
            preparedStatement.execute();
            preparedStatement = connection.prepareStatement("delete from chapter where number = ?");
            preparedStatement.setInt(1, chapterNumber);
            preparedStatement.execute();
            preparedStatement = connection.prepareStatement("delete from coordinates where number = ?");
            preparedStatement.setInt(1, coordinatesNumber);
            preparedStatement.execute();

        } catch (SQLException throwables) {
            logger.error("Ошибка!", throwables);
        }
    }

    public boolean update(Integer key, SpaceMarine spaceMarine, String login) {
        if (getInformation().get(key).getUserLogin().equals(login)) {
            removeElement(key);
            addMarineToDataBase(key, spaceMarine);
            return true;
        }
        return false;
    }

    public void removeGreater(SpaceMarine spaceMarine, String login) {
        for (Integer i : getInformation().keySet()) {
            if (getInformation().get(i).getUserLogin().equals(login) && (getInformation().get(i).getHeight() > spaceMarine.getHeight()))
                removeElement(i);
        }
    }

    public void removeLowerKey(Integer key, String login) {
        for (Integer i : getInformation().keySet()) {
            if (i < key && getInformation().get(i).getUserLogin().equals(login)) removeElement(i);
        }
    }

    public boolean replaceIfLowe(Integer key, SpaceMarine spaceMarine, String login) {
        SpaceMarine spaceMarine1 = getInformation().get(key);
        if (spaceMarine1.getUserLogin().equals(login)) {

            if (spaceMarine.getHeight() < spaceMarine1.getHeight()) {
                update(key, spaceMarine, login);
                return true;
            }
        }
        return false;
    }

    public Coordinates getCoordinates(int coordinatesNumber) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from coordinates where number = ?");
            preparedStatement.setInt(1, coordinatesNumber);
            if (preparedStatement.execute()) {
                ResultSet resultSet = preparedStatement.getResultSet();
                if (resultSet.next()) {
                    Float x = resultSet.getFloat("x");
                    Double y = resultSet.getDouble("y");

                    Coordinates coordinates = new Coordinates(x, y);
                    return coordinates;
                }
            }
        } catch (SQLException e) {
            logger.error("Ошибка!", e);
        }
        return null;
    }

    public Chapter getChapter(int chapterNumber) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from chapter where number = ?");
            preparedStatement.setInt(1, chapterNumber);
            if (preparedStatement.execute()) {
                ResultSet resultSet = preparedStatement.getResultSet();
                if (resultSet.next()) {
                    String chapterName = resultSet.getString("chaptername");
                    String chapterWorld = resultSet.getString("chapterworld");

                    Chapter chapter = new Chapter(chapterName, chapterWorld);
                    return chapter;
                }
            }
        } catch (SQLException e) {
            logger.error("Ошибка!", e);
        }
        return null;
    }


    public void addInformation(Map<Integer, SpaceMarine> marines) {
        for (Integer i : marines.keySet()) {
            addMarineToDataBase(i, marines.get(i));
        }
    }

    public boolean checkUser(String login) {
        try {
            ResultSet resultSet = statement.executeQuery("select * from CLIENTS");
            String dataBaseLogin = "";
            while (resultSet.next()) {
                dataBaseLogin = resultSet.getString("login");
                if (login.equals(dataBaseLogin)) return false;
            }
        } catch (SQLException throwables) {
            logger.error("Ошибка!", throwables);
        }
        return true;
    }

    public void registerUser(String login, String password) {
        try {
            byte[] passwordInfo = password.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] digest = messageDigest.digest(passwordInfo);

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into CLIENTS (login,password) values (?,?)");
            preparedStatement.setString(1, login);
            preparedStatement.setBytes(2, digest);
            preparedStatement.executeUpdate();
            logger.info("Клиент " + login + " успешно зарегистрировался в базе данных!");
        } catch (NoSuchAlgorithmException | SQLException e) {
            logger.error("Ошибка!", e);
        }
    }

    public boolean authorizationUser(String login, String password) {
        try {
            ResultSet resultSet = statement.executeQuery("select * from CLIENTS");
            byte[] passwordData = password.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] digest = messageDigest.digest(passwordData);
            byte[] dataBasePassword;
            String dataBaseLogin = "";
            while (resultSet.next()) {
                dataBaseLogin = resultSet.getString("login");
                dataBasePassword = resultSet.getBytes("password");
                if (dataBaseLogin.equals(login) && !Arrays.equals(dataBasePassword, digest))
                    return false;
            }


        } catch (NoSuchAlgorithmException |
                SQLException e) {
            logger.error("Ошибка!", e);
        }
        return true;
    }


}
