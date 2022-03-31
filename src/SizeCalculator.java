import java.util.HashMap;

public class SizeCalculator {

    private static final Character[] srt = {'B', 'K', 'M', 'G', 'T'};
    private static final HashMap<Character, Integer> char2multiplier = getMultipliers();

    public static String getFromHumanReadableSize(long length) {

        int i = 0;
        double result = (double) length;
        while (result > 1000.0) {
            if (i == srt.length) break;
            result = result / 1024;
            i++;
        }
        return String.format("%.2f " + srt[i] + (i > 0 ? "b" : ""), result);
    }

    public static long getSizeFromHumanReadable(String size) {
        char sizeFactor = size
                .replaceAll("[0-9\\s+]+", "")
                .charAt(0);
        int multiplier = char2multiplier.get(sizeFactor);
        return multiplier * Long.parseLong(
                size.replaceAll("[^0-9]", "")
        );
    }

    private static HashMap<Character, Integer> getMultipliers() {
        HashMap<Character, Integer> char2multiplier = new HashMap<>();
        for (int i = 0; i < srt.length; i++) {
            char2multiplier.put(
                    srt[i],
                    (int) Math.pow(1024, i)
            );
        }
        return char2multiplier;
    }
}
