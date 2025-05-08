package homework_5;

interface PlaneBuilder {
    public void setWheels(int wheelsCounter);

    public void setType(PlaneType type);

    public void setEngine(Engine engine);

    public void setSeats(Seats seats);

    public void setSeatsCount(int seatcount);
}


interface Builable<T> {
    public T build();
}


interface Flyable {
    void fly();
}


abstract class Plane implements Flyable {
    protected Engine engine;
    protected Seats seats;
    protected PlaneType type;
    protected int wheels;
    protected int seatCounter;

    private int max_speed;
    private int max_height;
    private int wings;

    public enum Wings {
        ONE(1), TWO(2);

        private int count;

        Wings(int count) {
            this.count = count;
        }

        public int getWingsCount() {
            return count;
        }

    }

    public Plane() {
        this.wings = Wings.TWO.getWingsCount();
    }

    public void setMaxSpeed(int speed) {
        this.max_speed = speed;
    }

    public int getMaxSpeed() {
        return max_speed;
    }

    public void setMaxHeight(int max_height) {
        this.max_height = max_height;
    }

    public int getMaxHeight() {
        return max_height;
    }

    public void setWingsCount(Wings wings) {
        this.wings = wings.getWingsCount();
    }

    public int getWingsCount() {
        return wings;
    }

    public Engine getEngine() {
        return engine;
    }

    public Seats getSeats() {
        return seats;
    }

    public int getWheels() {
        return wheels;
    }

    public int getSeatCount() {
        return seatCounter;
    }

    @Override
    public String toString() {
        return "type : " + type + ", seats : " + seats + ", seat count : " + seatCounter
                + ", wheels : " + wheels + ", engine : " + engine;
    }
}


enum Engine {
    JET, TURBOJET, ROCKET;
}


enum Seats {
    COMFORT, ORDINARY, LUXURIOUS;
}


enum PlaneType {
    COMERCIAL, SPORT, PERSONAL, MILATARY;
}


class SportPlane extends Plane {
    public SportPlane(PlaneType type, Engine engine, Seats seats, int seatCount, int wheels) {
        this.wheels = wheels;
        this.seats = seats;
        this.engine = engine;
        this.type = type;
        this.seatCounter = seatCount;
    }


    @Override
    public void fly() {
        System.out.println("Sport plane is flying");
    }
};


class SportPlaneBuilder implements PlaneBuilder, Builable<SportPlane> {
    private Engine engine;
    private Seats seats;
    private PlaneType type;
    private int wheels;
    private int seatCount;

    @Override
    public void setWheels(int wheelsCounter) {
        this.wheels = wheelsCounter;
    }

    @Override
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void setSeats(Seats seats) {
        this.seats = seats;
    }

    @Override
    public void setType(PlaneType type) {
        this.type = type;
    }

    @Override
    public void setSeatsCount(int seatcount) {
        this.seatCount = seatcount;
    }

    public SportPlane build() {
        return new SportPlane(type, engine, seats, seatCount, wheels);
    }



}


class ComercialPlane extends Plane {
    public ComercialPlane(PlaneType type, Engine engine, Seats seats, int seatCount, int wheels) {
        this.wheels = wheels;
        this.seats = seats;
        this.engine = engine;
        this.type = type;
        this.seatCounter = seatCount;
    }

    @Override
    public void fly() {
        System.out.println("comercial plane is flying");
    }

}


class ComercialPlaneBuilder implements PlaneBuilder, Builable<ComercialPlane> {
    private Engine engine;
    private Seats seats;
    private PlaneType type;
    private int wheels;
    private int seatCount;

    @Override
    public void setWheels(int wheelsCounter) {
        this.wheels = wheelsCounter;
    }

    @Override
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void setSeats(Seats seats) {
        this.seats = seats;
    }

    @Override
    public void setType(PlaneType type) {
        this.type = type;
    }

    @Override
    public void setSeatsCount(int seatcount) {
        this.seatCount = seatcount;
    }

    public ComercialPlane build() {
        return new ComercialPlane(type, engine, seats, seatCount, wheels);
    }

}


class PlaneBuilderManager {
    public void createSportPlane(PlaneBuilder builder) {
        builder.setEngine(Engine.TURBOJET);
        builder.setSeats(Seats.COMFORT);
        builder.setSeatsCount(2);
        builder.setType(PlaneType.SPORT);
        builder.setWheels(3);
    };

    public void createComercialPlane(PlaneBuilder builder) {
        builder.setEngine(Engine.JET);
        builder.setSeats(Seats.ORDINARY);
        builder.setSeatsCount(220);
        builder.setType(PlaneType.COMERCIAL);
        builder.setWheels(6);
    }
}


public class BuilderDemo {
    public static void main(String[] args) {
        PlaneBuilderManager manager = new PlaneBuilderManager();

        SportPlaneBuilder sportBuilder = new SportPlaneBuilder();
        manager.createSportPlane(sportBuilder);
        SportPlane sportPlane = sportBuilder.build();

        System.out.println(sportPlane.toString());

        ComercialPlaneBuilder comercialBuilder = new ComercialPlaneBuilder();
        manager.createComercialPlane(comercialBuilder);
        ComercialPlane comercialPlane = comercialBuilder.build();

        System.out.println(comercialPlane.toString());
    }
}
