package gabrielruiu.packt.model;

/**
 * @author Gabriel Mihai Ruiu (gabriel.ruiu@mail.com)
 */
public class BookSummary {

    private String title;
    private String description;
    private String imageSrc;
    private String url;

    public BookSummary(String title, String description, String imageSrc, String url) {
        this.title = title;
        this.description = description;
        this.imageSrc = imageSrc;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BookSummary{");
        sb.append("title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", imageSrc='").append(imageSrc).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
