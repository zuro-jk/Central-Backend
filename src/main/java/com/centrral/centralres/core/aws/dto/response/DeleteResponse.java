package com.centrral.centralres.core.aws.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeleteResponse {
    private Long fileId;
    private Long deletedById;
    private String deletedByUsername;
}