package com.efhems.socialvue.ui.profession


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.efhems.socialvue.databinding.FragmentProfDashboardBinding
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate


/**
 * A simple [Fragment] subclass.
 *
 */
class ProfDashboardFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
         val binding:FragmentProfDashboardBinding =
                 DataBindingUtil.inflate(inflater, com.efhems.socialvue.R.layout.fragment_prof_dashboard, container, false)


        var NoOfEmp = ArrayList<BarEntry>()
        var NoOfEmpPie = ArrayList<PieEntry>()

        NoOfEmp.add(BarEntry(945f, 0f))
        NoOfEmp.add(BarEntry(1040f, 1f))
        NoOfEmp.add(BarEntry(1133f, 2f))
        NoOfEmp.add(BarEntry(1240f, 3f))
        NoOfEmp.add(BarEntry(1369f, 4f))
        NoOfEmp.add(BarEntry(1487f, 5f))
        NoOfEmp.add(BarEntry(1501f, 6f))
        NoOfEmp.add(BarEntry(1645f, 7f))
        NoOfEmp.add(BarEntry(1578f, 8f))
        NoOfEmp.add(BarEntry(1695f, 9f))

        NoOfEmpPie.add(PieEntry(945f, 0f))
        NoOfEmpPie.add(PieEntry(1040f, 1f))
        NoOfEmpPie.add(PieEntry(1133f, 2f))
        NoOfEmpPie.add(PieEntry(1240f, 3f))
        NoOfEmpPie.add(PieEntry(1369f, 4f))
        NoOfEmpPie.add(PieEntry(1487f, 5f))
        NoOfEmpPie.add(PieEntry(1501f, 6f))
        NoOfEmpPie.add(PieEntry(1645f, 7f))
        NoOfEmpPie.add(PieEntry(1578f, 8f))
        NoOfEmpPie.add(PieEntry(1695f, 9f))

        val year = ArrayList<String>()

        year.add("2008")
        year.add("2009")
        year.add("2010")
        year.add("2011")
        year.add("2012")
        year.add("2013")
        year.add("2014")
        year.add("2015")
        year.add("2016")
        year.add("2017")

        val bardataset = BarDataSet(NoOfEmp, "No of Client")
        binding.barChart.animateY(5000)
        val data = BarData(bardataset)
        bardataset.setColors(*ColorTemplate.COLORFUL_COLORS)
        binding.barChart.setData(data)


        val dataSet = PieDataSet(NoOfEmpPie, "Number of job Completed")
        val dataPieData = PieData(dataSet)
        binding.pieChart.setData(dataPieData)
        dataSet.setColors(*ColorTemplate.MATERIAL_COLORS)
        binding.pieChart.animateXY(5000, 5000)

        return binding.root
    }


}
