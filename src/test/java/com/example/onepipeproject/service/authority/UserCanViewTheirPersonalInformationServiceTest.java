package com.example.onepipeproject.service.authority;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserCanViewTheirPersonalInformationServiceTest {
    @InjectMocks
    UserCanViewTheirPersonalInformationService userCanViewTheirPersonalInformationService;

    @Test
    void testIsEligibleUsingTwoDifferentUserIdShouldReturnFalse(){
        long authorizedUserId = 1L;
        long userId = 3L;

        boolean valid =userCanViewTheirPersonalInformationService.isLicensed(authorizedUserId,userId);
        assertFalse(valid);
    }

    @Test
    void testIsEligibleUsingTwoSameUserIdShouldReturnTrue(){
        long authorizedUserId = 1L;
        long userId = 1L;

        boolean valid =userCanViewTheirPersonalInformationService.isLicensed(authorizedUserId,userId);
        assertTrue(valid);
    }

}