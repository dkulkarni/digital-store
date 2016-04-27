package com.dk.digitalstore.action;

import com.dk.catalogclient.response.FetchWorksResponse;
import com.google.common.collect.Lists;

/**
 * Created by dkulkarni on 27/04/16.
 */
public class WorksFactory {

    public static FetchWorksResponse fetchAllWorks() {
        FetchWorksResponse fetchWorksResponse = new FetchWorksResponse();
        FetchWorksResponse.Work work1 = new FetchWorksResponse.Work();
        work1.setExif(new FetchWorksResponse.Work.Exif("NIKON", "NIKON CORPORATION"));

        FetchWorksResponse.Work work2 = new FetchWorksResponse.Work();
        work2.setExif(new FetchWorksResponse.Work.Exif("NIKON", "NIKON CORPORATION"));

        FetchWorksResponse.Work work3 = new FetchWorksResponse.Work();
        work3.setExif(new FetchWorksResponse.Work.Exif("CANON", "Canon EOS 20D"));

        fetchWorksResponse.setWork(Lists.newArrayList(work1, work2, work3));
        return fetchWorksResponse;
    }
}
