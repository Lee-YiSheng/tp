package seedu.mama.model;

import java.util.Objects;

/**
 * Base type for all entries (Meal, Pump, Weight, Workout, Note, ...).
 */
public abstract class Entry {
    private final String type;         // e.g., MEAL, NOTE
    private final String description;  // human-readable

    protected Entry(String type, String description) {
        this.type = Objects.requireNonNull(type);
        this.description = Objects.requireNonNull(description);
    }

    public String type() {
        return type;
    }

    public String description() {
        return description;
    }

    /**
     * One-line string for previews/lists.
     */
    public String toListLine() {
        return "[" + type + "] " + description;
    }

    public Boolean contains(String keyword) {
        return this.description().contains(keyword);
    }

    /**
     * Stable storage form; subclasses may append fields.
     */
    public abstract String toStorageString();

    /**
     * Factory from storage line; dispatches to the correct parser via the EntryType enum.
     */
    public static Entry fromStorageString(String line) {
        String[] parts = line.split("\\|", 2); // TYPE|payload
        if (parts.length < 2) {
            throw new IllegalArgumentException("Bad storage line: " + line);
        }

        String typeToken = parts[0];
        try {
            // Use the enum to find the correct type and call its parsing function.
            EntryType entryType = EntryType.valueOf(typeToken);
            return entryType.parseFromStorage(line);
        } catch (IllegalArgumentException e) {
            // This runs if the typeToken doesn't match any enum constant.
            throw new IllegalArgumentException("Unknown entry type in storage file: " + typeToken);
        }
    }
}
