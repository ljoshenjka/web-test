package base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Placeholder to share objects between steps
 */
public class BaseStep {

    private static final ThreadLocal<Map<String, Object>> testsData = ThreadLocal.withInitial(HashMap::new);

    public static void saveData(String key, Object value) {
        testsData.get().put(key, value);
    }

    public static Object getData(String key) {
        return testsData.get().getOrDefault(key, "no data");
    }

    public static String getStringData(String key) {
        return testsData.get().getOrDefault(key, "no data").toString();
    }

    public static Boolean containsData(String key) {
        return testsData.get().containsKey(key);
    }
}
