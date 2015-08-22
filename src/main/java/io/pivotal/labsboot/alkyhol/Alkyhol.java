package io.pivotal.labsboot.alkyhol;

class Alkyhol {
    private String name;
    private Integer totalPackageUnits;
    private Integer packageUnitVolumeInMilliliters;
    private String packageUnitType;
    private Integer pricePerLiterInCents;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getTotalPackageUnits() {
        return totalPackageUnits;
    }

    public void setTotalPackageUnits(final Integer totalPackageUnits) {
        this.totalPackageUnits = totalPackageUnits;
    }

    public Integer getPackageUnitVolumeInMilliliters() {
        return packageUnitVolumeInMilliliters;
    }

    public void setPackageUnitVolumeInMilliliters(final Integer packageUnitVolumeInMilliliters) {
        this.packageUnitVolumeInMilliliters = packageUnitVolumeInMilliliters;
    }

    public String getPackageUnitType() {
        return packageUnitType;
    }

    public void setPackageUnitType(final String packageUnitType) {
        this.packageUnitType = packageUnitType;
    }

    public Integer getPricePerLiterInCents() {
        return pricePerLiterInCents;
    }

    public void setPricePerLiterInCents(final Integer pricePerLiterInCents) {
        this.pricePerLiterInCents = pricePerLiterInCents;
    }

    @Override
    public String toString() {
        return "Alkyhol{" +
                "name='" + name + '\'' +
                ", totalPackageUnits=" + totalPackageUnits +
                ", packageUnitVolumeInMilliliters=" + packageUnitVolumeInMilliliters +
                ", packageUnitType='" + packageUnitType + '\'' +
                ", pricePerLiterInCents=" + pricePerLiterInCents +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Alkyhol alkyhol = (Alkyhol) o;

        if (name != null ? !name.equals(alkyhol.name) : alkyhol.name != null) return false;
        if (totalPackageUnits != null ? !totalPackageUnits.equals(alkyhol.totalPackageUnits) : alkyhol.totalPackageUnits != null)
            return false;
        if (packageUnitVolumeInMilliliters != null ? !packageUnitVolumeInMilliliters.equals(alkyhol.packageUnitVolumeInMilliliters) : alkyhol.packageUnitVolumeInMilliliters != null)
            return false;
        if (packageUnitType != null ? !packageUnitType.equals(alkyhol.packageUnitType) : alkyhol.packageUnitType != null)
            return false;
        return !(pricePerLiterInCents != null ? !pricePerLiterInCents.equals(alkyhol.pricePerLiterInCents) : alkyhol.pricePerLiterInCents != null);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (totalPackageUnits != null ? totalPackageUnits.hashCode() : 0);
        result = 31 * result + (packageUnitVolumeInMilliliters != null ? packageUnitVolumeInMilliliters.hashCode() : 0);
        result = 31 * result + (packageUnitType != null ? packageUnitType.hashCode() : 0);
        result = 31 * result + (pricePerLiterInCents != null ? pricePerLiterInCents.hashCode() : 0);
        return result;
    }
}
