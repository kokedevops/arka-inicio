package com.skillnest.arka.Users.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillnest.arka.controller.PermitController;
import com.skillnest.arka.dto.PermitDTO;
import com.skillnest.arka.service.PermitService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.emptyString;

@WebMvcTest(PermitController.class) // solo carga los controladores y configuración web
public class PermitTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PermitController permitController;
    @MockitoBean
    private PermitService permitService;

    @Test
    @DisplayName("the controller is injected correctly in the spring context")
    void ControllerIsInject(){
        assertNotNull(permitController, "the controller is injected ");
    }

    @Test
    void testGetMethod() throws Exception {
        when(permitService.getPermits()).thenReturn(List.of(new PermitDTO()));
        this.mockMvc.perform(get("/api/permits"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testPostMethod() throws  Exception{
        when(permitService.newPermit(any())).thenReturn(
                new PermitDTO(1L, "agregar usuarios", "tiene la potestad de agregar usuarios al sistema")
        );

        String bodyRequest = new ObjectMapper().writeValueAsString(
                new PermitDTO(1L, "agregar usuarios", "tiene la potestad de agregar usuarios al sistema")
        );

        MvcResult mvcResult = this.mockMvc.perform(
                        post("/api/permits")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(bodyRequest)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$['name']").value("agregar usuarios"))
                .andReturn();

        assertThat(mvcResult.getResponse().getContentAsString(),
                is(not(emptyString()))); // valida que la respuesta no esté vacía

        assertFalse(mvcResult.getResponse().getContentAsString().isEmpty()); // valida que la respuesta no esté vacía
    }

    @Test
    void testPostmanBadRequest() throws Exception{
        String bodyRequest = new ObjectMapper().writeValueAsString(
                new PermitDTO(null, "", "")
        );

        this.mockMvc.perform(post("/api/permits")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyRequest)
                )
                .andExpect(status().isBadRequest()) //valida que la respuesta se un badRequest o un 400
                .andExpect(jsonPath("$.errors").exists()) //valida que exista errors en la respuesta jdon
                .andReturn();
        verify(permitService, never()).newPermit(any()); // valida que no se invoque al servicio cuando hay errores
    }

    @Test
    void testUpdatePermit() throws Exception {
        Long id = 1L;
        PermitDTO updatePermit = new PermitDTO(id, "nuevo nombre", "nueva descripcion");
        when(permitService.updatePermit(eq(id), any(PermitDTO.class))).thenReturn(updatePermit);

        String bodyRequest = new ObjectMapper().writeValueAsString(updatePermit);

        MvcResult mvcResult = this.mockMvc.perform(
                put("/api/permits/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("nuevo nombre"))
                .andExpect(jsonPath("$.description"). value("nueva descripcion"))
                .andReturn();
    }

    @Test
    void updateNotFoundPermit404() throws  Exception{
        Long id = 3L;
        PermitDTO permitToUpdate = new PermitDTO(id, "nuevo nombre", "nueva descripción");

        when(permitService.updatePermit(eq(id), any(PermitDTO.class)))
                .thenThrow(new EntityNotFoundException("Permit not found"));

        String bodyRequest = new ObjectMapper().writeValueAsString(permitToUpdate);

        mockMvc.perform(put("/api/permits/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyRequest))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errors.message").value("Permit not found"));
    }

    @Test
    void updatePernmitBadRequest() throws  Exception{
        Long id =4L;
        PermitDTO ivalidPermit = new PermitDTO(id, "", "");
        String bodyrequest = new ObjectMapper().writeValueAsString(ivalidPermit);

        mockMvc.perform(put("/api/permits/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyrequest))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").exists())
                .andExpect(jsonPath("$.errors.name").value("the name of permit is mandatory"))
                .andExpect(jsonPath("$.errors.description").value("the description of permit is mandatory"));
        verify(permitService, never()).updatePermit(anyLong(), any(PermitDTO.class));
    }

    @Test
    void deletePermit() throws Exception {
        Long id=1L;
        doNothing().when(permitService).deletePermit(id);
        mockMvc.perform(delete("/api/permits/{id}", id))
                .andExpect(status().isNoContent());
        verify(permitService, times(1)).deletePermit(id);
    }
    @Test
    void deletePermitNotFound() throws Exception{
        Long id = 34L;
        doThrow(new EntityNotFoundException("Permit not found")).when(permitService).deletePermit(id);
        mockMvc.perform(delete("/api/permits/{id}", id))
                .andExpect(status().isNotFound());
    }

}
