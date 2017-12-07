package com.ThoughtWorks.DDD.Order.Application;

import com.ThoughtWorks.DDD.Order.domain.order.Order;
import com.ThoughtWorks.DDD.Order.domain.order.OrderRepository;
import com.ThoughtWorks.DDD.Order.domain.order.OrderStatus;
import com.ThoughtWorks.DDD.Order.domain.payment.Payment;
import com.ThoughtWorks.DDD.Order.domain.payment.PaymentRepository;
import com.ThoughtWorks.DDD.Order.domain.payment.PaymentStatus;
import com.ThoughtWorks.DDD.Order.domain.payment.PayOrderService;
import org.hamcrest.CustomMatcher;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderApplicationServiceTest {
    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private PayOrderService paymentService;

    @InjectMocks
    private OrderApplicationService orderApplicationService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_be_able_to_pay_the_created_order() throws Exception {
        long orderId = 123456L;
        Order order = new Order();
        order.setId(orderId);
        when(orderRepository.findOne(orderId)).thenReturn(order);
        when(paymentService.payOrder(order)).thenReturn(new Payment(orderId, PaymentStatus.PAID));

        orderApplicationService.payOrder(orderId);

        verify(paymentRepository).save(argThat(new CustomMatcher<Payment>("has paid.") {
            @Override
            public boolean matches(Object item) {
                Payment payment = (Payment) item;
                return payment.getPaymentStatus() == PaymentStatus.PAID;
            }
        }));

        verify(orderRepository).save(argThat(new CustomMatcher<Order>("Order has been paid.") {
            @Override
            public boolean matches(Object o) {
                Order order = (Order) o;
                return order.getOrderStatus() == OrderStatus.COMPLETED;
            }
        }));
    }
}