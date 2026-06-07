//package com.example.Event_Management_System.DTO;
//
//import com.example.Event_Management_System.Modal.Event;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.MappingTarget;
//import org.springframework.stereotype.Component;
//
//@Mapper(componentModel = "spring")
//@Component
//public interface EventMapper {
//
//    // CreateEventRequest → Event entity
//    @Mapping(source = "categoryId", target = "category.id")
//    @Mapping(source = "organizerId", target = "organizer.id")
//    Event toEntity(CreateEventRequest request);
//
//    // Event → EventResponse
//    @Mapping(source = "organizer.id", target = "organizerId")
//    @Mapping(source = "organizer.name", target = "organizerName")
//    @Mapping(source = "category.id", target = "categoryId")
//    @Mapping(source = "category.name", target = "categoryName")
//    EventResponse toResponse(Event event);
//
//    // UpdateEventRequest → Event
//    @Mapping(source = "categoryId", target = "category.id")
//    void updateEvent(UpdateEventRequest request, @MappingTarget Event event);
//}
