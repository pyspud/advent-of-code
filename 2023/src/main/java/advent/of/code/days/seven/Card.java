package advent.of.code.days.seven;

public enum Card {
    L2, L3, L4, L5, L6, L7, L8, L9, T, J, Q, K, A;

    public static Card of(char label) {
        return switch(label){
            case 'A', 'K', 'Q', 'J', 'T' -> valueOf(""+label);
            case 'a', 'k', 'q', 'j', 't' -> valueOf((""+label).toUpperCase());
            case '9', '8', '7', '6', '5', '4', '3', '2' -> valueOf("L" + label);
            default -> throw new IllegalArgumentException("Unknown card label "+label);
        };
    }
}
