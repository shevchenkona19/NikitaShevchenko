package sheva.newsprovider.utils;

/**
 * Created by shevc on 16.04.2017.
 * x
 */

public class PositionUtils {
    public static int[] resolvePosition(int position) {
        switch (position) {
            case 2:
                return new int[]{0,0};
            case 3:
                return new int[]{0,1};
            case 4:
                return new int[]{0,2};
            case 7:
                return new int[]{1,0};
            case 8:
                return new int[]{1,1};
            case 9:
                return new int[]{1,2};
            case 12:
                return new int[]{2,0};
            case 13:
                return new int[]{2,1};
            case 14:
                return new int[]{2,2};
            case 17:
                return new int[]{3,0};
            case 18:
                return new int[]{3,1};
            case 19:
                return new int[]{3,2};
            case 22:
                return new int[]{4,0};
            case 23:
                return new int[]{4,1};
            case 24:
                return new int[]{4,2};
            case 27:
                return new int[]{5,0};
            case 28:
                return new int[]{5,1};
            case 29:
                return new int[]{5,2};
            case 32:
                return new int[]{6,0};
            case 33:
                return new int[]{6,1};
            case 34:
                return new int[]{6,2};
            case 37:
                return new int[]{7,0};
            case 38:
                return new int[]{7,1};
            case 39:
                return new int[]{7,2};
            case 42:
                return new int[]{8,0};
            case 43:
                return new int[]{8,1};
            case 44:
                return new int[]{8,2};
            case 47:
                return new int[]{9,0};
            case 48:
                return new int[]{9,1};
            case 49:
                return new int[]{9,2};
            case 52:
                return new int[]{10,0};
            case 53:
                return new int[]{10,1};
            case 54:
                return new int[]{10,2};
            case 57:
                return new int[]{11,0};
            case 58:
                return new int[]{11,1};
            case 59:
                return new int[]{11,2};
            case 1:
                return new int[]{0};
            case 6:
                return new int[]{1};
            case 11:
                return new int[]{2};
            case 16:
                return new int[]{3};
            case 21:
                return new int[]{4};
            case 26:
                return new int[]{5};
            case 31:
                return new int[]{6};
            case 36:
                return new int[]{7};
            case 41:
                return new int[]{8};
            case 46:
                return new int[]{9};
            case 51:
                return new int[]{10};
            case 56:
                return new int[]{11};
            default:
                return null;
        }
    }
}
