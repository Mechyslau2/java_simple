package homework_5;

interface Tradable {
    public double getPrice();

    public void setPrice(double newPrice);
}


class Product implements Tradable {
    private static final double MIN_PRICE = 1.0;
    private Double price;

    public Product() {
        price = MIN_PRICE;
    };

    public Product(double price) {
        this.price = price;
    }


    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double newPrice) {
        this.price = newPrice;
    }
}


class DiscounterDecorator implements Tradable {
    private Tradable tradeThing;
    private double discountPercent = 1.0;

    public DiscounterDecorator(Tradable tradeThing) {
        if (tradeThing == null) {
            throw new NullPointerException();
        }
        this.tradeThing = tradeThing;
    }

    public DiscounterDecorator(Tradable trade, double discountPercent) {
        this(trade);
        this.discountPercent = discountPercent;
    }

    public void setDiscount(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public double getDiscount() {
        return discountPercent;
    }

    private double countNewPrice(double price) {
        double result = price - (price * discountPercent);
        return result;
    }

    @Override
    public double getPrice() {
        return tradeThing.getPrice() - (tradeThing.getPrice() * discountPercent);
    }

    @Override
    public void setPrice(double newPrice) {
        tradeThing.setPrice(countNewPrice(newPrice));
    }

}


public class DecoratorDemo {
    public static void main(String[] args) {
        Tradable product = new Product(100.0);
        System.out.println(product.getPrice());
        product = new DiscounterDecorator(product, 0.3);
        System.out.println(product.getPrice());

    }
}
