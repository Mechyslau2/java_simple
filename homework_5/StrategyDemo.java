package homework_5;

import java.util.HashMap;

interface RenderStrategy {
    void renderContent();
}


class UserStrategy implements RenderStrategy {

    @Override
    public void renderContent() {
        System.out.println("User strategy");
    }

}


class AdminStrategy implements RenderStrategy {

    @Override
    public void renderContent() {
        System.out.println("Admin strategy");
    }

}



class RenderStrategyContent {
    private RenderStrategy strategy;
    private HashMap<String, RenderStrategy> map = new HashMap<>();


    public boolean setStrategy(String key, RenderStrategy strategy) {
        if (key == null || strategy == null) {
            throw new NullPointerException();
        }

        return map.put(key, strategy) != null;
    }

    public boolean useStrategy(String role) {
        strategy = map.get(role);
        return strategy != null;
    }


    public void render() {
        if (strategy != null) {
            strategy.renderContent();
        }
    }
}


enum Roles {
    USER("user"), ADIMN("admin");

    private String role;

    private Roles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}


public class StrategyDemo {
    public static void main(String[] args) throws NullPointerException {
        RenderStrategyContent content = new RenderStrategyContent();

        content.setStrategy(Roles.ADIMN.getRole(), new AdminStrategy());
        content.setStrategy(Roles.USER.getRole(), new UserStrategy());

        content.useStrategy(Roles.ADIMN.getRole());

        content.render();

        content.useStrategy(Roles.USER.getRole());
        content.render();
    }
}
