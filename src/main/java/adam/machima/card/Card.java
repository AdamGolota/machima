package adam.machima.card;

import jakarta.persistence.*;

import java.util.Arrays;

@Entity
@Table
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private String lat_str;
    private String lon_str;

    @Lob
    private byte[] image;

    public Card() {
    }

    public Card(Long id, String type, String lat_str, String lon_str, byte[] image) {
        this.id = id;
        this.type = type;
        this.lat_str = lat_str;
        this.lon_str = lon_str;
        this.image = image;
    }

    public Card(String type, String lat_str, String lon_str, byte[] image) {
        this.type = type;
        this.lat_str = lat_str;
        this.lon_str = lon_str;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLat_str() {
        return lat_str;
    }

    public void setLat_str(String lat_str) {
        this.lat_str = lat_str;
    }

    public String getLon_str() {
        return lon_str;
    }

    public void setLon_str(String lon_str) {
        this.lon_str = lon_str;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", lat_str='" + lat_str + '\'' +
                ", lon_str='" + lon_str + '\'' +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
