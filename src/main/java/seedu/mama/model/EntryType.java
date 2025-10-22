package seedu.mama.model;

import java.util.function.Function;

/**
 * Defines the valid types of entries, their classes, and their storage parsing logic.
 */
public enum EntryType {
    MEAL(MealEntry::fromStorage),
    WORKOUT(WorkoutEntry::fromStorage),
    MILK(MilkEntry::fromStorage),
    WEIGHT(WeightEntry::fromStorage),
    NOTE(NoteEntry::fromStorage);

    // This is a function that takes a String and returns an Entry.
    private final Function<String, Entry> storageParser;

    EntryType(Function<String, Entry> storageParser) {
        this.storageParser = storageParser;
    }

    /**
     * Parses a line from storage using the correct method for this entry type.
     * @param line The string line from the storage file.
     * @return The parsed Entry object.
     */
    public Entry parseFromStorage(String line) {
        return this.storageParser.apply(line);
    }

    /**
     * Returns a comma-separated list of all valid type names.
     */
    public static String getValidTypesString() {
        return String.join(", ",
                java.util.Arrays.stream(EntryType.values())
                        .map(et -> et.name().toLowerCase())
                        .toArray(String[]::new));
    }
}
