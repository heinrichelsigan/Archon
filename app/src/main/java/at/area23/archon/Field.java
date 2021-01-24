package at.area23.archon;

import android.renderscript.ScriptIntrinsicYuvToRGB;

public class Field {
    int y = -1;
    char x = '\0';
    String fieldName = null;

    public Field(char xColumn, int yRpw) {
        y = yRpw;
        x = xColumn;
        fieldName = String.valueOf(xColumn) + String.valueOf(yRpw);
    }

    public boolean IsValid() {
        boolean valid = false;
        for (char xCol: Constants.X) {
            if (x == xCol) {
                valid = true;
                break;
            }
        }
        if (valid) {
            for (int yRow: Constants.Y) {
                if (yRow == y) {
                    return true;
                }
            }
        }
        return false;
    }


}
