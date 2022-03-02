package by.vorobey.myProject.bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
// @Table( name = название как в базе данных
@Table( name = "news", schema = "public")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String anons;
    private String fullText;

    public News() {
    }

    public News(String title, String anons, String fullText) {
        this.title = title;
        this.anons = anons;
        this.fullText = fullText;
    }

    public News(Long id, String title, String anons, String fullText) {
        this.id = id;
        this.title = title;
        this.anons = anons;
        this.fullText = fullText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnons() {
        return anons;
    }

    public void setAnons(String anons) {
        this.anons = anons;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return id == news.id && Objects.equals(title, news.title) && Objects.equals(anons, news.anons) && Objects.equals(fullText, news.fullText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, anons, fullText);
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", anons='" + anons + '\'' +
                ", fullText='" + fullText + '\'' +
                '}';
    }
}
