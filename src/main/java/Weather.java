import javax.persistence.*;



    public class Weather {


        private int id;


        private String date;


        private String phenomenon;


        private String temperature;


        private String pressure;

        private String humidity;

        private String wind;


        public Weather() {
        }

        public Weather(int id, String date, String phenomenon, String temperature, String pressure, String humidity, String wind) {
            this.id = id;
            this.date = date;
            this.phenomenon = phenomenon;
            this.temperature = temperature;
            this.pressure = pressure;
            this.humidity = humidity;
            this.wind = wind;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
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

