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
    private Endian endianTo;

    /**
     * Default constructor that sets the values to the GUI defaults.
     */
    public Converter() {
        this.from = Encoding.DECIMAL;
        this.to = Encoding.BINARY;
        this.endian = Endian.LITTLE;
        this.endianTo = Endian.LITTLE;
    }

    /**
     * Sets endianness of input.
     *
     * @param endian enum Endian.
     */
    public void setEndian(Endian endian) {
        this.endian = endian;
    }

    /**
     * Sets the endianness of output.
     *
     * @param endian enum Endian.
     */
    public void setEndianTo(Endian endian) {
        this.endianTo = endian;
    }

    /**
     * Sets the input encoding.
     *
     * @param from Value of the enum Encoding.
     */
    public void setFrom(Encoding from) {
        this.from = from;
    }

    /**
     * Sets the output format to encode to.
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
        input = handleEndians(input);
        switch (to) {
            case DECIMAL:
                return Integer.toString(input);
            case HEX:
                return Integer.toHexString(input);
            case BINARY:
                String ret = Integer.toBinaryString(input);
                if (endianTo == Endian.BIG && input > 0) {
                    ret = appendZeros(ret);
                }
                return ret;
        }
        return "";
    }

    /**
     * Handles the output endianness.
     *
     * @param input an int to possibly convert
     * @return converted int
     */
    private int handleEndians(int input) {
        if (endian == Endian.BIG && endianTo == Endian.BIG) {
            input = reverseEndian(input);
        } else if (endian == Endian.LITTLE && endianTo == Endian.BIG) {
            input = reverseEndian(input);
        }
        return input;
    }

    /**
     * Makes Big-endian results prettier by adding tailing zeros.
     *
     * @param ret A string to modify
     * @return a binary-String
     */
    private String appendZeros(String ret) {
        StringBuilder sb = new StringBuilder(ret);
        while (sb.length() < 31) {
            sb.append("0");
        }
        return sb.toString();
    }

    /**
     * Parses a hexadecimal String to an int.
     *
     * @param input see convert().
     * @return
     */
    private String parseHex(String input) {
        input = appendHex(input);
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
        if (parse < 0) {
            return "Negative";
        }
        return convertInt(parse);
    }

    /**
     * fixes user input hex-Strings.
     *
     * @param input a Hex-String
     * @return a HexString with zeros in the end.
     */
    private String appendHex(String input) {
        if (endian == Endian.BIG) {
            StringBuilder sb = new StringBuilder(input);
            while (sb.length() < 7) {
                sb.append("0");
            }
            input = sb.toString();
            return input;
        }
        return input;
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
        if (parse < 0) {
            return "Negative";
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
        if (endian == Endian.BIG) {
            StringBuilder sb = new StringBuilder(input);
            while (sb.length() < 31) {
                sb.append("0");
            }
            input = sb.toString();
        }
        int parse;
        try {
            parse = Integer.parseInt(input, 2);
        } catch (NumberFormatException e) {
            return "NaN";
        }
        if (parse < 0) {
            return "Negative";
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
     * @return enum Encoding.
     */
    public Encoding getFrom() {
        return from;
    }

    /**
     * Getter for Encoding to.
     *
     * @return enum Encoding.
     */
    public Encoding getTo() {
        return to;
    }

    /**
     * Getter for Endian.
     *
     * @return enum Endian.
     */
    public Endian getEndian() {
        return endian;
    }

    /**
     * Getter for output Endian.
     *
     * @return enum Endian
     */
    public Endian getEndianTo() {
        return endianTo;
    }

}
