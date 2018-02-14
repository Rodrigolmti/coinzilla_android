package com.rodrigolmti.coinzilla.coinzilla.model.entity.coin

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class CryptoCurrency(

        @PrimaryKey
        var id: String? = "",
        var name: String? = "",
        var rank: String? = "",
        var symbol: String? = "",
        @SerializedName("price_usd")
        var priceUsd: String? = "",
        @SerializedName("price_btc")
        var priceBtc: String? = "",
        @SerializedName("price_brl")
        var priceBrl: String? = "",
        @SerializedName("24h_volume_usd")
        var volumeUsd: String? = "",
        @SerializedName("market_cap_usd")
        var marketCapUsd: String? = "",
        @SerializedName("available_supply")
        var availableSupply: String? = "",
        @SerializedName("total_supply")
        var totalSupply: String? = "",
        @SerializedName("percent_change_1h")
        var percentChange1H: String? = "",
        @SerializedName("percent_change_24h")
        var percentChange24H: String? = "",
        @SerializedName("percent_change_7d")
        var percentChange7D: String? = "",
        @SerializedName("24h_volume_brl")
        var volumeBrl: String? = "",
        @SerializedName("market_cap_brl")
        var marketCapBrl: String? = "",
        var favorite: Boolean = false

) : RealmObject(), Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readByte() != 0.toByte())

    fun checkNullValue() {
        name = name ?: ""
        rank = rank ?: ""
        symbol = symbol ?: ""
        priceUsd = priceUsd ?: ""
        priceBtc = priceBtc ?: ""
        priceBrl = priceBrl ?: ""
        volumeBrl = volumeBrl ?: ""
        volumeUsd = volumeUsd ?: ""
        totalSupply = totalSupply ?: ""
        marketCapBrl = marketCapBrl ?: ""
        marketCapUsd = marketCapUsd ?: ""
        availableSupply = availableSupply ?: ""
        percentChange1H = percentChange1H ?: ""
        percentChange7D = percentChange7D ?: ""
        percentChange24H = percentChange24H ?: ""
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(rank)
        parcel.writeString(symbol)
        parcel.writeString(priceUsd)
        parcel.writeString(priceBtc)
        parcel.writeString(priceBrl)
        parcel.writeString(volumeUsd)
        parcel.writeString(marketCapUsd)
        parcel.writeString(availableSupply)
        parcel.writeString(totalSupply)
        parcel.writeString(percentChange1H)
        parcel.writeString(percentChange24H)
        parcel.writeString(percentChange7D)
        parcel.writeString(volumeBrl)
        parcel.writeString(marketCapBrl)
        parcel.writeByte(if (favorite) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CryptoCurrency> {
        override fun createFromParcel(parcel: Parcel): CryptoCurrency {
            return CryptoCurrency(parcel)
        }

        override fun newArray(size: Int): Array<CryptoCurrency?> {
            return arrayOfNulls(size)
        }
    }
}
