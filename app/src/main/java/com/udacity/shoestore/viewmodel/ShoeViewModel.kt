package com.udacity.shoestore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoes

class ShoeViewModel : ViewModel() {
    private val _shoeList = MutableLiveData<MutableList<Shoes>>()
    val shoeList: LiveData<MutableList<Shoes>>
        get() = _shoeList

    var newShoeName = ""
    var newShoeSize = ""
    var newCompanyName = ""
    var newShoeDescription = ""

    init {
        _shoeList.value = mutableListOf()
        mockData()
    }

    fun resetNewShoe() {
        newShoeName = ""
        newShoeSize = ""
        newCompanyName = ""
        newShoeDescription = ""
    }

    fun addNewShoe(): Boolean {

        if (!validateNewShoeInputs()) return false

        addShoe(
            newShoeName,
            newShoeSize.toInt(),
            newCompanyName,
            newShoeDescription
        )

        return true
    }

    private fun mockData() {
        var count = 1
        while (count <= 3) {
            addShoe("Shoe $count", 7, "Company $count", "Shoe Desc $count")
            ++count
        }
    }

    private fun addShoe(name: String, size: Int, company: String, description: String) {
        val shoes = Shoes(name, size, company, description)
        _shoeList.value!!.add(shoes)
    }

    private fun validateNewShoeInputs(): Boolean {

        if (newShoeName.isEmpty() ||
            newCompanyName.isEmpty() ||
            newShoeSize.isEmpty()  ||
            newShoeDescription.isEmpty()) {
            return false
        }

        return true
    }
}