package com.dk.digitalstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class GetAllWorksResponse {

    private List<Work> work = newArrayList();

    public void addWork(Work work) {
        this.work.add(work);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class Work {
        private Integer id;

        private List<Url> url;

        private Exif exif;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @EqualsAndHashCode
        public static class Exif {

            private String make;

            private String model;
        }


        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @EqualsAndHashCode
        public static class Url {

            private String type;

            private String value;

        }
    }
}
