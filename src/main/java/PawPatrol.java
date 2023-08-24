import java.util.*;

public class PawPatrol {
    /*
    Поиск марштура от щенка до человека.
    Щенок может двигаться только вниз или вправо и не может вставать на клетки с кактусами (*).
     */

    public static int steps; // Кол-во шагов для оценки эффективности алгоритма
    public static int memorySize = 0; // Кол-во элементов в дополнительном списке "плохих" координат
    public static Random random = new Random(); // Рандом для выбора направления

    public static void main(String[] args) throws Exception {
        // Вариант 1: Создаём комнату как в задании
        //Room room = new Room();
        // Вариант 2: Генерируем комнату с кактусами случайным образом
        Room room = new Room(21,35);

        // Задаём координаты щенка
        room.showRoom();
        room.putPuppy();

        // Задём с клавиатуры координаты человека
        room.showRoom();
        room.putHuman();
        room.showRoom();

        // Поиск при помощи рекурсии
        findPath(room);

        // Поиск при помощи рекурсии с динамическим программированием
        findPathWithMemory(room);
    }

    // Поиск маршрута от положения щенка до положения человека
    public static List<Coordinates> findPath(Room room) throws Exception {
        steps = 0;
        // Маршрут
        List<Coordinates> path = new ArrayList<>();
        // Задаём начальную позицию маршрута
        path.add(room.getPuppyPosition());
        // Запускаем поиск маршрута
        goNext(room, path);
        // Вывод результата
        showResult(1, room, path, false);

        return path;
    }

    // Поиск маршрута от положения щенка до положения человека (с запоминанием неудачных точек маршрута)
    public static List<Coordinates> findPathWithMemory(Room room) throws Exception {
        steps = 0;
        // Маршрут
        List<Coordinates> path = new ArrayList<>();
        // Список проверенных неудачных координат (из которых невозможно дойти до человека)
        Set<Coordinates> badCoordinates = new HashSet<>();
        // Задаём начальную позицию маршрута
        path.add(room.getPuppyPosition());
        // Запускаем поиск маршрута
        goNextWithMemory(room, path, badCoordinates);
        // Запоминаем кол-во элементов в памяти
        memorySize = badCoordinates.size();
        // Вывод результата
        showResult(2, room, path, true);

        return path;
    }

    // Метод определяющий можно дойти из последней точки маршрута до человека (true - можно, false - нет)
    public static boolean goNext(Room room, List<Coordinates> path) throws Exception {
        steps++;

        Coordinates lastStep = path.get(path.size() - 1);

        // Проверяем, не находимся ли мы на финише
        if (room.getHumanPosition().equals(lastStep)) {
            return true;
        }

        int fromX = lastStep.getX();
        int fromY = lastStep.getY();

        // Если находимся правее/ниже человека или за пределами комнаты или на кактусе, откатываемся назад
        if (fromX > Math.min(room.getSize(), room.getHumanPosition().getX())
                || fromY > Math.min(room.getSize(), room.getHumanPosition().getY())
                || room.getField(fromX, fromY) == Room.CACTUS) {
            path.remove(path.size() - 1);
            return false;
        }

        // Выбираем следующее направление случайным образом
        if (random.nextInt(2) == 0) {
            // Пробуем пойти вправо
            path.add(new Coordinates(fromX + 1, fromY));
            if (goNext(room, path)) return true;

            // Пробуем пойти вниз
            path.add(new Coordinates(fromX, fromY + 1));
            if (goNext(room, path)) return true;
        } else {
            // Пробуем пойти вниз
            path.add(new Coordinates(fromX, fromY + 1));
            if (goNext(room, path)) return true;

            // Пробуем пойти вправо
            path.add(new Coordinates(fromX + 1, fromY));
            if (goNext(room, path)) return true;
        }

        // Если дошли до сюда, значит, никуда нельзя пойти
        path.remove(path.size() - 1);
        return false;
    }

    // Метод определяющий можно дойти из последней точки маршрута до человека (true - можно, false - нет)
    public static boolean goNextWithMemory(Room room, List<Coordinates> path, Set<Coordinates> memory) throws Exception {
        steps++;

        Coordinates lastStep = path.get(path.size() - 1);

        // Проверяем, есть ли в памяти неудачный маршрут, проходящий через данную точку
        if (memory.contains(lastStep)) {
            path.remove(path.size() - 1);
            return false;
        }

        // Проверяем, не находимся ли мы на финише
        if (room.getHumanPosition().equals(lastStep)) {
            return true;
        }

        int fromX = lastStep.getX();
        int fromY = lastStep.getY();

        // Если находимся правее/ниже человека или за пределами комнаты или на кактусе, откатываемся назад
        if (fromX > Math.min(room.getSize(), room.getHumanPosition().getX())
                || fromY > Math.min(room.getSize(), room.getHumanPosition().getY())
                || room.getField(fromX, fromY) == Room.CACTUS) {
            memory.add(lastStep);
            path.remove(path.size() - 1);
            return false;
        }

        // Выбираем следующее направление случайным образом
        if (random.nextInt(2) == 0) {
            // Пробуем пойти вправо
            path.add(new Coordinates(fromX + 1, fromY));
            if (goNextWithMemory(room, path, memory)) return true;

            // Пробуем пойти вниз
            path.add(new Coordinates(fromX, fromY + 1));
            if (goNextWithMemory(room, path, memory)) return true;
        } else {
            // Пробуем пойти вниз
            path.add(new Coordinates(fromX, fromY + 1));
            if (goNextWithMemory(room, path, memory)) return true;

            // Пробуем пойти вправо
            path.add(new Coordinates(fromX + 1, fromY));
            if (goNextWithMemory(room, path, memory)) return true;
        }

        // Если дошли до сюда, значит, никуда нельзя пойти
        memory.add(lastStep);
        path.remove(path.size() - 1);
        return false;
    }

    // Вывод результата поиска маршрута
    public static void showResult(int algorithmNumber, Room room, List<Coordinates> path, boolean withMemory) throws Exception {
        // Вывод статистики
        System.out.print("Алгоритм №" + algorithmNumber + " совершил итераций: " + steps);
        if (withMemory) {
            System.out.println(" (запомнив " + memorySize + " \"плохих\" координат)");
        } else {
            System.out.println();
        }
        // Вывод результата поиска маршрута
        if (!path.isEmpty()) {
            System.out.println("Маршрут:" + path + "\n");
            room.showRoomWithPath(path);
        } else {
            System.out.println("Маршрут до человека построить невозможно!\n");
        }
    }
}
