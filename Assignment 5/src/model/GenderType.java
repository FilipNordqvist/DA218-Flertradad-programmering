package model;

public enum GenderType {
    Male,
    Female,
    Unknown;

    public static String[] names() {
        GenderType[] gender = values();
        String[] names = new String[gender.length];

        for (int i = 0; i < gender.length; i++) {
            names[i] = gender[i].name();
        }

        return names;
    }

    public static GenderType getValue(int index) {
        GenderType[] gender = values();
        for (int i = 0; i < gender.length; i++) {
            if (gender[i].ordinal() == index)
                return gender[i];
        }

        return null;
    }
}
