package com.zerone.moneyloji.utill

import com.zerone.moneyloji.model.response.DataList
import java.util.regex.Pattern

object Validator {

    fun valid(dataList: ArrayList<DataList>): ArrayList<Validation> {
        val list = ArrayList<Validation>()
        for (i in 0 until dataList.size) {
            val matches = isValid(dataList[i].regex, dataList[i].editText.text.toString())
            if (!matches) {
                list.add(
                    Validation(
                        dataList[i].id,
                        Resource.error(dataList[i].id + "is invalid")
                    )
                )
            } else {
                list.add(
                    Validation(
                        dataList[i].id,
                        Resource.success(dataList[i].id)
                    )
                )
            }
        }

        return list
    }

    fun isValid(regex: String, data: String): Boolean {
        val ps: Pattern = Pattern.compile(regex)
        val matcher = ps.matcher(data)
        return matcher.matches()
    }


}