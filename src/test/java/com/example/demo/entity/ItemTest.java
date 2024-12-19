package com.example.demo.entity;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;


class ItemTest {

    @Test
    void nullableTest1() {
        // GIVEN
        User user1 = new User("user", "test1@test.com", "kim1", "sdf");
        User user2 = new User("user", "test2@test.com", "kim2", "sdf");

        // WHEN
        Item item = new Item("kim", "하이여", user1, user2);

        // THEN
        assertNull(item.getStatus());
    }
}