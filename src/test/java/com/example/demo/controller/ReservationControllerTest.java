package com.example.demo.controller;

import com.example.demo.dto.Authentication;
import com.example.demo.dto.ReservationRequestDto;
import com.example.demo.entity.Role;
import com.example.demo.service.ReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = ReservationController.class)
class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ReservationService service;

    private MockHttpSession session;

    @BeforeEach
    void init() {
        Authentication authentication = new Authentication(1L, Role.USER);
        session = new MockHttpSession();
        session.setAttribute("USER_AUTH", authentication);
    }

    @Test
    void createReservation() throws Exception {
        ReservationRequestDto reservationRequestDto = new ReservationRequestDto(1L, 1L, "2024-02-12T12:00:00", "2024-02-12T12:00:00");
        mockMvc.perform(
                        post("/reservations")
                                .session(session)
                                .contentType(MediaType.APPLICATION_JSON) // 요청 타입 설정
                                .content(objectMapper.writeValueAsString(reservationRequestDto)))
                .andExpect(status().isOk());
    }

    @Test
    void updateReservation() throws Exception {
        Long reservationId = 1L;
        String text = "APPROVE";
        mockMvc.perform(
                        patch("/reservations/{reservationId}/update-status", reservationId)
                                .session(session)
                                .contentType(MediaType.TEXT_PLAIN) // 요청 타입 설정
                                .content(text))
                .andExpect(status().isOk());
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/reservations").session(session))
                .andExpect(status().isOk());
    }

    @Test
    void searchAll() throws Exception {
        Long userId = 1L;
        Long itemId = 1L;
        mockMvc.perform(
                        get("/reservations/search")
                                .session(session)
                                .param("userId", String.valueOf(userId))
                                .param("itemId", String.valueOf(itemId)))
                .andExpect(status().isOk());
    }
}