package com.centrral.centralres.features.employees.dto.employee.response;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PositionResponse {
    private Long id;
    private String name;
    private String description;

    private Set<RoleSummary> roles;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RoleSummary {
        private Long id;
        private String name;
    }
}
