package com.dk.catalogclient.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.List;


@Data
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@XmlRootElement(name = "works")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FetchWorksResponse {

    private List<Work> work;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Work {

        private Integer id;

        @XmlElementWrapper(name = "urls")
        private List<Url> url;

        private Exif exif;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @EqualsAndHashCode
        @XmlAccessorType(XmlAccessType.FIELD)
        public static class Exif {

            private String make;

            private String model;
        }


        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @EqualsAndHashCode
        @XmlAccessorType(XmlAccessType.FIELD)
        public static class Url {

            @XmlAttribute
            private String type;

            @XmlValue
            private String value;

        }
    }
}

