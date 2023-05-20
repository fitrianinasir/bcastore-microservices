package com.shop.customers.controller;


import com.shop.customers.dto.CustomerDTO;
import com.shop.customers.dto.MessageResponseDTO;
import com.shop.customers.model.CustomerModel;
import com.shop.customers.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    public @ResponseBody ResponseEntity<CustomerDTO> getAllCustomers(){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setStatus(200);
        customerDTO.setMessage("Successfully retrieved customers data");
        customerDTO.setData(customerRepository.findAll());
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public @ResponseBody ResponseEntity<CustomerDTO> getCustomerById(@PathVariable("id") Integer id){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setStatus(200);
        customerDTO.setMessage("Data retrieved successfully");
        customerDTO.setData(customerRepository.findById(id));
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @PostMapping("/customer/create")
    public @ResponseBody ResponseEntity<Object> createCustomer(@RequestBody CustomerModel customerModel){
        if(customerRepository.existsByEmail(customerModel.getEmail())){
            MessageResponseDTO messageResponseDTO = new MessageResponseDTO();
            messageResponseDTO.setMessage("Email is already taken!");
            return new ResponseEntity<>(messageResponseDTO, HttpStatus.BAD_REQUEST);
        }else if(customerRepository.existsByAccountNumber(customerModel.getAccountNumber())){
            MessageResponseDTO messageResponseDTO = new MessageResponseDTO();
            messageResponseDTO.setMessage("Account Number is already registered!");
            return new ResponseEntity<>(messageResponseDTO, HttpStatus.BAD_REQUEST);
        }

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setStatus(200);
        customerDTO.setMessage("Customer created successfully");
        customerDTO.setData(customerRepository.save(customerModel));
        return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);
    }

    @PutMapping("/customer/update/{id}")
    public @ResponseBody ResponseEntity<Object> updateCustomer(
            @PathVariable("id") Integer id,
            @RequestBody CustomerModel customerModel
    ){
        if(customerRepository.existsByEmail(customerModel.getEmail())){
            CustomerModel checkExistingEmail = customerRepository.findByEmail(customerModel.getEmail());
            if(customerModel.getId() == checkExistingEmail.getId()){
                customerModel.setId(id);
                CustomerDTO customerDTO = new CustomerDTO();
                customerDTO.setStatus(200);
                customerDTO.setMessage("Data updated successfully");
                customerDTO.setData(customerRepository.save(customerModel));
                return new ResponseEntity<>(customerDTO, HttpStatus.OK);
            }

            MessageResponseDTO messageResponseDTO = new MessageResponseDTO();
            messageResponseDTO.setMessage("Email is already taken!");
            return new ResponseEntity<>(messageResponseDTO, HttpStatus.BAD_REQUEST);
        }

        if(customerRepository.existsByAccountNumber(customerModel.getAccountNumber())){
            CustomerModel checkExistingAccountNumber = customerRepository.findByAccountNumber(customerModel.getAccountNumber());
            if(customerModel.getId() == checkExistingAccountNumber.getId()){
                customerModel.setId(id);
                CustomerDTO customerDTO = new CustomerDTO();
                customerDTO.setStatus(200);
                customerDTO.setMessage("Data updated successfully");
                customerDTO.setData(customerRepository.save(customerModel));
                return new ResponseEntity<>(customerDTO, HttpStatus.OK);
            }

            MessageResponseDTO messageResponseDTO = new MessageResponseDTO();
            messageResponseDTO.setMessage("Account Number is already registered!");
            return new ResponseEntity<>(messageResponseDTO, HttpStatus.BAD_REQUEST);
        }

        if(customerRepository.findById(id).isEmpty()){
            MessageResponseDTO messageResponseDTO = new MessageResponseDTO();
            messageResponseDTO.setMessage("User Not Found!");
            return new ResponseEntity<>(messageResponseDTO, HttpStatus.BAD_REQUEST);
        }

        customerModel.setId(id);
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setStatus(200);
        customerDTO.setMessage("Data updated successfully");
        customerDTO.setData(customerRepository.save(customerModel));
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @DeleteMapping("/customer/delete/{id}")
    public ResponseEntity<MessageResponseDTO> deleteCustomer(@PathVariable("id") Integer id){

        if(customerRepository.findById(id).isEmpty()){
            MessageResponseDTO messageResponseDTO = new MessageResponseDTO();
            messageResponseDTO.setMessage("User Not Found!");
            return new ResponseEntity<>(messageResponseDTO, HttpStatus.BAD_REQUEST);
        }

        customerRepository.deleteById(id);
        MessageResponseDTO messageResponseDTO = new MessageResponseDTO();
        messageResponseDTO.setMessage("Data with id " + Integer.toString(id) + " deleted successfully");
        return new ResponseEntity<>(messageResponseDTO, HttpStatus.OK);

    }
}
