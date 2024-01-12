
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// При запуске сервер направит нас в браузер, в адресной строке нужно дописать /weather
// После этого в браузер выведется информация о погоде на неделю

public class WeatherServlet extends HttpServlet {


    // Регулярное выражение, что бы выводить только дату.
    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{2}\\.\\d{2}");

    // Метод выполняет несколько действий:
    // Устанавливает тип содержимого и кодировку для ответа.
    // Получает страницу с сайта с помощью метода getPage().
    // Извлекает таблицу с данными о погоде из страницы с помощью методов select() и first().
    // Извлекает из таблицы данные о датах, явлениях, температуре, давлении, влажности и ветре с помощью методов select() и text().
    // Формирует HTML-страницу с помощью метода writer.println(), в которой выводятся данные о погоде.
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();

        Document page = getPage();

        Element tableWth = page.select("table[class=wt]").first();


        Elements names = tableWth.select("tr[class=wth]");
        Elements values = tableWth.select("tr[valign=top]");
        int index = 0;
        for (Element name : names) {


            String dateString = name.select("th[id=dt]").text();
            String date;
            try {
                date = getDateFromString(dateString);
            } catch (Exception e) {
                writer.println("Can't extract data from string!");
                continue;
            }
            writer.println("<p>" + " " + "</p>");
            writer.println("<hr>");
            writer.println(date + "             Явления                  Температура   Давл   Влажность     Ветер  -  ");
            int iterationCount = printFourValues(values, index, writer);
            index = index + iterationCount;

        }
    }

    // Метод получает страницу с сайта и возвращает ее в виде объекта
        public Document getPage() throws IOException {
        String url = "http://www.pogoda.spb.ru/";
        Document page = Jsoup.parse(new URL(url), 3000);
        return page;
    }


    // Данный метод извлекает дату из строки, используя регулярное выражение DATE_PATTERN.
    // Входной параметр stringDate - строка, из которой нужно извлечь дату.
    // Метод создает объект Matcher, который будет искать совпадения с регулярным выражением в строке stringDate.
    // Если совпадение найдено, то метод возвращает найденную дату в виде строки с помощью метода matcher.group().
    // Если совпадение не найдено, то метод выбрасывает исключение с сообщением "Can't extract data from string!".
    private static String getDateFromString(String stringDate) throws Exception {
        Matcher matcher = DATE_PATTERN.matcher(stringDate);
        if (matcher.find()) {
            return matcher.group();
        }
        throw new Exception("Can't extract data from string!");
    }


    // Данный метод выводит в файл (PrintWriter) четыре значения
    // из списка элементов (Elements) начиная с индекса
    // Если index равен 0, то метод проверяет, содержит ли третий элемент списка текст "День".
    // Если содержит, то количество значений для вывода устанавливается в 2
    private static int printFourValues(Elements values, int index, PrintWriter writer) {
        int iterationCount = 4;
        if (index == 0) {
            Element valueLn = values.get(3);
            boolean isMorning = valueLn.text().contains("День");
            if (isMorning) {
                iterationCount = 2;
            }
        }

        for (int i = 0; i < iterationCount; i++) {
            Element valueLine = values.get(index + i);
            for (Element td : valueLine.select("td")) {
                writer.print(td.text() + "      ");
            }
            writer.println();
        }
        return iterationCount;
    }


}
