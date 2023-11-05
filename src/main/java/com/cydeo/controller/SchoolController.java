package com.cydeo.controller;

import com.cydeo.dto.AddressDTO;
import com.cydeo.dto.ResponseWrapper;
import com.cydeo.dto.TeacherDTO;
import com.cydeo.service.AddressService;
import com.cydeo.service.ParentService;
import com.cydeo.service.StudentService;
import com.cydeo.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SchoolController {

    private final TeacherService teacherService;
    private final StudentService studentService;
    private final ParentService parentService;
    private final AddressService addressService;

    public SchoolController(TeacherService teacherService, StudentService studentService, ParentService parentService, AddressService addressService) {
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.parentService = parentService;
        this.addressService = addressService;
    }

    @GetMapping("/teachers")
    public List<TeacherDTO> readAllTeacher(){
        List<TeacherDTO> teachers = teacherService.findAll();
        return teachers;
    }

    @GetMapping("/students")
    public ResponseEntity<ResponseWrapper> readAllStudents(){
        return ResponseEntity.ok(new ResponseWrapper("Students are succesfully retrieved",studentService.findAll())); // ok method. You can directly give your data inside this method
    }

    @GetMapping("/parents")
    public ResponseEntity<ResponseWrapper> readAllParents(){
        ResponseWrapper responseWrapper =
                new ResponseWrapper(true,"Parents are retrieved successfully", HttpStatus.OK.value(), parentService.findAll());
        return ResponseEntity.status(HttpStatus.OK).body(responseWrapper); // status method
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<ResponseWrapper> getAddress(@PathVariable("id") Long id) throws Exception{
        AddressDTO addressDTO = addressService.findById(id);
        return ResponseEntity.ok(new ResponseWrapper("Address is succesfully retrieved",addressDTO));
    }

    @PutMapping("/address/{id}")
    public AddressDTO updateAddress(@PathVariable("id") Long id, @RequestBody AddressDTO addressDTO) throws Exception {
        addressDTO.setId(id); //because I used @JsonIgnore annotation on top of "AddressDTO id field". So it cannot capture "id", if I don't use this set method!
        return addressService.update(addressDTO);
    }


}
