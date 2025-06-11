package org.BodeLogistics.com.dto.request;

import lombok.Data;
import org.BodeLogistics.com.data.models.ActivityType;

@Data
public class ActivityStatusRequest {
    private String activityId;
    private boolean successful;
    private boolean cancelled;

}
