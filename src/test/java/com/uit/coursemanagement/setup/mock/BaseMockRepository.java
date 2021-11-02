package com.uit.coursemanagement.setup.mock;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * BaseMockRepository
 *
 * @author VuKhanh [vukhanh.nguyen@citynow.vn]
 * @since 11/2/2021
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public interface BaseMockRepository<Input> {
    void setUpMock(Input input);
}