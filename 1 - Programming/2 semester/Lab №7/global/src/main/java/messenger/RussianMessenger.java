package messenger;

/**
 * class for Russian messages
 */
public class RussianMessenger extends AbstractMessenger {

    public RussianMessenger() {
        setCommandsDescription();
    }

    public void setCommandsDescription() {
        commandsDescription.put("help", "вывести справку по доступным командам");
        commandsDescription.put("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        commandsDescription.put("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        commandsDescription.put("insert null {element}", "добавить новый элемент с заданным ключом");
        commandsDescription.put("update id {element}", "обновить значение элемента коллекции, id которого равен заданному");
        commandsDescription.put("remove_key null", "удалить элемент из коллекции по его ключу");
        commandsDescription.put("clear", "очистить коллекцию");
        commandsDescription.put("save", "сохранить коллекцию в файл");
        commandsDescription.put("execute_script file_name", " считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        commandsDescription.put("exit", "завершить программу (без сохранения в файл)");
        commandsDescription.put("remove_greater {element}", "удалить из коллекции все элементы, превышающие заданный");
        commandsDescription.put("replace_if_lowe null {element}", "заменить значение по ключу, если новое значение меньше старого");
        commandsDescription.put("remove_lower_key null", "удалить из коллекции все элементы, ключ которых меньше, чем заданный");
        commandsDescription.put("average_of_height", "вывести среднее значение поля height для всех элементов коллекции");
        commandsDescription.put("group_counting_by_category", "сгруппировать элементы коллекции по значению поля category, вывести количество элементов в каждой группе");
        commandsDescription.put("print_field_descending_heart_count", "вывести значения поля heartCount всех элементов в порядке убывания");
    }

    @Override
    public String getCollectionInfo(String type, int size, String lastInitialization, String lastSave) {
        return "Информация о коллекции:\n" + "Тип коллекции: " + type + "\nКоличество элементов в коллекции: " + size +
                "\nДата последней инициализации: " + lastInitialization + "\nДата последнего сохранения: " + lastSave;
    }


    @Override
    public String successfullyExecuteCommandMessage(String commandName) {
        return "Команда " + commandName + " успешно отработала!";
    }

    @Override
    public String canNotSaveFile() {
        return "Коллекция не может быть сохранена, из-за неверно указанного пути ";
    }

    @Override
    public String collectionIsEmptyMessage() {
        return "Коллекция пустая!";
    }

    @Override
    public String itemNotFoundMessage() {
        return "Солдата с таким параметром не существует!";
    }

    @Override
    public String commandNotFoundMessage(String s1, String s2) {
        return "Команда '" + s1 + " " + s2 + "' не найдена. Наберите 'help' для справки.";
    }

    @Override
    public String getCountingByCategory(int[] array) {
        return "Количество элементов в группе SCOUT: " + array[0] +
                "\nКоличество элементов в группе DREADNOUGHT: " + array[1] +
                "\nКоличество элементов в группе AGGRESSOR: " + array[2] +
                "\nКоличество элементов в группе SUPPRESSOR: " + array[3] +
                "\nКоличество элементов в группе CHAPLAIN: " + array[4];
    }

    @Override
    public String inputLoginMessage() {
        return "Введите логин:";
    }

    @Override
    public String notSuccessfullyRegUser() {
        return "Данный пользователь уже зарегистрирован!";
    }

    @Override
    public String successfullyRegUser() {
        return "Пользователь успешно зарегистрировался в базе данных!";
    }

    @Override
    public String notSuccessfullyAuthMessage() {
        return "Такого пользователя нет в базе";
    }

    @Override
    public String incorrectPasswordMessage() {
        return "Неправильно введен пароль!";
    }

    @Override
    public String executionBan() {
        return "Не авторизированным пользователям запрещается выполнение команд!";
    }

    @Override
    public String successfullyAuthMessage() {
        return "Вы успешно вошли в систему";
    }

    @Override
    public String canNotAuthAgain() {
        return "Вы уже авторизировались в базе данных!";
    }

    @Override
    public String inputPasswordMessage() {
        return "Введите пароль:";
    }


    @Override
    public String recursionMessage() {
        return "Скрипты не могут вызываться рекурсивно!";
    }

    @Override
    public String incorrectScriptFileMessage() {
        return "Проверьте скрипт на корректность введенных данных!";
    }

    @Override
    public String scriptFileDoesntExistMessage() {
        return "Файл со скриптом не найден!";
    }

    @Override
    public String noSuchElementInputMessage() {
        return "Не обнаружен пользовательский ввод";
    }

    @Override
    public String incorrectFileMessage() {
        return "Произошла ошибка во время считывания полей из файла! Данные из файла не загружены";
    }

    @Override
    public String collectionSuccessfullyMessage() {
        return "Коллекция успешно загружена! " + '\n' + useHelpMessage();
    }

    @Override
    public String useHelpMessage() {
        return "Введите help для отображения списка доступных команд";
    }

    @Override
    public String illegalStateMessage() {
        return "Непредвиденная ошибка";
    }

    @Override
    public String noSuchElementInFileMessage() {
        return "Загрузочный файл пуст!";
    }

    @Override
    public String securityErrorMessage() {
        return "Недостаточно прав для чтения/записи в файл!\nПредупреждение! В будущем коллекция не сможет быть сохранена!";
    }

    @Override
    public String fileNotFoundMessage() {
        return "Путь до файла json нужно передать через переменную окружения My_Path!\nПредупреждение! В будущем коллекция не сможет быть сохранена!";
    }

    @Override
    public String jsonSyntaxMessage() {
        return "Ошибка JSON синтексиса! Данные из файла не были загружены!";
    }

    @Override
    public String inputOutputMessage() {
        return "Ошибка! Загрузочный файл является директорией или не может быть открыт!";
    }

    @Override
    public String successfullySave() {
        return "Коллекция успешна сохранена в файл!";
    }

    @Override
    public String nameInputMessage() {
        return "Введите имя солдата: ";
    }

    @Override
    public String xCoordinateInputMessage() {
        return "Введите координату X:";
    }

    @Override
    public String yCoordinateInputMessage() {
        return "Введите координату Y:";
    }

    @Override
    public String healthInputMessage() {
        return "Введите здоровье:";
    }

    @Override
    public String heartCountInputMessage() {
        return "Введите количество сердец:";
    }

    @Override
    public String heightInputMessage() {
        return "Введите рост:";
    }

    @Override
    public String categoryInputMessage() {
        return "Введите категорию:";
    }

    @Override
    public String listOfAvailableCategoriesMessage(String line) {
        return "Список доступных категорий: " + line;
    }

    @Override
    public String chapterNameMessage() {
        return "Введите название главы:";
    }

    @Override
    public String chapterWorldMessage() {
        return "Введите название мира:";
    }

    @Override
    public String argumentErrorMessage(String command, boolean t) {
        if (!t) return "Используйте команду " + command + " без аргумента!";
        else return "Используйте команду " + command + " с аргументом!";
    }

    @Override
    public String averageOfHeightMessage(float f) {
        return "Среднее значение поля height всех элементов: " + f;
    }

    @Override
    public String successfullyClearedMessage() {
        return "Коллекция очищена";
    }

    @Override
    public String successfullyExitMessage() {
        return "Завершение программы...";
    }

    @Override
    public String hasNotBeenInitializationYetMessage() {
        return "Инициализации еще не было.";
    }

    @Override
    public String hasNotBeenSaveYetMessage() {
        return "Сохранения еще не было.";
    }

    @Override
    public String successfullyAddMessage() {
        return "Солдат успешно добавлен!";
    }

    @Override
    public String numberFormatArgumentMessage() {
        return "Аргумент должен быть представлен числом!";
    }

    @Override
    public String successfullyDeleteMessage() {
        return "Солдат(ы) успешно удалены";
    }

    @Override
    public String notSuccessfullyDeleteMessage() {
        return "Элементов превышающих заданный - нет!";
    }

    @Override
    public String notSuccessfullyDeleteByKeyMessage() {
        return "Элементов, ключ которых меньше заданного - нет!";
    }

    @Override
    public String successfullyReplaceMessage() {
        return "Замена была успешно проведена!";
    }

    @Override
    public String notSuccessfullyReplaceMessage() {
        return "Элемент не может быть заменен!";
    }

    @Override
    public String successfullyUpdatedMessage() {
        return "Элемент коллекции успешно обновлен!";
    }

    @Override
    public String idFormatErrorMessage() {
        return "Параметр id должен быть представлен числом!";
    }

    @Override
    public String incorrectChapterNameMessage() {
        return "Поле chapterName не может быть null, Строка не может быть пустой";
    }

    @Override
    public String incorrectChapterWorldMessage() {
        return "Поле chapterWorld не может быть null";
    }

    @Override
    public String incorrectCoordinatesMessage() {
        return "Поле coordinates не может быть null";
    }

    @Override
    public String incorrectXCoordinateMessage() {
        return "Ошибка! Параметр x долен иметь тип Float, Максимальное значение поля: 610, Поле не может быть null";
    }

    @Override
    public String incorrectYCoordinateMessage() {
        return "Ошибка! Параметр y должен иметь тип Double, Поле не может быть null";
    }

    @Override
    public String notEnoughRights(){
        return "У вас недостаточно прав для изменения объекта";
    }

    @Override
    public String incorrectNameMessage() {
        return "Поле name не может быть null, Строка не может быть пустой";
    }

    @Override
    public String incorrectCreationDateMessage() {
        return "Поле creationDate не может быть null";
    }

    @Override
    public String incorrectHealthMessage() {
        return "Ошибка! Поле health должно иметь тип float,  не может быть null, Значение поля должно быть больше 0";
    }

    @Override
    public String incorrectIdMessage() {
        return "Поле id не может быть null, Значение поля должно быть больше 0";
    }

    @Override
    public String incorrectIdUniquenessMessage() {
        return "Поле id должно быть уникальным!";
    }

    @Override
    public String incorrectHeartCountMessage() {
        return "Ошибка! Поле heartCount должно иметь тип Integer, не может быть null, Значение поля должно быть больше 0, Максимальное значение поля: 3";
    }

    @Override
    public String incorrectHeightMessage() {
        return "Параметр height должен иметь тип float";
    }

    @Override
    public String incorrectCategoryMessage() {
        return "Поле category не может быть null";
    }

    @Override
    public String categoryDoesNotExist() {
        return "Такой категории не существует! Поле не может быть null";
    }

}
