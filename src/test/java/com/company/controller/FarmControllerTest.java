package com.company.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FarmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenCsvFileUploaded_thenVerifyStatus() throws Exception {
        MockMultipartFile mockitoMultipartFile
                = new MockMultipartFile(
                "file",
                "hello.txt",
                "text/csv",
                this.getClass().getClassLoader()
                        .getResourceAsStream("test.csv")
        );

        mockMvc.perform(multipart("/farm/upload").file(mockitoMultipartFile))
                .andExpect(status().isOk());

    }

    @Test
    void whenExcelFileUploaded_thenVerifyStatus() throws Exception {
        MockMultipartFile mockitoMultipartFile
                = new MockMultipartFile(
                "file",
                "hello.txt",
                "application/vnd.ms-excel",
                this.getClass().getClassLoader()
                        .getResourceAsStream("test.xlsx")
        );

        mockMvc.perform(multipart("/farm/upload").file(mockitoMultipartFile))
                .andExpect(status().isOk());

    }

    @Test
    void whenFileContentTypeIsNotCsvOrExcel_thenFail() throws Exception {
        MockMultipartFile mockitoMultipartFile
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "test".getBytes()
        );

        mockMvc.perform(multipart("/farm/upload").file(mockitoMultipartFile))
                .andExpect(status().isBadRequest());
    }
}
