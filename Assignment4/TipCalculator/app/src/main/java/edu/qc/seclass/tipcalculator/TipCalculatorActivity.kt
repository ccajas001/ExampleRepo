package edu.qc.seclass.tipcalculator
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast

import edu.qc.seclass.tipcalculator.databinding.ActivityTipCalculatorBinding

/*
*
*
*       ---Main App activity---
*
* 2 calculating functions - self explanatory
*   - calculateTip()
*   - calculateTotal()
*
* 2 view/display function
*   - displayTip() - changes text of views with ids "fifteenPercentTipValue","twentyPercentTipValue",
*                       and "twentyfivePercentTipValue" with appropriate calculations
*
*   - displayTotal() - changes text of views with ids "fifteenPercentTotalValue","twentyPercentTotalValue",
*                       and "twentyfivePercentTotalValue" with appropriate calculations
*
* 2 supporting functions
*   - roundUp() - used to round up values of tip + total for display using Kotlin ceil function,
*                   meant to reduce kotlin.math.ceil(<value>).toInt() to a single function
*
*   - validateInput() - used to check if views "checkAmountValue" and "partySizeValue" contained valid inputs (i.e. where strictly positive numbers)
*                       note: these values can be in decimal form but later they are rounded up
*
*   -changeColorLine() - changes color line of a plainText view to specified color
*
*
* */


class TipCalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTipCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTipCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCompute.setOnClickListener {

            val isChkAmtValid = validateInput(binding.checkAmountValue, true) //check for valid double/int
            val isPartySizeValid = validateInput(binding.partySizeValue,false)//checks for valid int - no fractions of people

            if (isChkAmtValid&&
                isPartySizeValid){

                val perPersonInitial = binding.checkAmountValue.text.toString().toDouble()/binding.partySizeValue.text.toString().toInt()
                val tips = calculateTip(perPersonInitial)
                calculateTotal(perPersonInitial,tips)

            }else{
                //display default value of zero for all calculations
                binding.fifteenPercentTipValue.text="0"
                binding.twentyPercentTipValue.text="0"
                binding.twentyfivePercentTipValue.text="0"
            }
        }


    }

    private fun calculateTotal(perPerson: Double, tips: DataTip) {
        val total = DataTotal(tips.tip15+perPerson,
            tips.tip20+perPerson,
            tips.tip25+perPerson)
        displayTotal(total)
    }//calculateTotal

    private fun displayTotal(total: DataTotal) {
        binding.fifteenPercentTotalValue.text=roundUp(total.total15).toString()
        binding.twentyPercentTotalValue.text=roundUp(total.total20).toString()
        binding.twentyfivePercentTotalValue.text=roundUp(total.total25).toString()
    }//displayTotal

    private fun calculateTip(perPerson: Double): DataTip{
        val tips = DataTip((perPerson*.15),
            (perPerson*.20),
            (perPerson*.25))
        displayTips(tips)
        return tips
    }//calculateTips

    private fun displayTips(tips: DataTip){

        binding.fifteenPercentTipValue.text=roundUp(tips.tip15).toString()
        binding.twentyPercentTipValue.text=roundUp(tips.tip20).toString()
        binding.twentyfivePercentTipValue.text=roundUp(tips.tip25).toString()
    }//displayTips


    private fun roundUp(value: Double): Int{
        return kotlin.math.ceil(value).toInt()
    }//roundUp

    private fun validateInput(inputView: EditText, canBeDouble: Boolean): Boolean {
        val input = inputView.text.toString() //set to text within view
        try{
            if (canBeDouble && input.toDouble()<=0){
                //output range and change color of view to red
                changeLineColor(inputView,Color.RED)
                Toast.makeText(applicationContext,"Invalid Range input",Toast.LENGTH_LONG).show()
                return false
            }
            if (!canBeDouble && input.toInt()<=0){
                changeLineColor(inputView,Color.RED)
                Toast.makeText(applicationContext,"Invalid Range input",Toast.LENGTH_LONG).show()
                return false
            }

            //if its not a number its invalid
        }catch(e: NumberFormatException){
            changeLineColor(inputView,Color.RED)
            Toast.makeText(applicationContext,"Invalid or Null input",Toast.LENGTH_LONG).show()
            return false
        }

        changeLineColor(inputView,Color.BLACK)
        return true


    }

    private fun changeLineColor(view: EditText, color:Int){
        view.backgroundTintList = ColorStateList.valueOf(color)
    }




}