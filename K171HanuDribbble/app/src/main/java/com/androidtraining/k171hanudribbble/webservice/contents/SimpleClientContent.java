package com.androidtraining.k171hanudribbble.webservice.contents;

import com.androidtraining.k171hanudribbble.webservice.inputs.BaseInput;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

public class SimpleClientContent extends RequestBody {
    private ObjectMapper mapper;
    private BaseInput input;
    private final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    public SimpleClientContent(ObjectMapper mapper, BaseInput input) {
        this.mapper = mapper;
        this.input = input;
    }

    @Override
    public MediaType contentType() {
        return MEDIA_TYPE;
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        mapper.writeValue(sink.outputStream(), input);
        sink.outputStream().close();
    }
}
