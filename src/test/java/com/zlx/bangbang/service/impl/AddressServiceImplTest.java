package com.zlx.bangbang.service.impl;

import com.zlx.bangbang.domain.Address;
import com.zlx.bangbang.dto.AddressDTO;
import com.zlx.bangbang.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceImplTest {
    @Autowired
    private AddressService addressService;

    private Address address;

    private AddressDTO addressDTO;

    @Before
    public void setUp() throws Exception {
        address = Address.builder().address("美国硅谷中国郭杜").isDeleted(false).phone(Long.valueOf("12222222222"))
                .userId("A12345").userName("李华").build();
        addressDTO = new AddressDTO();
        addressDTO.setAddress("美国硅谷中国郭杜");
        addressDTO.setPhone(Long.valueOf("12222222222"));
        addressDTO.setUserId("A12345");
        addressDTO.setUserName("李华");
    }

    @Test
    public void addUsualAddress() throws AccessDeniedException {
        String userId = "A12345";
        addressService.addUsualAddress(userId, addressDTO);
    }

    @Test
    public void getById() {
        Integer addressId = 75;
        Address actualAddress = addressService.getById(addressId);
        Assert.assertEquals("美国硅谷中国郭杜", actualAddress.getAddress());
    }

    @Test
    public void getUsualAddress() {
        List<Address> addresses = addressService.getUsualAddress("A12345");
        Assert.assertEquals(Integer.valueOf(71), addresses.get(0).getId());
    }

    @Test
    public void getAllByAddress() {
        List<Address> addresses = addressService.getAllByAddress("A12345", "西安");
        Assert.assertEquals(1, addresses.size());
    }

    @Test
//    @Transactional(rollbackFor = Exception.class)
    public void modifyAddress() throws AccessDeniedException {
        addressDTO = new AddressDTO();
        addressDTO.setAddress("美国硅谷中国郭杜在长安区");
        addressDTO.setPhone(Long.valueOf("12222222222"));
        addressDTO.setUserId("A12345");
        addressDTO.setUserName("李华");
        Integer addressId = 75;
        String userId = "A12345";
        addressService.modifyAddress(addressId, userId, addressDTO);
    }

    @Test
//    @Transactional(rollbackFor = Exception.class)
    public void deleteAddress() throws AccessDeniedException {
        Integer addressId = 62;
        String userId = "KCWU77";
        addressService.deleteAddress(addressId, userId);
    }
}