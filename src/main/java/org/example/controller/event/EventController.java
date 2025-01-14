package org.example.controller.event;

import lombok.AllArgsConstructor;
import org.example.application.event.service.EventService;
import org.example.controller.dto.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@RestController
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;
    @GetMapping("/{eventId}")
    public ResponseEntity<?> getEventById(@PathVariable long eventId) {
        return ResponseEntity.ok(ResponseBody.builder()
                .data(eventService.getEventById(eventId))
                .message("Get event success!")
                .build());
    }
    @GetMapping("/hot")
    public ResponseEntity<?> getHotEvent(@RequestParam(name = "page", defaultValue = "0") int page,
                                         @RequestParam(name = "perPage", defaultValue = "10") int perPage) {
        return ResponseEntity.ok(ResponseBody.builder()
                .data(eventService.getHotEvent(page, perPage))
                .message("Get hot event success!")
                .build());
    }
    @GetMapping("/recent")
    public ResponseEntity<?> getRecentEvent(@RequestParam(name = "page", defaultValue =  "0") int page,
                                            @RequestParam(name = "perPage", defaultValue = "10") int perPage) {
        return  ResponseEntity.ok(ResponseBody.builder()
                .data(eventService.getRecentEvent(page, perPage))
                .message("Get recent event success!")
                .build());
    }
    @GetMapping("/filter")
    public ResponseEntity<?> filterEvent(@RequestParam(name = "name") String name,
                                         @RequestParam(name = "status") String status,
                                         @RequestParam(name = "eventType") String eventType,
                                         @RequestParam(name = "startTime") LocalDateTime startTime,
                                         @RequestParam(name =  "endTime") LocalDateTime endTime,
                                         @RequestParam(name = "page", defaultValue = "0") int page,
                                         @RequestParam(name = "perPage", defaultValue = "10") int perPage) {
        return ResponseEntity.ok(ResponseBody.builder()
                .data(eventService.filterEvent(name, status, eventType, startTime,endTime, page, perPage))
                .message("Filter success!")
                .build());
    }
}
