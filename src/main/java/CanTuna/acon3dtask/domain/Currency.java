package CanTuna.acon3dtask.domain;

public class Currency {

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    private String country;
    private Double rate;

}
