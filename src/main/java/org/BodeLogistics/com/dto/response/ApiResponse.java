package org.BodeLogistics.com.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {
    private  boolean success;
    private Object data;
}
