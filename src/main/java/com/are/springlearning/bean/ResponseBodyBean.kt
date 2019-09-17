package com.are.springlearning.bean

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper

class ResponseBodyBean {

    @JsonProperty
    private var state = 200

    @JsonProperty
    private var message = "success"

    @JsonProperty
    private var body: Any? = ObjectMapper().createObjectNode()

    constructor() {

    }

    constructor(state: Int, message: String){
        this.state = state
        this.message = message
    }

    constructor(state: Int, message: String, body: Any) {
        this.state = state
        this.message = message
        this.body = body
    }

}
