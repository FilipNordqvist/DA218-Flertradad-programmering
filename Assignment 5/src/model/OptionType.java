package model;

public enum OptionType {
    Add,
    Update,
    Delete,
    Search_by_name,
    ListAll;

    public static String[] names() {
        OptionType[] options = values();
        String[] names = new String[options.length];

        for (int i = 0; i < options.length; i++) {
            names[i] = options[i].name();
        }

        return names;
    }

    public static OptionType getValue(int index) {
        OptionType[] options = values();
        for (int i = 0; i < options.length; i++) {
            if (options[i].ordinal() == index)
                return options[i];
        }

        return null;
    }
}