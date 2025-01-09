package org.example.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.domain.entity.enums.EventType;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Event {
    private long eId;
    private String eName;
    private String eDescription;
    private LocalDateTime eStartTime;
    private LocalDateTime eEndTime;
    private EventType eType;
    private String eAddress;
    private String eMapUrl;
    private String eStatus;
    private List<KeyValueItem> eIntroduction;
    private String eThumbnail;
    private Company company;
}
