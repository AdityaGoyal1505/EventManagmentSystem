//package com.example.Event_Management_System.DTO;
//
//import com.example.Event_Management_System.Modal.Booking;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.MappingTarget;
//import org.springframework.stereotype.Component;
//
//@Mapper(componentModel = "spring")
//@Component
//public interface BookingMapper {
//
//    @Mapping(source = "userId", target = "user.id")
//    @Mapping(source = "eventId", target = "event.id")
//    Booking toEntity(CreateBookingRequest request);
//
//    @Mapping(source = "user.id", target = "userId")
//    @Mapping(source = "event.id", target = "eventId")
//    BookingResponse toResponse(Booking booking);
//}
