import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Room {
    private final Scanner scanner = new Scanner(System.in);

    // Обозначения в комнате
    public static final char PUPPY = 'Щ';  // Щенок
    public static final char CACTUS = '*'; // Кактус
    public static final char HUMAN = 'М';  // Человек
    public static final char VOID = '.';   // Пустые (свободные) клетки
    public static final char PATH = 'O';   // Маршрут

    // Размер квадратной комнаты
    private final int size;
    // Поля комнаты
    private char[][] fields;
    // Координаты щенка
    private Coordinates puppyPosition = new Coordinates(0, 0);
    // Координаты человека (по умолчанию за пределами комнаты)
    private Coordinates humanPosition = new Coordinates(-1, -1);

    // Конструктор без параметров создаёт комнату как в задании
    public Room() {
        size = 10;
        generateTaskRoom(size);
    }

    // Генерация случайной квадратной комнаты произвольных размеров
    public Room(int size, int cactusCount) {
        this.size = size;
        generateEmptyRoom(size);
        setPuppyPosition(0, 0);
        generateCactuses(cactusCount);
    }

    public int getSize() {
        return size;
    }

    public Coordinates getPuppyPosition() {
        return puppyPosition;
    }

    public Coordinates getHumanPosition() {
        return humanPosition;
    }

    // Просмотр значения в конкретном поле
    public char getField(int x, int y) throws Exception {
        if (x >= 0 && x < size && y >= 0 && y < size) {
            return fields[x][y];
        } else {
            throw new Exception("Обращение к несуществующим координатам!");
        }
    }

    // Ввод координат человека
    public void putHuman() {
        try {
            System.out.println("Введите координаты человека (x, y):");
            String[] rawData = scanner.nextLine().split(",");
            int x = Integer.parseInt(rawData[0].trim());
            int y = Integer.parseInt(rawData[1].trim());
            if (x >= 0 && x < size && y >= 0 && y < size) {
                if (x != puppyPosition.getX() || y != puppyPosition.getY()){
                    setHumanPosition(x, y);
                } else {
                    System.out.println("Координаты человека не должны совпадать с координатами щенка.");
                    putHuman();
                }
            } else {
                System.out.println("Координаты должны принимать значения от 0 до " + (size - 1));
                putHuman();
            }
        } catch (Exception e) {
            System.out.println("Что-то пошло не так. Попробуем ещё раз.");
            putHuman();
        }
    }

    // Ввод координат щенка
    public void putPuppy() {
        try {
            System.out.println("Введите координаты щенка (x, y):");
            String[] rawData = scanner.nextLine().split(",");
            int x = Integer.parseInt(rawData[0].trim());
            int y = Integer.parseInt(rawData[1].trim());
            if (x >= 0 && x < size && y >= 0 && y < size) {
                if (x != humanPosition.getX() || y != humanPosition.getY()){
                    setPuppyPosition(x, y);
                } else {
                    System.out.println("Координаты щенка не должны совпадать с координатами человека.");
                    putHuman();
                }
            } else {
                System.out.println("Координаты должны принимать значения от 0 до " + (size - 1));
                putHuman();
            }
        } catch (Exception e) {
            System.out.println("Что-то пошло не так. Попробуем ещё раз.");
            putHuman();
        }
    }

    // Отрисовка комнаты
    public void showRoom() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                System.out.printf("%2s", fields[x][y]);
            }
            System.out.println();
        }
        System.out.println();
    }

    // Отрисовка комнаты с маршрутом щенка
    public void showRoomWithPath(List<Coordinates> path) throws Exception {
        // Создаём копию комнаты для добавления в неё точек маршрута
        char[][] fieldsWithPath = new char[size][size];
        for (int i = 0; i < fields.length; i++) {
            System.arraycopy(fields[i], 0, fieldsWithPath[i], 0, fields.length);
        }

        // Добавляем маршрут
        for (int i = 0; i < path.size(); i++) {
            int x = path.get(i).getX();
            int y = path.get(i).getY();
            if (getField(x, y) != CACTUS) {
                if (i == 0) {
                    if (!path.get(i).equals(puppyPosition)) {
                        throw new Exception("Некорректный путь! Пусть должен начинаться положением щенка " + puppyPosition + "!");
                    }
                } else if (i < path.size() - 1) {
                    fieldsWithPath[x][y] = PATH;
                } else if (!path.get(i).equals(humanPosition)) {
                    throw new Exception("Некорректный путь! Пусть должен завершаться положением человека " + humanPosition + "!");
                }
            } else {
                throw new Exception("Некорректный путь! Через кактус проходить нельзя!");
            }
        }

        // Выводим комнату с маршрутом на экран
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                System.out.printf("%2s", fieldsWithPath[x][y]);
            }
            System.out.println();
        }
        System.out.println();
    }

    // Создание пустой комнаты
    private void generateEmptyRoom(int size) {
        fields = new char[size][size];
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                fields[i][j] = VOID;
            }
        }
    }

    // Создание комнаты как в задании
    private void generateTaskRoom(int size) {
        // Щ - - * * - - - - -
        // - - - - * - * * - -
        // - - - * - * - - - *
        // - * - - - - - - Ч -
        // - - - - - - * - - -
        // - - * - - * - - - -
        // - - - * - - * * * -
        // - - - - - - - * - -
        // - - - - - - - * - -
        // - - - - - * * - - -
        generateEmptyRoom(size);
        setPuppyPosition(0, 0);
        setCactusPosition(1, 3);
        setCactusPosition(2, 5);
        setCactusPosition(3, 0);
        setCactusPosition(3, 2);
        setCactusPosition(3, 6);
        setCactusPosition(4, 0);
        setCactusPosition(4, 1);
        setCactusPosition(5, 2);
        setCactusPosition(5, 5);
        setCactusPosition(5, 9);
        setCactusPosition(6, 1);
        setCactusPosition(6, 4);
        setCactusPosition(6, 6);
        setCactusPosition(6, 9);
        setCactusPosition(7, 1);
        setCactusPosition(7, 6);
        setCactusPosition(7, 7);
        setCactusPosition(7, 8);
        setCactusPosition(8, 6);
        setCactusPosition(9, 2);
    }

    // Генерация кактусов
    private void generateCactuses(int cactusCount) {
        Random random = new Random();
        for (int i = 0; i < cactusCount; i++) {
            int x = random.nextInt(size);
            int y = random.nextInt(size);
            if (fields[x][y] == VOID) {
                fields[x][y] = CACTUS;
            } else if (i == size * size - 2) {
                break;
            } else {
                i--;
            }
        }
    }

    // Установка положения щенка
    private void setPuppyPosition(int x, int y) {
        if (x >= 0 && x < size && y >= 0 && y < size) {
            fields[puppyPosition.getX()][puppyPosition.getY()] = VOID;
            puppyPosition = new Coordinates(x, y);
            fields[x][y] = PUPPY;
        }
    }

    // Установка положения человека
    private void setHumanPosition(int x, int y) {
        int currX = humanPosition.getX();
        int currY = humanPosition.getY();

        humanPosition = new Coordinates(x, y);

        // Если старые координаты человека в пределах комнаты - затираем их
        if (currX >= 0 && currX < size && currY >= 0 && currY < size) {
            if (fields[currX][currY] == HUMAN) {
                fields[currX][currY] = VOID;
            }
        }

        // Если новые координаты в пределах комнаты - добавляем туда человека
        if (x >= 0 && x < size && y >= 0 && y < size) {
            fields[x][y] = HUMAN;
        }
    }

    // Добавление кактуса
    private void setCactusPosition(int x, int y) {
        fields[x][y] = CACTUS;
    }
}
