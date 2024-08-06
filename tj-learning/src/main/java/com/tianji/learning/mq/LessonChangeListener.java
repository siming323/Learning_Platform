package com.tianji.learning.mq;

import com.tianji.api.dto.trade.OrderBasicDTO;
import com.tianji.common.constants.MqConstants;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class LessonChangeListener {

    final ILearningLessonService lessonService;

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value= "learning.lesson.pay.queue", durable = "true"),
            exchange = @Exchange(value = MqConstants.Exchange.ORDER_EXCHANGE, type = ExchangeTypes.TOPIC),
            key = MqConstants.Key.ORDER_PAY_KEY))

    public void onMsg(OrderBasicDTO dto) {
        //verify
        if (dto.getUserId() == null || dto.getOrderId() == null || dto.getCourseIds() == null || dto.getCourseIds().size() == 0) {
            return;
        }
        //use service to store courses
        lessonService.addUserLesson(dto.getUserId(), dto.getCourseIds());
    }
}
