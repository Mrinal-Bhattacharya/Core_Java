//****************************************************************
//* Copyright (c) 2015 Ford Motor Company. All Rights Reserved.
//****************************************************************
package puzzls;

public class Cordinate {

    private int x;
    private int y;

    protected Cordinate(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public void move(final char face) {
        final CardinalHelper cardinal = new CardinalHelper();
        if (cardinal.isNorthFacing(face)) {
            this.y++;
        } else if (cardinal.isSouthFacing(face)) {
            this.y--;
        } else if (cardinal.isEastFacing(face)) {
            this.x++;
        } else {
            this.x--;
        }
    }

    public String getCordinate() {
        return "X = " + this.x + " Y = " + this.y;
    }

}
