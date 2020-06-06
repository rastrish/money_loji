package com.zerone.moneyloji.ui.main

import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import android.util.Log
import android.view.ContextThemeWrapper
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.gson.Gson
import com.zerone.moneyloji.R
import com.zerone.moneyloji.utill.Status
import com.zerone.moneyloji.model.request.FormData
import com.zerone.moneyloji.model.response.DataList
import com.zerone.moneyloji.model.response.ResponseData
import com.zerone.moneyloji.model.response.ResponseType
import com.zerone.moneyloji.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import com.zerone.moneyloji.utill.Validator


class MainActivity : BaseActivity<MainViewModel>(
    MainViewModel::class
) {


    lateinit var button: Button
    var dataList = ArrayList<DataList>()
    lateinit var data: ArrayList<ResponseType>

    val gson: Gson by inject()

    override fun provideLayoutId(): Int = R.layout.activity_main


    override fun setupObservers() {
        super.setupObservers()

        viewModel.jsonString.observe(this, androidx.lifecycle.Observer {
            parseJsonGenerateUI(it)
        })
    }

    override fun setupView(savedInstanceState: Bundle?) {
        data = ArrayList()
        button = Button(
            ContextThemeWrapper(this, R.style.Widget_MaterialComponents_Button),
            null,
            R.style.Widget_MaterialComponents_Button
        )

        // to get JSON from resource Later can be fetched by Network Call
        viewModel.loadGrades()

        button.setOnClickListener {

            for (i in 0 until dataList.size) {

                if (dataList[i].editText.text.toString().isEmpty()) {
                    Toast.makeText(this, "Please Enter all the details", Toast.LENGTH_LONG).show()
                    break

                } else {
                    val formList = Validator.valid(dataList)
                    if (formList.isNotEmpty()) {
                        val successValidation =
                            formList.filter { it.resource.status == Status.SUCCESS }
                        if (successValidation.size == formList.size) {
                            data.add(
                                ResponseType(
                                    dataList[i].id,
                                    dataList[i].editText.text.toString()
                                )
                            )
                            val responseData = ResponseData(data)
                            val submissionJSON = gson.toJson(responseData)
                            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                        } else {
                            val failedValidation =
                                (formList.filter { it.resource.status == Status.ERROR }[0]).resource
                            Toast.makeText(
                                this,
                                failedValidation.data.toString(),
                                Toast.LENGTH_SHORT
                            )
                                .show()

                        }
                    }
                }
            }
        }
    }

            // Generate UI
            private fun parseJsonGenerateUI(json: String) {
                val data = gson.fromJson(json, FormData::class.java)
                for (f in data!!.data) {
                    if (f.type == "EditText") {
                        val editText = EditText(this)
                        editText.hint = f.hintText
                        content.addView(editText)
                        for (v in f.validator) {
                            if (v.type == "Regex") {
                                dataList.add(
                                    DataList(
                                        editText,
                                        v.Value,
                                        f.id
                                    )
                                )
                            }
                            if (v.Value == "Alphabetic") {
                                editText.filters = arrayOf(object : InputFilter {
                                    override fun filter(
                                        source: CharSequence?,
                                        start: Int,
                                        end: Int,
                                        dest: Spanned?,
                                        dstart: Int,
                                        dend: Int
                                    ): CharSequence {
                                        if (source!! == "") {
                                            return source
                                        }
                                        if (source.toString().matches("[a-zA-Z\\s]+".toRegex())) {
                                            return source
                                        }
                                        return ""
                                    }

                                })
                            }
                        }
                    }
                }

                Log.e("data", dataList.toString())
                button.text = "submit"
                button.setPadding(8, 8, 8, 8)
                button.setBackgroundColor(resources.getColor(R.color.colorAccent))
                content.addView(button)
            }

        }