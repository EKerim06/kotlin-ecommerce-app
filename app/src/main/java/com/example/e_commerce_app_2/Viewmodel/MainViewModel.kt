package com.example.e_commerce_app_2.Viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerce_app_2.Model.BrandModel
import com.example.e_commerce_app_2.Model.SliderModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue

class MainViewModel : ViewModel() {

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    private val _banner = MutableLiveData<List<SliderModel>>()

    private val _brand = MutableLiveData<MutableList<BrandModel>>()

    public val banners: LiveData<List<SliderModel>> = _banner

    public val brand: LiveData<MutableList<BrandModel>> = _brand

    fun loadBanners() {
        val Ref = firebaseDatabase.getReference("Banner")
        Ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<SliderModel>()

                for (chilsSnapshot in snapshot.children) {

                    val element = chilsSnapshot.getValue(SliderModel::class.java)

                    if (element != null) {
                        lists.add(element)
                    }

                }
                _banner.value = lists
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    fun loadBrand() {
        val Ref = firebaseDatabase.getReference("Category")
        Ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val lists = mutableListOf<BrandModel>()

                for (chilsSnapshot in snapshot.children) {

                    val element = chilsSnapshot.getValue(BrandModel::class.java)

                    if (element != null) {

                        lists.add(element)

                    }

                }
                _brand.value = lists
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

}