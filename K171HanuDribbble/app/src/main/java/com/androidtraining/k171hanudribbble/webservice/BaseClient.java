package com.androidtraining.k171hanudribbble.webservice;

import android.util.Log;

import com.androidtraining.k171hanudribbble.providers.IHttpHeaderProvider;
import com.androidtraining.k171hanudribbble.webservice.contents.SimpleClientContent;
import com.androidtraining.k171hanudribbble.webservice.inputs.BaseInput;
import com.androidtraining.k171hanudribbble.webservice.outputs.BaseOutput;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Callable;

import bolts.Task;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public abstract class BaseClient {

    private IHttpHeaderProvider httpHeaderProvider;
    private OkHttpClient client;

    public BaseClient(IHttpHeaderProvider httpHeaderProvider){
        this.httpHeaderProvider = httpHeaderProvider;
        this.client = new OkHttpClient.Builder().build();
    }

    protected abstract HttpUrl baseUrl();

    protected <T> Task<BaseOutput<T>> executeAsync(final BaseInput input, final Class<T> outputDataType){
        return Task.callInBackground(new Callable<BaseOutput<T>>() {
            @Override
            public BaseOutput<T> call() throws Exception {
                BaseOutput<T> output = new BaseOutput<T>();

                ObjectMapper mapper = createObjectMapper();
                JsonFactory jsonFactory = new JsonFactory();

                Response response = null;

                try {
                    response = client.newCall(createRequest(input)).execute();
                    output.setStatusCode(response.code());
                    output.setStatusMessage(response.message());

                    if(response.isSuccessful()){
                        response.body();
                        JsonParser jsonParser = jsonFactory.createParser(response.body().string());
                        output.setData(mapper.readValue(jsonParser, outputDataType));
                        response.body().close();
                    }
                } catch (IOException e) {
                    output.setStatusCode(response.code());
                    output.setStatusMessage(e.getMessage() != null ? e.getMessage() : e.getClass().getName());
                    e.printStackTrace();
                }

                return output;
            }
        });
    }

    private Request createRequest(BaseInput input){
        Request.Builder requestBuilder = new Request.Builder();

        if(httpHeaderProvider != null){
            if(httpHeaderProvider.getHeaders() != null){
                requestBuilder.headers(httpHeaderProvider.getHeaders());
            }
        }

        requestBuilder.url(buildHttpUrl(input));

        if(input.getMethod() != BaseInput.HttpMethod.GET){
            switch(input.getMethod()){
                case POST:
                    requestBuilder.post(new SimpleClientContent(createObjectMapper(), input));
                    break;

                case PUT:
                    requestBuilder.put(new SimpleClientContent(createObjectMapper(), input));

                    break;

                case DELETE:
                    requestBuilder.delete(new SimpleClientContent(createObjectMapper(), input));

                    break;

                default: break;
            }
        }

        return requestBuilder.build();
    }

    private ObjectMapper createObjectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

        return mapper;
    }

    private HttpUrl buildHttpUrl(BaseInput input){
        //Complete the base URl
        HttpUrl.Builder temHttpUrlBuilder = baseUrl().newBuilder();
        if(input.getResource().contains("/")){
            for(String str : input.getResource().split("/")){
                temHttpUrlBuilder.addPathSegment(str);
            }
        } else {
            temHttpUrlBuilder.addPathSegment(input.getResource());
        }
        HttpUrl httpUrl = temHttpUrlBuilder.build();

        HttpUrl.Builder fullUrlBuilder = httpUrl.newBuilder();


        for(Map.Entry<String, String> pathSegment : input.getPathSegments().entrySet()){
            int index = httpUrl.pathSegments().indexOf(pathSegment.getKey());
            fullUrlBuilder.setPathSegment(index, pathSegment.getValue());
        }

        for(Map.Entry<String, String> queryParameter : input.getQueryParamters().entrySet()){
            fullUrlBuilder.setQueryParameter(queryParameter.getKey(), queryParameter.getValue());
        }

        Log.i("AAAAAAAAAAAAAAAAA", fullUrlBuilder.toString());
        return fullUrlBuilder.build();

    }

}

