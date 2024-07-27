package com.example.sceneformar

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    var product: Int? = null

    val itemArrayElectronics = arrayListOf<ProductData>(ProductData(R.drawable.laptop,
        "Laptop (Windows 10)",
        "₹50,000",
        "1 year+extension"),
        ProductData(R.drawable.tv, "Smart TV (Android)", "₹70,000", "2 years"),
        ProductData(R.drawable.pc, "Gaming PC (Windows 11, AMD)", "₹1,00,000", "2 years+extension"),
        ProductData(R.drawable.fridge, "Washing Machine (5kg)", "₹20,000", "5 years"))

    val itemArrayFurnitures = arrayListOf<ProductData>(ProductData(R.drawable.bed,
        "Wooden bed (Double)",
        "₹`25,000",
        "5 year"),
        ProductData(R.drawable.chair, "Swing chair", "₹10,000", "2 years"),
        ProductData(R.drawable.sofa, "Sofa (black)", "₹20,000", "2 years"),
        ProductData(R.drawable.lamp, "Bed lamp", "₹2,000", "1 years"))

    val itemArrayCloths =
        arrayListOf<ProductData>(ProductData(R.drawable.fullsleeve, "Shirt full sleeve", "₹500", "Cotton Dry fit"),
            ProductData(R.drawable.tshirt, "T shirt(Slim fit)", "₹399", "Cotton Dry fit"),
            ProductData(R.drawable.shortsleeve, "Short sleeve shirt", "₹499", "Sweat Shirt Dry Fit"),
            ProductData(R.drawable.tshirt_white, "T shirt (white)", "₹699", "Sweat Shirt Dry Fit"))


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val view = inflater.inflate(R.layout.fragment_product, container, false)
        recyclerView = view.findViewById(R.id.vwRecyclerNotesOne)

        layoutManager = LinearLayoutManager(activity as MainActivity)
        recyclerView.layoutManager = layoutManager
        product = arguments?.getInt("product")
        loadContents()
        return view
    }

    fun loadContents() {
        (activity as MainActivity).appBar.setExpanded(true)
        when (product) {
            1 -> {
                (activity as MainActivity).supportActionBar?.title = "Electronics"
                recyclerView.adapter =
                    ProductAdapter(activity as Context,
                        itemArrayElectronics)
            }
            2 -> {
                (activity as MainActivity).supportActionBar?.title = "Furtinures"
                recyclerView.adapter =
                    ProductAdapter(activity as Context,
                        itemArrayFurnitures)
            }
            3 -> {
                (activity as MainActivity).supportActionBar?.title = "Cloths"
                recyclerView.adapter =
                    ProductAdapter(activity as Context,
                        itemArrayCloths)
            }

        }

    }


}