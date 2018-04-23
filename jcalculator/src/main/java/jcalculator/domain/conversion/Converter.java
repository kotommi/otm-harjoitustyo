package jcalculator.domain.conversion;

/**
 * Class for converting numbers from one base to another.
 *
 * @author tomko
 */
public class Converter {

    private Encoding from;
    private Encoding to;
    private Endian endian;

    /**
     * Default constructor that sets the values to the GUI defaults
     */
    public Converter() {
        this.from = Encoding.DECIMAL;
        this.to = Encoding.BINARY;
        this.endian = Endian.LITTLE;
    }

    /**
     * Sets endian.
     *
     * @param endian
     */
    public void setEndian(Endian endian) {
        this.endian = endian;
    }

    /**
     * Sets the starting encoding.
     *
     * @param from Value of the enum Encoding.
     */
    public void setFrom(Encoding from) {
        this.from = from;
    }

    /**
     * Sets the base to encode to.
     *
     * @param to Value of the enum Encoding.
     */
    public void setTo(Encoding to) {
        this.to = to;
    }

    /**
     * Converts a number from a base to another according to the settings.
     *
     * @param input A String representing a numerical value.
     * @return A String in the base represented by Encoding to.
     */
    public String convert(String input) {
        input = input.trim();
        switch (from) {
            case DECIMAL:
                return parseDecimal(input);
            case HEX:
                return parseHex(input);
            case BINARY:
                return parseBinary(input);
        }
        return input;
    }

    /**
     * Converts an int value to a String according to the Encoding to.
     *
     * @param input integer to convert.
     * @return String formatted to a given base.
     */
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

    /**
     * Parses a hexadecimal String to an int.
     *
     * @param input see convert().
     * @return
     */
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

    /**
     * Parses a decimal String to an int.
     *
     * @param input see convert().
     * @return
     */
    private String parseDecimal(String input) {
        int parse;
        try {
            parse = Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            return "NaN";
        }
        return convertInt(parse);
    }

    /**
     * Parses a binary String to an int.
     *
     * @param input see convert().
     * @return
     */
    private String parseBinary(String input) {
        int parse;
        try {
            parse = Integer.parseInt(input, 2);
        } catch (NumberFormatException e) {
            return "NaN";
        }
        return convertInt(parse);
    }

    /**
     * Flips the endianness of an int.
     *
     * @param i an int to convert.
     * @return An int in opposite endian format.
     */
    private int reverseEndian(int i) {
        return (i & 0xff) << 24 | (i & 0xff00) << 8 | (i & 0xff0000) >> 8 | (i >> 24) & 0xff;
    }

    /**
     * Getter for Encoding from.
     *
     * @return
     */
    public Encoding getFrom() {
        return from;
    }

    /**
     * Getter for Encoding to.
     *
     * @return
     */
    public Encoding getTo() {
        return to;
    }

    /**
     * Getter for Endian.
     *
     * @return
     */
    public Endian getEndian() {
        return endian;
    }

}