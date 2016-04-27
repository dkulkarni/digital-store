package com.dk.digitalstore.internal;

import com.dk.catalogclient.response.FetchWorksResponse;

/**
 * Created by dkulkarni on 27/04/16.
 */
public class FetchWorksFactory {

    private static FetchWorksResponse fetchWorksResponse;

    public static FetchWorksResponse fetchWorks() {
        return fetchWorksResponse;
    }

    public static void setResponse(FetchWorksResponse response) {
        fetchWorksResponse = response;
    }
}
