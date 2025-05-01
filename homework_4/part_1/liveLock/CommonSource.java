package homework_4.part_1.liveLock;

public class CommonSource {
    private boolean isUsing = false;
    private boolean isAnotherWaiting = false;
    private String data = "some data";
    private User currentUser;

    public void showUseInfo() {
        String userName = currentUser != null ? currentUser.getName() : "unknown";
        String threadName = Thread.currentThread().getName();
        System.out.println("using by : " + userName + " , thread : " + threadName);
    }

    public synchronized void setUser(User user) {
        if (user == null) {
            throw new NullPointerException();
        }
        currentUser = user;
    }

    public synchronized User getUser() {
        return currentUser;
    }

    public String getData() {
        return data;
    }

    public synchronized void setIsUsing(boolean flag) {
        isUsing = flag;
    }

    public synchronized boolean isActive() {
        return isUsing;
    }

    public synchronized void setisSomeOneWait(boolean isWait) {
        isAnotherWaiting = isWait;
    }

    public synchronized boolean getIsSomeOneWait() {
        return isAnotherWaiting;
    }

}
