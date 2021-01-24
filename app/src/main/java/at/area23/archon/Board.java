package at.area23.archon;

public class Board {
    Field[] fields = new Field[81];
    int idx = -1;

    public Board() {
        idx = 0;
        for (char xCol: Constants.X) {
            for (int yRow: Constants.Y) {
                fields[idx++] = new Field(xCol, yRow);
            }
        }
    }


}
