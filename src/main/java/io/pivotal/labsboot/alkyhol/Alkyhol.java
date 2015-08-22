package io.pivotal.labsboot.alkyhol;

class Alkyhol {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Alkyhol alkyhol = (Alkyhol) o;

        return !(name != null ? !name.equals(alkyhol.name) : alkyhol.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
