package com.zip.service;

import com.zip.pojo.ZippingData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ZippingServiceTest {

    @Autowired
    private ZippingService zippingService;

    @Test
    public void positivePerformShippingCode() {
        List<ZippingData> zipcodeDataList = Arrays.asList(new ZippingData(94133, 94133),
                new ZippingData(94200, 94299),
                new ZippingData(94300, 94399));
        List<ZippingData> calculatedZipCodeList = zippingService.performZipping(zipcodeDataList);
        assertNotNull(calculatedZipCodeList);
        assertEquals(3, calculatedZipCodeList.size());
        assertEquals(94133, calculatedZipCodeList.get(0).getLowerRangeData());
    }

    @Test
    public void positivePerformShippingCodeWithOverlap() {
        List<ZippingData> zipcodeDataList = Arrays.asList(new ZippingData(94133, 94133),
                new ZippingData(94200, 94299),
                new ZippingData(94266, 94399));
        List<ZippingData> calculatedZipCodeList = zippingService.performZipping(zipcodeDataList);
        assertNotNull(calculatedZipCodeList);
        assertEquals(2, calculatedZipCodeList.size());
        assertEquals(94200, calculatedZipCodeList.get(1).getLowerRangeData());
    }
}
