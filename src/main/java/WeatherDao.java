import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WeatherDao {

    private static final String URL = "jdbc:postgresql://localhost:5433/Pogoda";
    private static final String LASTNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static Connection connection;

    static {

        try {
            connection = DriverManager.getConnection(URL, LASTNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void save(Weather weather) {

        try {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO weather_forecast VALUES(?, ?, ?, ?, ?, ?)");


            preparedStatement.setString(1, weather.getDate());
            preparedStatement.setString(2, weather.getPhenomenon());
            preparedStatement.setString(3, weather.getTemperature());
            preparedStatement.setString(4, weather.getPressure());
            preparedStatement.setString(5, weather.getHumidity());
            preparedStatement.setString(6, weather.getWind());

            preparedStatement.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


}