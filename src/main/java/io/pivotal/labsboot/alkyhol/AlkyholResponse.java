package io.pivotal.labsboot.alkyhol;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

class AlkyholResponse {
    private List<Alkyhol> alkyhols;

    public AlkyholResponse() {}

    public AlkyholResponse(final List<Alkyhol> alkyhols) {
        this.alkyhols = alkyhols;
    }

    public List<Alkyhol> getAlkyhols() {
        return alkyhols;
    }

    @JsonProperty("result")
    public void setAlkyhols(final List<Alkyhol> alkyhols) {
        this.alkyhols = alkyhols;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final AlkyholResponse that = (AlkyholResponse) o;

        return !(alkyhols != null ? !alkyhols.equals(that.alkyhols) : that.alkyhols != null);
    }

    @Override
    public int hashCode() {
        return alkyhols != null ? alkyhols.hashCode() : 0;
    }
}
