package com.centrral.centralres.features.employees.dto.schedule.response;

import java.time.LocalTime;

import com.centrral.centralres.features.employees.enums.DayOfWeekEnum;

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
public class ScheduleResponse {
    private Long id;
    private Long employeeId;
    private String employeeName;
    private String positionName;
    private DayOfWeekEnum dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
}
