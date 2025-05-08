package homework_5;

import java.util.Date;

interface SourceAI {
    public SourceA getSource();

    public String getValue();
}


interface SourceBI {
    public SourceB getSource();

    public String getValue();
}


class SourceA implements SourceAI {
    private Date date;
    private String value;

    public SourceA(Date date, String value) {
        if (date == null || value == null) {
            throw new NullPointerException();
        }
        this.date = date;
        this.value = value;
    }


    @Override
    public String getValue() {
        return value;
    }

    @Override
    public SourceA getSource() {
        return new SourceA(date, value);
    }

    @Override
    public String toString() {
        return "date : " + date.getTime() + ", value : " + value;
    }
}


class SourceB implements SourceBI {
    private int id;
    private String value;

    public SourceB(int id, String value) {
        if (value == null) {
            throw new NullPointerException();
        }
        this.id = id;
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public SourceB getSource() {
        return new SourceB(id, value);
    }

    @Override
    public String toString() {
        return "id : " + id + ", value : " + value;
    }
}


class SimpleAdapterB implements SourceAI {
    private SourceBI source;

    public SimpleAdapterB(SourceBI source) {
        this.source = source;
    }

    @Override
    public SourceA getSource() {
        return new SourceA(new Date(), source.getValue());
    }

    @Override
    public String getValue() {
        return source.getValue();
    }
}


class SimpleAdapterA implements SourceBI {
    private SourceAI source;
    private static int count = 0;

    public SimpleAdapterA(SourceAI source) {
        this.source = source;
    }

    @Override
    public SourceB getSource() {
        return new SourceB(SimpleAdapterA.count++, source.getValue());
    }

    @Override
    public String getValue() {
        return source.getValue();
    }
}


public class AdapterDemo {
    public static void main(String[] args) {
        SourceAI source1 = new SourceA(new Date(), "simple value for A");
        SourceBI source2 = new SourceB(20, "simple value for B");

        System.out.println("source 1 : " + source1.toString());
        System.out.println("source 2 : " + source2.toString());

        SourceBI test1 = new SimpleAdapterA(source1);
        SourceAI test2 = new SimpleAdapterB(source2);

        System.out.println("simple adapter test1 : " + test1.getSource().toString());
        System.err.println("simple adapter test2 : " + test2.getSource().toString());
    }
}
