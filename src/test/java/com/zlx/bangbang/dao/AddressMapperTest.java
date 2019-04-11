package com.zlx.bangbang.dao;

import com.zlx.bangbang.domain.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressMapperTest {
    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    public void findAddressByUserIdAndIsDeletedIsFalse() {
        List<Address> address = addressMapper.findAddressByUserIdAndIsDeletedIsFalse("pJXIOQ");
        System.out.println(address);
        for (Address a : address) {
            System.out.println(a.toString());
        }
    }

    @Test
    public void findAllByUserIdAndAddressLikeAndIsDeletedIsFalse() {
        List<Address> addresses = addressMapper.findAllByUserIdAndAddressLikeAndIsDeletedIsFalse("pjXIOQ",
                "%不%");
        System.out.println(addresses);
    }
}