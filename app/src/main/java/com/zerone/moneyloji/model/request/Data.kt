package com.zerone.moneyloji.model.request

data class Data(
    val hintText: String,
    val id: String,
    val label: String,
    val required: String,
    val type: String,
    val validator: List<Validator>
)