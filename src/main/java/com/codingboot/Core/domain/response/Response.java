package com.codingboot.Core.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private int total;
    private List<Item> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item{
        private Long id;
        private Long questionId;
        private String name;
        private Long questionTypeId;
        private List<OptionDTO> options;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OptionDTO {
        private Long id;
        private Long questionId;
        private String name;
        private Boolean isAnswer;
    }
}


