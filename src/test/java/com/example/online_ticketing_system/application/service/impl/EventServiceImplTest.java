package com.example.online_ticketing_system.application.service.impl;

import com.example.online_ticketing_system.application.dto.event.EventCreateDTO;
import com.example.online_ticketing_system.application.dto.event.EventResponseDTO;
import com.example.online_ticketing_system.application.dto.event.EventUpdateDTO;
import com.example.online_ticketing_system.application.mapper.EventMapper;
import com.example.online_ticketing_system.domain.exception.AlreadyDeletedException;
import com.example.online_ticketing_system.domain.exception.ResourceNotFoundException;
import com.example.online_ticketing_system.domain.model.Event;
import com.example.online_ticketing_system.domain.model.EventCategory;
import com.example.online_ticketing_system.domain.model.EventHall;
import com.example.online_ticketing_system.domain.model.User;
import com.example.online_ticketing_system.domain.repository.EventCategoryRepository;
import com.example.online_ticketing_system.domain.repository.EventHallRepository;
import com.example.online_ticketing_system.domain.repository.EventRepository;
import com.example.online_ticketing_system.domain.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class EventServiceImplTest {

    @InjectMocks
    private EventServiceImpl eventService;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private EventCategoryRepository eventCategoryRepository;

    @Mock
    private EventHallRepository eventHallRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EventMapper eventMapper;

    @BeforeEach
    void setUp() {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("testUser", null)
        );
    }

    @Nested
    class CreateEventTests {
        @Test
        void testCreateEvent_Success() {
            EventCreateDTO eventCreateDTO = new EventCreateDTO();
            eventCreateDTO.setName("Test Event");
            eventCreateDTO.setDescription("Test Description");
            eventCreateDTO.setEventHallId(1L);
            eventCreateDTO.setEventCategoryId(1L);
            eventCreateDTO.setCapacity(20);
            eventCreateDTO.setStartDate(LocalDateTime.now().plusDays(1));
            eventCreateDTO.setEndDate(LocalDateTime.now().plusDays(2));

            User user = new User();
            EventCategory eventCategory = new EventCategory();
            EventHall eventHall = new EventHall();
            Event event = new Event();
            Event savedEvent = new Event();

            EventResponseDTO eventResponseDTO = new EventResponseDTO();
            eventResponseDTO.setName("Test Event");

//        Mockito.when(userRepository.findByUsername(Mockito.anyString()))
//                .thenReturn(Optional.of(user));
            Mockito.when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));
            Mockito.when(eventCategoryRepository.findById(1L)).thenReturn(Optional.of(eventCategory));
            Mockito.when(eventHallRepository.findById(1L)).thenReturn(Optional.of(eventHall));
            Mockito.when(eventRepository.save(event)).thenReturn(savedEvent);
            Mockito.when(eventMapper.toEntity(eventCreateDTO)).thenReturn(event);
            Mockito.when(eventMapper.toResponseDTO(savedEvent)).thenReturn(eventResponseDTO);

            UserDetails userDetails = org.springframework.security.core.userdetails.User
                    .withUsername("testUser")
                    .password("password")
                    .authorities(new SimpleGrantedAuthority("ROLE_USER"))
                    .build();

            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities())
            );

            EventResponseDTO result = eventService.create(eventCreateDTO);

            Assertions.assertNotNull(result);
            Assertions.assertEquals(eventCreateDTO.getName(), result.getName());

            Mockito.verify(userRepository).findByUsername("testUser");//verify onu yoxlayir ki heqiqeten burda gonderilen meselen findByUsername ve save cagirilibmi? cagirilibsa sayini yazir nece defe cagirirlib deye
            Mockito.verify(eventRepository).save(event);
        }

        @Test
        void testCreateEvent_UserNotFound_ThrowException() {
            EventCreateDTO eventCreateDTO = new EventCreateDTO();
            Mockito.when(userRepository.findByUsername("testUser")).thenReturn(Optional.empty());
            Assertions.assertThrows(ResourceNotFoundException.class, () -> eventService.create(eventCreateDTO));
        }
    }

    @Nested
    class GetEventTests {
        @Test
        void testGetById_WhenEventExists_ReturnEventResponseDTO() {
            EventResponseDTO eventResponseDTO = new EventResponseDTO();
            eventResponseDTO.setName("Sample Event");
            Event event = new Event();
            long eventId = 1L;

            Mockito.when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
            Mockito.when(eventMapper.toResponseDTO(event)).thenReturn(eventResponseDTO);

            EventResponseDTO result = eventService.getById(eventId);
            Assertions.assertNotNull(result);
            Assertions.assertEquals(eventResponseDTO.getName(), result.getName());

            Mockito.verify(eventRepository).findById(eventId);
            Mockito.verify(eventMapper).toResponseDTO(event);
        }

        @Test
        void testGetById_WhenEventDoesNotExist_ReturnNull() {
            long eventId = 1L;

            Mockito.when(eventRepository.findById(eventId)).thenReturn(Optional.empty());

            Assertions.assertNull(eventService.getById(eventId));

            Mockito.verify(eventRepository).findById(eventId);
            Mockito.verifyNoInteractions(eventMapper);
        }

        @Test
        void testGetAll_WhenEventsExist_ReturnList() {
            Event event1 = new Event();
            Event event2 = new Event();

            EventResponseDTO dto1 = new EventResponseDTO();
            dto1.setName("Event 1");
            EventResponseDTO dto2 = new EventResponseDTO();
            dto2.setName("Event 2");

            Mockito.when(eventRepository.findAll()).thenReturn(List.of(event1, event2));
            Mockito.when(eventMapper.toResponseDTO(event1)).thenReturn(dto1);
            Mockito.when(eventMapper.toResponseDTO(event2)).thenReturn(dto2);

            List<EventResponseDTO> result = eventService.getAll();

            Assertions.assertEquals(2, result.size());
            Assertions.assertEquals("Event 1", result.get(0).getName());
            Assertions.assertEquals("Event 2", result.get(1).getName());

            Mockito.verify(eventRepository).findAll();
            Mockito.verify(eventMapper, Mockito.times(2)).toResponseDTO(Mockito.any());
        }
    }

    @Nested
    class DeleteEventTests {
        @Test
        void testDeleteById_WhenEventExists_Success() {
            Long eventId = 1L;
            Event event = new Event();
            event.setDeletedAt(null);
            event.setId(eventId);

            Mockito.when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));

            eventService.delete(eventId);

            Mockito.verify(eventRepository).findById(eventId);
            Mockito.verify(eventRepository).delete(eventId);
        }

        @Test
        void testDeleteById_WhenEventDoesNotExist_ThrowException() {
            Long eventId = 1L;

            Mockito.when(eventRepository.findById(eventId)).thenReturn(Optional.empty());
            Assertions.assertThrows(ResourceNotFoundException.class, () -> eventService.delete(eventId));
            Mockito.verify(eventRepository).findById(eventId);
            Mockito.verify(eventRepository, Mockito.never()).delete(Mockito.any());
        }

        @Test
        void testDeleteById_WhenAlreadyDeletedEvent_ThrowException() {
            Long eventId = 1L;
            Event event = new Event();
            event.setDeletedAt(LocalDateTime.now());
            event.setId(eventId);

            Mockito.when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));

            Assertions.assertThrows(AlreadyDeletedException.class, () -> eventService.delete(eventId));

            Mockito.verify(eventRepository).findById(eventId);
            Mockito.verify(eventRepository, Mockito.never()).delete(Mockito.any());
        }
    }


    @Nested
    class UpdateEventTests {
        @Test
        void testUpdate_WhenEventExists_Success() {
            Long eventId = 1L;
            EventUpdateDTO updateDTO = new EventUpdateDTO();
            updateDTO.setName("Updated Name");
            updateDTO.setEventCategoryId(2L);
            updateDTO.setEventHallId(3L);

            Event existingEvent = new Event();
            EventCategory category = new EventCategory();
            EventHall hall = new EventHall();
            Event updatedEvent = new Event();

            EventResponseDTO responseDTO = new EventResponseDTO();
            responseDTO.setName("Updated Name");

            Mockito.when(eventRepository.findById(eventId)).thenReturn(Optional.of(existingEvent));
            Mockito.when(eventCategoryRepository.findById(2L)).thenReturn(Optional.of(category));
            Mockito.when(eventHallRepository.findById(3L)).thenReturn(Optional.of(hall));
            Mockito.when(eventRepository.save(existingEvent)).thenReturn(updatedEvent);
            Mockito.when(eventMapper.toResponseDTO(updatedEvent)).thenReturn(responseDTO);

            EventResponseDTO result = eventService.update(eventId, updateDTO);

            Assertions.assertNotNull(result);
            Assertions.assertEquals("Updated Name", result.getName());

            Mockito.verify(eventRepository).findById(eventId);
            Mockito.verify(eventCategoryRepository).findById(2L);
            Mockito.verify(eventHallRepository).findById(3L);
            Mockito.verify(eventRepository).save(existingEvent);
        }

        @Test
        void testUpdate_WhenEventNotFound_ThrowException() {
            Long eventId = 1L;
            EventUpdateDTO updateDTO = new EventUpdateDTO();
            updateDTO.setEventCategoryId(2L);
            updateDTO.setEventHallId(3L);

            Mockito.when(eventRepository.findById(eventId)).thenReturn(Optional.empty());

            Assertions.assertThrows(ResourceNotFoundException.class, () -> eventService.update(eventId, updateDTO));

            Mockito.verify(eventRepository).findById(eventId);
            Mockito.verifyNoInteractions(eventCategoryRepository);
            Mockito.verifyNoInteractions(eventHallRepository);
        }

        @Test
        void testUpdate_WhenCategoryNotFound_ThrowException() {
            Long eventId = 1L;
            EventUpdateDTO updateDTO = new EventUpdateDTO();
            updateDTO.setEventCategoryId(2L);
            updateDTO.setEventHallId(3L);

            Event existingEvent = new Event();

            Mockito.when(eventRepository.findById(eventId)).thenReturn(Optional.of(existingEvent));
            Mockito.when(eventCategoryRepository.findById(2L)).thenReturn(Optional.empty());

            Assertions.assertThrows(ResourceNotFoundException.class, () -> eventService.update(eventId, updateDTO));

            Mockito.verify(eventRepository).findById(eventId);
            Mockito.verify(eventCategoryRepository).findById(2L);
            Mockito.verifyNoInteractions(eventHallRepository);
        }

        @Test
        void testUpdate_WhenHallNotFound_ThrowException() {
            Long eventId = 1L;
            EventUpdateDTO updateDTO = new EventUpdateDTO();
            updateDTO.setEventCategoryId(2L);
            updateDTO.setEventHallId(3L);

            Event existingEvent = new Event();
            EventCategory category = new EventCategory();

            Mockito.when(eventRepository.findById(eventId)).thenReturn(Optional.of(existingEvent));
            Mockito.when(eventCategoryRepository.findById(2L)).thenReturn(Optional.of(category));
            Mockito.when(eventHallRepository.findById(3L)).thenReturn(Optional.empty());

            Assertions.assertThrows(ResourceNotFoundException.class, () -> eventService.update(eventId, updateDTO));

            Mockito.verify(eventRepository).findById(eventId);
            Mockito.verify(eventCategoryRepository).findById(2L);
            Mockito.verify(eventHallRepository).findById(3L);
        }
    }


}
