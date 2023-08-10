package nikschadowsky.engine.input;

import java.util.HashSet;

public class Input {
    public static final int KEY0 = 48;
    public static final int KEY1 = 49;
    public static final int KEY2 = 50;
    public static final int KEY3 = 51;
    public static final int KEY4 = 52;
    public static final int KEY5 = 53;
    public static final int KEY6 = 54;
    public static final int KEY7 = 55;
    public static final int KEY8 = 56;
    public static final int KEY9 = 57;
    public static final int A = 65;
    public static final int B = 66;
    public static final int C = 67;
    public static final int D = 68;
    public static final int E = 69;
    public static final int F = 70;
    public static final int G = 71;
    public static final int H = 72;
    public static final int I = 73;
    public static final int J = 74;
    public static final int K = 75;
    public static final int L = 76;
    public static final int M = 77;
    public static final int N = 78;
    public static final int O = 79;
    public static final int P = 80;
    public static final int Q = 81;
    public static final int R = 82;
    public static final int S = 83;
    public static final int T = 84;
    public static final int U = 85;
    public static final int V = 86;
    public static final int W = 87;
    public static final int X = 88;
    public static final int Y = 89;
    public static final int Z = 90;
    public static final int F1 = 112;
    public static final int F2 = 113;
    public static final int F3 = 114;
    public static final int F4 = 115;
    public static final int F5 = 116;
    public static final int F6 = 117;
    public static final int F7 = 118;
    public static final int F8 = 119;
    public static final int F9 = 120;
    public static final int F10 = 121;

    public static final int CIRCUMFLEX = 130;
    public static final int ACUTE = 129;
    public static final int PLUS = 521;
    public static final int NUMBER_SIGN = 520;
    public static final int MINUS = 45;
    public static final int PERIOD = 46;
    public static final int COMMA = 44;
    public static final int LESS_THAN = 153;

    public static final int TAB = 9;
    public static final int CAPS_LOCK = 20;
    public static final int SHIFT = 16;
    public static final int CTRL = 17;
    public static final int WINDOWS = 524;
    public static final int ALT = 18;
    public static final int SPACE = 32;
    public static final int CONTEXT_MENU = 525;
    public static final int ENTER = 10;
    public static final int BACKSPACE = 8;

    public static final int LEFT = 37;
    public static final int RIGHT = 39;
    public static final int UP = 38;
    public static final int DOWN = 40;

    public static final int INSERT = 155;
    public static final int HOME = 36;
    public static final int PAGE_UP = 33;
    public static final int PAGE_DOWN = 34;
    public static final int DELETE = 127;
    public static final int END = 35;
    public static final int PRINT_SCREEN = 154;
    public static final int PAUSE = 19;

    public static final int NUM0 = 96;
    public static final int NUM1 = 97;
    public static final int NUM2 = 98;
    public static final int NUM3 = 99;
    public static final int NUM4 = 100;
    public static final int NUM5 = 101;
    public static final int NUM6 = 102;
    public static final int NUM7 = 103;
    public static final int NUM8 = 104;
    public static final int NUM9 = 105;

    public static final int NUM_LOCK = 144;
    public static final int NUM_DIVIDE = 111;
    public static final int NUM_MULTIPLICATION = 106;
    public static final int NUM_MINUS = 109;
    public static final int NUM_PLUS = 107;

    public static final int MOUSE_OFFSET = 800;

    public static final int LEFT_CLICK = 801;
    public static final int MOUSE_WHEEL = 802;
    public static final int RIGHT_CLICK = 803;
    public static final int MB4 = 804;
    public static final int MB5 = 805;
    public static final int MB6 = 806;
    public static final int MB7 = 807;
    public static final int MB8 = 808;
    public static final int MB9 = 809;


    public static boolean isPressed(int identifier) {
        return pressed.contains(identifier);
    }

    public static boolean isReleased(int identifier) {
        return released.contains(identifier);
    }

    /**
     * S(ingle)A(ction)K(ey)P(ress) -> SAKP
     *
     * @return
     */
    public static boolean isSAKP(int identifier) {
        return !sakpWaiting.contains(identifier) && pressed.contains(identifier);
    }

    public static boolean isShift() {
        return pressed.contains(SHIFT);
    }

    public static boolean isAlt() {
        return pressed.contains(ALT);
    }

    public static boolean isCtrl() {
        return pressed.contains(CTRL);
    }

    public static boolean scrolledUp() {
        return mwClicks < 0;
    }

    public static boolean scrolledDown() {
        return mwClicks > 0;
    }

    public static void setMWClicks(int clicks) {
        mwClicks += clicks;
    }

    private static int mwClicks = 0;

    /**
     * @return the number of mouse wheel clicks, that occurred between now and the last {@link #update()} call.
     * @see java.awt.event.MouseWheelListener
     */
    public static int getMWClicks() {
        return mwClicks;
    }

    private static HashSet<Integer> pressed = new HashSet<>(), released = new HashSet<>(), sakpWaiting = new HashSet<>();

    public static void press(int identifier) {

        pressed.add(identifier);
    }

    public static void release(int identifier) {

        released.add(identifier);
        pressed.remove(identifier);
        sakpWaiting.remove(identifier);
    }

    public static void update() {

        released.clear();
        sakpWaiting.addAll(pressed);
        mwClicks = 0;
    }


}
