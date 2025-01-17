package com.codecool.termlib;

public class Terminal {
    /**
     * The beginning of control sequences.
     */
    // HINT: In \033 the '0' means it's an octal number. And 33 in octal equals 0x1B in hexadecimal.
    // Now you have some info to decode that page where the control codes are explained ;)
    private static final String CONTROL_CODE = "\033[";
    /**
     * Command for whole screen clearing.
     *
     * Might be partitioned if needed.
     */
    private static final String CLEAR = "2J";
    /**
     * Command for moving the cursor.
     */
    private static final String MOVE = "H";
    /**
     * Command for printing style settings.
     *
     * Handles foreground color, background color, and any other
     * styles, for example color brightness, or underlines.
     */
    private static final String STYLE = "m";

    /**
     *
     * Reset printing rules in effect to terminal defaults.
     *
     * Reset the color, background color, and any other style
     * (i.e.: underlined, dim, bright) to the terminal defaults.
     */
    public void resetStyle() {
        System.out.print(CONTROL_CODE + Attribute.valueOf("RESET").ordinal() + STYLE);
    }

    /**
     * Clear the whole screen.
     *
     * Might reset cursor position.
     */
    public void clearScreen() {
        System.out.print(CONTROL_CODE + CLEAR);
        moveTo(1, 5);
    }

    /**
     * Move cursor to the given position.
     *
     * Psitions are counted from one.  Cursor position 1,1 is at
     * the top left corner of the screen.
     *
     * @param x Column number.
     * @param y Row number.
     */
    public void moveTo(Integer x, Integer y) {
        System.out.print(CONTROL_CODE + y + ";" + x + MOVE);
    }

    /**
     * Set the foreground printing color.
     *
     * Already printed text is not affected.
     *
     * @param color The color to set.
     */
    public void setColor(Color color) {
        System.out.print(CONTROL_CODE + (30+color.ordinal()) + STYLE);
    }

    /**
     * Set the background printing color.
     *
     * Already printed text is not affected.
     *
     * @param color The background color to set.
     */
    public void setBgColor(Color color) {

        System.out.print(CONTROL_CODE + (40+color.ordinal()) + STYLE);
    }

    /**
     * Make printed text underlined.
     *
     * On some terminals this might produce slanted text instead of
     * underlined.  Cannot be turned off without turning off colors as
     * well.
     */
    public void setUnderline() {
        System.out.println(CONTROL_CODE+ "4m");
    }

    public void setDim() {
        System.out.println(CONTROL_CODE+ "2m");
    }

    /**
     * Move the cursor relatively.
     *
     * Move the cursor amount from its current position in the given
     * direction.
     *
     * @param direction Step the cursor in this direction.
     * @param amount Step the cursor this many times.
     */
    public void moveCursor(Direction direction, Integer amount) {

        switch(direction) {
            case UP:
                System.out.println(CONTROL_CODE + amount + "A");
                break;
            case DOWN:
                System.out.println(CONTROL_CODE + amount + "B");
                break;
            case FORWARD:
                System.out.println(CONTROL_CODE + amount + "C");
                break;
            case BACKWARD:
                System.out.println(CONTROL_CODE + amount + "D");
                break;
        }
    }

    /**
     * Set the character diplayed under the current cursor position.
     *
     * The actual cursor position after calling this method is the
     * same as beforehand.  This method is useful for drawing shapes
     * (for example frame borders) with cursor movement.
     *
     * @param c the literal character to set for the current cursor
     * position.
     */
    public void setChar(char c) {
        moveCursor(Direction.BACKWARD,1);
        System.out.print(c);
    }

    /**
     * Helper function for sending commands to the terminal.
     *
     * The common parts of different commands shall be assembled here.
     * The actual printing shall be handled from this command.
     *
     * @param commandString The unique part of a command sequence.
     */


    public void hideString() {
        System.out.print("\033[2K");
    }

    private void command(String commandString) {


    }

}
