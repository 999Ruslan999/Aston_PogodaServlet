package org.example.aston_pogoda;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherServlet extends HttpServlet {

    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{2}\\.\\d{2}");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
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
            writer.println(date + "             Явления                  Температура   Давл   Влажность     Ветер");
            int iterationCount = printFourValues(values, index, writer);
            index = index + iterationCount;

        }
    }

    private static Document getPage() throws IOException {
        String url = "http://www.pogoda.spb.ru/";
        Document page = Jsoup.parse(new URL(url), 3000);
        return page;
    }

    private static String getDateFromString(String stringDate) throws Exception {
        Matcher matcher = DATE_PATTERN.matcher(stringDate);
        if(matcher.find()) {
            return matcher.group();
        }
        throw new Exception("Can't extract data from string!");
    }

    private static int printFourValues(Elements values, int index, PrintWriter writer) {
        int iterationCount = 4;
        if(index == 0) {
            Element valueLn = values.get(3);
            boolean isMorning = valueLn.text().contains("День");
            if(isMorning) {
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