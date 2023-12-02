package com.gl4.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale
import kotlin.random.Random

class StudentAdapter(private val context: Context, private var studentList: ArrayList<Student>)  :
    RecyclerView.Adapter<StudentAdapter.ViewHolder>() ,Filterable{
    val list = studentList
    var dataFilterList = ArrayList<Student>()
    init {
        // associer le tableau des données initiales
        dataFilterList = studentList
    }



    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val logoView : ImageView
        val nameView : TextView
        val lastNameView: TextView
        val checked:CheckBox
        init {
            nameView = itemView.findViewById(R.id.name)
            lastNameView = itemView.findViewById(R.id.last_name)
            logoView = itemView.findViewById(R.id.imageView)
            checked=itemView.findViewById(R.id.presentCheckbox)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.student_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentAdapter.ViewHolder, position: Int) {
        val student = studentList[position]
        val n = Random.nextInt(1, 4)

        var imageName: String = if (student.gender == 'F') {
            "woman_$n"
        } else {
            "man_$n"
        }
        val imageResId: Int = context.resources.getIdentifier(imageName, "drawable", context.packageName)
        holder.logoView.setImageResource(imageResId)
        holder.nameView.text = student.name
        holder.lastNameView.text = student.lastName
        holder.checked.isChecked = student.present

        holder.checked.setOnClickListener {
            val isChecked = holder.checked.isChecked
            student.present = isChecked
            Toast.makeText(context, "Student ${student.name} is ${if (isChecked) "present" else "absent"}", Toast.LENGTH_SHORT).show()

        }
    }


    override fun getItemCount(): Int {
        return studentList.size
    }
    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                var bool :Boolean
                bool = charSearch=="present"
                if (charSearch.isEmpty()) {
                    dataFilterList = list
                } else {
                    val resultList = ArrayList<Student>()
                        for (student in list) {
                            if (student.present==bool

                            ) {
                                resultList.add(student)
                            }
                        }
                        dataFilterList = resultList
                    }
                val filterResults = FilterResults()
                filterResults.values = dataFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                dataFilterList = results?.values as ArrayList<Student>
                studentList = dataFilterList
                notifyDataSetChanged()
                println(dataFilterList.size)

            }

        }
    }
}
