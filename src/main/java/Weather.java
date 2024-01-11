import javax.persistence.*;

    @Entity
    @Table(name = "weather_forecast")
    public class Weather {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "date")
        private String date;

        @Column(name = "phenomenon")
        private String phenomenon;

        @Column(name = "temperature")
        private String temperature;

        @Column(name = "pressure")
        private String pressure;

        @Column(name = "humidity")
        private String humidity;

        @Column(name = "wind")
        private String wind;


        public Weather() {
        }

        public Weather(Long id, String date, String phenomenon, String temperature, String pressure, String humidity, String wind) {
            this.id = id;
            this.date = date;
            this.phenomenon = phenomenon;
            this.temperature = temperature;
            this.pressure = pressure;
            this.humidity = humidity;
            this.wind = wind;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getPhenomenon() {
            return phenomenon;
        }

        public void setPhenomenon(String phenomenon) {
            this.phenomenon = phenomenon;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getPressure() {
            return pressure;
        }

        public void setPressure(String pressure) {
            this.pressure = pressure;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getWind() {
            return wind;
        }

        public void setWind(String wind) {
            this.wind = wind;
        }

        @Override
        public String toString() {
            return "Weather{" +
                   "id=" + id +
                   ", date='" + date + '\'' +
                   ", phenomenon='" + phenomenon + '\'' +
                   ", temperature='" + temperature + '\'' +
                   ", pressure='" + pressure + '\'' +
                   ", humidity='" + humidity + '\'' +
                   ", wind='" + wind + '\'' +
                   '}';
        }
    }

