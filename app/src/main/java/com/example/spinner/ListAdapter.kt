package com.example.spinner

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ListAdapter<T>(context: Context, personList:MutableList<Person>):
    ArrayAdapter<Person>(context,R.layout.list_item,personList){

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val person = getItem(position)
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)
        }
        val personNameTV = view?.findViewById<TextView>(R.id.personNameTV)
        val personSurnameTV = view?.findViewById<TextView>(R.id.personSurnameTV)
        val personAgeTV = view?.findViewById<TextView>(R.id.personAgeTV)
        val personRoleTV = view?.findViewById<TextView>(R.id.personRoleTV)

        personNameTV?.text = person?.name
        personSurnameTV?.text = person?.surname
        personAgeTV?.text = "${person?.age} года(-лет)"
        personRoleTV?.text = person?.role

        return view!!
    }
}