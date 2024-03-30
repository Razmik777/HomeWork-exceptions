import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HomeWork {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные: Фамилия Имя Отчество, дата рождения(в формате дд.мм.гггг), номер телефона, пол(m - мужской, f - женский).");

        String input = scanner.nextLine();
        String[] data = input.split(" ");

        if (data.length != 6) {
            System.out.println("Ошибка: Вы ввели недостаточно данных.");
            return ;
        }

        String surname = data[0];
        String name = data[1];
        String patronymic = data[2];
        String dateOfBirth = data[3];
        long phoneNumber;
        char gender;

        if (!(surname instanceof String) || !(name instanceof String) || !(patronymic instanceof String)) {
            System.out.println("Ошибка: Фамилия, имя или отчество не являются строками.");
        }
        
        try {
            dateOfBirth = data[3];
            if (!dateOfBirth.matches("\\d{2}.\\d{2}.\\d{4}")) {
                throw new IllegalArgumentException("Ошибка: Некорректная дата рождения.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
        
        // if(!dateOfBirth.matches("\\d{2}.\\d{2}.\\d{4}")) {
        //     System.out.println("Ошибка: Некорректная дата рождения.");
        //     return;
        // }

        if (!data[4].matches("\\d+")) {
            System.out.println("Ошибка: Номер телефона должен быть целым числом без форматирования.");
            return;
        }

        try {
            gender = data[5].charAt(0);
            if (gender != 'f' && gender != 'm') {
                throw new IllegalArgumentException("Ошибка: Некорректный формат пола. Пол должен быть указан как 'm' или 'f'.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        
        String fn = data[0] + ".txt";
        phoneNumber = Long.parseLong(data[4]);
        try (FileWriter writer = new FileWriter(fn, true)) {
            writer.write(String.format("<%s> <%s> <%s> <%s> <%s> <%s>\n", surname, name, patronymic, dateOfBirth, phoneNumber, gender));
            writer.flush();
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл:");
            e.printStackTrace();
        }
        finally {
            scanner.close();
        }

        System.out.println("Данные успешно записаны в файл.");
    }
}