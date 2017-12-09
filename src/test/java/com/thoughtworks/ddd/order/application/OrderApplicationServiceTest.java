package com.thoughtworks.ddd.order.application;

import com.thoughtworks.ddd.order.application.dto.OrderDTO;
import com.thoughtworks.ddd.order.domain.OrderService;
import com.thoughtworks.ddd.order.domain.order.Order;
import com.thoughtworks.ddd.order.domain.order.OrderRepository;
import com.thoughtworks.ddd.order.domain.order.OrderStatus;
import com.thoughtworks.ddd.order.domain.order.Pet;
import com.thoughtworks.ddd.order.domain.payment.Payment;
import com.thoughtworks.ddd.order.domain.payment.PaymentRepository;
import com.thoughtworks.ddd.order.domain.payment.PaymentStatus;
import com.thoughtworks.ddd.order.domain.payment.PayOrderService;
import org.hamcrest.CustomMatcher;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderApplicationServiceTest {
    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderService orderService;

    @Mock
    private PetPurchaseService petPurchaseService;

    @Mock
    private PayOrderService paymentService;

    @InjectMocks
    private OrderApplicationService orderApplicationService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_able_to_create_the_order() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setName("Ryan");
        orderDTO.setProvince("Sichuan");
        orderDTO.setCity("Chengdu");
        orderDTO.setArea("");
        orderDTO.setStreet("");
        orderDTO.setMoreDetails("");
        orderDTO.setPrice(10);
        orderDTO.setAmount(1);
        orderDTO.setDescription("");
        orderDTO.setBrand("");
        orderDTO.setPetId("123");

        Order order = new Order();
        order.setId(123456L);
        order.setPet(new Pet(orderDTO.getPrice(), orderDTO.getAmount(), orderDTO.getDescription(), orderDTO.getPetId()));

        when(orderService.createOrder(anyObject())).thenReturn(order);

        Order savedOrder = orderApplicationService.bookPet(new Order(orderDTO.getCustomer(), orderDTO.getShop(), orderDTO.getPet()));

        assertThat(savedOrder.getId()).isEqualTo(123456L);

        verify(petPurchaseService).lockPetOfOrder(orderDTO.getPetId());
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