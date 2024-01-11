import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

public class WeatherDao {

    private WeatherServlet weatherServlet;
    public void saveWeatherData() throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // получение данных о погоде с сайта и сохранение в базу данных
        Document page = weatherServlet.getPage();
        Element tableWth = page.select("table[class=wt]").first();
        Elements names = tableWth.select("tr[class=wth]");
        Elements values = tableWth.select("tr[valign=top]");

        for (int i = 0; i < names.size(); i++) {
            Element name = names.get(i);
            String dateString = name.select("th[id=dt]").text();
            String phenomenon = values.get(i * 4).select("td").get(0).text();
            String temperature = values.get(i * 4 + 1).select("td").get(0).text();
            String pressure = values.get(i * 4 + 2).select("td").get(0).text();
            String humidity = values.get(i * 4 + 3).select("td").get(0).text();
            String wind = values.get(i * 4 + 1).select("td").get(1).text();

            Weather weather = new Weather();
            weather.setDate(dateString);
            weather.setPhenomenon(phenomenon);
            weather.setTemperature(temperature);
            weather.setPressure(pressure);
            weather.setHumidity(humidity);
            weather.setWind(wind);

            em.persist(weather);
        }

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}