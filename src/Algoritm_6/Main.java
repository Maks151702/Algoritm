package Algoritm_6;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Пример использования конвертера
        Map<String, Object> person = new HashMap<>();
        person.put("name", "John");
        person.put("age", 30);
        person.put("isStudent", false);

        Map<String, Object> address = new HashMap<>();
        address.put("street", "123 Main St");
        address.put("city", "New York");
        address.put("zipCode", "10001");

        person.put("address", address);

        String json = toJSON(person);
        System.out.println(json);
    }
    public static String toJSON(Object obj) { // блок схема для этого метода
        if (obj == null) {
            return "null";
        }

        if (obj instanceof String) {
            return "\"" + escapeString((String) obj) + "\"";
        }

        if (obj instanceof Number || obj instanceof Boolean) {
            return obj.toString();
        }

        if (obj instanceof Map<?, ?>) {
            return mapToJSON((Map<?, ?>) obj);
        }

        if (obj instanceof Iterable<?>) {
            return iterableToJSON((Iterable<?>) obj);
        }

        if (obj.getClass().isArray()) {
            return arrayToJSON((Object[]) obj);
        }

        // Для пользовательских классов рекурсивно преобразовываем поля объекта в JSON
        return objectToJSON(obj);
    }

    private static String escapeString(String str) {
        return str.replace("\"", "\\\"");
    }

    private static String mapToJSON(Map<?, ?> map) {
        StringBuilder json = new StringBuilder("{");
        boolean first = true;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (!first) {
                json.append(",");
            }
            json.append("\"").append(entry.getKey()).append("\":").append(toJSON(entry.getValue()));
            first = false;
        }
        json.append("}");
        return json.toString();
    }

    private static String iterableToJSON(Iterable<?> iterable) {
        StringBuilder json = new StringBuilder("[");
        boolean first = true;
        for (Object item : iterable) {
            if (!first) {
                json.append(",");
            }
            json.append(toJSON(item));
            first = false;
        }
        json.append("]");
        return json.toString();
    }

    private static String arrayToJSON(Object[] array) {
        StringBuilder json = new StringBuilder("[");
        boolean first = true;
        for (Object item : array) {
            if (!first) {
                json.append(",");
            }
            json.append(toJSON(item));
            first = false;
        }
        json.append("]");
        return json.toString();
    }

    private static String objectToJSON(Object obj) {
        Class<?> clazz = obj.getClass();
        Map<String, Object> fields = new HashMap<>();
        for (java.lang.reflect.Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                fields.put(field.getName(), field.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return mapToJSON(fields);
    }

}
