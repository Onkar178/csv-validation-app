package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.ApiResponse;
import com.example.demo.model.ErrorDetail;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class CsvController {

    @PostMapping("/upload")
    public ApiResponse uploadFile(@RequestParam("file") MultipartFile file) {

        List<ErrorDetail> errors = new ArrayList<>();

        //File validation
        if (file.isEmpty() || !file.getOriginalFilename().toLowerCase().endsWith(".csv")) {
            errors.add(new ErrorDetail(0, "Only CSV files are allowed"));
            return new ApiResponse("error", errors);
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

            String line;
            int rowNumber = 0;

            while ((line = reader.readLine()) != null) {
                rowNumber++;

                //Skip header
                if (rowNumber == 1) continue;

                //Skip completely empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(",", -1); // keep empty values

                //Invalid row check
                if (data.length < 2) {
                    errors.add(new ErrorDetail(rowNumber, "Invalid row format"));
                    continue;
                }

                String name = data[0].trim();
                String dob = data[1].trim();

                //Name validation
                if (name.isEmpty()) {
                    errors.add(new ErrorDetail(rowNumber, "Name is missing"));
                }

                //DOB validation
                if (dob.isEmpty()) {
                    errors.add(new ErrorDetail(rowNumber, "DateOfBirth is missing"));
                } else if (!isValidDate(dob)) {
                    errors.add(new ErrorDetail(rowNumber, "Invalid DateOfBirth (yyyy-MM-dd)"));
                }
            }

        } catch (Exception e) {
            errors.add(new ErrorDetail(0, "Error processing file"));
        }

        return errors.isEmpty()
                ? new ApiResponse("success", errors)
                : new ApiResponse("error", errors);
    }

   //Date Validation (FIXED)
    private boolean isValidDate(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter
                    .ofPattern("yyyy-MM-dd") 
                    .withResolverStyle(ResolverStyle.STRICT);

            LocalDate.parse(date.trim(), formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}