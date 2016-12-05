//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package puzzls;

public class Rotation {

    public char rotateFaceBasedOnCommand(final char command, final char face) {
        if (command == 'L') {
            return rotateLeft(face);
        }
        return rotateRight(face);

    }

    private char rotateRight(final char face) {
        final CardinalHelper cardinal = new CardinalHelper();
        if (cardinal.isNorthFacing(face)) {
            return 'E';
        }
        if (cardinal.isSouthFacing(face)) {
            return 'W';
        }
        if (cardinal.isEastFacing(face)) {
            return 'S';
        }
        return 'N';

    }

    private char rotateLeft(final char face) {
        final CardinalHelper cardinal = new CardinalHelper();
        if (cardinal.isNorthFacing(face)) {
            return 'W';
        }
        if (cardinal.isSouthFacing(face)) {
            return 'E';
        }
        if (cardinal.isEastFacing(face)) {
            return 'N';
        }
        return 'S';
    }

}
