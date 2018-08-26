package com.rodrigolmti.coinzilla.util

import android.databinding.BindingAdapter
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import com.github.mikephil.charting.charts.CandleStickChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry
import com.rodrigolmti.coinzilla.data.model.api.HistoricResponse

@BindingAdapter("android:visibility")
fun setVisibility(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("itemSelectedListener")
fun addOnItemSelectedListener(spinner: Spinner, onItemSelectedListener: AdapterView.OnItemSelectedListener) {
    spinner.onItemSelectedListener = onItemSelectedListener
}

@BindingAdapter("editTextError")
fun addEditTextErrorMessage(editText: EditText, message: String?) {
    if (!message.isNullOrEmpty()) {
        editText.error = message
    }
}

@BindingAdapter("loadCandleStickData")
fun loadCandleStickData(candleChart: CandleStickChart, result: List<HistoricResponse>) {
    if (result.isNotEmpty()) {
        candleChart.resetTracking()
        candleChart.setBackgroundColor(Color.WHITE)
        candleChart.description.isEnabled = false
        candleChart.setMaxVisibleValueCount(60)
        candleChart.setPinchZoom(false)
        candleChart.setTouchEnabled(false)
        candleChart.setDrawGridBackground(false)

        val xAxis = candleChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawAxisLine(false)
        xAxis.setDrawLabels(false)
        xAxis.setDrawGridLines(false)

        val leftAxis = candleChart.axisLeft
        leftAxis.setLabelCount(7, false)
        leftAxis.setDrawGridLines(false)
        leftAxis.setDrawAxisLine(false)

        val rightAxis = candleChart.axisRight
        rightAxis.isEnabled = false

        candleChart.legend.isEnabled = false

        val entries: ArrayList<CandleEntry> = ArrayList()
        var count = 0f
        for (obj in result) {
            count++
            entries.add(CandleEntry(count, obj.high, obj.low, obj.open, obj.close))
        }
        val dataSet = CandleDataSet(entries, "HistoricResponse")
        dataSet.color = Color.rgb(80, 80, 80)
        dataSet.shadowColor = Color.DKGRAY
        dataSet.setDrawValues(false)
        dataSet.shadowWidth = 0.7f
        dataSet.decreasingColor = Color.rgb(192, 57, 43)
        dataSet.decreasingPaintStyle = Paint.Style.FILL
        dataSet.increasingColor = Color.rgb(39, 174, 96)
        dataSet.increasingPaintStyle = Paint.Style.FILL
        dataSet.setDrawIcons(false)
        dataSet.neutralColor = Color.BLUE
        dataSet.valueTextColor = Color.RED
        val candleData = CandleData(dataSet)
        candleChart.animate()
        candleChart.data = candleData
        candleChart.invalidate()
    }
}