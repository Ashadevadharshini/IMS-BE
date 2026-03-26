package com.example.interview_scheduler;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootTest
        (properties = {"spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"})
class InterviewSchedulerApplicationTests {

	@Test
	void contextLoads() {
	}

}