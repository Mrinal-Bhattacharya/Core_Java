//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package puzzls;

/**
 * TODO - Place class description here
 */
public class MarsProblem {

    private char facing;
    private Cordinate cordinate;
    private Rotation rotation;

    public MarsProblem(final int x, final int y, final char facing) {
        this.cordinate = new Cordinate(x, y);
        this.rotation = new Rotation();
        this.facing = facing;
    }

    public static void main(final String[] args) {
        final MarsProblem marsProblem = new MarsProblem(1, 2, 'N');
        // marsProblem.setCurrentPosition();
        marsProblem.processCommands("LMLMLMLMM");
        System.out.println(marsProblem.getCurrentCordinate());
    }

    private void processCommands(final String commands) {
        for (int i = 0; i < commands.length(); i++) {
            final char command = commands.charAt(i);
            if (shouldRotate(command)) {
                rotateFace(command);
                continue;
            }
            moveCordinateBasedOnFace();
        }

    }

    private void rotateFace(final char command) {
        this.facing =
                this.rotation.rotateFaceBasedOnCommand(command, this.facing);
    }

    private void moveCordinateBasedOnFace() {
        this.cordinate.move(this.facing);
    }

    private boolean shouldRotate(final char command) {
        return command == 'L' || command == 'R';
    }

    public String getCurrentCordinate() {
        return this.cordinate.getCordinate();
    }

}
