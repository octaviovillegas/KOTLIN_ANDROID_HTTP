package com.example.comunicacionhttp_istic

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class AdaptadorLsvSimple(var contexto:Context, listado:ArrayList<Pais>) :BaseAdapter(){

    var listado:ArrayList<Pais>?=null
    init {
        this.listado=listado
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
      //  TODO("Not yet implemented")
        var holder :ViewHolder?=null
        var vista:View?=convertView
        if(vista==null)
        {
            vista=LayoutInflater.from(contexto).inflate(R.layout.filaconmasdatos,null)
            holder =ViewHolder(vista)
            vista.tag=holder
        }
        else
        {
            holder=vista.tag as? ViewHolder
        }
        val unPais=getItem(position)as Pais

        holder?.nombre?.text=unPais.nombre
        holder?.region?.text=unPais.region

        return vista!!
    }

    override fun getItem(position: Int): Any {
      //  TODO("Not yet implemented")
        return listado?.get(position)!!
    }

    override fun getItemId(position: Int): Long {
        //TODO("Not yet implemented")
        return  position.toLong()
    }

    override fun getCount(): Int {
       //TODO("Not yet implemented")
        return listado?.count()!!// para optener el valor !!
    }
    private class ViewHolder(vista:View){
        var nombre :TextView?=null
        var region:TextView?=null
        init {
            nombre=vista.findViewById(R.id.textView3)
            region=vista.findViewById(R.id.txtBandera)
        }
    }
}