package com.algotracker.AlgotrackerProject.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {
    List<T> content;

    int page;

    int size;

    long totalElements;

    int totalPages;
}
