package Algoritm_7;

import java.util.*;

public class Main {

    static class City {
        String name;
        Map<City, Integer> neighbors;

        City(String name) {
            this.name = name;
            this.neighbors = new HashMap<>();
        }

        void addNeighbor(City neighbor, int distance) {
            neighbors.put(neighbor, distance);
            neighbor.neighbors.put(this, distance);
        }
    }

    public static void main(String[] args) {
        Map<String, City> graph = new HashMap<>();

        City kiev = new City("Киев");
        City lviv = new City("Львов");
        City rivne = new City("Ривне");
        City ternopol = new City("Тернополь");
        City khmelnytsky = new City("Хмельницький");
        City zhytomyr = new City("Житомир");
        City vinnitsa = new City("Винница");
        City uman = new City("Умань");
        City odessa = new City("Одесса");
        City mikolaev = new City("Николаев");
        City kherson = new City("Херсон");
        City kropyvnytskyi = new City("Кропивницкий");
        City krivoy_rog = new City("Кривой Рог");
        City dnepr = new City("Днепр");
        City poltava = new City("Полтава");
        City kharkiv = new City("Харьков");
        City sumy = new City("Сумы");

        // Добавляем соседей и расстояния между ними
        kiev.addNeighbor(zhytomyr, 140);
        zhytomyr.addNeighbor(rivne, 188);
        rivne.addNeighbor(ternopol, 159);
        rivne.addNeighbor(lviv, 121);
        ternopol.addNeighbor(lviv, 127);
        khmelnytsky.addNeighbor(ternopol, 111);
        vinnitsa.addNeighbor(khmelnytsky, 122);
        uman.addNeighbor(vinnitsa, 160);
        uman.addNeighbor(kiev, 212);
        uman.addNeighbor(kropyvnytskyi, 167);
        odessa.addNeighbor(uman, 271);
        odessa.addNeighbor(mikolaev, 132);
        mikolaev.addNeighbor(kherson, 71);
        mikolaev.addNeighbor(krivoy_rog, 204);
        kropyvnytskyi.addNeighbor(krivoy_rog, 119);
        kropyvnytskyi.addNeighbor(dnepr, 245);
        krivoy_rog.addNeighbor(dnepr, 145);
        dnepr.addNeighbor(kharkiv, 216);
        kiev.addNeighbor(poltava, 342);
        poltava.addNeighbor(kharkiv, 143);
        kharkiv.addNeighbor(sumy, 183);

        // Добавляем города в граф
        graph.put("Киев", kiev);
        graph.put("Львов", lviv);
        graph.put("Ривне", rivne);
        graph.put("Тернополь", ternopol);
        graph.put("Хмельницький", khmelnytsky);
        graph.put("Житомир", zhytomyr);
        graph.put("Винница", vinnitsa);
        graph.put("Умань", uman);
        graph.put("Одесса", odessa);
        graph.put("Николаев", mikolaev);
        graph.put("Херсон", kherson);
        graph.put("Кропивницкий", kropyvnytskyi);
        graph.put("Кривой Рог", krivoy_rog);
        graph.put("Днепр", dnepr);
        graph.put("Полтава", poltava);
        graph.put("Харьков", kharkiv);
        graph.put("Сумы", sumy);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите начальный город: ");
        String startCity = scanner.nextLine();

        System.out.print("Введите конечный город: ");
        String endCity = scanner.nextLine();

        System.out.println("Маршрут: " + startCity + " ---> " + endCity);

        if (isConnected(graph, startCity, endCity)) {
            List<List<String>> allPaths = findAllPaths(graph, startCity, endCity);

            if (!allPaths.isEmpty()) {
                System.out.println("Все возможные пути из " + startCity + " в " + endCity + ":");
                for (int i = 0; i < allPaths.size(); i++) {
                    System.out.println("Путь " + (i + 1) + ": " + String.join(" -> ", allPaths.get(i)));
                }

                List<String> shortestPath = findShortestPath(graph, allPaths);
                int distance = calculateDistance(graph, shortestPath);

                System.out.println("\nСамый короткий путь из " + startCity + " в " + endCity + ":");
                System.out.println(String.join(" -> ", shortestPath));
                System.out.println("Общее расстояние: " + distance + " км");
            } else {
                System.out.println("Не удалось найти пути из " + startCity + " в " + endCity + ".");
            }
        } else {
            System.out.println("Города не связаны.");
        }
    }

    static boolean isConnected(Map<String, City> graph, String startCity, String endCity) {
        if (!graph.containsKey(startCity) || !graph.containsKey(endCity)) {
            return false;
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.offer(startCity);
        visited.add(startCity);

        while (!queue.isEmpty()) {
            String currentCity = queue.poll();

            if (currentCity.equals(endCity)) {
                return true;
            }

            City city = graph.get(currentCity);

            for (City neighbor : city.neighbors.keySet()) {
                String neighborName = neighbor.name;
                if (!visited.contains(neighborName)) {
                    queue.offer(neighborName);
                    visited.add(neighborName);
                }
            }
        }

        return false;
    }

    static List<List<String>> findAllPaths(Map<String, City> graph, String startCity, String endCity) {
        List<List<String>> allPaths = new ArrayList<>();
        List<String> currentPath = new ArrayList<>();
        Set<String> visited = new HashSet<>();

        findAllPathsRecursive(graph, startCity, endCity, visited, currentPath, allPaths);

        return allPaths;
    }

    static void findAllPathsRecursive(
            Map<String, City> graph, String currentCity, String endCity,
            Set<String> visited, List<String> currentPath, List<List<String>> allPaths) {

        visited.add(currentCity);
        currentPath.add(currentCity);

        if (currentCity.equals(endCity)) {
            allPaths.add(new ArrayList<>(currentPath));
        } else {
            City city = graph.get(currentCity);
            for (City neighbor : city.neighbors.keySet()) {
                String neighborName = neighbor.name;
                if (!visited.contains(neighborName)) {
                    findAllPathsRecursive(graph, neighborName, endCity, visited, currentPath, allPaths);
                }
            }
        }

        visited.remove(currentCity);
        currentPath.remove(currentPath.size() - 1);
    }

    static List<String> findShortestPath(Map<String, City> graph, List<List<String>> allPaths) {
        List<String> shortestPath = null;
        int shortestDistance = Integer.MAX_VALUE;

        for (List<String> path : allPaths) {
            int distance = calculateDistance(graph, path);
            if (distance < shortestDistance) {
                shortestDistance = distance;
                shortestPath = path;
            }
        }

        return shortestPath;
    }

    static int calculateDistance(Map<String, City> graph, List<String> path) {
        int distance = 0;

        for (int i = 0; i < path.size() - 1; i++) {
            String currentCity = path.get(i);
            String nextCity = path.get(i + 1);

            if (graph.containsKey(currentCity) && graph.containsKey(nextCity)) {
                distance += graph.get(currentCity).neighbors.get(graph.get(nextCity));
            } else {
                System.out.println("Город " + currentCity + " или " + nextCity + " не найден в графе.");
                return -1;
            }
        }

        return distance;
    }
}
