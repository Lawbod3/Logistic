package org.BodeLogistics.com.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.BodeLogistics.com.data.models.DispatchActivity;
import org.BodeLogistics.com.data.models.DispatchRider;

import java.util.List;

@Data
@AllArgsConstructor
public class DeliveryResponse {
    private DispatcherActivityResponse dispatcherActivityResponse;
    private DispatchActivity activity;
}
