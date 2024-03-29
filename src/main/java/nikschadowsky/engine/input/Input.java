package nikschadowsky.engine.input;

/**
 * Constants for the various input events:
 * <ul>
 *     <li>PRESSED: a key or a mouse button has been pressed down</li>
 *     <li>RELEASED: a key or a mouse button has been released</li>
 *     <li>TYPED: a key or a mouse button has been pressed down and returned a valid unicode character</li>
 *     <li>CLICKED: a key or a mouse button has been pressed and released fairly quickly</li>
 *     <li>ENTERED: the cursor has entered the component</li>
 *     <li>EXITED: the cursor has left the component</li>
 *     <li>SCROLLED: the mouse wheel has been turned</li>
 * </ul>
 * <p>
 * This class also holds constants for regular ASCII keycodes as defined in {@link java.awt.event.KeyEvent}
 */
public enum Input {
    PRESSED, RELEASED, TYPED, CLICKED, ENTERED, EXITED, SCROLLED;
    public static final int UNDEFINED = 0;
    public static final int CANCEL = 3;
    public static final int BACK_SPACE = 8;
    public static final int TAB = 9;
    public static final int ENTER = 10;
    public static final int CLEAR = 12;
    public static final int SHIFT = 16;
    public static final int CONTROL = 17;
    public static final int ALT = 18;
    public static final int PAUSE = 19;
    public static final int CAPS_LOCK = 20;
    public static final int KANA = 21;
    public static final int FINAL = 24;
    public static final int KANJI = 25;
    public static final int ESCAPE = 27;
    public static final int CONVERT = 28;
    public static final int NONCONVERT = 29;
    public static final int ACCEPT = 30;
    public static final int MODECHANGE = 31;
    public static final int SPACE = 32;
    public static final int PAGE_UP = 33;
    public static final int PAGE_DOWN = 34;
    public static final int END = 35;
    public static final int HOME = 36;
    public static final int LEFT = 37;
    public static final int UP = 38;
    public static final int RIGHT = 39;
    public static final int DOWN = 40;
    public static final int COMMA = 44;
    public static final int MINUS = 45;
    public static final int PERIOD = 46;
    public static final int SLASH = 47;
    public static final int KEY_0 = 48;
    public static final int KEY_1 = 49;
    public static final int KEY_2 = 50;
    public static final int KEY_3 = 51;
    public static final int KEY_4 = 52;
    public static final int KEY_5 = 53;
    public static final int KEY_6 = 54;
    public static final int KEY_7 = 55;
    public static final int KEY_8 = 56;
    public static final int KEY_9 = 57;
    public static final int SEMICOLON = 59;
    public static final int EQUALS = 61;
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
    public static final int OPEN_BRACKET = 91;
    public static final int BACK_SLASH = 92;
    public static final int CLOSE_BRACKET = 93;
    public static final int NUMPAD0 = 96;
    public static final int NUMPAD1 = 97;
    public static final int NUMPAD2 = 98;
    public static final int NUMPAD3 = 99;
    public static final int NUMPAD4 = 100;
    public static final int NUMPAD5 = 101;
    public static final int NUMPAD6 = 102;
    public static final int NUMPAD7 = 103;
    public static final int NUMPAD8 = 104;
    public static final int NUMPAD9 = 105;
    public static final int MULTIPLY = 106;
    public static final int ADD = 107;
    public static final int SEPARATOR = 108;
    public static final int SUBTRACT = 109;
    public static final int DECIMAL = 110;
    public static final int DIVIDE = 111;
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
    public static final int F11 = 122;
    public static final int F12 = 123;
    public static final int DELETE = 127;
    public static final int DEAD_GRAVE = 128;
    public static final int DEAD_ACUTE = 129;
    public static final int DEAD_CIRCUMFLEX = 130;
    public static final int DEAD_TILDE = 131;
    public static final int DEAD_MACRON = 132;
    public static final int DEAD_BREVE = 133;
    public static final int DEAD_ABOVEDOT = 134;
    public static final int DEAD_DIAERESIS = 135;
    public static final int DEAD_ABOVERING = 136;
    public static final int DEAD_DOUBLEACUTE = 137;
    public static final int DEAD_CARON = 138;
    public static final int DEAD_CEDILLA = 139;
    public static final int DEAD_OGONEK = 140;
    public static final int DEAD_IOTA = 141;
    public static final int DEAD_VOICED_SOUND = 142;
    public static final int DEAD_SEMIVOICED_SOUND = 143;
    public static final int NUM_LOCK = 144;
    public static final int SCROLL_LOCK = 145;
    public static final int AMPERSAND = 150;
    public static final int ASTERISK = 151;
    public static final int QUOTEDBL = 152;
    public static final int LESS = 153;
    public static final int PRINTSCREEN = 154;
    public static final int INSERT = 155;
    public static final int HELP = 156;
    public static final int META = 157;
    public static final int GREATER = 160;
    public static final int BRACELEFT = 161;
    public static final int BRACERIGHT = 162;
    public static final int BACK_QUOTE = 192;
    public static final int QUOTE = 222;
    public static final int KP_UP = 224;
    public static final int KP_DOWN = 225;
    public static final int KP_LEFT = 226;
    public static final int KP_RIGHT = 227;
    public static final int ALPHANUMERIC = 240;
    public static final int KATAKANA = 241;
    public static final int HIRAGANA = 242;
    public static final int FULL_WIDTH = 243;
    public static final int HALF_WIDTH = 244;
    public static final int ROMAN_CHARACTERS = 245;
    public static final int ALL_CANDIDATES = 256;
    public static final int PREVIOUS_CANDIDATE = 257;
    public static final int CODE_INPUT = 258;
    public static final int JAPANESE_KATAKANA = 259;
    public static final int JAPANESE_HIRAGANA = 260;
    public static final int JAPANESE_ROMAN = 261;
    public static final int KANA_LOCK = 262;
    public static final int INPUT_METHOD_ON_OFF = 263;
    public static final int AT = 512;
    public static final int COLON = 513;
    public static final int CIRCUMFLEX = 514;
    public static final int DOLLAR = 515;
    public static final int EURO_SIGN = 516;
    public static final int EXCLAMATION_MARK = 517;
    public static final int INVERTED_EXCLAMATION_MARK = 518;
    public static final int LEFT_PARENTHESIS = 519;
    public static final int NUMBER_SIGN = 520;
    public static final int PLUS = 521;
    public static final int RIGHT_PARENTHESIS = 522;
    public static final int UNDERSCORE = 523;
    public static final int WINDOWS = 524;
    public static final int CONTEXT_MENU = 525;
    public static final int F13 = 61440;
    public static final int F14 = 61441;
    public static final int F15 = 61442;
    public static final int F16 = 61443;
    public static final int F17 = 61444;
    public static final int F18 = 61445;
    public static final int F19 = 61446;
    public static final int F20 = 61447;
    public static final int F21 = 61448;
    public static final int F22 = 61449;
    public static final int F23 = 61450;
    public static final int F24 = 61451;
    public static final int COMPOSE = 65312;
    public static final int BEGIN = 65368;
    public static final int ALT_GRAPH = 65406;
    public static final int STOP = 65480;
    public static final int AGAIN = 65481;
    public static final int PROPS = 65482;
    public static final int UNDO = 65483;
    public static final int COPY = 65485;
    public static final int PASTE = 65487;
    public static final int FIND = 65488;
    public static final int CUT = 65489;

    // Mouse Inputs
    public static final int LEFT_CLICK = 801;
    public static final int MOUSE_WHEEL = 802;
    public static final int RIGHT_CLICK = 803;
    public static final int MB4 = 804;
    public static final int MB5 = 805;
    public static final int MB6 = 806;
    public static final int MB7 = 807;
    public static final int MB8 = 808;
    public static final int MB9 = 809;

    public static int getMouseInputOffset() {
        return 800;
    }
}
