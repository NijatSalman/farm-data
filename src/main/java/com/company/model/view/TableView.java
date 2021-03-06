package com.company.model.view;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TableView<T> {
    private int recordCount;
    private long totalElements;
    private int totalPages;
    private List<T> list;
}
