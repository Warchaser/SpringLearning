package com.are.springlearning.bean

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.web.bind.annotation.ResponseBody

class ResponseBodyBean {

    @JsonProperty
    private var state = 200

    @JsonProperty
    private var message = "success"

    @JsonProperty
    private var body: Any? = null

    constructor() {
        val mapper = ObjectMapper()
        body = mapper.createObjectNode()
    }

    constructor(state: Int, message: String, body: Any) {
        this.state = state
        this.message = message
        this.body = body
    }
}
