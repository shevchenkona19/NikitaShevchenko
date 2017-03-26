
package sheva.economicprovider.mvp.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExchangeRate {

    @SerializedName("baseCurrency")
    @Expose
    private String baseCurrency;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("saleRateNB")
    @Expose
    private Double saleRateNB;
    @SerializedName("purchaseRateNB")
    @Expose
    private Double purchaseRateNB;
    @SerializedName("saleRate")
    @Expose
    private Double saleRate;
    @SerializedName("purchaseRate")
    @Expose
    private Double purchaseRate;

    /**
     * No args constructor for use in serialization
     */
    public ExchangeRate() {
    }

    /**
     * @param purchaseRateNB
     * @param saleRate
     * @param purchaseRate
     * @param baseCurrency
     * @param saleRateNB
     * @param currency
     */
    public ExchangeRate(String baseCurrency, String currency, Double saleRateNB, Double purchaseRateNB, Double saleRate, Double purchaseRate) {
        super();
        this.baseCurrency = baseCurrency;
        this.currency = currency;
        this.saleRateNB = saleRateNB;
        this.purchaseRateNB = purchaseRateNB;
        this.saleRate = saleRate;
        this.purchaseRate = purchaseRate;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getSaleRateNB() {
        return saleRateNB;
    }

    public void setSaleRateNB(Double saleRateNB) {
        this.saleRateNB = saleRateNB;
    }

    public Double getPurchaseRateNB() {
        return purchaseRateNB;
    }

    public void setPurchaseRateNB(Double purchaseRateNB) {
        this.purchaseRateNB = purchaseRateNB;
    }

    public Double getSaleRate() {
            return saleRate;
    }

    public void setSaleRate(Double saleRate) {
        this.saleRate = saleRate;
    }

    public Double getPurchaseRate() {
            return purchaseRate;
    }

    public void setPurchaseRate(Double purchaseRate) {
        this.purchaseRate = purchaseRate;
    }
}
