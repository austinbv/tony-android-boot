package io.pivotal.labsboot.domain;

import java.util.List;

public class AlkyholResponse {
    private List<Alkyhol> alkyhols;
    private List<Link> links;

    public AlkyholResponse() {this(null);}

    public AlkyholResponse(final List<Alkyhol> alkyhols) {
        this(alkyhols, null);
    }

    public AlkyholResponse(final List<Alkyhol> alkyhols, final List<Link> links) {
        this.alkyhols = alkyhols;
        this.links = links;
    }

    public List<Alkyhol> getAlkyhols() {
        return alkyhols;
    }

    public void setAlkyhols(final List<Alkyhol> alkyhols) {
        this.alkyhols = alkyhols;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(final List<Link> links) {
        this.links = links;
    }

    public Link findLink(final String ref){
        for (final Link link : links) {
            if (ref.equals(link.getRel())) {
                return link;
            }
        }
        return null;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final AlkyholResponse that = (AlkyholResponse) o;

        if (alkyhols != null ? !alkyhols.equals(that.alkyhols) : that.alkyhols != null)
            return false;
        return !(links != null ? !links.equals(that.links) : that.links != null);

    }

    @Override
    public int hashCode() {
        int result = alkyhols != null ? alkyhols.hashCode() : 0;
        result = 31 * result + (links != null ? links.hashCode() : 0);
        return result;
    }
}
