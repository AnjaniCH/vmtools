package id.dbs.vmtools.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CurrencyRates")
public class CurrencyRate {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "currencyRateId", length = 11, nullable = false)
  private Integer currencyRateId;

  @Column(name = "year", length = 11, nullable = false)
  private Integer year;

  @Column(name = "month", length = 11, nullable = false)
  private Integer month;

  @Column(name = "currency", length = 3, nullable = true)
  private String currency;

  @Column(name = "rate", nullable = true)
  private Double rate;

  public CurrencyRate() {
  }

  public CurrencyRate(Integer currencyRateId, Integer year, Integer month, String currency, Double rate) {
    this.currencyRateId = currencyRateId;
    this.year = year;
    this.month = month;
    this.currency = currency;
    this.rate = rate;
  }

  public Integer getCurrencyRateId() {
    return currencyRateId;
  }

  public void setCurrencyRateId(Integer currencyRateId) {
    this.currencyRateId = currencyRateId;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public Integer getMonth() {
    return month;
  }

  public void setMonth(Integer month) {
    this.month = month;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public Double getRate() {
    return rate;
  }

  public void setRate(Double rate) {
    this.rate = rate;
  }
}
