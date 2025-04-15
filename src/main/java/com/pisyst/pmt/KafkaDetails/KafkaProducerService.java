package com.pisyst.pmt.KafkaDetails;

import com.pisyst.pmt.dtos.EmployeeDTO;
import com.pisyst.pmt.common.EmployeeOnboardedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, EmployeeOnboardedEvent> kafkaTemplate;

    @Value("${company.hr.dl}")
    private String hrEmail;

    @Value("${company.manager.dl}")
    private String managerEmail;

    private static String ONBOARDING_TOPIC= "employee-onboarding-topic";

    public KafkaProducerService(KafkaTemplate<String, EmployeeOnboardedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEmployeeOnboardedEvent(EmployeeDTO dto) {
        try {
            EmployeeOnboardedEvent event = EmployeeOnboardedEvent.builder()
                    .employeeName(dto.getFirstName() + " " + dto.getLastName())
                    .employeeEmail(dto.getEmail())
                    .department(dto.getDepartment())
                    .doj(dto.getDoj())
                    .managerEmail(managerEmail).build();

            kafkaTemplate.send(ONBOARDING_TOPIC, event);
            log.info("Event Send : {} ", event.toString());
            log.info("Sent Kafka message to employee-onboarding-topic");
        } catch (Exception e) {
            log.info("Exception while sending the event to the Listener: {}", e.getMessage());
        }

    }
}
