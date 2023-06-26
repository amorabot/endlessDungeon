package Enums;

public enum AsciiElements {

    BRICK_WALL("_|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|\n" +
            "___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|__\n" +
            "_|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|\n"),
    HEART("♥"),
    BOW("   (\n" +
            "    \\\n" +
            "     )\n" +
            "##-------->\n" +
            "     )\n" +
            "    /\n" +
            "   ("),
    BOTTLE("  (-)  \n"+
            ".-'-'-.\n"+
            "|-...-|      #\n"+
            "|;:.._|\n"+
            "`-...-'\n"),
    SWORD("      /| ________________\n" +
            "O|===|* >________________/    #\n" +
            "      \\|"),
    DAMAGE("⚔");

    final String value;

    AsciiElements(String asciiValue){
        this.value = asciiValue;
    }

    public String getValue(){
        return value;
    }
}
