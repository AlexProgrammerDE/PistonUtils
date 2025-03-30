package net.pistonmaster.pistonutils.update;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Arrays;

@Getter
@EqualsAndHashCode
public class SemanticVersion {
    // Requires at least three parts
    // Filled with zeros at the end if needed
    private final int[] version;

    public SemanticVersion(int[] version) {
        this.version = fillZerosIfNeeded(version);
    }

    private static int[] fillZerosIfNeeded(int[] version) {
        int[] newVersion = new int[Math.max(3, version.length)];
        System.arraycopy(version, 0, newVersion, 0, version.length);
        return newVersion;
    }

    public SemanticVersion(int major, int minor, int patch) {
        this(new int[]{major, minor, patch});
    }

    public static SemanticVersion fromString(String version) {
        int firstDigit = 0;
        for (int i = 0; i < version.length(); i++) {
            if (Character.isDigit(version.charAt(i))) {
                firstDigit = i;
                break;
            }
        }

        int lastDigit = version.length();
        for (int i = version.length() - 1; i >= 0; i--) {
            if (Character.isDigit(version.charAt(i))) {
                lastDigit = i;
                break;
            }
        }

        version = version.substring(firstDigit, lastDigit + 1);

        String[] split = version.split("\\.");
        return new SemanticVersion(Arrays.stream(split).mapToInt(Integer::parseInt).toArray());
    }

    public boolean isNewerThan(SemanticVersion otherVersion) {
        int i = 0;
        for (int version : version) {
            if (i == otherVersion.version.length) {
                return true;
            }

            int otherVersionPart = otherVersion.version[i];
            if (version > otherVersionPart) {
                return true;
            } else if (version < otherVersionPart) {
                return false;
            }

            i++;
        }

        return false;
    }

    public boolean isOlderThan(SemanticVersion otherVersion) {
        return !isNewerThan(otherVersion) && !equals(otherVersion);
    }

    @Override
    public String toString() {
        return String.join(".", Arrays.stream(version)
                .mapToObj(String::valueOf)
                .toArray(String[]::new));
    }
}
