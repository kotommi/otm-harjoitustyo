package jcalculator.domain.conversion;

public class Converter {
    private Encoding from;
    private Encoding to;
    private Endian endian;

    public Converter() {
        this.from = Encoding.DECIMAL;
        this.to = Encoding.BINARY;
        this.endian = Endian.LITTLE;
    }

    public void setEndian(Endian endian) {
        this.endian = endian;
    }

    public void setFrom(Encoding from) {
        this.from = from;
    }

    public void setTo(Encoding to) {
        this.to = to;
    }

    public String convert(String input) {
        input = input.trim();
        switch (from) {
            case DECIMAL:
                return parseDecimal(input);
            case HEX:
                return parseHex(input);
            case BINARY:
                break;
        }
        return input;
    }

    private String convertInt(int input) {
        if (endian == Endian.BIG) {
            input = reverseEndian(input);
        }
        switch (to) {
            case DECIMAL:
                return Integer.toString(input);
            case HEX:
                return Integer.toHexString(input);
            case BINARY:
                return Integer.toBinaryString(input);
        }
        return "";
    }

    private String parseHex(String input) {
        int parse;
        try {
            parse = Integer.decode(input);
        } catch (NumberFormatException e) {
            try {
                parse = Integer.parseInt(input, 16);
            } catch (NumberFormatException ex) {
                return "NaN";
            }
        }
        return convertInt(parse);
    }

    private String parseDecimal(String input) {
        int parse;
        try {
            parse = Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            return "NaN";
        }
        return convertInt(parse);
    }

    private int reverseEndian(int i) {
        return (i & 0xff) << 24 | (i & 0xff00) << 8 | (i & 0xff0000) >> 8 | (i >> 24) & 0xff;
    }

    public Encoding getFrom() {
        return from;
    }

    public Encoding getTo() {
        return to;
    }

    public Endian getEndian() {
        return endian;
    }
}
