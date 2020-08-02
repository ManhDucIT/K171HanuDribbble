package com.androidtraining.k171hanudribbble.providers;

import com.androidtraining.k171hanudribbble.webservice.clients.DribbbleClient;
import com.androidtraining.k171hanudribbble.webservice.clients.IDribbbleClient;
import com.androidtraining.k171hanudribbble.webservice.clients.IOauth2Client;
import com.androidtraining.k171hanudribbble.webservice.clients.Oauth2Client;

public class ServicesProvider {

    private IAuthorizationProvider authorizationProvider;
    private IHttpHeaderProvider httpHeaderProvider;
    private IOauth2Client oauth2Client;
    private IDribbbleClient dribbbleClient;

    private ServicesProvider(){
        authorizationProvider = new AuthorizationProvider();
        httpHeaderProvider = new HttpHeaderProvider(authorizationProvider);
        oauth2Client = new Oauth2Client(httpHeaderProvider);
        dribbbleClient = new DribbbleClient(httpHeaderProvider);
    }

    private static class SingletonHelper{
        private static final ServicesProvider INSTANCE = new ServicesProvider();
    }

    public static ServicesProvider getInstance(){
        return SingletonHelper.INSTANCE;
    }

    public IAuthorizationProvider getAuthorizationProvider(){
        return authorizationProvider;
    }

    public IOauth2Client getOauth2Client(){
        return oauth2Client;
    }

    public IDribbbleClient getDribbbleClient(){
        return dribbbleClient;
    }

}
