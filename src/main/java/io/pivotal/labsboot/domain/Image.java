package io.pivotal.labsboot.domain;

public class Image {
    private String full;
    private String thumb;

    public String getFull() {
        return full;
    }

    public void setFull(final String full) {
        this.full = full;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(final String thumb) {
        this.thumb = thumb;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Image image = (Image) o;

        if (full != null ? !full.equals(image.full) : image.full != null) return false;
        return !(thumb != null ? !thumb.equals(image.thumb) : image.thumb != null);
    }

    @Override
    public int hashCode() {
        int result = full != null ? full.hashCode() : 0;
        result = 31 * result + (thumb != null ? thumb.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Image{" +
                "full='" + full + '\'' +
                ", thumb='" + thumb + '\'' +
                '}';
    }
}
