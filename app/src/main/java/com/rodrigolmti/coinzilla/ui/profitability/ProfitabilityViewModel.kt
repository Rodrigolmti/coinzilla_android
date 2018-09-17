package com.rodrigolmti.coinzilla.ui.profitability

import android.content.Context
import android.content.res.Resources
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import com.crashlytics.android.Crashlytics
import com.rodrigolmti.coinzilla.R
import com.rodrigolmti.coinzilla.di.qualifier.AppContext
import com.rodrigolmti.coinzilla.ui.base.view.MvvmView
import com.rodrigolmti.coinzilla.ui.base.viewModel.BaseViewModel
import java.text.DecimalFormat
import javax.inject.Inject

class ProfitabilityViewModel
@Inject constructor(
        @AppContext val context: Context,
        private val resources: Resources)
    : BaseViewModel<MvvmView>(), ProfitabilityMvvm {

    val onItemSelectedHashListener: AdapterView.OnItemSelectedListener
    val onItemSelectedCalcListener: AdapterView.OnItemSelectedListener

    val hashField: ObservableField<String> = ObservableField()
    val errorHashMessage: ObservableField<String> = ObservableField()

    val rewardField: ObservableField<String> = ObservableField()
    val errorRewardMessage: ObservableField<String> = ObservableField()

    val difficultyField: ObservableField<String> = ObservableField()
    val errorDifficultyMessage: ObservableField<String> = ObservableField()

    val feeField: ObservableField<String> = ObservableField()

    val result: ObservableBoolean = ObservableBoolean(false)

    val resultDay: ObservableField<String> = ObservableField()
    val resultMonth: ObservableField<String> = ObservableField()
    val resultYear: ObservableField<String> = ObservableField()

    private var difficultyMultiplier = 1F
    private var hashMultiplier = 1F

    init {

        onItemSelectedHashListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                val type = parent.getItemAtPosition(pos).toString()
                hashMultiplier = when (type) {
                    resources.getString(R.string.activity_profitability_hs) -> 1F
                    resources.getString(R.string.activity_profitability_khs) -> 1000F
                    resources.getString(R.string.activity_profitability_mhs) -> 1000000F
                    resources.getString(R.string.activity_profitability_ghs) -> 1000000000F
                    else -> 1F
                }
            }

            override fun onNothingSelected(parent: AdapterView<out Adapter>?) {
                hashMultiplier = 1F
            }
        }

        onItemSelectedCalcListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                val type = parent.getItemAtPosition(pos).toString()
                hashMultiplier = when (type) {
                    resources.getString(R.string.activity_profitability_ethereum) -> 1F
                    resources.getString(R.string.activity_profitability_cryptonode) -> 1F
                    resources.getString(R.string.activity_profitability_bitcoin) -> Math.pow(2.0, 32.0).toFloat()
                    resources.getString(R.string.activity_profitability_zcash) -> Math.pow(2.0, 13.0).toFloat()
                    else -> 1F
                }
            }

            override fun onNothingSelected(parent: AdapterView<out Adapter>?) {
                difficultyMultiplier = 1F
            }
        }
    }

    fun onClickCalc() {
        try {

            var fee = 0F
            var error = 0

            if (hashField.get().isNullOrBlank()) {
                errorHashMessage.set(resources.getString(R.string.activity_profitability_message))
                error++
            }
            if (rewardField.get().isNullOrBlank()) {
                errorRewardMessage.set(resources.getString(R.string.activity_profitability_message))
                error++
            }
            if (difficultyField.get().isNullOrBlank()) {
                errorDifficultyMessage.set(resources.getString(R.string.activity_profitability_message))
                error++
            }
            if (!feeField.get().isNullOrBlank()) {
                fee = feeField.get().toString().toFloat()
            }

            if (error == 0) {

                val hash = hashField.get().toString().toFloat()
                val reward = rewardField.get().toString().toFloat()
                val difficulty = difficultyField.get().toString().toFloat()
                result.set(true)

                val decimalFormat = DecimalFormat("0.00000000")
                val blockMined = ((hash * hashMultiplier) * reward) / (difficulty * difficultyMultiplier) * fee

                val day = blockMined * 86400
                val month = blockMined * 2.628e+6
                val year = blockMined * 31535965.4396976

                resultDay.set(decimalFormat.format(day).toString())
                resultMonth.set(decimalFormat.format(month).toString())
                resultYear.set(decimalFormat.format(year).toString())
            }

        } catch (error: Exception) {
            Crashlytics.logException(error)
            error.stackTrace
        }
    }
}