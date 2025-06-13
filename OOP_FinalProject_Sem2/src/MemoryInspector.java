import java.lang.reflect.*;
import java.util.*;

public class MemoryInspector {

    private static final Map<Class<?>, Integer> primitiveSizes = Map.of(
            boolean.class, 1,
            byte.class, 1,
            char.class, 2,
            short.class, 2,
            int.class, 4,
            float.class, 4,
            long.class, 8,
            double.class, 8
    );

    private static final int OBJECT_HEADER_SIZE = 16; // rough estimate in bytes

    // Used to avoid infinite recursion in deep size
    private static final Set<Object> visited = Collections.newSetFromMap(new IdentityHashMap<>());

    // ---- SHALLOW SIZE ESTIMATE USING REFLECTION ----
    public static long getShallowSize(Object obj) {
        if (obj == null) return 0;

        Class<?> clazz = obj.getClass();
        long size = OBJECT_HEADER_SIZE;

        while (clazz != null) {
            for (Field field : clazz.getDeclaredFields()) {
                if (Modifier.isStatic(field.getModifiers())) continue;

                Class<?> type = field.getType();
                if (type.isPrimitive()) {
                    size += primitiveSizes.getOrDefault(type, 4);
                } else {
                    // Reference size, typically 4 or 8 bytes depending on JVM (assume 8)
                    size += 8;
                }
            }
            clazz = clazz.getSuperclass();
        }

        return size;
    }

    // ---- DEEP SIZE ----
    public static long getDeepSize(Object obj) {
        visited.clear();
        return internalDeepSize(obj);
    }

    private static long internalDeepSize(Object obj) {
        if (obj == null || visited.contains(obj)) {
            return 0;
        }

        visited.add(obj);
        Class<?> clazz = obj.getClass();

        // Handle arrays
        if (clazz.isArray()) {
            long size = OBJECT_HEADER_SIZE; // estimated array header size
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                Object element = Array.get(obj, i);
                size += internalDeepSize(element);
            }
            return size;
        }

        long size = OBJECT_HEADER_SIZE;

        while (clazz != null) {
            for (Field field : clazz.getDeclaredFields()) {
                if (Modifier.isStatic(field.getModifiers())) continue;

                try {
                    field.setAccessible(true);
                    Class<?> type = field.getType();
                    if (type.isPrimitive()) {
                        size += primitiveSizes.getOrDefault(type, 4);
                    } else {
                        Object fieldValue = field.get(obj);
                        size += internalDeepSize(fieldValue);
                    }
                } catch (IllegalAccessException | InaccessibleObjectException e) {
                    // Skip inaccessible fields silently
                }
            }
            clazz = clazz.getSuperclass();
        }

        return size;
    }

    // ---- Utility Print ----
    public static void printMemoryUsage(Object obj) {
        System.out.println("Shallow size estimate: " + getShallowSize(obj) + " bytes");
        System.out.println("Deep size estimate: " + getDeepSize(obj) + " bytes");
    }
}