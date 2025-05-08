package homework_5;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

interface SimpleMessager {
    public boolean updateMessage(Integer key, String value);

    public boolean createMessage(Integer key, String value);

    public String deleteMessage(Integer key);

    public String getMessage(Integer key);
}


class Messager implements SimpleMessager {
    HashMap<Integer, String> simpleStore = new HashMap<>();

    private boolean isNullElement(String data) {
        if (data == null) {
            throw new NullPointerException();
        }
        return true;
    }

    @Override
    public boolean createMessage(Integer key, String value) {
        isNullElement(value);
        if (!simpleStore.containsKey(key)) {
            return simpleStore.put(key, value) == null;

        }
        return false;
    }

    @Override
    public boolean updateMessage(Integer key, String value) {
        isNullElement(value);
        return simpleStore.put(key, value) != null;

    }

    @Override
    public String deleteMessage(Integer key) {
        return simpleStore.remove(key);
    }

    @Override
    public String getMessage(Integer key) {
        return simpleStore.get(key);
    }

}


class LogMessagerProxy implements SimpleMessager {
    private Messager messager = new Messager();

    private static HashSet<Logger> logger = new HashSet<>();

    private enum ActionType {
        DELETE, PUT, GET, POST;
    }

    private class Logger {
        String date;
        String value;
        Integer key;
        boolean isSuccess;
        ActionType type;

        Logger(ActionType type, Integer key, String value, boolean success) {
            this.date = new Date().toString();
            this.value = value;
            this.type = type;
            this.key = key;
            this.isSuccess = success;
        }

        @Override
        public String toString() {
            return "type : " + type + ", date :" + date + ", success : " + isSuccess + ", key : "
                    + key + ", value : " + value;
        }

    }

    @Override
    public boolean updateMessage(Integer key, String value) {
        boolean isUpdated = messager.updateMessage(key, value);
        logger.add(new Logger(ActionType.PUT, key, value, isUpdated));
        return isUpdated;
    }

    @Override
    public String deleteMessage(Integer key) {
        String message = messager.deleteMessage(key);
        logger.add(new Logger(ActionType.DELETE, key, message, (message != null)));
        return message;
    }

    @Override
    public String getMessage(Integer key) {
        String message = messager.getMessage(key);
        logger.add(new Logger(ActionType.GET, key, message, (message != null)));
        return message;
    }

    @Override
    public boolean createMessage(Integer key, String value) {
        boolean isAdded = messager.createMessage(key, value);
        logger.add(new Logger(ActionType.POST, key, value, isAdded));
        return isAdded;
    }

    public static void showLogs() {
        for (Logger log : logger) {
            System.out.println(log.toString());
        }
    }

    public static void clearLogs() {
        logger.clear();
    }

}

public class ProxyDemo {
    public static void main(String[] args) {
        SimpleMessager messager = new LogMessagerProxy();
        messager.createMessage(1, "test 1");
        messager.createMessage(2, "test 2");
        messager.createMessage(3, "test 3");
        messager.deleteMessage(2);
        messager.getMessage(2);
        messager.updateMessage(1, "test 11");
        LogMessagerProxy.showLogs();
        LogMessagerProxy.clearLogs();
        LogMessagerProxy.showLogs();
        messager.createMessage(1, "test 1");
        messager.createMessage(2, "test 2");
        messager.createMessage(3, "test 3");
        System.out.println("-------------------");
        LogMessagerProxy.showLogs();

    }
}
