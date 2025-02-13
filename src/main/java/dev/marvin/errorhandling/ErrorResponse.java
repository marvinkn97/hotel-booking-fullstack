package dev.marvin.errorhandling;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse<T> {
    private Date timestamp;
    private Boolean success;
    private String error;
    private T errorDetail;
}
