
package sheva.economicprovider.mvp.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NBCurrency implements Parcelable{

    @SerializedName("r030")
    @Expose
    private Integer r030;
    @SerializedName("txt")
    @Expose
    private String txt;
    @SerializedName("rate")
    @Expose
    private Double rate;
    @SerializedName("cc")
    @Expose
    private String cc;
    @SerializedName("exchangedate")
    @Expose
    private String exchangedate;

    public NBCurrency() {
    }

    public NBCurrency(Integer r030, String txt, Double rate, String cc, String exchangedate) {
        super();
        this.r030 = r030;
        this.txt = txt;
        this.rate = rate;
        this.cc = cc;
        this.exchangedate = exchangedate;
    }

    protected NBCurrency(Parcel in) {
        txt = in.readString();
        rate = in.readDouble();
        cc = in.readString();
        exchangedate = in.readString();
    }

    public static final Creator<NBCurrency> CREATOR = new Creator<NBCurrency>() {
        @Override
        public NBCurrency createFromParcel(Parcel in) {
            return new NBCurrency(in);
        }

        @Override
        public NBCurrency[] newArray(int size) {
            return new NBCurrency[size];
        }
    };

    public Integer getR030() {
        return r030;
    }

    public void setR030(Integer r030) {
        this.r030 = r030;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getExchangedate() {
        return exchangedate;
    }

    public void setExchangedate(String exchangedate) {
        this.exchangedate = exchangedate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(txt);
        parcel.writeDouble(rate);
        parcel.writeString(cc);
        parcel.writeString(exchangedate);
    }
}
