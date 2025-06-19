package org.BodeLogistics.com.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.BodeLogistics.com.data.models.DispatchActivity;

@Data
@AllArgsConstructor
public class DeliveryResponse {
    private DispatcherProfileResponse dispatcherProfileResponseResponse;
    private DispatchActivity activity;
}
