package io.pivotal.labsboot.domain;

public class Alkyhol {
    private Integer id;
    private String name;
    private String price;
    private String category;
    private String origin;
    private String alcoholContent;
    private String producer;
    private Image image;
    private Container container;

    public Alkyhol() {}

    public Alkyhol(final Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(final String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(final String origin) {
        this.origin = origin;
    }

    public String getAlcoholContent() {
        return alcoholContent;
    }

    public void setAlcoholContent(final String alcoholContent) {
        this.alcoholContent = alcoholContent;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(final String producer) {
        this.producer = producer;
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(final Container container) {
        this.container = container;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(final Image image) {
        this.image = image;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Alkyhol alkyhol = (Alkyhol) o;

        if (id != null ? !id.equals(alkyhol.id) : alkyhol.id != null) return false;
        if (name != null ? !name.equals(alkyhol.name) : alkyhol.name != null) return false;
        if (price != null ? !price.equals(alkyhol.price) : alkyhol.price != null) return false;
        if (category != null ? !category.equals(alkyhol.category) : alkyhol.category != null)
            return false;
        if (origin != null ? !origin.equals(alkyhol.origin) : alkyhol.origin != null) return false;
        if (alcoholContent != null ? !alcoholContent.equals(alkyhol.alcoholContent) : alkyhol.alcoholContent != null)
            return false;
        if (producer != null ? !producer.equals(alkyhol.producer) : alkyhol.producer != null)
            return false;
        if (container != null ? !container.equals(alkyhol.container) : alkyhol.container != null)
            return false;
        return !(image != null ? !image.equals(alkyhol.image) : alkyhol.image != null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (origin != null ? origin.hashCode() : 0);
        result = 31 * result + (alcoholContent != null ? alcoholContent.hashCode() : 0);
        result = 31 * result + (producer != null ? producer.hashCode() : 0);
        result = 31 * result + (container != null ? container.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Alkyhol{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", category='" + category + '\'' +
                ", origin='" + origin + '\'' +
                ", alcoholContent='" + alcoholContent + '\'' +
                ", producer='" + producer + '\'' +
                ", container=" + container +
                ", image=" + image +
                '}';
    }
}
